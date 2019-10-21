package com.org.blogs.tld;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.org.blogs.util.PartenrWebUtil;

public class JavascriptTag implements Tag {
	
	private PageContext pageContext;

	private HttpServletRequest request;

	private HttpServletResponse response;

	private String name;

	private String contextPath;

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			String[] scripts;
			String script = "";
			if (getName().indexOf(",") != -1) {
				scripts = getName().split("[,]");
			} else {
				scripts = new String[] { getName() };
			}
			for (String str : scripts) {
				str = str.trim();
				script += "<script src=\"{0}/{1}.js?v={2}\" ></script>\n"
						.replace("{0}", getContextPath()).replace("{1}", str)
						.replace("{2}", PartenrWebUtil.getVersionRmd());
			}

			this.pageContext.getOut().println(script);
			this.pageContext.getOut().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Tag getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPageContext(PageContext arg0) {
		pageContext = arg0;
		setRequest((HttpServletRequest) pageContext.getRequest());
		setResponse((HttpServletResponse) pageContext.getResponse());
		setContextPath(getRequest().getContextPath());
	}

	@Override
	public void setParent(Tag arg0) {
		// TODO Auto-generated method stub

	}

}
