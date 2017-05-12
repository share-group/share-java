package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * CPC项目
 */
@Pojo
public class DCpc extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 话题
	 */
	private String topic;
	/**
	 * 活动链接
	 */
	private String link;
	/**
	 * 活动简介
	 */
	private String description;
	/**
	 * 状态(1-审核不通过，2-审核通过，3-待支付，4-已支付)
	 */
	private int status;
	/**
	 * 支付订单号
	 */
	private long orderId;
	/**
	 * 排序字段
	 */
	private int orderIndex;
	/**
	 * 活动领域
	 */
	private String type;
	/**
	 * 活动图片
	 */
	private String image;
	/**
	 * 媒体地区
	 */
	private String mediaArea;
	/**
	 * 投放圈子
	 */
	private String group;
	/**
	 * 推广预算
	 */
	private double budget;
	/**
	 * 剩余预算
	 */
	private double rest;
	/**
	 * 奖励模式
	 */
	private int awardMode;
	/**
	 * 分享模式
	 */
	private int shareMode;
	/**
	 * 奖励模式对应的值
	 */
	private String value;
	/**
	 * 参与人数
	 */
	private int joinNum;
	/**
	 * 总曝光
	 */
	private int pv;
	/**
	 * 总点击
	 */
	private int uv;
	/**
	 * 推广开始时间
	 */
	private int startTime;
	/**
	 * 推广结束时间
	 */
	private int endTime;

	/**
	 * 获取企业id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置企业id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获取话题
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * 设置话题
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * 获取活动链接
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 设置活动链接
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 获取活动简介
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置活动简介
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取状态(1-审核不通过，2-审核通过，3-待支付，4-已支付)
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置状态(1-审核不通过，2-审核通过，3-待支付，4-已支付)
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 获取支付订单号
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * 设置支付订单号
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取排序字段
	 */
	public int getOrderIndex() {
		return orderIndex;
	}

	/**
	 * 设置排序字段
	 */
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	/**
	 * 获取活动领域
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置活动领域
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取活动图片
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 设置活动图片
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 获取媒体地区
	 */
	public String getMediaArea() {
		return mediaArea;
	}

	/**
	 * 设置媒体地区
	 */
	public void setMediaArea(String mediaArea) {
		this.mediaArea = mediaArea;
	}

	/**
	 * 获取投放圈子
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * 设置投放圈子
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * 获取推广预算
	 */
	public double getBudget() {
		return budget;
	}

	/**
	 * 设置推广预算
	 */
	public void setBudget(double budget) {
		this.budget = budget;
	}

	/**
	 * 获取剩余预算
	 */
	public double getRest() {
		return rest;
	}

	/**
	 * 设置剩余预算
	 */
	public void setRest(double rest) {
		this.rest = rest;
	}

	/**
	 * 获取奖励模式
	 */
	public int getAwardMode() {
		return awardMode;
	}

	/**
	 * 设置奖励模式
	 */
	public void setAwardMode(int awardMode) {
		this.awardMode = awardMode;
	}

	/**
	 * 获取分享模式
	 */
	public int getShareMode() {
		return shareMode;
	}

	/**
	 * 设置分享模式
	 */
	public void setShareMode(int shareMode) {
		this.shareMode = shareMode;
	}

	/**
	 * 获取奖励模式对应的值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置奖励模式对应的值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取参与人数
	 */
	public int getJoinNum() {
		return joinNum;
	}

	/**
	 * 设置参与人数
	 */
	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	/**
	 * 获取总曝光
	 */
	public int getPv() {
		return pv;
	}

	/**
	 * 设置总曝光
	 */
	public void setPv(int pv) {
		this.pv = pv;
	}

	/**
	 * 获取总点击
	 */
	public int getUv() {
		return uv;
	}

	/**
	 * 设置总点击
	 */
	public void setUv(int uv) {
		this.uv = uv;
	}

	/**
	 * 获取推广开始时间
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * 设置推广开始时间
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取推广结束时间
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 设置推广结束时间
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}


}