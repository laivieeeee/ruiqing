package com.ruiqing.common.utils;

/**
 * 常量表
 * 
 * @author zhang
 */
public interface Constants {
	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	static final String Exception_Head = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :";
	/** 缓存键值 */
	//static final Map<Class<?>, String> cacheKeyMap = InstanceUtil.newHashMap();
	/** 操作名称 */
	static final String OPERATION_NAME = "OPERATION_NAME";
	/** 客户端语言 */
	static final String USERLANGUAGE = "userLanguage";
	/** 客户端主题 */
	static final String WEBTHEME = "webTheme";
	/** 当前用户 */
	static final String CURRENT_USER = "CURRENT_USER";
	/** 客户端信息 */
	static final String USER_AGENT = "USER-AGENT";
	/** 客户端信息 */
	static final String USER_IP = "USER_IP";
	/** 上次请求地址 */
	static final String PREREQUEST = "PREREQUEST";
	/** 上次请求时间 */
	static final String PREREQUEST_TIME = "PREREQUEST_TIME";
	/** 登录地址 */
	static final String LOGIN_URL = "/login.html";
	/** 非法请求次数 */
	static final String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
	/** 缓存命名空间 */
	static final String CACHE_NAMESPACE = "iBase4J:";
	/** 在线用户数量 */
	static final String ALLUSER_NUMBER = "SYSTEM:" + CACHE_NAMESPACE + "ALLUSER_NUMBER";
	/** TOKEN */
	static final String TOKEN_KEY = CACHE_NAMESPACE + "TOKEN_KEY";

	/** 日志表状态 */
	interface JOBSTATE {
		/**
		 * 日志表状态，初始状态，插入
		 */
		static final String INIT_STATS = "I";
		/**
		 * 日志表状态，成功
		 */
		static final String SUCCESS_STATS = "S";
		/**
		 * 日志表状态，失败
		 */
		static final String ERROR_STATS = "E";
		/**
		 * 日志表状态，未执行
		 */
		static final String UN_STATS = "N";
	}

	/**
	 * 任务调度组名
	 */
	String QUARTZ_GROUP_NAME = "mams";

	/**
	 * 任务调度的方法名
	 */
	String QUARTZ_JOB_METHOD_KEY = "jobMethod";

	/**
	 * 任务调度的参数key
	 */
	String QUARTZ_JOB_PARAM_KEY = "jobParam";

	/**
	 * 任务调度实例KEY
	 */
	String QUARTZ_JOB_INSTANCE_KEY = "_runTask";

	/**
	 * 表示提供的Job服务是否来源于dubbo，1-是
	 */
	String QUARTZ_DUBBO_SERVICE_JOB = "_dubboJob_";

	/**
	 * 表示提供的Job服务是否来源于dubbo，1-是
	 */
	String QUARTZ_JOB_METHOD_PARAMS_SEPARTOR = ",";

	/**
	 * 任务调度是同步
	 */
	String QUARTZ_JOB_SYNC = "1";
	
	/**
	 * token名称
	 */
	String TOKEN_NAME="crctoken";

	/**
	 * 组织维度
	 */
	String DIMENSION_ORG="wd_orgId";
	
	/**
	 * 角色字段名称
	 */
	String ROLE_ID="currRoleId";
	
	/**
	 * 数据过滤字段
	 */
	String DATA_SCOPE="dataScope";
	
	/**
	 * 语言编码
	 */
	String LANG_CODE="langCode";

	String SUPPORT_OFFSITE_LOGIN = "Y";

}
