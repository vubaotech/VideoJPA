package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.entity.Category;
import vn.iotstar.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	@Override

	public void insert(Category category) {

		categoryDao.insert(category);

	}

	@Override

	public void update(Category category) {

		categoryDao.update(category);

	}

	@Override

	public void delete(int cateid) throws Exception {

		categoryDao.delete(cateid);

	}

	@Override

	public Category findById(int cateid) {

		return categoryDao.findById(cateid);

	}

	@Override

	public List<Category> findAll() {

		return categoryDao.findAll();

	}

	@Override

	public List<Category> findByCategoryname(String catname) {

		return categoryDao.findByCategoryname(catname);

	}

	@Override

	public List<Category> findAll(int page, int pagesize) {

		return categoryDao.findAll(page, pagesize);

	}

	@Override

	public int count() {

		return categoryDao.count();

	}
}
