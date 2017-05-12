package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
@Pojo
public class DUse1r extends DSuper {
	private int uid;
	private String data;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


}