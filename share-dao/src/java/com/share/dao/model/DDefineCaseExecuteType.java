package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业自定义专案执行类型表
 */
@Pojo
public class DDefineCaseExecuteType extends DSuper {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型(1=线上,2=线下)
	 */
	private int type;

	/**
	 * 获取名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取类型(1=线上,2=线下)
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置类型(1=线上,2=线下)
	 */
	public void setType(int type) {
		this.type = type;
	}


}