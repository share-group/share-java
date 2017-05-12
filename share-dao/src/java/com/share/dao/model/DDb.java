package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * Database privileges
 */
@Pojo
public class DDb extends DSuper {
	private String host;
	private String db;
	private String user;
	private String selectPriv;
	private String insertPriv;
	private String updatePriv;
	private String deletePriv;
	private String createPriv;
	private String dropPriv;
	private String grantPriv;
	private String referencesPriv;
	private String indexPriv;
	private String alterPriv;
	private String createTmpTablePriv;
	private String lockTablesPriv;
	private String createViewPriv;
	private String showViewPriv;
	private String createRoutinePriv;
	private String alterRoutinePriv;
	private String executePriv;
	private String eventPriv;
	private String triggerPriv;

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

	public String getSelectPriv() {
		return selectPriv;
	}

	public void setSelectPriv(String selectPriv) {
		this.selectPriv = selectPriv;
	}

	public String getInsertPriv() {
		return insertPriv;
	}

	public void setInsertPriv(String insertPriv) {
		this.insertPriv = insertPriv;
	}

	public String getUpdatePriv() {
		return updatePriv;
	}

	public void setUpdatePriv(String updatePriv) {
		this.updatePriv = updatePriv;
	}

	public String getDeletePriv() {
		return deletePriv;
	}

	public void setDeletePriv(String deletePriv) {
		this.deletePriv = deletePriv;
	}

	public String getCreatePriv() {
		return createPriv;
	}

	public void setCreatePriv(String createPriv) {
		this.createPriv = createPriv;
	}

	public String getDropPriv() {
		return dropPriv;
	}

	public void setDropPriv(String dropPriv) {
		this.dropPriv = dropPriv;
	}

	public String getGrantPriv() {
		return grantPriv;
	}

	public void setGrantPriv(String grantPriv) {
		this.grantPriv = grantPriv;
	}

	public String getReferencesPriv() {
		return referencesPriv;
	}

	public void setReferencesPriv(String referencesPriv) {
		this.referencesPriv = referencesPriv;
	}

	public String getIndexPriv() {
		return indexPriv;
	}

	public void setIndexPriv(String indexPriv) {
		this.indexPriv = indexPriv;
	}

	public String getAlterPriv() {
		return alterPriv;
	}

	public void setAlterPriv(String alterPriv) {
		this.alterPriv = alterPriv;
	}

	public String getCreateTmpTablePriv() {
		return createTmpTablePriv;
	}

	public void setCreateTmpTablePriv(String createTmpTablePriv) {
		this.createTmpTablePriv = createTmpTablePriv;
	}

	public String getLockTablesPriv() {
		return lockTablesPriv;
	}

	public void setLockTablesPriv(String lockTablesPriv) {
		this.lockTablesPriv = lockTablesPriv;
	}

	public String getCreateViewPriv() {
		return createViewPriv;
	}

	public void setCreateViewPriv(String createViewPriv) {
		this.createViewPriv = createViewPriv;
	}

	public String getShowViewPriv() {
		return showViewPriv;
	}

	public void setShowViewPriv(String showViewPriv) {
		this.showViewPriv = showViewPriv;
	}

	public String getCreateRoutinePriv() {
		return createRoutinePriv;
	}

	public void setCreateRoutinePriv(String createRoutinePriv) {
		this.createRoutinePriv = createRoutinePriv;
	}

	public String getAlterRoutinePriv() {
		return alterRoutinePriv;
	}

	public void setAlterRoutinePriv(String alterRoutinePriv) {
		this.alterRoutinePriv = alterRoutinePriv;
	}

	public String getExecutePriv() {
		return executePriv;
	}

	public void setExecutePriv(String executePriv) {
		this.executePriv = executePriv;
	}

	public String getEventPriv() {
		return eventPriv;
	}

	public void setEventPriv(String eventPriv) {
		this.eventPriv = eventPriv;
	}

	public String getTriggerPriv() {
		return triggerPriv;
	}

	public void setTriggerPriv(String triggerPriv) {
		this.triggerPriv = triggerPriv;
	}


}