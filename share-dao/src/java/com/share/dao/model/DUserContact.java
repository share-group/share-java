package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户通讯录
 */
@Pojo
public class DUserContact extends DSuper {
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 上传用户id
	 */
	private long userId;
	/**
	 * 联系人名称
	 */
	private String name;
	/**
	 * 联系人公司
	 */
	private String organization;
	/**
	 * 联系人职位
	 */
	private String job;
	/**
	 * 联系人工作部门
	 */
	private String department;
	/**
	 * 联系人生日
	 */
	private int birthday;
	/**
	 * 联系人email
	 */
	private String email;
	/**
	 * 是否已关注
	 */
	private int focusStatus;
	/**
	 * 排序值
	 */
	private int order;

	/**
	 * 获取手机号
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取上传用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置上传用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取联系人名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置联系人名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取联系人公司
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * 设置联系人公司
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * 获取联系人职位
	 */
	public String getJob() {
		return job;
	}

	/**
	 * 设置联系人职位
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * 获取联系人工作部门
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * 设置联系人工作部门
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 获取联系人生日
	 */
	public int getBirthday() {
		return birthday;
	}

	/**
	 * 设置联系人生日
	 */
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获取联系人email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置联系人email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取是否已关注
	 */
	public int getFocusStatus() {
		return focusStatus;
	}

	/**
	 * 设置是否已关注
	 */
	public void setFocusStatus(int focusStatus) {
		this.focusStatus = focusStatus;
	}

	/**
	 * 获取排序值
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * 设置排序值
	 */
	public void setOrder(int order) {
		this.order = order;
	}


}