package com.org.blogs.util.Enum;

import java.util.HashMap;
import java.util.Map;

public enum Source {
	
	tb(1,"淘宝天猫"),
	jd(2,"京东"),
	other(3,"1688"),
	pdd(4,"拼多多");
	
	private Integer id;

	private String name;
	
	private static Map<Integer, String> map = new HashMap<Integer, String>();

	static {
		for (Source source : Source .values()) {
			map.put(source.getId(), source.getName());
		}
	}
	
	public static Map<Integer, String> getMap() {
		return map;
	}

	public static void setMap(Map<Integer, String> map) {
		Source.map = map;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Source(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
