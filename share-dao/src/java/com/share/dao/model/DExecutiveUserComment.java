package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 个人执行方评价表
 */
@Pojo
public class DExecutiveUserComment extends DSuper {
	/**
	 * 被评价的用户id
	 */
	private long ownerId;
	/**
	 * 发表评价的用户id
	 */
	private long userId;
	/**
	 * 发表评价的企业id
	 */
	private long companyId;
	/**
	 * 评价内容
	 */
	private String content;
	/**
	 * 总评分
	 */
	private double totalScore;
	/**
	 * 完成质量得分
	 */
	private double qualityScore;
	/**
	 * 完成速度得分
	 */
	private double speedScore;
	/**
	 * 服务质量得分
	 */
	private double servScore;

	/**
	 * 获取被评价的用户id
	 */
	public long getOwnerId() {
		return ownerId;
	}

	/**
	 * 设置被评价的用户id
	 */
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * 获取发表评价的用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置发表评价的用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取发表评价的企业id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置发表评价的企业id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获取评价内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置评价内容
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * 获取完成质量得分
	 */
	public double getQualityScore() {
		return qualityScore;
	}

	/**
	 * 设置完成质量得分
	 */
	public void setQualityScore(double qualityScore) {
		this.qualityScore = qualityScore;
	}

	/**
	 * 获取完成速度得分
	 */
	public double getSpeedScore() {
		return speedScore;
	}

	/**
	 * 设置完成速度得分
	 */
	public void setSpeedScore(double speedScore) {
		this.speedScore = speedScore;
	}

	/**
	 * 获取服务质量得分
	 */
	public double getServScore() {
		return servScore;
	}

	/**
	 * 设置服务质量得分
	 */
	public void setServScore(double servScore) {
		this.servScore = servScore;
	}


}