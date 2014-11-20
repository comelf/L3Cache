package core.search;

import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.l3cache.dao.Response;
import org.l3cache.dao.SearchResult;
import org.l3cache.dao.ShopItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import core.utils.ResultCode;

@Service
public class EHCacheService {
	private static final Logger log = LoggerFactory.getLogger(EHCacheService.class);

	private static final int DISPLAY_COUNT = 20;
	
	SearchHelper searchHelper;
	
	Cache cache;
	
	public EHCacheService(SearchHelper searchHelper, Cache cache) {
		this.searchHelper = searchHelper;
		this.cache = cache;
	}

	public Response getResponse(Map<String, Object> params) {
		String key = makeKey((String) params.get("query"), (int)params.get("start"), (String)params.get("sort"));
		Element ele = cache.get(key);
		
		if(ele != null){
			log.debug("return Cashed Api ( key ={})",key);
			return (Response) ele.getObjectValue();
		}else{
			log.debug("return non Cashed Api ( key ={})",key);
			return naverApiToResponse(params);
		}		
	}
	
	private Response naverApiToResponse(Map<String, Object> params) {
		int start = (int)params.get("start");
		ShopItems si = searchHelper.searchNaverApi(params);
		
		if(si==null){
			return new Response(1);
		}
		
		int total = si.getChannel().getTotal();
		int arrCount = si.getChannel().getItem().size();
		log.debug("api response count={}, arrSize={})",total,arrCount);
		
		if(total==0){
			return new Response(1);
		}else if(total<=20){
			Response response = new Response(ResultCode.SUCCESS);
			SearchResult result = new SearchResult(start, total, si.getChannel().getItem().subList(0, total));
			response.setData(result);
			
			apiCache(si, params, start, total);
			return response;
		}else{
			Response response = new Response(ResultCode.SUCCESS);
			SearchResult result = new SearchResult(start, total, si.getChannel().getItem().subList(0, DISPLAY_COUNT));
			response.setData(result);
			apiCache(si, params, start, total);
			return response;
		}
		
	}

	@Async
	private void apiCache(ShopItems si, Map<String, Object> params, int start, int end) {
		int totalPage = 0;
		
		if(end>100){
			totalPage = 5;
		}else{
			totalPage = end / DISPLAY_COUNT;
		}
		
		for(int i=0; i<totalPage; i++){
			int pageIdx = i*DISPLAY_COUNT;
			int realStart = start + i;
			int total = si.getChannel().getTotal();
			
			Response response = new Response(ResultCode.SUCCESS);
			SearchResult result = new SearchResult(realStart, total, si.getChannel().getItem().subList(pageIdx, pageIdx + DISPLAY_COUNT));
			response.setData(result);
			
			String key = makeKey((String) params.get("query"), realStart, (String)params.get("sort"));
			
			Element element = new Element(key, response);
			cache.put(element);
			log.debug("Api Cashe ( key ={})",key);
		}
	}

	private String makeKey(String param, int start, String sort) {
		StringBuffer sb = new StringBuffer();
		sb.append(param);
		sb.append("_");
		sb.append(start);
		sb.append("_");
		sb.append(sort);
		return sb.toString();
	}

}
