package com.sc.tradmaster.controller.favorites;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.Favorites;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.favorites.FavoritesService;
import com.sc.tradmaster.service.project.impl.dto.ProjectDTO;
import com.sc.tradmaster.service.user.UserService;

@Controller("favoritesController")
public class FavoritesController extends BaseController {
	
	@Resource(name="favoritesService")
	private FavoritesService favoritesService;

	/**
	 * 添加项目到项目收藏夹
	 */
	@RequestMapping("/to_addFavorite")
	public void insertFavorite(String projectId) {
		User u = (User) this.request.getSession().getAttribute("userInfo");
		favoritesService.add(u, projectId);
		
	}
	
	/**
	 * 收藏项目数量显示
	 */
	@RequestMapping("/to_favoriteListSize")
	public void getFavoriteListSize() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		
		int count = favoritesService.findFavSize(u);
		Map<String, Object> map = new HashMap<>();
		map.put("favSize", count);
		this.outObjectString(map, null);
	}
	
	/**
	 * 所有收藏的项目
	 */
	@RequestMapping("/to_getAllFavorite")
	public void getAllFavorite() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		
		List<Favorites> fList = favoritesService.findAllFav(u);
		
		this.outListString(fList);
	}
	
	/**
	 * 项目收藏夹默认页
	 */
	@RequestMapping("/to_favoriteList")
	public ModelAndView allFavorites() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		List<ProjectDTO> pList = favoritesService.findFavProject(u);
		ModelAndView model = new ModelAndView("app/house/collection");
		model.addObject("data", pList);
		return model;
	}
	
	/**
	 * 收藏项目删除
	 */
	@RequestMapping("/to_deleteFav")
	public void deleteFavorites(String allProjectId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		
		favoritesService.dropFav(u, allProjectId);
	
	}
	
	/**
	 * 中介经纪人发期待看
	 */
	@RequestMapping("/to_addFavoriteToCollection")
	public ModelAndView addFavoriteToCollection(String projectId) {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		
		
		List<ProjectDTO> pList = favoritesService.addFav(u, projectId);
		
		ModelAndView model = new ModelAndView("app/house/collection");
		model.addObject("data", pList);
		return model;
	}
}
