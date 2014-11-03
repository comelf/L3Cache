package core.search;

import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.l3cache.dto.Response;
import org.l3cache.dto.ShopItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import core.utils.ResultCode;

@Service
public class EHCacheService {
	private static final Logger log = LoggerFactory.getLogger(EHCacheService.class);
	
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
	
	// "start" 변화에 따른 return 값 변경
	private Response naverApiToResponse(Map<String, Object> params) {
		int start = (int)params.get("start");
		ShopItems si = searchHelper.searchNaverApi(params);
		int total = si.getChannel().getTotal();
		
		Response response = new Response(ResultCode.OK);
		response.setTotal(total);
		response.setItemList(si.getChannel().getItem().subList(0, 19));
		response.setStart(start);
		
		apiCache(si, params, start);
		return response;
	}

	@Async
	private void apiCache(ShopItems si, Map<String, Object> params, int start) {
		for(int i=0; i<5; i++){
			int pageIdx = i*20;
			int realStart = start + i;
			int total = si.getChannel().getTotal();
			
			Response response = new Response(ResultCode.OK);
			response.setTotal(total);
			response.setItemList(si.getChannel().getItem().subList(pageIdx, pageIdx + 19));
			response.setStart(realStart);
			
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
