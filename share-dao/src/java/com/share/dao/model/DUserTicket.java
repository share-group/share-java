package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户礼券
 */
@Pojo
public class DUserTicket extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 礼券id
	 */
	private long ticketId;

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
	 * 获取礼券id
	 */
	public long getTicketId() {
		return ticketId;
	}

	/**
	 * 设置礼券id
	 */
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}


}