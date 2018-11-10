package com.znbl.modules.interest.entity;

import java.util.Date;

import com.znbl.common.persistence.DataEntity;

/**
 * 动漫兴趣
 * @author Gray
 *
 */
public class Comic extends DataEntity<Comic>{
	
	
	private static final long serialVersionUID = 1L;

    private String id;
    private String type; //话题类型
	private String title;	// 标题
	private String image;	// 图片
	private String introduction; //简介
	private String keywords;// 关键字
	private Integer hits;	// 点击数
	private Integer claimNumber; //正方人数
	private Integer counterclaimNumber; //反方人数
    private Date startDate; //生效时间
    private Date expireDate; //失效时间
    private String openFlag; 	// 是否公开：0否 1是
    
    private String createByName; //创建者名
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(Integer claimNumber) {
		this.claimNumber = claimNumber;
	}
	public Integer getCounterclaimNumber() {
		return counterclaimNumber;
	}
	public void setCounterclaimNumber(Integer counterclaimNumber) {
		this.counterclaimNumber = counterclaimNumber;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}
    

	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	
	
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "Comic [id=" + id + ", type=" + type + ", title=" + title + ", image=" + image + ", introduction="
				+ introduction + ", keywords=" + keywords + ", hits=" + hits + ", claimNumber=" + claimNumber
				+ ", counterclaimNumber=" + counterclaimNumber + ", startDate=" + startDate + ", expireDate="
				+ expireDate + ", openFlag=" + openFlag + ", createByName=" + createByName + "]";
	}
	public Comic(String id, String type, String title, String image, String introduction, String keywords, Integer hits,
			Integer claimNumber, Integer counterclaimNumber, Date startDate, Date expireDate, String openFlag,
			String createByName) {
		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.image = image;
		this.introduction = introduction;
		this.keywords = keywords;
		this.hits = hits;
		this.claimNumber = claimNumber;
		this.counterclaimNumber = counterclaimNumber;
		this.startDate = startDate;
		this.expireDate = expireDate;
		this.openFlag = openFlag;
		this.createByName = createByName;
	}
	public Comic() {
		super();
	}

}
