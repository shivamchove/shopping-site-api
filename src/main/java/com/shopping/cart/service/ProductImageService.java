package com.shopping.cart.service;

import java.util.List;

import com.shopping.cart.modal.ProductImage;

public interface ProductImageService {

	public ProductImage getById(long id);
	
	public List<ProductImage> findAll();
	
	public List<ProductImage> findByProductId(long productId);
	
	public void delete(long id);
	
	public ProductImage save(ProductImage productImage);
	
	public ProductImage update(ProductImage productImage, long id);
}
