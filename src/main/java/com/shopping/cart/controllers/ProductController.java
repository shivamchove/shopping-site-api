package com.shopping.cart.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.cart.modal.Product;
import com.shopping.cart.modal.ProductImage;
import com.shopping.cart.service.ProductImageService;
import com.shopping.cart.service.ProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductImageService productImageService;
	
	@Value("${product.image.path}")
	String productImagePath;
	
	@Value("${product.image.dir}")
	String productImageDir;
	
	@GetMapping("/getall")
	public ResponseEntity<?> findAll(
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(value = "searchText", defaultValue = "") String searchText){
		Page<Product> products= productService.findByProductName(searchText, pageNo, pageSize, sortBy);
		return ResponseEntity.ok().body(products);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Product product){
		
		//productService.save(product);
		return ResponseEntity.ok().body("Saved");
	}
	
	@PostMapping(value = "/fileupload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> saveFile(@RequestParam("productImage") MultipartFile[] productImage, @RequestParam("product") String product ){
		
		Product prod;
		try {
			ObjectMapper om=new ObjectMapper();
			prod=om.readValue(product, Product.class);
			productService.save(prod);
			if(productImage!=null) {
				
				for(int i=0; i<productImage.length; i++) {
					
					String fileName=prod.getId()+"_"+productImage[i].getOriginalFilename();
					String fileDest=productImagePath+productImageDir+fileName;
					
					byte[] bytes = productImage[i].getBytes();
		            Path path = Paths.get(fileDest);
		            Files.write(path, bytes);
		            
					ProductImage prodImg=new ProductImage();
					prodImg.setActive(true);
					prodImg.setImageName(fileName);
					prodImg.setProductId(prod.getId());
					prodImg.setImagePath(productImageDir);
					
					System.out.println("File Name: "+ productImage[i].getOriginalFilename());
					productImageService.save(prodImg);
				}
			}			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body("Product saved successfully.");
	}
}
