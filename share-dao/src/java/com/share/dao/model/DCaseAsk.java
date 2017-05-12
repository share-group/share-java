package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案调查问卷问题表
 */
@Pojo
public class DCaseAsk extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 问题
	 */
	private String ask;
	/**
	 * 问题类型(单选/多选/不定选/问答)
	 */
	private int type;
	/**
	 * 多选限制
	 */
	private int choicesMax;

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
	 * 获取问题
	 */
	public String getAsk() {
		return ask;
	}

	/**
	 * 设置问题
	 */
	public void setAsk(String ask) {
		this.ask = ask;
	}

	/**
	 * 获取问题类型(单选/多选/不定选/问答)
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置问题类型(单选/多选/不定选/问答)
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 获取多选限制
	 */
	public int getChoicesMax() {
		return choicesMax;
	}

	/**
	 * 设置多选限制
	 */
	public void setChoicesMax(int choicesMax) {
		this.choicesMax = choicesMax;
	}


}