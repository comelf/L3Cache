package org.l3cache.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/test")
	public String requestTest() {
		
		return "requestTest";
	}
	
}
