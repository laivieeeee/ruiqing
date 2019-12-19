package com.ruiqing.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 国际化支持 Created by zlb on 2017年12月19日
 */
public final class Resources {
	/** 国际化信息 */
	private static final Map<String, ResourceBundle> MESSAGES = new HashMap<String, ResourceBundle>();

	/**
	 * 定义记录日志信息
	 */
	protected static Logger logger = LoggerFactory.getLogger(Resources.class);

	/** 国际化信息 */
	public static String getMessage(String key, Object... params) {
		Locale locale = LoginUserSessionHelper.getLoginUserLocale(getRequest());
		if (logger.isDebugEnabled()) {
			logger.debug(" locale :{}", JSONUtils.toJSONString(locale));
		}
		if (locale == null) {
			locale = new Locale("zh","CN");
			if (logger.isDebugEnabled()) {
				logger.debug(" locale is null:{}", JSONUtils.toJSONString(locale));
			}
		}

		ResourceBundle resourceBundle = MESSAGES.get(locale.getLanguage());
		if (resourceBundle == null) {
			synchronized (MESSAGES) {
				resourceBundle = MESSAGES.get(locale.getLanguage());
				if (resourceBundle == null) {
					resourceBundle = ResourceBundle.getBundle("messages", locale);
					MESSAGES.put(locale.getLanguage(), resourceBundle);
				}
			}
		}
		if (params != null && params.length > 0) {
			return String.format(resourceBundle.getString(key), params);
		}
		return resourceBundle.getString(key);
	}

	/** 清除国际化信息 */
	public static void flushMessage() {
		MESSAGES.clear();
	}
	
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
}