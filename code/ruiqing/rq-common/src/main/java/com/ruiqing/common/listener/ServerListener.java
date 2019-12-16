package com.ruiqing.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ServerListener implements ServletContextListener {
	protected static Logger logger = LoggerFactory.getLogger(ServerListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		logger.info("=================================");
		logger.info("系统[{}]启动完成!!!", contextEvent.getServletContext().getServletContextName());
		logger.info("=================================");
	}
}