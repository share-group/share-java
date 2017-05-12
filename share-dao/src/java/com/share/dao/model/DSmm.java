package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 社会化营销项目
 */
@Pojo
public class DSmm extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 套餐id
	 */
	private long packageId;
	/**
	 * 预算金额
	 */
	private int amount;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 首付、尾款的订单号
	 */
	private String orderId;
	/**
	 * 状态(1-审核不通过，2-支付订金，3-进行中，4-支付尾款，5-已结束)
	 */
	private int status;
	/**
	 * 任务详细
	 */
	private String detail;

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
	 * 获取套餐id
	 */
	public long getPackageId() {
		return packageId;
	}

	/**
	 * 设置套餐id
	 */
	public void setPackageId(long packageId) {
		this.packageId = packageId;
	}

	/**
	 * 获取预算金额
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * 设置预算金额
	 */
	public void setAmount(int amount) {
		this.amount = amount;
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
	 * 获取联系电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取首付、尾款的订单号
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置首付、尾款的订单号
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取状态(1-审核不通过，2-支付订金，3-进行中，4-支付尾款，5-已结束)
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置状态(1-审核不通过，2-支付订金，3-进行中，4-支付尾款，5-已结束)
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 获取任务详细
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置任务详细
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}


}