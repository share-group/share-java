package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 执行方报价表
 */
@Pojo
public class DExecutiveOffer extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 需求方报价
	 */
	private double offerAmount;
	/**
	 * 需求方工期开始时间
	 */
	private int offerStartTime;
	/**
	 * 需求方工期结束时间
	 */
	private int offerEndTime;
	/**
	 * 需求类型
	 */
	private String offerType;
	/**
	 * 需求描述
	 */
	private String offerDescription;
	/**
	 * 执行方还价
	 */
	private double execAmount;
	/**
	 * 执行方工期开始时间
	 */
	private int execStartTime;
	/**
	 * 执行方工期结束时间
	 */
	private int execEndTime;

	/**
	 * 获取专案id
	 */
	public long getCaseId() {
		return caseId;
	}

	/**
	 * 设置专案id
	 */
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	/**
	 * 获取需求方报价
	 */
	public double getOfferAmount() {
		return offerAmount;
	}

	/**
	 * 设置需求方报价
	 */
	public void setOfferAmount(double offerAmount) {
		this.offerAmount = offerAmount;
	}

	/**
	 * 获取需求方工期开始时间
	 */
	public int getOfferStartTime() {
		return offerStartTime;
	}

	/**
	 * 设置需求方工期开始时间
	 */
	public void setOfferStartTime(int offerStartTime) {
		this.offerStartTime = offerStartTime;
	}

	/**
	 * 获取需求方工期结束时间
	 */
	public int getOfferEndTime() {
		return offerEndTime;
	}

	/**
	 * 设置需求方工期结束时间
	 */
	public void setOfferEndTime(int offerEndTime) {
		this.offerEndTime = offerEndTime;
	}

	/**
	 * 获取需求类型
	 */
	public String getOfferType() {
		return offerType;
	}

	/**
	 * 设置需求类型
	 */
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	/**
	 * 获取需求描述
	 */
	public String getOfferDescription() {
		return offerDescription;
	}

	/**
	 * 设置需求描述
	 */
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	/**
	 * 获取执行方还价
	 */
	public double getExecAmount() {
		return execAmount;
	}

	/**
	 * 设置执行方还价
	 */
	public void setExecAmount(double execAmount) {
		this.execAmount = execAmount;
	}

	/**
	 * 获取执行方工期开始时间
	 */
	public int getExecStartTime() {
		return execStartTime;
	}

	/**
	 * 设置执行方工期开始时间
	 */
	public void setExecStartTime(int execStartTime) {
		this.execStartTime = execStartTime;
	}

	/**
	 * 获取执行方工期结束时间
	 */
	public int getExecEndTime() {
		return execEndTime;
	}

	/**
	 * 设置执行方工期结束时间
	 */
	public void setExecEndTime(int execEndTime) {
		this.execEndTime = execEndTime;
	}


}