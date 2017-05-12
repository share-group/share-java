package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 执行方任务表
 */
@Pojo
public class DExecutiveTask extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 执行方推荐id
	 */
	private long executiveRecommendId;
	/**
	 * 订单id
	 */
	private long orderId;
	/**
	 * 应付金额(包含抽水的)
	 */
	private double amount;
	/**
	 * 支付渠道
	 */
	private int channel;
	/**
	 * 任务状态
	 */
	private int status;
	/**
	 * 是否开发票
	 */
	private int receipt;
	/**
	 * 发票抬头
	 */
	private String title;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 联系地址
	 */
	private String address;
	/**
	 * 优惠码
	 */
	private String discountCode;
	/**
	 * 是否已经评价(1-是 0-否)
	 */
	private int isComment;
	/**
	 * 评价id
	 */
	private long commentId;

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
	 * 获取执行方推荐id
	 */
	public long getExecutiveRecommendId() {
		return executiveRecommendId;
	}

	/**
	 * 设置执行方推荐id
	 */
	public void setExecutiveRecommendId(long executiveRecommendId) {
		this.executiveRecommendId = executiveRecommendId;
	}

	/**
	 * 获取订单id
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * 设置订单id
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取应付金额(包含抽水的)
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * 设置应付金额(包含抽水的)
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * 获取支付渠道
	 */
	public int getChannel() {
		return channel;
	}

	/**
	 * 设置支付渠道
	 */
	public void setChannel(int channel) {
		this.channel = channel;
	}

	/**
	 * 获取任务状态
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置任务状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 获取是否开发票
	 */
	public int getReceipt() {
		return receipt;
	}

	/**
	 * 设置是否开发票
	 */
	public void setReceipt(int receipt) {
		this.receipt = receipt;
	}

	/**
	 * 获取发票抬头
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置发票抬头
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取联系人
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * 设置联系人
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * 获取联系方式
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系方式
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取联系地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取优惠码
	 */
	public String getDiscountCode() {
		return discountCode;
	}

	/**
	 * 设置优惠码
	 */
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	/**
	 * 获取是否已经评价(1-是 0-否)
	 */
	public int getIsComment() {
		return isComment;
	}

	/**
	 * 设置是否已经评价(1-是 0-否)
	 */
	public void setIsComment(int isComment) {
		this.isComment = isComment;
	}

	/**
	 * 获取评价id
	 */
	public long getCommentId() {
		return commentId;
	}

	/**
	 * 设置评价id
	 */
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}


}