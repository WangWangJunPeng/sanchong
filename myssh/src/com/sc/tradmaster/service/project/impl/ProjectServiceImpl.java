package com.sc.tradmaster.service.project.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.Advertisement;
import com.sc.tradmaster.bean.Confess;
import com.sc.tradmaster.bean.ContractRecords;
import com.sc.tradmaster.bean.CountryProvinceInfo;
import com.sc.tradmaster.bean.EnterBuy;
import com.sc.tradmaster.bean.Favorites;
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
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.SystemChargeDefinition;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.controller.common.OrderWillExpiringSmsQuartz;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.advertisement.impl.dto.CitySessionDTO;
import com.sc.tradmaster.service.dynamic.DynamicUtil;
import com.sc.tradmaster.service.project.ProjectService;
import com.sc.tradmaster.service.project.impl.dto.ContractRecordsBillDTO;
import com.sc.tradmaster.service.project.impl.dto.MediAboutDataDTO;
import com.sc.tradmaster.service.project.impl.dto.MyBusinessDTO;
import com.sc.tradmaster.service.project.impl.dto.ProCustomerDTO;
import com.sc.tradmaster.service.project.impl.dto.ProjectDTO;
import com.sc.tradmaster.service.project.impl.dto.SeeBuyApplyDTO;
import com.sc.tradmaster.service.project.impl.dto.ShopAndUserNameDTO;
import com.sc.tradmaster.service.project.impl.dto.TodayNeedToDoDTO;
import com.sc.tradmaster.service.project.impl.dto.UserDTO;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.utils.DateTime;
import com.sc.tradmaster.utils.DateUtil;
import com.sc.tradmaster.utils.JavaSmsApi;
import com.sc.tradmaster.utils.Page;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

