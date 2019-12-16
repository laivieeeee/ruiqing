package com.ruiqing.enums;

/**
 * 接口响应提示
 * @author Administrator
 * @date  2017年10月11日
 */
public enum ErrorCodeEnum {

	SUCCESS("0", "PROCESS_SUCCESS"),//"处理成功"

	FAIL("-1", "PROCESS_FAIL"),// "服务器繁忙，请稍后重试"
	
	SESSION_TIMEOUT("-9", "SESSION_TIMEOUT"),//"登录超时,请登录后访问"
	
	SESSION_FORGE("-8", "SESSSION_PURGE"),//"您被管理员强制下线，请重新登录或联系管理员"
	
	SESSION_LOGIN_DUPLICATE("-7", "SESSION_INVALID_TOKEN"),//"Token失效，您的账户可能在异地登录，请检查账户安全或重新登录"
	
	ACCESS_WITHOUT_PERMISSION("-11", "PERMISSION_NOACCESS"),//"无权限访问"
	
	REPETITIVE_SUBMISSION("-1", "SUBMIT_REPETITIVE"),//"数据重复提交"
	
	PARAM_IS_NULL("-1", "PARAM_NULL"),//"传入参数L不能为空"
	
	PARAM_IS_ERROR("-1", "PARAM_FORMAT_WRONG"),//"传入参数格式不正确"
	
	ACCOUNT_IS_NULL("-1","ACCOUNT_NULL"),//"用户账号不能为空"
	
	ACCOUNT_NOT_EXISTED("-1","ACCOUNT_NOT_EXIST"),// "用户账号不存在"
	
	PASSWORD_IS_EMPTY("-1","PASSWORD_EMPTY"),//"用户密码不能为空"
	
	USER_LOGIN_USERID_IS_NULL("-1", "LOGIN_NO"),//"未登录或登录超时"

	NAME_EXISTED("-1", "NAME_EXIST"),//"名称已存在"
	
	CODE_EXISTED("-1", "CODE_EXIST"),//"编码已存在"
	
	NAME_CAN_NOT_BE_NULL("-1", "NAME_NOT_NULL"),//"名称不能为空"
	
	CODE_CAN_NOT_BE_NULL("-1", "CODE_NOT_NULL"),//"编码不能为空"

	SCHEDULE_JOB_CRON_SCHEDULE_INVALID("-1", "SCHEDULE_WRONG_CRON"),//"时间表达式格式异常"

	SCHEDULE_JOB_SUB_CLASS_INVALID("-1", "SCHEDULE_SUBTASK_FAIL"),//"子任务类创建失败"

	SCHEDULE_JOB_SAVE_FAIL("-1", "SCHEDULE_JOB_CREATE_FAIL"),//"创建调度任务失败"

	SCHEDULE_JOB_PAUSE_FAIL("-1", "SCHEDULE_JOB_PAUSE_FAIL"),//"暂停调度任务失败"

	SCHEDULE_JOB_RESUME_FAIL("-1", "SCHEDULE_JOB_RESUME_FAIL"),//"恢复调度任务失败"

	SCHEDULE_JOB_UPDATE_FAIL("-1", "SCHEDULE_JOB_UPDATE_FAIL"),//"更新调度任务失败"

	SCHEDULE_JOB_UPDATE_FAIL2("-1", "SCHEDULE_JOB_UPDATE_FAIL2"),//"更新调度任务失败，任务处理暂停状态"

	SCHEDULE_JOB_DELETE_FAIL("-1", "SCHEDULE_JOB_DELETE_FAIL"),//"更新调度任务失败"

	SCHEDULE_JOB_RUN_ONCE_FAIL("-1", "SCHEDULE_JOB_RUN_ONCE_FAIL"),//"调度任务启动一次失败"

	SCHEDULE_JOB_ALREADY_EXIST("-1", "SCHEDULE_JOB_ALREADY_EXIST"),//"该调度任务已经存在"

	SCHEDULE_JOB_NOT_EXIST("-1", "SCHEDULE_JOB_NOT_EXIST"),//"该调度任务不存在"

	SCHEDULE_JOB_CHECK_EXIST_ERROR("-1", "SCHEDULE_JOB_CHECK"),//"检查调度任务是否存在出现异常"

