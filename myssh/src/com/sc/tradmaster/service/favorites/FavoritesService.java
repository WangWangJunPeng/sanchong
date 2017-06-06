package com.sc.tradmaster.service.favorites;

import java.util.List;

import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.User;

public interface FavoritesService {
	/**
	 * 增加收藏项目
	 */	
	void add(User user, String projectId);
	
	/**
	 * 项目收藏默认页
	 */	
	List findFavProject(User user);
	
	/**
	 * 删除收藏项目
	 */	
	void dropFav(User user, String allProjectId);
	
	/**
	 * 收藏条数显示
	 */
	int findFavSize(User user);
	
	/**
	 * 所有收藏项目
	 */
	List findAllFav(User user);
	
	/**
	 * 发起成交
	 */
	List addFav(User user,String projectId);
}
