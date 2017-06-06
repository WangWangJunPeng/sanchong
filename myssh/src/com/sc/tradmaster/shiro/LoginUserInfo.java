package com.sc.tradmaster.shiro;

import com.sc.tradmaster.bean.User;

/**
 * 
 * @author grl 2017-01-05
 *
 */
public class LoginUserInfo {
	private String name;
	private String passworld;
	
	

    public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPassworld() {
		return passworld;
	}



	public void setPassworld(String passworld) {
		this.passworld = passworld;
	}



	public static LoginUserInfo fromPerson(User u) {
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setName(u.getPhone());
        loginUserInfo.setPassworld(u.getPassword());
        return loginUserInfo;
    }
}
