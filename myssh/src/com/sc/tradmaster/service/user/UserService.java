package com.sc.tradmaster.service.user;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.ManagerOwnAnalyse;
import com.sc.tradmaster.bean.Mydynamic;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.user.impl.dto.AllCheckedCustomerDTO;
import com.sc.tradmaster.service.user.impl.dto.GrDto;
import com.sc.tradmaster.service.user.impl.dto.RankingDto;
import com.sc.tradmaster.service.user.impl.dto.VisitRecordsDTO;


public interface UserService {
	/**
	 * 通过id查询用户信息
	 * @param id
	 * @return
	 */
	User findById(String id);
	
	/**
	 * 用户登录接口
	 * @param u
	 * @return
	 */
	User addUserTokenAndlogin(User u);
	
	/**
	 * 中介和置业顾问app个人资料
	 * @param u
	 */
	Map findUserInfo(User user);
	
	/**
	 * 中介和置业顾问app原来密码验证
	 * @param u
	 */
	void findOldPassword(User user, String password);
	
	/**
	 * 中介和置业顾问app密码修改
	 * @param u
	 */
	void updatePassowrd(User user, String firstPassword, String secondPassword);

	/**
	 * 添加获取更新user
	 * @return 
	 */
	Map addOrUpdateUser(User u ,User user,String rightSign);

	/**
	 * 账户管理 重置密码、启停用户、删除用户（修改状态）
	 * @param proCustomerId
	 * @param doSign
	 */
	void updateUserInfo(User user, String doSign);
	
	/**
	 * 中介经纪人我的页面上面的信息显示
	 */
	Map findMidInfo(User user);
	
	/**
	 * 中介经纪人我的页面业务统计
	 */
	Map findMidBusiness(User user)throws ParseException;
	
	/**
	 * 置业顾问我的页面上面的信息显示
	 */
	Map findSaleInfo(User user);
	
	/**
	 * 置业顾问我的页面业务统计
	 */
	Map findSaleBusiness(User user)throws ParseException;

	
	/**
	 * 陈冬华 2017-03-06
	 * 注册门店
	 * @param shop
	 * @throws IOException 
	 * @throws Exception 
	 */
	void addShop(Shops shop, MultipartFile photo, MultipartFile licensePhoto, String province, String market, String area) throws Exception;
	/**
	 * 添加或更新中介用户
	 * @param u
	 * @param user
	 * @param rightSign
	 */
	void addOrUpdateMediUser(User u, User user, String rightSign);

	/**
	 * 通过userToken
	 * @param userToken
	 * @return
	 */
	User findByUserToken(String userToken);
	
	/**
	 * 查找注册的手机号码是否已经注册了
	 * @param phoneNum
	 * @return
	 */
	boolean findExistPhoneNum(String phoneNum);

	/**
	 * 异常测试
	 */
	void getArray();
	
	/**
	 * 查找所有的用户
	 * @param  status代表筛选条件
	 * @return 用户所有的信息
	 */
	List<Map<String,Object>> findAllUser(String status);
	
	
	/**
	 * 更新用户信息包括角色的更换
	 * @param user
	 * @param doSign
	 */
	void updateSystemUser(User u,User user, String doSign, String role);
	/**
	 * 密码修改
	 * @param u
	 * @param oldPsw
	 * @param newPsw
	 * @return 
	 */
	Boolean addOrUpdateUserPassWorld(User u, String oldPsw, String newPsw);
	
	/**
	 * 中介经纪人我的动态
	 * @return
	 */
	List<Mydynamic> findMidMydynamic(User user);
	
	/**
	 * 中介经纪人我的动态改已读
	 * @return
	 */
	void updateMidMydynamic(User user,Integer dynamicId);
	
	/**
	 * 中介经纪人我的动态未读数量显示
	 * @return
	 */
	int toGetMydynamicNotReadNum(User user);
	
	/**
	 * 经理今日接访未登记
	 * @param user
	 * @return
	 */
	List<VisitRecordsDTO> findToadyVisitRecords(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 案场今日备案数量和备案为到访数量
	 * @param todayTime
	 * @param user
	 * @return
	 */
	Map<String, String> findTodayGuideRecords(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 经理_任务_带盘客
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Integer> findCheckCustomerNum(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 案场备案详情
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	List<GrDto> findGuideRecordsDetail(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 接访排名
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 */
	List<RankingDto> findVisitRanking(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 认购排名
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	List<RankingDto> findRengouRanking(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 签约排名
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	List<RankingDto> findContractRanking(String startTime,String endTime,User user)throws ParseException;

	/**
	 * 成交转换率排名
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	List<RankingDto> findSlewRateRanking(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 业务报表_接访_______
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findVisitStatement(String startTime,String endTime,User user)throws ParseException;
	

	/**
	 * 业务报表_成交________ 
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findDealStatement(String startTime,String endTime,User user)throws ParseException;
	
	
	/**
	 * 业务报表_外场________ 
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findOutFieldStatement(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 生成城市session
	 * @param user
	 * @return
	 */
	CitySessionDTO findCityIntoSession(User user);
	
	
	/**
	 * 生成城市session
	 * @param cityId
	 * @return
	 */
	CitySessionDTO findCityIntoSessionByCityId(String cityId,User user);
	
	/**
	 * 经理_任务_带盘客页面
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	List<AllCheckedCustomerDTO> findAllCheckedCustomer(String startTime,String endTime,User user)throws ParseException;
	
	/**
	 * 经理点评
	 * @param projectCustomerId
	 */
	void updateProjectCustomerByManager(String projectCustomerId,String evaluate,User user);
	
	
	/**
	 * 案场客户归属
	 * @param projectCustomerId
	 * @param user
	 * @return
	 */
	Map<String, String> findCustomerAffiliation(String projectCustomerId,User user);
	
	/**
	 * 经理收藏的标签___________
	 * @param user
	 * @return
	 */
	List<ManagerOwnAnalyse> findManagerOwnAnalyse(User user);
	
	/**
	 * 经理新增收藏标签
	 * @param user
	 */
	void addManagerOwnAnalyse(User user,Integer categoryId);
	
	/**
	 * 经理删除标签
	 * @param user
	 * @param categoryId
	 */
	void updateManagerOwnAnalyse(User user,Integer categoryId);
	
	
	/**
	 * 经理_分析_客户接待成效
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findReceptionByManager(String startTime,String endTime,User user)throws ParseException;

	/**
	 * 经理_分析_新老客户通道
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findNewAndOldCustomerPassagewayInfo(String startTime,String endTime,User user)throws ParseException;

	
	/**
	 * 经理_分析_观察指定接访
	 * @param startTime
	 * @param endTime
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> findSeeAppointCustomerReceptionInfo(String startTime,String endTime,User user)throws ParseException;

}
