package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案调查问卷用户回答表
 */
@Pojo
public class DCaseAskAnswer extends DSuper {
	/**
	 * 问题id
	 */
	private long askId;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 答案
	 */
	private String answer;

	/**
	 * 获取问题id
	 */
	public long getAskId() {
		return askId;
	}

	/**
	 * 设置问题id
	 */
	public void setAskId(long askId) {
		this.askId = askId;
	}

	/**
	 * 获取用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取答案
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * 设置答案
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}


}