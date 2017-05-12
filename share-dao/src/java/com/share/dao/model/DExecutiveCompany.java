package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业申请执行方
 */
@Pojo
public class DExecutiveCompany extends DSuper {
	/**
	 * 企业法人名字
	 */
	private String companyLegal;
	/**
	 * 企业法人身份证号
	 */
	private String companyLegalIdNumber;
	/**
	 * 上传企业法人身份证
	 */
	private String companyLegalIdCard;
	/**
	 * 营业证号
	 */
	private String businessCardNumber;
	/**
	 * 营业证上传
	 */
	private String businessCard;
	/**
	 * 标签(“,”分割)
	 */
	private String tag;
	/**
	 * 是否认证(1-是 0-未审核 -1-撤销资格，-2-初次审核不通过)
	 */
	private int status;
	/**
	 * 总评分
	 */
	private double totalScore;
	/**
	 * 完成质量总共得分
	 */
	private double qualityScore;
	/**
	 * 完成速度总共得分
	 */
	private double speedScore;
	/**
	 * 服务质量总共得分
	 */
	private double servScore;
	/**
	 * 评论总数
	 */
	private int commentNum;
	/**
	 * 作品数
	 */
	private int opusNum;
	/**
	 * 套餐数
	 */
	private int packageNum;
	/**
	 * 接单数
	 */
	private int jobNum;
	/**
	 * 被终止次数
	 */
	private int stopNum;
	/**
	 * 服务金额
	 */
	private double servAmount;
	/**
	 * 累计金额
	 */
	private double totalAmount;
	/**
	 * 联系电话
	 */
	private String phoneNumber;
	/**
	 * 地区
	 */
	private int region;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 自我介绍
	 */
	private String description;

	/**
	 * 获取企业法人名字
	 */
	public String getCompanyLegal() {
		return companyLegal;
	}

	/**
	 * 设置企业法人名字
	 */
	public void setCompanyLegal(String companyLegal) {
		this.companyLegal = companyLegal;
	}

	/**
	 * 获取企业法人身份证号
	 */
	public String getCompanyLegalIdNumber() {
		return companyLegalIdNumber;
	}

	/**
	 * 设置企业法人身份证号
	 */
	public void setCompanyLegalIdNumber(String companyLegalIdNumber) {
		this.companyLegalIdNumber = companyLegalIdNumber;
	}

	/**
	 * 获取上传企业法人身份证
	 */
	public String getCompanyLegalIdCard() {
		return companyLegalIdCard;
	}

	/**
	 * 设置上传企业法人身份证
	 */
	public void setCompanyLegalIdCard(String companyLegalIdCard) {
		this.companyLegalIdCard = companyLegalIdCard;
	}

	/**
	 * 获取营业证号
	 */
	public String getBusinessCardNumber() {
		return businessCardNumber;
	}

	/**
	 * 设置营业证号
	 */
	public void setBusinessCardNumber(String businessCardNumber) {
		this.businessCardNumber = businessCardNumber;
	}

	/**
	 * 获取营业证上传
	 */
	public String getBusinessCard() {
		return businessCard;
	}

	/**
	 * 设置营业证上传
	 */
	public void setBusinessCard(String businessCard) {
		this.businessCard = businessCard;
	}

	/**
	 * 获取标签(“,”分割)
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * 设置标签(“,”分割)
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * 获取是否认证(1-是 0-未审核 -1-撤销资格，-2-初次审核不通过)
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置是否认证(1-是 0-未审核 -1-撤销资格，-2-初次审核不通过)
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 获取总评分
	 */
	public double getTotalScore() {
		return totalScore;
	}

	/**
	 * 设置总评分
	 */
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * 获取完成质量总共得分
	 */
	public double getQualityScore() {
		return qualityScore;
	}

	/**
	 * 设置完成质量总共得分
	 */
	public void setQualityScore(double qualityScore) {
		this.qualityScore = qualityScore;
	}

	/**
	 * 获取完成速度总共得分
	 */
	public double getSpeedScore() {
		return speedScore;
	}

	/**
	 * 设置完成速度总共得分
	 */
	public void setSpeedScore(double speedScore) {
		this.speedScore = speedScore;
	}

	/**
	 * 获取服务质量总共得分
	 */
	public double getServScore() {
		return servScore;
	}

	/**
	 * 设置服务质量总共得分
	 */
	public void setServScore(double servScore) {
		this.servScore = servScore;
	}

	/**
	 * 获取评论总数
	 */
	public int getCommentNum() {
		return commentNum;
	}

	/**
	 * 设置评论总数
	 */
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * 获取作品数
	 */
	public int getOpusNum() {
		return opusNum;
	}

	/**
	 * 设置作品数
	 */
	public void setOpusNum(int opusNum) {
		this.opusNum = opusNum;
	}

	/**
	 * 获取套餐数
	 */
	public int getPackageNum() {
		return packageNum;
	}

	/**
	 * 设置套餐数
	 */
	public void setPackageNum(int packageNum) {
		this.packageNum = packageNum;
	}

	/**
	 * 获取接单数
	 */
	public int getJobNum() {
		return jobNum;
	}

	/**
	 * 设置接单数
	 */
	public void setJobNum(int jobNum) {
		this.jobNum = jobNum;
	}

	/**
	 * 获取被终止次数
	 */
	public int getStopNum() {
		return stopNum;
	}

	/**
	 * 设置被终止次数
	 */
	public void setStopNum(int stopNum) {
		this.stopNum = stopNum;
	}

	/**
	 * 获取服务金额
	 */
	public double getServAmount() {
		return servAmount;
	}

	/**
	 * 设置服务金额
	 */
	public void setServAmount(double servAmount) {
		this.servAmount = servAmount;
	}

	/**
	 * 获取累计金额
	 */
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 设置累计金额
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * 获取联系电话
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 设置联系电话
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 获取地区
	 */
	public int getRegion() {
		return region;
	}

	/**
	 * 设置地区
	 */
	public void setRegion(int region) {
		this.region = region;
	}

	/**
	 * 获取地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取自我介绍
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置自我介绍
	 */
	public void setDescription(String description) {
		this.description = description;
	}


}