/**
 * 2017-01-08
 * 
 * @author grl
 *
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	@Resource(name="tagService")
	private TagService tagService;

	@Resource(name = "baseDao")
	private BaseDao baseDao;

	@Override
	public void addOrUpdateProject(Project p, User u) {
		if (p.getProjectId() == null || p.getProjectId() == "") {
			String pId = SysContent.uuid();
			p.setProjectId(pId);
			User user = (User) baseDao.loadById(User.class, u.getUserId());
			user.setParentId(pId);
			baseDao.saveOrUpdate(user);
		}
		baseDao.saveOrUpdate(p);
	}

	@Override
	public void dropProjectById(String id) {

	}

	@Override
	public Project findProjectById(String id) {
		Project pro = (Project) baseDao.loadById(Project.class, id);
		pro.setStrVolumeRatio(SysContent.get2Double(pro.getVolumeRatio()));
		pro.setStrDensity(SysContent.get2Double(pro.getDensity()));
		pro.setStrAfforestationRatio(SysContent.get2Double(pro.getAfforestationRatio()));
		pro.setStrPropertyCost(SysContent.get2Double(pro.getPropertyCost()));
		pro.setStrAveragePrice(SysContent.get2Double(pro.getAveragePrice()));
		return pro;
	}


	/**
	 * 中介端房源首页按条件搜索项目
	 * @throws IOException 
	 */
	@Override
	public List findProjectByConditions(User user, String[] selectNames, String[] selectValues,CitySessionDTO  csdto) throws IOException {
		// 根据输入的项目名和市区 模糊查询项目
		String pHQL = "from Project where ";

		if (selectValues[0] != null && !"".equals(selectValues[0])) {
			pHQL += " projectName like '%" + selectValues[0] + "%' and";
		}
		if (csdto != null && !"".equals(csdto)){
			pHQL += " city like '%" + csdto.getCityId() + "%' ";
		}else {
			if (selectValues[1] != null && !"".equals(selectValues[1])) {
				pHQL += " city like '%" + selectValues[1] + "%' ";
			}
		}
		List<Project> pList = baseDao.findByHql(pHQL);
		// 获取更多里面的
		// 获取面积区间
		String useAreas = selectValues[3];
		// String[] useAreaStr = useAreas.split("-");
		// 定义一个满足条件的projectList
		List<Project> pList2 = new ArrayList<>();

		// 判断面积区间
		if (useAreas != null && !"".equals(useAreas)) {
			String[] useAreaStr = useAreas.split("-");
			if (useAreaStr.length == 2) {
				double smallArea = Double.parseDouble(useAreaStr[0]);
				double bigeArea = Double.parseDouble(useAreaStr[1]);
				Project p = null;
				for (Project project : pList) {
					p = project;
					// 将project赋值给p
					String pgHQL = "from ProjectGuide pg where pg.projectId = '" + p.getProjectId() + "'";

					if (selectValues[5] != null && !"".equals(selectValues[5])) {
						// 选了支持带看的,
						if (selectValues[5].equals("支持带看")) {
							pgHQL += " and pg.isDaiKan = 1";
						}
						// 选了支持快速结佣的
						if (selectValues[5].equals("快速结佣")) {
							pgHQL += " and pg.isFast = 1";
						}
						// 选了支持异地的
						if (selectValues[5].equals("异地备案")) {
							pgHQL += " and pg.isYiDi = 1";
						}
					}
					ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
					TreeSet<String> set = new TreeSet<>();
					if (pg != null && !"".equals(pg)) {
						String proHQL = "from Project where projectId = '" + pg.getProjectId() + "'";
						Project project2 = (Project) baseDao.loadObject(proHQL);
						p = project2;
					} else {
						continue;
					}
					// 查面积是否选择
					String hHQL = "from House h where h.projectId = '" + p.getProjectId() + "' and h.houseStatus = 1 and h.isOpen = 1";
					if (selectValues[3] != null && !selectValues[3].equals("")) {
						hHQL += " and h.usefulArea >= '" + smallArea + "' and h.usefulArea < '" + bigeArea + "'";
					}
					// 查出根据面积条件筛选的house对象
					List<House> hList = baseDao.findByHql(hHQL);
					for (House house : hList) {
						// 模糊查询户型
						String htHQL = "from HouseType ht where ht.houseTypeId = '" + house.getHouseTypeId() + "' and projectId = '"+p.getProjectId()+"' ";
						if (selectValues[2] != null && !selectValues[2].equals("")) {
							htHQL += " and ht.caption like '%" + selectValues[2] + "%'";
						}
						HouseType ht = (HouseType) baseDao.loadObject(htHQL);
						if (ht != null && !"".equals(ht)) {
							if (ht.getProjectId() != null && !"".equals(ht.getProjectId())) {
								set.add(ht.getProjectId());
							}
						}
					}
					// 所有条件走一遍 之后的 projectId
					for (String string : set) {
						Project p2 = (Project) baseDao.loadById(Project.class, string);
						pList2.add(p2);
					}

				}
			} else {
				double useArea = Double.parseDouble(useAreaStr[0]);
				Project p = null;
				for (Project project : pList) {
					p = project;
					// 将project赋值给p
					String pgHQL = "from ProjectGuide pg where pg.projectId = '" + p.getProjectId() + "'";
					if (selectValues[5] != null && !"".equals(selectValues[5])) {
						// 选了支持带看的,
						if (selectValues[5].equals("支持带看")) {
							pgHQL += " and pg.isDaiKan = 1";
						}
						// 选了支持快速结佣的
						if (selectValues[5].equals("快速结佣")) {
							pgHQL += " and pg.isFast = 1";
						}
						// 选了支持异地的
						if (selectValues[5].equals("异地备案")) {
							pgHQL += " and pg.isYiDi = 1";
						}
					}
					ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
					TreeSet<String> set = new TreeSet<>();
					if (pg != null && !"".equals(pg)) {
						String proHQL = "from Project where projectId = '" + pg.getProjectId() + "'";
						Project project2 = (Project) baseDao.loadObject(proHQL);
						p = project2;
					} else {
						continue;
					}
					// 查面积是否选择
					String hHQL = "from House h where h.projectId = '" + p.getProjectId() + "' and h.houseStatus = 1 and h.isOpen = 1";
					if (selectValues[3] != null && !selectValues[3].equals("")) {
						hHQL += " and (h.usefulArea >= '" + useArea + "' or h.usefulArea < '" + useArea + "')";
					}
					// 查出根据面积条件筛选的house对象
					List<House> hList = baseDao.findByHql(hHQL);
					
					for (House house : hList) {
						// 模糊查询户型
						String htHQL = "from HouseType ht where ht.houseTypeId = '" + house.getHouseTypeId() + "' and projectId = '"+p.getProjectId()+"' ";
						if (selectValues[2] != null && !selectValues[2].equals("")) {
							htHQL += " and ht.housType like '%" + selectValues[2] + "%'";
						}
						HouseType ht = (HouseType) baseDao.loadObject(htHQL);
						if (ht != null && !"".equals(ht)) {
							if (ht.getProjectId() != null && !"".equals(ht.getProjectId())) {
								set.add(ht.getProjectId());
							}
						}
					}
					// 所有条件走一遍 之后的 projectId
					for (String string : set) {
						Project p2 = (Project) baseDao.loadById(Project.class, string);
						pList2.add(p2);
					}
				}
			}
		} else {
			Project p = null;
			if (pList.size() > 0) {
				for (Project project : pList) {
					p = project;
					// 将project赋值给p
					String pgHQL = "from ProjectGuide pg where pg.projectId = '" + p.getProjectId() + "'";
					if (selectValues[5] != null && !"".equals(selectValues[5])) {
						// 选了支持带看的,
						if (selectValues[5].equals("支持带看")) {
							pgHQL += " and pg.isDaiKan = 1";
						}
						// 选了支持快速结佣的
						if (selectValues[5].equals("快速结佣")) {
							pgHQL += " and pg.isFast = 1";
						}
						// 选了支持异地的
						if (selectValues[5].equals("异地备案")) {
							pgHQL += " and pg.isYiDi = 1";
						}
					}
					ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
					TreeSet<String> set = new TreeSet<>();
					if (pg != null && !"".equals(pg)) {
						String proHQL = "from Project where projectId = '" + pg.getProjectId() + "'";
						Project project2 = (Project) baseDao.loadObject(proHQL);
						p = project2;
					} else {
						continue;
					}
					// 查面积是否选择
					String hHQL = "from House h where h.projectId = '" + p.getProjectId() + "' and h.houseStatus = 1 and h.isOpen = 1";
					// 查出根据面积条件筛选的house对象
					List<House> hList = baseDao.findByHql(hHQL);
					
					for (House house : hList) {
						// 模糊查询户型
						String htHQL = "from HouseType ht where ht.houseTypeId = '" + house.getHouseTypeId() + "' and projectId = '"+p.getProjectId()+"' ";
						if (selectValues[2] != null && !selectValues[2].equals("")) {
							htHQL += " and ht.housType like '%" + selectValues[2] + "%'";
						}
						HouseType ht = (HouseType) baseDao.loadObject(htHQL);
						if (ht != null && !"".equals(ht)) {
							set.add(ht.getProjectId());
						}
					}
					if (selectValues[2] == null || "".equals(selectValues[2])) {
						set.add(p.getProjectId());
					}
					// 所有条件走一遍 之后的 projectId
					for (String string : set) {
						Project p2 = (Project) baseDao.loadById(Project.class, string);
						pList2.add(p2);
					}
				}
			}
		}
		TreeSet<String> pts = new TreeSet<>();
		for (Project project : pList2) {
			pts.add(project.getProjectId());
		}
		
		List<Project> pList3 = new ArrayList<>();
		for (String string : pts) {
			String ProHQL = "from Project where projectId = '"+ string+"'";
			Project projectOnly = (Project) baseDao.loadObject(ProHQL);
			pList3.add(projectOnly);
		}
		
		List<ProjectDTO> pdtoList = new ArrayList<>();
		// 遍历满足条件的project,根据推荐排序
		for (Project p : pList3) {
			// new dto对象
			ProjectDTO pdto = new ProjectDTO();
			String youhui = "";
			String pId = p.getProjectId();
			// 查带看
			String pgHQL = "from ProjectGuide pg where pg.projectId = '" + pId + "'";
			ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
			String nowTime = DateTime.toString1(new Date());
			// 查优惠
			String wql = "from ProjectBenefits pb where pb.projectId  = '" + pId + "' and pb.startTime <= '"+nowTime+"' and pb.endTime >= '"+nowTime+"'";
			List<ProjectBenefits> pbList = baseDao.findByHql(wql);
			// 查收藏表里是否有这个项目
			String favHQL = "from Favorites where projectId = '" + p.getProjectId() + "' " + " and userId = '"
					+ user.getUserId() + "'";
			Favorites favorites = (Favorites) baseDao.loadObject(favHQL);
			if (favorites != null && !"".equals(favorites)) {
				pdto.setIsCollection("已收藏");
			} else {
				pdto.setIsCollection("未收藏");
			}
			
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
//						System.out.println("dsdasdas---------------"+ pictureStr);
////						效果图缩略图............
//						BufferedImage originalImage = ImageIO.read(new File("D:\\workspace\\uppro\\myssh\\WebContent\\static\\images\\1.png"));
//						System.out.println("d---------------"+ originalImage);
//						BufferedImage thumbnail = Thumbnails.of(originalImage).scale(0.25f).asBufferedImage();
//						System.out.println("d2312312---------------"+ thumbnail);
						//pdto.setThumbnail(thumbnail.toString());
//						Builder<File> file = Thumbnails.of(pictureStr).scale(0.5f);
						
						
						
//						Thumbnails.Builder<File> builder = Thumbnails.of(pictureStr).scale(1f).outputFormat("jpg"); 
//						System.out.println("================"+ builder.toString());
//						BufferedImage asBufferedImage = file.asBufferedImage();
//						ImageIO.write(asBufferedImage, "asd.jsp", new FileOutputStream(new File("./cs")));
						
						//BASE64Encoder encoder = new BASE64Encoder();  
						/* InputStream in = null;  
					        byte[] data = null;  
					        // 读取图片字节数组  
					        try {  
					            in = new FileInputStream("root-1252627680.cossh.myqcloud.com/pictures/20170405173438945.jpg");  
					            data = new byte[in.available()];  
					            in.read(data);  
					            in.close();  
					        } catch (IOException e) {  
					            e.printStackTrace();  
					        }  
					        // 对字节数组Base64编码  
					        BASE64Encoder encoder = new BASE64Encoder();  
					        // 返回Base64编码过的字节数组字符串  
					        System.out.println("-----------------" + encoder.encode(data));*/
//						pdto.setThumbnail(encoder.encode(data));
					}
				}
			}
			
			pdto.setProjectId(pId);
			pdto.setProjectName(p.getProjectName());
			String city = p.getCity();
			String[] cityStr = city.split("-");
			String cpHQL = "from CountryProvinceInfo where cityId = '" + cityStr[2] + "'";
			CountryProvinceInfo cp = (CountryProvinceInfo) baseDao.loadObject(cpHQL);
			pdto.setCity(cp.getCityName());
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
				pdto.setUnitCount(hList.size());
			}else {
				pdto.setUnitCount(0);
			}
			pdto.setEffectPic(p.getEffectPic());
			// 判断ProjectGuide是否存在
			if (pg != null) {
				// 是否支持异地
				if (pg.getIsYiDi() == 1) {
					pdto.setIsYiDi("异");
				} else {
					pdto.setIsYiDi("");
				}
				// 是否支持快速结佣
				if (pg.getIsFast() == 1) {
					pdto.setIsFast("快");
				} else {
					pdto.setIsFast("");
				}
				// 是否支持带看
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
			// 判断优惠是否存在
			if (pbList != null && pbList.size() > 0) {
				for (int i = 0; i < pbList.size(); i++) {
					if (pbList.get(i).getCaption() != null && !"".equals(pbList.get(i).getCaption())) {
						youhui = pbList.get(i).getCaption();
						break;
					}
				}
				pdto.setYouhuiInfo(youhui);
			}
			if (pbList.size() > 0) {
				pdto.setYouhuiListSize(pbList.size());
			} else {
				pdto.setYouhuiListSize(0);
			}
			pdtoList.add(pdto);
		}
		if (selectValues[4] != null && !"".equals(selectValues[4])) {
			// 带看比从高到低
			if ("daikanDown".equals(selectValues[4])) {
				for (int i = 0; i < pdtoList.size(); i++) {
					for (int j = 0; j < pdtoList.size() - 1 - i; j++) {
						if (pdtoList.get(j).getDaiKanMoney() != null && !"".equals(pdtoList.get(j).getDaiKanMoney())) {
							if (pdtoList.get(j).getDaiKanMoney() < pdtoList.get(j + 1).getDaiKanMoney()) {
								ProjectDTO r = pdtoList.get(j);
								pdtoList.set(j, pdtoList.get(j + 1));
								pdtoList.set(j + 1, r);
							}
						}
					}
				}
			}
			// 带看比从低到高
			if ("daikanUp".equals(selectValues[4])) {
				for (int i = 0; i < pdtoList.size(); i++) {
					for (int j = 0; j < pdtoList.size() - 1 - i; j++) {
						if (pdtoList.get(j).getDaiKanMoney() != null && !"".equals(pdtoList.get(j).getDaiKanMoney())) {
							if (pdtoList.get(j).getDaiKanMoney() > pdtoList.get(j + 1).getDaiKanMoney()) {
								ProjectDTO r = pdtoList.get(j);
								pdtoList.set(j, pdtoList.get(j + 1));
								pdtoList.set(j + 1, r);
							}
						}
					}
				}
			}
			// 分销比从高到低
			if ("fenxiaoDown".equals(selectValues[4])) {
				for (int i = 0; i < pdtoList.size(); i++) {
					for (int j = 0; j < pdtoList.size() - 1 - i; j++) {
						if (pdtoList.get(j).getFenXiaoMoney() != null && !"".equals(pdtoList.get(j).getFenXiaoMoney())){
							if (pdtoList.get(j).getFenXiaoMoney() < pdtoList.get(j + 1).getFenXiaoMoney()) {
								ProjectDTO r = pdtoList.get(j);
								pdtoList.set(j, pdtoList.get(j + 1));
								pdtoList.set(j + 1, r);
							}
						}
					}
				}
			}
			// 分销比从低到高
			if ("fenxiaoUp".equals(selectValues[4])) {
				for (int i = 0; i < pdtoList.size(); i++) {
					for (int j = 0; j < pdtoList.size() - 1 - i; j++) {
						if (pdtoList.get(j).getFenXiaoMoney() != null && !"".equals(pdtoList.get(j).getFenXiaoMoney())){
							if (pdtoList.get(j).getFenXiaoMoney() > pdtoList.get(j + 1).getFenXiaoMoney()) {
								ProjectDTO r = pdtoList.get(j);
								pdtoList.set(j, pdtoList.get(j + 1));
								pdtoList.set(j + 1, r);
							}
						}
					}
				}
			}
		}
		return pdtoList;
	}

	@Override
	public void addIdManageInfo(User u, String idManageNum, MultipartFile pic) throws Exception {
		if (u.getParentId() != null && !u.getParentId().equals("")) {
			// 获取持久化项目对象
			Project pro = (Project) baseDao.loadById(Project.class, u.getParentId());
			// 上传图片
			if (!pic.isEmpty() && pic.getSize() > 0) {
				String urlStr = pro.getPresalePermissionURL();
				// 把urlStr字符串转换成listMap数据结构
				List<HashMap> listMap = new ArrayList<>();
				if (urlStr != null && !urlStr.equals("")) {
					listMap = JSON.parseArray(urlStr, HashMap.class);
				}
				// 存储预售证编号，预售证图片路径
				Map<String, String> picMap = new HashMap<String, String>();
				// 获取上传路径
				/*String sepa = File.separator;
				String realPath = SysContent.getSession().getServletContext().getRealPath("/static/upload");
				String rename = SysContent.getFileRename(pic.getOriginalFilename());
				String rpn = realPath + sepa + rename;
				String savePath = rpn.substring(rpn.indexOf("static"));
				String reSavePath = savePath.replace(sepa, "/");
				File file = new File(rpn);
				FileUtils.copyInputStreamToFile(pic.getInputStream(), file);*/
				String rename = SysContent.getFileRename(pic.getOriginalFilename());
				String reSavePath = PicUploadToYun.upload(rename, pic,SmsContext.WSC_PIC);
				// 将预售证信息封装成{'num':'num','path':'path'},...
				picMap.put("imNum", idManageNum);
				picMap.put("path", reSavePath);
				// 把数据添加到集合中
				listMap.add((HashMap) picMap);
				// 把每个map数据结构数据转成标准json格式，存入listStr集合中
				List<String> listStr = new ArrayList<>();
				for (Map m : listMap) {
					JSONObject jsonObject = JSONObject.fromObject(m);
					listStr.add(jsonObject.toString());
				}
				pro.setPresalePermissionURL(listStr.toString());
			}
			// 更新项目对象，保存预售证信息
			baseDao.saveOrUpdate(pro);
		}
	}

	@Override
	public void dropIdManageByProPicIndex(String pId, int index) {
		if (pId != null && !pId.equals("")) {
			// 获取项目对象信息
			Project pro = (Project) baseDao.loadById(Project.class, pId);
			// 获取预售证字符串
			String urlStr = pro.getPresalePermissionURL();
			// 将字符串转化为listMap数据结构
			List<HashMap> listMap = JSON.parseArray(urlStr, HashMap.class);
			// 移除listMap中index位置的数据
			listMap.remove(index);
			// 便利listMap集合，将其转化为标准的json格式
			List<String> listStr = new ArrayList<>();
			if (listMap != null && listMap.size() > 0) {
				for (Map m : listMap) {
					JSONObject jsonObject = JSONObject.fromObject(m);
					listStr.add(jsonObject.toString());
				}
			}
			// 更新预售证数据字段
			pro.setPresalePermissionURL(listStr.toString());
			// 数据持久化
			baseDao.saveOrUpdate(pro);
		}
	}

	@Override
	public void updateIdManageInfo(User u, String idManageNum, MultipartFile pic, int index) throws Exception {
		// 获取持久化项目对象
		Project pro = (Project) baseDao.loadById(Project.class, u.getParentId());
		// 获取预售证信息
		String urlStr = pro.getPresalePermissionURL();
		List<HashMap> listMap = JSON.parseArray(urlStr, HashMap.class);
		HashMap urlMap = listMap.get(index);
		urlMap.put("imNum", idManageNum);
		if (!pic.isEmpty() && pic.getSize() > 0) {
			// 获取上传路径
			/*String sepa = File.separator;
			String realPath = SysContent.getSession().getServletContext().getRealPath("/static/upload");
			String rename = SysContent.getFileRename(pic.getOriginalFilename());
			String rpn = realPath + sepa + rename;
			String savePath = rpn.substring(rpn.indexOf("static"));
			String reSavePath = savePath.replace(sepa, "/");
			File file = new File(rpn);
			FileUtils.copyInputStreamToFile(pic.getInputStream(), file);*/
			String rename = SysContent.getFileRename(pic.getOriginalFilename());
			String reSavePath = PicUploadToYun.upload(rename, pic,SmsContext.WSC_PIC);
			// 将预售证信息封装成{'num':'num','path':'path'},...
			urlMap.put("path", reSavePath);
			// 上传图片备份
			SysContent.backUploadPics(pic, rename);
		}
		// 把数据添加到集合中
		listMap.add((HashMap) urlMap);
		// 移除原来的数据
		listMap.remove(index);
		// 把每个map数据结构数据转成标准json格式，存入listStr集合中
		List<String> listStr = new ArrayList<>();
		for (Map m : listMap) {
			JSONObject jsonObject = JSONObject.fromObject(m);
			listStr.add(jsonObject.toString());
		}
		pro.setPresalePermissionURL(listStr.toString());
		// 保存新的信息
		baseDao.saveOrUpdate(pro);
	}

	/**
	 * 项目详情app(中介端)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> findProjectByProperty(User user,String projectId) {
		Map<String, Object> map = new HashMap<>();
		String nowTime = DateTime.toString1(new Date());
		// 根据id查project
		Project p = (Project) baseDao.loadById(Project.class, projectId);
		// 查优惠信息
		String hql = "from ProjectBenefits where projectId = '" + projectId + "'" + "	and startTime <= '" + nowTime
				+ "' and endTime >= '" + nowTime + "'";
		List<ProjectBenefits> pbList = baseDao.findByHql(hql);
		// 查带看表
		String pgHQL = "from ProjectGuide pg where pg.projectId = '" + projectId + "'";
		ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);
		// 查该案场是否支持认购, 支持认购可以发起成交,不支持认购不可以发起成交
		String enterBuyHQL = "from EnterBuy eb where eb.projectId = '" + projectId + "'";
		EnterBuy eb = (EnterBuy) baseDao.loadObject(enterBuyHQL);

		//查所有效果图
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
		//取第一张效果图
		for (int i = 0; i < photoList.size(); i++) {
			if (i==0){
				String pictureStr = photoList.get(i);
				if (pictureStr != null && !"".equals(pictureStr)) {
					map.put("adPhoto", pictureStr);
				}
			}
		}
		//查该项目下所有户型图和效果图,一起轮播
		String houseTypeHQL = "from HouseType where projectId = '"+p.getProjectId()+"'";
		List<HouseType> htList = baseDao.findByHql(houseTypeHQL);
		for (HouseType houseType : htList) {
			photoList.add(houseType.getPhotoURL());
		}
		map.put("allPhotos", photoList);
		
		//查案场下可售房源
		String houseHQL = "from House where projectId = '"+p.getProjectId()+"' and houseStatus = 1 ";//and isOpen = 1";
		Set<Role> rs = user.getRoleId();
		if (user.getRoleId()!=null && !"".equals(user.getRoleId())){
			for (Role role : rs) {
				if (role.getRoleId() == 1 || role.getRoleId() == 2){
					houseHQL += " and isOpen = 1";
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
			map.put("unitCount", hList.size());
		}else {
			map.put("unitCount", 0);
		}
		
		// 获得是否支持认购
		if (eb != null && !"".equals(eb)) {
			String isSupportBuy = eb.getIsSupportBuy();
			if ("1".equals(isSupportBuy)) {
				map.put("isRenGou", "支持认购");
			} else {
				map.put("isRenGou", "不支持认购");
			}
			
		}
		
		// map.put("presalePermissionURL", p.getPresalePermissionURL());
		// 预售证号+图片 JSON解析字符串
		// if (p.getPresalePermissionURL()!=null) {
		// HashMap urlMap =
		// JSON.parseObject(p.getPresalePermissionURL(),HashMap.class);
		// map.put("presalePermissionInfo", urlMap);
		// //System.out.println("------预售证号+图片--------"+house.getPresalePermissionInfo());
		// }
		map.put("propertyAddress", p.getPropertyAddress());
		map.put("projectId", p.getProjectId());
		map.put("projectName", p.getProjectName());
		String city = p.getCity();
		String[] citystr = city.split("-");
		map.put("city", citystr[2]);
		map.put("landArea", SysContent.get2Double(p.getLandArea())+"㎡");
		map.put("buildArea", SysContent.get2Double(p.getBuildArea())+"㎡");
		map.put("groundArea", SysContent.get2Double(p.getGroundArea())+"㎡");
		map.put("underGroundArea", SysContent.get2Double(p.getUnderGroundArea())+"㎡");
		map.put("zonghushu", p.getUnitCount());
		map.put("volumeRatio", p.getVolumeRatio());
		map.put("density", p.getDensity()+"%");
		map.put("afforestationRatio", p.getAfforestationRatio()+"%");
		map.put("developer", p.getDeveloper());
		map.put("investor", p.getInvestor());
		map.put("manager", p.getManager());
		map.put("propertyCost", p.getPropertyCost()+"元/m²·月");
		map.put("rightsYears", p.getRightsYears()+"年");
		map.put("startTime", p.getStartTime());
		map.put("deliverTime", p.getDeliverTime());
		map.put("effectPic", p.getEffectPic());
		if (pg != null && !"".equals(pg)) {
			// 是否支持带看
			if (pg.getIsDaiKan() == 1) {
				map.put("isDaiKan", "支持带看");
			} else {
				map.put("isDaiKan", "不支持");
			}
			// 是否快速结佣
			if (pg.getIsFast() == 1) {
				map.put("isFast", "快速结佣");
			} else {
				map.put("isFast", "不支持");
			}
			// 是否支持异地
			if (pg.getIsYiDi() == 1) {
				map.put("isYiDi", "异地备案");
			} else {
				map.put("iaYiDi", "不支持");
			}
			// 备案有效期
			if (pg.getValidDays() != null && !"".equals(pg.getValidDays())) {
				map.put("validDays", pg.getValidDays());
			}else {
				map.put("validDays", 0);
			}
			
		}
		// 优惠信息
		if (pbList != null) {
			map.put("youhuiList", pbList);
		}
		return map;
	}

	@Override
	public Map<String, Object> findProjectByProperty(User user) {
		String projectId = user.getParentId();
		// 根据id查project
		Project p = (Project) baseDao.loadById(Project.class, projectId);
		// 查优惠信息
		String hql = "from ProjectBenefits where projectId = '" + projectId + "'";
		List<ProjectBenefits> pbList = baseDao.findByHql(hql);
		String pgHQL = "from ProjectGuide pg where pg.projectId = '" + projectId + "'";
		ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHQL);

		Map<String, Object> map = new HashMap<>();
		map.put("projectId", p.getProjectId());
		map.put("projectName", p.getProjectName());
		map.put("city", p.getCity());
		map.put("landArea", p.getLandArea());
		map.put("buildArea", p.getBuildArea());
		map.put("groundArea", p.getGroundArea());
		map.put("underGroundArea", p.getUnderGroundArea());
		map.put("unitCount", p.getUnitCount());
		map.put("volumeRatio", p.getVolumeRatio());
		map.put("density", p.getDensity());
		map.put("afforestationRatio", p.getAfforestationRatio());
		map.put("developer", p.getDeveloper());
		map.put("investor", p.getInvestor());
		map.put("manager", p.getManager());
		map.put("propertyCost", p.getPropertyCost());
		map.put("rightsYears", p.getRightsYears());
		map.put("startTime", p.getStartTime());
		map.put("deliverTime", p.getDeliverTime());
		map.put("effectPic", p.getEffectPic());
		// 是否支持带看
		if (pg.getIsDaiKan() == 1) {
			map.put("isDaiKan", "支持");
		} else {
			map.put("isDaiKan", "不支持");
		}
		// 是否快速结佣
		if (pg.getIsFast() == 1) {
			map.put("isFast", "支持");
		} else {
			map.put("isFast", "不支持");
		}
		// 是否支持异地
		if (pg.getIsYiDi() == 1) {
			map.put("isYiDi", "支持");
		} else {
			map.put("iaYiDi", "不支持");
		}
		// 客户保护期
		map.put("custormerProtectDays", pg.getCustormerProtectDays());
		// 优惠信息
		if (pbList != null) {
			map.put("youhuiList", pbList);
		}
		return map;
	}

	/**
	 * 中介端我的业务
	 * 
	 * @throws ParseException
	 */
	@Override
	public List findMidBusiness(User user, String startTime, String endTime, String projectName) throws ParseException {
		String userId = user.getUserId();
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 查所有
		// 根据userId查出备案成功的备案记录表信息
		String grHQL = "from GuideRecords gr where gr.userId = '" + userId + "' and "
				+ " ( gr.applyStatus = 0 or gr.applyStatus = 1 )";
		List<GuideRecords> grList = baseDao.findByHql(grHQL);
		// 成交记录
		String crHQL = "from ContractRecords cr where cr.userId = '" + userId + "' and cr.recordStatus = 5";
		List<ContractRecords> crList = baseDao.findByHql(crHQL);
		TreeSet ts = new TreeSet<>();
		// 遍历中介相关备案记录表获取所有案场id
		for (GuideRecords gr : grList) {
			ts.add(gr.getProjectId());
		}
		// 遍历中介成交记录所有案场id
		for (ContractRecords cr : crList) {
			ts.add(cr.getProjectId());
		}
		// 新new List
		List<MyBusinessDTO> myList = new ArrayList<>();
		// 遍历所有和中介有关系的案场id
		for (Object object : ts) {
			// new 对象
			MyBusinessDTO mbdto = new MyBusinessDTO();
			// 根据输入projectName 模糊查询
			String pHQL = "from Project p where p.projectId = '" + object + "' and p.projectName like '%" + projectName
					+ "%'";
			Project p = (Project) baseDao.loadObject(pHQL);
			if (p != null) {
				// 设置项目名称
				mbdto.setProjectName(p.getProjectName());
				// 获取projectId
				String pId = p.getProjectId();
				mbdto.setProjectId(p.getProjectId());
				// 查出该项目下所有的备案成功的备案信息,且在所选时间范围内的的备案信息
				// 已备案信息list
				String grHQL2 = "from GuideRecords gr where gr.projectId = '" + pId + "' and gr.userId = '" + userId
						+ "' and gr.applyStatus = 0";

				if (startTime != null && !"".equals(startTime)) {
					grHQL2 += " and gr.applyTime >= '" + startTime + "'";
				}
				if (endTime != null && !"".equals(endTime)) {
					grHQL2 += " and gr.applyTime <= '" + endTime + "'";
				}
				List<GuideRecords> grList2 = baseDao.findByHql(grHQL2);
				if (grList2 != null) {
					// 已备案的客户数量
					int yibeian = grList2.size();
					// 设置已备案数量
					mbdto.setGuideRecords(yibeian);
				}

				// 查带看业务表 查有效天数 该案场的带看记录表
				String projectGuideHQL = "from ProjectGuide pg where pg.projectId = '" + pId + "'";
				ProjectGuide pg = (ProjectGuide) baseDao.loadObject(projectGuideHQL);
				// 备案有效天数
				int validDays = pg.getValidDays();
				// 每一个案场对应的申请成功的该中介的客户
				String grHQL3 = "from GuideRecords gr where gr.projectId = '" + pId
						+ "' and gr.applyStatus = 0  and gr.userId = '" + userId + "'";
				List<GuideRecords> grList3 = baseDao.findByHql(grHQL3);
				// 将过期备案信息数计量
				int count = 0;
				for (GuideRecords guideRecords : grList3) {
					// 当前时间
					Date date = new Date();
					// 备案申请时间
					String applyTime = guideRecords.getApplyTime();
					Date d = sdf.parse(guideRecords.getApplyTime());
					// 申请时间+有效天数=到期时间
					String time = sdf.format(new Date(d.getTime() + validDays * 24 * 60 * 60 * 1000));
					Date date2 = sdf.parse(time);
					long j = date.getTime() - date2.getTime();
					int day = (int) (j / 1000 / 60 / 60 / 24);
					if (day <= 1 && day > 0) {
						count++;
					}
				}
				// 将过期数量
				mbdto.setNearOverdue(count);
				// 已到访list

				String grHQL4 = "from GuideRecords gr where gr.projectId = '" + pId + "' and gr.userId = '" + userId
						+ "'" + " and gr.applyStatus = 1";
				if (startTime != null && !"".equals(startTime)) {
					grHQL4 += " and gr.applyTime >= '" + startTime + "'";
				}
				if (endTime != null && !"".equals(endTime)) {
					grHQL4 += " and gr.applyTime <= '" + endTime + "'";
				}
				List<GuideRecords> grList4 = baseDao.findByHql(grHQL4);
				if (grList4 != null) {
					// 已到访的客户数量
					int yidaodaNum = grList4.size();
					// 设置已到访数量
					mbdto.setVisitRecords(yidaodaNum);
				}
				// 查已成交信息
				String crHQL2 = " from ContractRecords cr where cr.userId = '" + userId + "' and cr.projectId = '" + pId
						+ "' and cr.recordStatus = 5";

				if (startTime != null && !"".equals(startTime)) {
					crHQL2 += " and cr.applyTime >= '" + startTime + "'";
				}
				if (endTime != null && !"".equals(endTime)) {
					crHQL2 += " and cr.applyTime <= '" + endTime + "'";
				}
				List<ContractRecords> crList2 = baseDao.findByHql(crHQL2);
				if (crList2 != null) {
					int contractNum = crList2.size();
					// 设置成交数量
					mbdto.setDeal(contractNum);
				}
				myList.add(mbdto);
			}
		}
		return myList;
	}

	/**
	 * 置业顾问app端我的业务
	 */
	@Override
	public Map<String, Object> findSaleBusiness(User user) {
		// 置业顾问的userId
		String userId = user.getUserId();
		String parentId = user.getParentId();
		// 根据置业顾问parentId获得project
		String hql = "from Project p where p.projectId = '" + user.getParentId() + "'";
		Project project = (Project) baseDao.loadObject(hql);
		Map<String, Object> map = new HashMap<>();
		if (project != null && !"".equals(project)) {
			// 新new map
			// 添加项目id
			map.put("projectId", project.getProjectId());
			// 添加项目Name
			map.put("projectName", project.getProjectName());
			// 查该案场属于该置业顾问的的案场客户表list
			String pcHQL = "from ProjectCustomers pc where pc.projectId = '" + project.getProjectId()
					+ "' and pc.ownerUserId = '" + user.getUserId() + "' and pc.lastTime is not null ";
			List<ProjectCustomers> pcList = baseDao.findByHql(pcHQL);

			if (pcList != null) {
				// 该置业顾问的已到访客户的数量
				int yidaofangNum = pcList.size();
				// 添加已到访数量
				map.put("yidaofangNum", yidaofangNum);
			}
			// 查该案场已成交(签约)的记录表list
			String crHQL = "from ContractRecords cr where cr.projectId = '" + user.getParentId() + "' and cr.userId = '"
					+ user.getUserId() + "' and cr.recordStatus = 5";
			List<ContractRecords> crList = baseDao.findByHql(crHQL);
			if (crList != null) {
				// 该置业顾问的已成交客户数量
				int yichengjiaoNum = crList.size();
				map.put("yichengjiaoNum", yichengjiaoNum);
			}
		}
		return map;
	}

	/**
	 * 今日待办 list
	 * 
	 * @param user
	 * @param title
	 * @return
	 */
	@Override
	public List findInfoAboutProForAgent(User user, String title) {
		List needList = new ArrayList<>();
		if (user != null) {
			// 获取当天的起始时间和截至时间
			String startTime = SysContent.getStartTime();
			String endTime = SysContent.getEndTime();
			// 获取项目信息
			Project pro = (Project) baseDao.loadById(Project.class, user.getParentId());
			// 获取认购规则
			EnterBuy eb = (EnterBuy) baseDao.loadObject("from EnterBuy where projectId = '" + user.getParentId() + "'");
			// 获对应title的数据
			List<ContractRecords> list = new ArrayList<>();
			String hql = null;
			if (title != null && title.equals("enterBuy")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and recordStatus = 0 and applyTime >= '" + startTime + "' and applyTime <='" + endTime
						+ "'";
			} else if (title != null && title.equals("getMoney")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and voucherUploadTime != 'null' and applyTime >= '" + startTime + "' and applyTime <='"
						+ endTime + "'";
			} else if (title != null && title.equals("waitSign")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and recordStatus = 4 and applyTime >= '" + startTime + "' and applyTime <='" + endTime
						+ "'";
			} else if (title != null && title.equals("waitCash")) {
				String waitCashHql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and recordStatus = 5 and applyTime >= '" + startTime + "' and applyTime <='" + endTime
						+ "'";
				List<ContractRecords> crList = baseDao.findByHql(waitCashHql);
				if (crList != null) {
					Boolean isDaiKan = false;
					for (ContractRecords cr : crList) {
						if (cr != null) {
							User u = (User) baseDao.loadById(User.class, cr.getUserId());
							if (u != null) {
								Set<Role> rSet = u.getRoleId();
								for (Role r : rSet) {
									// 判断该提交订购申请角色是否是中介，获取带看的待结佣信息,若订购人是中介，则该案场客户为分销客户，反之为带看客户
									if (r.getRoleName().equals("medi") || r.getRoleName().equals("shopowner")) {
										// 获取带看备案记录信息
										String grHql = "from GuideRecords where projectCustomerId = '"
												+ cr.getProjectCustomerId() + "' and userId = '" + cr.getUserId() + "'";
										GuideRecords gr = (GuideRecords) baseDao.loadObject(grHql);// 该带看记录必须存在
										// 创建所需数据对象TodayNeedToDoDTO
										if (gr != null) {
											TodayNeedToDoDTO tnt = new TodayNeedToDoDTO();
											TodayNeedToDoDTO oneTnt = tnt.getOneTNT(pro, cr, eb, gr, isDaiKan);
											// 封装所需信息内容
											needList.add(oneTnt);
										}
									} else if (r.getRoleName().equals("agent")) {
										// 查询带看备案记录，判断该案场客户在备案记录中是否存在，若存在则该客户为中介带看客户
										String grHql = "from GuideRecords where projectCustomerId = '"
												+ cr.getProjectCustomerId() + "'";
										GuideRecords gr = (GuideRecords) baseDao.loadObject(grHql);
										if (gr != null) {
											isDaiKan = true;
											// 创建所需数据对象TodayNeedToDoDTO
											TodayNeedToDoDTO tnt = new TodayNeedToDoDTO();
											TodayNeedToDoDTO oneTnt = tnt.getOneTNT(pro, cr, eb, gr, isDaiKan);
											// 封装所需信息内容
											needList.add(oneTnt);
										}
									}

								}
							}
						}
					}
				}
			}
			if (hql != null) {
				// 获取数据集合
				list = baseDao.findByHql(hql);
				if (!list.isEmpty()) {
					for (ContractRecords crs : list) {
						// 创建所需数据对象TodayNeedToDoDTO
						TodayNeedToDoDTO tnt = new TodayNeedToDoDTO();
						TodayNeedToDoDTO oneTnt = tnt.getOneTNT(pro, crs, eb, null, null);
						String[] cityArr = pro.getCity().split("-");
						String area = cityArr[cityArr.length-1];
						String areaHql = "from CountryProvinceInfo where cityId = '"+area+"'";
						CountryProvinceInfo cpi = (CountryProvinceInfo) baseDao.loadObject(areaHql);
						//获取市的名字
						String cityName = cpi.getUpCityName();
						//获取区的名字
						String areaName = cpi.getCityName();
						//获取市的id,获取省的名字
						String cityUpHql = "from CountryProvinceInfo where cityId = '" +cpi.getUpCityId()+"'";
						CountryProvinceInfo cpi2 = (CountryProvinceInfo) baseDao.loadObject(cityUpHql);
						String provinName = cpi2.getUpCityName();
						oneTnt.setProCity(provinName+cityName+areaName);
						// 封装所需信息内容
						needList.add(oneTnt);
					}
				}
			}
		}
		return needList;
	}

	/**
	 * 今日待办 counts
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public Map findCountInfo(User user) {
		Map<String, Integer> countMap = new HashMap<>();
		if (user != null) {
			// 获取当天的起始时间和截至时间
			String startTime = SysContent.getStartTime();
			String endTime = SysContent.getEndTime();
			String enterBuyHql = "select count(*) from ContractRecords where projectId = '" + user.getParentId()
					+ "' and recordStatus = 0 and applyTime >= '" + startTime + "' and applyTime <='" + endTime + "'";
			String getMoneyHql = "select count(*) from ContractRecords where projectId = '" + user.getParentId()
					+ "' and voucherUploadTime != 'null' and applyTime >= '" + startTime + "' and applyTime <='"
					+ endTime + "'";
			String waitSignHql = "select count(*) from ContractRecords where projectId = '" + user.getParentId()
					+ "' and recordStatus = 4 and applyTime >= '" + startTime + "' and applyTime <='" + endTime + "'";
			String waitCashHql = "from ContractRecords where projectId = '" + user.getParentId()
					+ "' and recordStatus = 5 and applyTime >= '" + startTime + "' and applyTime <='" + endTime + "'";
			// 获取认购确认的数量
			int enterBuyCount = baseDao.countQuery(enterBuyHql);
			// 获取到款确认的数量
			int getMoneyCount = baseDao.countQuery(getMoneyHql);
			// 获取待签约中的数量
			int waitSignCount = baseDao.countQuery(waitSignHql);
			// 获取待结佣中数量
			int waitCashCount = 0;
			List<ContractRecords> crList = baseDao.findByHql(waitCashHql);
			if (crList != null) {
				for (ContractRecords cr : crList) {
					if (cr != null) {
						User u = (User) baseDao.loadById(User.class, cr.getUserId());
						if (u != null) {
							Set<Role> rSet = u.getRoleId();
							for (Role r : rSet) {
								// 如果成交记录中的userid是中介角色，则该客户为分销客户，反之为带看客户
								if (r.getRoleName().equals("medi") || r.getRoleName().equals("shopowner")) {
									waitCashCount++;
								} else if (r.getRoleName().equals("agent")) {
									// 查询带看备案记录，判断该案场客户在备案记录中是否存在，若存在则该客户为中介带看客户
									String grHql = "from GuideRecords where projectCustomerId = '"
											+ cr.getProjectCustomerId() + "'";
									GuideRecords gr = (GuideRecords) baseDao.loadObject(grHql);
									if (gr != null) {
										waitCashCount++;
									}
								}
							}
						}
					}
				}
			}
			countMap.put("enterBuyCount", enterBuyCount);
			countMap.put("getMoneyCount", getMoneyCount);
			countMap.put("waitSignCount", waitSignCount);
			countMap.put("waitCashCount", waitCashCount);
		}
		return countMap;
	}

	/**
	 * 今日案场 counts
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public Map findTodayProCounts(User user) {
		Map<String, Integer> countMap = new HashMap<>();
		if (user != null) {
			String startTime = SysContent.getStartTime();
			String endTime = SysContent.getEndTime();
			String todayVisitHql = "select count(*) from VisitRecords where projectId = '" + user.getParentId()
					+ "' and arriveTime >= '" + startTime + "' and arriveTime<='" + endTime + "'";
			String todayGuidHql = "select count(*) from GuideRecords where projectId = '" + user.getParentId()
					+ "' and applyStatus = 1 and applyTime >= '" + startTime + "' and applyTime <='" + endTime + "'";
			String todayRecordHql = "select count(*) from GuideRecords where projectId = '" + user.getParentId()
					+ "' and applyStatus = 0 and applyTime >= '" + startTime + "' and applyTime <='" + endTime + "'";
			String todayEnterBuyHql = "select count(*) from ContractRecords where projectId = '" + user.getParentId()
					+ "' and recordStatus = 1 and applyTime >= '" + startTime + "' and applyTime <='" + endTime + "'";
			String todaySignHql = "select count(*) from ContractRecords where projectId = '" + user.getParentId()
					+ "' and recordStatus = 5 and contractConfirmTime >= '" + startTime
					+ "' and contractConfirmTime <='" + endTime + "'";
			// 获取今日接访的数量
			int todayVisitCount = baseDao.countQuery(todayVisitHql);
			// 获取今日带看的数量
			int todayGuidCount = baseDao.countQuery(todayGuidHql);
			// 获取今日备案的数量
			int todayRecordCount = baseDao.countQuery(todayRecordHql);
			// 获取今日认购中数量
			int todayEnterBuyCount = baseDao.countQuery(todayEnterBuyHql);
			// 获取今日签约
			int todaySignCount = baseDao.countQuery(todaySignHql);

			countMap.put("todayVisitCount", todayVisitCount);
			countMap.put("todayGuidCount", todayGuidCount);
			countMap.put("todayRecordCount", todayRecordCount);
			countMap.put("todayEnterBuyCount", todayEnterBuyCount);
			countMap.put("todaySignCount", todaySignCount);
		}
		return countMap;
	}

	/**
	 * 今日案场 list
	 * 
	 * @param user
	 * @param title
	 * @return
	 */
	@Override
	public List findTodayProList(User user, String title) {
		return null;
	}

	@Override
	public List findAllAgentByProId(User user, String selectStatus,Page page) {
		List userDtoList = new ArrayList<>();
		if (user != null) {
			String hql = "from User as model where model.parentId = '" + user.getParentId()
					+ "' and model.userStatus !=2 and model.userStatus !=0";
			if (selectStatus != null && !selectStatus.equals("")) {
				hql += " and userStatus = " + Integer.parseInt(selectStatus);
			}
			List<User> userList = baseDao.findByHql(hql);
			for (User u : userList) {
				UserDTO uDto = new UserDTO();
				UserDTO userDto = uDto.creatUserDTO(u);
				userDtoList.add(userDto);
			}
		}
		return userDtoList;
	}

	@Override
	public List findAllMediByShopId(User user, String selectRole, String enOrDisable) {
		List userDtoList = new ArrayList<>();
		List<User> uList = new ArrayList<>();
		if (user != null) {
			String hql = "from User as model where model.parentId = '" + Integer.parseInt(user.getParentId())
					+ "' and model.userStatus !=2 and model.userStatus !=0";
			if (enOrDisable != null && !enOrDisable.equals("")) {
				hql += " and userStatus = " + Integer.parseInt(enOrDisable);
			}
			List<User> userList = baseDao.findByHql(hql);
			if (selectRole != null && !selectRole.equals("")) {
				for (User u : userList) {
					Set<Role> role = u.getRoleId();
					for (Role r : role) {
						if (r.getRoleName().equals(selectRole)) {
							uList.add(u);
						}
					}
				}
			} else {
				uList.addAll(userList);
			}
			if (uList != null) {
				for (User u : uList) {
					UserDTO uDto = new UserDTO();
					UserDTO userDto = uDto.creatUserDTO(u);
					userDtoList.add(userDto);
				}
			}
		}
		return userDtoList;
	}

	/**
	 * 今日门店 counts
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public Map findTodayShopCounts(User user) {
		Map<String, Integer> countMap = new HashMap<>();
		if (user != null) {
			String startTime = SysContent.getStartTime();
			String endTime = SysContent.getEndTime();
			int parentId = Integer.parseInt(user.getParentId());
			String shopUserHql = "from User where parentId = " + parentId;
			List<User> shopUserList = baseDao.findByHql(shopUserHql);// 获取当前门店下所有中介
			List<ContractRecords> todayEnterBuyList = new ArrayList<>();// 今日认购
			List<GuideRecords> todayRecordList = new ArrayList<>();// 今日备案
			List<GuideRecords> todayGuideList = new ArrayList<>();// 今日带看
			List<GuideRecords> willExpiredList = new ArrayList<>();// 即将到期
			// 便利当前门店下所有中介集合
			for (User u : shopUserList) {
				String todayEnterBuyHql = "from ContractRecords where userId = '" + u.getUserId()
						+ "' and applyTime >= '" + startTime + "' and applyTime <='" + endTime
						+ "'";
				String todayRecordHql = "from GuideRecords where userId = '" + u.getUserId()
						+ "' and applyStatus = 0 and applyTime >= '" + startTime + "' and applyTime <='" + endTime
						+ "'";
				String todayGuidHql = "from GuideRecords where userId = '" + u.getUserId()
						+ "' and applyStatus = 1 and applyTime >= '" + startTime + "' and applyTime <='" + endTime
						+ "'";
				String oneMediAllGRSHql = "from GuideRecords where userId = '" + u.getUserId()
						+ "' and applyStatus = 0";
				List<GuideRecords> oneMediAllGRSList = baseDao.findByHql(oneMediAllGRSHql);// 每一个中介的备案记录集合
				// 便利每一个中介的备案记录集合
				for (GuideRecords gr : oneMediAllGRSList) {
					String pgHql = "from ProjectGuide where projectId ='" + gr.getProjectId() + "'";
					ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHql);// 获取当前中介的当前备案信息（为下面获取有效天数，计算即将到期时间和到期时间做准备）
					String willExpireTime = DateTime.getNewDay(gr.getApplyTime(),pg.getValidDays()-1); // 即将到期的时间
					String ExpireTime = DateTime.getNewDay(gr.getApplyTime(),pg.getValidDays()); // 到期的时间
					if(new Date().getTime() > DateUtil.parse(ExpireTime).getTime() && new Date().getTime() <=
							DateUtil.parse(willExpireTime).getTime()){
						willExpiredList.add(gr);// 所有中介的备案即将到期数据
					}
				}
				List<ContractRecords> thisUserEnterBuyList = baseDao.findByHql(todayEnterBuyHql);// 当前中介的今日认购
				List<GuideRecords> thisUserRecordList = baseDao.findByHql(todayRecordHql);// 当前中介的今日备案
				List<GuideRecords> thisUserGuideList = baseDao.findByHql(todayGuidHql);// 当前中介的今日带看
				for (ContractRecords cr : thisUserEnterBuyList) {
					todayEnterBuyList.add(cr);// 所有中介的今日认购
				}
				for (GuideRecords cr : thisUserRecordList) {
					todayRecordList.add(cr);// 所有中介的今日备案
				}
				for (GuideRecords cr : thisUserGuideList) {
					todayGuideList.add(cr);// 所有中介的今日带看
				}
			}
			// 获取今日带看的数量
			int todayGuidCount = todayGuideList.size();
			// 获取今日备案的数量
			int todayRecordCount = todayRecordList.size();
			// 获取今日认购中数量
			int todayEnterBuyCount = todayEnterBuyList.size();
			// 即将到期
			int willExpiredCount = willExpiredList.size();

			countMap.put("todayGuidCount", todayGuidCount);
			countMap.put("todayRecordCount", todayRecordCount);
			countMap.put("todayEnterBuyCount", todayEnterBuyCount);
			countMap.put("willExpiredCount", willExpiredCount);
		}
		return countMap;
	}

	@Override
	public List findTodayShopList(User user, String title) {
		List needList = new ArrayList<>();
		if (user != null) {
			String startTime = SysContent.getStartTime();
			String endTime = SysContent.getEndTime();
			int parentId = Integer.parseInt(user.getParentId());
			String shopUserHql = "from User where parentId = " + parentId;
			List<User> shopUserList = baseDao.findByHql(shopUserHql);// 获取当前门店下所有中介
			List<GuideRecords> willExpiredList = new ArrayList<>();// 即将到期
			// 便利当前门店下所有中介集合
			for (User u : shopUserList) {
				if (title.equals("enterbuy")) {
					String todayEnterBuyHql = "from ContractRecords where userId = '" + u.getUserId()
							+ "' and applyTime >= '" + startTime + "' and applyTime <='" + endTime
							+ "'";
					List<ContractRecords> thisUserEnterBuyList = baseDao.findByHql(todayEnterBuyHql);// 当前中介的今日认购
					for (ContractRecords cr : thisUserEnterBuyList) {
						Project pro = (Project) baseDao.loadById(Project.class, cr.getProjectId());
						TodayNeedToDoDTO tntdd = new TodayNeedToDoDTO();
						tntdd.createTodayShopObj(pro, cr, null, null, null);
						needList.add(tntdd);
					}

				} else if (title.equals("record")) {
					String todayRecordHql = "from GuideRecords where userId = '" + u.getUserId()
							+ "' and applyStatus = 0 and applyTime >= '" + startTime + "' and applyTime <='" + endTime
							+ "'";
					List<GuideRecords> thisUserRecordList = baseDao.findByHql(todayRecordHql);// 当前中介的今日备案
					for (GuideRecords gr : thisUserRecordList) {
						Project pro = (Project) baseDao.loadById(Project.class, gr.getProjectId());
						TodayNeedToDoDTO tntdd = new TodayNeedToDoDTO();
						tntdd.createTodayShopObj(pro, null, gr, u, null);
						needList.add(tntdd);
					}
				} else if (title.equals("guid")) {
					String todayGuidHql = "from GuideRecords where userId = '" + u.getUserId()
							+ "' and applyStatus = 1 and applyTime >= '" + startTime + "' and applyTime <='" + endTime
							+ "'";
					List<GuideRecords> thisUserGuideList = baseDao.findByHql(todayGuidHql);// 当前中介的今日带看
					for (GuideRecords gr : thisUserGuideList) {
						Project pro = (Project) baseDao.loadById(Project.class, gr.getProjectId());
						ProjectCustomers pc = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class,
								gr.getProjectCustomerId());
						TodayNeedToDoDTO tntdd = new TodayNeedToDoDTO();
						tntdd.createTodayShopObj(pro, null, null, u, pc);
						needList.add(tntdd);
					}
				} else if (title.equals("willexpire")) {
					String oneMediAllGRSHql = "from GuideRecords where userId = '" + u.getUserId()
							+ "' and applyStatus = 0";
					List<GuideRecords> oneMediAllGRSList = baseDao.findByHql(oneMediAllGRSHql);// 每一个中介的备案记录集合
					// 便利每一个中介的备案记录集合
					for (GuideRecords gr : oneMediAllGRSList) {
						String pgHql = "from ProjectGuide where projectId ='" + gr.getProjectId() + "'";
						ProjectGuide pg = (ProjectGuide) baseDao.loadObject(pgHql);// 获取当前中介的当前备案信息（为下面获取有效天数，计算即将到期时间和到期时间做准备）
						String willExpireTime = DateTime.getNewDay(gr.getApplyTime(),pg.getValidDays()-1); // 即将到期的时间
						String ExpireTime = DateTime.getNewDay(gr.getApplyTime(),pg.getValidDays()); // 到期的时间
						if(new Date().getTime() > DateUtil.parse(ExpireTime).getTime() && new Date().getTime()<=
								DateUtil.parse(willExpireTime).getTime()){
							Project pro = (Project) baseDao.loadById(Project.class, gr.getProjectId());
							TodayNeedToDoDTO tntdd = new TodayNeedToDoDTO();
							tntdd.createTodayShopObj(pro, null, gr, u, null);
							tntdd.setAppTime(ExpireTime);// 设置即将到期中的到期时间
							needList.add(tntdd);
						}
					}
				}
			}
		}
		return needList;
	}

	/**
	 * 今日待办 - 查看认购申请
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> findCurrentHouseBuyApply(User user, Integer houseNum) throws Exception {
		Map map = new HashMap<>();
		if (user != null && houseNum != null) {
			House h = (House) baseDao.loadById(House.class, houseNum);
			HouseType ht = (HouseType) baseDao.loadById(HouseType.class, h.getHouseTypeId());
			SeeBuyApplyDTO sadHouse = new SeeBuyApplyDTO();
			SeeBuyApplyDTO houseDto = sadHouse.creatHouseDTO(h, ht);
			map.put("houseInfo", houseDto);
			String hql = "from ContractRecords where houseNum = " + houseNum + " and recordStatus = 0";
			String hadConReHql = "from ContractRecords where houseNum = " + houseNum + " and recordStatus = 1";
			List<ContractRecords> crList = baseDao.findByHql(hql);
			ContractRecords hadConRe = (ContractRecords) baseDao.loadObject(hadConReHql);
			List mapList = new ArrayList<>();
			for (ContractRecords crs : crList) {
				SeeBuyApplyDTO sadBuyApply = new SeeBuyApplyDTO();
				SeeBuyApplyDTO buyApplyDto = sadBuyApply.creatBuyApplyDTO(crs, user);
				String ethql = "from EnterBuy where projectId = '" +crs.getProjectId()+"'";
				EnterBuy eb = (EnterBuy) baseDao.loadObject(ethql);
				int outTime = Integer.parseInt(eb.getOutOfTime());//订购规则的超时时间
				if(hadConRe!=null){
					long time = DateUtil.parse(SysContent.addSameHours(hadConRe.getAuditingTime(), outTime)).getTime();//最晚打款时间
					if(time>new Date().getTime()){
						buyApplyDto.setHadConRe("yes");
					}
				}
				
				/*新增的字段*/
				//真实购买人
				List<RealEnterBuyMan> realList = new ArrayList<>();
				if(crs.getOrderProperty() != null && !"".equals(crs.getOrderProperty()) && crs.getOrderProperty() == 1){	//如果订单性质为代购才有真实购买人
					List<Integer> list = JSON.parseArray(crs.getRealCustomerId(), Integer.TYPE);
					for(Integer in : list){
						RealEnterBuyMan realEnterBuyMan = (RealEnterBuyMan) baseDao.loadById(RealEnterBuyMan.class, in);
						realList.add(realEnterBuyMan);
					}
					
				}
				
				//认购规则
				String enterBuyHql = "from EnterBuy where projectId = '" + crs.getProjectId() + "'";
				EnterBuy enterBuy = (EnterBuy) baseDao.loadObject(enterBuyHql);
				
				
				SeeBuyApplyDTO contractMenInfo = sadBuyApply.createContractMenInfo(crs, realList, enterBuy);
				//遮挡身份证号码
				String idCard = contractMenInfo.getCustomerIDCard();
				String head = null;
				String end = null;
				if(idCard != null && !"".equals(idCard)){
//					head = idCard.substring(0, 3);
//					end = idCard.substring(idCard.length()-3,idCard.length());
//					buyApplyDto.setCustomerIDCard(head+"************"+end);
					buyApplyDto.setCustomerIDCard(idCard);
				}else{
					buyApplyDto.setCustomerIDCard("-无-");
				}
				buyApplyDto.setOrderProperty(contractMenInfo.getOrderProperty());
				if(crs.getOrderProperty() != null && "".equals(crs.getOrderProperty()) && crs.getOrderProperty() == 1)
					buyApplyDto.setrName(contractMenInfo.getrName());
				buyApplyDto.setDposit(contractMenInfo.getDposit());
				buyApplyDto.setPayStyle(contractMenInfo.getPayStyle());
				buyApplyDto.setAccountStyle(contractMenInfo.getAccountStyle());
				buyApplyDto.setBenefitInfo(contractMenInfo.getBenefitInfo());
				buyApplyDto.setCustomerPhone(contractMenInfo.getCustomerPhone());
				buyApplyDto.setCustomerName(contractMenInfo.getCustomerName());
				mapList.add(buyApplyDto);
			}
			map.put("buyApplyList", mapList);
		}
		return map;
	}

	/**
	 * 今日待办 - 认购申请 审核
	 * 
	 * @param u
	 * @param recordNo
	 * @param checkReson
	 * @throws IOException
	 */
	@Override
	public void updateAgreeBuyApply(User u, Integer recordNo, String checkReson) throws Exception {
		if (u != null && recordNo != null && !recordNo.equals("")) {
			// 同意申请，修改订购信息
			ContractRecords crs = (ContractRecords) baseDao.loadById(ContractRecords.class, recordNo);
			crs.setAuditingTime(DateTime.toString1(new Date()));
			crs.setAuditionUserId(u.getUserId());
			crs.setAuditionReson(checkReson);
			crs.setRecordStatus(1);
			baseDao.saveOrUpdate(crs);
			// 短信发送
			// 内容（"您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，请及时处理！"）
			User appUser = (User) baseDao.loadById(User.class, crs.getUserId());
			Project pro = (Project) baseDao.loadById(Project.class, crs.getProjectId());
			House house = (House) baseDao.loadById(House.class, crs.getHouseNum());
			String text = SmsContext.AGREE_BUY_APPLY.replace("#projectName#", pro.getProjectName())
					.replace("#8#", house.getBuildingNo() + house.getUnit() + house.getHouseNo())
					.replace("#customerName#", crs.getCustomerName()).replace("#state#", "审核通过");
			// 调用短信发送接口，发送信息给申请人
			JavaSmsApi.sendSms(text, appUser.getPhone());
			//获取短信发送时间
			String ebHql = "from EnterBuy where projectId = '" + crs.getProjectId() + "'";
			EnterBuy eb = (EnterBuy) baseDao.loadObject(ebHql);// 获取订购规则
			int outTime = Integer.parseInt(eb.getOutOfTime());// 订购规则的超时时间
			String maxPalyMoneyTimeStr = SysContent.addSameHours(crs.getAuditingTime(), outTime);// 最晚打款时间
			String date = SysContent.addSameMinute(maxPalyMoneyTimeStr, -30);
			//将新的审核添加到待发短信表中
			MiniMessageWaitSend mmws = new MiniMessageWaitSend();
			mmws.setProjectId(crs.getProjectId());
			mmws.setHouseNum(crs.getHouseNum());
			mmws.setDate(date);
			mmws.setRecordNo(recordNo);
			mmws.setPhone(appUser.getPhone());
			mmws.setText(text);
			mmws.setIsSendMiniMessage("no");
			baseDao.saveOrUpdate(mmws);
			//给审核同意的客户添加定时超时状态
			HashMap map = new HashMap<>();
			map.put("uId", u.getUserId());
			map.put("uPhone", u.getPhone());
			map.put("msg", "客户"+crs.getCustomerName()+"打款已超期。");
			map.put("pId", crs.getProjectId());
			map.put("hNum", crs.getHouseNum().toString());
			map.put("pcId", crs.getProjectCustomerId());
			map.put("pcName", crs.getCustomerName());
			map.put("cPhone", crs.getCustomerPhone());
			map.put("pName", pro.getProjectName());
			OrderWillExpiringSmsQuartz.addJob(date, recordNo.toString(), appUser.getPhone(), text,mmws.getId(),map);// 加入定时短信发送池
			//添加认购动态
			String mdcStr = "客户"+crs.getCustomerName()+"认购申请已通过审核,请及时打款。";
			Mydynamic mdc = DynamicUtil.createOneDynamic(u.getUserId(), u.getPhone(), mdcStr, 0, DateTime.toString1(new Date()),
					crs.getProjectId(), crs.getHouseNum().toString(),"待下定","0",crs.getProjectCustomerId(),crs.getCustomerName(),crs.getCustomerPhone(),pro.getProjectName());
			baseDao.saveOrUpdate(mdc);
			//设置其他认购客户排队动态
			String otherConRecHql = "from ContractRecords where houseNum = "+crs.getHouseNum()+" and recordStatus = 0";
			List<ContractRecords> otherCRList = baseDao.findByHql(otherConRecHql);
			for(ContractRecords cr : otherCRList){
				String othermdcStr = "客户"+cr.getCustomerName()+"申请的房源已被认购,已进入排队状态,是否继续等待？";
				Mydynamic otherMdc = DynamicUtil.createOneDynamic(u.getUserId(), u.getPhone(), othermdcStr, 0, DateTime.toString1(new Date()),
						cr.getProjectId(), cr.getHouseNum().toString(),"认购排队","0",cr.getProjectCustomerId(),cr.getCustomerName(),cr.getCustomerPhone(),pro.getProjectName());
				baseDao.saveOrUpdate(otherMdc);
			}
		}

	}

	@Override
	public void updateRefuseBuyApply(User u, Integer recordNo, String reason) throws IOException {
		if (u != null && recordNo != null && !recordNo.equals("")) {
			// 拒绝申请，修改订购信息
			ContractRecords crs = (ContractRecords) baseDao.loadById(ContractRecords.class, recordNo);
			crs.setAuditingTime(DateTime.toString1(new Date()));
			crs.setAuditionUserId(u.getUserId());
			crs.setAuditionReson(reason);
			crs.setRecordStatus(3);
			baseDao.saveOrUpdate(crs);
			// 短信发送
			// 内容（"您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，原因为：#reason#";）
			User appUser = (User) baseDao.loadById(User.class, crs.getUserId());
			Project pro = (Project) baseDao.loadById(Project.class, crs.getProjectId());
			House house = (House) baseDao.loadById(House.class, crs.getHouseNum());
			String text = SmsContext.REFUSE_BUY_APPLY.replace("#projectName#", pro.getProjectName())
					.replace("#8#", house.getBuildingNo() + house.getUnit() + house.getHouseNo())
					.replace("#customerName#", crs.getCustomerName()).replace("#state#", "拒绝")
					.replace("#reason#", reason);
			// 调用短信发送接口，发送信息给申请人
			JavaSmsApi.sendSms(text, appUser.getPhone());
			//添加认购拒绝动态
			String mdcStr = "客户"+crs.getCustomerName()+"的认购申请已被拒绝,您的订单审核未通过。";
			Mydynamic mdc = DynamicUtil.createOneDynamic(u.getUserId(), u.getPhone(), mdcStr, 0, DateTime.toString1(new Date()),
					crs.getProjectId(), crs.getHouseNum().toString(),"申请被拒","0",crs.getProjectCustomerId(),crs.getCustomerName(),crs.getCustomerPhone(),pro.getProjectName());
			baseDao.saveOrUpdate(mdc);
		}
	}

	@Override
	public List findBuyApplyAppointList(User user, String doSign) throws ParseException {
		List sbaDtoList = new ArrayList<>();
		if (user != null) {
			// 获取当前项目的认购规则
			String enterBuyHql = "from EnterBuy where projectId = '" + user.getParentId() + "'";
			EnterBuy eb = (EnterBuy) baseDao.loadObject(enterBuyHql);
			int outTime = Integer.parseInt(eb.getOutOfTime());
			String hql = null;
			if (doSign.equals("waitCheck")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and recordStatus = 0 GROUP BY houseNum";
			} else if (doSign.equals("alreadyCheck")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and recordStatus = 1";
			} else if (doSign.equals("alreadyRefuse")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId() + "' and recordStatus = 3";
			} else if (doSign.equals("outTimeNotPlayMoney")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and recordStatus = 1 and auditingTime < '"
						+ SysContent.addSameHours(DateTime.toString1(new Date()), -outTime) + "'";
			} else if (doSign.equals("backOutApply")) { //撤单
				hql = "from ContractRecords where projectId = '" + user.getParentId() + "' and recordStatus = 7";
			}
			if (hql != null) {
				List<ContractRecords> crList = baseDao.findByHql(hql);
				for (ContractRecords crs : crList) {
					DecimalFormat df = new DecimalFormat("#.##");
					Double minute = null;
					Double outMinute = null;
					// 非申请状态下 审核时间 是存在的  因为撤单也可以是在没有审核的情况下进行，所以审核时间可能为空
					if (!crs.getRecordStatus().equals(0) && (crs.getAuditingTime() != null && !"".equals(crs.getAuditingTime()))) {
						// 打款剩余时间
						String MaxpalyMoneyTime = SysContent.addSameHours(crs.getAuditingTime(), outTime);
						minute = (double) DateUtil.getOffsetMinute(new Date(), DateUtil.parse(MaxpalyMoneyTime));
						// 打款超期时间
						outMinute = (double) DateUtil.getOffsetMinute(DateUtil.parse(MaxpalyMoneyTime), new Date());
					}
					House h = (House) baseDao.loadById(House.class, crs.getHouseNum());
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, h.getHouseTypeId());
					SeeBuyApplyDTO sba = new SeeBuyApplyDTO();
					SeeBuyApplyDTO sbaDto = sba.createTradeBusinessBuyApplyDTO(h, ht, crs, user);
					// 申请数量
					String countHql = "select count(*) from ContractRecords where houseNum= " + crs.getHouseNum()
							+ " and recordStatus = 0";
					sbaDto.setApplyThisHouseCounts(baseDao.countQuery(countHql));

					if (minute != null && minute >= 0) {
						Double hour = minute / 1000 / 3600;
						sbaDto.setSurplusPalyMoneyTime(df.format(hour));
					}else{
						sbaDto.setSurplusPalyMoneyTime("已超期");
					}
					if (outMinute != null && outMinute >= 0) {
						Double hour = outMinute / 1000 / 3600;
						sbaDto.setPalyMoneyOutTime(df.format(hour));
					}
					sbaDtoList.add(sbaDto);
				}
			}

		}
		return sbaDtoList;
	}

	@Override
	public List findGetBargainAppointList(User user, String doSign) throws ParseException {
		List sbaDtoList = new ArrayList<>();
		if (user != null) {
			String hql = null;
			if (doSign.equals("notEnter")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and recordStatus = 1 and voucherUploadTime is not null";
			} else if (doSign.equals("alreadyEnter")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and recordStatus = 4 and remitConfirmUserId is not null";
			}
			if (hql != null) {
				List<ContractRecords> crList = baseDao.findByHql(hql);
				for (ContractRecords crs : crList) {
					House h = (House) baseDao.loadById(House.class, crs.getHouseNum());
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, h.getHouseTypeId());
					SeeBuyApplyDTO sba = new SeeBuyApplyDTO();
					SeeBuyApplyDTO sbaDto = sba.createTradeBusinessBuyApplyDTO(h, ht, crs, user);
					sbaDtoList.add(sbaDto);
				}
			}

		}
		return sbaDtoList;
	}

	@Override
	public List findSignAppointList(User user, String doSign) throws ParseException {
		List sbaDtoList = new ArrayList<>();
		if (user != null) {
			String hql = null;
			if (doSign.equals("notEnter")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and recordStatus = 4 and contractconfirmUseerId is null";
			} else if (doSign.equals("alreadyEnter")) {
				hql = "from ContractRecords where projectId = '" + user.getParentId()
						+ "' and recordStatus = 5 and contractconfirmUseerId is not null";
			}
			if (hql != null) {
				List<ContractRecords> crList = baseDao.findByHql(hql);
				for (ContractRecords crs : crList) {
					House h = (House) baseDao.loadById(House.class, crs.getHouseNum());
					HouseType ht = (HouseType) baseDao.loadById(HouseType.class, h.getHouseTypeId());
					SeeBuyApplyDTO sba = new SeeBuyApplyDTO();
					SeeBuyApplyDTO sbaDto = sba.createTradeBusinessBuyApplyDTO(h, ht, crs, user);
					sbaDtoList.add(sbaDto);
				}
			}

		}
		return sbaDtoList;
	}

	@Override
	public void addOrUpdateContractRecords(User user, Integer recordNo) throws IOException {
		if (user != null) {
			ContractRecords cr = (ContractRecords) baseDao.loadById(ContractRecords.class, recordNo);
			cr.setRecordStatus(4);
			cr.setRemitConfirmTime(DateTime.toString1(new Date()));
			cr.setRemitConfirmUserId(user.getUserId());
			baseDao.saveOrUpdate(cr);
			// 确认到款之后，修改房源状态
			House house = (House) baseDao.loadById(House.class, cr.getHouseNum());
			//house.setHouseStatus(4);
			//baseDao.saveOrUpdate(house);
			//给已下定客户添加待签约动态
			//添加认购动态
			Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());//获取项目信息
			String mdcStr = "客户"+cr.getCustomerName()+"定金已确认到款,请及时办理签约事宜。";
			Mydynamic mdc = DynamicUtil.createOneDynamic(user.getUserId(), user.getPhone(), mdcStr, 0, DateTime.toString1(new Date()),
					cr.getProjectId(), cr.getHouseNum().toString(),"等待签约","0",cr.getProjectCustomerId(),cr.getCustomerName(),cr.getCustomerPhone(),p.getProjectName());
			baseDao.saveOrUpdate(mdc);
			// 确认到款后，否决其他申请订购客户
			String hql = "from ContractRecords where recordStatus = 0 and houseNum = " + cr.getHouseNum();
			List<ContractRecords> crList = baseDao.findByHql(hql);
			for (ContractRecords crs : crList) {
				/*
				 * crs.setRecordStatus(3);
				crs.setAuditionReson("已被其他用户购买");
				crs.setAuditingTime(DateTime.toString1(new Date()));
				crs.setAuditionUserId(user.getUserId());
				baseDao.saveOrUpdate(crs);
				*/
				
				// 短信通知，房源已被下定
				// 内容（"您申请#projectName#项目，房源名称为#8#，客户姓名：#customerName#，已经被#state#，原因为：#reason#";）
				User appUser = (User) baseDao.loadById(User.class, crs.getUserId());
				Project pro = (Project) baseDao.loadById(Project.class, crs.getProjectId());
				// House house = (House) baseDao.loadById(House.class,
				// crs.getHouseNum());
				String text = SmsContext.REFUSE_BUY_APPLY.replace("#projectName#", pro.getProjectName())
						.replace("#8#", house.getBuildingNo() + house.getUnit() + house.getHouseNo())
						.replace("#customerName#", crs.getCustomerName()).replace("#state#", "下定")
						.replace("#reason#", "已被其他人下定");
				// 调用短信发送接口，发送信息给申请人
				JavaSmsApi.sendSms(text, appUser.getPhone());
				//给否决认购的客户添加房源锁定动态
				String otherMdcStr = "客户"+crs.getCustomerName()+"的申请的房源已被锁定。";
				Mydynamic otherMdc = DynamicUtil.createOneDynamic(user.getUserId(), user.getPhone(), otherMdcStr, 0, DateTime.toString1(new Date()),
						crs.getProjectId(), crs.getHouseNum().toString(),"房源锁定","0",crs.getProjectCustomerId(),crs.getCustomerName(),crs.getCustomerPhone(),pro.getProjectName());
				baseDao.saveOrUpdate(otherMdc);
			}
		}
	}

	@Override
	public void addorUpdateContractRecordsForSign(User user, Integer recordNo) {
		if (user != null) {
			ContractRecords cr = (ContractRecords) baseDao.loadById(ContractRecords.class, recordNo);
			cr.setRecordStatus(5);
			cr.setContractConfirmTime(DateTime.toString1(new Date()));
			cr.setContractconfirmUseerId(user.getUserId());
			baseDao.saveOrUpdate(cr);
			// 确认到款之后，修改房源状态
			House house = (House) baseDao.loadById(House.class, cr.getHouseNum());
			house.setHouseStatus(0);
			baseDao.saveOrUpdate(house);
			//给签约成功的客户添加动态
			Project p = (Project) baseDao.loadById(Project.class, cr.getProjectId());//获取项目信息
			String otherMdcStr = "您的客户:"+cr.getCustomerName()+"已成功签约。";
			Mydynamic otherMdc = DynamicUtil.createOneDynamic(user.getUserId(), user.getPhone(), otherMdcStr, 0, DateTime.toString1(new Date()),
					cr.getProjectId(), cr.getHouseNum().toString(),"签约成功","0",cr.getProjectCustomerId(),cr.getCustomerName(),cr.getCustomerPhone(),p.getProjectName());
			baseDao.saveOrUpdate(otherMdc);
		}
	}

	@Override
	public List findContracRecordsBill(User user, String startTime, String endTime) {
		List billList = new ArrayList<>();
		Boolean isDaiKan = false;
		if (user != null) {
			String hql = "from ContractRecords where recordStatus = 5 and contractConfirmTime is not null ";
			if (startTime != null && !startTime.equals("")) {
				hql += " and contractConfirmTime >= '" + startTime + "' ";
			}
			if (endTime != null && !endTime.equals("")) {
				hql += " and contractConfirmTime <= '" + endTime + "'";
			}
			List<ContractRecords> crList = baseDao.findByHql(hql);
			for (ContractRecords crs : crList) {
				User u = (User) baseDao.loadById(User.class, crs.getUserId());
				Set<Role> role = u.getRoleId();
				for (Role r : role) {
					String grHql = null;
					if (r.getRoleName().equals("agent")) {// 如果该订购申请的角色是职业顾问时，该客户可能是带看,如果在带看备案定义中存在该客户则为带看客户
						isDaiKan = true;
						grHql = "from GuideRecords where projectCustomerId = '" + crs.getProjectCustomerId() + "'";
					} else if (r.getRoleName().equals("medi") || r.getRoleName().equals("shopowner")) {// 分销
						grHql = "from GuideRecords where customerPhone = '" + crs.getCustomerPhone()
								+ "' and userId = '" + crs.getUserId() + "'";
					}
					if (grHql != null) {
						GuideRecords gr = (GuideRecords) baseDao.loadObject(grHql);
						if (gr != null) {
							VisitRecords visit = (VisitRecords) baseDao.loadById(VisitRecords.class, gr.getVisitNo());
							ProjectCustomers pc = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class,visit.getCustomerId());
							Project p = (Project) baseDao.loadById(Project.class, crs.getProjectId());
							String sysHql = "from SystemChargeDefinition where projectId = '" + crs.getProjectId()
									+ "'";
							List<SystemChargeDefinition> sysList = baseDao.findByHql(sysHql);
							SystemChargeDefinition sys = null;
							for (SystemChargeDefinition sy : sysList) {
								if (sy.getMinRewarMoney() <= crs.getPrice()
										&& sy.getMaxRewarMoney() >= crs.getPrice()) {
									sys = sy;
								}
							}
							ContractRecordsBillDTO crb = new ContractRecordsBillDTO();
							House h = (House) baseDao.loadById(House.class, crs.getHouseNum());
							ContractRecordsBillDTO billDto = crb.createBillDTO(gr, p, h, crs, isDaiKan, sys);
							billDto.setDaiKanTime(pc.getLastTime());
							billList.add(billDto);
						}
					}
				}
			}

		}
		return billList;
	}

	@Override
	public void addorUpdateContractRecordsForSignEnterPayMoney(User user, Integer recordNo, String enterSign,
			String desc) {
		if (user != null) {
			ContractRecords cr = (ContractRecords) baseDao.loadById(ContractRecords.class, recordNo);
			if (enterSign.equals("mediPay")) {
				cr.setIsShopPayConfirm(1);
				cr.setShopPayConfirmTime(DateTime.toString1(new Date()));
				cr.setShopPayConfirmUserId(user.getUserId());
				cr.setShopPayConfirmDesc(desc);
			} else if (enterSign.equals("systemPay")) {
				cr.setIsSystemPayConfirm(1);
				cr.setSystemPayConfirmTime(DateTime.toString1(new Date()));
				cr.setSystemPayConfirmUserId(user.getUserId());
				cr.setSystemPayConfirmDesc(desc);
			}
			baseDao.saveOrUpdate(cr);
		}
	}

	@Override
	public void addorUpdateContractRecordsForSignCancelPayMoney(User user, Integer recordNo, String cancelSign,
			String desc) {
		if (user != null) {
			ContractRecords cr = (ContractRecords) baseDao.loadById(ContractRecords.class, recordNo);
			if (cancelSign.equals("mediCancel")) {
				cr.setIsShopPayConfirm(0);
				// cr.setShopPayConfirmTime(DateTime.toString1(new Date()));
				// cr.setShopPayConfirmUserId(user.getUserId());
				cr.setCancelShopPayConfirmDesc(
						user.getUserCaption() + ",于" + DateTime.toString1(new Date()) + "取消了结款，原因：" + desc);
			} else if (cancelSign.equals("systemCancel")) {
				cr.setIsSystemPayConfirm(0);
				// cr.setSystemPayConfirmTime(DateTime.toString1(new Date()));
				// cr.setSystemPayConfirmUserId(user.getUserId());
				cr.setCancelSystemPayConfirmDesc(
						user.getUserCaption() + ",于" + DateTime.toString1(new Date()) + "取消了结款，原因：" + desc);
			}
			baseDao.saveOrUpdate(cr);
		}
	}

	@Override
	public void findShoperContracRecordsBill(User user, String startTime, String endTime, Page page, String projectId) {
		List list = new ArrayList<>();
		int total = 0;
		Boolean isDaiKan = false;
		if (user != null) {
			String hql = "from ContractRecords where recordStatus = 5 and contractConfirmTime is not null ";
			if (startTime != null && !"".equals(startTime)) {
				hql += " and contractConfirmTime >= '" + startTime + "' ";
			}
			if (endTime != null && !"".equals(endTime)) {
				hql += " and contractConfirmTime <= '" + endTime + "'";
			}
			if(!StringUtils.isEmpty(projectId)){
				hql += " and projectId = '" + projectId + "'";
			}
				
			hql += " order by projectId";
			List<ContractRecords> crList = baseDao.findByHql(hql,page.getStart(),page.getLimit());
			for (ContractRecords crs : crList) {
				User u = (User) baseDao.loadById(User.class, crs.getUserId());
				if (u.getParentId().equals(user.getParentId())) {// 判断是否为该门店的中介
					Set<Role> role = u.getRoleId();
					for (Role r : role) {
						String grHql = null;
						if (r.getRoleName().equals("agent")) {// 可能是带看,如果在带看备案定义中存在该客户则为带看客户
							isDaiKan = true;
							grHql = "from GuideRecords where projectCustomerId = '" + crs.getProjectCustomerId() + "'";
						} else if (r.getRoleName().equals("medi") || r.getRoleName().equals("shopowner")) {// 分销
							grHql = "from GuideRecords where projectCustomerId = '" + crs.getProjectCustomerId()
									+ "' and userId = '" + crs.getUserId() + "'";
						}
						if (grHql != null) {
							GuideRecords gr = (GuideRecords) baseDao.loadObject(grHql);
							if (gr != null) {
								ProjectCustomers pc = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class,
										crs.getProjectCustomerId());
								Project p = (Project) baseDao.loadById(Project.class, crs.getProjectId());
								ContractRecordsBillDTO crb = new ContractRecordsBillDTO();
								House h = (House) baseDao.loadById(House.class, crs.getHouseNum());
								ContractRecordsBillDTO billDto = crb.createShoperBillDTO(gr, p, h, crs, isDaiKan);
								billDto.setDaiKanTime(pc.getLastTime());
								billDto.setMediName(u.getUserCaption());
								list.add(billDto);
							}
						}
					}
				}
			}
			String countHql = "select count(*) " + hql;
			total = baseDao.countQuery(countHql);
		}
		page.setRoot(list);
		page.setTotal(total);
	}

	@Override
	public void addorUpdateContractRecordsForShoperEnterReceiveMoney(User user, Integer recordNo, String desc,
			String doSingle) {
		if (user != null) {
			ContractRecords cr = (ContractRecords) baseDao.loadById(ContractRecords.class, recordNo);
			if (doSingle.equals("enterReceive")) {
				cr.setIsShopPayConfirm(2);
				cr.setShopReceiveConfirmTime(DateTime.toString1(new Date()));
				cr.setShopReceiveConfirmUserId(user.getUserId());

			} else if (doSingle.equals("cancelReceive")) {
				cr.setIsShopPayConfirm(1);
				cr.setShopCancelReceiveConfirmDesc(desc);
			}
			baseDao.saveOrUpdate(cr);
		}
	}

	@Override
	public void findAllMediRelativeDataInfo(User user, String startTime, String endTime, Page page, String projectId) {
		List list = new ArrayList<>();
		int total = 0;
		if (user != null && user.getParentId() != null && !user.getParentId().equals("")) {
			String uHql = "from User where parentId =" + Integer.parseInt(user.getParentId());
			List<User> uList = baseDao.findByHql(uHql,page.getStart(),page.getLimit());// 获取当前门店下所有中介信息
			for (User u : uList) {
				int notVisitOutValidCustomer = 0;
				String grCountHql = "select count(*) from GuideRecords where userId = '" + u.getUserId()
						+ "' and (applyStatus = 0 or applyStatus = 1) ";
				String enterCountHql = "select count(*) from GuideRecords where userId = '" + u.getUserId()
						+ "' and applyStatus = 1 ";
				String notVisitCountHql = "from GuideRecords where userId = '" + u.getUserId()
						+ "' and applyStatus = 0 ";
				String outValidTimeCountHql = "select count(*) from GuideRecords where userId = '" + u.getUserId()
						+ "' and applyStatus = 4 ";
				String enterBuyApplyCountHql = "select count(*) from ContractRecords where userId = '" + u.getUserId()
						+ "' and recordStatus = 0 ";
				String enterBuyEnterApplyCountHql = "select count(*) from ContractRecords where userId = '"
						+ u.getUserId() + "' and recordStatus = 1 ";
				String signFinishCountHql = "select count(*) from ContractRecords where userId = '" + u.getUserId()
						+ "' and recordStatus = 5 ";
				String payMoneyCountHql = "select count(*) from ContractRecords where userId = '" + u.getUserId()
						+ "' and recordStatus = 5 and isShopPayConfirm = 1";
				String receiveMoneyCountHql = "select count(*) from ContractRecords where userId = '" + u.getUserId()
						+ "' and recordStatus = 5 and isShopPayConfirm = 2";
				if (startTime != null && !startTime.equals("")) {
					grCountHql += " and applyTime >= '" + startTime + "' ";
					enterCountHql += " and applyTime >= '" + startTime + "' ";
					notVisitCountHql += " and applyTime >= '" + startTime + "' ";
					outValidTimeCountHql += " and applyTime >= '" + startTime + "' ";
					enterBuyApplyCountHql += " and applyTime >= '" + startTime + "' ";
					enterBuyEnterApplyCountHql += " and auditingTime >= '" + startTime + "' ";
					signFinishCountHql += " and contractConfirmTime >= '" + startTime + "' ";
					payMoneyCountHql += " and shopPayConfirmTime >= '" + startTime + "' ";
					receiveMoneyCountHql += " and shopReceiveConfirmTime >= '" + startTime + "' ";
				}
				if (endTime != null && !endTime.equals("")) {
					grCountHql += " and applyTime <= '" + endTime + "'";
					enterCountHql += " and applyTime <= '" + endTime + "'";
					notVisitCountHql += " and applyTime <= '" + endTime + "' ";
					outValidTimeCountHql += " and applyTime <= '" + endTime + "' ";
					enterBuyApplyCountHql += " and applyTime <= '" + endTime + "' ";
					enterBuyEnterApplyCountHql += " and auditingTime <= '" + endTime + "' ";
					signFinishCountHql += " and contractConfirmTime <= '" + endTime + "' ";
					payMoneyCountHql += " and shopPayConfirmTime <= '" + endTime + "' ";
					receiveMoneyCountHql += " and shopReceiveConfirmTime <= '" + endTime + "' ";
				}
				if(!StringUtils.isEmpty(projectId)){
					grCountHql += " and projectId = '" + projectId + "'";
					enterCountHql += " and projectId = '" + projectId + "'";
					notVisitCountHql += " and projectId = '" + projectId + "'";
					outValidTimeCountHql += " and projectId = '" + projectId + "'";
					enterBuyApplyCountHql += " and projectId = '" + projectId + "'";
					enterBuyEnterApplyCountHql += " and projectId = '" + projectId + "'";
					signFinishCountHql += " and projectId = '" + projectId + "'";
					payMoneyCountHql += " and projectId = '" + projectId + "'";
					receiveMoneyCountHql += " and projectId = '" + projectId + "'";
				}
				// 备案数
				int guideCount = baseDao.countQuery(grCountHql);
				// 确客数
				int enterCount = baseDao.countQuery(enterCountHql);
				// 备案未到访
				int ontVisitCount = baseDao.findByHql(notVisitCountHql).size();
				// 未到访逾期客户
				List<GuideRecords> notVisitOutValidCustomerList = baseDao.findByHql(notVisitCountHql);
				for (GuideRecords gr : notVisitOutValidCustomerList) {
					String rules = gr.getRules();
					System.out.println(rules);
					JSONObject json = JSONObject.fromObject(rules);
					// HashMap grMap = JSON.parseObject(rules, HashMap.class);
					HashMap grMap = JSON.parseObject(json.toString(), HashMap.class);
					int day = Integer.parseInt(grMap.get("validDays").toString());
					Date date1 = DateUtil.parse(DateTime.getNewDay(DateTime.toString1(new Date()), day));
					Date date2 = DateUtil.parse(gr.getApplyTime().toString());
					long value = DateUtil.getOffsetMinute(date1, date2);
					if (value > 0) {
						notVisitOutValidCustomer += 1;
					}
				}
				// 备案已逾期=到访逾期客户+未到访逾期客户
				int outValidTimeCount = baseDao.countQuery(outValidTimeCountHql) + notVisitOutValidCustomer;
				// 认购申请数
				int enterBuyApplyCount = baseDao.countQuery(enterBuyApplyCountHql);
				// 认购完成数
				int enterBuyEnterApplyCount = baseDao.countQuery(enterBuyEnterApplyCountHql);
				// 签约完成数
				int signFinishCount = baseDao.countQuery(signFinishCountHql);
				// 结佣数
				int payMoneyCount = baseDao.countQuery(payMoneyCountHql);
				// 到款数
				int receiveMoneyCount = baseDao.countQuery(receiveMoneyCountHql);
				MediAboutDataDTO madd = new MediAboutDataDTO();
				madd.createMediAboutDataDTO(u);
				madd.setGuideCount(guideCount);
				madd.setEnterCount(enterCount);
				madd.setOntVisitCount(ontVisitCount);
				madd.setOutValidTimeCount(outValidTimeCount);
				madd.setEnterBuyApplyCount(enterBuyApplyCount);
				madd.setEnterBuyEnterApplyCount(enterBuyEnterApplyCount);
				madd.setSignFinishCount(signFinishCount);
				madd.setPayMoneyCount(payMoneyCount);
				madd.setReceiveMoneyCount(receiveMoneyCount);
				list.add(madd);
				
			}
			String countHql = "select count(*) " + uHql;
			total = baseDao.countQuery(countHql);
		}
		page.setRoot(list);
		page.setTotal(total);
	}

	@Override
	public void findAllProRelativeDataForMediInfo(User user, String startTime, String endTime, Page page, String projectId) {
		
		int total = 0;
		List list = new ArrayList<>();
		if (user != null && user.getParentId() != null && !user.getParentId().equals("")) {
			String userHql = "from User where parentId = " + Integer.parseInt(user.getParentId());
			List<User> uList = baseDao.findByHql(userHql);// 当前门店下的中介
			String proHql = "from Project where 1=1";
			if(!StringUtils.isEmpty(projectId)){
				proHql += " and projectId = '" + projectId + "'";
			}
			List<Project> proList = baseDao.findByHql(proHql,page.getStart(),page.getLimit());// 获取所有项目
			for (Project p : proList) {
				int notVisitValidTimeCurstomer = 0;
				String grHql = "from GuideRecords where projectId = '" + p.getProjectId()
						+ "' and (applyStatus = 0 or applyStatus = 1)";
				String enterHql = "from GuideRecords where projectId = '" + p.getProjectId() + "' and applyStatus = 1";
				String grNotVisitHql = "from GuideRecords where projectId = '" + p.getProjectId()
						+ "' and applyStatus = 0";
				String vistedOutValidHql = "from GuideRecords where projectId = '" + p.getProjectId()
						+ "' and applyStatus = 4 ";

				String enterBuyApplyHql = "from ContractRecords where projectId = '" + p.getProjectId()
						+ "' and recordStatus = 0 ";
				String enterBuyEnterApplyHql = "from ContractRecords where projectId = '" + p.getProjectId()
						+ "' and recordStatus = 1 ";
				String signFinishHql = "from ContractRecords where projectId = '" + p.getProjectId()
						+ "' and recordStatus = 5 ";
				String payMoneyHql = "from ContractRecords where projectId = '" + p.getProjectId()
						+ "' and recordStatus = 5 and isShopPayConfirm = 1";
				String receiveMoneyHql = "from ContractRecords where projectId = '" + p.getProjectId()
						+ "' and recordStatus = 5 and isShopPayConfirm = 2";

				if (startTime != null && !startTime.equals("")) {
					grHql += " and applyTime >= '" + startTime + "' ";
					enterHql += " and applyTime >= '" + startTime + "' ";
					grNotVisitHql += " and applyTime >= '" + startTime + "' ";
					vistedOutValidHql += " and applyTime >= '" + startTime + "' ";
					enterBuyApplyHql += " and applyTime >= '" + startTime + "' ";

					enterBuyEnterApplyHql += " and auditingTime >= '" + startTime + "' ";
					signFinishHql += " and contractConfirmTime >= '" + startTime + "' ";
					payMoneyHql += " and shopPayConfirmTime >= '" + startTime + "' ";
					receiveMoneyHql += " and shopReceiveConfirmTime >= '" + startTime + "' ";
				}
				if (endTime != null && !endTime.equals("")) {
					grHql += " and applyTime <= '" + endTime + "'";
					enterHql += " and applyTime <= '" + endTime + "'";
					grNotVisitHql += " and applyTime <= '" + endTime + "'";
					vistedOutValidHql += " and applyTime <= '" + endTime + "'";
					enterBuyApplyHql += " and applyTime <= '" + endTime + "'";

					enterBuyEnterApplyHql += " and auditingTime <= '" + endTime + "' ";
					signFinishHql += " and contractConfirmTime <= '" + endTime + "' ";
					payMoneyHql += " and shopPayConfirmTime <= '" + endTime + "' ";
					receiveMoneyHql += " and shopReceiveConfirmTime <= '" + endTime + "' ";

				}

				// 备案数
				int grCount = 0;
				List<GuideRecords> grCountList = baseDao.findByHql(grHql);
				for (GuideRecords gr : grCountList) {
					for (User u : uList) {
						if (u.getUserId().equals(gr.getUserId())) {
							grCount += 1;
						}
					}
				}
				// 确客数
				int enterCount = 0;
				List<GuideRecords> enterCountList = baseDao.findByHql(enterHql);
				for (GuideRecords gr : enterCountList) {
					for (User u : uList) {
						if (u.getUserId().equals(gr.getUserId())) {
							enterCount += 1;
						}
					}
				}
				// 备案未到数
				int grNotVisitCount = 0;
				List<GuideRecords> grNotVisitCountList = baseDao.findByHql(grNotVisitHql);
				for (GuideRecords gr : grNotVisitCountList) {
					for (User u : uList) {
						if (u.getUserId().equals(gr.getUserId())) {
							grNotVisitCount += 1;
						}
					}
				}
				// 未到访客户——筛选逾期客户 = 未到访逾期客户
				List<GuideRecords> notVisitOutValidCustomerList = baseDao.findByHql(grNotVisitHql);
				for (GuideRecords gr : notVisitOutValidCustomerList) {
					for (User u : uList) {
						if (u.getUserId().equals(gr.getUserId())) {
							HashMap grMap = JSON.parseObject(gr.getRules(), HashMap.class);
							int day = Integer.parseInt(grMap.get("validDays").toString());
							Date date1 = DateUtil.parse(DateTime.getNewDay(DateTime.toString1(new Date()), day));
							Date date2 = DateUtil.parse(gr.getApplyTime().toString());
							long value = DateUtil.getOffsetMinute(date1, date2);
							if (value > 0) {
								notVisitValidTimeCurstomer += 1; // 未到访逾期客户
							}
						}
					}

				}
				// 备案已逾期数=到访逾期客户+未到访逾期客户
				int outValidTimeCount = 0;
				outValidTimeCount += notVisitValidTimeCurstomer;
				List<GuideRecords> outValidTimeCountList = baseDao.findByHql(vistedOutValidHql);
				for (GuideRecords gr : outValidTimeCountList) {
					for (User u : uList) {
						if (u.getUserId().equals(gr.getUserId())) {
							outValidTimeCount += 1;
						}
					}
				}

				// 认购申请数
				int enterBuyCount = 0;
				List<ContractRecords> crList = baseDao.findByHql(enterBuyApplyHql);
				for (ContractRecords cr : crList) {
					for (User u : uList) {
						if (u.getUserId().equals(cr.getUserId())) {
							enterBuyCount += 1;
						}
					}
				}

				// 认购完成数
				int enterBuyCompleteCount = 0;
				List<ContractRecords> enterBuyCompleteList = baseDao.findByHql(enterBuyEnterApplyHql);
				for (ContractRecords cr : enterBuyCompleteList) {
					for (User u : uList) {
						if (u.getUserId().equals(cr.getUserId())) {
							enterBuyCompleteCount += 1;
						}
					}
				}

				// 签约完成数
				int signCompleteCount = 0;
				List<ContractRecords> signCompleteList = baseDao.findByHql(signFinishHql);
				for (ContractRecords cr : signCompleteList) {
					for (User u : uList) {
						if (u.getUserId().equals(cr.getUserId())) {
							signCompleteCount += 1;
						}
					}
				}

				// 结佣数
				int payMoneyCount = 0;
				List<ContractRecords> payMoneyList = baseDao.findByHql(payMoneyHql);
				for (ContractRecords cr : payMoneyList) {
					for (User u : uList) {
						if (u.getUserId().equals(cr.getUserId())) {
							payMoneyCount += 1;
						}
					}
				}

				// 到款数
				int receiveMoneyCount = 0;
				List<ContractRecords> receiveMoneyList = baseDao.findByHql(receiveMoneyHql);
				for (ContractRecords cr : receiveMoneyList) {
					for (User u : uList) {
						if (u.getUserId().equals(cr.getUserId())) {
							receiveMoneyCount += 1;
						}
					}
				}

				// 结佣平均时间
				int datedistance = 0;
				for (ContractRecords signcr : signCompleteList) {
					for (ContractRecords paycr : payMoneyList) {
						if (signcr.getProjectId().equals(paycr.getProjectId())) {
							String payMoneyTime = paycr.getShopPayConfirmTime();
							String signEnterTime = signcr.getContractConfirmTime();
							//
							if (payMoneyTime != null && !payMoneyTime.equals("")) {
								Date payMoneyDate = DateUtil.parse(payMoneyTime);
								Date signDate = DateUtil.parse(signEnterTime);
								datedistance += DateUtil.getOffsetDays(signDate, payMoneyDate);
							}
						}
					}
				}
				if (signCompleteList.size() > 0) {
					datedistance = datedistance / signCompleteList.size();
				}

				// 认购审核通过率（审核通过数/审核通过数+申请数）
				double checkProgressRate = 0.00;
				if (enterBuyCompleteCount + enterBuyCount > 0) {
					checkProgressRate = (double) enterBuyCompleteCount
							/ (double) (enterBuyCompleteCount + enterBuyCount);
				}

				// 组装dto
				MediAboutDataDTO madd = new MediAboutDataDTO();
				MediAboutDataDTO newMadd = madd.createProAboutMediDataDTO(p);
				newMadd.setGuideCount(grCount);
				newMadd.setEnterCount(enterCount);
				newMadd.setOntVisitCount(grNotVisitCount);
				newMadd.setOutValidTimeCount(outValidTimeCount);
				newMadd.setEnterBuyApplyCount(enterBuyCount);
				newMadd.setEnterBuyEnterApplyCount(enterBuyCompleteCount);
				newMadd.setSignFinishCount(signCompleteCount);
				newMadd.setPayMoneyCount(payMoneyCount);
				newMadd.setReceiveMoneyCount(receiveMoneyCount);
				newMadd.setAverageDates(datedistance);
				newMadd.setCheckProgressRate(checkProgressRate);

				// 将dto加入到返回集合
				list.add(newMadd);

			}

			String countHql = "select count(*) " + proHql;
			total = baseDao.countQuery(countHql);
		}
		page.setRoot(list);
		page.setTotal(total);
	}

	@Override
	public List findAllProjectsNameByCity(String city) {

		List list = new ArrayList();
		String sHql = "from Project where city = '" + city + "'";
		List<Project> all = baseDao.findByHql(sHql);
		for (Project porject : all) {
			Map map = new HashMap();
			map.put("projectId", porject.getProjectId());
			map.put("projectName", porject.getProjectName());
			list.add(map);
		}
		return list;
	}

	/* 
	 *  
	 */
	@Override
	public void addOrUpdateFirstProject(Project project, User u) {
		String pId = SysContent.uuid();
		project.setProjectId(pId);
		project.setProInSystemStutas(1);
		if (u != null) {
			String phone = u.getPhone();
			User user = (User) baseDao.loadObject("from User where phone= '" + phone + "'");
			if (user == null) {
				// 设置案场助理新用户
				u.setUserId(SysContent.uuid());
				u.setParentId(pId);
				Role role = (Role) baseDao.loadById(Role.class, 4);
				Set roles = new HashSet();
				roles.add(role);
				u.setRoleId(roles);
				// 为新用户设置密码 电话号码后6为
				String password = phone.substring(5);
				//密码加密
				String pwd = SysContent.md5(password);
				u.setPassword(pwd);
				u.setUserStatus(1);
				u.setCreateTime(DateUtil.format(new Date()));
				baseDao.save(project);
				baseDao.save(u);
				//给案场添加标准标签库
				tagService.add_copyTagLib(pId);
			}
		}

	}

	@Override
	public List<Project> findAllBySystem() {
		List<Project> listAll = baseDao.listAll("Project");
		return listAll;
	}

	@Override
	public void addPlatform(SystemChargeDefinition s) {
		String hql = "from SystemChargeDefinition where projectId ='"+s.getProjectId()+"'";
		SystemChargeDefinition scd = (SystemChargeDefinition) baseDao.loadObject(hql);
		if(scd != null){
			scd.setRewardType(s.getRewardType());
			scd.setReward(s.getReward());
			scd.setMaxRewarMoney(s.getMaxRewarMoney());
			scd.setMinRewarMoney(s.getMinRewarMoney());
			
		}else{
			scd = s;
			
		}
		baseDao.saveOrUpdate(scd);
	}

	@Override
	public List selectAllPlatform() {
		// SystemChargeDefinition scd = (SystemChargeDefinition)
		// baseDao.loadById(SystemChargeDefinition.class, projectId);
		List<SystemChargeDefinition> list = baseDao.findByHql("from SystemChargeDefinition");
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (SystemChargeDefinition s : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("projectId", s.getProjectId());
			map.put("rewardType", s.getRewardType());
			map.put("reward", s.getReward());
			map.put("minRewarMoney", s.getMinRewarMoney());
			map.put("MaxRewarMoney", s.getMaxRewarMoney());
			mapList.add(map);
		}
		return mapList;
	}
	
	@Override
	public String findCityNameByCityNum(String cityNum) {
		String[] cityArray = cityNum.split("-");
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<cityArray.length; i++){
			if(cityArray[i] != null && !cityArray[i].equals("")){
				String hql = "from CountryProvinceInfo where cityId = '" + cityArray[i] + "'";
				List<CountryProvinceInfo> list = baseDao.findByHql(hql);
				String cityName = list.get(0).getCityName();
				sb.append(cityName);
			}
		}
		return sb.toString();
	}

	@Override
	public List<ShopAndUserNameDTO> findShopAndUserNameDTO(List<Shops> shopList) {

		List<ShopAndUserNameDTO> shopDtoList = new ArrayList<ShopAndUserNameDTO>();
		for (Shops s : shopList) {
			if(s.getShopStatus() != 2){
				ShopAndUserNameDTO shopDto = new ShopAndUserNameDTO();
				shopDto.setShopId(s.getShopId());
				shopDto.setCompanyName(s.getCompanyName());
				shopDto.setShopName(s.getShopName());
				if(s.getCity() != null && !s.getCity().equals("")){
					String cityName = findCityNameByCityNum(s.getCity());
					shopDto.setAddress(cityName+s.getAddress());
				}
				shopDto.setContactPerson(s.getContactPerson());
				shopDto.setPhone(s.getPhone());
				shopDto.setApplyTime(s.getApplyTime());
				shopDto.setShopStatus(s.getShopStatus());
				if (s.getApproveUserId() != null) {
					
					User u = (User) baseDao.loadById(User.class, s.getApproveUserId());
					if (u != null)
						shopDto.setUserCaption(u.getUserCaption());
				}
				shopDto.setApproveTime(s.getApproveTime());
				
				shopDtoList.add(shopDto);
			}
		}
		return shopDtoList;
	}

	@Override
	public void findAllReviewedShops(Page page, Integer applyId) {

		Map<String, String> shopMap = new HashMap<>();
		List<ShopAndUserNameDTO> shopDtoList = null;
		if (applyId == null) {
			String hql = "from Shops where shopStatus != 2";
			List<Shops> Project = baseDao.findByHql(hql, page.getStart(), page.getLimit());
			List<ShopAndUserNameDTO> allProject = findShopAndUserNameDTO(Project);
			String cHql = "select count(*) " + hql;
			int total = baseDao.countQuery(cHql);
			page.setRoot(allProject);
			page.setTotal(total);
		} else if (applyId == 0) { // 当applyId为0的时候表示“只显示审核中的申请”
			String hql = "from Shops where shopStatus = 0";
			List<Shops> Project = baseDao.findByHql(hql, page.getStart(), page.getLimit());
			List<ShopAndUserNameDTO> allProject = findShopAndUserNameDTO(Project);
			String cHql = "select count(*) " + hql;
			int total = baseDao.countQuery(cHql);
			page.setRoot(allProject);
			page.setTotal(total);
		} else if (applyId == 1) { // 当applyId为1的时候表示“只显示已经通过的申请”
			String hql = "from Shops where shopStatus = 1";
			List<Shops> Project = baseDao.findByHql(hql, page.getStart(), page.getLimit());
			List<ShopAndUserNameDTO> allProject = findShopAndUserNameDTO(Project);
			String cHql = "select count(*) " + hql;
			int total = baseDao.countQuery(cHql);
			page.setRoot(allProject);
			page.setTotal(total);
		} else if (applyId == 3) { // 当applyId为3的时候表示“只显示已经拒绝的申请”
			String hql = "from Shops where shopStatus = 3";
			List<Shops> Project = baseDao.findByHql(hql, page.getStart(), page.getLimit());
			List<ShopAndUserNameDTO> allProject = findShopAndUserNameDTO(Project);
			String cHql = "select count(*) " + hql;
			int total = baseDao.countQuery(cHql);
			page.setRoot(allProject);
			page.setTotal(total);
		}
	}

	@Override
	public Shops findReviewdShopById(Integer shopId) {

		if (shopId != null) {

			Shops shops = new Shops();
			Shops shop = (Shops) baseDao.loadById(Shops.class, shopId);
			return shop;
		} else {
			return null;
		}
	}

	@Override
	public String addReviewShopById(User u, Shops s, Integer tag) {

		if (s != null) {
			if (tag == 0) { // tag等于零代表"拒绝申请"
				Shops shop = (Shops) baseDao.loadById(Shops.class, s.getShopId());
				// shop.setApproveUserId(u.getUserId());
				// 添加审核意见,如果拒绝申请
				if (s.getAuditOpinion() != null && !s.getAuditOpinion().equals("")) {
					shop.setAuditOpinion(s.getAuditOpinion());
				}
				shop.setApproveUserId(u.getUserId());
				shop.setApproveTime(DateUtil.format(new Date()));// 审核时间
				shop.setShopStatus(3);
				baseDao.save(shop);
				return "failure";
			} else { // 否则就是同意申请

				Shops shop = (Shops) baseDao.loadById(Shops.class, s.getShopId());
				List findByHql = baseDao.findByHql("from Role where id=2");//门店店长
				List<User> user = baseDao.findByHql("from User where parentId = '" + s.getShopId() + "'");
				shop.setAuditOpinion(s.getAuditOpinion());
				shop.setApproveUserId(u.getUserId());
				shop.setApproveTime(DateUtil.format(new Date()));// 审核时间
				shop.setShopStatus(1);
				shop.setInSystemStutas(1);
				baseDao.save(shop);
				for (User us : user) {

					if (findByHql != null && findByHql.size() > 0) {
						Role role = (Role) findByHql.get(0);
						Set rSet = new HashSet();
						rSet.add(role);
						us.setRoleId(rSet);// 角色定义为案场助理
					}
					us.setUserStatus(1);// 状态为正常
					baseDao.save(us);
				}
				return "success";
			}
		} else {
			return "not info";
		}
	}
	
	@Override
	public void dropShopByShopId(Integer shopId){
		Shops shop = (Shops) baseDao.loadById(Shops.class, shopId);
		//状态设置为删除
		shop.setShopStatus(2);
		baseDao.saveOrUpdate(shop);
	}

	@Override
	public UserDTO findUserById(String userId) {
		User u = (User) baseDao.loadById(User.class, userId);
		UserDTO uDto = new UserDTO();
		UserDTO userDto = uDto.creatUserDTO(u);
		return userDto;
	}

	@Override
	public void findCusForShopBusiness(User user, String cusOrProName, String startTime, String endTime,
			String status, Page page) {
		
		int total = 0;
		List dtoList = new ArrayList<>();
		if (user != null) {
			String shopCusHql = "from ShopCustomers where 1=1 ";
			String guidRecordHql = "from GuideRecords where 1=1 ";
			if (cusOrProName != null && !cusOrProName.equals("")) {
				shopCusHql += " and shopCustomerName like '%" + cusOrProName + "%'";
			}
			if (startTime != null && !startTime.equals("")) {
				guidRecordHql += " and applyTime >= '" + startTime + "'";
			}
			if (endTime != null && !endTime.equals("")) {
				guidRecordHql += " and applyTime <= '" + endTime + "'";
			}
			if (status != null && !status.equals("")) {
				guidRecordHql += " and applyStatus =" + Integer.parseInt(status);
			}
			List<GuideRecords> grList = baseDao.findByHql(guidRecordHql);
			List<ShopCustomers> scList = baseDao.findByHql(shopCusHql);
			for (ShopCustomers sc : scList) {
				for (GuideRecords gr : grList) {
					Project p = (Project) baseDao.loadById(Project.class, gr.getProjectId());
					if (p!=null && sc.getShopCustomerId().equals(gr.getShopCustomerId())) {
						if (gr.getApplyStatus().equals(0)) {// 已备案
							MyBusinessDTO mbd = new MyBusinessDTO();
							MyBusinessDTO newMbd = mbd.creatShoperBusinessDto(sc, gr, p);
							dtoList.add(newMbd);
						} else if (gr.getApplyStatus().equals(1)) {
							String crHql = "from ContractRecords where projectCustomerId = '"
									+ gr.getProjectCustomerId() + "' and recordStatus = 5";
							ContractRecords crObj = (ContractRecords) baseDao.loadObject(crHql);
							MyBusinessDTO mbd = new MyBusinessDTO();
							MyBusinessDTO newMbd = mbd.creatShoperBusinessDto(sc, gr, p);
							if (crObj != null) {
								newMbd.setCurrentStatus("已成交");
							}
							dtoList.add(newMbd);
						}
					}
				}
			}
			total = dtoList.size();
			
		}
		int maxCount = page.getStart() + page.getLimit();
		if(maxCount >= total){
			maxCount = total;
		}
		page.setRoot(dtoList.subList(page.getStart(), maxCount));
		page.setTotal(total);
	}

	@Override
	public List<ContractRecords> findConRecListByHql(String conrecHql) {
		if(conrecHql!=null){
			return baseDao.findByHql(conrecHql);
		}
		return null;
	}
	
	public void updateConRec(ContractRecords crs){
		baseDao.saveOrUpdate(crs);
	}
	
	@Override
	public List<ContractRecords> findThisMonthShopownerRecord(User user) {
		List<ContractRecords> thisMonthAgentRecordList = new ArrayList<>();
//		List<String> uList = new ArrayList<>();
		//本月第一天
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.PATTERN_CLASSICAL);
		String thisMonthStartDay = sdf.format(DateUtil.getMonthStartTime());
		//当月所有认购信息
		String recordHql = "from ContractRecords where applyTime >= '"+ thisMonthStartDay +"'";
		List<ContractRecords> allRecords= baseDao.findByHql(recordHql);
		/*user属于店长和中介经纪人权限的属于分销*/
		//找出当前门店下的所有中介和店长
		Shops shop = (Shops) baseDao.loadById(Shops.class, Integer.parseInt(user.getParentId()));
		String userHql = "from User where parentId = '" + shop.getShopId() + "'";
		List<User> uList = baseDao.findByHql(userHql);
		/*//中介经纪人角色
		
		Role medi = (Role) baseDao.loadById(Role.class, 1);
		for(User u : userList){
			Set<Role> role = u.getRoleId();
			for(Role r : role){
				if(r.getRoleId() == medi.getRoleId()){
					uList.add(u.getUserId());
				}
			}
		}
		//店长角色
		Role shoper = (Role) baseDao.loadById(Role.class, 2);
		for(User u : userList){
			Set<Role> role = u.getRoleId();
			for(Role r : role){
				if(r.getRoleId() == shoper.getRoleId()){
					uList.add(u.getUserId());
				}
			}
		}*/
		//认购表中为中介经纪人和店长发起的
		for(ContractRecords cr : allRecords){
			for(User s : uList){
				if(s.getUserId().equals(cr.getUserId())){
					thisMonthAgentRecordList.add(cr);
				}
			}
		}
		
		return thisMonthAgentRecordList;
	}
	
	@Override
	public List<ContractRecords> findThisMonthAgentDistribute(User user) {
		List<ContractRecords> distributeList = new ArrayList<>();
//		List<String> uList = new ArrayList<>();
		//本月第一天
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.PATTERN_CLASSICAL);
		String thisMonthStartDay = sdf.format(DateUtil.getMonthStartTime());
		//当月所有的带看备案记录信息
		String guideHql = "from GuideRecords where applyTime >= '" + thisMonthStartDay + "'";
		List<GuideRecords> allGuide = baseDao.findByHql(guideHql);
		//当月所有认购信息
		String recordHql = "from ContractRecords where contractConfirmTime >= '"+ thisMonthStartDay.toString() +"'";
		List<ContractRecords> allRecords= baseDao.findByHql(recordHql);
		//当前门店下的所有User
		Shops shop = (Shops) baseDao.loadById(Shops.class, Integer.parseInt(user.getParentId()));
		String userHql = "from User where parentId = '" + shop.getShopId() + "'";
		List<User> uList = baseDao.findByHql(userHql);
		//置业顾问角色
