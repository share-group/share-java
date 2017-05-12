package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 执行方推荐列表
 */
@Pojo
public class DExecutiveRecommend extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 用户执行方id
	 */
	private long userId;
	/**
	 * 企业执行方id
	 */
	private long companyId;
	/**
	 * 预算
	 */
	private double budget;
	/**
	 * 是否已选择
	 */
	private int select;
	/**
	 * 任务id
	 */
	private long taskId;
	/**
	 * 类型
	 */
	private long tag;
	/**
	 * 套餐id
	 */
	private long packageId;

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
	 * 获取用户执行方id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置用户执行方id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取企业执行方id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置企业执行方id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获取预算
	 */
	public double getBudget() {
		return budget;
	}

	/**
	 * 设置预算
	 */
	public void setBudget(double budget) {
		this.budget = budget;
	}

	/**
	 * 获取是否已选择
	 */
	public int getSelect() {
		return select;
	}

	/**
	 * 设置是否已选择
	 */
	public void setSelect(int select) {
		this.select = select;
	}

	/**
	 * 获取任务id
	 */
	public long getTaskId() {
		return taskId;
	}

	/**
	 * 设置任务id
	 */
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	/**
	 * 获取类型
	 */
	public long getTag() {
		return tag;
	}

	/**
	 * 设置类型
	 */
	public void setTag(long tag) {
		this.tag = tag;
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


}