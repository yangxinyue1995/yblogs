package com.org.blogs.util;

/**
 * 全局的静态变量,用于全局变量的存放
 * 
 * 作者：yxy
 */
public class GlobalStatic {
	
	// *****************************************************系统默认值*****************************************************
	public static final String DEFAULT_VALUE_STR = "";
	public static final int DEFAULT_VALUE_INT = 0;
	// 默认验证码参数名称
	public static final String CURRENT_CAPTCHA = "captcha";
	//当前登录用户
	public static final String CURRENT_USER = "currentUser";
	// 加密次数
	public static final int ENCTYPTION_NUM = 10;
	// 默认密码
	public static final String DEFAULT_PASSWORD = "123456";
	// 网站标题
	public static final String SYS_TITLE = "sysTitle";
	// 网站标题
	public static final String SYS_COPYRIGHT = "sysCopyRight";
	
	// 视频服务
	public static final String SERVER_VIDEO = "video";
	// 主项目服务
	public static final String SERVER_MAIN = "main";
	// CXF服务
	public static final String SERVER_CXF = "cxf";
	// 主项目服务
	public static final String SERVER_MEMCACHED = "memcached";
	
	// 默认文件夹
	public static final String DEFAULT_FOLDER = "upload";
	// 临时文件夹
	public static final String TEMPPATH = "temp";
	// 照片存储文件夹
	public static final String PHOTOPATH = "photo";
	
	// 性别
	public static final int GENDER_SECRET = 0;
	public static final int GENDER_MAN = 1;
	public static final int GENDER_WOMAN = 2;
	
	public static final int SYS_YES = 1;
    public static final int SYS_NO = 0;
    
    // 删除状态
    public static final int STATUS_USE = 1;
    public static final int STATUS_HID = 0;
    public static final int STATUS_DEL = -1;
    // 转化状态
    public static final int CONVER_SUCCESS = 1;
    public static final int CONVER_FAILURE = -1;
    public static final int CONVER_NO = 0;
    
	// 文本
    public static final String MEDIATYP_TEXT = "text";
    // 图片
    public static final String MEDIATYP_IMAGE = "image";
	// 动画
    public static final String MEDIATYP_FLASH = "flash";
    // 视频
    public static final String MEDIATYP_VIDEO = "video";
    // 音频
    public static final String MEDIATYP_SOUNDE = "sound";
    // 其他
    public static final String MEDIATYP_OTHERS = "others";
    
}