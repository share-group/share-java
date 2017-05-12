package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户所有专案的标签
 */
@Pojo
public class DUserInterestTag extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 标签id(自定义的+100000000)
	 */
	private long typeId;

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
	 * 获取标签id(自定义的+100000000)
	 */
	public long getTypeId() {
		return typeId;
	}

	/**
	 * 设置标签id(自定义的+100000000)
	 */
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}


}