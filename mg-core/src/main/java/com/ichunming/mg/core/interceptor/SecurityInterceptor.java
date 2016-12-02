package com.ichunming.mg.core.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ichunming.mg.common.constant.SystemSettings;
import com.ichunming.mg.common.util.SessionUtil;
import com.ichunming.mg.core.exception.InvalidSessionException;
import com.ichunming.mg.entity.SessionInfo;

public class SecurityInterceptor extends HandlerInterceptorAdapter{
	private static Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	public List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());

		if(excludeUrls.contains(url)) {
			return true;
		}
		
		SessionInfo sessionInfo = SessionUtil.getSessionInfo(request);
		// DEBUG MODE
		if(SystemSettings.DEBUG_MODE) {
			sessionInfo = new SessionInfo(1L, "test@test.com", "13761104110");
		}
		if (null == sessionInfo) {
			logger.debug("No Session." );
			throw new InvalidSessionException("2001","No Session.");
		} else {
			logger.debug("uid:" + sessionInfo.getUid().toString());
		}
		return true;
	}
}