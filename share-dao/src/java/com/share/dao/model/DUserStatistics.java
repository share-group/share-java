package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户统计字段表
 */
@Pojo
public class DUserStatistics extends DSuper {
	private long userId;
	/**
	 * 用户赚取的点数
	 */
	private int pointsEarn;
	/**
	 * 获赞数
	 */
	private int praiseNum;
	/**
	 * 创意数
	 */
	private int ideaNum;
	/**
	 * 品牌选中次数
	 */
	private int companySelect;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取用户赚取的点数
	 */
	public int getPointsEarn() {
		return pointsEarn;
	}

	/**
	 * 设置用户赚取的点数
	 */
	public void setPointsEarn(int pointsEarn) {
		this.pointsEarn = pointsEarn;
	}

	/**
	 * 获取获赞数
	 */
	public int getPraiseNum() {
		return praiseNum;
	}

	/**
	 * 设置获赞数
	 */
	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	/**
	 * 获取创意数
	 */
	public int getIdeaNum() {
		return ideaNum;
	}

	/**
	 * 设置创意数
	 */
	public void setIdeaNum(int ideaNum) {
		this.ideaNum = ideaNum;
	}

	/**
	 * 获取品牌选中次数
	 */
	public int getCompanySelect() {
		return companySelect;
	}

	/**
	 * 设置品牌选中次数
	 */
	public void setCompanySelect(int companySelect) {
		this.companySelect = companySelect;
	}


}