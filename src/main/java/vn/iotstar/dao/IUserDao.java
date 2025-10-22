package vn.iotstar.dao;

import vn.iotstar.entity.User;

public interface IUserDao {
	void insert(User user);
    User findByEmail(String email);
    User findById(int id);
}
