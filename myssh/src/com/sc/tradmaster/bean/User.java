package com.sc.tradmaster.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/**
 * 2017-01-05
 * @author grl 
 *
 */
@Entity
@Table(name="t_users")
public class User {
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
	private Set<Role> roleId;
	private String userToken;
	
	@Id
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

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_role",joinColumns=@JoinColumn(name="u_id"),inverseJoinColumns=@JoinColumn(name="r_id"))
	public Set<Role> getRoleId() {
		return roleId;
	}
	public void setRoleId(Set<Role> roleId) {
		this.roleId = roleId;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
}
