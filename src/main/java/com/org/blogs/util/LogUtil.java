package com.org.blogs.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

/**
 */
public class LogUtil {
	/**
	 * 致命错误，导致系统中止。
	 * @param logName　配置的日志模块名称
	 * @param message　日志信息
	 * @throws 
	 */
	public static void fatal(String logName,Object message){
		Logger log = Logger.getLogger(logName);
		log.fatal(message);
	}
	
	/**
	 * 致命错误，导致系统中止。
	 * @param logName　配置的日志模块名称
	 * @param message　日志信息
	 * @param t 系统例外对象
	 * @throws 
	 */ 
	public static void fatal(String logName,Object message,Throwable t){
		Logger log = Logger.getLogger(logName);
		log.fatal(message,t);
	}
	
	
	/**
	 * 记录普通逻辑错误，但是不会导致系统中止。
	 * @param logName　日志实例名
	 * @param message　记录的日志信息对象
	 * @throws 
	 */
	public static void error(String logName,Object message) {
		Logger log = Logger.getLogger(logName);
		log.error(message);
	}
	/**
	 * 记录普通逻辑错误，不会导致系统中止
	 * @param logName　日志实例名
	 * @param message　日志信息
	 * @param t　例外对象
	 * @throws 
	 */ 
	public static void error(String logName,Object message,Throwable t){
		Logger log = Logger.getLogger(logName);
		log.error(message,t);
	}
	
	/**
	 * 记录警告信息
	 * @param logName　日志实例名
	 * @param message　日志信息
	 * @throws 
	 */
	public static void warn(String logName,Object message) {
		Logger log = Logger.getLogger(logName);
		log.warn(message);
	}
	/**
	 * 记录警告信息。
	 * @param logName　日志实例名
	 * @param message　日志信息
	 * @param t　例外实例
	 * @throws 
	 */
	public static void warn(String logName,Object message,Throwable t){
		Logger log = Logger.getLogger(logName);
		log.warn(message,t);
	}
	/**
	 * 记录提示信息，
	 * @param logName　日志实例名称
	 * @param message　日志信息
	 * @throws 
	 */
	public static void info(String logName,Object message){
		Logger log = Logger.getLogger(logName);
		log.info(message);
	}
		
	/**
	 * 记录提示信息。
	 * @param logName　日志实例名称
	 * @param message　日志信息
	 * @param t　例外对象信息
	 * @throws 
	 */
	public static void info(String logName,Object message,Throwable t) {
		Logger log = Logger.getLogger(logName);
		log.info(message,t);
	}
	/**
	 * 记录调试信息
	 * @param logName 日志实例名称
	 * @param message　日志信息
	 * @throws 
	 */
	public static void debug(String logName,Object message){
		Logger log = Logger.getLogger(logName);
		log.debug(message);
	}
	/**
	 * 记录调试信息。
	 * @param logName　日志实例名称
	 * @param message　日志信息
	 * @param t　例外对象
	 * @throws 
	 */
	public static void debug(String logName,Object message,Throwable t) {
		Logger log = Logger.getLogger(logName);
		log.debug(message,t);
	}
	
	/**
	 * 记录日志
	 * @param logName
	 * @param message
	 */
	public static void logTime(String message){
		Logger log = Logger.getLogger("CONSOLE");
		log.warn("when :"+new Date()+":"+message);
	}
	
}
