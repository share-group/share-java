package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 创意附加模块表
 */
@Pojo
public class DIdeaAttachModule extends DSuper {
	/**
	 * 创意id
	 */
	private long ideaId;
	/**
	 * 模块id
	 */
	private long moduleId;
	/**
	 * 模块名称
	 */
	private String moduleName;
	/**
	 * 模块类型
	 */
	private int moduleType;
	/**
	 * 内容
	 */
	private String content;

	/**
	 * 获取创意id
	 */
	public long getIdeaId() {
		return ideaId;
	}

	/**
	 * 设置创意id
	 */
	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

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
	 * 获取模块名称
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * 设置模块名称
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * 获取模块类型
	 */
	public int getModuleType() {
		return moduleType;
	}

	/**
	 * 设置模块类型
	 */
	public void setModuleType(int moduleType) {
		this.moduleType = moduleType;
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


}