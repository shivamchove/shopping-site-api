package com.shopping.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shopping.cart.modal.Category;
import com.shopping.cart.repository.CategoryRepository;
import com.shopping.cart.service.CategoryService;
import com.shopping.cart.service.UserService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public Category getById(long id) {
		Category cate=categoryRepository.findById(id).get();
		return cate;
	}

	

	@Override
	public void delete(long id) {
		
		
	}

	@Override
	public Category save(Category category) {
		category.setModifiedBy(userService.getLoggedinUserId());
		category.setAddedBy(userService.getLoggedinUserId());
		categoryRepository.save(category);
		return category;
	}

	@Override
	public Category update(Category category, long id) {
		Category cate=categoryRepository.findById(id).get();
		category.setModifiedBy(userService.getLoggedinUserId());
		if(category.getCateName()!="") {
			cate.setCateName(category.getCateName());
		}
		if(category.getCateDesc()!="") {
			cate.setCateDesc(category.getCateDesc());
		}
		if(category.isActive()) {
			cate.setActive(category.isActive());
		}
		categoryRepository.save(cate);
		return cate;
	}



	@Override
	public Page<Category> findByCateName(String cateName, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return categoryRepository.findByCateNameContaining(cateName, pageable);
	}

	
}
