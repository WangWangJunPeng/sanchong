package com.sc.tradmaster.service.projectPics.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sc.tradmaster.bean.ProjectPics;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.projectPics.ProjectPicsService;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.PropertiesUtil;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

/**
 * 
 * @author grl 2017-01-15
 *
 */
@Service("projectPicsService")
public class ProjectPicsServiceImpl implements ProjectPicsService {
	
	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	@Override
	public void addOrUpdataProPics(ProjectPics proPic,String[] urlArray, User u,MultipartFile[] pics) throws Exception {
		List<String> urlList = new ArrayList<>();
		//判断id存在则更新
		if(proPic.getPhotoId()!=null && !proPic.getPhotoId().equals("")){
			ProjectPics haveProPic = (ProjectPics) baseDao.loadById(ProjectPics.class, proPic.getPhotoId());
			haveProPic.setCaption(proPic.getCaption());
			haveProPic.setDescription(proPic.getDescription());
			//urlList = JSON.parseArray(urlStr,String.class);
			if(urlArray.length>0){
				for(int i=0;i<urlArray.length;i++){
					urlList.add(urlArray[i]);
				}
			}
			//haveProPic.setUrl(urlList.toString());
			proPic = haveProPic;
		}else{
			//为新增数据设置id
			proPic.setPhotoId(SysContent.uuid());
			//添加项目id
			proPic.setProjectId(u.getParentId());
		}
		//遍历上传图片数组
		if(pics!=null && pics.length>0){
			// 获取上传路径
			//String sepa = File.separator;
			//String realPath = SysContent.getSession().getServletContext().getRealPath("/static/upload");
			//便利数组
			for(MultipartFile pic:pics){
				if(!pic.isEmpty()){
					/*String rpn = realPath + sepa + rename;
					System.out.println(rpn);
					String savePath = rpn.substring(rpn.indexOf("static"));
					String reSavePath = savePath.replace(sepa, "/");
					File file = new File(rpn);
					FileUtils.copyInputStreamToFile(pic.getInputStream(), file);*/
					String rename = SysContent.getFileRename(pic.getOriginalFilename());
					String reSavePath = PicUploadToYun.upload(rename, pic,SmsContext.EF_PIC);
					urlList.add(reSavePath.trim());
				}
			}
			//把图片路径赋值给案场照片集对象
			proPic.setUrl(urlList.toString());
		}
		baseDao.saveOrUpdate(proPic);
	}

	@Override
	public ProjectPics findById(String ppId) {
		if(ppId!=null && !ppId.equals("")){
			return (ProjectPics) baseDao.loadById(ProjectPics.class, ppId);
		}else{
			return null;
		}
	}

	@Override
	public List<ProjectPics> findAllProPics(User u) {
		String hql = "from ProjectPics where projectId = '" + u.getParentId() +"'";
		return baseDao.findByHql(hql);
	}

	@Override
	public void dropProPics(String ppId) {
		if(ppId!=null && !ppId.equals("")){
			baseDao.deleteById(ProjectPics.class, ppId);
		}
	}

	@Override
	public List findPicsUrlList(String proPicsId) {
		List urlList = new ArrayList<>();
		if(proPicsId!=null && !proPicsId.equals("")){
			ProjectPics pps = (ProjectPics) baseDao.loadById(ProjectPics.class, proPicsId);
			if(pps!=null){
				//获取url字符串		"[adfasdf,asdfasdf,asdfasdf]"
				String picUrl = pps.getUrl();
				//去掉url字符串中的前后括号
				picUrl = picUrl.substring(1, picUrl.length()-1);
				//判断字符串是否需要拆分成数组
				if(picUrl.indexOf(",")!=-1){
					String[] picUrlStr = picUrl.split(",");
					urlList = Arrays.asList(picUrlStr);
				}else{
					urlList.add(picUrl);
				}
			}
		}
		return urlList;
	}

}
