package com.sc.tradmaster.service.project.impl.dto;

public class ShopAndUserNameDTO {

	//中介门店编号,自动生成,唯一性
		private Integer shopId;
		//门店状态,0:申请,1:正常,2:删除,3:冻结
		private Integer shopStatus;
		//申请时间
		private String applyTime;
		//批准时间
		private String approveTime;
		//批准的用户编码
		private String approveUserId;
		//中介门店名称
		private String shopName;
		//中介公司名称
		private String companyName;
		//省,市,区
		private String city;
		//联系人名字
		private String contactPerson;
		//联系人电话
		private String phone;
		//邮箱
		private String email;
		//最后一次登陆的时间
		private String lastLoginTime;
		//预留的描述字段
		private String description;
		//预留的标签字段
		private String tags;
		//门店照片url
		private String photo;
		//银行卡卡号
		private String bank_cards;
		//开户银行名称
		private String bankName;
		//银行卡法人代表
		private String representative;
		//营业执照复印件url
		private String licensePhoto;
		//门店营业执照号
		private String licenseNo;
		//审核意见
		private String auditOpinion;
		//审核人id
		private String userId;
		//审核人姓名
		private String userCaption;
		//门店地址
		private String address;
		
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public Integer getShopId() {
			return shopId;
		}
		public void setShopId(Integer shopId) {
			this.shopId = shopId;
		}
		public Integer getShopStatus() {
			return shopStatus;
		}
		public void setShopStatus(Integer shopStatus) {
			this.shopStatus = shopStatus;
		}
		public String getApplyTime() {
			return applyTime;
		}
		public void setApplyTime(String applyTime) {
			this.applyTime = applyTime;
		}
		public String getApproveTime() {
			return approveTime;
		}
		public void setApproveTime(String approveTime) {
			this.approveTime = approveTime;
		}
		public String getApproveUserId() {
			return approveUserId;
		}
		public void setApproveUserId(String approveUserId) {
			this.approveUserId = approveUserId;
		}
		public String getShopName() {
			return shopName;
		}
		public void setShopName(String shopName) {
			this.shopName = shopName;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getContactPerson() {
			return contactPerson;
		}
		public void setContactPerson(String contactPerson) {
			this.contactPerson = contactPerson;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getLastLoginTime() {
			return lastLoginTime;
		}
		public void setLastLoginTime(String lastLoginTime) {
			this.lastLoginTime = lastLoginTime;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getTags() {
			return tags;
		}
		public void setTags(String tags) {
			this.tags = tags;
		}
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		public String getBank_cards() {
			return bank_cards;
		}
		public void setBank_cards(String bank_cards) {
			this.bank_cards = bank_cards;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
		public String getRepresentative() {
			return representative;
		}
		public void setRepresentative(String representative) {
			this.representative = representative;
		}
		public String getLicensePhoto() {
			return licensePhoto;
		}
		public void setLicensePhoto(String licensePhoto) {
			this.licensePhoto = licensePhoto;
		}
		public String getLicenseNo() {
			return licenseNo;
		}
		public void setLicenseNo(String licenseNo) {
			this.licenseNo = licenseNo;
		}
		public String getAuditOpinion() {
			return auditOpinion;
		}
		public void setAuditOpinion(String auditOpinion) {
			this.auditOpinion = auditOpinion;
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
}
