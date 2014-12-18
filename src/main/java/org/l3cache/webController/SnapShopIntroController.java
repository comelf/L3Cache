package org.l3cache.webController;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SnapShopIntroController {
	private static final Logger LOG = LoggerFactory
			.getLogger(SnapShopIntroController.class);

	@RequestMapping("/download")
	public String home() {
		LOG.debug("HELLO!");
		return "download";
	}

	@ExceptionHandler(FileNotFoundException.class)
	public String fileNotFound(Exception e) 
	{
		return "home";
	}
}
