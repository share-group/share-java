package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业表
 */
@Pojo
public class DCompany extends DSuper {
	/**
	 * 绑定用户id
	 */
	private long userId;
	/**
	 * 企业名称
	 */
	private String name;
	/**
	 * 企业全称
	 */
	private String fullname;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 最后登录时间
	 */
	private int lastLoginTime;
	/**
	 * 企业简介
	 */
	private String description;
	/**
	 * 一句话介绍公司品牌
	 */
	private String introduce;
	/**
	 * 类型(领域)
	 */
	private long typeId;
	/**
	 * 企业logo
	 */
	private String logoImage;
	/**
	 * 企业图片
	 */
	private String pic;
	/**
	 * 点数(金额)
	 */
	private int points;
	/**
	 * 全部专案数量
	 */
	private int allCaseNum;
	/**
	 * 进行专案数量
	 */
	private int goingCaseNum;
	/**
	 * 完结专案数量
	 */
	private int overCaseNum;
	/**
	 * 粉丝数量
	 */
	private int fansNum;
	/**
	 * 专案收藏数
	 */
	private int collectNum;
	/**
	 * 关注用户数
	 */
	private int focusUserNum;
	/**
	 * 管理员姓名
	 */
	private String adminName;
	/**
	 * 管理员职位
	 */
	private String adminJob;
	/**
	 * 管理员电话
	 */
	private String adminPhone;
	/**
	 * 管理员邮箱
	 */
	private String adminEmail;
	/**
	 * 是否通过邮箱认证(0-否 1-是)
	 */
	private byte isAuth;
	/**
	 * 官网
	 */
	private String website;
	/**
	 * 微信公众号二维码
	 */
	private String wechatMpQr;
	/**
	 * 充值G点数
	 */
	private int chargePoints;
	/**
	 * 充值人民币
	 */
	private String chargeRmb;
	/**
	 * 剩余发专案次数(-1为无限)
	 */
	private int pushCaseNum;
	/**
	 * 本次充值结束时间
	 */
	private int chargeEndTime;
	/**
	 * 报告数量
	 */
	private int reportNum;

	/**
	 * 获取绑定用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置绑定用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取企业名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置企业名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取企业全称
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * 设置企业全称
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * 获取登录密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取最后登录时间
	 */
	public int getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * 设置最后登录时间
	 */
	public void setLastLoginTime(int lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 获取企业简介
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置企业简介
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取一句话介绍公司品牌
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * 设置一句话介绍公司品牌
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * 获取类型(领域)
	 */
	public long getTypeId() {
		return typeId;
	}

	/**
	 * 设置类型(领域)
	 */
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	/**
	 * 获取企业logo
	 */
	public String getLogoImage() {
		return logoImage;
	}

	/**
	 * 设置企业logo
	 */
	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	/**
	 * 获取企业图片
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * 设置企业图片
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}

	/**
	 * 获取点数(金额)
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * 设置点数(金额)
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * 获取全部专案数量
	 */
	public int getAllCaseNum() {
		return allCaseNum;
	}

	/**
	 * 设置全部专案数量
	 */
	public void setAllCaseNum(int allCaseNum) {
		this.allCaseNum = allCaseNum;
	}

	/**
	 * 获取进行专案数量
	 */
	public int getGoingCaseNum() {
		return goingCaseNum;
	}

	/**
	 * 设置进行专案数量
	 */
	public void setGoingCaseNum(int goingCaseNum) {
		this.goingCaseNum = goingCaseNum;
	}

	/**
	 * 获取完结专案数量
	 */
	public int getOverCaseNum() {
		return overCaseNum;
	}

	/**
	 * 设置完结专案数量
	 */
	public void setOverCaseNum(int overCaseNum) {
		this.overCaseNum = overCaseNum;
	}

	/**
	 * 获取粉丝数量
	 */
	public int getFansNum() {
		return fansNum;
	}

	/**
	 * 设置粉丝数量
	 */
	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}

	/**
	 * 获取专案收藏数
	 */
	public int getCollectNum() {
		return collectNum;
	}

	/**
	 * 设置专案收藏数
	 */
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	/**
	 * 获取关注用户数
	 */
	public int getFocusUserNum() {
		return focusUserNum;
	}

	/**
	 * 设置关注用户数
	 */
	public void setFocusUserNum(int focusUserNum) {
		this.focusUserNum = focusUserNum;
	}

	/**
	 * 获取管理员姓名
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * 设置管理员姓名
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	/**
	 * 获取管理员职位
	 */
	public String getAdminJob() {
		return adminJob;
	}

	/**
	 * 设置管理员职位
	 */
	public void setAdminJob(String adminJob) {
		this.adminJob = adminJob;
	}

	/**
	 * 获取管理员电话
	 */
	public String getAdminPhone() {
		return adminPhone;
	}

	/**
	 * 设置管理员电话
	 */
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	/**
	 * 获取管理员邮箱
	 */
	public String getAdminEmail() {
		return adminEmail;
	}

	/**
	 * 设置管理员邮箱
	 */
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	/**
	 * 获取是否通过邮箱认证(0-否 1-是)
	 */
	public byte getIsAuth() {
		return isAuth;
	}

	/**
	 * 设置是否通过邮箱认证(0-否 1-是)
	 */
	public void setIsAuth(byte isAuth) {
		this.isAuth = isAuth;
	}

	/**
	 * 获取官网
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * 设置官网
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * 获取微信公众号二维码
	 */
	public String getWechatMpQr() {
		return wechatMpQr;
	}

	/**
	 * 设置微信公众号二维码
	 */
	public void setWechatMpQr(String wechatMpQr) {
		this.wechatMpQr = wechatMpQr;
	}

	/**
	 * 获取充值G点数
	 */
	public int getChargePoints() {
		return chargePoints;
	}

	/**
	 * 设置充值G点数
	 */
	public void setChargePoints(int chargePoints) {
		this.chargePoints = chargePoints;
	}

	/**
	 * 获取充值人民币
	 */
	public String getChargeRmb() {
		return chargeRmb;
	}

	/**
	 * 设置充值人民币
	 */
	public void setChargeRmb(String chargeRmb) {
		this.chargeRmb = chargeRmb;
	}

	/**
	 * 获取剩余发专案次数(-1为无限)
	 */
	public int getPushCaseNum() {
		return pushCaseNum;
	}

	/**
	 * 设置剩余发专案次数(-1为无限)
	 */
	public void setPushCaseNum(int pushCaseNum) {
		this.pushCaseNum = pushCaseNum;
	}

	/**
	 * 获取本次充值结束时间
	 */
	public int getChargeEndTime() {
		return chargeEndTime;
	}

	/**
	 * 设置本次充值结束时间
	 */
	public void setChargeEndTime(int chargeEndTime) {
		this.chargeEndTime = chargeEndTime;
	}

	/**
	 * 获取报告数量
	 */
	public int getReportNum() {
		return reportNum;
	}

	/**
	 * 设置报告数量
	 */
	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}


}