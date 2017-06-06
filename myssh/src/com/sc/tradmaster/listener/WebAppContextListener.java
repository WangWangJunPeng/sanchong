package com.sc.tradmaster.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sc.tradmaster.utils.SysContent;

/**
 * 
 * @author grl
 *
 */
public class WebAppContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		SysContent.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}

}
