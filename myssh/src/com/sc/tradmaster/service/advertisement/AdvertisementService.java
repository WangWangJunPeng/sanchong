package com.sc.tradmaster.service.advertisement;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.Advertisement;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
/**
 * 2017-02-06
 * @author grl 
 *
 */
public interface AdvertisementService {
	/**
	 * 通过当前用户所在门店的城市及类别，返回当前城市下的广告信息
	 * @return
	 */
	List getAdsByUserInfo(User user,String shi,String adPosition);
	
	/**
	 * 精选为你推荐首页
	 */
	List getAdsForYou(User user, String shi,CitySessionDTO  csdto);

	/**
	 * 精选本地精选首页
	 */
	List<Advertisement> getAdsLocation(User user, String shi);
	
	/**
	 * 精选其他推荐首页
	 */
	List<Advertisement> getAdsForOthers(User user, String shi);
	
	/**
	 * 广告也返回当前city
	 * @return
	 */
	String findCityLocation(User user);
	
	/**
	 * 2017-3-6 maoxy
	 * 添加广告
	 * @param ad
	 */
	void addAds(Advertisement ad,MultipartFile adPic);
	/**
	 * 2017-3-8 maoxy
	 * @param ad
	 * @return sorting 的最大值
	 */
	int findSorting(Advertisement ad);

	/**
	 * 广告修改
	 * @param u
	 * @param ad
	 * @throws Exception 
	 */
	void updateAdvertisement(User u, Advertisement ad,MultipartFile picFile) throws Exception;

}
