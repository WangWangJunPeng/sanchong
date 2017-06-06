package com.sc.tradmaster.service.contractRecords.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.EnterBuyManAndRealEnterBuyManRelation;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.MiniMessageWaitSend;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.ProjectPics;
import com.sc.tradmaster.bean.RealEnterBuyMan;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.ShopCustomers;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.contractRecords.ContractRecordsService;
import com.sc.tradmaster.service.contractRecords.impl.dto.AllMyContractRecordDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.ContractRecordsDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.NewContractRecordStatusDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.NewContractRecordsDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.OrderDetailsDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.RealEnterBuyDTO;
import com.sc.tradmaster.service.contractRecords.impl.dto.ShopCustomerDTO;
import com.sc.tradmaster.service.house.impl.dto.HouseListDTO;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.JavaSmsApi;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

@Service("contractRecordsService")
public class ContractRecordsServiceImpl implements ContractRecordsService {

	@Resource(name = "baseDao")
	private BaseDao baseDao;

	
	/**
	 * 中介app我的客户
	 * @throws ParseException 
	 */
	@Override
	public List<ShopCustomerDTO> findMidCustomer(User user,Integer houseNum) throws ParseException {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String hql = "from ShopCustomers sc where sc.userId = '" + user.getUserId() + "'";
		List<ShopCustomers> scList = baseDao.findByHql(hql);
		
		String houseHQL = "from House where houseNum = '"+houseNum+"'";
		House h = (House) baseDao.loadObject(houseHQL);
		List<ShopCustomerDTO> scdtoList = new ArrayList<>();
		for (ShopCustomers shopCustomers : scList) {
			ShopCustomerDTO scdto = new ShopCustomerDTO();
			scdto.setCreateTime(shopCustomers.getCreateTime());
			scdto.setDescription(shopCustomers.getDescription());
			scdto.setSex(shopCustomers.getSex());
			scdto.setShopCustomerId(shopCustomers.getShopCustomerId());
			scdto.setShopCustomerName(shopCustomers.getShopCustomerName());
			scdto.setShopCustomerPhone(shopCustomers.getShopCustomerPhone());
			scdto.setShopId(shopCustomers.getShopId());
			scdto.setUserId(shopCustomers.getUserId());
			scdto.setTags(shopCustomers.getTags());
			scdto.setIdCard(shopCustomers.getIdCard());
			String grHQL = "from GuideRecords where shopCustomerId = '"+shopCustomers.getShopCustomerId()+"' and userId = '"+user.getUserId() +"' and projectId = '"+h.getProjectId()+"' order by applyTime DESC";
			List<GuideRecords> grList = baseDao.findByHql(grHQL);
			
			if (grList.size()>0){
				for (int i = 0; i < grList.size(); i++) {
					if (grList.get(i).getApplyStatus() == 1){
						String applyTime = grList.get(i).getApplyTime();
						Date applyTimeDate = sdf.parse(applyTime);
						//根据项目id查带看表
						String pgHQL = "from ProjectGuide pg where pg.projectId = '" + h.getProjectId() + "'";
						ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
						if (pg != null && !"".equals(pg)){
							Integer validDays = pg.getValidDays();
							//到期时间
							Date daoqiTime = DateUtil.rollDay(applyTimeDate, validDays);
							long j = d.getTime() - daoqiTime.getTime();
							if (j <= 0){
								scdto.setIsChoose(1);
							}else {
								scdto.setIsChoose(0);
							}
						}
						if (grList.get(i).getApplyStatus() == 1){
							scdto.setIsChoose(1);
						}else {
							scdto.setIsChoose(0);
						}
						break;
					}else {
						scdto.setIsChoose(0);
					}
				}
			}
			scdtoList.add(scdto);
		}
		return scdtoList;
	}

	/**
	 * 中介我的客户选中
	 * 
	 */
	@Override
	public Map<String, Object> findOneCustomer(User user, Integer houseNum, String shopCustomerId) {
		// 点击选中客户,获取house对象 和客户信息
		String houseHQL = "from House h where h.houseNum = '" + houseNum + "'";
		House house = (House) baseDao.loadObject(houseHQL);
		
		String scHQL = "from ShopCustomers sc where sc.shopCustomerId = '" + shopCustomerId + "'";
		ShopCustomers sc = (ShopCustomers) baseDao.loadObject(scHQL);

		// new map
		Map<String, Object> map = new HashMap<>();
		// 房源号(id)
		map.put("houseNum", house.getHouseNum());
		// 房号
		map.put("houseNo", house.getHouseNo());
		// 预售证号+图片 JSON解析字符串
		// if (house.getPresalePermissionInfo() !=null) {
		// HashMap urlMap =
		// JSON.parseObject(house.getPresalePermissionInfo(),HashMap.class);
		// map.put("presalePermissionInfo", urlMap);
		// }
		map.put("presalePermissionInfo", house.getPresalePermissionInfo());
		// 楼栋号
		map.put("buildingNo", house.getBuildingNo());
		// 单元
		map.put("unit", house.getUnit());
		// 根据house获得houseTypeId
		String houseTypeId = house.getHouseTypeId();
		// houseTypeId);
		String htHQL = "from HouseType where houseTypeId = '" + houseTypeId + "'";
		HouseType ht = (HouseType) baseDao.loadObject(htHQL);
		if (ht != null) {
			// 户型
			map.put("housType", ht.getHousType());
		}
		// 价格
		map.put("listPrice", house.getListPrice());
		if (sc != null && !"".equals(sc)) {
			// 客户姓名
			map.put("customerName", sc.getShopCustomerName());
			// 客户电话
			map.put("customerPhone", sc.getShopCustomerPhone());
			//身份证号
		}
		return map;
	}

	/**
	 * 置业顾问我的客户选中
	 * 
	 */
	@Override
	public Map<String, Object> findSaleOneCustomer(User user, Integer houseNum, String projectCustomerId) {
		// 点击选中客户,获取house对象 和客户信息
		String houseHQL = "from House h where h.houseNum = '" + houseNum + "'";
		House house = (House) baseDao.loadObject(houseHQL);

		String hql = "from ProjectCustomers pc where pc.projectCustomerId = '" + projectCustomerId
				+ "' and pc.ownerUserId = '" + user.getUserId() + "'";
		ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);

