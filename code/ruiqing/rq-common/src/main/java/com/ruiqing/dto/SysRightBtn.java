package com.ruiqing.dto;

import com.ruiqing.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Description 角色按钮关联
 * @ClassName Sysrolebutton.java
 * @CreationDate 2017年11月23日 上午11:28:57
 * @Version V1.0
 * @ModificationHistory
 */
@Entity
@Table(name = "sys_right_btn")
public class SysRightBtn extends BaseEntity implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 411115002516228515L;
	private String id;// 主键
	private String roleId;// 角色ID
	private String userId;// 用户ID
	private String btnId;// 按钮ID
	private String btnName;// 按钮名称
	private String createUserId;// 创建者
	private Date createTime;// 创建时间
	private String updateUserId;// 修改者
	private Date updateTime;// 修改时间
	private String url;
	private String btnCode;

	// Constructors

	/** default constructor */
	public SysRightBtn() {
	}

	/** minimal constructor */
	public SysRightBtn(String id) {
		this.id = id;
	}

	/** full constructor */
	public SysRightBtn(String id, String roleId, String userId, String btnId, String createUserId, Date createTime,
                       String updateUserId, Timestamp updateTime) {
		this.id = id;
		this.roleId = roleId;
		this.userId = userId;
		this.btnId = btnId;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.updateUserId = updateUserId;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@Column(name = "id")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "roleId")
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "userId")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "btnId")
	public String getBtnId() {
		return this.btnId;
	}

	public void setBtnId(String btnId) {
		this.btnId = btnId;
	}

	@Column(name = "createUserId")
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "createTime")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "updateUserId")
	public String getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the btnName
	 */
	public String getBtnName() {
		return btnName;
	}

	/**
	 * @param btnName
	 *            the btnName to set
	 */
	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBtnCode() {
		return btnCode;
	}

	public void setBtnCode(String btnCode) {
		this.btnCode = btnCode;
	}

}