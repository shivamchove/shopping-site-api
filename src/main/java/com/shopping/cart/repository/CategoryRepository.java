package com.shopping.cart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.modal.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
	Page<Category> findByCateNameContaining(String cateName, Pageable pageable);
}
