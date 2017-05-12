package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业自定义专案媒体类型表
 */
@Pojo
public class DDefineCaseMediaType extends DSuper {
	/**
	 * 名称
	 */
	private String name;

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


}