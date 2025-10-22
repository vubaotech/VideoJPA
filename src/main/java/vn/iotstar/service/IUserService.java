package vn.iotstar.service;

import vn.iotstar.entity.User;

public interface IUserService {
	void register(User user) throws Exception;
    User login(String email, String password);
}
