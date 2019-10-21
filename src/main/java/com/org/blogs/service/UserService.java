package com.org.blogs.service;

import java.util.List;

import com.org.blogs.entity.UserInfo;

public interface UserService {
	
	public int deleteByid(Integer id);
	 
	public int insert(UserInfo record);
 
    public UserInfo selectByid(Integer id);
    
    public UserInfo selectByCode(String code);
    
    public List<UserInfo> selectAll();

    UserInfo selectByUserNameAndPassWord(String userName,String password);

    UserInfo selectUserNameForLogin(String username);

    int insertUser(UserInfo info);

}
