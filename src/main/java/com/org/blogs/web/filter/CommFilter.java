package com.org.blogs.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.blogs.web.spring.XssHttpServletRequestWrapper;

public class CommFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain arg2) throws IOException, ServletException {
		// 转换对象
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String path = request.getContextPath();
		int port = request.getServerPort();
		String website = request.getScheme() + "://" + request.getServerName() + (port == 80 ? "" : (":" + port)) + path;
		request.setAttribute("root", website);

		arg2.doFilter(new XssHttpServletRequestWrapper(request), response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
