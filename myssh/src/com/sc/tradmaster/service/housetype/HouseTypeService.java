package com.sc.tradmaster.service.housetype;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.User;
/**
 * 
 * @author grl 2017-01-14
 *
 */
public interface HouseTypeService {
	/**
	 * 添加或更新户型信息
	 * @param ht
	 * @param u
	 * @param pic
	 * @throws Exception 
	 */
	void addOrupdateHouseType(HouseType ht,User u,MultipartFile pic) throws Exception;
	/**
	 * 通过id删除户型
	 * @param id
	 */
	void dropHouseType(String id);
	/**
	 * 通过id查询户型
	 * @param id
	 * @return
	 */
	HouseType findById(String id);
	/**
	 * 查询所有户型
	 * @param pId
	 * @return
	 */
	List<HouseType> findAllHouseType(String pId);
}
