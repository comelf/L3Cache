package org.l3cache.mobileController;


import java.util.HashMap;
import java.util.Map;

import org.l3cache.dto.Response;
import org.l3cache.support.EHCacheService;
import org.l3cache.support.QueryValidator;
import org.l3cache.support.SearchHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import core.utils.ResultCode;

@RestController
@RequestMapping("/search")
public class SearchController {
	private static final Logger log = LoggerFactory
			.getLogger(SearchController.class);
	
	@Autowired
	SearchHelper searchHelper;
	
	@Autowired
	EHCacheService ehCacheService;
	
	@RequestMapping(value="/shop", method={RequestMethod.POST, RequestMethod.GET})
	public Response searchWithQuery(@RequestParam(value="query") String query,
								@RequestParam(value="display") int display,
								@RequestParam(value="start") int start,
								@RequestParam(value="sort", defaultValue="sim") String sort,
								Model model){
		QueryValidator qv = new QueryValidator();
		if(!qv.validate(query)){
			return Response.error();
		}
		
		
		Map<String, Object> adultParams = new HashMap<String, Object>();
		adultParams.put("query", query);
		if(searchHelper.isAdultQuery(adultParams)){
			return Response.adult_Query();
		}
		
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("display", display);
		searchParams.put("start", start);
		searchParams.put("query", query);
		searchParams.put("sort", sort);
		
		return ehCacheService.getResponse(searchParams);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView illegalArgument(Exception e){
		log.debug("[DEBUG] {}",e.getMessage());
		return new ModelAndView("MissingServletRequestParameterException").addObject("result", ResultCode.ARGUMENT_ERROR);
	}
	
	@ExceptionHandler(value={ NullPointerException.class, IllegalArgumentException.class})
	public ModelAndView exception(Exception e) {
		log.debug("[DEBUG] {}",e.getMessage());
		return new ModelAndView("IllegalArgumentException").addObject("result", ResultCode.ERROR);
	}
	
	@ExceptionHandler(UnmarshallingFailureException.class)
	public ModelAndView unmarshallingFailureException(Exception e){
		log.debug("[DEBUG] {}",e.getMessage());
		return new ModelAndView("UnmarshallingFailureException").addObject("result", ResultCode.API_ERROR);
	}
	
	

}