		// new map
		Map<String, Object> map = new HashMap<>();
		// 房源号(id)
		map.put("houseNum", house.getHouseNum());
		// 房号
		map.put("houseNo", house.getHouseNo());
		// 预售证号+图片 JSON解析字符串
		// if (house.getPresalePermissionInfo() !=null) {
		// HashMap urlMap =
		// JSON.parseObject(house.getPresalePermissionInfo(),HashMap.class);
		// map.put("presalePermissionInfo", urlMap);
		// }
		map.put("presalePermissionInfo", house.getPresalePermissionInfo());
		// 楼栋号
		map.put("buildingNo", house.getBuildingNo());
		// 单元
		map.put("unit", house.getUnit());
		// 根据house获得houseTypeId
		String houseTypeId = house.getHouseTypeId();
		HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
		if (ht != null) {
			// 户型
			map.put("housType", ht.getHousType());
		}
		// 价格
		map.put("listPrice", house.getListPrice());
		// 客户姓名
		map.put("customerName", pc.getProjectCustomerName());
		// 客户电话
		map.put("customerPhone", pc.getProjectCustomerPhone());
		return map;
	}

	/**
	 * 中介提交购买申请按钮
	 */
	@Override
	public Map<String, Object> findContractApply(User user, Integer houseNum, String customerName, String customerPhone,
			Double buyPrice) {

		// 判断是置业顾问 还是中介经纪人
		Set<Role> roleSet = user.getRoleId();
		// new map
		Map<String, Object> map = new HashMap<>();
		for (Role role : roleSet) {
			Integer roleId = role.getRoleId();
			if (role.getRoleId() == 3) {
				String hql = "from ProjectCustomers pc where pc.projectId = '" + user.getParentId()
						+ "' and pc.ownerUserId = '" + user.getUserId() + "'" + "and pc.projectCustomerPhone = '"
						+ customerPhone + "'";
				ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);
				// projectCustomerId
				map.put("projectCustomerId", pc.getProjectCustomerId());
			}
		}
		// 查house对象信息
		String houseHQL = "from House h where h.houseNum = '" + houseNum + "'";
		House house = (House) baseDao.loadObject(houseHQL);
		String projectId = house.getProjectId();
		// 查认购规则
		String ebHQL = "from EnterBuy eb where eb.projectId = '" + house.getProjectId() + "'";
		EnterBuy eb = (EnterBuy) baseDao.loadObject(ebHQL);
		// 存定金
		map.put("dposit", eb.getDposit());
		// 认购规则
		map.put("description", eb.getDscription());
		// 存houseNum
		map.put("houseNum", houseNum);
		// 客户姓名
		map.put("customerName", customerName);
		// 客户电话
		map.put("customerPhone", customerPhone);
		// 申请购买价格
		if (buyPrice != null && !"".equals(buyPrice)) {
			map.put("buyPrice", buyPrice);
		} else {
			map.put("buyPrice", 0);
		}

		return map;
	}

	/**
	 * 提交申请同意和拒绝
	 * 
	 * @throws IOException
	 */
	@Override
	public void addOrUpdateContractRecords(User user, Integer houseNum, String customerName, String customerPhone,
			Double buyPrice, String projectCustomerId) throws IOException {

		String crHQL = "from ContractRecords cr where cr.customerPhone = '" + customerPhone + "' and cr.customerName= '"
				+ customerName + "'" + " and cr.houseNum = '" + houseNum + "' and isOut is null and (recordStatus = 0 or recordStatus = 1)";
		ContractRecords contract = (ContractRecords) baseDao.loadObject(crHQL);
		Date date = new Date();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (contract == null || "".equals(contract)) {
			// 获取userId
			String userId = user.getUserId();
			ContractRecords cr = new ContractRecords();

			// 设置申请人用户编码
			cr.setUserId(user.getUserId());
			// 设置房源编码
			cr.setHouseNum(houseNum);
			// 根据houseNum查出House
			String hql = "from House h where h.houseNum = '" + houseNum + "'";
			House house = (House) baseDao.loadObject(hql);
			// 设置projectId
			cr.setProjectId(house.getProjectId());
			// 设置客户姓名
			cr.setCustomerName(customerName);
			// 设置客户电话
			cr.setCustomerPhone(customerPhone);
			// 设置申请价格
			cr.setBuyPrice(buyPrice);

			// 设置申请时间
			cr.setApplyTime(sdf.format(date));
			// 设置订购记录状态为申请
			cr.setRecordStatus(0);
			
			if (projectCustomerId != null && !"".equals(projectCustomerId)) {
				// 设置projectCustomerId
				cr.setProjectCustomerId(projectCustomerId);
			}

			baseDao.saveOrUpdate(cr);
			Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
			
			//new  动态
			Mydynamic md = new Mydynamic();
			md.setUserId(user.getUserId());
			md.setUserPhone(user.getPhone());
			md.setCustomerName(customerName);
			md.setCustomerPhone(customerPhone);
			md.setIsRead(0);
			md.setCreatTime(DateTime.toString1(new Date()));
			md.setProjectId(cr.getProjectId());
			md.setProjectName(p.getProjectName());
			md.setHosueNum(cr.getHouseNum().toString());
			md.setIsDelete("0");
			md.setDynamicInfo("客户"+customerName+"已提交认购申请!");
			md.setDynamicStatus("认购申请");
			baseDao.save(md);
			// 短信发送
			// 内容（"您收到一笔来自#userName#的购买申请，购买的房源名称为#houseName#，价格为#money#元，请及时审核！"）
			String uHQL = "from User where parentId = '" + cr.getProjectId() + "'";
			// 查案场助理
			List<User> uList = baseDao.findByHql(uHQL);
			for (User user2 : uList) {
				if (user2.getUserStatus() == 1){
					Set<Role> hs = user2.getRoleId();
					for (Role role : hs) {
						if (role.getRoleId() == 4) {
							String text = SmsContext.BUY_APPLY.replace("#userName#", user.getUserCaption())
									.replace("#houseName#", house.getBuildingNo()+"栋" + house.getUnit()+"单元" + house.getFloor()+"楼"+ house.getHouseNo()+"号")
									.replace("#money#", cr.getBuyPrice().toString());
							// 调用短信发送接口，发送信息给申请人
							JavaSmsApi.sendSms(text, user2.getPhone());
						}
					}
				}
			}
		}
	}

	/**
	 * 中介签约客户
	 */
	@Override
	public List findMidDeal(User user, String projectId, String startTime, String endTime) {
		// 当前中介的user信息
		String userId = user.getUserId();
		// 查已成交信息,在时间范围内
		String crHQL = " from ContractRecords cr where cr.userId = '" + userId + "' " + " and cr.projectId = '"
				+ projectId + "' and cr.recordStatus = 5 order by contractConfirmTime DESC";
		if (startTime != null && !"".equals(startTime)) {
			crHQL += " and cr.applyTime >= '" + startTime + "'";
		}
		if (endTime != null && !"".equals(endTime)) {
			crHQL += " and cr.applyTime <= '" + endTime + "'";
		}
		List<ContractRecords> crList = baseDao.findByHql(crHQL);

		return crList;
	}

	/**
	 * 中介认购客户list
	 */
	@Override
	public List findMidBuy(User user) {
		String userId = user.getUserId();
		// 查认购记录表 (该中介的所有认购客户表信息),状态为1,案场助理同意的
		String hql = "from ContractRecords cr where cr.userId = '" + userId
				+ "' and (cr.recordStatus = 1 or cr.recordStatus = 0) ";
		List<ContractRecords> crList = baseDao.findByHql(hql);
		// new crdtoLIst
		List<ContractRecordsDTO> crdtoList = new ArrayList<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 设置价格保留两位小数
		DecimalFormat df = new DecimalFormat("#.#");
		if (crList != null) {
			for (ContractRecords cr : crList) {
				// 根据projectId查认购规则
				String ebHQL = "from EnterBuy eb where eb.projectId = '" + cr.getProjectId() + "'";
				EnterBuy eb = (EnterBuy) baseDao.loadObject(ebHQL);
				// 超时时间 (小时)
				String outOfTime = eb.getOutOfTime();
				double youxiaoxioashi = Double.parseDouble(outOfTime);
				int intyouxiao = Integer.parseInt(outOfTime);
				// 申请时间
				String auditingTime = cr.getAuditingTime();
				// 当前时间
				Date date = new Date();
				// 认购申请时间
				try {
					// new dto
					ContractRecordsDTO crdto = new ContractRecordsDTO();
					// 将订购记录表No加进去
					crdto.setRecordNo(cr.getRecordNo());
					// 设置houseNum
					crdto.setHouseNum(cr.getHouseNum());
					// 根据认购客户表对应的projectId查project
					String pHQL = "from Project p where p.projectId = '" + cr.getProjectId() + "'";
					Project project = (Project) baseDao.loadObject(pHQL);
					// 设置projectId
					crdto.setProjectId(cr.getProjectId());
					// 设置projectName
					crdto.setProjectName(project.getProjectName());
					// 设置客户姓名
					crdto.setCustomerName(cr.getCustomerName());
					String nowTime = DateTime.toString1(new Date());
					if (cr.getRecordStatus() == 1) {
						// 到期时间
						String daoqiTime = SysContent.addSameHours(auditingTime, intyouxiao);
						if ((daoqiTime.compareTo(nowTime)) >= 0) {
							Date daoqi = sdf.parse(daoqiTime);
							Date now = new Date();
							double xx = daoqi.getTime() - now.getTime();
							double hours = xx / 1000 / 60 / 60;
							// 设置剩余时间小时
							String remainingTime = df.format(hours);
							crdto.setResidueHours(remainingTime + "小时");
						} else {
							crdto.setResidueHours("已过期");
						}
						if (cr.getVoucherUploadTime() != null && !"".equals(cr.getVoucherUploadTime())){
							crdto.setResidueHours("已打款");
						}
					} else if (cr.getRecordStatus() == 0){
						crdto.setResidueHours("审核中");
					} 
					// 添加集合
					crdtoList.add(crdto);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return crdtoList;
	}

	/**
	 * 中介取消认购
	 */
	@Override
	public void updateCancelCR(User user, Integer recordNo) {
		String hql = "from ContractRecords cr where cr.recordNo = '" + recordNo + "'";
		ContractRecords cr = (ContractRecords) baseDao.loadObject(hql);

		cr.setRecordStatus(2);
		baseDao.saveOrUpdate(cr);
	}

	/**
	 * 中介确认下定
	 */
	@Override
	public Map<String, Object> findTrueBuy(User user, Integer recordNo) {
		// 根据recordNo查出订购记录表
		String crHQL = "from ContractRecords cr where cr.recordNo = '" + recordNo + "'";
		ContractRecords cr = (ContractRecords) baseDao.loadObject(crHQL);
		int houseNum = cr.getHouseNum();
		String projectCustomerId = cr.getProjectCustomerId();
		// 根据houseNum获得house对象
		String houseHQL = "from House h where h.houseNum = '" + houseNum + "'";
		House house = (House) baseDao.loadObject(houseHQL);
		// new map
		Map<String, Object> map = new HashMap<>();
		// 订购记录表No
		map.put("recordNo", recordNo);
		// 房源号(id)
		map.put("houseNum", house.getHouseNum());
		// 房号
		map.put("houseNo", house.getHouseNo());
		// 预售证号+图片 JSON解析字符串
		// if (house.getPresalePermissionInfo() !=null) {
		// HashMap urlMap =
		// JSON.parseObject(house.getPresalePermissionInfo(),HashMap.class);
		// map.put("presalePermissionInfo", urlMap);
		// }
		map.put("presalePermissionInfo", house.getPresalePermissionInfo());
		// 楼栋号
		map.put("buildingNo", house.getBuildingNo());
		// 单元
		map.put("unit", house.getUnit());
		// 根据house获得houseTypeId
		String houseTypeId = house.getHouseTypeId();
		HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
		if (ht != null) {
			// 户型
			map.put("caption", ht.getCaption());
		}
		// 价格
		map.put("listPrice", house.getListPrice());
		// 客户姓名
		map.put("customerName", cr.getCustomerName());
		// 客户电话
		map.put("customerPhone", cr.getCustomerPhone());
		// 根据projectId查认购规则
		String ebHQL = "from EnterBuy eb where eb.projectId = '" + cr.getProjectId() + "'";
		EnterBuy eb = (EnterBuy) baseDao.loadObject(ebHQL);
		// 定金
		map.put("dposit", "定金: " + eb.getDposit());
		// 下定规则说明
		map.put("description", eb.getDscription());

		return map;
	}

	/**
	 * 在置业顾问成交客户
	 */
	@Override
	public List findSaleDeal(User user) {

		// 获取该置业顾问所在案场的所有属于该置业顾问的已成交(签约)的记录表, userId与订购表对应上
		String hql = "from ContractRecords cr where cr.projectId = '" + user.getParentId() + "' " + " and cr.userId = '"
				+ user.getUserId() + "' and cr.recordStatus = 5 order by contractConfirmTime DESC";
		List<ContractRecords> crList = baseDao.findByHql(hql);

		return crList;
	}

	/**
	 * 置业顾问我的客户
	 */
	@Override
	public List findSaleCustomer(User user) {
		// 置业顾问对应的该案场的属于该置业顾问的 案场客户表信息
		String hql = "from ProjectCustomers pc where pc.projectId = '" + user.getParentId() + "' and pc.ownerUserId = '"
				+ user.getUserId() + "'";
		List<ProjectCustomers> pcList = baseDao.findByHql(hql);

		return pcList;
	}

	/**
	 * 置业顾问我的认购客户(搜索)
	 */
	@Override
	public List findSaleAllBuy(User user, String customerNameOrPhone) {
		// 置业顾问所有的认购客户
		String hql = "from ContractRecords cr where cr.userId = '" + user.getUserId() + "' ";

		if (customerNameOrPhone != null && !"".equals(customerNameOrPhone)) {
			hql += " and ( cr.customerName like '%" + customerNameOrPhone + "%' or cr.customerPhone like '%"
					+ customerNameOrPhone + "%' )";
		}
		List<ContractRecords> crList = baseDao.findByHql(hql);

		return crList;
	}

	/**
	 * 置业顾问app我的客户搜索
	 * 
	 */
	@Override
	public List findMyCustomer(User user, String customerNameOrPhone) {
		String hql = "from ProjectCustomers pc where pc.ownerUserId = '" + user.getUserId() + "' ";
		if (customerNameOrPhone != null && !"".equals(customerNameOrPhone)) {
			hql += " and ( pc.projectCustomerName like '%" + customerNameOrPhone
					+ "%' or pc.projectCustomerPhone like '%" + customerNameOrPhone + "%' )";
		}
		List<ProjectCustomers> pcList = baseDao.findByHql(hql);
		return pcList;
	}

	/**
	 * 置业顾问app业务下认购客户(下定客户)(订购有效期内的)
	 */
	@Override
	public List findSaleBuy(User user) {
		// 该置业顾问所在案场的属于该置业顾问的所有认购客户表, 状态为案场助理同意的
		String crHQL = "from ContractRecords cr where cr.projectId = '" + user.getParentId() + "' and cr.userId = '"
				+ user.getUserId() + "' and (cr.recordStatus = 1 or cr.recordStatus = 0)";
		List<ContractRecords> crList = baseDao.findByHql(crHQL);
		// new 订购记录list
		List<ContractRecordsDTO> crdtoList = new ArrayList<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 设置价格保留两位小数
		DecimalFormat df = new DecimalFormat("#.#");
		if (crList != null) {
			for (ContractRecords cr : crList) {
				// 根据projectId查认购规则
				String ebHQL = "from EnterBuy eb where eb.projectId = '" + cr.getProjectId() + "'";
				EnterBuy eb = (EnterBuy) baseDao.loadObject(ebHQL);
				// 超时时间 (小时)
				String outOfTime = eb.getOutOfTime();
				int youxiaoxioashi = Integer.parseInt(outOfTime);
				// 申请时间
				String auditingTime = cr.getAuditingTime();
				try {
					
					// new dto
					ContractRecordsDTO crdto = new ContractRecordsDTO();
					// 设置订购表记录号,唯一
					crdto.setRecordNo(cr.getRecordNo());
					// 获取房源编码,唯一性
					Integer houseNum = cr.getHouseNum();
					// 设置客户姓名
					crdto.setCustomerName(cr.getCustomerName());
					// 设置projectCustomerId 唯一性
					crdto.setProjectCustomerId(cr.getProjectCustomerId());
					// 根据订购表获得project对象
					String pHQL = "from Project p where p.projectId = '" + cr.getProjectId() + "'";
					Project project = (Project) baseDao.loadObject(pHQL);
					// 设置案场名字
					crdto.setProjectName(project.getProjectName());
					// 根据订购表查出house对象
					String houseHQL = "from House h where h.houseNum = '" + cr.getHouseNum() + "'";
					House house = (House) baseDao.loadObject(houseHQL);
					// 设置楼栋号
					crdto.setBuildingNo(house.getBuildingNo());
					// 设置单元号
					crdto.setUnit(house.getUnit());
					// 设置房号
					crdto.setHouseNo(house.getHouseNo());
					String nowTime = DateTime.toString1(new Date());
					if (cr.getRecordStatus() == 1) {
						// 到期时间
						String daoqiTime = SysContent.addSameHours(auditingTime, youxiaoxioashi);
						if ((daoqiTime.compareTo(nowTime)) >= 0) {
							Date daoqi = sdf.parse(daoqiTime);
							Date now = new Date();
							double xx = daoqi.getTime() - now.getTime();
							double hours = xx / 1000 / 60 / 60;
							// 设置剩余时间小时
							String remainingTime = df.format(hours);
							crdto.setResidueHours(remainingTime + "小时");
						} else {
							crdto.setResidueHours("已过期");
						}
						if (cr.getVoucherUploadTime() != null && !"".equals(cr.getVoucherUploadTime())){
							crdto.setResidueHours("已打款");
						}
					}else if (cr.getRecordStatus() == 0){
						crdto.setResidueHours("审核中");
					}
					// 添加集合
					crdtoList.add(crdto);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return crdtoList;
	}

	/**
	 * 置业顾问app确认下定
	 * 
	 */
	@Override
	public Map<String, Object> findSaleReal(User user, Integer recordNo) {
		// 根据recordNo查出订购记录表
		String crHQL = "from ContractRecords cr where cr.recordNo = '" + recordNo + "'";
		ContractRecords cr = (ContractRecords) baseDao.loadObject(crHQL);
		Integer houseNum = cr.getHouseNum();
		String projectCustomerId = cr.getProjectCustomerId();
		// 根据houseNum获得house对象
		String houseHQL = "from House h where h.houseNum = '" + houseNum + "'";
		House house = (House) baseDao.loadObject(houseHQL);
		// new map
		Map<String, Object> map = new HashMap<>();
		// 订购记录表No
		map.put("recordNo", recordNo);
		// 房源号(id)
		map.put("houseNum", house.getHouseNum());
		// 房号
		map.put("houseNo", house.getHouseNo());
		// 预售证号+图片 JSON解析字符串
		map.put("presalePermissionInfo", house.getPresalePermissionInfo());
		// 楼栋号
		map.put("buildingNo", house.getBuildingNo());
		// 单元
		map.put("unit", house.getUnit());
		// 根据house获得houseTypeId
		String houseTypeId = house.getHouseTypeId();
		HouseType ht = (HouseType) baseDao.loadById(HouseType.class, houseTypeId);
		if (ht != null) {
			// 户型
			map.put("caption", ht.getCaption());
		}
		// 价格
		map.put("listPrice", house.getListPrice());

		// 客户姓名
		map.put("customerName", cr.getCustomerName());
		// 客户电话
		map.put("customerPhone", cr.getCustomerPhone());
		// 根据projectId查认购规则
		String ebHQL = "from EnterBuy eb where eb.projectId = '" + cr.getProjectId() + "'";
		EnterBuy eb = (EnterBuy) baseDao.loadObject(ebHQL);
		// 定金
		map.put("dposit", eb.getDposit());
		// 下定规则说明
		map.put("description", eb.getDscription());
		return map;
	}

	/**
	 * 置业顾问app确认下定页面 确认大打款按钮 更新订购记录信息
	 * @throws Exception 
	 * 
	 */
	@Override
	public void addOrUpdateContract(User user, String desc, Integer credentialsType, Integer recordNo,
			MultipartFile pic) throws Exception {
		
		// 根据recordNo查出订购记录表
		ContractRecords cr = (ContractRecords) baseDao.loadById(ContractRecords.class, recordNo);
		if (cr!= null && !"".equals(cr)){
			if (!pic.isEmpty() && pic.getSize() > 0) {
				String rename = SysContent.getFileRename(pic.getOriginalFilename());
				// 设置凭证url
				String savePath = PicUploadToYun.upload(rename, pic,SmsContext.CR_PIC);
				cr.setCredentialsPhoto(savePath);
				Date date = new Date();
				// 设置时间格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String voucherUploadTime = sdf.format(date);
				// 设置凭证上传时间
				cr.setVoucherUploadTime(voucherUploadTime);
				cr.setCredentialsNum(desc);
			}
			// 更新保存订购记录表
			baseDao.saveOrUpdate(cr);
			//map.put("returenCode", "200");
			Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
			String houseHQL = "from House where houseNum = '"+cr.getHouseNum()+"' ";
			
			House house2 = (House) baseDao.loadObject(houseHQL);
			//new  动态
			Mydynamic md = new Mydynamic();
			md.setUserId(user.getUserId());
			md.setUserPhone(user.getPhone());
			md.setCustomerName(cr.getCustomerName());
			md.setCustomerPhone(cr.getCustomerPhone());
			md.setIsRead(0);
			md.setCreatTime(DateTime.toString1(new Date()));
			md.setProjectId(cr.getProjectId());
			md.setProjectName(p.getProjectName());
			md.setHosueNum(cr.getHouseNum().toString());
			md.setIsDelete("0");
			md.setDynamicInfo("客户"+cr.getCustomerName()+"已提交打款!");
			md.setDynamicStatus("提交打款");
			baseDao.save(md);
			// 短信发送
			// 内容（"您好！来自#userName#的购买申请，房源名称为#houseName#，已经打款，请及时确认！"）
			String uHQL = "from User where parentId = '" + cr.getProjectId() + "'";
			// 查案场助理
			List<User> uList = baseDao.findByHql(uHQL);
			for (User user2 : uList) {
				if (user2.getUserStatus() == 1){
					Set<Role> hs = user2.getRoleId();
					for (Role role : hs) {
						if (role.getRoleId() == 4) {
							House house = (House) baseDao.loadById(House.class, cr.getHouseNum());
							String text = SmsContext.PLAY_MONEY_ENTER.replace("#userName#", user.getUserCaption())
									.replace("#houseName#", house.getBuildingNo() + house.getUnit() + house.getHouseNo());
							// 调用短信发送接口，发送信息给申请人
							JavaSmsApi.sendSms(text, user2.getPhone());
						}
					}
				}
			}
		}
	}

	/**
	 * 置业顾问客户资料显示页面
	 */
	@Override
	public Map selectOneCustomer(User user, String projectCustomerId,String projectCustomerPhoneTwo) {
		Map<String, Object> map = new HashMap<>();
		String pcHQL = "from ProjectCustomers where ownerUserId= '"+ user.getUserId()+"' ";
		if (projectCustomerId != null && !"".equals(projectCustomerId)){
			pcHQL += " and projectCustomerId = '"+ projectCustomerId + "' ";
		}
		if (projectCustomerPhoneTwo != null && !"".equals(projectCustomerPhoneTwo)){
			pcHQL += " and projectCustomerPhone = '"+ projectCustomerPhoneTwo+ "'";
		}
		ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(pcHQL);
		if (pc != null && !"".equals(pc)){
			if (pc.getProjectCustomerName() != null && !"".equals(pc.getProjectCustomerName())){
				map.put("projectCustomerName", pc.getProjectCustomerName());
			}
			if (pc.getProjectCustomerPhone() != null && !"".equals(pc.getProjectCustomerPhone())){
				map.put("projectCustomerPhone", pc.getProjectCustomerPhone());
			}
			if (pc.getSex()!= null && !"".equals(pc.getSex())){
				int sex = pc.getSex();
				if (sex == 0) {
					map.put("sex", "未知");
				} else if (sex == 1) {
					map.put("sex", "男");
				} else if (sex == 2) {
					map.put("sex", "女");
				}
			}
			if (pc.getIdCard() != null && !"".equals(pc.getIdCard())){
				map.put("idCard", pc.getIdCard());
			}
			if (pc.getProjectCustomerId() != null && !"".equals(pc.getProjectCustomerId())){
				map.put("projectCustomerId", pc.getProjectCustomerId());
			}
			map.put("description", pc.getDescription());
		}

		return map;
	}

	/**
	 * 客户资料修改,保存
	 */
	@Override
	public void updateProjectCustomer(User user, String projectCustomerId, String description,String projectCustomerPhone) {
		
		String pcHQL = "from ProjectCustomers where ownerUserId= '"+ user.getUserId()+"' ";
		if (projectCustomerId != null && !"".equals(projectCustomerId)){
			pcHQL += " and projectCustomerId = '"+ projectCustomerId + "' ";
		}
		if (projectCustomerPhone != null && !"".equals(projectCustomerPhone)){
			pcHQL += " and projectCustomerPhone = '"+ projectCustomerPhone+ "'";
		}
		ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(pcHQL);
		if (description != null && !"".equals(description)) {
			pc.setDescription(description);
		}
		baseDao.saveOrUpdate(pc);
	}

	/**
	 * 中介认购客户的数量
	 */
	@Override
	public int findContractRecordsNum(User user) {

		String userId = user.getUserId();
		// 查认购记录表 (该中介的所有认购客户表信息),状态为1,案场助理同意的
		String hql = "select count(*) from ContractRecords cr where cr.userId = '" + userId
				+ "' and (cr.recordStatus = 1 or cr.recordStatus = 0)";
		int count = baseDao.countQuery(hql);

		return count;
	}

	/**
	 * 认购页面房源信息显示(改版后)
	 */
	@Override
	public NewContractRecordsDTO findOneHouseToContractRecord(User user,Integer houseNum) {
		String hql = "from House where houseNum = '"+houseNum + "' ";
		House h = (House) baseDao.loadObject(hql);
		NewContractRecordsDTO crdto = new NewContractRecordsDTO();
		if (h!= null && !"".equals(h)){
			crdto.setHouseNum(h.getHouseNum().toString());
			crdto.setHouseNo(h.getHouseNo());
			crdto.setHouseInfo(h.getBuildingNo()+"栋"+h.getUnit()+"单元"+h.getFloor()+"楼"+h.getHouseNo()+"号");
			crdto.setPresalePermissionInfo(h.getPresalePermissionInfo());
			crdto.setProjectId(h.getProjectId());
			String htHQL = "from HouseType where houseTypeId = '"+ h.getHouseTypeId()+"' ";
			HouseType ht = (HouseType) baseDao.loadObject(htHQL);
			if (ht!= null && !"".equals(ht)){
				crdto.setHousType(ht.getHousType());
			}
			crdto.setListPriceStr(SysContent.get2Double(h.getListPrice()));
			crdto.setBuyPrice(SysContent.get2Double(h.getListPrice()));
			crdto.setDanjia(SysContent.get2Double(h.getListPrice()/h.getBuildArea()));
			String enterBuyHQL = "from EnterBuy where projectId = '"+h.getProjectId()+"' ";
			EnterBuy eb = (EnterBuy) baseDao.loadObject(enterBuyHQL);
			if (eb!= null && !"".equals(eb)){
				crdto.setDposit(SysContent.get2Double(eb.getDposit()));
			}
		}
		return crdto;
	}

	
	/**
	 * 认购页面价格优惠信息显示(改版后)
	 */
	@Override
	public Map<String, Object> findOneProjectBenefitsToContractRecord(User user, String allBenefitsId,Integer houseNum) {
		Map<String, Object> map = new HashMap<>();
		return map;
	}

	
	/**
	 * 认购页面房源列表展示(改版后)
	 */
	@Override
	public List<HouseListDTO> findNowProjectHouses(User user, Integer houseNum) {
		String hql = "from House where houseNum = '"+houseNum + "' ";
		House h = (House) baseDao.loadObject(hql);
		
		List<HouseListDTO> hdtoList = new ArrayList<>();
		
		if (h!= null && !"".equals(h)){
			String proHQL = "from Project where projectId = '"+h.getProjectId()+"' ";
			Project p = (Project) baseDao.loadObject(proHQL);
			HouseType ht = (HouseType) baseDao.loadObject("from HouseType where houseTypeId = '"+h.getHouseTypeId()+"'");
			if (p != null && !"".equals(p) && ht != null && !"".equals(ht)){
				List<ProjectPics> picList = baseDao.findByHql("from ProjectPics where projectId = '"+h.getProjectId()+"'");
				String picture = null;
				if (picList.size()>0){
					List<String> photoList = new ArrayList<>();
					for (ProjectPics projectPics : picList) {
						String pic = projectPics.getUrl();
						String picStr = pic.substring(1, pic.length()-1);
						String[] photoarr = picStr.split(",");
						for(int i =0;i<photoarr.length;i++){
							photoList.add(photoarr[i]);
						}
					}
					//取第一张效果图
					for (int i = 0; i < photoList.size(); i++) {
						if (i==0){
							String pictureStr = photoList.get(i);
							if (pictureStr != null && !"".equals(pictureStr)) {
								picture = pictureStr;
							}
						}
					}
				}
				String houseHQL = "from House where projectId = '"+p.getProjectId()+"' and houseStatus = 1";
				Set<Role> set = user.getRoleId();
				for (Role role : set) {
					if (role.getRoleId() == 1 || role.getRoleId() == 2){
						houseHQL +=" and isOpen = 1";
					}
				}
				List<House> hList = baseDao.findByHql(houseHQL);
				if (hList.size()>0){
					for (House house : hList) {
						
						HouseListDTO hdto = new HouseListDTO();
						HouseListDTO dtoObject = hdto.getHouseListDTO(house, p, ht, picture);
						hdtoList.add(dtoObject);
					}
				}
			}
		}
		return hdtoList;
	}

	
	/**
	 * 认购页面房源优惠信息列表(改版后)
	 * @throws ParseException 
	 */
	@Override
	public List<ProjectBenefits> findNowProjectBenefits(User user, Integer houseNum) throws ParseException {
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		House h = (House) baseDao.loadObject(" from House where houseNum = '"+houseNum+"'");
		if (h != null && !"".equals(h)){
			
			String hql = "from ProjectBenefits pb where pb.projectId = '" + h.getProjectId() + "'";
			List<ProjectBenefits> pbList = baseDao.findByHql(hql);
			List<ProjectBenefits> pbList2 = new ArrayList<>();
			ProjectBenefits projectBenefits = null;
			for (ProjectBenefits pb : pbList) {
				projectBenefits = pb;
				Date nowTime = new Date();
				String start = pb.getStartTime();
				String end = pb.getEndTime();
				if (start != null && end != null) {
					Date startTime = sdf.parse(pb.getStartTime());
					Date endTime = sdf.parse(pb.getEndTime());
					
					double i = nowTime.getTime() - startTime.getTime();
					double j = nowTime.getTime() - endTime.getTime();
					if (i >= 0 && j <= 0) {
						pbList2.add(projectBenefits);
					} 
				}
			}
			return pbList2;
		}
		return null;
	}

	/**
	 * 提交认购信息(改版后)
	 * @throws IOException 
	 */
	@Override
	public void addNewContractRecord(User user, Integer orderProperty,Integer isAlreadyRead,Integer payStyle,
			Integer accountStyle,String benefitInfo,Integer houseNum,String buyPrice,String customerId,String rengourenIdCard,String realCustomerId,String dposit) throws IOException {
		
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		
		ContractRecords cr = new ContractRecords();
		// 判断是置业顾问 还是中介经纪人
		Set<Role> roleSet = user.getRoleId();
		// new map
		Map<String, Object> map = new HashMap<>();
		for (Role role : roleSet) {
			Integer roleId = role.getRoleId();
			if (role.getRoleId() == 3) {
				String hql = "from ProjectCustomers pc where pc.projectId = '" + user.getParentId()
						+ "' and pc.ownerUserId = '" + user.getUserId() + "'" + "and pc.projectCustomerId = '"
						+ customerId + "'";
				ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);
				if (pc != null && !"".equals(pc)){
					// projectCustomerId
					cr.setProjectCustomerId(pc.getProjectCustomerId());
					cr.setCustomerName(pc.getProjectCustomerName());
					cr.setCustomerPhone(pc.getProjectCustomerPhone());
					pc.setIdCard(rengourenIdCard);
					baseDao.saveOrUpdate(pc);
				}
			}
			if (role.getRoleId() == 1 || role.getRoleId() == 2){
				ShopCustomers sc = (ShopCustomers) baseDao.loadObject("from ShopCustomers where shopCustomerId = '"+customerId+"' and userId = '"+user.getUserId()+"' ");
				if (sc != null && !"".equals(sc)){
					cr.setShopCustomerId(sc.getShopCustomerId());
					cr.setCustomerName(sc.getShopCustomerName());
					cr.setCustomerPhone(sc.getShopCustomerPhone());
					sc.setIdCard(rengourenIdCard);
					baseDao.saveOrUpdate(sc);
				}
			}
		}
		
		cr.setOrderNum(sdf.format(new Date()));
		cr.setUserId(user.getUserId());
		cr.setHouseNum(houseNum);
		cr.setBuyPrice(Double.parseDouble(buyPrice));
		House house = (House) baseDao.loadObject("from House where houseNum = '"+houseNum+"'");
		cr.setProjectId(house.getProjectId());
		cr.setCustomerIDCard(rengourenIdCard);
		cr.setRealCustomerId(realCustomerId);
		cr.setOrderProperty(orderProperty);
		cr.setIsAlreadyRead(isAlreadyRead);
		cr.setAccountStyle(accountStyle);
		cr.setBenefitInfo(benefitInfo);
		cr.setPayStyle(payStyle);
		cr.setHaveToPayMoney(dposit);
		// 设置订购记录状态为申请
		cr.setRecordStatus(0);
		cr.setApplyTime(DateTime.toString1(new Date()));
		cr.setRecordStatus(0);
		
		baseDao.saveOrUpdate(cr);
		
		Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
		//new  动态
		Mydynamic md = new Mydynamic();
		md.setUserId(user.getUserId());
		md.setUserPhone(user.getPhone());
		md.setCustomerName(cr.getCustomerName());
		md.setCustomerPhone(cr.getCustomerPhone());
		md.setIsRead(0);
		md.setCreatTime(DateTime.toString1(new Date()));
		md.setProjectId(cr.getProjectId());
		md.setProjectName(p.getProjectName());
		md.setHosueNum(cr.getHouseNum().toString());
		md.setIsDelete("0");
		md.setDynamicInfo("客户"+cr.getCustomerName()+"已提交认购申请!");
		md.setDynamicStatus("认购申请");
		baseDao.save(md);
		// 短信发送
		// 内容（"您收到一笔来自#userName#的购买申请，购买的房源名称为#houseName#，价格为#money#元，请及时审核！"）
		String uHQL = "from User where parentId = '" + cr.getProjectId() + "'";
		// 查案场助理
		List<User> uList = baseDao.findByHql(uHQL);
		for (User user2 : uList) {
			if (user2.getUserStatus() == 1){
				Set<Role> hs = user2.getRoleId();
				for (Role role : hs) {
					if (role.getRoleId() == 4) {
						String text = SmsContext.BUY_APPLY.replace("#userName#", user.getUserCaption())
								.replace("#houseName#", house.getBuildingNo()+"栋" + house.getUnit()+"单元" + house.getFloor()+"楼" + house.getHouseNo()+"号")
								.replace("#money#", cr.getBuyPrice().toString());
						// 调用短信发送接口，发送信息给申请人
						JavaSmsApi.sendSms(text, user2.getPhone());
					}
				}
			}
		}
		
	}


	/**
	 * 中介购买申请房源重新选中  (改版后)
	 * 
	 * @return
	 */
	@Override
	public NewContractRecordsDTO findNewProjectHouse(User user, NewContractRecordsDTO crdto,Integer houseNum,String allBenefitsId) {
		House h = (House) baseDao.loadObject("from House where houseNum = '"+houseNum+"'");
		
		if (h!= null && !"".equals(h)){
			crdto.setHouseNum(h.getHouseNum().toString());
			crdto.setHouseNo(h.getHouseNo());
			crdto.setHouseInfo(h.getBuildingNo()+"栋"+h.getUnit()+"单元"+h.getFloor()+"楼"+h.getHouseNo()+"号");
			crdto.setPresalePermissionInfo(h.getPresalePermissionInfo());
			
			String htHQL = "from HouseType where houseTypeId = '"+ h.getHouseTypeId()+"' ";
			HouseType ht = (HouseType) baseDao.loadObject(htHQL);
			if (ht!= null && !"".equals(ht)){
				crdto.setHousType(ht.getHousType());
			}
			crdto.setListPriceStr(SysContent.get2Double(h.getListPrice()));
			crdto.setDanjia(SysContent.get2Double(h.getListPrice()/h.getBuildArea()));
			
			double nowPrice = h.getListPrice();
			if (allBenefitsId != null && !"".equals(allBenefitsId)){
				String[] benefitsStr = allBenefitsId.split(",");
				String benefitsCaption = "";
				for (String string : benefitsStr) {
					String hql= "from ProjectBenefits where benefitId = '"+string+"' ";
					ProjectBenefits pb = (ProjectBenefits) baseDao.loadObject(hql);
					benefitsCaption += pb.getCaption() + ";";
					if (pb.getType() == 0) {
						nowPrice = nowPrice - pb.getBenefitMoney();
					} else if (pb.getType() == 1) {
						nowPrice = nowPrice *(1-pb.getBenefitPercent()/100);
					}
				}
				
				crdto.setAllBenefitsId(allBenefitsId);
				crdto.setBenefitInfo(benefitsCaption);
				crdto.setBuyPrice(SysContent.get2Double(nowPrice));
			}else {
				crdto.setBuyPrice(SysContent.get2Double(h.getListPrice()));
			}
			String enterBuyHQL = "from EnterBuy where projectId = '"+h.getProjectId()+"' ";
			EnterBuy eb = (EnterBuy) baseDao.loadObject(enterBuyHQL);
			if (eb!= null && !"".equals(eb)){
				crdto.setDposit(SysContent.get2Double(eb.getDposit()));
			}
		}
		return crdto;
	}

	/**
	 * 认购页面价格优惠信息重新选中(改版后)
	 */
	@Override
	public NewContractRecordsDTO findNewProjectBenefits(User user, NewContractRecordsDTO crdto, String allBenefitsId,Integer houseNum) {
		String houseHQL = "from House where houseNum = '"+houseNum + "' ";
		House h = (House) baseDao.loadObject(houseHQL);
		if (h!= null && !"".equals(h)){
			double nowPrice = h.getListPrice();
			
			if (allBenefitsId != null && !"".equals(allBenefitsId)){
				String[] benefitsStr = allBenefitsId.split(",");
				String benefitsCaption = "";
				for (String string : benefitsStr) {
					String hql= "from ProjectBenefits where benefitId = '"+string+"' ";
					ProjectBenefits pb = (ProjectBenefits) baseDao.loadObject(hql);
					benefitsCaption += pb.getCaption() + ";";
					if (pb.getType() == 0) {
						nowPrice = nowPrice - pb.getBenefitMoney();
					} else if (pb.getType() == 1) {
						nowPrice = nowPrice *(1-pb.getBenefitPercent()/100);
					}
				}
				
				crdto.setAllBenefitsId(allBenefitsId);
				crdto.setBenefitInfo(benefitsCaption);
				crdto.setBuyPrice(SysContent.get2Double(nowPrice));
			}else {
				crdto.setBuyPrice(SysContent.get2Double(h.getListPrice()));
			}
			String enterBuyHQL = "from EnterBuy where projectId = '"+h.getProjectId()+"' ";
			EnterBuy eb = (EnterBuy) baseDao.loadObject(enterBuyHQL);
			if (eb!= null && !"".equals(eb)){
				crdto.setDposit(SysContent.get2Double(eb.getDposit()));
			}
		}
		
		return crdto;
	}

	
	/**
	 * 中介我的客户选中 (改版后)
	 * 
	 */
	@Override
	public NewContractRecordsDTO findMidCustomerNew(User user, NewContractRecordsDTO crdto, Integer houseNum,
			String shopCustomerId) {
		ShopCustomers sc = (ShopCustomers) baseDao.loadObject("from ShopCustomers sc where sc.shopCustomerId = '" + shopCustomerId + "'");
		if (sc != null && !"".equals(sc)) {
			// 客户姓名
			crdto.setrName(sc.getShopCustomerName());
			// 客户电话
			crdto.setrPhone(sc.getShopCustomerPhone());
			//id
			crdto.setCustomerId(shopCustomerId);
			if (sc.getIdCard() != null && !"".equals(sc.getIdCard())){
				crdto.setRengourenIdCard(sc.getIdCard());
			}else {
				crdto.setRengourenIdCard("");
			}
		}
		
		return crdto;
	}

	/**
	 * 置业顾问app我的客户选中按钮跳转页(改版后)
	 * 
	 * @return
	 */
	@Override
	public NewContractRecordsDTO findAgentCustomerNew(User user, NewContractRecordsDTO crdto, Integer houseNum,
			String projectCustomerId) {
		String hql = "from ProjectCustomers pc where pc.projectCustomerId = '" + projectCustomerId
				+ "' and pc.ownerUserId = '" + user.getUserId() + "'";
		ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject(hql);
		if (pc!= null && !"".equals(pc)){
			// 客户姓名
			crdto.setrName(pc.getProjectCustomerName());
			// 客户电话
			crdto.setrPhone(pc.getProjectCustomerPhone());
			crdto.setCustomerId(projectCustomerId);
			if (pc.getIdCard() != null && !"".equals(pc.getIdCard())){
				crdto.setRengourenIdCard(pc.getIdCard());
			}else {
				crdto.setRengourenIdCard("");
			}
		}
		
		return crdto;
	}

	/**
	 * 鼠标移开填写信息添加到session(改版后)
	 * 
	 */
	@Override
	public NewContractRecordsDTO toAddSession(User user ,NewContractRecordsDTO crdto,Integer orderProperty, String buyPrice, Integer accountStyle,
			Integer payStyle, Integer isAlreadyRead,String rengourenIdCard) {
		if (orderProperty != null && !"".equals(orderProperty)){
			crdto.setOrderProperty(orderProperty);
		}
		if (buyPrice != null && !"".equals(buyPrice)){
			crdto.setBuyPrice(buyPrice);
		}
		if (accountStyle != null && !"".equals(accountStyle)){
			crdto.setAccountStyle(accountStyle);
		}
		if (payStyle != null && !"".equals(payStyle)){
			crdto.setPayStyle(payStyle);
		}
		if (isAlreadyRead != null && !"".equals(isAlreadyRead)){
			crdto.setIsAlreadyRead(isAlreadyRead);
		}
		if (rengourenIdCard != null && !"".equals(rengourenIdCard)){
			crdto.setRengourenIdCard(rengourenIdCard);
		}
		return crdto;
	}

	/**
	 * 真实认购人存入session(改版后)
	 * 
	 */
	@Override
	public List<RealEnterBuyDTO> findRebList(String rName, String rPhone, String rIdCard, String relationDesc) {
		
		List<RealEnterBuyDTO> rebList = new ArrayList<>();
		RealEnterBuyDTO reb = new RealEnterBuyDTO();
		if (rName != null && !"".equals(rName)){
			reb.setrName(rName);
		}
		if (rPhone != null && !"".equals(rPhone)){
			reb.setrPhone(rPhone);
		}
		if (rIdCard != null && !"".equals(rIdCard)){
			reb.setrIdCard(rIdCard);
		}
		if (relationDesc != null && !"".equals(relationDesc)){
			reb.setRelationDesc(relationDesc);
		}
		rebList.add(reb);
		return rebList;
	}

	

	/**
	 * 单条订单的所有状态显示
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * 
	 */
	@Override
	public NewContractRecordStatusDTO findOneContractRecord(Integer recordNo) throws Exception {
		
		ContractRecords cr = (ContractRecords) baseDao.loadObject("from ContractRecords where recordNo = '"+recordNo+"' ");
		if (cr != null && !"".equals(cr)){
			House h = (House) baseDao.loadById(House.class, cr.getHouseNum());
			NewContractRecordStatusDTO crdto = new NewContractRecordStatusDTO();
			NewContractRecordStatusDTO DtoObject = crdto.getNewContractRecordDTO(cr);
			DtoObject.setHouseName(h.getBuildingNo()+"栋"+h.getUnit()+"单元"+h.getHouseNo()+"号");
			// 根据projectId查认购规则
			EnterBuy eb = (EnterBuy) baseDao.loadObject("from EnterBuy eb where eb.projectId = '" + cr.getProjectId() + "'");
			
			if (eb != null && !"".equals(eb)){
				if(cr.getAuditingTime() != null && !"".equals(cr.getAuditingTime())){
					String outTime = SysContent.addSameHours(cr.getAuditingTime(), Integer.parseInt(eb.getOutOfTime()));
					DtoObject.setOutTime(outTime);
				}
			}
			return DtoObject;
		}
		return null;
	}

	/**
	 * 所有订单页面(包含不同订单状态)
	 * 
	 */
	@Override
	public List<AllMyContractRecordDTO> findAllContractRecords(User user) {
		
		List<AllMyContractRecordDTO> amcrdtoList = new ArrayList<>();
		
		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+user.getUserId()+"' order by recordNo DESC");
		if (crList.size()>0){
			for (ContractRecords cr : crList) {
				Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
				if (p != null && !"".equals(p)){
					
					House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+ "'");
					
					if (h!= null && !"".equals(h)){
						AllMyContractRecordDTO amcrdto = new AllMyContractRecordDTO();
						List<ProjectPics> picList = baseDao.findByHql("from ProjectPics where projectId = '"+h.getProjectId()+"'");
						String picture = null;
						
						if (picList.size()>0){
							List<String> photoList = new ArrayList<>();
							for (ProjectPics projectPics : picList) {
								String pic = projectPics.getUrl();
								String picStr = pic.substring(1, pic.length()-1);
								String[] photoarr = picStr.split(",");
								for(int i =0;i<photoarr.length;i++){
									photoList.add(photoarr[i]);
								}
							}
							//取第一张效果图
							for (int i = 0; i < photoList.size(); i++) {
								if (i==0){
									String pictureStr = photoList.get(i);
									if (pictureStr != null && !"".equals(pictureStr)) {
										picture = pictureStr;
									}
								}
							}
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, picture);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}else {
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, null);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}
					}
				}
			}
		}
		
		return amcrdtoList;
	}
	
	/**
	 * 待审核订单页面(
	 * 
	 */
	@Override
	public List<AllMyContractRecordDTO> findContractRecordsByReadyCheck(User user) {
		List<AllMyContractRecordDTO> amcrdtoList = new ArrayList<>();
		
		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+user.getUserId()+"' and recordStatus = 0 order by applyTime DESC");
		if (crList.size()>0){
			for (ContractRecords cr : crList) {
				Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
				if (p != null && !"".equals(p)){
					
					House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+ "'");
					
					if (h!= null && !"".equals(h)){
						AllMyContractRecordDTO amcrdto = new AllMyContractRecordDTO();
						List<ProjectPics> picList = baseDao.findByHql("from ProjectPics where projectId = '"+h.getProjectId()+"'");
						if (picList.size()>0){
							List<String> photoList = new ArrayList<>();
							for (ProjectPics projectPics : picList) {
								String pic = projectPics.getUrl();
								String picStr = pic.substring(1, pic.length()-1);
								String[] photoarr = picStr.split(",");
								for(int i =0;i<photoarr.length;i++){
									photoList.add(photoarr[i]);
								}
							}
							String pic = null;
							//取第一张效果图
							for (int i = 0; i < photoList.size(); i++) {
								if (i==0){
									String pictureStr = photoList.get(i);
									if (pictureStr != null && !"".equals(pictureStr)) {
										pic = pictureStr;
									}
								}
							}
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, pic);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}else {
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, null);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}
					}
				}
			}
		}
		
		return amcrdtoList;
	}

	/**
	 * 待付款订单页面(
	 * 
	 */
	@Override
	public List<AllMyContractRecordDTO> findContractRecordsByReadyPay(User user) {

		List<AllMyContractRecordDTO> amcrdtoList = new ArrayList<>();
		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+user.getUserId()+"' and recordStatus = 1 and voucherUploadTime is null order by auditingTime DESC");
		if (crList.size()>0){
			for (ContractRecords cr : crList) {
				Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
				if (p != null && !"".equals(p)){
					
					House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+ "'");
					
					if (h!= null && !"".equals(h)){
						AllMyContractRecordDTO amcrdto = new AllMyContractRecordDTO();
						List<ProjectPics> picList = baseDao.findByHql("from ProjectPics where projectId = '"+h.getProjectId()+"'");
						if (picList.size()>0){
							List<String> photoList = new ArrayList<>();
							for (ProjectPics projectPics : picList) {
								String pic = projectPics.getUrl();
								String picStr = pic.substring(1, pic.length()-1);
								String[] photoarr = picStr.split(",");
								for(int i =0;i<photoarr.length;i++){
									photoList.add(photoarr[i]);
								}
							}
							String pic = null;
							//取第一张效果图
							for (int i = 0; i < photoList.size(); i++) {
								if (i==0){
									String pictureStr = photoList.get(i);
									if (pictureStr != null && !"".equals(pictureStr)) {
										pic = pictureStr;
									}
								}
							}
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, pic);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}else {
							
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, null);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}
					}
				}
			}
		}
		
		return amcrdtoList;
	}
	
	/**
	 * 待确认付款订单页面(
	 * 
	 */
	@Override
	public List<AllMyContractRecordDTO> findContractRecordsByEnterPay(User user) {

		List<AllMyContractRecordDTO> amcrdtoList = new ArrayList<>();
		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+user.getUserId()+"' and recordStatus = 1 and voucherUploadTime is not null order by auditingTime DESC");
		if (crList.size()>0){
			for (ContractRecords cr : crList) {
				Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
				if (p != null && !"".equals(p)){
					
					House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+ "'");
					if (h!= null && !"".equals(h)){
						List<ProjectPics> picList = baseDao.findByHql("from ProjectPics where projectId = '"+h.getProjectId()+"'");
						if (picList.size()>0){
							List<String> photoList = new ArrayList<>();
							for (ProjectPics projectPics : picList) {
								String pic = projectPics.getUrl();
								String picStr = pic.substring(1, pic.length()-1);
								String[] photoarr = picStr.split(",");
								for(int i =0;i<photoarr.length;i++){
									photoList.add(photoarr[i]);
								}
							}
							String pic = null;
							//取第一张效果图
							for (int i = 0; i < photoList.size(); i++) {
								if (i==0){
									String pictureStr = photoList.get(i);
									if (pictureStr != null && !"".equals(pictureStr)) {
										pic = pictureStr;
									}
								}
							}
							AllMyContractRecordDTO amcrdto = new AllMyContractRecordDTO();
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, pic);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}else {
							
							AllMyContractRecordDTO amcrdto = new AllMyContractRecordDTO();
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, null);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}
					}
				}
			}
		}
		
		return amcrdtoList;
	}

	/**
	 * 待签约订单页面(
	 * 
	 */
	@Override
	public List<AllMyContractRecordDTO> findContractRecordsByReadyContract(User user) {
		
		List<AllMyContractRecordDTO> amcrdtoList = new ArrayList<>();
		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+user.getUserId()+"' and recordStatus = 4 and voucherUploadTime is not null order by voucherUploadTime DESC");
		if (crList.size()>0){
			for (ContractRecords cr : crList) {
				Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
				if (p != null && !"".equals(p)){
					
					House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+ "'");

					if (h!= null && !"".equals(h)){
						AllMyContractRecordDTO amcrdto = new AllMyContractRecordDTO();
						List<ProjectPics> picList = baseDao.findByHql("from ProjectPics where projectId = '"+h.getProjectId()+"'");
						if (picList.size()>0){
							List<String> photoList = new ArrayList<>();
							for (ProjectPics projectPics : picList) {
								String pic = projectPics.getUrl();
								String picStr = pic.substring(1, pic.length()-1);
								String[] photoarr = picStr.split(",");
								for(int i =0;i<photoarr.length;i++){
									photoList.add(photoarr[i]);
								}
							}
							String pic = null;
							//取第一张效果图
							for (int i = 0; i < photoList.size(); i++) {
								if (i==0){
									String pictureStr = photoList.get(i);
									if (pictureStr != null && !"".equals(pictureStr)) {
										pic = pictureStr;
									}
								}
							}
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, pic);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}else {
							
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, null);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}
					}
				}
			}
		}
		
		return amcrdtoList;
	}

	/**
	 * 已签约订单页面(
	 * 
	 */
	@Override
	public List<AllMyContractRecordDTO> findContractRecordsByHaveContract(User user) {

		List<AllMyContractRecordDTO> amcrdtoList = new ArrayList<>();
		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+user.getUserId()+"' and recordStatus = 5 order by contractConfirmTime DESC");
		if (crList.size()>0){
			for (ContractRecords cr : crList) {
				Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
				if (p != null && !"".equals(p)){
					
					House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+ "'");

					if (h!= null && !"".equals(h)){
						AllMyContractRecordDTO amcrdto = new AllMyContractRecordDTO();
						List<ProjectPics> picList = baseDao.findByHql("from ProjectPics where projectId = '"+h.getProjectId()+"'");
						if (picList.size()>0){
							List<String> photoList = new ArrayList<>();
							for (ProjectPics projectPics : picList) {
								String pic = projectPics.getUrl();
								String picStr = pic.substring(1, pic.length()-1);
								String[] photoarr = picStr.split(",");
								for(int i =0;i<photoarr.length;i++){
									photoList.add(photoarr[i]);
								}
							}
							String pic = null;
							//取第一张效果图
							for (int i = 0; i < photoList.size(); i++) {
								if (i==0){
									String pictureStr = photoList.get(i);
									if (pictureStr != null && !"".equals(pictureStr)) {
										pic = pictureStr;
									}
								}
							}
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, pic);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}else {
							
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, null);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}
					}
				}
			}
		}
		
		return amcrdtoList;
	}

	/**
	 * 已拒绝订单页面(
	 * 
	 */
	@Override
	public List<AllMyContractRecordDTO> findContractRecordsByRefuse(User user) {

		List<AllMyContractRecordDTO> amcrdtoList = new ArrayList<>();
		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+user.getUserId()+"' and recordStatus = 3 order by auditingTime DESC");
		if (crList.size()>0){
			for (ContractRecords cr : crList) {
				Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
				if (p != null && !"".equals(p)){
					
					House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+ "'");

					if (h!= null && !"".equals(h)){
						AllMyContractRecordDTO amcrdto = new AllMyContractRecordDTO();
						List<ProjectPics> picList = baseDao.findByHql("from ProjectPics where projectId = '"+h.getProjectId()+"'");
						if (picList.size()>0){
							List<String> photoList = new ArrayList<>();
							for (ProjectPics projectPics : picList) {
								String pic = projectPics.getUrl();
								String picStr = pic.substring(1, pic.length()-1);
								String[] photoarr = picStr.split(",");
								for(int i =0;i<photoarr.length;i++){
									photoList.add(photoarr[i]);
								}
							}
							String pic = null;
							//取第一张效果图
							for (int i = 0; i < photoList.size(); i++) {
								if (i==0){
									String pictureStr = photoList.get(i);
									if (pictureStr != null && !"".equals(pictureStr)) {
										pic = pictureStr;
									}
								}
							}
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, pic);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}else {
							
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, null);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}
					}
				}
			}
		}
		
		return amcrdtoList;
	}

	/**
	 * 已撤销订单页面(
	 * 
	 */
	@Override
	public List<AllMyContractRecordDTO> findContractRecordsByRevoke(User user) {

		List<AllMyContractRecordDTO> amcrdtoList = new ArrayList<>();
		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+user.getUserId()+"' and recordStatus = 7 order by revokeTime DESC");
		if (crList.size()>0){
			for (ContractRecords cr : crList) {
				Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
				if (p != null && !"".equals(p)){
					
					House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+ "'");

					if (h!= null && !"".equals(h)){
						AllMyContractRecordDTO amcrdto = new AllMyContractRecordDTO();
						List<ProjectPics> picList = baseDao.findByHql("from ProjectPics where projectId = '"+h.getProjectId()+"'");
						if (picList.size()>0){
							List<String> photoList = new ArrayList<>();
							for (ProjectPics projectPics : picList) {
								String pic = projectPics.getUrl();
								String picStr = pic.substring(1, pic.length()-1);
								String[] photoarr = picStr.split(",");
								for(int i =0;i<photoarr.length;i++){
									photoList.add(photoarr[i]);
								}
							}
							String pic = null;
							//取第一张效果图
							for (int i = 0; i < photoList.size(); i++) {
								if (i==0){
									String pictureStr = photoList.get(i);
									if (pictureStr != null && !"".equals(pictureStr)) {
										pic = pictureStr;
									}
								}
							}
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, pic);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}else {
							
							AllMyContractRecordDTO dtoObject = amcrdto.getAllMyContractRecordDTO(cr, h, null);
							dtoObject.setObjectName(p.getProjectName());
							amcrdtoList.add(dtoObject);
						}
					}
				}
			}
		}
		
		return amcrdtoList;
	}

	/**
	 * 撤销订单操作
	 * 
	 */
	@Override
	public void updateContractRecordToRevoke(Integer recordNo,String killTheOrderReason,String revokeOrderNotes) {
		
		ContractRecords cr = (ContractRecords) baseDao.loadObject("from ContractRecords where recordNo = '"+recordNo+"'");
		if (cr != null && !"".equals(cr)){
			if (killTheOrderReason != null && !"".equals(killTheOrderReason)){
				cr.setKillTheOrderReason(killTheOrderReason);
			}
			if (revokeOrderNotes != null && !"".equals(revokeOrderNotes)){
				cr.setRevokeOrderNotes(revokeOrderNotes);
			}
			if(cr.getRecordStatus().equals(5)){
				House h = (House) baseDao.loadById(House.class, cr.getHouseNum());
				h.setHouseStatus(3);
				baseDao.saveOrUpdate(h);
			}
			cr.setRecordStatus(7);
			cr.setRevokeTime(DateTime.toString1(new Date()));
		}
		baseDao.saveOrUpdate(cr);
	}

	/**
	 * 查阅合同条款
	 * 
	 */
	@Override
	public EnterBuy findProjectEnterBuy(Integer houseNum) {
		
		House h = (House) baseDao.loadObject("from House where houseNum = '"+houseNum+"'");
		if (h != null && !"".equals(h)){
			EnterBuy eb = (EnterBuy) baseDao.loadObject("from EnterBuy where projectId = '"+h.getProjectId()+"'");
			if (eb != null && !"".equals(eb)){
				return eb;
			}
		}
		return null;
	}

	/**
	 * 真实认购人添加
	 * 
	 */
	@Override
	public Map<String, String> addRealBuyCustomer(String customerId,String rName,String rPhone,String rIdCard,String relation) {
	
		Map<String, String> map = new HashMap<>();
		
		List<RealEnterBuyMan> rebmList = baseDao.findByHql("from RealEnterBuyMan where customerId = '"+customerId+"' and isUserful = '0'");
				
		if (rebmList.size()>=4){
			map.put("status", "600");
			map.put("message", "上限4人");
		}else {
			if (rPhone != null && !"".equals(rPhone)){
				RealEnterBuyMan rebm2 = (RealEnterBuyMan) baseDao.loadObject("from RealEnterBuyMan where rPhone = '"+rPhone+"' and customerId = '"+customerId+"' ");
				if (rebm2 != null && !"".equals(rebm2)){
					if (rebm2.getIsUserful().equals("0")){
						map.put("status", "400");
						map.put("message", "重复客户,请检查");
					}else if (rebm2.getIsUserful().equals("1")){
						rebm2.setIsUserful("0");
						baseDao.saveOrUpdate(rebm2);
						map.put("status", "200");
						map.put("message", "添加成功");
					}
					
				}else {
					RealEnterBuyMan rebm = new RealEnterBuyMan();
					if (customerId != null && !"".equals(customerId)){
						rebm.setCustomerId(customerId);
					}
					if (rName != null && !"".equals(rName)){
						rebm.setrName(rName);
					}
					if (rPhone != null && !"".equals(rPhone)){
						rebm.setrPhone(rPhone);
					}
					if (rIdCard != null && !"".equals(rIdCard)){
						rebm.setrIdCard(rIdCard);
					}
					if (relation != null && !"".equals(relation)){
						rebm.setRelation(relation);
					}
					rebm.setIsUserful("0");
					baseDao.saveOrUpdate(rebm);
					map.put("status", "200");
					map.put("message", "添加成功");
				}
			}
		}
		
		return map;
	}

	/**
	 * 真实认购人删除
	 * 
	 */
	@Override
	public void updateRealBuyCustomer(String allRealEnterBuyId) {
		
		if (allRealEnterBuyId != null && !"".equals(allRealEnterBuyId)){
			String[] realEnterBuyId = allRealEnterBuyId.split(",");
			for (String string : realEnterBuyId) {
				Integer realEnterBuyIdInteger = Integer.parseInt(string);
				RealEnterBuyMan rebm = (RealEnterBuyMan) baseDao.loadObject("from RealEnterBuyMan where realEnterBuyId = '"+realEnterBuyIdInteger+"'");
				
				rebm.setIsUserful("1");
				baseDao.saveOrUpdate(rebm);
			}
		}
		
	}

	/**
	 * 真实认购人列表
	 * 
	 */
	@Override
	public List<RealEnterBuyMan> findAllRebmList(String customerId) {
		
		List<RealEnterBuyMan> rebmList = baseDao.findByHql("from RealEnterBuyMan where customerId = '"+customerId+"' and isUserful = '0'");
		
		List<RealEnterBuyMan> rebmList2 = new ArrayList<>();
		if (rebmList.size()>0 && rebmList.size()<=4){
			for (int i = 0; i < rebmList.size(); i++) {
				rebmList2.add(rebmList.get(i));
			}
		}else if (rebmList.size()>4){
			for (int i = 0; i < 4; i++) {
				rebmList2.add(rebmList.get(i));
			}
		}
		return rebmList2;
	}


	/**
	 * 真实认购人选中////////////////
	 * 
	 */
	@Override
	public NewContractRecordsDTO findChooseRebmList(NewContractRecordsDTO crdto,String allRealEnterBuyId) {
		
		String realEnterBuyManInfo = "";
		List<String> sList = new ArrayList<>();
		if (allRealEnterBuyId != null && !"".equals(allRealEnterBuyId)){
			String[] realEnterBuyId = allRealEnterBuyId.split(",");
			for (String string : realEnterBuyId) {
				Integer realEnterBuyIdInteger = Integer.parseInt(string);
				RealEnterBuyMan rebm = (RealEnterBuyMan) baseDao.loadObject("from RealEnterBuyMan where realEnterBuyId = '"+realEnterBuyIdInteger+"'");
				
				realEnterBuyManInfo += rebm.getrName() + ";";
				sList.add(rebm.getRealEnterBuyId().toString());
			}
		}
		crdto.setRealEnterBuyManInfo(realEnterBuyManInfo);
		crdto.setRealCustomerId(sList.toString());
		return crdto;
	}

	
	
	/**
	 * 我的客户新增...............
	 * 
	 */
	@Override
	public void addMyCustomerNew(User user, String rName, String rPhone, String rIdCard) {
		
		if (user != null && !"".equals(user)){
			Set<Role> set = user.getRoleId();
			for (Role role : set) {
				if (role.getRoleId() == 1 || role.getRoleId() == 2){
					ShopCustomers sc = new ShopCustomers();
					sc.setShopCustomerId(SysContent.uuid());
					if (rName != null && !"".equals(rName)){
						sc.setShopCustomerName(rName);
					}
					if (rPhone != null && !"".equals(rPhone)){
						sc.setShopCustomerPhone(rPhone);
					}
					if (rIdCard != null && !"".equals(rIdCard)){
						sc.setIdCard(rIdCard);
					}
					baseDao.saveOrUpdate(sc);
				}
				if (role.getRoleId() == 3) {
					ProjectCustomers pc = new ProjectCustomers();
					pc.setProjectCustomerId(SysContent.uuid());
					if (rName != null && !"".equals(rName)){
						pc.setProjectCustomerName(rName);
					}
					if (rPhone != null && !"".equals(rPhone)){
						pc.setProjectCustomerPhone(rPhone);
					}
					if (rIdCard != null && !"".equals(rIdCard)){
						pc.setIdCard(rIdCard);
					}
					baseDao.saveOrUpdate(pc);
				}
			}
		}
		
	}

	/**
	 * 订单详情
	 */
	@Override
	public OrderDetailsDTO findOrderDetailsByRecordNo(User user, Integer recordNo) {
		if(user!=null){
			ContractRecords cr= (ContractRecords) baseDao.loadById(ContractRecords.class, recordNo);
			//认购人
			String enterBuyMan = null;//认购人信息
			String idCardNum = null;
			Set<Role> role = user.getRoleId();
			for(Role r : role){
				if(r.getRoleId() == 1 || r.getRoleId() == 2){
					String enterBuyManId = cr.getShopCustomerId();
					if(enterBuyManId!=null && !enterBuyManId.equals("")){
						ShopCustomers sc = (ShopCustomers) baseDao.loadById(ShopCustomers.class, enterBuyManId);
						enterBuyMan = sc.getShopCustomerName();
						idCardNum = sc.getIdCard();
					}
				}else if(r.getRoleId()==3){
					String enterBuyManId = cr.getProjectCustomerId();
					ProjectCustomers pc = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class,enterBuyManId);
					enterBuyMan = pc.getProjectCustomerName();
					idCardNum = pc.getIdCard();
				}
			}
			
			//判断认购状态，获取该状态下相应数据
			List rbList = new ArrayList<>();//真实认购人信息
			if (cr.getOrderProperty() != null && !"".equals(cr.getOrderProperty())){
				if(cr.getOrderProperty().equals(1)){
					//真实认购人
					String realEnterBuyManId = cr.getRealCustomerId();
					List list = JSON.parseArray(realEnterBuyManId);
					for(int i=0;i<list.size();i++){
						RealEnterBuyMan rebm = (RealEnterBuyMan) baseDao.loadById(RealEnterBuyMan.class, Integer.parseInt(list.get(i).toString()));
						if (rebm != null && !"".equals(rebm)){
							rbList.add(rebm.getrName());
						}
					}
				}
			}
			//房源信息
			Integer houseNum = cr.getHouseNum();
			House h = (House) baseDao.loadById(House.class, houseNum);
			HouseType ht = (HouseType) baseDao.loadById(HouseType.class, h.getHouseTypeId());
			Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());
			
			OrderDetailsDTO odDto = new OrderDetailsDTO();
			OrderDetailsDTO newOdDto = odDto.createThisDTO(cr,h,ht,p);
			newOdDto.setEnterBuyMan(enterBuyMan);
			newOdDto.setRealEnterBuyMan(rbList);
			newOdDto.setIdCardNum(idCardNum);
			
			return newOdDto;
		}
		return null;
	}

	@Override
	public Boolean addOrUpdateCallOrderSms(User user, Integer orderNo) throws Exception {
		Boolean sendSmsFlag = false;
		if(user!=null && orderNo!=null){
			ContractRecords cr = (ContractRecords) baseDao.loadById(ContractRecords.class, orderNo);
			if(cr!=null){
				House h = (House) baseDao.loadById(House.class, cr.getHouseNum());
				String orderNum = cr.getOrderNum();//订单号
				if(orderNum!=null && !orderNum.equals("")){
					MiniMessageWaitSend sendMsg = (MiniMessageWaitSend) baseDao.loadObject("from MiniMessageWaitSend where orderNum = '"+orderNum+"'");
					//查询有无催单记录，若有判断是否可以再次催单
					String newTime = DateTime.toString1(new Date());
					if(sendMsg!=null){
						String sendTime = sendMsg.getDate();
						String newTimeAddSomeHourse = SysContent.addSameHours(newTime, -6);
						//sendTime>newTimeAddSomeHourse时可以再次催单
						Boolean flag = SysContent.doubleDateComper(newTimeAddSomeHourse, sendTime);
						if(flag){
							//更新短信记录
							sendMsg.setDate(newTime);
							//催单
							sendSmsFlag = true;
						}
					}else{
						sendSmsFlag = true;
						sendMsg = new MiniMessageWaitSend();
						sendMsg.setDate(newTime);
						sendMsg.setHouseNum(h.getHouseNum());
						sendMsg.setIsSendMiniMessage("yes");
						sendMsg.setOrderNum(orderNum);
					}
					//催单及短信记录信息维护
					if(sendSmsFlag){
						//您的项目关于#housName#房源的订单，订单号为：#No#，#userRole##userName#请求您审核，请尽快处理！
						String role = "";
						Set<Role> roleSet = user.getRoleId();
						for(Role r : roleSet){
							role = r.getDesctiption();
						}
						String newChar = h.getBuildingNo()+"栋"+h.getUnit()+"单元"+h.getFloor()+"楼"+h.getHouseNo();
						String text = SmsContext.CALL_ORDER.replace("#housName#", newChar).replace("#No#", orderNum).
								replace("#userRole#", role).replace("#userName#", user.getUserCaption());
						//获取当前案场下的助理
						String uHQL = "from User where parentId = '" + cr.getProjectId() + "' and userStatus = 1";
						List<User> userList = baseDao.findByHql(uHQL);
						for(User u : userList){
							Set<Role> rSet = u.getRoleId();
							for(Role r : rSet){
								if(r.getRoleId()==4){
									JavaSmsApi.sendSms(text, u.getPhone());
								}
							}
						}
						//更新短信记录
						baseDao.saveOrUpdate(sendMsg);
					}
				}
			}
		}
		return sendSmsFlag;
	}

}
