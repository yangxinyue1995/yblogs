package com.org.blogs.mapper;

import java.util.List;

import com.org.blogs.entity.UserInfo;

public interface UserInfoMapper {
	
	int deleteByid(Integer id);
	 
    int insert(UserInfo record);
 
    UserInfo selectByid(Integer id);
    
    UserInfo SelectByCode(String code);
    
    List<UserInfo> selectAll();

    UserInfo selectByUserNameAndPassWord(String userName,String password);

    UserInfo selectUserNameForLogin(String username);

    int insertUser(UserInfo info);

}
