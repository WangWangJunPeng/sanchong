package com.sc.tradmaster.service.favorites.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sc.tradmaster.bean.Advertisement;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.Favorites;
import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.ProjectBenefits;
import com.sc.tradmaster.bean.ProjectGuide;
import com.sc.tradmaster.bean.ProjectPics;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.favorites.FavoritesService;
import com.sc.tradmaster.service.project.impl.dto.ProjectDTO;
import com.sc.tradmaster.utils.DateTime;

@Service("favoritesService")
public class FavoritesServiceImpl implements FavoritesService{

	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	/**
	 * 增加收藏项目
	 */	
	@Override
	public void add(User user, String projectId) {
		String userId = user.getUserId();
		
		Date d = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String hql = "from Favorites where projectId = '"+ projectId+ "' and userId = '"+ user.getUserId()+"'";
	    Favorites favorites = (Favorites) baseDao.loadObject(hql);
	    if (favorites == null || "".equals(favorites)){
	    	Favorites f = new Favorites();
	    	f.setUserId(userId);
	    	f.setProjectId(projectId);
	    	f.setAddTime(sdf.format(d));
	    	baseDao.saveOrUpdate(f);
	    }
	}
	
	/**
	 * 收藏条数显示
	 */
	@Override
	public int findFavSize(User user) {
		String hql = " select count(*) from Favorites where userId = '" + user.getUserId() + "'";
		int count = baseDao.countQuery(hql);
		
		return count;
	}
	
	/**
	 * 所有收藏项目
	 */
	@Override
	public List findAllFav(User user) {
		String hql = "from Favorites where userId = '" + user.getUserId() + "'";
		List<Favorites> fList = baseDao.findByHql(hql);
		
		return fList;
	}
	
	
	/**
	 * 项目收藏默认页
	 */	
	@Override
	public List findFavProject(User user) {
		String nowTime = DateTime.toString1(new Date());
		String userId = user.getUserId();
		String fHQL = "from Favorites f where f.userId = '" + userId +"'";
		List<Favorites> fList = baseDao.findByHql(fHQL);
		List<ProjectDTO> pDTOList =  new ArrayList<>();
		for (Favorites f : fList) {
			String youhui = "";
			String pId = f.getProjectId();
			Project p = (Project) baseDao.loadById(Project.class, pId);
			if (p != null && !"".equals(p)){
				//查带看
				String pgHQL = "from ProjectGuide pg where pg.projectId = '" + pId + "'";
				ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
				//查优惠
				String wql = "from ProjectBenefits where projectId = '" + pId + "'"
						+ "	and startTime <= '"+nowTime +"' and endTime >= '" + nowTime + "'";
				List<ProjectBenefits> pbList = baseDao.findByHql(wql); 
				
				//new dto对象
				ProjectDTO pdto = new ProjectDTO();
				pdto.setProjectId(pId);
				pdto.setProjectName(p.getProjectName());
				String projectPhotoHQL = "from ProjectPics where projectId = '" +p.getProjectId()+"'";
				List<ProjectPics> ppList = baseDao.findByHql(projectPhotoHQL);
				List<String> photoList = new ArrayList<>();
				for (ProjectPics projectPics : ppList) {
					String pic = projectPics.getUrl();
					String picStr = pic.substring(1, pic.length()-1);
					String[] photoarr = picStr.split(",");
					for(int i =0;i<photoarr.length;i++){
						photoList.add(photoarr[i]);
					}
				}
				for (int i = 0; i < photoList.size(); i++) {
					if (i==0){
						String pictureStr = photoList.get(i);
						if (pictureStr != null && !"".equals(pictureStr)) {
							pdto.setAdPicture(pictureStr);
						}
					}
				}
			
				String city = p.getCity();
				String[] citystr = city.split("-");
				String cpHQL = "from CountryProvinceInfo where cityId = '"+ citystr[2]+"'";
				CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject(cpHQL);
				pdto.setCity(cp.getCityName());
				
				//可售房源数量
				String houseHQL = "from House where projectId = '"+p.getProjectId()+"' and houseStatus = 1 and isOpen = 1";
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
	//				int qianyueNum = 0;
	//				for (House house : hList) {
	//					String cnHQL = "from ContractRecords where houseNum = '"+house.getHouseNum()+"' and recordStatus = 5";
	//					ContractRecords cr = (ContractRecords) baseDao.loadObject(cnHQL);
	//					if (cr != null && !"".equals(cr)){
	//						qianyueNum ++;
	//					}
	//				}
					pdto.setUnitCount(hList.size());
				}else {
					pdto.setUnitCount(0);
				}
				pdto.setEffectPic(p.getEffectPic());
				//判断ProjectGuide是否存在
				if (pg!=null) {
					if (pg.getIsYiDi() == 1) {
						pdto.setIsYiDi("异");
					} else{
						pdto.setIsYiDi("");
					}
					if (pg.getIsFast() == 1) {
						pdto.setIsFast("快");
					} else{
						pdto.setIsFast("");
					}
					if (pg.getIsDaiKan() == 1) {
						pdto.setIsDaiKan("看");
					} else {
						pdto.setIsDaiKan("");
					}
					// 带看佣金比
					pdto.setDaiKanMoney(pg.getDaiKanMoney());
					// 分销佣金比
					pdto.setFenXiaoMoney(pg.getFenXiaoMoney());
					
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
				//判断优惠是否存在
				if(pbList != null && pbList.size()>0) {
					for (int i = 0; i < pbList.size(); i++) {
						if (pbList.get(i).getCaption() != null && !"".equals(pbList.get(i).getCaption())) {
							//第一条优惠信息
							youhui = pbList.get(i).getCaption();
							break;
						}
					}
					pdto.setYouhuiInfo(youhui);
				}
				if (pbList.size()>0){
					pdto.setYouhuiListSize(pbList.size());
				}else {
					pdto.setYouhuiListSize(0);
				}
				pDTOList.add(pdto);
			}
		}
		return pDTOList;
	}

