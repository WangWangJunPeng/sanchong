package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_city")
public class CountryProvinceInfo {
	private Integer id;
	private String cityId;
	private String cityName;
	private String cityLevel;
	private String upCityId;
	private String upCityName;
	private String tags;
	private String description;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityLevel() {
		return cityLevel;
	}
	public void setCityLevel(String cityLevel) {
		this.cityLevel = cityLevel;
	}
	public String getUpCityId() {
		return upCityId;
	}
	public void setUpCityId(String upCityId) {
		this.upCityId = upCityId;
	}
	public String getUpCityName() {
		return upCityName;
	}
	public void setUpCityName(String upCityName) {
		this.upCityName = upCityName;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
