package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *分析图
 * @author wjp 2017-05-26
 *
 */
@Entity
@Table(name="t_manaerChartData")
public class ManaerChartData {
	//主键
	private String chartDataId;
	//显示文本
	private String buttonText;
	//图表title
	private String titleName;
	//说明文字
	private String descText;
	//前端图Name;
	private String chartName;
	//后台方法
	private String backgroundMethod;
	//关联按钮集合
	private String relationList;
	//返回地址
	private String returnAddress;
	//应用场景(app,web,all)
	private String scenario;
	//是否有趋势图(0:没有,1:有)
	private String trendPhoto;
	//预留标签字段
	private String tags;
	//预留的说明字段
	private String description;
	//预留的其他字段
	private String otherObligate;
	
	@Id
	public String getChartDataId() {
		return chartDataId;
	}
	public void setChartDataId(String chartDataId) {
		this.chartDataId = chartDataId;
	}
	public String getButtonText() {
		return buttonText;
	}
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getDescText() {
		return descText;
	}
	public void setDescText(String descText) {
		this.descText = descText;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public String getBackgroundMethod() {
		return backgroundMethod;
	}
	public void setBackgroundMethod(String backgroundMethod) {
		this.backgroundMethod = backgroundMethod;
	}
	public String getRelationList() {
		return relationList;
	}
	public void setRelationList(String relationList) {
		this.relationList = relationList;
	}
	public String getReturnAddress() {
		return returnAddress;
	}
	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getTrendPhoto() {
		return trendPhoto;
	}
	public void setTrendPhoto(String trendPhoto) {
		this.trendPhoto = trendPhoto;
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
	public String getOtherObligate() {
		return otherObligate;
	}
	public void setOtherObligate(String otherObligate) {
		this.otherObligate = otherObligate;
	}
	@Override
	public String toString() {
		return "ManaerChartData [chartDataId=" + chartDataId + ", buttonText=" + buttonText + ", titleName=" + titleName
				+ ", descText=" + descText + ", chartName=" + chartName + ", backgroundMethod=" + backgroundMethod
				+ ", relationList=" + relationList + ", returnAddress=" + returnAddress + ", scenario=" + scenario
				+ ", trendPhoto=" + trendPhoto + ", tags=" + tags + ", description=" + description + ", otherObligate="
				+ otherObligate + "]";
	}
	
}
