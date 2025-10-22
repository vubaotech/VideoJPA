package vn.iotstar.service.impl;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;
import vn.iotstar.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao userDao = new UserDaoImpl();

    @Override
    public void register(User user) throws Exception {
        if (userDao.findByEmail(user.getEmail()) != null) {
            throw new Exception("Email đã tồn tại!");
        }
        // Mặc định là user
        user.setAdmin(false); 
        userDao.insert(user);
    }

    @Override
    public User login(String email, String password) {
        User user = userDao.findByEmail(email);
        
        if (user != null && user.getPassword().equals(password)) {
            // Login thành công
            return user;
        }
        // Login thất bại
        return null;
    }
}
