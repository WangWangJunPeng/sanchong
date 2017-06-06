package com.sc.tradmaster.service.user.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.AnalysisCategory;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.GuideRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.ManagerOwnAnalyse;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectCustomers;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.ShopCustomers;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.service.user.impl.dto.AllCheckedCustomerDTO;
import com.sc.tradmaster.service.user.impl.dto.GrDto;
import com.sc.tradmaster.service.user.impl.dto.RankingDto;
import com.sc.tradmaster.service.user.impl.dto.VisitRecordsDTO;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="baseDao")
	private BaseDao baseDao;

	@Override
	public User findById(String id) {
		return (User) baseDao.loadById(User.class, id);
	}

	@Override
	public User addUserTokenAndlogin(User u) {
		if(u!=null && !"".equals(u.getPassword())){
			String paw = SysContent.md5(u.getPassword());//登录密码加密
			String hql = "from User where phone = '" + u.getPhone() + "' and password = '" +paw + "' and userStatus = 1";
			List<User> list = baseDao.findByHql(hql);
			if(list.size()>0){
				User user = list.get(0);
				//token生成策略
				String uuid = SysContent.uuid();
				String token = user.getUserId() + "-" +DateTime.dateForTokenToString(new Date())+ "-" + uuid;
				user.setUserToken(token);
				baseDao.saveOrUpdate(user);
				return user;
			}
		}
		return null;
	}

	@Override//u是当前登录用户，user是接收数据参数
	public Map addOrUpdateUser(User u ,User user,String rightSign) {
		Map map = new HashMap<>();
		User aginUser = (User) baseDao.loadObject("from User where phone = '"+user.getPhone()+"'");
		if(u!=null){
			if(user.getUserId()==null){
				if(aginUser!=null){
					map.put("code", 201);
					map.put("msg", "该手机号已经存在，不能重复添加");
					return map;
				}
				user.setUserId(SysContent.uuid());
				user.setCreateTime(DateTime.toString1(new Date()));
			}
			User haveUser = (User) baseDao.loadById(User.class, user.getUserId());
			if(haveUser!=null){
				Set<Role> role = haveUser.getRoleId();
				if(!role.isEmpty()){
					for(Role haveRole : role){
						haveUser.getRoleId().remove(haveRole);
					}
				}
			}else{
				haveUser = user;
			}
			
			//获取分配的权限
			if(rightSign.equals("engineer")){
				Role r = (Role) baseDao.loadById(Role.class, 4);
				Set<Role> roleId = new HashSet<>();
				roleId.add(r);
				haveUser.setRoleId(roleId);
			}else if(rightSign.equals("agent")){
				Role r = (Role) baseDao.loadById(Role.class, 3);
				Set<Role> roleId = new HashSet<>();
				roleId.add(r);
				haveUser.setRoleId(roleId);
			}
			haveUser.setUserCaption(user.getUserCaption());
			haveUser.setPhone(user.getPhone());
			haveUser.setPassword(SysContent.md5(user.getPhone().substring(user.getPhone().length()-6)));
			haveUser.setIdCard(user.getIdCard());
			haveUser.setUserStatus(1);
			haveUser.setParentId(u.getParentId());
			//持久化user对象
			baseDao.saveOrUpdate(haveUser);
			map.put("code", 200);
			map.put("msg", "添加成功");
		}
		return map;
	}
	
	
	@Override//u是当前登录用户，user是接收数据参数
	public void addOrUpdateMediUser(User u ,User user,String rightSign) {
		if(u!=null){
			if(user.getUserId()==null){
				user.setUserId(SysContent.uuid());
				user.setCreateTime(DateTime.toString1(new Date()));
			}
			User haveUser = (User) baseDao.loadById(User.class, user.getUserId());
			if(haveUser!=null){
				Set<Role> role = haveUser.getRoleId();
				if(!role.isEmpty()){
					for(Role haveRole : role){
						haveUser.getRoleId().remove(haveRole);
					}
				}
			}else{
				haveUser = user;
			}
			
			//获取分配的权限
			if(rightSign.equals("medi")){
				Role r = (Role) baseDao.loadById(Role.class, 1);
				Set<Role> roleId = new HashSet<>();
				roleId.add(r);
				haveUser.setRoleId(roleId);
			}else if(rightSign.equals("shopowner")){
				Role r = (Role) baseDao.loadById(Role.class, 2);
				Set<Role> roleId = new HashSet<>();
				roleId.add(r);
				haveUser.setRoleId(roleId);
			}
			haveUser.setUserCaption(user.getUserCaption());
			haveUser.setPhone(user.getPhone());
			haveUser.setPassword(SysContent.md5(user.getPhone().substring(user.getPhone().length()-6)));
			haveUser.setIdCard(user.getIdCard());
			haveUser.setUserStatus(1);
			haveUser.setParentId(u.getParentId());
			//持久化user对象
			baseDao.saveOrUpdate(haveUser);
		}
	}


	@Override	
	public void updateUserInfo(User user, String doSign) {
		if(user!=null){
			//获取选择用户
			User u = (User) baseDao.loadById(User.class, user.getUserId());
			//重置用户密码
			if(doSign!=null && doSign.equals("reset")){
				u.setPassword(SysContent.md5(u.getPhone().substring(u.getPhone().length()-6)));
			}else if (doSign!=null && doSign.equals("enableOrdisable")){
				u.setUserStatus(user.getUserStatus());
			}else if(doSign!=null && doSign.equals("delete")){
				u.setUserStatus(2);
			}
			baseDao.saveOrUpdate(u);
		}
	}


	/**
	 * 中介和置业顾问app个人资料
	 * @param u
	 */
	@Override
	public Map findUserInfo(User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("photo", user.getPhoto());
		map.put("userName", user.getUserCaption());
		map.put("userPhone", user.getPhone());
		map.put("idCard", user.getIdCard());
		return map;
	}
	/**
	 * 中介和置业顾问app原来密码验证 
	 * 
	 */
	@Override
	public void findOldPassword(User user,String passwwrd) {
		if (passwwrd.equals(user.getPassword())) {
			
		}
	}

	/**
	 * 中介和置业顾问app密码修改
	 * 
	 */
	@Override
	public void updatePassowrd(User user, String firstPassword, String secondPassword) {
		user.setPassword(firstPassword);
		baseDao.saveOrUpdate(user);
	}

	/**
	 * 中介经纪人我的页面上面信息显示
	 */
	@Override
	public Map findMidInfo(User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("userCaption", user.getUserCaption());
		map.put("photo", user.getPhoto());
		int yijiesuan = 0;
		int weijiesuan = 0;
		int chengjiaoNum = 0;
		//查出该中介的所有订购成交的订购记录表 (已结算)
		String crhql = "from ContractRecords cr where cr.userId = '" + user.getUserId() + "' and cr.recordStatus = 5 and cr.shopPayConfirmTime is not null";
		List<ContractRecords> crList = baseDao.findByHql(crhql);
		for (ContractRecords contractRecords : crList) {
			//查带看表 查分销佣金比
			String pgHQL = "from ProjectGuide pg where pg.projectId = '" + contractRecords.getProjectId() + "'";
			ProjectGuide pg  = (ProjectGuide) baseDao.loadObject(pgHQL);
			if (pg != null && !"".equals(pg)){
				if (pg.getFenXiaoMoney() !=null && !"".equals(pg.getFenXiaoMoney())){
					if (contractRecords.getBuyPrice() != null && !"".equals(contractRecords.getBuyPrice())) {
						//已结算分销佣金
						yijiesuan += contractRecords.getBuyPrice()*pg.getFenXiaoMoney();
					}
				}
			}
		}
		//查该中介的成交的备案记录表
		String grHQL = "from GuideRecords gr where gr.userId = '" + user.getUserId() + "' and gr.isDeal = 1" ;
		List<GuideRecords> grList = baseDao.findByHql(grHQL);
		for (GuideRecords guideRecords : grList) {
			//查带看表 查带看佣金比
			String pgHQL = "from ProjectGuide pg where pg.projectId = '" + guideRecords.getProjectId() + "'";
			ProjectGuide pg  = (ProjectGuide) baseDao.loadObject(pgHQL);
			//根据备案记录,查订购表
			String crHQL2 = "from ContractRecords cr where cr.projectCustomerId = '" + guideRecords.getProjectCustomerId() + "' and cr.recordStatus = 5 and cr.shopPayConfirmTime is not null";
			ContractRecords cr = (ContractRecords) baseDao.loadObject(crHQL2);
			if (cr.getBuyPrice()!=null && !"".equals(cr.getBuyPrice())){
				if (pg.getDaiKanMoney() !=null &&!"".equals(pg.getDaiKanMoney())){
					//已结算带看佣金
					yijiesuan += cr.getBuyPrice()*pg.getDaiKanMoney();
				}
			}
		}
		//已结算佣金
		map.put("yijiesuan", yijiesuan+"元");
		
		//查出该中介的所有订购成交的订购记录表 (未结算)
		String crhql2 = "from ContractRecords cr where cr.userId = '" + user.getUserId() + "' and cr.recordStatus = 5 and cr.shopPayConfirmTime is null";
		List<ContractRecords> crList2 = baseDao.findByHql(crhql2);
		for (ContractRecords contractRecords : crList2) {
			//查带看表 查分销佣金比
			String pgHQL = "from ProjectGuide pg where pg.projectId = '" + contractRecords.getProjectId() + "'";
			ProjectGuide pg  = (ProjectGuide) baseDao.loadObject(pgHQL);
			if (contractRecords.getBuyPrice() !=null && !"".equals(contractRecords.getBuyPrice())) {
				if (pg.getFenXiaoMoney() != null && !"".equals(pg.getFenXiaoMoney())) {
					//未结算分销佣金
					weijiesuan += contractRecords.getBuyPrice()*pg.getFenXiaoMoney();
				}
			}
		}
		//查该中介的成交的备案记录表
		String grHQL2 = "from GuideRecords gr where gr.userId = '" + user.getUserId() + "' and gr.isDeal = 1" ;
		List<GuideRecords> grList2 = baseDao.findByHql(grHQL);
		for (GuideRecords guideRecords : grList2) {
			//查带看表 查带看佣金比
			String pgHQL = "from ProjectGuide pg where pg.projectId = '" + guideRecords.getProjectId() + "'";
			ProjectGuide pg  = (ProjectGuide) baseDao.loadObject(pgHQL);
			//根据备案记录,查订购表
			String crHQL2 = "from ContractRecords cr where cr.projectCustomerId = '" + guideRecords.getProjectCustomerId() + "' and cr.recordStatus = 5 and cr.shopPayConfirmTime is not null";
			ContractRecords cr = (ContractRecords) baseDao.loadObject(crHQL2);
			if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())){
				if (pg.getDaiKanMoney() !=null && !"".equals(pg.getDaiKanMoney())){
					//未结算带看佣金
					weijiesuan += cr.getBuyPrice()*pg.getDaiKanMoney();
				}
			}
		}
		//未结算佣金
		map.put("weijiesuan", weijiesuan+"元");
		
		//查出该中介的所有订购成交的订购记录表 
		String crhq3 = "from ContractRecords cr where cr.userId = '" + user.getUserId() + "' and cr.recordStatus = 5 ";
		List<ContractRecords> crList3 = baseDao.findByHql(crhq3);
		//分销成交的数量
		chengjiaoNum += crList3.size();
		
		//查该中介的成交的备案记录表
		String grHQL3 = "from GuideRecords gr where gr.userId = '" + user.getUserId() + "' and gr.isDeal = 1" ;
		List<GuideRecords> grList3 = baseDao.findByHql(grHQL3);
		//带看成交的数量
		chengjiaoNum += grList3.size();
		
		//成交数量
		map.put("chengjiaoNum", chengjiaoNum+"套");
		
		return map;
	}

	/**
	 * 中介经纪人我的页面业务统计
	 * @throws ParseException 
	 */
	@Override
	public Map findMidBusiness(User user) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//当前时间
		String nowTime = DateTime.toString1(new Date());
		//当前月第一天
		Date monthStart = DateUtil.getMonthStartTime();
		String monthStartTime = sdf.format(monthStart);
		//查备案成功的
		String grHQL = "from GuideRecords gr where gr.userId = '" + user.getUserId() + "' and ( gr.applyStatus = 0 or gr.applyStatus = 1)";
		List<GuideRecords> grList = baseDao.findByHql(grHQL);
		int beianNum = 0;
		if (grList != null && !"".equals(grList)) {
			for (GuideRecords guideRecords : grList) {
				String applyTime = guideRecords.getApplyTime();
				double x = sdf.parse(nowTime).getTime() - sdf.parse(applyTime).getTime();
				double y = sdf.parse(monthStartTime).getTime() - sdf.parse(applyTime).getTime();
				if (x >= 0 && y <= 0) {
					beianNum ++;
				}
			}
		}
		//本月备案成功的数量
		map.put("beianNum", beianNum);
		
		//查带看
		String grHQL2 = "from GuideRecords gr where gr.userId = '" + user.getUserId() + "' and gr.applyStatus = 1";
		List<GuideRecords> grList2 = baseDao.findByHql(grHQL2);
		int daikanNum = 0;
		if (grList2 != null && !"".equals(grList2)) {
			
			for (GuideRecords guideRecords : grList2) {
				String applyTime = guideRecords.getApplyTime();
				double x = sdf.parse(nowTime).getTime() - sdf.parse(applyTime).getTime();
				double y = sdf.parse(monthStartTime).getTime() - sdf.parse(applyTime).getTime();
				if (x >= 0 && y <= 0) {
					daikanNum ++;
				}
			}
		}
		// 本月带看的数量
		map.put("daikanNum", daikanNum);
		
		//查该中介分销成交
		String crHQL = "from ContractRecords cr where cr.userId = '" + user.getUserId() + "' and cr.recordStatus = 5 ";
		List<ContractRecords> crList = baseDao.findByHql(crHQL);
		//本月该中介分销成交的数量
		int midfenxiaoNum = 0;
		if (crList!=null && !"".equals(crList)) {
			for (ContractRecords contractRecords : crList) {
				String contractConfirmTime = contractRecords.getContractConfirmTime();
				if (contractConfirmTime !=null && !"".equals(contractConfirmTime)){
					double x = sdf.parse(nowTime).getTime() - sdf.parse(contractConfirmTime).getTime();
					double y = sdf.parse(monthStartTime).getTime() - sdf.parse(contractConfirmTime).getTime();
					if (x >= 0 && y <= 0) {
						midfenxiaoNum ++;
					}
				}
			}
		}
		
		//查该中介带看成交的数量
		//查带看
		String grHQL3 = "from GuideRecords gr where gr.userId = '" + user.getUserId() + "' and gr.applyStatus = 1";
		List<GuideRecords> grList3 = baseDao.findByHql(grHQL3);
		//本月该中介带看成交的数量
		int middaikanNum = 0;
		if (grList3!= null && !"".equals(grList3)) {
			
			for (GuideRecords guideRecords : grList3) {
				String projectCustomerId = guideRecords.getProjectCustomerId();
				//根据projectCustomerId 查置业顾问成交的该中介备案的客户
				String crHQL2 = "from ContractRecords cr where cr.projectCustomerId = '" + guideRecords.getProjectCustomerId() + "'";
				ContractRecords cr = (ContractRecords) baseDao.loadObject(crHQL2);
				if (cr != null && !"".equals(cr)){
					String contractConfirmTime = cr.getContractConfirmTime();
					if (contractConfirmTime !=null && !"".equals(contractConfirmTime)){
						double x = sdf.parse(nowTime).getTime() - sdf.parse(contractConfirmTime).getTime();
						double y = sdf.parse(monthStartTime).getTime() - sdf.parse(contractConfirmTime).getTime();
						if (x >= 0 && y <= 0) {
							middaikanNum ++;
						}
					}
				}
			}
		}
		
		//本月成交数量
		map.put("dealNum", midfenxiaoNum+middaikanNum);
		
		return map;
	}

	/**
	 * 置业顾问我的页面上面的信息显示
	 */
	@Override
	public Map findSaleInfo(User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("photo", user.getPhoto());
		map.put("userCaption", user.getUserCaption());
		return map;
	}

	/**
	 * 置业顾问我的页面业务统计
	 */
	@Override
	public Map findSaleBusiness(User user) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//当前时间
		String nowTime = DateTime.toString1(new Date());
		//当前月第一天
		Date monthStart = DateUtil.getMonthStartTime();
		String monthStartTime = sdf.format(monthStart);
		String hql = "from ContractRecords cr where cr.userId = '" + user.getUserId() + "' and cr.recordStatus = 5";
		List<ContractRecords> crList = baseDao.findByHql(hql);
		//本月成交数量
		int dealNum = 0;
		for (ContractRecords contractRecords : crList) {
			String contractConfirmTime = contractRecords.getContractConfirmTime();
			if (contractConfirmTime !=null && !"".equals(contractConfirmTime)) {
				double x = sdf.parse(nowTime).getTime() - sdf.parse(contractConfirmTime).getTime();
				double y = sdf.parse(monthStartTime).getTime() - sdf.parse(contractConfirmTime).getTime();
				if (x >= 0 && y <= 0) {
					dealNum ++;
				}
			}
		}
		map.put("dealNum", dealNum);
		return map;
	}
	
	/* 
	 * 
	 * 门店注册
	 * 
	 */
	@Override
	public void addShop(Shops shop, MultipartFile photoPic, MultipartFile licensePic, String province, String market,
			String area) throws Exception {

		/* 进行shop的持久化 */
		shop.setApplyTime(DateUtil.format(new Date()));
		shop.setShopStatus(0);
		shop.setCity(province + "-" + market + "-" + area);
		if (!photoPic.isEmpty() &&  photoPic.getSize() > 0) {
			String photoName = photoPic.getOriginalFilename();
			if (photoName.matches("(?i).+?\\.(jpg|gif|bmp|png|jpeg)")) {
				String photoRename = SysContent.getFileRename(photoName);
				String photoSavePath = PicUploadToYun.upload(photoRename, photoPic,SmsContext.SHOP_PIC);
				if (photoSavePath != null && !photoSavePath.equals("")) {
					shop.setPhoto(photoSavePath);
				}
			}
		}

		if (!licensePic.isEmpty() && licensePic.getSize() > 0) {
			String licenseName = licensePic.getOriginalFilename();
			if (licenseName.matches("(?i).+?\\.(jpg|gif|bmp|png|jpeg)")) {
				String licensePhotoRename = SysContent.getFileRename(licensePic.getOriginalFilename());
				String licensePhotophotoSavePath = PicUploadToYun.upload(licensePhotoRename, licensePic,SmsContext.SHOP_PIC);
				if (licensePhotophotoSavePath != null && !licensePhotophotoSavePath.equals("")) {
					shop.setLicensePhoto(licensePhotophotoSavePath);
				}
			}
		}
		shop.setInSystemStutas(0);
		baseDao.save(shop);

		/* 进行user的持久化处理 */
		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setCreateTime(shop.getApplyTime());
		user.setParentId(shop.getShopId().toString());
		user.setPhone(shop.getPhone());
		user.setUserCaption(shop.getContactPerson());
		user.setUserStatus(0);
		String password = shop.getPhone().substring(5);
		// 进行密码的加密
		String pwMd5 = SysContent.md5(password);
		user.setPassword(pwMd5);

		baseDao.save(user);

	}

	@Override
	public User findByUserToken(String userToken) {
		String hql = "from User where userToken = '"+userToken+"'";
		User user = (User) baseDao.loadObject(hql);
		return user;
	}
	
	
	@Override
	public boolean findExistPhoneNum(String phoneNum) {
		Integer phoneCount = 0;
		String hql = "select count(*) from User where phone = '"+phoneNum+"' and userStatus != 2";
		phoneCount = baseDao.countQuery(hql);
		if(phoneCount == 0){
			return true;
		}
		return false;
	}

	@Override
	public void getArray() {
		String[] str = new String[]{"1","2","3"};
		System.out.println(1/0);
		
	}
	
	private List<Map<String, Object>> getUserMap(String hql) {

		List<Map<String, Object>> listMap = new ArrayList<>();
		List<User> list = baseDao.findByHql(hql);
		boolean flag = true;
		for (User u : list) {
			// 查找当前用户的角色
			Set<Role> rSet = u.getRoleId();
			//防止数据库乱数据
			if (rSet != null && rSet.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}
			Map<String, Object> map = new HashMap<>();
			// 查找该用户所属上级
			Shops shop = null;
			if (u.getParentId() != null && !u.getParentId().equals("")) {
				Project project = (Project) baseDao.loadById(Project.class, u.getParentId());
				if(project != null){
					map.put("contactPerson", project.getProjectName());
				}else{
					shop = (Shops) baseDao.loadById(Shops.class, Integer.parseInt(u.getParentId()));
					if (shop != null) {
						map.put("contactPerson", shop.getContactPerson());
					}
				}
			}else{
				map.put("contactPerson", "");
			}
			map.put("userCaption", u.getUserCaption());
			map.put("phone", u.getPhone());
			map.put("createTime", u.getCreateTime());
			map.put("userStatus", u.getUserStatus());
			map.put("userId", u.getUserId());
			for (Role r : rSet) {
				map.put("roleId", r.getRoleId());
			}
			if (flag) {
				listMap.add(map);
			}
		}

		return listMap;
	}

	@Override
	public List<Map<String, Object>> findAllUser(String status) {
		List<Map<String, Object>> userList = null;
		if (status.equals("")) { // 所有的用户账户，不包括申请状态和删除状态。
			String hql = "from User where userStatus != 0 and userStatus != 2";
			userList = getUserMap(hql);
		} else if (status.equals("1")) { // 正常的账号
			String hql = "from User where userStatus = 1";
			userList = getUserMap(hql);
		} else if (status.equals("2")) { // 已经被禁用的账户
			String hql = "from User where userStatus = 3";
			userList = getUserMap(hql);
		}
		return userList;
	}
	
	@Override
	public void updateSystemUser(User us, User user, String doSign, String role) {
		if(us != null){
			User u = (User) baseDao.loadById(User.class, user.getUserId());
			//重置用户密码
			if(doSign!=null && doSign.equals("reset")){
				u.setPassword(SysContent.md5(u.getPhone().substring(u.getPhone().length()-6)));
			}
			//更换角色
			Role roles = (Role) baseDao.loadById(Role.class, Integer.parseInt(role));
			Set<Role> rSet = new HashSet<>();
			rSet.add(roles);
			u.setRoleId(rSet);
			//change phone
			u.setPhone(user.getPhone());
			//更换身份证号
			u.setIdCard(user.getIdCard());
			baseDao.saveOrUpdate(u);
		}
	}

	@Override
	public Boolean addOrUpdateUserPassWorld(User u, String oldPsw, String newPsw) {
		oldPsw = SysContent.md5(oldPsw.trim());
		newPsw = SysContent.md5(newPsw.trim());
		if(u!=null && oldPsw!=null && newPsw!=null){
			User user = (User) baseDao.loadById(User.class, u.getUserId());
			if(user.getPassword().equals(oldPsw)){
				user.setPassword(newPsw);
				baseDao.saveOrUpdate(user);
				return true;
			}
		}
		return false;
	}

	/**
	 * 中介经纪人我的动态
	 * @return
	 */
	@Override
	public List<Mydynamic> findMidMydynamic(User user) {
		String hql = "from Mydynamic where userId = '"+user.getUserId()+"' order by creatTime DESC";
		List<Mydynamic> mList = baseDao.findByHql(hql);
		
		return mList;
	}

	/**
	 * 中介经纪人我的动态改已读
	 * @return
	 */
	@Override
	public void updateMidMydynamic(User user, Integer dynamicId) {

		String hql = "from Mydynamic where dynamicId = '" + dynamicId + "' ";
		Mydynamic mydynamic = (Mydynamic) baseDao.loadObject(hql);
		//改已读
		mydynamic.setIsRead(1);
		mydynamic.setReadTime(DateTime.toString1(new Date()));
		
		baseDao.saveOrUpdate(mydynamic);
	}

	/**
	 * 中介经纪人我的动态未读数量显示
	 * @return
	 */
	@Override
	public int toGetMydynamicNotReadNum(User user) {
		String hql = "select count(*) from Mydynamic where userId = '"+user.getUserId()+"' and isRead = 0";
		int count = baseDao.countQuery(hql);
		
		return count;
	}

	/**
	 * 经理app分割线__________________________________________________________________________________
	 */
	/**
	 * 经理今日接访未登记
	 * @throws ParseException 
	 */
	@Override
	public List<VisitRecordsDTO> findToadyVisitRecords(String startTime,String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		
		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '"+ user.getParentId() + "' and arriveTime >= '"+startTimeStr + "' and arriveTime <= '"+endTimeStr + "' and writeState != 1");
		List<VisitRecordsDTO> vrdtoList = new ArrayList<>();
		if (vrList.size()>0){
			for (VisitRecords vr : vrList) {
				User agent = (User) baseDao.loadObject("from User where userId = '"+vr.getUserId()+ "' ");
				VisitRecordsDTO vrdto = new VisitRecordsDTO();
				VisitRecordsDTO DtoVr = vrdto.getVisitRecordsDTO(vr, agent);
				vrdtoList.add(DtoVr);
			}
		}
		
		return vrdtoList;
	}

	/**
	 *  案场今日备案数量和备案为到访数量
	 * @throws ParseException 
	 */
	@Override
	public Map<String, String> findTodayGuideRecords(String startTime,String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Map<String, String> map = new HashMap<>();
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		
		List<GuideRecords> grList = baseDao.findByHql("from GuideRecords where applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+ "' and projectId = '"+user.getParentId()+ "' ");
		if (grList.size()>0){
			map.put("haveRecords", grList.size()+"组");
			
			//已到访
			List<GuideRecords> grList2 = baseDao.findByHql("from GuideRecords where applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+ "' and projectId = '"+user.getParentId()+ "' and visitNo is not null");
			if (grList2.size()>0){
				map.put("haveVisit", grList2.size()+"组");
				//有效到访率
				map.put("percent", Integer.parseInt(SysContent.get2Double((double)(grList2.size()/grList.size())*100))+"%");
			}else {
				//有效到访率
				map.put("percent", "0");
			}
			List<GuideRecords> grList3 = baseDao.findByHql("from GuideRecords where applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+ "' and projectId = '"+user.getParentId()+ "' and visitNo is null");
			if (grList3.size()>0){
				//未到访
				map.put("notVisit", grList3.size()+"组");
			}
			
		}else {
			map.put("haveRecords", "0组");
			map.put("percent", "暂无");
		}
		
		return map;
	}

	
	@Override
	public Map<String, Integer> findCheckCustomerNum(String startTime, String endTime, User user)
			throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Map<String, Integer> map = new HashMap<>();
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		List<ProjectCustomers> pcList = baseDao.findByHql("from ProjectCustomers where projectId = '"+user.getParentId()+"' and lastTime >= '"+startTimeStr+"' and lastTime <= '"+ endTimeStr+"' ");
		if (pcList.size()>0) {
			map.put("allCustomerNum", pcList.size());
			int notCheckedNum = 0;
			for (ProjectCustomers pc : pcList) {
				if (pc.getEvaluate() == null || pc.getEvaluate() == "" ){
					notCheckedNum ++;
				}
			}
			map.put("notCheckedNum", notCheckedNum);
		}else {
			map.put("allCustomerNum", 0);
			map.put("notCheckedNum", 0);
		}
		return map;
	}
	
	
	@Override
	public List<GrDto> findGuideRecordsDetail(String startTime, String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		List<String> sList = new ArrayList<>();
		
		//一段时间内的备案总数
		List<GuideRecords> grList = baseDao.findByHql("from GuideRecords where applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+ "' and projectId = '"+user.getParentId()+ "'");
		if (grList.size()>0){
			for (GuideRecords gr : grList) {
				sList.add(gr.getShopCustomerId());
			}
		}
		Set<Integer> inset = new HashSet<>();
		for (String string : sList) {
			ShopCustomers sc = (ShopCustomers) baseDao.loadObject("from ShopCustomers where shopCustomerId = '"+string+"' ");
			inset.add(sc.getShopId());
		}
		
		List<GrDto> grdtoList = new ArrayList<>();
		for (Integer integer : inset) {
			GrDto grdto = new GrDto();
			int haveRecordsNum = 0;
			int haveVisitNum = 0;
			int notVisitNum = 0;
			List<ShopCustomers> scList = baseDao.findByHql("from ShopCustomers where shopId = '"+integer+"' ");
			for (ShopCustomers sc : scList) {
				List<GuideRecords> grList2 = baseDao.findByHql("from GuideRecords where applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+ "' and projectId = '"+user.getParentId()+ "' and shopCustomerId = '"+ sc.getShopCustomerId()+"' ");
				if (grList2.size()>0){
					haveRecordsNum += grList2.size();
				}
				List<GuideRecords> grList3 = baseDao.findByHql("from GuideRecords where applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+ "' and projectId = '"+user.getParentId()+ "' and shopCustomerId = '"+ sc.getShopCustomerId()+"' and visitNo is not null");
				if (grList3.size()>0){
					haveVisitNum += grList3.size();
				}
				List<GuideRecords> grList4 = baseDao.findByHql("from GuideRecords where applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+ "' and projectId = '"+user.getParentId()+ "' and shopCustomerId = '"+ sc.getShopCustomerId()+"' and visitNo is null");
				if (grList4.size()>0){
					haveVisitNum += grList4.size();
				}
			}
			grdto.setGrAllNum(haveRecordsNum+"");
			grdto.setHaveVisitNum(haveVisitNum+"");
			grdto.setNotVisitNum(notVisitNum+"");
			Shops shops = (Shops) baseDao.loadObject("from Shops where shopId = '"+integer + "' ");
			grdto.setShopId(shops.getShopId());
			grdto.setShopName(shops.getShopName());
			grdto.setAddress(shops.getAddress());
			grdto.setPhone(shops.getPhone());
			
			grdtoList.add(grdto);
		}
		return grdtoList;
	}

	/**
	 * 排名
	 * @throws ParseException 
	 */
	@Override
	public List<RankingDto> findVisitRanking(String startTime, String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		List<User> uList = baseDao.findByHql("from User where parentId = '"+user.getParentId()+ "' ");
		List<RankingDto> rdtoList = new ArrayList<>();
		for (User u : uList) {
			RankingDto rdto = new RankingDto();
			List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where userId = '"+u.getUserId()+"' and arriveTime >= '"+startTimeStr+"' and arriveTime <= '"+endTimeStr+"' ");
			if (vrList.size()>0) {
				rdto.setVisitNum(vrList.size());
			}else {
				rdto.setVisitNum(0);
			}
			rdto.setUserId(u.getUserId());
			rdto.setPhone(u.getPhone());
			rdtoList.add(rdto);
		}
		for (int i = 0; i < rdtoList.size(); i++) {
			for (int j = 0; j < rdtoList.size()-1-i; j++) {
				if (rdtoList.get(j).getVisitNum()<rdtoList.get(j+1).getVisitNum()){
					RankingDto r = rdtoList.get(j);
					rdtoList.set(j, rdtoList.get(j+1));
					rdtoList.set(j+1, r);
				}
				
			}
		}
		
		return rdtoList;
	}

	
	@Override
	public List<RankingDto> findRengouRanking(String startTime, String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		List<User> uList = baseDao.findByHql("from User where parentId = '"+user.getParentId()+ "' ");
		List<RankingDto> rdtoList = new ArrayList<>();
		for (User u : uList) {
			RankingDto rdto = new RankingDto();
			
			List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+u.getUserId()+"' and applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+"' and (recordStatus = 4 or recordStatus = 0 or recordStatus = 1)");
			if (crList.size()>0) {
				rdto.setRengouNum(crList.size());
			}else {
				rdto.setRengouNum(0);
			}
			rdto.setUserId(u.getUserId());
			rdto.setPhone(u.getPhone());
			rdtoList.add(rdto);
		}
		for (int i = 0; i < rdtoList.size(); i++) {
			for (int j = 0; j < rdtoList.size()-1-i; j++) {
				if (rdtoList.get(j).getRengouNum()<rdtoList.get(j+1).getRengouNum()){
					RankingDto r = rdtoList.get(j);
					rdtoList.set(j, rdtoList.get(j+1));
					rdtoList.set(j+1, r);
				}
				
			}
		}
		return rdtoList;
	}

	
	@Override
	public List<RankingDto> findContractRanking(String startTime, String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		List<User> uList = baseDao.findByHql("from User where parentId = '"+user.getParentId()+ "' ");
		List<RankingDto> rdtoList = new ArrayList<>();
		for (User u : uList) {
			RankingDto rdto = new RankingDto();
			
			List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+u.getUserId()+"' and applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+"' and recordStatus = 5 ");
			if (crList.size()>0) {
				rdto.setContractNum(crList.size());
			}else {
				rdto.setContractNum(0);
			}
			rdto.setUserId(u.getUserId());
			rdto.setPhone(u.getPhone());
			rdtoList.add(rdto);
		}
		for (int i = 0; i < rdtoList.size(); i++) {
			for (int j = 0; j < rdtoList.size()-1-i; j++) {
				if (rdtoList.get(j).getContractNum()<rdtoList.get(j+1).getContractNum()){
					RankingDto r = rdtoList.get(j);
					rdtoList.set(j, rdtoList.get(j+1));
					rdtoList.set(j+1, r);
				}
				
			}
		}
		return rdtoList;
	}

	
	@Override
	public List<RankingDto> findSlewRateRanking(String startTime, String endTime, User user) throws ParseException {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		List<User> uList = baseDao.findByHql("from User where parentId = '"+user.getParentId()+ "' ");
		List<RankingDto> rdtoList = new ArrayList<>();
		for (User u : uList) {
			RankingDto rdto = new RankingDto();
			List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where userId = '"+u.getUserId()+"' and arriveTime >= '"+startTimeStr+"' and arriveTime <= '"+endTimeStr+"' ");
			if (vrList.size()>0) {
				rdto.setVisitNum(vrList.size());
			}else {
				rdto.setVisitNum(0);
			}
			
			List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where userId = '"+u.getUserId()+"' and applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+"' and recordStatus = 5 ");
			if (crList.size()>0) {
				rdto.setContractNum(crList.size());
			}else {
				rdto.setContractNum(0);
			}
			if (vrList.size()>0 && crList.size()>0) {
				rdto.setSlewRate(Integer.parseInt(SysContent.get2Double((double)(crList.size()/vrList.size())*100)));
			}else if (vrList.size()>0 && crList.size()<=0){
				rdto.setSlewRate(0);
			}
			rdto.setUserId(u.getUserId());
			rdto.setPhone(u.getPhone());
			rdtoList.add(rdto);
		}
		for (int i = 0; i < rdtoList.size(); i++) {
			for (int j = 0; j < rdtoList.size()-1-i; j++) {
				if (rdtoList.get(j).getSlewRate()<rdtoList.get(j+1).getSlewRate()){
					RankingDto r = rdtoList.get(j);
					rdtoList.set(j, rdtoList.get(j+1));
					rdtoList.set(j+1, r);
				}
				
			}
		}
		return rdtoList;
	}

	
	@Override
	public Map<String, Object> findVisitStatement(String startTime, String endTime, User user) throws ParseException {
		
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '"+ user.getParentId() + "' and arriveTime >= '"+startTimeStr + "' and arriveTime <= '"+endTimeStr + "' ");
			
		
		//替接接访时长
		long replaceTime = 0L;
		//新客户接访时长
		long newVisitTime = 0L;
		//老客户接访时长
		long oldVisitTime = 0L;
		//平均接访时常
		long timeDiff = 0L;
		int totalNum = 0;
		double AverageLongTime = 0.00;
		String strAverageLongTime = "0.00";
		//总接访量
		int allVisitNum = 0;
		//有效接访量
		int effectiveNum = 0;
		//无效接访量
		int invalidNum = 0;
		//新客户通道接访
		int newCustomerNum = 0;
		//老客户通道接访
		int oldCustomerNum = 0;
		//新客户通道有效接访
		int newCustomerEffectiveNum = 0;
		//老客户通道有效接访
		int oldCustomerEffectiveNum = 0;
		//指定接访量
		int appointAgentNum = 0;
		//新客户通道指定接访
		int newCustomerAppointAgentNum = 0;
		//老客户通道指定接访
		int oldCustomerAppointAgentNum = 0;
		//指定有效接访
		int appointAgentEffectiveNum = 0;
		//总替接数
		int allReplaceNum = 0;
		//按序接访替接
		int orderReplaceNum = 0;
		
		if (vrList.size()>0){
			//总接访量
			map.put("allVisitNum", vrList.size());
			
			for (VisitRecords vr : vrList) {
				
				if (vr.getWriteState() == 1){
					effectiveNum ++;
				}else {
					invalidNum ++;
				}
				if (vr.getIsNew()){
					newCustomerNum ++;
					if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime())){
						long newLeave = DateUtil.parse(vr.getLeaveTime()).getTime();;
						long newRecept = DateUtil.parse(vr.getReceptTime()).getTime();
						newVisitTime += newLeave - newRecept;
					}
					if (vr.getWriteState() == 1){
						newCustomerEffectiveNum ++;
					}
				}else {
					oldCustomerNum ++;
					if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime())){
						long oldLeave = DateUtil.parse(vr.getLeaveTime()).getTime();;
						long oldRecept = DateUtil.parse(vr.getReceptTime()).getTime();
						oldVisitTime += oldLeave - oldRecept;
					}
					if (vr.getWriteState() == 1){
						oldCustomerEffectiveNum ++;
					}
				}
				if (vr.getAppointUserId() != null && !"".equals(vr.getAppointUserId())){
					//指定
					if (vr.getAppointUserId().equals(vr.getUserId())){
						appointAgentNum ++;
						if (vr.getIsNew()){
							newCustomerAppointAgentNum ++;
						}else {
							oldCustomerAppointAgentNum ++;
						}
						if (vr.getWriteState() == 1){
							appointAgentEffectiveNum ++;
						}
						//替接
					}else {
						allReplaceNum ++;
						if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime())){
							long replaceLeave = DateUtil.parse(vr.getLeaveTime()).getTime();;
							long replaceRecept = DateUtil.parse(vr.getReceptTime()).getTime();
							replaceTime += replaceLeave - replaceRecept;
						}
					}
				}/*else {
					orderReplaceNum ++;
				}*/
				
				if (vr.getLeaveTime() != null && !"".equals(vr.getLeaveTime())){
					long leave = DateUtil.parse(vr.getLeaveTime()).getTime();;
					long recept = DateUtil.parse(vr.getReceptTime()).getTime();
					timeDiff += leave -recept;
					totalNum++;
				}
			}
			//平均接待时间
			if(totalNum>0){
				AverageLongTime = timeDiff/totalNum/1000/60;
				strAverageLongTime = SysContent.get2Double(AverageLongTime);
				//每次平均解放时长
				map.put("averageReceptionTime", strAverageLongTime);
			}
			map.put("allTime", timeDiff/1000/60/60+"小时");
			map.put("newVisitTime", newVisitTime/1000/60/60+"小时");
			map.put("oldVisitTime", oldVisitTime/1000/60/60+"小时");
			map.put("replaceTime", replaceTime/1000/60/60+"小时");
			int dayNum = DateUtil.getOffsetDays(sdf.parse(endTimeStr), sdf.parse(startTimeStr));
			if (dayNum >0 ){
				map.put("everyDayAverageTime", timeDiff/dayNum/1000/60/60+"小时");
			}else {
				map.put("everyDayAverageTime", "暂无");
			}
			
			
			map.put("effectiveNum", effectiveNum);
			map.put("effectiveRate", Integer.parseInt(SysContent.get2Double((double)(effectiveNum/vrList.size())*100))+"%");
			map.put("invalidNum", invalidNum);
			map.put("newCustomerNum", newCustomerNum);
			map.put("oldCustomerNum", oldCustomerNum);
			map.put("newCustomerEffectiveNum", newCustomerEffectiveNum);
			map.put("oldCustomerEffectiveNum", oldCustomerEffectiveNum);
			if (newCustomerNum != 0){
				map.put("newCustomerEffectiveRate", Integer.parseInt(SysContent.get2Double((double)(newCustomerEffectiveNum/newCustomerNum)*100))+"%");
				map.put("newCustomerAppointAgentRate", Integer.parseInt(SysContent.get2Double((double)(newCustomerAppointAgentNum/newCustomerNum)*100))+"%");
			}else {
				map.put("newCustomerEffectiveRate", "暂无");
			}
			if (oldCustomerNum != 0){
				map.put("oldCustomerEffectiveRate", Integer.parseInt(SysContent.get2Double((double)(oldCustomerEffectiveNum/oldCustomerNum)*100))+"%");
				map.put("oldCustomerAppointAgentRate", Integer.parseInt(SysContent.get2Double((double)(oldCustomerAppointAgentNum/oldCustomerNum)*100))+"%");
			}else {
				map.put("oldCustomerEffectiveRate", "暂无");
			}
			map.put("oldCustomerRate", Integer.parseInt(SysContent.get2Double((double)(oldCustomerNum/vrList.size())*100))+"%");
			map.put("appointAgentNum", appointAgentNum);
			map.put("newCustomerAppointAgentNum", newCustomerAppointAgentNum);
			map.put("oldCustomerAppointAgentNum", oldCustomerAppointAgentNum);
			map.put("appointAgentEffectiveNum", appointAgentEffectiveNum);
			if (appointAgentNum != 0){
				map.put("appointAgentEffectiveRate", Integer.parseInt(SysContent.get2Double((double)(appointAgentEffectiveNum/appointAgentNum)*100))+"%");
			}else {
				map.put("appointAgentEffectiveRate", "暂无");
			}
			map.put("appointAgentRate", Integer.parseInt(SysContent.get2Double((double)(appointAgentNum/vrList.size())*100))+"%");
			map.put("allReplaceNum", allReplaceNum);
			if (allReplaceNum != 0){
				map.put("allReplaceRate", Integer.parseInt(SysContent.get2Double((double)(allReplaceNum/vrList.size())*100))+"%");
			}else {
				map.put("allReplaceRate", "暂无");
			}
			
			
		}else {
			
			map.put("averageReceptionTime", "暂无");
			map.put("allTime", "暂无");
			map.put("newVisitTime", "暂无");
			map.put("oldVisitTime", "暂无");
			map.put("replaceTime", "暂无");
			map.put("everyDayAverageTime", "暂无");
			
			map.put("allVisitNum", "暂无");
			map.put("effectiveNum", "暂无");
			map.put("invalidNum", "暂无");
			map.put("effectiveRate", "暂无");
			map.put("newCustomerNum", "暂无");
			map.put("oldCustomerNum", "暂无");
			map.put("newCustomerEffectiveNum", "暂无");
			map.put("oldCustomerEffectiveNum", "暂无");
			map.put("newCustomerEffectiveRate", "暂无");
			map.put("oldCustomerEffectiveRate", "暂无");
			map.put("oldCustomerRate", "暂无");
			map.put("newCustomerAppointAgentRate", "暂无");
			map.put("oldCustomerAppointAgentRate", "暂无");
			map.put("appointAgentNum", "暂无");
			map.put("newCustomerAppointAgentNum", "暂无");
			map.put("oldCustomerAppointAgentNum", "暂无");
			map.put("appointAgentEffectiveNum", "暂无");
			map.put("appointAgentEffectiveRate", "暂无");
			map.put("appointAgentRate", "暂无");
			map.put("allReplaceNum", "暂无");
			map.put("allReplaceRate", "暂无");
			
		}
		
		return map;
	}

	/**
	 * 中介经纪人分割线___________________________________________________________________________________________________________________________
	 */
	/**
	 * 中介app城市存session____________________________________________________
	 */
	@Override
	public CitySessionDTO findCityIntoSession(User user) {
		
		Shops shops = (Shops) baseDao.loadObject("from Shops where shopId = '"+user.getParentId()+"' ");
		String city = shops.getCity();
		String[] cityArr = city.split("-");
		CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject("from CountryProvinceInfo where cityId = '"+cityArr[1]+"' ");
		CitySessionDTO csdto = new CitySessionDTO();
		csdto.setCityId(cp.getCityId());
		csdto.setCityName(cp.getCityName());
		
		return csdto;
	}

	@Override
	public CitySessionDTO findCityIntoSessionByCityId(String cityId,User user) {
		CitySessionDTO csdto = new CitySessionDTO();
		
		if (cityId != null && !"".equals(cityId)){
			CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject("from CountryProvinceInfo where cityId = '"+cityId+"' ");
			csdto.setCityId(cityId);
			csdto.setCityName(cp.getCityName());
			
		}else {
			Shops shops = (Shops) baseDao.loadObject("from Shops where shopId = '"+user.getParentId()+"' ");
			String city = shops.getCity();
			String[] cityArr = city.split("-");
			CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject("from CountryProvinceInfo where cityId = '"+cityArr[1]+"' ");
			csdto.setCityId(cp.getCityId());
			csdto.setCityName(cp.getCityName());
		}
		return csdto;
	}

	@Override
	public List<AllCheckedCustomerDTO> findAllCheckedCustomer(String startTime, String endTime, User user)
			throws ParseException {
		List<AllCheckedCustomerDTO> accdtoList = new ArrayList<>();
		
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		
		Set<String> set = new HashSet<String>();
		
		List<ProjectCustomers> pcList = baseDao.findByHql("from ProjectCustomers where projectId = '"+user.getParentId()+"' and lastTime >= '"+startTimeStr+"' and lastTime <= '"+ endTimeStr+"' ");
		if (pcList.size()>0){
			for (ProjectCustomers pc : pcList) {
				set.add(pc.getOwnerUserId());
			}
			for (String string : set) {
				AllCheckedCustomerDTO accdto = new AllCheckedCustomerDTO();
				User u = (User) baseDao.loadObject("from User where userId = '"+string+"' ");
				accdto.setAgentId(u.getUserId());
				accdto.setAgentName(u.getUserCaption());
				accdto.setPhone(u.getPhone());
				accdto.setPhoto(u.getPhoto());
				List<ProjectCustomers> pcList2 = baseDao.findByHql("from ProjectCustomers where projectId = '"+user.getParentId()+"' and lastTime >= '"+startTimeStr+"' and lastTime <= '"+ endTimeStr+"' and evaluate is not null");
				if (pcList2.size()>0){
					accdto.setHaveCheckedNum(pcList2.size());
					accdto.setHaveCheckedList(pcList2);
				}else {
					accdto.setHaveCheckedNum(0);
				}
				List<ProjectCustomers> pcList3 = baseDao.findByHql("from ProjectCustomers where projectId = '"+user.getParentId()+"' and lastTime >= '"+startTimeStr+"' and lastTime <= '"+ endTimeStr+"' and evaluate is null");
				if (pcList3.size()>0){
					accdto.setNotCheckedNum(pcList3.size());
					accdto.setNotCheckedList(pcList3);
				}else {
					accdto.setNotCheckedNum(0);
				}
				
				accdtoList.add(accdto);
			}
		}
		
		return accdtoList;
	}

	
	@Override
	public void updateProjectCustomerByManager(String projectCustomerId,String evaluate,User user) {

		if (projectCustomerId != null && !"".equals(projectCustomerId)){
			ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject("from ProjectCustomers where projectCustomerId = '"+projectCustomerId+"' ");
			if (evaluate != null && !"".equals(evaluate)) {
				pc.setEvaluate(evaluate);
				
				baseDao.saveOrUpdate(pc);
			}
		}
	}

	
	@Override
	public Map<String, String> findCustomerAffiliation(String projectCustomerId, User user) {
		
		Map<String, String> map = new HashMap<>();
		if (projectCustomerId != null && !"".equals(projectCustomerId)){
			ProjectCustomers pc = (ProjectCustomers) baseDao.loadObject("from ProjectCustomers where projectCustomerId = '"+projectCustomerId+"' ");
			map.put("agentId", pc.getOwnerUserId());
			User u = (User) baseDao.loadObject("from User where userId = '"+pc.getOwnerUserId()+"' ");
			
			map.put("agentName", u.getUserCaption());
			map.put("agentPhone", u.getPhone());
		}
		
		return map;
	}

	
	/**
	 * 业务报表_成交________ 
	 */
	@Override
	public Map<String, Object> findDealStatement(String startTime, String endTime, User user) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		//认购套数
		int allContractNum = 0;
		Set<Integer> st = new HashSet<>();
		//认购金额
		double allContractMoney = 0.00;
		//认购到款金额
		double reachContractMoney = 0.00;
		//认购锁定房源货值
		double lockHousePrice = 0.00;
		//已签约数
		int haveDealNum = 0;
		//放弃签约数
		int abandonDealNum = 0;
		//待签约数
		int readyToDealNum = 0;
		//已签约房源货值
		double haveDealHousePrice = 0.00;
		//放弃签约房源货值
		double abandonDealHousePrice = 0.00;
		//待签约房源货值
		double readyToDealHousePrice = 0.00;
		//按揭到款套数
		int haveGetMoneyNum = 0;
		
		List<House> hList = baseDao.findByHql("from House where projectId = '"+user.getParentId()+"' ");

		List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId ='"+user.getParentId()+"' and applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+"' ");
		if (crList.size()>0){
			for (ContractRecords cr : crList) {
				st.add(cr.getHouseNum());
				if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())){
					allContractMoney += cr.getBuyPrice();
				}
				if (cr.getRecordStatus() == 4){
					readyToDealNum ++;
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())){
						reachContractMoney += cr.getBuyPrice();
						House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+"' ");
						lockHousePrice += h.getListPrice();
						readyToDealHousePrice += h.getListPrice();
					}
					if (cr.getAccountStyle() == 2){
						haveGetMoneyNum ++;
					}
				}
				if (cr.getRecordStatus() == 5){
					haveDealNum ++;
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())){
						House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+"' ");
						haveDealHousePrice += h.getListPrice();
					}
				}
				if (cr.getRecordStatus() == 7 ){
					abandonDealNum ++;
					if (cr.getBuyPrice() != null && !"".equals(cr.getBuyPrice())){
						House h = (House) baseDao.loadObject("from House where houseNum = '"+cr.getHouseNum()+"' ");
						abandonDealHousePrice += h.getListPrice();
					}
				}
			}
			if (st.size()>0){
				map.put("allContractNum", st.size());
				//认购套数占比
				map.put("readyToAllPercent", Integer.parseInt(SysContent.get2Double((double)(allContractNum/st.size())*100))+"%");
			}else {
				map.put("allContractNum", "暂无");
				map.put("readyToAllPercent", "暂无");
			}
			map.put("allContractMoney", SysContent.get2Double(allContractMoney/10000)+"万元");
			map.put("reachContractMoney", SysContent.get2Double(reachContractMoney/10000)+"万元");
			map.put("haveDealNum", haveDealNum);
			map.put("lockHousePrice", SysContent.get2Double(reachContractMoney/10000)+"万元");
			map.put("abandonDealNum", abandonDealNum);
			map.put("readyToDealNum", readyToDealNum);
			map.put("haveDealHousePrice", SysContent.get2Double(haveDealHousePrice/10000)+"万元");
			map.put("abandonDealHousePrice", SysContent.get2Double(abandonDealHousePrice/10000)+"万元");
			map.put("readyToDealHousePrice", SysContent.get2Double(readyToDealHousePrice/10000)+"万元");
			
			List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '"+ user.getParentId() + "' and arriveTime >= '"+startTimeStr + "' and arriveTime <= '"+endTimeStr + "' ");
			if (vrList.size()>0){
				//来访成交比
				map.put("visitToDealRate", Integer.parseInt(SysContent.get2Double((double)(haveDealNum/vrList.size())*100))+"%");
			}else {
				map.put("visitToDealRate", "暂无");
			}
			if (allContractMoney != 0){
				//认购签约率
				map.put("readyToDealRate", Integer.parseInt(SysContent.get2Double((double)(haveDealNum/crList.size())*100))+"%");
			}else {
				map.put("readyToDealRate", "暂无");
			}
			if (hList.size()>0){
				//签约套数比
				map.put("haveToDealPercent", Integer.parseInt(SysContent.get2Double((double)(haveDealNum/hList.size())*100))+"%");
				//按揭到款套数比
				map.put("mortgageToGetPercent", Integer.parseInt(SysContent.get2Double((double)(haveGetMoneyNum/hList.size())*100))+"%");
			}else {
				map.put("haveToDealPercent", "暂无");
				map.put("mortgageToGetPercent", "暂无");
			}
			
		}else {
			map.put("allContractNum", "暂无");
			map.put("allContractMoney", "暂无");
			map.put("reachContractMoney", "暂无");
			map.put("haveDealNum", "暂无");
			map.put("lockHousePrice", "暂无");
			map.put("abandonDealNum", "暂无");
			map.put("readyToDealNum", "暂无");
			map.put("haveDealHousePrice", "暂无");
			map.put("abandonDealHousePrice", "暂无");
			map.put("readyToDealHousePrice", "暂无");
			map.put("visitToDealRate", "暂无");
			map.put("readyToDealRate", "暂无");
			map.put("readyToAllPercent", "暂无");
			map.put("haveToDealPercent", "暂无");
			map.put("mortgageToGetPercent", "暂无");
			
		}
		
		
		return null;
	}

	
	@Override
	public Map<String, Object> findOutFieldStatement(String startTime, String endTime, User user)
			throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		//报备到访数
		int grToGetVisitNum = 0;
		//外场成交数
		int outFieldToDealNum = 0;
		
		//备案客户数
		List<GuideRecords> grList = baseDao.findByHql("from GuideRecords where applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+ "' and projectId = '"+user.getParentId()+ "' ");
		if (grList.size()>0){
			map.put("allGrNum", grList.size());
			for (GuideRecords gr : grList) {
				if (gr.getVisitNo() != null && !"".equals(gr.getVisitNo())){
					grToGetVisitNum ++;
				}
			}
			map.put("grToGetVisitNum", grToGetVisitNum);
			map.put("grToNotGetVisitNum", grList.size() - grToGetVisitNum);
			map.put("grToVisitRate", grToGetVisitNum/grList.size());
			
			List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '"+ user.getParentId() + "' and arriveTime >= '"+startTimeStr + "' and arriveTime <= '"+endTimeStr + "' and isNew is true");
			if (vrList.size()>0){
				//外场导客占比
				map.put("outFieldToVisitRate", grToGetVisitNum/vrList.size());
			}else {
				map.put("outFieldToVisitRate", "暂无");
			}
			List<String> sList = new ArrayList<>();
			//带客成交数
			int guideToDealNum = 0;
			
			List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId = '"+user.getParentId()+"' and applyTime >= '"+startTimeStr + "' and applyTime <= '"+endTimeStr + "' ");
			if (crList.size()>0){
				for (ContractRecords cr : crList) {
					if (cr.getRecordStatus() == 5){
						String userId = cr.getUserId();
						User u = (User) baseDao.loadObject("from User where userId = '"+cr.getUserId()+"' ");
						Set<Role> sr = u.getRoleId();
						for (Role role : sr) {
							if (role.getRoleId() == 1 || role.getRoleId() == 2){
								outFieldToDealNum ++;
							}
							if (role.getRoleId() == 3){
								sList.add(cr.getCustomerPhone());
							}
						}
					}
					map.put("outFieldToDealNum", outFieldToDealNum);
					map.put("outFieldToDealRate", outFieldToDealNum/crList.size());
					
				}
				for (String string : sList) {
					List<GuideRecords> grList2 = baseDao.findByHql("from GuideRecords where applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+ "' and projectId = '"+user.getParentId()+ "' and customerPhone = '"+string+"' ");
					if (grList2.size()>0){
						guideToDealNum ++;
					}
				}
				map.put("guideToDealNum", guideToDealNum);
				
			}else {
				map.put("outFieldToVisitRate", "暂无");
				map.put("guideToDealNum", "暂无");
				map.put("outFieldToVisitNum", "暂无");
				map.put("outFieldToVisitRate", "暂无");
			}
		
		}else {
			map.put("allGrNum", "暂无");
			map.put("grToGetVisitNum", "暂无");
			map.put("grToNotGetVisitNum", "暂无");
			map.put("grToVisitRate", "暂无");
			
			List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '"+ user.getParentId() + "' and arriveTime >= '"+startTimeStr + "' and arriveTime <= '"+endTimeStr + "' and isNew is true");
			if (vrList.size()>0){
				//外场导客占比
				map.put("outFieldToVisitRate", grToGetVisitNum/vrList.size());
			}else {
				map.put("outFieldToVisitRate", "暂无");
			}
			List<String> sList = new ArrayList<>();
			//带客成交数
			int guideToDealNum = 0;
			
			List<ContractRecords> crList = baseDao.findByHql("from ContractRecords where projectId = '"+user.getParentId()+"' and applyTime >= '"+startTimeStr + "' and applyTime <= '"+endTimeStr + "' ");
			if (crList.size()>0){
				for (ContractRecords cr : crList) {
					if (cr.getRecordStatus() == 5){
						String userId = cr.getUserId();
						User u = (User) baseDao.loadObject("from User where userId = '"+cr.getUserId()+"' ");
						Set<Role> sr = u.getRoleId();
						for (Role role : sr) {
							if (role.getRoleId() == 1 || role.getRoleId() == 2){
								outFieldToDealNum ++;
							}
							if (role.getRoleId() == 3){
								sList.add(cr.getCustomerPhone());
							}
						}
					}
					map.put("outFieldToDealNum", outFieldToDealNum);
					map.put("outFieldToDealRate", outFieldToDealNum/crList.size());
					
				}
				for (String string : sList) {
					List<GuideRecords> grList2 = baseDao.findByHql("from GuideRecords where applyTime >= '"+startTimeStr+"' and applyTime <= '"+endTimeStr+ "' and projectId = '"+user.getParentId()+ "' and customerPhone = '"+string+"' ");
					if (grList2.size()>0){
						guideToDealNum ++;
					}
				}
				map.put("guideToDealNum", guideToDealNum);
			}else {
				map.put("outFieldToVisitRate", "暂无");
				map.put("guideToDealNum", "暂无");
				map.put("outFieldToVisitNum", "暂无");
				map.put("outFieldToVisitRate", "暂无");
			}
		}
		
		return map;
	}

	
	
	@Override
	public List<ManagerOwnAnalyse> findManagerOwnAnalyse(User user) {
	
		List<ManagerOwnAnalyse> moaList = baseDao.findByHql("from ManagerOwnAnalyse where managerId = '"+user.getUserId()+"' and ownAnalyseStatus = 0");
		
		return moaList;
	}

	
	@Override
	public void addManagerOwnAnalyse(User user,Integer categoryId) {
		if (categoryId != null && !"".equals(categoryId)){
			AnalysisCategory ac = (AnalysisCategory) baseDao.loadObject("from AnalysisCategory where categoryId = '"+categoryId+"' ");
			ManagerOwnAnalyse manageroa = (ManagerOwnAnalyse) baseDao.loadObject("from ManagerOwnAnalyse where managerId = '"+user.getUserId()+"' and categoryId = '"+categoryId+"' ");
			if (manageroa != null && !"".equals(manageroa)){
				manageroa.setOwnAnalyseStatus(0);
				manageroa.setAddTime(DateTime.toString1(new Date()));
				baseDao.saveOrUpdate(manageroa);
			}else {
				ManagerOwnAnalyse moa = new ManagerOwnAnalyse();
				moa.setManagerId(user.getUserId());
				moa.setManagerName(user.getUserCaption());
				moa.setCategoryId(categoryId);
				moa.setLabelName(ac.getCategoryName());
				moa.setAddTime(DateTime.toString1(new Date()));
				moa.setOwnAnalyseStatus(0);
				baseDao.saveOrUpdate(moa);
			}
		}
	}

	
	@Override
	public void updateManagerOwnAnalyse(User user, Integer categoryId) {
		if (categoryId != null && !"".equals(categoryId)){
			ManagerOwnAnalyse moa = (ManagerOwnAnalyse) baseDao.loadObject("from ManagerOwnAnalyse where managerId = '"+user.getUserId()+"' and categoryId = '"+categoryId+"' ");
			if (moa != null && !"".equals(moa)){
				moa.setDeleteTime(DateTime.toString1(new Date()));
				moa.setOwnAnalyseStatus(1);
				baseDao.saveOrUpdate(moa);
			}
		}
	}

	
	@Override
	public Map<String, Object> findReceptionByManager(String startTime, String endTime, User user)
			throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '"+ user.getParentId() + "' and arriveTime >= '"+startTimeStr + "' and arriveTime <= '"+endTimeStr + "' ");

		//有效接访量
		int effectiveNum = 0;
		//无效接访量
		int invalidNum = 0;

		map.put("title", "观察客户接待成效");
		map.put("name_1", "总接访数");
		map.put("name_2", "有效接访数");
		map.put("name_3", "无效接访数");
		map.put("name_4", "有效接访率");
		
		if (vrList.size()>0){
			//总接访量
			map.put("data_1", vrList.size());
			for (VisitRecords vr : vrList) {
				if (vr.getWriteState() == 1){
					effectiveNum ++;
				}else {
					invalidNum ++;
				}
			}
			map.put("data_2", effectiveNum);
			map.put("data_3", invalidNum);
			map.put("data_4", effectiveNum/vrList.size());
			
		}else {
			map.put("data_1", "暂无");
			map.put("data_2", "暂无");
			map.put("data_3", "暂无");
			map.put("data_4", "暂无");
			
		}
		return map;
	}

	
	@Override
	public Map<String, Object> findNewAndOldCustomerPassagewayInfo(String startTime, String endTime, User user)
			throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '"+ user.getParentId() + "' and arriveTime >= '"+startTimeStr + "' and arriveTime <= '"+endTimeStr + "' ");
		//新客户通道接访
		int newCustomerNum = 0;
		//老客户通道接访
		int oldCustomerNum = 0;
		//新客户通道有效接访
		int newCustomerEffectiveNum = 0;
		//老客户通道有效接访
		int oldCustomerEffectiveNum = 0;
		
		map.put("title", "观察新老客户通道");
		map.put("name_1", "新客户通道接访");
		map.put("name_2", "老客户通道接访");
		map.put("name_3", "新客户通道有效接访");
		map.put("name_4", "老客户通道有效接访");
		map.put("name_5", "新客户通道有效接访率");
		map.put("name_6", "老客户通道有效接访率");
		map.put("name_7", "老客户通道占比");
		if (vrList.size()>0){
			for (VisitRecords vr : vrList) {
				if (vr.getIsNew()){
					newCustomerNum ++;
					if (vr.getWriteState() == 1){
						newCustomerEffectiveNum ++;
					}
				}else {
					oldCustomerNum ++;
					if (vr.getWriteState() == 1){
						oldCustomerEffectiveNum ++;
					}
				}
			}
			map.put("data_1", newCustomerNum);
			map.put("data_2", oldCustomerNum);
			map.put("data_3", newCustomerEffectiveNum);
			map.put("data_4", oldCustomerEffectiveNum);
			if (newCustomerNum > 0){
				map.put("data_5", Integer.parseInt(SysContent.get2Double((double)(newCustomerEffectiveNum/newCustomerNum)*100))+"%");
			}else {
				map.put("data_5", "暂无");
			}
			if (oldCustomerNum > 0){
				map.put("data_6", Integer.parseInt(SysContent.get2Double((double)(oldCustomerEffectiveNum/oldCustomerNum)*100))+"%");
			}else {
				map.put("data_6", "暂无");
			}
			
			map.put("data_7", Integer.parseInt(SysContent.get2Double((double)(oldCustomerNum/vrList.size())*100))+"%");
			
		}else {
			map.put("data_1", "暂无");
			map.put("data_2", "暂无");
			map.put("data_3", "暂无");
			map.put("data_4", "暂无");
			map.put("data_5", "暂无");
			map.put("data_6", "暂无");
			map.put("data_7", "暂无");
		}
		
		return map;
	}

	
	@Override
	public Map<String, Object> findSeeAppointCustomerReceptionInfo(String startTime, String endTime, User user)
			throws ParseException {
		Map<String, Object> map = new HashMap<>();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(DateUtil.getIntegralStartTime(sdf.parse(DateTime.toString1(new Date()))));
		String endTimeStr = sdf.format(DateUtil.getIntegralEndTime(sdf.parse(DateTime.toString1(new Date()))));
		if (startTime != null && !"".equals(startTime)){
			startTimeStr = startTime; 
		}
		if (endTime != null && !"".equals(endTime)){
			endTimeStr = endTime;
		}
		List<VisitRecords> vrList = baseDao.findByHql("from VisitRecords where projectId = '"+ user.getParentId() + "' and arriveTime >= '"+startTimeStr + "' and arriveTime <= '"+endTimeStr + "' ");
		//总替接数
		int allReplaceNum = 0;
		//按序接访替接
		int orderReplaceNum = 0;
		
		map.put("title", "观察替接");
		map.put("name_1", "总替接数");
		map.put("name_2", "总替接率");
		
		if (vrList.size()>0){
			for (VisitRecords vr : vrList) {
				if (vr.getAppointUserId() != null && !"".equals(vr.getAppointUserId())){
					//指定
					if (!vr.getAppointUserId().equals(vr.getUserId())){
						//替接
						allReplaceNum ++;
					}
				}
			}
			map.put("data_1", allReplaceNum);
			if (allReplaceNum > 0){
				map.put("data_2", Integer.parseInt(SysContent.get2Double((double)(allReplaceNum/vrList.size())*100))+"%");
			}else {
				map.put("data_2", "暂无");
			}
		}else {
			map.put("data_1", "暂无");
			map.put("data_2", "暂无");
		}
		
		return map;
	}

	
}
