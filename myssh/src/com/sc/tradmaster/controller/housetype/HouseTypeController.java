package com.sc.tradmaster.controller.housetype;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.HouseType;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.housetype.HouseTypeService;
import com.sc.tradmaster.service.user.UserService;

/**
 * 2017-01-14
 * @author grl 
 *
 */
@Controller("houseTypeController")
public class HouseTypeController extends BaseController {
	@Resource(name="houseTypeService")
	private HouseTypeService houseTypeService;
	
	@Resource(name="userService")
	private UserService userService;
	
	/**
	 * 户型页面跳转控制器
	 * @return
	 */
	@RequestMapping("/to_houseType")
	public String toHouseType(){
		return "project/houseStyle";
	}
	
	/**
	 * 户型添加或户型信息修改
	 * @param ht
	 * @param pic
	 * @param home
	 * @param hall
	 * @param toilet
	 * @param isSave
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/add_house_type")
	public String addHouseType(HouseType ht, MultipartFile pic,String home,String hall,String toilet, String isSave) throws Exception{
		//获取登录用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		//获取持久化用户对象
		User user = userService.findById(u.getUserId());
		//户型值组合
		ht.setHousType(home+" "+hall+" "+toilet);
		//执行添加户型业务逻辑
		houseTypeService.addOrupdateHouseType(ht, user,pic);
		//页面跳转
		if(isSave.equals("保存")){
			return "redirect:/to_houseType";
		}else{
			return "redirect:/to_bankCount";
		}
		
	}
	
	/**
	 * 通过id获取当前户型信息
	 * @param hId
	 */
	@ResponseBody
	@RequestMapping("/get_current_house_type")
	public void getHouseTypeById(String hId){
		//若户型id存在，则获取该户型信息
		if(hId!=null && !hId.equals("")){
			HouseType ht = houseTypeService.findById(hId);
			this.outObjectString(ht, null);
		}
	}
	
	/**
	 *获取当前项目下的所有户型 
	 */
	@ResponseBody
	@RequestMapping("/list_all_house_type")
	public void allHouseTypesList() {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		// 通过案场助理用户对象中对应的parentId记录的项目id关联银行账户的项目id，获取当前案场下的所有银行帐号
		if (user.getParentId() != null && !user.getParentId().equals("")) {
			List<HouseType> houseTypeList = houseTypeService.findAllHouseType(u.getParentId());
			if (houseTypeList != null) {
				this.outListString(houseTypeList);
			}
		}

	}
	
	/**
	 * 通过户型id删除该户型
	 * @param hId
	 */
	@ResponseBody
	@RequestMapping("/delete_house_type_byId")
	public void deleteHouseTypeById(String hId){
		if(hId!=null && !hId.equals("")){
			houseTypeService.dropHouseType(hId);
			this.outString("{success:true,msg:'删除成功!'}");
		}
	}
	
	
	
	
}
