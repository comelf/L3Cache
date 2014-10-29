package org.l3cache.search;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.l3cache.dto.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.search.ApiCaller;
import core.search.SearchHelper;

@Controller
@RequestMapping("/search")
public class SearchController {
	private static final Logger log = LoggerFactory
			.getLogger(SearchController.class);
	
	@Autowired
	SearchHelper searchHelper;
	
	@Resource(name="naverApiCaller")
	ApiCaller apiCaller;
	
	@RequestMapping(value="/naver/shop", method={RequestMethod.POST, RequestMethod.GET})
	public void searchWithQuery(@RequestParam(value="query") String query,
								@RequestParam(value="display") int display,
								@RequestParam(value="start") int start,
								@RequestParam(value="sort", defaultValue="sim") String sort,
								Model model){
		
		long startTime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("display", display);
		params.put("start", start);
		params.put("query", query);
		params.put("sort", sort);
		
		List<Item> list = searchHelper.searchNaverApi(params);
		
		if(list!=null && !list.isEmpty()){
			model.addAttribute(list);
		}
		model.addAttribute("");
		
		long endTime = System.currentTimeMillis();
		log.debug("search query = '{}', time ={}" , query,((endTime - startTime )/1000.0)); 
	}
	
	@RequestMapping("/naver/shop/test")
	public void search(Model model, HttpServletRequest request){
		
		String qu = request.getParameter("query");
		
		log.debug("requst = {}", qu);
		
		String query = "청바지";
		long startTime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("display", 10);
		params.put("start",1);
		params.put("query", query);
		params.put("sort", "sim");
		
		List<Item> list = searchHelper.searchNaverApi(params);
		model.addAttribute(list);
		long endTime = System.currentTimeMillis();
		log.debug("test query = '{}', time ={}" , query,((endTime - startTime )/1000.0)); 
	}
	
}
