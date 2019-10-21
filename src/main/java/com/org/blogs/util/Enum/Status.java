package com.org.blogs.util.Enum;

import java.util.HashMap;
import java.util.Map;

public enum Status {
	
	ready(1,"待上传"),
	success(2,"成功"),
	err(3,"失败");
	
	private Integer id;

	private String name;
	
	private static Map<Integer, String> map = new HashMap<Integer, String>();

	static {
		for (Status status : Status .values()) {
			map.put(status.getId(), status.getName());
		}
	}
	
	public static Map<Integer, String> getMap() {
		return map;
	}

	public static void setMap(Map<Integer, String> map) {
		Status.map = map;
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

	private Status(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
