package com.org.blogs.util;

import java.text.NumberFormat;
import java.util.Locale;

public class StringUtil {

	public void test() {

	}

	public static String getThreeNumber(String a) {
		for (int i = 0; i < 3; i++) {
			if (a.length() < 3) {
				a = "0" + a;
			}
		}
		return a;
	}

	public static String getFourNumber(String a) {
		for (int i = 0; i < 4; i++) {
			if (a.length() < 4) {
				a = "0" + a;
			}
		}
		return a;
	}

	public static String getProviceCity(String address) {
		if (!address.contains(",")) {
			return address;
		}
		return address.substring(0, address.lastIndexOf(",")).replaceAll(",",
				"");
	}

	public static String getAddress(String address) {

		return address.replaceAll(",", "");
	}

	public static String formatNumber(float number) {
		NumberFormat f = NumberFormat.getInstance(Locale.US);
		return f.format(number).toString();
	}

}
