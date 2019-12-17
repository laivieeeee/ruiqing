package com.ruiqing.dto.base;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**   
 * @Description dto基础信息字段
 * @ClassName BaseDTO.java 
 * @CreationDate 2015年10月17日 下午2:31:09
 * @Version V1.0
 * @ModificationHistory
 *  Who        When                         What
 *  --------   -------------------------    -----------------------------------
 *  zhang     2017年10月17日 下午2:31:09      初始创建
 */
public abstract class BaseDTO {
	
	/***创建用户ID**/
	 @ApiModelProperty(hidden = true, required = false)
    protected String createUserId;
	 /***创建用户名**/
	 @ApiModelProperty(hidden = true, required = false)
    protected String createUserName;
	 /***创建日期**/
	 @ApiModelProperty(hidden = true, required = false)
    protected Date createTime;
    /***/
	 @ApiModelProperty(hidden = true, required = false)
    protected String createTimes;
	 /***修改人ID**/
	 @ApiModelProperty(hidden = true, required = false)
    protected String updateUserId;
	 /***修改人名称**/
	 @ApiModelProperty(hidden = true, required = false)
    protected String updateUserName;
	 /***修改日期**/
	 @ApiModelProperty(hidden = true, required = false)
    protected Date updateTime;
    /***/
	 @ApiModelProperty(hidden = true, required = false)
    protected String updateTimes;
    
	 @ApiModelProperty(hidden = true, required = false)
    private String id;
	 @ApiModelProperty(hidden = true, required = false)
    private String text;
    
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateTimes() {
		return createTimes;
	}
	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateTimes() {
		return updateTimes;
	}
	public void setUpdateTimes(String updateTimes) {
		this.updateTimes = updateTimes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	   
}
