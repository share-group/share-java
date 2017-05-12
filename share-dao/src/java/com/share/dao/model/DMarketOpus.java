package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 创意圈_作品表
 */
@Pojo
public class DMarketOpus extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 题目
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 赞
	 */
	private int praise;
	/**
	 * 评论统计
	 */
	private int commentNum;
	/**
	 * 图片1
	 */
	private String image1;
	/**
	 * 图片2
	 */
	private String image2;
	/**
	 * 图片3
	 */
	private String image3;

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
	 * 获取题目
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置题目
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取赞
	 */
	public int getPraise() {
		return praise;
	}

	/**
	 * 设置赞
	 */
	public void setPraise(int praise) {
		this.praise = praise;
	}

	/**
	 * 获取评论统计
	 */
	public int getCommentNum() {
		return commentNum;
	}

	/**
	 * 设置评论统计
	 */
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * 获取图片1
	 */
	public String getImage1() {
		return image1;
	}

	/**
	 * 设置图片1
	 */
	public void setImage1(String image1) {
		this.image1 = image1;
	}

	/**
	 * 获取图片2
	 */
	public String getImage2() {
		return image2;
	}

	/**
	 * 设置图片2
	 */
	public void setImage2(String image2) {
		this.image2 = image2;
	}

	/**
	 * 获取图片3
	 */
	public String getImage3() {
		return image3;
	}

	/**
	 * 设置图片3
	 */
	public void setImage3(String image3) {
		this.image3 = image3;
	}


}