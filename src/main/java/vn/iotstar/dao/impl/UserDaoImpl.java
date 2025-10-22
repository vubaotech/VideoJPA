package vn.iotstar.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.entity.User;

public class UserDaoImpl extends AbstractEntityDao<User> implements IUserDao {
	
	public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            TypedQuery<User> query = enma.createNamedQuery("User.findByEmail", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            // Không tìm thấy user
            return null;
        } finally {
            enma.close();
        }
    }

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
