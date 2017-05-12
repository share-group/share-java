package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 公告表
 */
@Pojo
public class DNotice extends DSuper {
	/**
	 * 管理员id
	 */
	private long adminId;
	/**
	 * 类型
	 */
	private long type;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String html;
	/**
	 * 是否显示
	 */
	private int isShow;

	/**
	 * 获取管理员id
	 */
	public long getAdminId() {
		return adminId;
	}

	/**
	 * 设置管理员id
	 */
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	/**
	 * 获取类型
	 */
	public long getType() {
		return type;
	}

	/**
	 * 设置类型
	 */
	public void setType(long type) {
		this.type = type;
	}

	/**
	 * 获取标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取内容
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * 设置内容
	 */
	public void setHtml(String html) {
		this.html = html;
	}

	/**
	 * 获取是否显示
	 */
	public int getIsShow() {
		return isShow;
	}

	/**
	 * 设置是否显示
	 */
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}


}