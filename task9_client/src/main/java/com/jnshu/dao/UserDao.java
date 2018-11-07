package com.jnshu.dao;

import com.jnshu.pojo.User;

import java.util.List;

public interface UserDao {
	public long add(User u);
	public User selectOne(long uid);
	public List<User> select();
	public User findUser(User u);
	public boolean update(User u);
}
