package com.shopping.cart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.modal.Category;
import com.shopping.cart.modal.Product;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
	//Page<Product> findByProductNameContaining(String productName, Pageable pageable);
	Page<Category> findByCateNameContaining(String cateName, Pageable pageable);
}
