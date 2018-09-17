package com.hptg.nhk.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hptg.nhk.entity.NewsList;
import com.hptg.nhk.service.CustomerService;

@RestController
@RequestMapping("/api")
public class NewsListController {

	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	
	// add mapping for GET /customers
	@GetMapping("/get_news")
	public List<NewsList> getNewsList(){
		return customerService.getNewsList();
	}
	
}


