	SCHEDULE_JOB_QUERY_SPECIFIC_TRIGGER_ERROR("-1", "SCHEDULE_JOB_TRIGGER_ERROR"),//"获取指定的Trigger出现异常"

	SCHEDULE_JOB_QUERY_SPECIFIC_TRIGGER_STATE_ERROR("-1", "SCHEDULE_JOB_TRIGGER_STATUS_ERROR"),//"获取指定的Trigger state出现异常"

	SCHEDULE_JOB_QUERY_QUERY_GROUP_FAIL("-1", "SCHEDULE_JOB_GROUP_FAIL"),//"获取任务组异常"

	SCHEDULE_JOB_QUERY_QUERY_JOB_KEY_FAIL("-1","SCHEDULE_JOB_KEY_FAIL"),//"获取任务KEY异常"

	SCHEDULE_JOB_QUERY_QUERY_JOB_DETAIL_FAIL("-1", "SCHEDULE_JOB_DETAIL_FAIL"),//"获取任务DETAIL异常"

	SCHEDULE_JOB_NOT_CRON_TRIGGER("-1","SCHEDULE_JOB_CRON_TRIGGER"),//"不是Cron Trigger"

	REDIS_EXCEPTION("-999","REDIS_EXCEPTION"),//"服务器异常，请联系管理员[errorCode:-999]"

	RESOURCE_MANAGE_DELETE_ASSCOCIATE_ROLE("-1","RESOURCE_ALREADY_ASSIGN_NO_DELETE"),//"资源已经被分配角色，不允许被删除"

	RESOURCE_MANAGE_DUPLICATION("-1","RESOURCE_CODE_DUPLICATE" ),//"资源编号有重复"

	RESOURCE_MANAGE_BUTTON_CODE_PATTERN_NOT_MATCH("-12", "RESOURCE_BUTTON_CODE_PATTERN_INVALID"),//"创建的资源编号不匹配，【菜单_资源】规划，比如，MENU_RESOURCE"
	
	UNKONOWN_SERVER_ERROR("-1", "UNKNOW_SERVER_ERROR"),//"未知服务器错误，请联系管理员"

	WS_SUCCESS_CODE("S000A000", "WS_SUCCESS_CODE"),//"Web Service调用成功"

	WS_USER_AUTH_FAIL("E0050001", "WS_USER_AUTH_FAIL"),//"Web Service授权用户失败"

	WS_USER_CREATE_FAIL("E0050002", "WS_USER_CREATE_FAIL"),//"Web Service创建用户失败"

	WS_USER_DELETE_FAIL("E0050003", "WS_USER_DELETE_FAIL"),//"Web Service删除用户失败"

	WS_USER_DISABLE_FAIL("E0050004", "WS_USER_DISABLE_FAIL"),//"Web Service失效用户失败"

	WS_USER_ENABLE_FAIL("E0050005", "WS_USER_ENABLE_FAIL"),//"Web Service激活用户失败"

	WS_USER_EDIT_FAIL("E0050006", "WS_USER_EDIT_FAIL"),//"Web Service编辑用户失败"
	
	UNAUTHORIZED("-1","SERVICE_UNAUTHORIZED"),//"服务访问未授权"
	
	UPDATE_ZERO_ROWS("-1", "UPDATE_VERSION_CHANGE_FAIL"),//"修改失败，记录状态或版本号可能已变更，请刷新后重试"
	
	MENU_NAME("-1","MENU_NAME_NOT_EMPTY"),//"菜单名称不能为空"

	MENU_CODE("-1","MENU_CODE_NOT_EMPTY"),//"菜单编码不能为空"
	
	NOT_FIND_ROLE("-1","ROLE_NOT_FIND");//"未找到要修改的角色"

	ErrorCodeEnum(String statusCode, String resourceKey){
		this.statusCode = statusCode;
		this.key = resourceKey;
	}
	
	private final String statusCode;

	private String key;
	
	public String getStatusCode(){
		return statusCode;
	}
	/*public static String getMessagebyKey(String key){
		return Resources.getMessage(key);
	}*/

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
}
