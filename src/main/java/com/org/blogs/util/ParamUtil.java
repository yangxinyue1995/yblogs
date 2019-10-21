package com.org.blogs.util;

import java.sql.Date;
import javax.servlet.http.*;



public class ParamUtil {
	

	public static String showCheckString(String temp)
	{
		System.out.println(temp);
		if(temp.indexOf("<pre>")>=0&&temp.indexOf("</pre>")>=0)//��һ��Ϊ0��û��Ϊ-1;
		{
			temp = temp.replaceAll("<pre>", " ");
			temp = temp.replaceAll("</pre>", " "); 
		}
		return temp; 
	}
	
	
	

	public static String getString(HttpServletRequest request, String paramName) {
		String temp = request.getParameter(paramName);
		
		if (temp != null && !temp.equals(""))
			return temp;//����
		else
			return null;
	}


	public static String getString(HttpServletRequest request, String paramName, String defaultString) {
		String temp = getString(request, paramName);
		
		if (temp == null)
			temp = defaultString;
		else
		{
			if(temp.indexOf("\r\n")>=0)
			{
				if(temp.indexOf("<pre>")>0&&temp.indexOf("</pre>")>0)
				{
//					System.out.println("+++++++++++request");
				}
				else
				{
//					System.out.println("-----------request");
					temp = "<pre>"+checkString(temp)+"</pre>";
				}
			}
			else
			{
//				System.out.println("///////////////////request");
			}
		}	
		
		return temp;//����
	}


	public static int getInt(HttpServletRequest request, String paramName) throws NumberFormatException {
		return Integer.parseInt(getString(request, paramName));
	}


	public static int getInt(HttpServletRequest request, String paramName, int defaultInt) {
		try {
			String temp = getString(request, paramName);

			if (temp == null)
				return defaultInt;
			else
				return Integer.parseInt(temp);

		} catch (NumberFormatException e) {
			return defaultInt;
		}
	}
	
	public static short getShort(HttpServletRequest request, String paramName) {
		return Short.parseShort(getString(request,paramName));
	}
	
	public static short getShort(HttpServletRequest request, String paramName, short defaultShort) {
		try {
			String temp = getString(request, paramName);

			if (temp == null)
				return defaultShort;
			else
				return Short.parseShort(temp);

		} catch (NumberFormatException e) {
			return defaultShort;
		}
	}

	public static Float getFloat(HttpServletRequest request, String paramName) throws NumberFormatException {
		return Float.parseFloat(getString(request, paramName));
	}
	public static Float getFloat(HttpServletRequest request, String paramName, Float defaultFloat) {
		try {
			String temp = getString(request, paramName);

			if (temp == null)
				return defaultFloat;
			else
				return Float.parseFloat(temp);

		} catch (NumberFormatException e) {
			return defaultFloat;
		}
	}
	
	
	

	public static Date getDate(HttpServletRequest request,String year,String month,String day){
		String birthday = ParamUtil.getString(request,year,"1900").trim()+"-"
		  + ParamUtil.getString(request,month,"1").trim()+"-"
		  + ParamUtil.getString(request,day,"1").trim();
		return Date.valueOf(birthday);
	}

	public static String [] getStringArray(HttpServletRequest request, String paramName){
		String []str_array = request.getParameterValues(paramName);
		if (str_array == null)
			return new String[0];
		return str_array;
	}
	public static int [] getIntArray(HttpServletRequest request, String paramName){
		String []str_array = getStringArray(request,paramName);
		int []x = new int[str_array.length];
		for (int i = 0; i < x.length; i++) {
			x[i] = Integer.parseInt(str_array[i]);
		}
		return x;
	}

	public static String showString(String str)
	{
		String amp=str;
		

		if(amp!=null && !amp.equals(""))
		  amp=amp.replaceAll("&amp;", "&");
		if(amp!=null && !amp.equals(""))
		  amp=amp.replaceAll("&lt;", "<");
		if(amp!=null && !amp.equals(""))
		  amp=amp.replaceAll("&gt;", ">");
		if(amp!=null && !amp.equals(""))
		  amp=amp.replaceAll("&#61;", "=");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("&#37;", "%");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("lik&#101;", "like");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("&#47;", "/");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("&#39;", "'");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("&#34;", "\"");		
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("sel&#101;ct", "select");		
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("jo&#105;n", "join");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("un&#105;on", "union");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("wh&#101;re", "where");		
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("ins&#101;rt", "insert");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("del&#101;te", "delete");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("up&#100;ate", "update");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("dro&#112;", "drop");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("cr&#101;ate", "create");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("mod&#105;fy", "modify");		
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("ren&#097;me", "rename");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("alt&#101;r", "alter");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("ca&#115;t;", "cast");
		return amp;
	}

	public static String checkString(String str){
		String amp=str;
		
	
		if(amp!=null && !amp.equals(""))
		  amp=amp.replaceAll("&", "&amp;");
		if(amp!=null && !amp.equals(""))
		  amp=amp.replaceAll("<", "&lt;");
		if(amp!=null && !amp.equals(""))
		  amp=amp.replaceAll(">", "&gt;");
		if(amp!=null && !amp.equals(""))
		  amp=amp.replaceAll("=", "&#61;");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("%", "&#37;");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("like", "lik&#101;");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("/", "&#47;");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("'", "&#39;");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("\"", "&#34;");		
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("select", "sel&#101;ct");		
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("join", "jo&#105;n");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("union", "un&#105;on");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("where", "wh&#101;re");		
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("insert", "ins&#101;rt");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("delete", "del&#101;te");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("update", "up&#100;ate");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("drop", "dro&#112;");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("create", "cr&#101;ate");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("modify", "mod&#105;fy");		
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("rename", "ren&#097;me");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("alter", "alt&#101;r");
		if(amp!=null && !amp.equals(""))
			  amp=amp.replaceAll("cast", "ca&#115;t");
		return amp;
	}
	

}
