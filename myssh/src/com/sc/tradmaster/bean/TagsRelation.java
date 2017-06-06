package com.sc.tradmaster.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name="t_tagsrelation")
public class TagsRelation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer relationId;//主键，自增
	private String targetId;//目标Id
	private String tags;//标签ID的集合
	@Override
	public String toString() {
		return "relationId=" + relationId + ", targetId=" + targetId + ", tags=" + tags;
	}
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
}
