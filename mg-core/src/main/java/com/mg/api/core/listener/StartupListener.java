/**
 * 应用启动监听器
 * create by ming 2016/11/17 
 */
package com.mg.api.core.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mg.api.core.helper.LocationManage;
import com.mg.api.dao.LocationDao;
import com.mg.api.model.Location;

/**
 * Application Lifecycle Listener implementation class appListener
 *
 */
public class StartupListener implements ServletContextListener {

	 private static Logger logger = LoggerFactory.getLogger(StartupListener.class);
	 
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
    	LocationDao locationDao = WebApplicationContextUtils.getWebApplicationContext(servletcontextevent.getServletContext()).getBean(LocationDao.class);
    	
    	/* 取得城市信息 */
    	logger.info("get location info...");
    	List<Location> locations = locationDao.getAll();
    	LocationManage.setLocations(locations);
    	/* end */
    }
   
    @Override
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletcontextevent) {
    	logger.info("应用程序关闭...");
    }
}