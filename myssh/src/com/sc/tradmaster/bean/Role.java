package com.sc.tradmaster.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_role")
public class Role {
	
	private int roleId;
	private String roleName;
	private String rights;
	private String desctiption;
	private String tags;
	private Set<User> user;
	
	@Id
	@GeneratedValue
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public String getDesctiption() {
		return desctiption;
	}
	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@ManyToMany(mappedBy="roleId",fetch=FetchType.EAGER)
	public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}
	
}
