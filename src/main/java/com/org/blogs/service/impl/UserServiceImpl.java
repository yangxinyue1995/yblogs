package com.org.blogs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.blogs.entity.UserInfo;
import com.org.blogs.mapper.UserInfoMapper;
import com.org.blogs.service.UserService;
@Service("UserService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public int deleteByid(Integer id) {
		// TODO Auto-generated method stub
		return userInfoMapper.deleteByid(id);
	}

	@Override
	public int insert(UserInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserInfo selectByid(Integer id) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByid(id);
	}

	@Override
	public List<UserInfo> selectAll() {
		return userInfoMapper.selectAll();
	}

	@Override
	public UserInfo selectByUserNameAndPassWord(String userName, String password) {
		return userInfoMapper.selectByUserNameAndPassWord(userName,password);
	}

	@Override
	public UserInfo selectUserNameForLogin(String username) {
		return userInfoMapper.selectUserNameForLogin(username);
	}

	@Override
	public int insertUser(UserInfo info) {
		return userInfoMapper.insertUser(info);
	}

	@Override
	public UserInfo selectByCode(String code) {
		// TODO Auto-generated method stub
		return userInfoMapper.SelectByCode(code);
	}
}