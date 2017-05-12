package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案抽奖获奖名单
 */
@Pojo
public class DCaseAwardList extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 中奖人用户id
	 */
	private long userId;
	/**
	 * 奖品id
	 */
	private long awardId;

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
	 * 获取中奖人用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置中奖人用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取奖品id
	 */
	public long getAwardId() {
		return awardId;
	}

	/**
	 * 设置奖品id
	 */
	public void setAwardId(long awardId) {
		this.awardId = awardId;
	}


}