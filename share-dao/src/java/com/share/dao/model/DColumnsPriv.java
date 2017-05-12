package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * Column privileges
 */
@Pojo
public class DColumnsPriv extends DSuper {
	private String host;
	private String db;
	private String user;
	private String tableName;
	private String columnName;
	private String timestamp;
	private String columnPriv;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getColumnPriv() {
		return columnPriv;
	}

	public void setColumnPriv(String columnPriv) {
		this.columnPriv = columnPriv;
	}


}