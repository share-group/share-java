package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户维度
 */
@Pojo
public class DUserDimension extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 参与
	 */
	private int join;
	/**
	 * 点赞
	 */
	private double praise;
	/**
	 * 合作
	 */
	private double collaboration;
	/**
	 * 转发
	 */
	private double forward;
	/**
	 * 企业选中
	 */
	private double select;
	/**
	 * 影响力
	 */
	private double influence;

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
	 * 获取参与
	 */
	public int getJoin() {
		return join;
	}

	/**
	 * 设置参与
	 */
	public void setJoin(int join) {
		this.join = join;
	}

	/**
	 * 获取点赞
	 */
	public double getPraise() {
		return praise;
	}

	/**
	 * 设置点赞
	 */
	public void setPraise(double praise) {
		this.praise = praise;
	}

	/**
	 * 获取合作
	 */
	public double getCollaboration() {
		return collaboration;
	}

	/**
	 * 设置合作
	 */
	public void setCollaboration(double collaboration) {
		this.collaboration = collaboration;
	}

	/**
	 * 获取转发
	 */
	public double getForward() {
		return forward;
	}

	/**
	 * 设置转发
	 */
	public void setForward(double forward) {
		this.forward = forward;
	}

	/**
	 * 获取企业选中
	 */
	public double getSelect() {
		return select;
	}

	/**
	 * 设置企业选中
	 */
	public void setSelect(double select) {
		this.select = select;
	}

	/**
	 * 获取影响力
	 */
	public double getInfluence() {
		return influence;
	}

	/**
	 * 设置影响力
	 */
	public void setInfluence(double influence) {
		this.influence = influence;
	}


}