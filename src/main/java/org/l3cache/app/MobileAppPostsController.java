package org.l3cache.app;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.l3cache.dao.Response;
import org.l3cache.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import core.utils.FileManager;
import core.utils.ResultCode;

@Controller
@RequestMapping("/app/posts/")
public class MobileAppPostsController {
	private static final Logger log = LoggerFactory.getLogger(MobileAppPostsController.class);
	
	@Autowired
	PostManager postManager;
	
	@Autowired
	FileManager fileManager;
	
	@RequestMapping("/")
	public void postsList(@RequestParam("start") int start,
							@RequestParam("sort") int sort,
							Model model) {
		try{
			Response response = new Response(ResultCode.SUCCESS);
			response.setData(postManager.getRecentlyLists(start));
			model.addAttribute(response);
		}catch (Exception e){
			model.addAttribute(new Response(ResultCode.ERROR));
			log.debug("포스트 리스트 출력 오류 = {}",e.toString());
		}
	}
	
	@RequestMapping("/")
	public void userBasePostsList(@RequestParam("email") String email,
								@RequestParam("start") int start,
								@RequestParam("sort") int sort,
								Model model) {
		try{
			Response response = new Response(ResultCode.SUCCESS);
			response.setData(postManager.getRecentlyLists(start));
			model.addAttribute(response);
		}catch (Exception e){
			model.addAttribute(new Response(ResultCode.ERROR));
			log.debug("포스트 리스트 출력 오류 = {}",e.toString());
		}
	}
	
	
	@RequestMapping("/{pid}")
	public void getPostDetail(@PathVariable("pid") long pid, Model model) {
		log.debug("/app/posts/[pid] = {}",pid);
		try{
			Response response = new Response(ResultCode.SUCCESS);
			response.setData(postManager.getPostDetail(pid));
			model.addAttribute(response);
		}catch (Exception e){
			model.addAttribute(new Response(ResultCode.ERROR));
			log.debug("포스트 출력 오류 = {}",e.toString());
		}
	}
	
	@RequestMapping(value="/new", method = RequestMethod.POST)
	public String newPosts(@RequestParam("title") String title,
			@RequestParam("shopUrl") String shopUrl,
			@RequestParam("contents") String contents,
			@RequestParam("image") MultipartFile image,
			@RequestParam("price") String price,
			@RequestParam("id") int id,
			Model model, HttpSession session) {
		if (fileManager.isValidatedFile(image)) {
			String uploadPath = session.getServletContext().getRealPath("/WEB-INF/postsImages/");
			String fileName = "";
			try {
				fileName = fileManager.saveFile(image, uploadPath);
			} catch (IllegalStateException | IOException e) {
				model.addAttribute(new Response(ResultCode.ERROR));
				log.debug("파일 저장 오류 = {}",e.toString());
			}
			Post post = new Post(title,shopUrl,contents,uploadPath+fileName,price,id);
			
			if(postManager.savePost(post)==1){
				model.addAttribute(new Response(ResultCode.SUCCESS));
				return "redirect:/app/posts/success";
			}else{
				model.addAttribute(new Response(ResultCode.ERROR));
			}
			
		}else{
			model.addAttribute("status", ResultCode.ERROR);
		}

        return "redirect:/app/posts/up";
	}
	
	@RequestMapping("/success")
	public void up(Model model) {
		 model.addAttribute("status", "10");
	}
	
}
