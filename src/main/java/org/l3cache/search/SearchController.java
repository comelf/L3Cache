package org.l3cache.search;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.l3cache.dao.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import core.search.ApiCaller;
import core.search.EHCacheService;
import core.search.QueryValidator;
import core.search.SearchHelper;
import core.utils.ResultCode;

@Controller
@RequestMapping("/search")
public class SearchController {
	private static final Logger log = LoggerFactory
			.getLogger(SearchController.class);
	
	@Autowired
	SearchHelper searchHelper;
	
	@Resource(name="naverApiCaller")
	ApiCaller apiCaller;
	
	@Autowired
	EHCacheService ehCacheService;
	
	@RequestMapping(value="/shop", method={RequestMethod.POST, RequestMethod.GET})
	public void searchWithQuery(@RequestParam(value="query") String query,
								@RequestParam(value="display") int display,
								@RequestParam(value="start") int start,
								@RequestParam(value="sort", defaultValue="sim") String sort,
								Model model){
		
		if(new QueryValidator().validate(query)){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("display", display);
			params.put("start", start);
			params.put("query", query);
			params.put("sort", sort);
			
			log.debug("search query = {}", query);
			model.addAttribute(ehCacheService.getResponse(params));	
		}else{
			model.addAttribute(new Response(1));
		}

	}
	
	@RequestMapping("/shop/test")
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
		
		model.addAttribute(searchHelper.searchNaverApi(params));
		long endTime = System.currentTimeMillis();
		log.debug("test query = '{}', time ={}" , query,((endTime - startTime )/1000.0)); 
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView illegalArgument(Exception e) 
	{
		return new ModelAndView("IllegalArgumentException").addObject("result", ResultCode.ERROR);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView nullPoint(Exception e) 
	{
		return new ModelAndView("NullPointerException").addObject("result", ResultCode.ERROR);
	}

}
