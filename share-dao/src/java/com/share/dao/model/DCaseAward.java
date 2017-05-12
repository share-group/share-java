package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案抽奖
 */
@Pojo
public class DCaseAward extends DSuper {
	/**
	 * 所属专案id
	 */
	private long caseId;
	/**
	 * 奖品顺序
	 */
	private int index;
	/**
	 * 奖项名称
	 */
	private String itemName;
	/**
	 * 奖品名称
	 */
	private String name;
	/**
	 * 奖品图片
	 */
	private String image;
	/**
	 * 数量
	 */
	private int num;
	private int weight;

	/**
	 * 获取所属专案id
	 */
	public long getCaseId() {
		return caseId;
	}

	/**
	 * 设置所属专案id
	 */
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	/**
	 * 获取奖品顺序
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 设置奖品顺序
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 获取奖项名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 设置奖项名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 获取奖品名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置奖品名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取奖品图片
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 设置奖品图片
	 */
	public void setImage(String image) {
		this.image = image;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}


}