package com.shopping.cart.service;

import org.springframework.data.domain.Page;

import com.shopping.cart.modal.Product;

public interface ProductService {
	
	public Product getById(long id);
	
	public Page<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy);
	
	public Page<Product> findByProductName(String productName, Integer pageNo, Integer pageSize, String sortBy);
	
	public void delete(long id);
	
	public Product save(Product product);
	
	public Product update(Product product, long id);
}
