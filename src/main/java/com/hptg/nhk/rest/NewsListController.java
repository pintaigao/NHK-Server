package com.hptg.nhk.rest;

import java.util.List;

import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hptg.nhk.entity.NewsList;
import com.hptg.nhk.service.NewsListService;
import com.hptg.nhk.utils.GetNewsDetail;

@RestController
@RequestMapping(value = "/api")
public class NewsListController {

	// autowire the CustomerService
	@Autowired
	private NewsListService customerService;
	
	// add mapping for GET /customers
	@GetMapping("/news")
	public List<NewsList> getNewsList(){
		return customerService.getNewsList();
	}

/*	@GetMapping("/news/{news_id}")
	public JSONObject getNewsDetail(@PathVariable String news_id){
		return GetNewsDetail.getNewsDetail(news_id);
	}*/

	@GetMapping(value = "/news/{news_id}",produces="text/html;charset=UTF-8")
	public String getNewsDetail(@PathVariable String news_id){
		return GetNewsDetail.getNewsDetail(news_id);
	}




}


















