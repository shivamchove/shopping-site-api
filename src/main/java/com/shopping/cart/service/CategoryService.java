package com.shopping.cart.service;

import org.springframework.data.domain.Page;

import com.shopping.cart.modal.Category;

public interface CategoryService {

	public Category getById(long id);
	
	public Page<Category> findByCateName(String cateName, Integer pageNo, Integer pageSize, String sortBy);
	
	public void delete(long id);
	
	public Category save(Category category);
	
	public Category update(Category category, long id);
}
