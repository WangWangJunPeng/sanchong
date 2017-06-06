package com.sc.tradmaster.service.project.impl.dto;

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
	private Integer userStatusNum;
	private String userStatus;
	private String lastLoginTime;
	private String parentId;
	private String tags;
	private String idCard;
	private String description;
	private Integer roleId;
	private String roleName;
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
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getUserStatusNum() {
		return userStatusNum;
	}
	public void setUserStatusNum(Integer userStatusNum) {
		this.userStatusNum = userStatusNum;
	}
	
	
	public UserDTO creatUserDTO(User user) {
		this.userId = user.getUserId();
		this.userCaption = user.getUserCaption();
		this.phone = user.getPhone();
		/*if(user.getUserStatus().equals(0)){
			this.userStatus = "申请";
		}*/
		this.userStatusNum = user.getUserStatus();
		if(user.getUserStatus().equals(1)){
			this.userStatus = "启用";
		}
		if(user.getUserStatus().equals(3)){
			this.userStatus = "禁用";
		}
		this.createTime = user.getCreateTime();
		this.lastLoginTime = user.getLastLoginTime();
		this.parentId = user.getParentId();
		this.tags = user.getTags();
		this.idCard = user.getIdCard();
		Set<Role> roleId = user.getRoleId();
		for (Role role : roleId) {
			this.roleId = role.getRoleId();
			this.roleName = role.getDesctiption();
		}
		return this;
	}
}