//		Role agent = (Role) baseDao.loadById(Role.class, 3);
//		for(User u : uList){
//			Set<Role> role = u.getRoleId();
//			for(Role r : role){
//				if(r.getRoleId() == agent.getRoleId()){
//					uList.add(u.getUserId());
//				}
//			}
//		}
		//属于当前门店的带看备案记录
		List<GuideRecords> agentGuideList = new ArrayList<>();
		for(GuideRecords gr : allGuide){
			for(User u : uList){
				if(u.getUserId().equals(gr.getUserId())){
					agentGuideList.add(gr);
				}
			}
		}
		//属于当前门店的认购记录
		List<ContractRecords> agentContractList = new ArrayList<>();
		for(ContractRecords cr : allRecords){
			for(User u : uList){
				if(u.getUserId().equals(cr.getUserId())){
					agentContractList.add(cr);
				}
			}
			for(GuideRecords gr : agentGuideList){		//如果认购记录和带看记录的破解rojectCustomerId相等说明是带看记录
				if(cr.getProjectCustomerId().equals(gr.getProjectCustomerId())){
					distributeList.add(cr);
				}
			}
		}
		
		return distributeList;
	}
	
	@Override
	public List<ContractRecords> findThisMonthAllMediInfo(User user) {
		List<ContractRecords> mediList = new ArrayList<>();
		//分销认购信息
		List<ContractRecords> agentRecordList = findThisMonthShopownerRecord(user);
		for(ContractRecords cr : agentRecordList){
			if(cr != null){
				mediList.add(cr);
			}
		}
		//带看认购信息
		List<ContractRecords> distributeList = findThisMonthAgentDistribute(user);
		for(ContractRecords cr : distributeList){
			if(cr != null){
				mediList.add(cr);
			}
		}
		return mediList;
	}
	
	@Override
	public List<ContractRecords> findThisMonthApplyAndAgreeContract(User user) {
		List<ContractRecords> list = new ArrayList<>();
		List<ContractRecords> mediList = findThisMonthAllMediInfo(user);
		for(ContractRecords cr : mediList){
			//0 ： 申请中的认购   1：同意的认购
			if(cr.getRecordStatus() == 0 || cr.getRecordStatus() == 1){
				list.add(cr);
			}
		}
		return list;
	}
	
	@Override
	public List<ContractRecords> findThisMonthSignedInfo(User user) {
		List<ContractRecords> list = new ArrayList<>();
		List<ContractRecords> mediList = findThisMonthAllMediInfo(user);
		for(ContractRecords cr : mediList){
			if(cr.getRecordStatus() == 5){		//状态为：5 表示签约
				list.add(cr);
			}
		}
		return list;
	}
	
	@Override
	public List<ProjectCustomers> findNewCustomerInThisMonth(User user) {
		List<ProjectCustomers> list = new ArrayList<>();
		//本月第一天
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.PATTERN_CLASSICAL);
		String thisMonthStartDay = sdf.format(DateUtil.getMonthStartTime());
		//属于当前shop的新客户
		if(user != null){
			Shops shop = (Shops) baseDao.loadById(Shops.class, Integer.parseInt(user.getParentId()));
			String userHql = "from User where parentId = '" + shop.getShopId() + "'";
			List<User> uList = baseDao.findByHql(userHql);
			//查找所有本月新增客户
			String newCustomerHql = "from ProjectCustomers where createTime >= '" + thisMonthStartDay + "'";
			List<ProjectCustomers> newCustomerList = baseDao.findByHql(newCustomerHql);
			for(ProjectCustomers pc : newCustomerList){
				for(User u : uList){
					if(u.getUserId().equals(pc.getCreateUserId())){		//遍历是当前shop新增的客户
						list.add(pc);
					}
				}
			}
			
		}
		return list;
	}
	
	@Override
	public List<GuideRecords> findGuideRecordInThieMonth(User user) {
		List<GuideRecords> list = new ArrayList<>();
		//本月第一天
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.PATTERN_CLASSICAL);
		String thisMonthStartDay = sdf.format(DateUtil.getMonthStartTime());
		if(user != null){
			Shops shop = (Shops) baseDao.loadById(Shops.class, Integer.parseInt(user.getParentId()));
			String userHql = "from User where parentId = '" + shop.getShopId() + "'";
			List<User> uList = baseDao.findByHql(userHql);
			//查找本月中介备案
			String recordHql = "from GuideRecords where applyTime >= '" + thisMonthStartDay + "' and applyStatus != 3";
			List<GuideRecords> recordList = baseDao.findByHql(recordHql);
			for(GuideRecords gr : recordList){
				for(User u : uList){
					if(u.getUserId().equals(gr.getUserId())){
						list.add(gr);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 认筹客户 list
	 */
	@Override
	public List findConfessOfCurrentUser(User user) {
		List cusDtoList = new ArrayList<>();
		if(user!=null){
			String confHql = "from Confess where projectId = '"+user.getParentId()+"'";
			List<Confess> confList = baseDao.findByHql(confHql);
			for(Confess con : confList){
				ProjectCustomers proCus = (ProjectCustomers) baseDao.loadById(ProjectCustomers.class, con.getProCustomerId());
				ProCustomerDTO proCusDto = new ProCustomerDTO();
				proCusDto = proCusDto.creatProCusDTO(proCus);
				cusDtoList.add(proCusDto);
			}
		}
		return cusDtoList;
	}

	@Override
	public List<MiniMessageWaitSend> findMiniMessageWaitSendByHQL() {
		String hql = "from MiniMessageWaitSend where isSendMiniMessage = 'no'";
		return baseDao.findByHql(hql);
	}
	
	@Override
	public void addOrupdateMessageSendStatus(MiniMessageWaitSend mmws) {
		baseDao.saveOrUpdate(mmws);
	}

	@Override
	public MiniMessageWaitSend findMessageById(Integer id) {
		if(id!=null){
			return (MiniMessageWaitSend) baseDao.loadById(MiniMessageWaitSend.class, id);
		}else{
			return null;
		}
	}

	@Override
	public Map basicInfoIsFull(User user) {
		String enterBuyHql = "from EnterBuy where projectId = '" + user.getParentId() + "'";
		EnterBuy eb = (EnterBuy) baseDao.loadObject(enterBuyHql);
		Map map = new HashMap<>();
		if(eb!=null){
			map.put("returnCode", "200");
			map.put("url", "to_trade_business_page");
		}else{
			map.put("returnCode", "201");
			map.put("url", "to_buyRule");
		}
		return map;
	}

	@Override
	public void addorUpdateMyDynamic(Mydynamic outTimeDynamic) {
		baseDao.saveOrUpdate(outTimeDynamic);
	}

	@Override
	public Map findAllProAndShopInfo(String pcq) {
		Map map = new HashMap<>();
		String proHql = "from Project where 1=1";
		String shopHql = "from Shops where 1=1";
		if(pcq!=null){
			proHql += " and city like '%"+pcq+"%'";
			shopHql += " and city like '%"+pcq+"%'";
		}
		String countHql = "select count(*) " + shopHql +" and inSystemStutas = 0";
		List listPro = baseDao.findByHql(proHql);
		List listShop = baseDao.findByHql(shopHql);
		int noInCount = baseDao.countQuery(countHql);
		map.put("pro", listPro);
		map.put("shop", listShop);
		map.put("noInCount", noInCount);
		return map;
	}

	//判断当前预售证是否被使用
	@Override
	public List<Map> findCurrentIdManageBeenUsed(List<Map<String, String>> urlList) {
		List<Map> list = new ArrayList<>();
		if(!urlList.isEmpty()){
			for(Map<String,String> m : urlList){
				Map map = new HashMap<>();
				String imNum = m.get("imNum");
				String isUsedHql = "from House where presalePermissionInfo = '"+imNum+"'";
				House h = (House) baseDao.loadObject(isUsedHql);
				if(h!=null){
					map.put("isUsed", "yes");
				}else{
					map.put("isUsed", "no");
				}
				map.put("picMap", m);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, String>> findAllProjectForMenu() {
		List<Map<String,String>> menu = new ArrayList<>();
		List<Project> proList = baseDao.findByHql("from Project where 1=1");
		for(Project p : proList){
			Map<String,String> map = new HashMap<>();
			map.put("proId", p.getProjectId());
			map.put("proName", p.getProjectName());
			menu.add(map);
		}
		return menu;
	}

}
