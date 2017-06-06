package com.sc.tradmaster.service.house;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.sc.tradmaster.bean.CountryProvinceInfo;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.house.impl.dto.HouseExportDTO;
import com.sc.tradmaster.service.house.impl.dto.SalesHouseDTO;
import com.sc.tradmaster.utils.Page;

public interface HouseService {
	/**
	 * 通过id删除用户(批量删除)
	 * @param id
	 */	
	void dropHouseByHouseStatus(House[] house);
	
	/**
	 * 搜索页(上下架管理搜索页)
	 * 
	 */	
	void findHouseByProperty4(User user, String[] selectNames, String[] selectValue,Page page);
	/**
	 * 一个案场下所有的房型(houseType)
	 */	
	TreeSet findAllHouseType(User user, Page page);
	/**
	 * 通过条件查询房源信息(案场助理房源管理)根据发布时间排序
	 * @param house
	 * @return
	 */	
	void findByProperty(User u,String[] propertyName, String[] propertyValue, Page page);
	/**
	 * 添加或更新房源信息
	 * @param house
	 * @return
	 */	
	void addOrUpdate(House house);
	/**
	 * 批量上架
	 * @param house
	 * @return
	 */	
	void updateUp(String[] houseNum);
	/**
	 * 批量下架
	 * @param house
	 * @return
	 */	
	void updateDown(String[] houseNum);
	/**
	 * 批量对经纪人可见
	 * @param house
	 * @return
	 */	
	void updateIsOpen(String[] houseNum);
	/**
	 * 批量对经纪人不可见
	 * @param house
	 * @return
	 */	
	void updateIsClose(String[] houseNum);
	/**
	 * 查看可售房源
	 * @param house
	 * @return
	 */	
	List findUp(String projectId);
	/**
	 * 根据房源id查找(一房一档)
	 * @param house
	 * @return
	 */	
	Map<String, Object> findById(Integer houseNum)throws ParseException;
	/**
	 * 置业顾问房源列表(按条件搜)
	 * @param houses
	 * @return
	 */	
	List findHouses(User user, String houseType,Double usefulArea, Integer floor)throws NumberFormatException, ParseException ;
	/**
	 * 置业顾问房源列表(所有)
	 * @param houses
	 * @return
	 */	
	List findAllHouses(User user);

	/**
	 *中介房源列表一个案场下房源(按条件搜)
	 * @param houses
	 * @return
	 */	
	List<SalesHouseDTO> findMidHouseByProperty(String projectId,String houseType, Double usefulArea, Integer floor)throws NumberFormatException, ParseException;
	
	/**
	 * 通过户型id获取房源信息
	 * 2017-01-21 grl add
	 * @param hNum
	 * @return 
	 */
	House findHouseByNum(Integer hNum);
	/**
	 * 装配户型动态菜单
	 * 2017-01-21 grl add
	 * @param parentId
	 * @return
	 */
	List<Map<String, String>> findHouseTypeByProId(String parentId);
	
	/**
	 * 微信分享页缩小
	 * @param houseNum
	 * @return
	 */	
	Map<String, Object> findOneHouseLittle(User user, Integer houseNum);
	
	/**
	 * 微信分享完全信息页
	 * @param house
	 * @return
	 */	
	Map<String, Object> findOneHouseById(User user,Integer houseNum)throws ParseException;
	
	/**
	 * 房型动态菜单 (web房型)
	 * @return
	 */
	TreeSet<String> findHouseCaptionByProIdWeb(String projectId);
	
	/**
	 * 房型动态菜单 (app房型)
	 * @return
	 */
	TreeSet<String> findHouseCaptionByProId(User user, String projectId);

	/**
	 * 面积动态菜单app
	 * @return
	 */
	TreeSet<Double> findHouseUsefulAreaByProId(User user, String projectId);
	
	/**
	 * 楼层动态菜单app
	 * @return
	 */
	TreeSet<Integer> findHouseFloorByProId(User user, String projectId);
	
	/**
	 *市区联动动态菜单
	 * @return 
	 */
	List<Map<String, String>> findCityAreaByShi(String shengOrShi);
	

	/**
	 * 根据省份的编号获取省的名称
	 * @param cityNo
	 * @return
	 */
	List<CountryProvinceInfo> findProvinceByCityNo(String cityNo);
	

	

	/**
	 *根据省市区id获得Name
	 * @return
	 */
	String findCityNameById(String shengOrShi);
	
	/**
	 * 2017-3-14 maoxy
	 *  加载所有省
	 * @return
	 */
	List<CountryProvinceInfo> findAllProvince();


	/**
	 * 
	 * 将Excel中的数据添加到数据库
	 * @param file
	 */
	String addHouseExcel(User user,MultipartFile file);

	
	/**
	 *精选页面默认的城市
	 */
	Map<String, String> findLocationCity(User user,String shengOrShi,String citySigle,CitySessionDTO  csdto);
	
	/**
	 *置业顾问project的区域获得
	 */
	String findAgentProjectCity(User user);
	
	
	/**
	 * 
	 * 通过当前登陆的店长的parentId去查找当前店所在的省市区。
	 * @param parentId
	 * @return
	 */
	Map<String,String> getCityByParentId(String parentId);

	/**
	 * 房源导出
	 * @param parentId
	 * @param selectedHIds 
	 * @return
	 */
	List<HouseExportDTO> findThisProjectHouse(String parentId, String[] selectedHIds);

}
