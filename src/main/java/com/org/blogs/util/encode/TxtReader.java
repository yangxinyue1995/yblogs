package com.org.blogs.util.encode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 敏感字读取帮助类
 * @author wlzj
 *
 */
public class TxtReader {
	public TxtReader() {
		super();
	}

	public static BufferedReader keywordReader(String fileName) {
		BufferedReader br = null;
		InputStream in = null;
		try {
			
			in = TxtReader.class.getResourceAsStream(fileName);
			InputStreamReader inReader = new InputStreamReader(in, "UTF-8");

			br = new BufferedReader(inReader);

		}  catch (UnsupportedEncodingException e) {
			System.out.println("你指定的编码类型不支持哦！！！");
			e.printStackTrace();
		}finally{
			/*try {
				//in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		return br;

	}
}
