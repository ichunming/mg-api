package com.ichunming.mg.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ichunming.mg.common.constant.SystemConstant;

public class CookieUtil {

	/**
	 * 取得cookie值
	 * @param request
	 * @param name
	 * @return
	 */
	public String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if(null == cookie) {
			return null;
		}
		return cookie.getValue();
	}
	
	/**
	 * 删除cookie
	 * @param response
	 * @param name
	 */
	public void deleteCookie(HttpServletResponse response, String name) {
		Cookie cookie = new Cookie(name, "");
        // Invalidate the cookie
        cookie.setMaxAge(0);
        cookie.setDomain(SystemConstant.DOMAIN);
        response.addCookie(cookie);
	}
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param domain
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
        // Save the cookie value for 1 month
        setCookie(response, name, value, 60 * 60 * 24 * 30);
    }
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        if (value == null) {
            value = "";
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setDomain(SystemConstant.DOMAIN);
        response.addCookie(cookie);
    }
	
	/**
	 * 取得cookie
	 * @param request
	 * @param name
	 * @return
	 */
	private Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		// check
		if(null == cookies || StringUtil.isEmpty(name)) {
			return null;
		}
		
		Cookie result = null;
		// search
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(name)) {
				result = cookie;
				if (request.getServerName().equals(cookie.getDomain())) {
                    break;
                }
			}
		}
		return result;
	}
}