	/**
	 * 删除收藏项目
	 */	
	@Override
	public void dropFav(User user, String allProjectId) {
		
		if (allProjectId != null && !"".equals(allProjectId)){
			String[] projectId = allProjectId.split(",");
			for (String string : projectId) {
				String favHQL = "from Favorites where userId = '" + user.getUserId() + "' and projectId = '" + string + "'";
				Favorites f = (Favorites) baseDao.loadObject(favHQL);
				baseDao.delete(f);
			}
		}
	}

	
	/**
	 * 发起带看
	 */
	@Override
	public List addFav(User user, String projectId) {
		String userId = user.getUserId();
		
		Date d = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String favHQL = "from Favorites where userId = '"+ user.getUserId() + "' and projectId = '"+ projectId+ "'";
	    Favorites favorites = (Favorites) baseDao.loadObject(favHQL);
	    if (favorites == null || "".equals(favorites)) {
	    	Favorites f = new Favorites();
	    	f.setUserId(userId);
	    	f.setProjectId(projectId);
	    	f.setAddTime(sdf.format(d));
	    	baseDao.saveOrUpdate(f);
	    }
	    String nowTime = DateTime.toString1(new Date());
		String fHQL = "from Favorites f where f.userId = '" + userId +"'";
		List<Favorites> fList = baseDao.findByHql(fHQL);
		List<ProjectDTO> pDTOList =  new ArrayList<>();
		for (Favorites f : fList) {
			String youhui = "";
			String pId = f.getProjectId();
			Project p = (Project) baseDao.loadById(Project.class, pId);
			if (p != null && !"".equals(p)){
				
				//查带看
				String pgHQL = "from ProjectGuide pg where pg.projectId = '" + pId + "'";
				ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
				//查优惠
				String wql = "from ProjectBenefits where projectId = '" + pId + "'"
						+ "	and startTime <= '"+nowTime +"' and endTime >= '" + nowTime + "'";
				List<ProjectBenefits> pbList = baseDao.findByHql(wql); 
				
				//new dto对象
				ProjectDTO pdto = new ProjectDTO();
				pdto.setProjectId(pId);
				pdto.setProjectName(p.getProjectName());
				String projectPhotoHQL = "from ProjectPics where projectId = '" +p.getProjectId()+"'";
				List<ProjectPics> ppList = baseDao.findByHql(projectPhotoHQL);
				List<String> photoList = new ArrayList<>();
				for (ProjectPics projectPics : ppList) {
					String pic = projectPics.getUrl();
					String picStr = pic.substring(1, pic.length()-1);
					String[] photoarr = picStr.split(",");
					for(int i =0;i<photoarr.length;i++){
						photoList.add(photoarr[i]);
					}
				}
				for (int i = 0; i < photoList.size(); i++) {
					if (i==0){
						String pictureStr = photoList.get(i);
						if (pictureStr != null && !"".equals(pictureStr)) {
							pdto.setAdPicture(pictureStr);
						}
					}
				}
				
				String city = p.getCity();
				String[] citystr = city.split("-");
				String cpHQL = "from CountryProvinceInfo where cityId = '"+ citystr[2]+"'";
				CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject(cpHQL);
				pdto.setCity(cp.getCityName());
				//可售房源数量
				String houseHQL = "from House where projectId = '"+p.getProjectId()+"' and houseStatus = 1 and isOpen = 1";
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
//				int qianyueNum = 0;
//				for (House house : hList) {
//					String cnHQL = "from ContractRecords where houseNum = '"+house.getHouseNum()+"' and recordStatus = 5";
//					ContractRecords cr = (ContractRecords) baseDao.loadObject(cnHQL);
//					if (cr != null && !"".equals(cr)){
//						qianyueNum ++;
//					}
//				}
					pdto.setUnitCount(hList.size());
				}else {
					pdto.setUnitCount(0);
				}
				pdto.setEffectPic(p.getEffectPic());
				//判断ProjectGuide是否存在
				if (pg!=null) {
					if (pg.getIsYiDi() == 1) {
						pdto.setIsYiDi("异");
					} else{
						pdto.setIsYiDi("");
					}
					if (pg.getIsFast() == 1) {
						pdto.setIsFast("快");
					} else{
						pdto.setIsFast("");
					}
					if (pg.getIsDaiKan() == 1) {
						pdto.setIsDaiKan("看");
					} else {
						pdto.setIsDaiKan("");
					}
					// 带看佣金比
					pdto.setDaiKanMoney(pg.getDaiKanMoney());
					// 分销佣金比
					pdto.setFenXiaoMoney(pg.getFenXiaoMoney());
					
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
				//判断优惠是否存在
				if(pbList != null && pbList.size()>0) {
					for (int i = 0; i < pbList.size(); i++) {
						if (pbList.get(i).getCaption() != null && !"".equals(pbList.get(i).getCaption())) {
							//第一条优惠信息
							youhui = pbList.get(i).getCaption();
							break;
						}
					}
					System.out.println(youhui);
					pdto.setYouhuiInfo(youhui);
				}
				if (pbList.size()>0){
					pdto.setYouhuiListSize(pbList.size());
				}else {
					pdto.setYouhuiListSize(0);
				}
				pDTOList.add(pdto);
			}
		}
		
		return pDTOList;
	}
	
}
