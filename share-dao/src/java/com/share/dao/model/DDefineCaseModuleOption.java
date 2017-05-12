package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 企业自定义专案模块选项表
 */
@Pojo
public class DDefineCaseModuleOption extends DSuper {
	/**
	 * 模块id
	 */
	private long moduleId;
	/**
	 * 选项名称
	 */
	private String name;

	/**
	 * 获取模块id
	 */
	public long getModuleId() {
		return moduleId;
	}

	/**
	 * 设置模块id
	 */
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * 获取选项名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置选项名称
	 */
	public void setName(String name) {
		this.name = name;
	}


}