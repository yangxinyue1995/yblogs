/** 
 * Project Name:Partenr-service 
 * File Name:PictureUtil.java 
 * Package Name:com.wl.cn.util 
 * Date:2016年7月15日上午10:28:59 
 * Copyright (c) 2016, 蔚蓝网 All Rights Reserved. 
 * 
 */
package com.org.blogs.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * ClassName: PictureUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年7月15日 上午10:28:59 <br/>
 * 
 * @author FW
 * @version
 * @since JDK 1.7
 */
public class PictureUtil {
	
	public static byte[] loadImgByUrl(String imgUrl) {
		byte[] byteArray = null;
		try {
			// 下载图片
			URL imUrl = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection) imUrl.openConnection();
			conn.setConnectTimeout(5 * 1000);
			InputStream is = conn.getInputStream();
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			int len;
			byte[] img = new byte[1024];
			while ((len = is.read(img)) != -1) {
				bao.write(img, 0, len);
			}
			is.close();
			bao.close();
			byteArray = bao.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteArray;
	}

	public static Integer getPicSizeByUrl(String imgUrl) {
		Integer size = 0;
		try {
			URL imUrl = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection) imUrl.openConnection();
			conn.setConnectTimeout(5 * 1000);
			InputStream is = conn.getInputStream();
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			int len;

			byte[] img = new byte[1024];
			while ((len = is.read(img)) != -1) {
				bao.write(img, 0, len);
			}
			is.close();
			bao.close();
			byte[] byteArray = bao.toByteArray();
			size = byteArray.length;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	public static void savePictureToLocal(String src, String fileName) {
		try {
			URL url = new URL(src);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			InputStream is = conn.getInputStream();
			OutputStream os = new FileOutputStream(fileName);
			int len;
			byte[] img = new byte[1024];
			while ((len = is.read(img)) != -1) {
				os.write(img, 0, len);
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存图片
	 * 
	 * @param im
	 *            流
	 * @param fileExt
	 * @param name
	 * @param savePath
	 * @return
	 */
	public static boolean writeImage(byte[] imgbt, String fileExt, String name, String savePath) throws Exception {
		
		ByteArrayInputStream is = new ByteArrayInputStream(imgbt);  
		FileOutputStream os = null;
		try {
			
			File imgFile = new File(savePath);
			if (!imgFile.exists()) {
				imgFile.mkdirs();
			}
			os = new FileOutputStream(imgFile.getPath() + "\\" + name + fileExt);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				os.write(b, 0, len);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}

		}
		return false;

	}
}
