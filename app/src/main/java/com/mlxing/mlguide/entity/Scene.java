package com.mlxing.mlguide.entity;

import java.io.Serializable;

/**
 * 景点
 * @author quan
 *
 */
public class Scene implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 6746406990804901474L;

	/**
	 * 
	 */

	private Integer id;

	private String homePagePic;

	private String name;

	private String sceneNo;

	private String latitude;

	private String longitude;

	private String listLeftPic;

	private String listRightPic;

	private Integer status;

	private String sceneryAddress;

	private String slogan;

	private Integer videoSouce;

	private String videoLink;

	private Integer cityId;

	private String cityName;

	private String website;

	/**
	 * 景点来源类型：网站录入，同程
	 */
	private int sourceType;

	/**
	 * 合作方ID
	 */
	private int partnerId;

	/**
	 * 景点名片背景图片地址
	 */
	private String sceneCardPic;
	
	/**
	 * 购票须知
	 */
	private String buyNotice;

	/**
	 * 是否本站可以预订景点门票
	 */
	private Integer mlzgBookFlag;

	private String provinceName;

	private String phone;

	private String collectingTicketsAddress;

	private String checkOpinion;

	private String qrcode;

	private String contactName;

	private String contactPhone;

	private String contactMobile;

	private String contactQq;

	private String contactMail;

	private int supplierId;

	private String supplierName;

	private int contractId;

	private String userNo;

	private String loginName;

	private Integer flag;
	
	/**
	 * 导览图地址
	 */
	private String aerialView;

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName( String provinceName ) {
		this.provinceName = provinceName;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name == null ? null : name.trim();
	}

	public String getSceneNo() {
		return sceneNo;
	}

	public void setSceneNo( String sceneNo ) {
		this.sceneNo = sceneNo;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude( String latitude ) {
		this.latitude = latitude == null ? null : latitude.trim();
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude( String longitude ) {
		this.longitude = longitude == null ? null : longitude.trim();
	}

	public String getListLeftPic() {
		return listLeftPic;
	}

	public void setListLeftPic( String listLeftPic ) {
		this.listLeftPic = listLeftPic == null ? null : listLeftPic.trim();
	}

	public String getListRightPic() {
		return listRightPic;
	}

	public void setListRightPic( String listRightPic ) {
		this.listRightPic = listRightPic == null ? null : listRightPic.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus( Integer status ) {
		this.status = status;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan( String slogan ) {
		this.slogan = slogan == null ? null : slogan.trim();
	}

	public Integer getVideoSouce() {
		return videoSouce;
	}

	public void setVideoSouce( Integer videoSouce ) {
		this.videoSouce = videoSouce;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink( String videoLink ) {
		this.videoLink = videoLink == null ? null : videoLink.trim();
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId( Integer cityId ) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName( String cityName ) {
		this.cityName = cityName == null ? null : cityName.trim();
	}

	public String getSceneryAddress() {
		return sceneryAddress;
	}

	public void setSceneryAddress( String sceneryAddress ) {
		this.sceneryAddress = sceneryAddress;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType( int sourceType ) {
		this.sourceType = sourceType;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId( int partnerId ) {
		this.partnerId = partnerId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite( String website ) {
		this.website = website;
	}

	public String getSceneCardPic() {
		return sceneCardPic;
	}

	public void setSceneCardPic( String sceneCardPic ) {
		this.sceneCardPic = sceneCardPic;
	}

	public String getHomePagePic() {
		return homePagePic;
	}

	public void setHomePagePic( String homePagePic ) {
		this.homePagePic = homePagePic;
	}

	public Integer getMlzgBookFlag() {
		return mlzgBookFlag;
	}

	public void setMlzgBookFlag( Integer mlzgBookFlag ) {
		this.mlzgBookFlag = mlzgBookFlag;
	}

	@Override
	public int hashCode() {

		return this.getId().intValue();
	}

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone( String phone ) {
		this.phone = phone;
	}

	public String getCollectingTicketsAddress() {
		return collectingTicketsAddress;
	}

	public void setCollectingTicketsAddress( String collectingTicketsAddress ) {
		this.collectingTicketsAddress = collectingTicketsAddress;
	}

	public String getCheckOpinion() {
		return checkOpinion;
	}

	public void setCheckOpinion( String checkOpinion ) {
		this.checkOpinion = checkOpinion;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode( String qrcode ) {
		this.qrcode = qrcode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName( String contactName ) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone( String contactPhone ) {
		this.contactPhone = contactPhone;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile( String contactMobile ) {
		this.contactMobile = contactMobile;
	}

	public String getContactQq() {
		return contactQq;
	}

	public void setContactQq( String contactQq ) {
		this.contactQq = contactQq;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail( String contactMail ) {
		this.contactMail = contactMail;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId( int supplierId ) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName( String supplierName ) {
		this.supplierName = supplierName;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId( int contractId ) {
		this.contractId = contractId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo( String userNo ) {
		this.userNo = userNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName( String loginName ) {
		this.loginName = loginName;
	}

	public Integer getFlag() {
	    return flag;
    }

	public void setFlag( Integer flag ) {
	    this.flag = flag;
    }

	public String getAerialView() {
		return aerialView;
	}

	public void setAerialView(String aerialView) {
		this.aerialView = aerialView;
	}

	public String getBuyNotice() {
		return buyNotice;
	}

	public void setBuyNotice(String buyNotice) {
		this.buyNotice = buyNotice;
	}

}