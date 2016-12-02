/**
 * charset encoding filter
 * create by ming 2016/11/17
 */
package com.ichunming.mg.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class CharsetEncodingFilter
 */
public class CharsetEncodingFilter implements Filter {
	
	private String encodeString;
	
    /**
     * Default constructor. 
     */
    public CharsetEncodingFilter() {}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encodeString);
		response.setCharacterEncoding(encodeString);
		response.setContentType("Accept=application/json;charset=" + encodeString);
		System.out.println("encode filter....");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encodeString=fConfig.getInitParameter("encoding");
		if(null == encodeString || "".equals(encodeString)) {
			encodeString = "utf-8";
		}
	}
}
