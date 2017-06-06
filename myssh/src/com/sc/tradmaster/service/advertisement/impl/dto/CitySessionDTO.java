package com.sc.tradmaster.service.advertisement.impl.dto;

public class CitySessionDTO {
	
	private String cityId;
	private String cityName;
	
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
	@Override
	public String toString() {
		return "CitySessionDTO [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
	
	
}
