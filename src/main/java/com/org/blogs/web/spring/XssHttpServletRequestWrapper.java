package com.org.blogs.web.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.org.blogs.util.encode.EncodeUtil;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		return EncodeUtil.encodeHtmlTag(super.getHeader(name));

	}

	@Override
	public String getQueryString() {
		return EncodeUtil.encodeHtmlTag(super.getQueryString());

	}

	@Override
	public String getParameter(String name) {
		return EncodeUtil.encodeHtmlTag(super.getParameter(name));
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
				escapseValues[i] = EncodeUtil.encodeHtmlTag(values[i]);

			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}
}
