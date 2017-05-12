package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
@Pojo
public class DJson extends DSuper {
	private String name;
	private com.share.core.util.JSONObject json;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public com.share.core.util.JSONObject getJson() {
		return json;
	}

	public void setJson(com.share.core.util.JSONObject json) {
		this.json = json;
	}


}