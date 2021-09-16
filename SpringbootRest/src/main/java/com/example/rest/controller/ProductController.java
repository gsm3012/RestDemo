package com.example.rest.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.entity.ProductEntity;

@RestController
public class ProductController 
{
	public static Map<Integer, ProductEntity> map = new HashMap<>();
	
	static {
		ProductEntity mobile = new ProductEntity();
		mobile.setId(1);
		mobile.setName("mobile");
		mobile.setPrice(30000);
		
		map.put(mobile.getId(), mobile);
		
		ProductEntity laptop = new ProductEntity();
		laptop.setId(2);
		laptop.setName("laptop");
		laptop.setPrice(60000);
		
		map.put(laptop.getId(), laptop);
		
		ProductEntity camera = new ProductEntity();
		camera.setId(3);
		camera.setName("Camere");
		camera.setPrice(300000);
		
		map.put(camera.getId(), camera);
	}
	
	@PostMapping("/product/add")
	public ResponseEntity<?> addProduct(@RequestBody ProductEntity product)
	{
		map.put(product.getId(), product);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getproducts")
	public ResponseEntity<?> getProduct()
	{
		return ResponseEntity.ok(map.values());
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProdductById(@PathVariable int id)
	{

		ProductEntity product = map.get(id);
		
		return ResponseEntity.ok(product);
	}
	
	
	@PutMapping("/product/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductEntity product) throws NullPointerException
	{
		
		if(map.containsKey(product.getId()))
		{
			ProductEntity newProduct = map.get(product.getId());
			
			newProduct.setId(product.getId());
			newProduct.setName(product.getName());
			newProduct.setPrice(product.getPrice());
			
			map.put(newProduct.getId(), newProduct);

		}
		else
		{
			throw new NullPointerException("Product not found with given Id");
		}
			
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/product/remove/{id}")
	public ResponseEntity<?> removeProduct(@PathVariable int id)
	{
		map.remove(id);
		return ResponseEntity.ok(map);
	}
}
