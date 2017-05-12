package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 项目
 */
@Pojo
public class DProject extends DSuper {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 项目标题
	 */
	private String title;
	/**
	 * 项目封面
	 */
	private String cover;
	/**
	 * 项目描述
	 */
	private String description;
	/**
	 * 项目描述附件
	 */
	private String descriptionAttach;
	/**
	 * 导师配置
	 */
	private String tutor;
	/**
	 * 评审配置
	 */
	private String reviewer;
	/**
	 * 支持机构
	 */
	private String agency;
	/**
	 * 结束时间
	 */
	private int endTime;
	private int ideaNum;
	private int researchNum;
	/**
	 * 贡献者数量
	 */
	private int contributors;
	/**
	 * 品牌介绍
	 */
	private String brandIntroduction;
	/**
	 * 品牌介绍附件
	 */
	private String brandIntroductionAttach;
	/**
	 * 显示状态(1-显示 0-隐藏)
	 */
	private byte status;

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
	 * 获取项目标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置项目标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取项目封面
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * 设置项目封面
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}

	/**
	 * 获取项目描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置项目描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取项目描述附件
	 */
	public String getDescriptionAttach() {
		return descriptionAttach;
	}

	/**
	 * 设置项目描述附件
	 */
	public void setDescriptionAttach(String descriptionAttach) {
		this.descriptionAttach = descriptionAttach;
	}

	/**
	 * 获取导师配置
	 */
	public String getTutor() {
		return tutor;
	}

	/**
	 * 设置导师配置
	 */
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	/**
	 * 获取评审配置
	 */
	public String getReviewer() {
		return reviewer;
	}

	/**
	 * 设置评审配置
	 */
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	/**
	 * 获取支持机构
	 */
	public String getAgency() {
		return agency;
	}

	/**
	 * 设置支持机构
	 */
	public void setAgency(String agency) {
		this.agency = agency;
	}

	/**
	 * 获取结束时间
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * 设置结束时间
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getIdeaNum() {
		return ideaNum;
	}

	public void setIdeaNum(int ideaNum) {
		this.ideaNum = ideaNum;
	}

	public int getResearchNum() {
		return researchNum;
	}

	public void setResearchNum(int researchNum) {
		this.researchNum = researchNum;
	}

	/**
	 * 获取贡献者数量
	 */
	public int getContributors() {
		return contributors;
	}

	/**
	 * 设置贡献者数量
	 */
	public void setContributors(int contributors) {
		this.contributors = contributors;
	}

	/**
	 * 获取品牌介绍
	 */
	public String getBrandIntroduction() {
		return brandIntroduction;
	}

	/**
	 * 设置品牌介绍
	 */
	public void setBrandIntroduction(String brandIntroduction) {
		this.brandIntroduction = brandIntroduction;
	}

	/**
	 * 获取品牌介绍附件
	 */
	public String getBrandIntroductionAttach() {
		return brandIntroductionAttach;
	}

	/**
	 * 设置品牌介绍附件
	 */
	public void setBrandIntroductionAttach(String brandIntroductionAttach) {
		this.brandIntroductionAttach = brandIntroductionAttach;
	}

	/**
	 * 获取显示状态(1-显示 0-隐藏)
	 */
	public byte getStatus() {
		return status;
	}

	/**
	 * 设置显示状态(1-显示 0-隐藏)
	 */
	public void setStatus(byte status) {
		this.status = status;
	}


}