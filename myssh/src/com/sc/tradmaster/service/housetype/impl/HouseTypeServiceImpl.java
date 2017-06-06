package com.sc.tradmaster.service.housetype.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.housetype.HouseTypeService;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.PropertiesUtil;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

/**
 * 
 * @author grl 2017-01-14
 *
 */
@Service("houseTypeService")
public class HouseTypeServiceImpl implements HouseTypeService {

	@Resource(name = "baseDao")
	private BaseDao baseDao;

	@Override
	public void addOrupdateHouseType(HouseType ht, User u, MultipartFile pic) throws Exception {
		HouseType upHT = null;
		// 判断该户型id是否存在，若不存在就新增户型，若存在就更新当前户型信息
		if (ht.getHouseTypeId() == null || ht.getHouseTypeId() == "") {
			ht.setHouseTypeId(SysContent.uuid());
		}
		if (!pic.isEmpty() && pic.getSize() > 0) {
			/*String sepa = File.separator;
			ServletContext session = SysContent.getSession().getServletContext();
			sepa + "static" + sepa + 
			String realPath = SysContent.getSession().getServletContext().getRealPath("/static/upload");
			System.out.println(realPath);
			String rpn = realPath + sepa + rename;
			File file = new File(rpn);
			FileUtils.copyInputStreamToFile(pic.getInputStream(), file);*/
			String rename = SysContent.getFileRename(pic.getOriginalFilename());
			String savePath = PicUploadToYun.upload(rename, pic,SmsContext.HT_PIC);
			ht.setPhotoURL(savePath);
		}else{
			if(upHT!=null){
				ht.setPhotoURL(upHT.getPhotoURL());
			}
		}
		ht.setProjectId(u.getParentId());
		//户型默认未启用
		ht.setIsUse("no");
		baseDao.saveOrUpdate(ht);
	}

	@Override
	public void dropHouseType(String id) {
		if (id != null && !id.equals("")) {
			baseDao.deleteById(HouseType.class, id);
		}
	}

	@Override
	public HouseType findById(String id) {
		if (id != null || !id.equals("")) {
			return (HouseType) baseDao.loadById(HouseType.class, id);
		} else {
			return null;
		}
	}

	@Override
	public List<HouseType> findAllHouseType(String pId) {
		if (pId != null && !pId.equals("")) {
			String hql = "from HouseType where projectId = '" + pId + "'";
			return baseDao.findByHql(hql);
		} else {
			return null;
		}
	}

}
