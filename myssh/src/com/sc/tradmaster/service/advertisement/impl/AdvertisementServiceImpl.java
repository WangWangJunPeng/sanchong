package com.sc.tradmaster.service.advertisement.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.Advertisement;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.advertisement.AdvertisementService;
import com.sc.tradmaster.utils.SysContent;
import com.sc.tradmaster.service.advertisement.impl.dto.AdvertisementDTO;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.project.impl.dto.ProjectDTO;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SmsContext;

/**
 * 2017-02-06
 * @author Administrator
 *
 */
@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {

	@Resource(name="baseDao")
	private BaseDao baseDao;

	@Override
	public List getAdsByUserInfo(User user,String shi,String adPosition) {
		//当前时间
		String nowTime = DateTime.toString1(new Date());
		if(user!=null && !"".equals(user)){
			String shopHQL = "from Shops where shopId = '" + user.getParentId() + "'";
			Shops s = (Shops) baseDao.loadObject(shopHQL);
			
			String city = s.getCity();
			String[] cityStr = city.split("-");
			String hql = "from Advertisement where adPosition = '"+ adPosition + "' and startTime <= '"+nowTime+"'"
					+ " and endTime >= '" + nowTime + "'";
			if (shi != null && !"".equals(shi)) {
				hql += " and adCity like '%" + shi + "%' order by sorting DESC";
			} else {
				hql += " and adCity like '%" + cityStr[1] + "%' order by sorting DESC";
			}
			List<Advertisement> adList = baseDao.findByHql(hql);
			List<ProjectDTO> pdtoList = new ArrayList<>();
			if (adList.size()>0) {
				for (Advertisement advertisement : adList) {
					String pHQL = "from Project p where p.projectId = '" + advertisement.getProjectId() + "'";
					Project p = (Project) baseDao.loadObject(pHQL);
					if (p!=null && !"".equals(p)){
						String pgHQL = "from ProjectGuide pg where pg.projectId = '"+ p.getProjectId()+ "'";
						ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
						ProjectDTO pdto = new ProjectDTO();
						pdto.setAdPicture(advertisement.getAdUrl());
						pdto.setProjectId(advertisement.getProjectId());
						pdto.setProjectName(p.getProjectName());
						if (pg != null && !"".equals(pg)) {
							if (pg.getIsFast()==1){
								pdto.setIsFast("快");
							}
							if (pg.getIsFast()==0){
								pdto.setIsFast("不支持快速结佣");
							}
							if (pg.getIsDaiKan()==1){
								pdto.setIsDaiKan("看");
							}
							if (pg.getIsDaiKan()==0){
								pdto.setIsDaiKan("不支持带看");
							}
							if (pg.getIsYiDi()==1){
								pdto.setIsYiDi("异");
							}
							if (pg.getIsYiDi()==0){
								pdto.setIsYiDi("不支持异地");
							}
							//多少星星显示
							Double daiKanMoney = pg.getDaiKanMoney();
							if (daiKanMoney <= 0.5){
								pdto.setStars(1.0);
							}else if (daiKanMoney > 0.5 && daiKanMoney <= 0.6) {
								pdto.setStars(1.5);
							} else if (daiKanMoney > 0.6 && daiKanMoney <= 0.7) {
								pdto.setStars(2.0);
							} else if (daiKanMoney > 0.7 && daiKanMoney <= 0.8) {
								pdto.setStars(2.5);
							} else if (daiKanMoney > 0.8 && daiKanMoney <= 0.9) {
								pdto.setStars(3.0);
							} else if (daiKanMoney > 0.9 && daiKanMoney <= 1.0) {
								pdto.setStars(3.5);
							}else if (daiKanMoney > 1.0 && daiKanMoney <= 1.1) {
								pdto.setStars(4.0);
							}else if (daiKanMoney > 1.1 && daiKanMoney <= 1.2) {
								pdto.setStars(4.5);
							}else if (daiKanMoney > 1.2 ) {
								pdto.setStars(5.0);
							}
						}
						//查案场下可售房源
						String houseHQL = "from House where projectId = '"+p.getProjectId()+"' and houseStatus = '1' ";//and isOpen = 1";
						Set<Role> rs = user.getRoleId();
						if (user.getRoleId()!=null && !"".equals(user.getRoleId())){
							for (Role role : rs) {
								if (role.getRoleId() == 1 || role.getRoleId() == 2){
									houseHQL += " and isOpen = '1'";
								}
							}
						}
						List<House> hList = baseDao.findByHql(houseHQL);
						/*List<House> hList2 = new ArrayList<>();
						//房子已经被认购同意的不让其他人发起认购
						for (House house : hList) {
							String crHQL = "from ContractRecords where houseNum = '"+house.getHouseNum()+"' "
									+ " and recordStatus = 1";
							ContractRecords cr = (ContractRecords) baseDao.loadObject(crHQL);
							if (cr == null || "".equals(cr)){
								hList2.add(house);
							}
						}*/
						if (hList.size()>0){
							pdto.setUnitCount(hList.size());
						}else {
							pdto.setUnitCount(0);
						}
						pdto.setAveragePrice(p.getAveragePrice());
						
						pdtoList.add(pdto);
					}
				}
				return pdtoList;
			}
		}
		return null;
	}

	@Override
	public void addAds(Advertisement ad,MultipartFile adPic) {
		if(adPic!=null){
			String name = adPic.getOriginalFilename();
			//判断是否为图片
	        if(name.matches("(?i).+?\\.(jpg|gif|bmp|png|jpeg)")){
	        	try {
		        	String rename = SysContent.getFileRename(name);
		        	//将图片存入云
		        	String upload = PicUploadToYun.upload(rename, adPic,SmsContext.AD_PIC);
		        	ad.setAdUrl(upload);
		        	baseDao.saveOrUpdate(ad);
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        		throw new RuntimeException();
	        	}
	        }
		}
	}

	@Override
	public int findSorting(Advertisement ad) {
		List<Advertisement> ads = baseDao.findByHql("from Advertisement where adCity = '"+ad.getAdCity() +"' and adPosition = '"+ad.getAdPosition()+"'");
		int maxSorting = 0;
		if (ads!=null) {
			for (Advertisement adver : ads) {
				int sorting = adver.getSorting();
				if (sorting > maxSorting) {
					maxSorting = sorting;
				}
			} 
			
		}
		return maxSorting;
	}


	/**
	 * 精选为你推荐首页
	 */
	@Override
	public List getAdsForYou(User user, String shi,CitySessionDTO  csdto) {
		//当前时间
		String nowTime = DateTime.toString1(new Date());
		if (user != null) {
			String shopHQL = "from Shops where shopId = '" + user.getParentId() + "'";
			Shops s = (Shops) baseDao.loadObject(shopHQL);
			String city = s.getCity();
			String[] cityStr = city.split("-");
			
			String hql = "from Advertisement where  startTime <= '"+nowTime+"'"
					+ " and endTime >= '" + nowTime + "'";
			if (csdto != null && !"".equals(csdto)){
				hql += " and adCity like '%" + csdto.getCityId() + "%' order by sorting DESC";
			}else {
				if (shi != null && !"".equals(shi)) {
					hql += " and adCity like '%" + shi + "%' order by sorting DESC";
				} else {
					hql += " and adCity like '%" + cityStr[1] + "%' order by sorting DESC";
				}
			}
			List<Advertisement> adList = baseDao.findByHql(hql);
			List<AdvertisementDTO> adtoList = new ArrayList<>();
			for (Advertisement advertisement : adList) {
				String pHQL = "from Project p where p.projectId = '" + advertisement.getProjectId() + "'";
				Project p = (Project) baseDao.loadObject(pHQL);
				AdvertisementDTO adto = new AdvertisementDTO();
				AdvertisementDTO advertisementDTO = adto.creatAdvertisementDTO(advertisement, p);
				
				adtoList.add(advertisementDTO);
			}
			return adtoList;
		}
		return null;
	}

	/**
	 * 精选本地精选首页
	 */
	@Override
	public List<Advertisement> getAdsLocation(User user, String shi) {
		if (user != null) {
			String shopHQL = "from Shops where shopId = '" + user.getParentId() + "'";
			Shops s = (Shops) baseDao.loadObject(shopHQL);
			String city = s.getCity();
			String[] cityStr = city.split("-");
			if (shi != null && !"".equals(shi)) {
				String hql = "from Advertisement where adCity like '%" + shi + "%' and adPosition = '" + "本地推荐" + "'";
				List<Advertisement> adList = baseDao.findByHql(hql);
				return adList;
			} else {
				String hql = "from Advertisement where adCity like '%" + cityStr[1] + "%' and adPosition = '" + "本地推荐" + "'";
				List<Advertisement> adList = baseDao.findByHql(hql);
				return adList;
			}
			
		}
		return null;
	}

	/**
	 * 精选其他推荐首页
	 */
	@Override
	public List<Advertisement> getAdsForOthers(User user, String shi) {
		if (user != null) {
			String shopHQL = "from Shops where shopId = '" + user.getParentId() + "'";
			Shops s = (Shops) baseDao.loadObject(shopHQL);
			String city = s.getCity();
			String[] cityStr = city.split("-");
			if (shi != null && !"".equals(shi)) {
				String hql = "from Advertisement where adCity like '%" + shi + "%' and adPosition = '" + "其他推荐" + "'";
				List<Advertisement> adList = baseDao.findByHql(hql);
				return adList;
			} else {
				String hql = "from Advertisement where adCity like '%" + cityStr[1] + "%' and adPosition = '" + "其他推荐" + "'";
				List<Advertisement> adList = baseDao.findByHql(hql);
				return adList;
			}
		}
		return null;
	}

	/**
	 * 广告页返回当前city
	 * @return
	 */
	@Override
	public String findCityLocation(User user) {
		if (user != null) {
			String shopHQL = "from Shops where shopId = '" + user.getParentId() + "'";
			Shops s = (Shops) baseDao.loadObject(shopHQL);
			
			String city = s.getCity();
			String[] cityStr = city.split("-");
			String shi = cityStr[1];
		
			return shi;
		}
		return null;
	}

	/**
	 * 广告修改
	 * @throws Exception 
	 */
	@Override
	public void updateAdvertisement(User u, Advertisement ad,MultipartFile picFile) throws Exception {
		if(u!=null){
			//Advertisement hadAd = (Advertisement) baseDao.loadById(Advertisement.class,ad.getAdId());
			if(!picFile.isEmpty()){
				String rename = SysContent.getFileRename(picFile.getOriginalFilename());
	        	//将图片存入云
	        	String upload = PicUploadToYun.upload(rename, picFile,SmsContext.AD_PIC);
	        	ad.setAdUrl(upload);
			}
			baseDao.saveOrUpdate(ad);
		}
		
	}
	
	
}
