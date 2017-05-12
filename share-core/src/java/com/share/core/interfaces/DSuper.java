package com.share.core.interfaces;

import com.share.core.util.JSONObject;

/**
 * 所有pojo对象的超类
 */
public abstract class DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 记录创建时间
	 */
	private long createTime;
	/**
	 * 最后修改时间
	 */
	private long lastModifyTime;

	/**
	 * toString方法
	 */
	public String toString() {
		return JSONObject.encode(this);
	}

	/**
	 * toJSON方法
	 */
	public JSONObject toJSON() {
		return JSONObject.decode(JSONObject.encode(this));
	}

	/**
	 * 获取自增id
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置自增id
	 * @param id 自增id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取记录创建时间
	 */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * 设置记录创建时间
	 * @param createTime 记录创建时间
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取最后修改时间
	 */
	public long getLastModifyTime() {
		return lastModifyTime;
	}

	/**
	 * 设置最后修改时间
	 * @param lastModifyTime 最后修改时间
	 */
	public void setLastModifyTime(long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
}