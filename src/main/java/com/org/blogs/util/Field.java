package com.org.blogs.util;

public class Field {

	private String key = null;
	private Object value = null;
	private String option = null;
	
	
	
	/**设置带默认操作（=）*/
	public Field(String key, Object value) {
		this.key = key;
		this.value = value;
		this.option = "=";
	}
	
	public Field(String key,String option,Object value) {
		this.key = key;
		this.option = option;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	
	
	
	
	

}
