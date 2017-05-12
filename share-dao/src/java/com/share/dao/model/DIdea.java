package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 创意(点子)表
 */
@Pojo
public class DIdea extends DSuper {
	/**
	 * user表id
	 */
	private long userId;
	/**
	 * case表id
	 */
	private long caseId;
	/**
	 * 名字(简述)
	 */
	private String title;
	/**
	 * 详细(描述)
	 */
	private String content;
	/**
	 * 赞
	 */
	private int praise;
	/**
	 * 创意值
	 */
	private double creative;
	/**
	 * 评论总数
	 */
	private int commentNum;
	/**
	 * 阅读数
	 */
	private int readNum;
	/**
	 * 转发数
	 */
	private int forwardNum;
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
	 * 最后点赞时间
	 */
	private long lastPraiseTime;
	/**
	 * 观看权限(整数=用户id,0=不公开,-1=all,-2=friend)
	 */
	private String visit;
	/**
	 * 第几阶段的点子
	 */
	private int stage;

	/**
	 * 获取user表id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置user表id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取case表id
	 */
	public long getCaseId() {
		return caseId;
	}

	/**
	 * 设置case表id
	 */
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	/**
	 * 获取名字(简述)
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置名字(简述)
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取详细(描述)
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置详细(描述)
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
	 * 获取创意值
	 */
	public double getCreative() {
		return creative;
	}

	/**
	 * 设置创意值
	 */
	public void setCreative(double creative) {
		this.creative = creative;
	}

	/**
	 * 获取评论总数
	 */
	public int getCommentNum() {
		return commentNum;
	}

	/**
	 * 设置评论总数
	 */
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * 获取阅读数
	 */
	public int getReadNum() {
		return readNum;
	}

	/**
	 * 设置阅读数
	 */
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	/**
	 * 获取转发数
	 */
	public int getForwardNum() {
		return forwardNum;
	}

	/**
	 * 设置转发数
	 */
	public void setForwardNum(int forwardNum) {
		this.forwardNum = forwardNum;
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

	/**
	 * 获取最后点赞时间
	 */
	public long getLastPraiseTime() {
		return lastPraiseTime;
	}

	/**
	 * 设置最后点赞时间
	 */
	public void setLastPraiseTime(long lastPraiseTime) {
		this.lastPraiseTime = lastPraiseTime;
	}

	/**
	 * 获取观看权限(整数=用户id,0=不公开,-1=all,-2=friend)
	 */
	public String getVisit() {
		return visit;
	}

	/**
	 * 设置观看权限(整数=用户id,0=不公开,-1=all,-2=friend)
	 */
	public void setVisit(String visit) {
		this.visit = visit;
	}

	/**
	 * 获取第几阶段的点子
	 */
	public int getStage() {
		return stage;
	}

	/**
	 * 设置第几阶段的点子
	 */
	public void setStage(int stage) {
		this.stage = stage;
	}


}