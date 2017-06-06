package com.sc.tradmaster.controller.bank;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sc.tradmaster.bean.BankCount;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.bank.BankCountService;
import com.sc.tradmaster.service.user.UserService;

/**
 * 2017-01-13
 * @author grl
 *
 */

@Controller("bankCountController")
public class BankCountController extends BaseController {
	
	@Resource(name="bankCountService")
	private BankCountService bankCountService;
	
	@Resource(name="userService")
	private UserService userService;
	/**
	 * 银行账号页面跳转控制器
	 * @return
	 */
	@RequestMapping("/to_bankCount")
	public String toBankCount(){
		return "project/bankCount";
	}
	
	/**
	 *银行账号列表
	 */
	@ResponseBody
	@RequestMapping("/get_bank_count_infos")
	public void getBankCountInfos(){
		//获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		//获取持久化用户对象
		User user = userService.findById(u.getUserId());
		//通过案场助理用户对象中对应的parentId记录的项目id关联银行账户的项目id，获取当前案场下的所有银行帐号
		if(user.getParentId()!=null && user.getParentId()!=""){
			List<BankCount> bankList = bankCountService.findAllBankCounts(user.getParentId());
			if(bankList!=null){
				this.outListString(bankList);
			}
		}
	}
	
	/**
	 * 添加银行账户
	 * @param bankCount
	 * @return
	 */
	@RequestMapping("/to_addBank_count")
	public String addBankCount(BankCount bankCount,String isSave){
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		//执行后台添加逻辑
		bankCountService.addBankCount(bankCount,user);
		
		if(isSave.equals("保存")){
			return "redirect:/to_bankCount";
		}else{
			return "redirect:/to_serviceDef";
		}
		
	}
	
	/**
	 * 通过银行账号id删除该帐号信息
	 * @param bankId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_delete_bank_count")
	public void deletBankCount(String bankId){
		//判断银行账号id是否存在，如果存在就执行删除逻辑，不存在直接回到当前页面
		if(bankId!=null && bankId!=""){
			bankCountService.dropBankCountById(bankId);
			this.outString("{success:true,msg:'删除成功!'}");
		}
	}
	
}
