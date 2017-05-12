package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 项目点子
 */
@Pojo
public class DProjectIdea extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 项目id
	 */
	private long projectId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 点赞数
	 */
	private int praise;
	private double creative;
	/**
	 * 评论数
	 */
	private int commentNum;
	/**
	 * 点击量
	 */
	private int pv;
	/**
	 * 所属标签
	 */
	private long tag;
	/**
	 * 显示状态(1-显示 0-隐藏)
	 */
	private byte status;
	/**
	 * 背景介绍
	 */
	private String background;
	/**
	 * 详细介绍
	 */
	private String detail;
	/**
	 * 调研资料
	 */
	private String research;
	/**
	 * 封面
	 */
	private String cover;
	/**
	 * 灵感启发
	 */
	private String inspiration;
	/**
	 * 最后编辑时间
	 */
	private int lastEditTime;

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
	 * 获取项目id
	 */
	public long getProjectId() {
		return projectId;
	}

	/**
	 * 设置项目id
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	/**
	 * 获取标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取点赞数
	 */
	public int getPraise() {
		return praise;
	}

	/**
	 * 设置点赞数
	 */
	public void setPraise(int praise) {
		this.praise = praise;
	}

	public double getCreative() {
		return creative;
	}

	public void setCreative(double creative) {
		this.creative = creative;
	}

	/**
	 * 获取评论数
	 */
	public int getCommentNum() {
		return commentNum;
	}

	/**
	 * 设置评论数
	 */
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * 获取点击量
	 */
	public int getPv() {
		return pv;
	}

	/**
	 * 设置点击量
	 */
	public void setPv(int pv) {
		this.pv = pv;
	}

	/**
	 * 获取所属标签
	 */
	public long getTag() {
		return tag;
	}

	/**
	 * 设置所属标签
	 */
	public void setTag(long tag) {
		this.tag = tag;
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

	/**
	 * 获取背景介绍
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * 设置背景介绍
	 */
	public void setBackground(String background) {
		this.background = background;
	}

	/**
	 * 获取详细介绍
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置详细介绍
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * 获取调研资料
	 */
	public String getResearch() {
		return research;
	}

	/**
	 * 设置调研资料
	 */
	public void setResearch(String research) {
		this.research = research;
	}

	/**
	 * 获取封面
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * 设置封面
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}

	/**
	 * 获取灵感启发
	 */
	public String getInspiration() {
		return inspiration;
	}

	/**
	 * 设置灵感启发
	 */
	public void setInspiration(String inspiration) {
		this.inspiration = inspiration;
	}

	/**
	 * 获取最后编辑时间
	 */
	public int getLastEditTime() {
		return lastEditTime;
	}

	/**
	 * 设置最后编辑时间
	 */
	public void setLastEditTime(int lastEditTime) {
		this.lastEditTime = lastEditTime;
	}


}