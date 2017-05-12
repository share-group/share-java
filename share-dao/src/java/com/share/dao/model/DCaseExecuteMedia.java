package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案执行方媒体配置表
 */
@Pojo
public class DCaseExecuteMedia extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 执行预算
	 */
	private String executeBudget;
	/**
	 * 执行开始时间
	 */
	private int executeBeginTime;
	/**
	 * 执行结束时间
	 */
	private int executeEndTime;
	/**
	 * 执行方法类型
	 */
	private String executeType;
	/**
	 * 执行类型预算JSON
	 */
	private String executeTypeBudget;
	/**
	 * 投放开始时间
	 */
	private int mediaBeginTime;
	/**
	 * 投放结束时间
	 */
	private int mediaEndTime;
	/**
	 * 媒体类型
	 */
	private String mediaType;
	/**
	 * 分类
	 */
	private String mediaSort;
	/**
	 * 投放地区
	 */
	private String mediaArea;
	/**
	 * 投放预算
	 */
	private String mediaBudget;
	/**
	 * 粉丝类型
	 */
	private int mediaFansNumber;

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
	 * 获取执行预算
	 */
	public String getExecuteBudget() {
		return executeBudget;
	}

	/**
	 * 设置执行预算
	 */
	public void setExecuteBudget(String executeBudget) {
		this.executeBudget = executeBudget;
	}

	/**
	 * 获取执行开始时间
	 */
	public int getExecuteBeginTime() {
		return executeBeginTime;
	}

	/**
	 * 设置执行开始时间
	 */
	public void setExecuteBeginTime(int executeBeginTime) {
		this.executeBeginTime = executeBeginTime;
	}

	/**
	 * 获取执行结束时间
	 */
	public int getExecuteEndTime() {
		return executeEndTime;
	}

	/**
	 * 设置执行结束时间
	 */
	public void setExecuteEndTime(int executeEndTime) {
		this.executeEndTime = executeEndTime;
	}

	/**
	 * 获取执行方法类型
	 */
	public String getExecuteType() {
		return executeType;
	}

	/**
	 * 设置执行方法类型
	 */
	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}

	/**
	 * 获取执行类型预算JSON
	 */
	public String getExecuteTypeBudget() {
		return executeTypeBudget;
	}

	/**
	 * 设置执行类型预算JSON
	 */
	public void setExecuteTypeBudget(String executeTypeBudget) {
		this.executeTypeBudget = executeTypeBudget;
	}

	/**
	 * 获取投放开始时间
	 */
	public int getMediaBeginTime() {
		return mediaBeginTime;
	}

	/**
	 * 设置投放开始时间
	 */
	public void setMediaBeginTime(int mediaBeginTime) {
		this.mediaBeginTime = mediaBeginTime;
	}

	/**
	 * 获取投放结束时间
	 */
	public int getMediaEndTime() {
		return mediaEndTime;
	}

	/**
	 * 设置投放结束时间
	 */
	public void setMediaEndTime(int mediaEndTime) {
		this.mediaEndTime = mediaEndTime;
	}

	/**
	 * 获取媒体类型
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * 设置媒体类型
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * 获取分类
	 */
	public String getMediaSort() {
		return mediaSort;
	}

	/**
	 * 设置分类
	 */
	public void setMediaSort(String mediaSort) {
		this.mediaSort = mediaSort;
	}

	/**
	 * 获取投放地区
	 */
	public String getMediaArea() {
		return mediaArea;
	}

	/**
	 * 设置投放地区
	 */
	public void setMediaArea(String mediaArea) {
		this.mediaArea = mediaArea;
	}

	/**
	 * 获取投放预算
	 */
	public String getMediaBudget() {
		return mediaBudget;
	}

	/**
	 * 设置投放预算
	 */
	public void setMediaBudget(String mediaBudget) {
		this.mediaBudget = mediaBudget;
	}

	/**
	 * 获取粉丝类型
	 */
	public int getMediaFansNumber() {
		return mediaFansNumber;
	}

	/**
	 * 设置粉丝类型
	 */
	public void setMediaFansNumber(int mediaFansNumber) {
		this.mediaFansNumber = mediaFansNumber;
	}


}