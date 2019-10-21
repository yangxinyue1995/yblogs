package com.org.blogs.util;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 工具类
 * 
 * @author yxy
 */
public class SysUtils {
	
	/**
	 * MD5加密(32位)
	 */
	public final static String encoderByMd5With32Bit(String password) {
		MessageDigest md5;
		try {
			// 生成一个MD5加密计算摘要ܼ
			md5 = MessageDigest.getInstance("MD5");
			for (int i=0; i<GlobalStatic.ENCTYPTION_NUM; i++) {
				// 计算md5函数
				md5.update(password.getBytes());
				// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
				// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
				password = new BigInteger(1, md5.digest()).toString(16);
			}
		} catch (Exception e) {
		}
		return password;
	}
	
	/**
	 * 获取随机的UUID字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
     * 验证为空
     */
	public static boolean isEmpty (Object obj) {
		// 判断参数是否为空或者''
        if (obj == null || "".equals(obj) || "null".equals(obj)) {
        	return true;
        } else if ("java.lang.String".equals(obj.getClass().getName())) {
        	// 判断传入的参数的String类型的
        	
            // 替换各种空格
            String tmobj = Pattern.compile("//r|//n|//u3000").matcher((String)obj).replaceAll("");
            // 匹配空
            return Pattern.compile("^(//s)*$").matcher(tmobj).matches();
        } else {
            // 方法类
            Method method = null;
            String newInput = "";
            try {
                // 访问传入参数的size方法
                method = obj.getClass().getMethod("size");
                // 判断size大小
                
                // 转换为String类型
                newInput = String.valueOf(method.invoke(obj));
                // size为0的场合
                if (Integer.parseInt(newInput) == 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                // 访问失败
                try {
                    // 访问传入参数的getItemCount方法
                    method = obj.getClass().getMethod("getItemCount");
                    // 判断size大小
                    
                    // 转换为String类型
                    newInput = String.valueOf(method.invoke(obj));
                    // getItemCount为0的场合
                    if (Integer.parseInt(newInput) == 0) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception ex) {
                    // 访问失败
                    try{
                        // 判断传入参数的长度
                        // 长度为0的场合
                        if (Array.getLength(obj) == 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } catch (Exception exx) {
                        // 访问失败
                        try{
                            // 访问传入参数的hasNext方法
                            method = Iterator.class.getMethod("hasNext");
                            // 转换String类型
                            newInput = String.valueOf(method.invoke(obj));
                            // 转换hasNext的值
                            if (!Boolean.valueOf(newInput)) {
                                return true;
                            } else {
                                return false;
                            }
                        } catch (Exception exxx) {
                            // 以上场合不满足
                            return false;
                        }
                    }
                }
            }
        }
    }
}