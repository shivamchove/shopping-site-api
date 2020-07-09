package com.shopping.cart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.modal.ProductImage;
import com.shopping.cart.repository.ProductImageRepository;
import com.shopping.cart.service.ProductImageService;
import com.shopping.cart.service.UserService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	ProductImageRepository productImageRepository;

	@Autowired
	UserService userService;
	
	@Override
	public ProductImage getById(long id) {
		
		return productImageRepository.findById(id).get();
	}

	@Override
	public List<ProductImage> findAll() {
		
		return productImageRepository.findAll();
	}

	@Override
	public List<ProductImage> findByProductId(long productId) {
		return productImageRepository.findByProductId(productId);
	}

	@Override
	public void delete(long id) {
		productImageRepository.deleteById(id);
	}

	@Override
	public ProductImage save(ProductImage productImage) {
		productImage.setModifiedBy(userService.getLoggedinUserId());
		productImage.setAddedBy(userService.getLoggedinUserId());
		return productImageRepository.save(productImage);
	}

	@Override
	public ProductImage update(ProductImage productImage, long id) {
		ProductImage pi=this.getById(id);
		if(productImage.getProductId()>0) {
			pi.setProductId(productImage.getProductId());
		}
		if(productImage.getImageName()!=null && !productImage.getImageName().isEmpty()) {
			pi.setImageName(productImage.getImageName());
		}
		if(productImage.getImageDesc()!=null && !productImage.getImageDesc().isEmpty()) {
			pi.setImageDesc(productImage.getImageDesc());
		}
		if(productImage.getImagePath()!=null && !productImage.getImagePath().isEmpty()) {
			pi.setImagePath(productImage.getImagePath());
		}
		pi.setModifiedBy(userService.getLoggedinUserId());
		//productImageRepository.save(pi);
		return pi;
	}
	
	
}
