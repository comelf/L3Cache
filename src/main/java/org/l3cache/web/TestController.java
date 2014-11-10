package org.l3cache.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/test")
	public String requestTest() {
		
		return "requestTest";
	}
	
}
