package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 专案附件模块表
 */
@Pojo
public class DCaseAttachModule extends DSuper {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 模块id
	 */
	private long moduleId;
	/**
	 * 模块类型
	 */
	private int moduleType;
	/**
	 * 模块名称
	 */
	private String moduleName;
	/**
	 * 内容
	 */
	private String answer;
	/**
	 * app是否显示
	 */
	private int appShow;

	/**
	 * 获取专案id
	 */
	public long getCaseId() {
		return caseId;
	}

	/**
	 * 设置专案id
	 */
	public void setCaseId(long caseId) {
		this.caseId = caseId;
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
	 * 获取内容
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * 设置内容
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * 获取app是否显示
	 */
	public int getAppShow() {
		return appShow;
	}

	/**
	 * 设置app是否显示
	 */
	public void setAppShow(int appShow) {
		this.appShow = appShow;
	}


}