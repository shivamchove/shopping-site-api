package com.shopping.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shopping.cart.modal.Product;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.service.ProductService;
import com.shopping.cart.service.UserService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public Product getById(long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).get();
	}


	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product save(Product product) {
		product.setModifiedBy(userService.getLoggedinUserId());
		product.setAddedBy(userService.getLoggedinUserId());
		product.setActive(true);
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product, long id) {
		Product prod=this.getById(id);
		if(product.getCateId()>0) {
			prod.setCateId(product.getCateId());
		}
		if(product.getProductName()!=null && !product.getProductName().isEmpty()) {
			prod.setProductName(product.getProductName());
		}
		if(product.getProductDesc()!=null && !product.getProductDesc().isEmpty()) {
			prod.setProductDesc(product.getProductDesc());
		}
		if(product.getPrice()!=null) {
			prod.setPrice(product.getPrice());
		}
		productRepository.save(prod);
		return prod;
	}

	@Override
	public Page<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy){
    
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return productRepository.findAll(pageable);
	}


	@Override
	public Page<Product> findByProductName(String productName, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return productRepository.findByProductNameContaining(productName, pageable);
	}
}
