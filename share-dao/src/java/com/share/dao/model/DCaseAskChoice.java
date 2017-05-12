package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 调查问卷选项表
 */
@Pojo
public class DCaseAskChoice extends DSuper {
	/**
	 * 问题id
	 */
	private long askId;
	/**
	 * 选项内容
	 */
	private String text;
	/**
	 * 被选总数统计
	 */
	private int chooseNum;

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
	 * 获取选项内容
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置选项内容
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 获取被选总数统计
	 */
	public int getChooseNum() {
		return chooseNum;
	}

	/**
	 * 设置被选总数统计
	 */
	public void setChooseNum(int chooseNum) {
		this.chooseNum = chooseNum;
	}


}