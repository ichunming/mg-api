/**
 * 应用启动监听器
 * create by ming 2016/11/17 
 */
package com.ichunming.mg.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class appListener
 *
 */
public class StartupListener implements ServletContextListener {

	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
    /**
     * Default constructor. 
     */
    public StartupListener() {}

    @Override
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletcontextevent) {
    	logger.info("应用程序启动...");
    }
   
    @Override
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletcontextevent) {
    	logger.info("应用程序关闭...");
    }
}