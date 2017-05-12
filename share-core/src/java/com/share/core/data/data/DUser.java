package com.share.core.data.data;

import com.share.core.annotation.AsMongoId;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;

@Pojo
public class DUser extends DSuper {
	@AsMongoId
	private int userId;
	private String name = "";

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}