/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kiatnakinbank.naos.flowmanagementservice.entity.base;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.common.util.DateUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 3504966882328890323L;

	@Column(name = "IS_ACTIVE")
	@Enumerated(EnumType.STRING)
	private ActiveFlag isActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	@Column(name = "UPDATE_USER")
	private String updateUser;

	public BaseEntity() {
		// Constructor Method
	}

	public void setCreateAttribute(String username) {
		this.isActive = ActiveFlag.Y;
		this.createDate = DateUtil.getSysTimestamp();
		this.createUser = username;
		this.updateDate = DateUtil.getSysTimestamp();
		this.updateUser = username;
	}

	public void setUpdateAttribute(String username) {
		this.updateDate = DateUtil.toDay();
		this.updateUser = username;
	}

	/**
	 * @return the isActive
	 */
	public ActiveFlag getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(ActiveFlag isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
