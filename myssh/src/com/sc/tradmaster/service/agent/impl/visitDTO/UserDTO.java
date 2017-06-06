package com.sc.tradmaster.service.agent.impl.visitDTO;

import java.util.Set;

import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.User;

public class UserDTO {
	private String userId;
	private String userCaption;
	private String photo;
	private String phone;
	private String password;
	private String createTime;
	private Integer userStatus;
	private String lastLoginTime;
	private String parentId;
	private String tags;
	private String idCard;
	private String description;
	private Integer roleId;
	private String roleName;
	private String userToken;
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCaption() {
		return userCaption;
	}
	public void setUserCaption(String userCaption) {
		this.userCaption = userCaption;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public UserDTO creatUserDTO(User user) {
		this.userId = user.getUserId();
		this.userCaption = user.getUserCaption();
		this.phone = user.getPhone();
		this.photo = user.getPhoto();
		this.password = user.getPassword();
		this.createTime = user.getCreateTime();
		this.userStatus = user.getUserStatus();
		this.lastLoginTime = user.getLastLoginTime();
		this.parentId = user.getParentId();
		this.tags = user.getTags();
		this.idCard = user.getIdCard();
		this.description = user.getDescription();
		Set<Role> roleId = user.getRoleId();
		for (Role role : roleId) {
			if (role.getRoleId() == 3) {
				this.roleId = role.getRoleId();
			}
		}
		return this;
	}
	
	public UserDTO creatUser(User user) {
		this.userId = user.getUserId();
		this.userCaption = user.getUserCaption();
		this.phone = user.getPhone();
		this.photo = user.getPhoto();
		this.createTime = user.getCreateTime();
		this.userStatus = user.getUserStatus();
		this.lastLoginTime = user.getLastLoginTime();
		this.parentId = user.getParentId();
		this.tags = user.getTags();
		this.idCard = user.getIdCard();
		this.description = user.getDescription();
		this.userToken = user.getUserToken();
		Set<Role> roleId = user.getRoleId();
		for (Role role : roleId) {
				this.roleId = role.getRoleId();
				this.roleName = role.getRoleName();
		}
		return this;
	}
}
