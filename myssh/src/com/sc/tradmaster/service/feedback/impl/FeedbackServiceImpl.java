package com.sc.tradmaster.service.feedback.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.Feedback;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.service.feedback.FeedbackService;
import com.sc.tradmaster.utils.PicUploadToYun;
import com.sc.tradmaster.utils.SmsContext;
import com.sc.tradmaster.utils.SysContent;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService{

	@Resource(name="baseDao")
	private BaseDao baseDao;

	
	/**
	 * 添加或更新反馈信息
	 * @return
	 * @throws Exception 
	 */	
	@Override
	public void addOrUpdate(User user, String problem, MultipartFile pic, String email, Integer stars) throws Exception {
		
		Feedback fb = new Feedback();
		fb.setUserId(user.getUserId());
		fb.setProblem(problem);
		fb.setEmail(email);
		fb.setStars(stars);
		if ( !pic.isEmpty() && pic.getSize() > 0) {
			
			String rename = SysContent.getFileRename(pic.getOriginalFilename());
			// 设置凭证url
			String savePath = PicUploadToYun.upload(rename, pic,SmsContext.FB_PIC);
			fb.setPhoto(savePath);
		}
		Date date = new Date();
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UploadTime = sdf.format(date);
		fb.setAddTime(UploadTime);
		//保存反馈信息
		baseDao.saveOrUpdate(fb);
	}
}
