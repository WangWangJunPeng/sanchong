package com.sc.tradmaster.service.agent.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.SignRecords;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.agent.AgentVisitRecordService;
import com.sc.tradmaster.service.agent.impl.visitDTO.Customer;
import com.sc.tradmaster.service.agent.impl.visitDTO.ProjectCustomerDTO;
import com.sc.tradmaster.service.agent.impl.visitDTO.UserDTO;
import com.sc.tradmaster.service.dynamic.DynamicUtil;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.SysContent;

/**
 * 2017-02-03
 * @author grl
 *
 */
@Service("agentVisitRecordService")
public class VisitRecordServiceImpl implements AgentVisitRecordService {

	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	@Override
	public List<VisitRecords> findVisitInfoByUser(User user,String data) {
		if(user!=null && user.getParentId()!=null && !user.getParentId().equals("")){
			String hql = "from VisitRecords where projectId = '"+user.getParentId()+"' and "
					+ "userId = '"+user.getUserId()+"' and visitStatus !=2 and arriveTime like '%"+data+"%' and writeState is null";
			return baseDao.findByHql(hql);
		}else{
			return new ArrayList<VisitRecords>();
		}
		
	}

	@Override
	public ProjectCustomerDTO findCustomerInfoByPhone(User u ,String phone) {
		if(phone!=null && !phone.equals("")){
			String hql = "from ProjectCustomers where projectId = '"+u.getParentId()+"' and projectCustomerPhone = '"+phone+"'";
			ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);
			User agent = u;
			if(pc!=null){
				if(pc.getOwnerUserId()!=null){
					agent = (User) baseDao.loadById(User.class, pc.getOwnerUserId());
				}
				ProjectCustomerDTO pcDto = new ProjectCustomerDTO();
				ProjectCustomerDTO creatyPcDto = pcDto.creatyProjectCustomer(pc,agent);
				return creatyPcDto;
			}
		}
		return null;
	}
	
	@Override
	public void addorUpdataVistInfo(User user, Integer visitNo, String phone){
		if(user!=null && phone!=null && !phone.equals("")){
			VisitRecords vr = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
			vr.setPhone(phone);
			baseDao.saveOrUpdate(vr);
		}
	}

	@Override
	public ProjectCustomers addOrUpdateAgentInsertCustomerInfo(User user,String cName, String phone,String desc,Integer visitNo) throws ParseException {
		if(user!=null && phone!=null && !phone.equals("")){
			ProjectCustomers procu = new ProjectCustomers();
			//获取当前phone的用户信息
			String hql ="from ProjectCustomers where projectId = '"+user.getParentId()+"' and projectCustomerPhone = '"+phone+"'";
			ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);
			//获取该项目带看信息
			String chql = "from ProjectGuide where projectId = '"+user.getParentId()+"'";
			ProjectGuide pg = (ProjectGuide) baseDao.loadObject(chql);
			//获取到访记录信息
			VisitRecords vsr = (VisitRecords) baseDao.loadById(VisitRecords.class, visitNo);
			//获取该项目的客户保护期
			int cpd = Integer.parseInt(pg.getCustormerProtectDays());
			//获取客户的当前到访时间
			String arriveTime = vsr.getArriveTime();
			//判断是否超过客户保护期
			Boolean noOut = false;
			if(pc!=null){
				//客户上一次的到访时间
				String lastTime = pc.getLastTime();
				noOut = SysContent.addSameMouthComperWithToday(arriveTime,lastTime,cpd);
			}
			//判断该用户是否为该案场客户,pc!=null,则该用户为当前案场的客户
			if(pc!=null && noOut){
				// 将案场客户的id回填到到访记录表中
				vsr.setCustomerId(pc.getProjectCustomerId());
				// 更新案场客户表
				pc.setProjectCustomerName(cName);
				pc.setProjectCustomerPhone(phone);
				pc.setDescription(desc);
				pc.setLastTime(vsr.getArriveTime());
				// 持久化案场客户信息
				baseDao.saveOrUpdate(pc);
			}else{
				//获取带看备案信息
				String cId = SysContent.uuid();
				String grhql = "from GuideRecords where projectId = '"+user.getParentId()+"' and customerPhone = '"+phone+"'";
				GuideRecords gr = (GuideRecords) baseDao.loadObject(grhql);
				//该用户在带看备案记录中存在且为申请状态时
				if(gr!=null && gr.getApplyStatus().equals(0)){
					//获取带看是否有效
					//①获取带看记录中的申请时间
					String applyTime = gr.getApplyTime();
					//②获取带看有效天数
					int vd = pg.getValidDays();
					//通过(到访时间<=有效天数+申请时间)，判断此次带看是否有效（单位：天）
					Boolean NoOutOf = SysContent.addSameDaysComperWithToday(applyTime,arriveTime,vd);
					if(NoOutOf){
						//更新带看备案记录中客户的申请状态为1:确认状态
						gr.setApplyStatus(1);
						gr.setVisitNo(visitNo);
						//给签约成功的客户添加动态
						Project p = (Project) baseDao.loadById(Project.class,user.getParentId());//获取项目信息
						String otherMdcStr = "您的客户:"+cName+"到访案场。";
						Mydynamic otherMdc = DynamicUtil.createOneDynamic(user.getUserId(), user.getPhone(), otherMdcStr, 0, DateTime.toString1(new Date()),
								p.getProjectId(),null,"到访案场","0",cId,cName,phone,p.getProjectName());
						baseDao.saveOrUpdate(otherMdc);
					}else{
						//超过备案有效期，修改备案状态为失败
						gr.setApplyStatus(4);
					}
					baseDao.saveOrUpdate(gr);
				}
				//以上条件不成立时和成立时都要执行的逻辑
				
				// 将到访记录表中的填写转台更改为1
				vsr.setWriteState(1);
				if(pc!=null){
					// 将案场客户的id回填到到访记录表中
					vsr.setCustomerId(pc.getProjectCustomerId());
					
					// 更新案场客户表
					pc.setProjectCustomerName(cName);
					pc.setProjectCustomerPhone(phone);
					pc.setDescription(desc);
					pc.setLastTime(vsr.getArriveTime());
					// 持久化案场客户信息
					baseDao.saveOrUpdate(pc);
				}else{
					//新增一个客户
					// 新增一个案场客户
					procu = new ProjectCustomers();
					//String cId = SysContent.uuid();
					procu.setProjectId(user.getParentId());
					procu.setCreateUserId(user.getUserId());
					procu.setCreateTime(DateTime.toString1(new Date()));
					procu.setOwnerUserId(user.getUserId());
					procu.setOwnerStartTime(DateTime.toString1(new Date()));
					procu.setProjectCustomerId(cId);
					procu.setProjectCustomerName(cName);
					procu.setProjectCustomerPhone(phone);
					procu.setDescription(desc);
					procu.setLastTime(vsr.getArriveTime());
					// 持久化案场客户信息
					baseDao.saveOrUpdate(procu);
					// 将案场客户的id回填到到访记录表中
					vsr.setCustomerId(cId);
				}
			}
			//将到访记录保存到到访记录表中
			vsr.setVisitStatus(1);
			vsr.setCustomerName(cName);
			vsr.setPhone(phone);
			vsr.setReceptTime(DateTime.toString1(new Date()));
			// 将到访记录表中的填写转台更改为1
			vsr.setWriteState(1);
			// 持久化客户到访信息
			baseDao.saveOrUpdate(vsr);
			return procu;
		}
		return null;
	}

	/**
	 * 通过projectId获得当前案场的所有置业顾问列表
	 * @return
	 */
	@Override
	public List findAgents(String projectId) {
		String hql = "from User where parentId = '" + projectId + "' and userStatus = 1";
		List<User> list = baseDao.findByHql(hql);
		List<UserDTO> udtoList = new ArrayList<>();
		for (User user : list) {
			Set<Role> roleId = user.getRoleId();
			for (Role role : roleId) {
				if (role.getRoleId() == 3) {
					UserDTO udto = new UserDTO();
					UserDTO uu = udto.creatUserDTO(user);
					udtoList.add(uu);
				}
			}
		}
		return udtoList;
	}

	/**
	 * 获取签到签退记录
	 * @return 
	 */
	@Override
	public List<SignRecords> findAllSignAndOutRecordList(User user, String projectId, String checkTime) {
			Date date = DateUtil.parse(checkTime);
			String start = SysContent.getStartTime2(date);
			String end = SysContent.getEndTime2(date);
			String hql = "from SignRecords where parentId = '"+projectId+"' and signInTime >='"+start+"' or signOutTime <= '"+end+"'";
			return baseDao.findByHql(hql);
	}

	@Override
	public Integer addOrUpdateVisitReocrdFromExcel(VisitRecords vr) {
		if(vr!=null){
			if(vr.getVisitNo()!=null && !vr.getVisitNo().equals("")){
				VisitRecords newVr = (VisitRecords) baseDao.loadById(VisitRecords.class, vr.getVisitNo());
				//持久化对象
				if(newVr==null){
					//判断vr有没有phone
					if(vr.getPhone()!=null && !vr.getPhone().equals("")){
						String proCusHql = "from ProjectCustomers where projectId = '"+vr.getProjectId()+"' and projectCustomerPhone = '"+vr.getPhone()+"'";
						ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(proCusHql);
						if(pc!=null){
							pc.setLastTime(vr.getArriveTime());
							//更新该用户信息
							baseDao.saveOrUpdate(pc);
							//回填案场客户id到到访记录中
							vr.setCustomerId(pc.getProjectCustomerId());
							vr.setCustomerName(pc.getProjectCustomerName());
						}else{
							ProjectCustomers newPc = new ProjectCustomers();
							String customerId = SysContent.uuid();
							newPc.setProjectCustomerId(customerId);
							newPc.setCreateTime(vr.getArriveTime());
							if(vr.getUserId()!=null){
								newPc.setCreateUserId(vr.getUserId());
								newPc.setOwnerUserId(vr.getUserId());
							}
							if(vr.getAppointUserId()!=null){
								newPc.setOwnerUserId(vr.getAppointUserId());
							}
							if(vr.getCustomerName()!=null){
								newPc.setProjectCustomerName(vr.getCustomerName());
							}
							if(vr.getPhone()!=null){
								newPc.setProjectCustomerPhone(vr.getPhone());
							}
							if(vr.getProjectId()!=null){
								newPc.setProjectId(vr.getProjectId());
							}
							if(vr.getArriveTime()!=null){
								newPc.setLastTime(vr.getArriveTime());
								newPc.setOwnerStartTime(vr.getArriveTime());
							}
							//添加新的案场客户
							baseDao.saveOrUpdate(newPc);
							//将新客户的id回填到到访记录表
							vr.setCustomerId(customerId);
						}
						
					}
					//保存新的到访记录
					baseDao.save(vr);
				}else{
					if(vr.getArriveTime()!=null){
						newVr.setArriveTime(vr.getArriveTime());
					}
					if(vr.getReceptTime()!=null){
						newVr.setReceptTime(vr.getReceptTime());
					}
					if(vr.getPhone()!=null){
						newVr.setPhone(vr.getPhone());
					}
					if(vr.getProjectId()!=null){
						newVr.setProjectId(vr.getProjectId());
					}
					if(vr.getUserId()!=null){
						newVr.setUserId(vr.getUserId());
					}
					if(vr.getVisitStatus()!=null){
						newVr.setVisitStatus(vr.getVisitStatus());
					}
					if(vr.getCustomerCount()!=null){
						newVr.setCustomerCount(vr.getCustomerCount());
					}
					if(vr.getLeaveTime()!=null){
						newVr.setLeaveTime(vr.getLeaveTime());
					}
					if(vr.getPhone()!=null && !vr.getPhone().equals("")){
						//更新到访记录时，可能存在customid不存在的情况，这是需要通过phone去load客户，如果客户不存在则添加客户信息，并将costormid回填到到访记录中
						if(newVr.getCustomerId()!=null && !newVr.getCustomerId().equals("")){
							//获取案场客户信息
							ProjectCustomers newPc = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class, newVr.getCustomerId());
							if(newPc!=null){
								//更新该客户的最后到访记录
								newPc.setLastTime(newVr.getArriveTime());
								newPc.setProjectCustomerPhone(newVr.getPhone());
								baseDao.saveOrUpdate(newPc);
							}
						}else{
							String proCusHql = "from ProjectCustomers where projectId = '"+vr.getProjectId()+"' and projectCustomerPhone = '"+vr.getPhone()+"'";
							ProjectCustomers newPc = (ProjectCustomers) baseDao.loadObject(proCusHql);
							if(newPc!=null){
								//更新该客户的最后到访记录
								newPc.setLastTime(newVr.getArriveTime());
								newPc.setProjectCustomerPhone(newVr.getPhone());
								baseDao.saveOrUpdate(newPc);
								newVr.setCustomerId(newPc.getProjectCustomerId());
								newVr.setCustomerName(newPc.getProjectCustomerName());
							}else{
								ProjectCustomers newPc1 = new ProjectCustomers();
								String customerId = SysContent.uuid();
								newPc1.setProjectCustomerId(customerId);
								newPc1.setCreateTime(vr.getArriveTime());
								if(vr.getUserId()!=null){
									newPc1.setCreateUserId(vr.getUserId());
									newPc1.setOwnerUserId(vr.getUserId());
								}
								if(vr.getAppointUserId()!=null){
									newPc1.setOwnerUserId(vr.getAppointUserId());
								}
								if(vr.getCustomerName()!=null){
									newPc1.setProjectCustomerName(vr.getCustomerName());
								}
								if(vr.getPhone()!=null){
									newPc1.setProjectCustomerPhone(vr.getPhone());
								}
								if(vr.getProjectId()!=null){
									newPc1.setProjectId(vr.getProjectId());
								}
								if(vr.getArriveTime()!=null){
									newPc1.setLastTime(vr.getArriveTime());
									newPc1.setOwnerStartTime(vr.getArriveTime());
								}
								//添加新的案场客户
								baseDao.saveOrUpdate(newPc1);
								//将新客户的id回填到到访记录表
								newVr.setCustomerId(customerId);
								if(vr.getIsNew()!=null && !vr.getIsNew().equals("")){
									newVr.setIsNew(vr.getIsNew());
								}else{
									newVr.setIsNew(true);
								}
							}
						}
						
					}
					baseDao.saveOrUpdate(newVr);
				}
			}else{
				if(vr.getPhone()!=null && !vr.getPhone().equals("")){
					String proCusHql = "from ProjectCustomers where projectId = '"+vr.getProjectId()+"' and projectCustomerPhone = '"+vr.getPhone()+"'";
					ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(proCusHql);
					if(pc!=null){
						pc.setLastTime(vr.getArriveTime());
						pc.setProjectCustomerPhone(vr.getPhone());
						pc.setOwnerUserId(vr.getAppointUserId());
						baseDao.saveOrUpdate(pc);
						vr.setCustomerId(pc.getProjectCustomerId());
						vr.setCustomerName(pc.getProjectCustomerName());
					}else{
						ProjectCustomers newPc = new ProjectCustomers();
						String customerId = SysContent.uuid();
						newPc.setProjectCustomerId(customerId);
						newPc.setCreateTime(vr.getArriveTime());
						if(vr.getUserId()!=null){
							newPc.setCreateUserId(vr.getUserId());
							newPc.setOwnerUserId(vr.getUserId());
						}
						if(vr.getAppointUserId()!=null){
							newPc.setOwnerUserId(vr.getAppointUserId());
						}
						if(vr.getCustomerName()!=null){
							newPc.setProjectCustomerName(vr.getCustomerName());
						}
						if(vr.getPhone()!=null){
							newPc.setProjectCustomerPhone(vr.getPhone());
						}
						if(vr.getProjectId()!=null){
							newPc.setProjectId(vr.getProjectId());
						}
						if(vr.getArriveTime()!=null){
							newPc.setLastTime(vr.getArriveTime());
							newPc.setOwnerStartTime(vr.getArriveTime());
						}
						
						//添加新的案场客户
						baseDao.saveOrUpdate(newPc);
						//将新客户的id回填到到访记录表
						vr.setCustomerId(customerId);
						if(vr.getIsNew()!=null && !vr.getIsNew().equals("")){
							vr.setIsNew(vr.getIsNew());
						}else{
							vr.setIsNew(true);
						}
					}
				}
				baseDao.saveOrUpdate(vr);
			}
			
			
			// 如果phone在带看备案记录里，则把到访编号更新到带看备案记录里
			if (vr.getPhone() != null && !vr.getPhone().equals("")) {
				String hql = "from GuideRecords where projectId = '" + vr.getProjectId() + "' and customerPhone = '"
						+ vr.getPhone() + "'";
				GuideRecords gr = (GuideRecords) baseDao.loadObject(hql);
				if(gr!=null){
					gr.setVisitNo(vr.getVisitNo());
					baseDao.saveOrUpdate(gr);
				}
			}
			return vr.getVisitNo();
		}
		return null;
	}

	@Override
	public Customer findCustomerVisitRecord(String projectId, String phone) {
		String hql = "from ProjectCustomers pc where pc.projectId = '" + projectId + "' "
				+ " and pc.projectCustomerPhone = '" + phone + "'";
		ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);
		
		String vHQL = "select count(*) from VisitRecords vr where vr.projectId = '" + projectId + "' and vr.phone = '" + phone + "'";
		//List<VisitRecords> vrList = baseDao.findByHql(vHQL);
		int count = baseDao.countQuery(vHQL);
		Customer c = null;
		if (pc != null) {
			c = new Customer();
			c.setUserId(pc.getOwnerUserId());
			c.setCustomerName(pc.getProjectCustomerName());
			c.setFirstVisitTime(pc.getCreateTime());
			c.setLastVisitTime(pc.getLastTime());
			c.setVisitCount(count);
		}
		return c;
	}
	@Override
	public void addSignInAndUpdateSignOut(User user,Integer type,String projectId,String userId,String time) {
			String signHql = "from SignRecords where userId = '"+user.getUserId()+"' and parentId = '"+projectId+"' "
					+ "and (signInTime like '%"+DateTime.toString(DateUtil.parse(time))+"%' or "
							+ "signOutTime like '%"+DateTime.toString(DateUtil.parse(time))+"%')";
			SignRecords sr = (SignRecords) baseDao.loadObject(signHql);
			if(sr==null){
				sr = new SignRecords();
				sr.setParentId(projectId);
				sr.setUserId(userId);
				if(type.equals(0)){
					sr.setSignInTime(time);
				}else if(type.equals(1)){
					sr.setSignOutTime(time);
				}
			}else{
				if(type.equals(0)){
					sr.setSignInTime(time);
				}else if(type.equals(1)){
					sr.setSignOutTime(time);
				}
			}
			baseDao.saveOrUpdate(sr);
	}
}
