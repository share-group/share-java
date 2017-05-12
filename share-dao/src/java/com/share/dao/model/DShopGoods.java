package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 积分商城商品
 */
@Pojo
public class DShopGoods extends DSuper {
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品简介
	 */
	private String introduce;
	/**
	 * 兑换分数
	 */
	private int score;
	/**
	 * 数量
	 */
	private int num;
	/**
	 * 分类
	 */
	private String type;
	/**
	 * 状态(0=下架,1=上架)
	 */
	private byte state;
	/**
	 * 商家
	 */
	private long companyId;
	/**
	 * 图片1
	 */
	private String image1;

	/**
	 * 获取商品名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取商品简介
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * 设置商品简介
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * 获取兑换分数
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 设置兑换分数
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * 获取数量
	 */
	public int getNum() {
		return num;
	}

	/**
	 * 设置数量
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * 获取分类
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置分类
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取状态(0=下架,1=上架)
	 */
	public byte getState() {
		return state;
	}

	/**
	 * 设置状态(0=下架,1=上架)
	 */
	public void setState(byte state) {
		this.state = state;
	}

	/**
	 * 获取商家
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置商家
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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


}