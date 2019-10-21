package com.org.blogs.util;

import java.util.Random;

public class PartenrWebUtil {
	
	private static int rv = 0;
	static{
		Random random = new Random();
		rv = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
	}

	public static String getVersion() {
		return "v20160506";
	}
	
	/**
	 * 每次项目部署会随机产生一个版本号
	 * @return
	 */
	public static String getVersionRmd() {
		return "v" + rv;
	}
}
