package com.sc.tradmaster.service.projectPics;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.ProjectPics;
import com.sc.tradmaster.bean.User;

/**
 * 
 * @author grl 2017-01-15
 *
 */
public interface ProjectPicsService {
	/**
	 * 添加或更新案场照片集(效果图)
	 * @param proPic
	 * @param u
	 * @throws Exception 
	 */
	void addOrUpdataProPics(ProjectPics proPic,String[] urlArray,User u,MultipartFile[] pics) throws Exception;
	/**
	 * 通过id获取一个案场照片集(效果图)
	 * @param ppId
	 * @return
	 */
	ProjectPics findById(String ppId);
	/**
	 * 获取当前项目下所有案场照片集的集合数据
	 * @return
	 */
	List<ProjectPics> findAllProPics(User u);
	/**
	 * 通过id删除当前信息
	 * @param ppId
	 */
	void dropProPics(String ppId);
	/**
	 * 通过id获取效果图的url集合
	 * @param proPicsId
	 * @return
	 */
	List findPicsUrlList(String proPicsId);
}
