package com.shopping.cart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.modal.Category;
import com.shopping.cart.service.CategoryService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/getall")
	public ResponseEntity<?> findAll(
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(value = "searchText", defaultValue = "") String searchText){
		Page<Category> category= categoryService.findByCateName(searchText, pageNo, pageSize, sortBy);
		return ResponseEntity.ok().body(category);
	}
	@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> getById(@PathVariable("id") long id){
		Category cate=categoryService.getById(id);
		return ResponseEntity.ok().body(cate);
	}
}
