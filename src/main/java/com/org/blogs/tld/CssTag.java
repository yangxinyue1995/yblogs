package com.org.blogs.tld;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.org.blogs.util.PartenrWebUtil;

public class CssTag implements Tag {
	private PageContext pageContext;

	private HttpServletRequest request;

	private HttpServletResponse response;

	private String name;

	private String contextPath;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
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
			String link = "";
			String[] links;
			if (getName().indexOf(",") != -1) {
				links = getName().split("[,]");
			} else {
				links = new String[] { getName() };
			}
			for (String str : links) {
				str = str.trim();
				link += "<link rel=\"stylesheet\" href=\"{0}/{1}.css?v={2}\"/>\n"
						.replace("{0}", getContextPath()).replace("{1}", str)
						.replace("{2}", PartenrWebUtil.getVersionRmd());
			}
			this.pageContext.getOut().println(link);
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
