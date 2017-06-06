package com.sc.tradmaster.service.feedback;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.User;

/**
 * 2017-03-02
 * @author wjp
 *
 */
public interface FeedbackService {

	/**
	 * 添加或更新反馈信息
	 * @return
	 */	
	void addOrUpdate(User user, String problem,MultipartFile pic,String email,Integer stars)throws Exception;
}
