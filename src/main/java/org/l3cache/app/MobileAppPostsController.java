package org.l3cache.app;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.l3cache.dao.Response;
import org.l3cache.model.WritePost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import core.utils.FileManager;
import core.utils.ResultCode;

@Controller
@RequestMapping("/app/posts")
public class MobileAppPostsController {
	private static final Logger log = LoggerFactory.getLogger(MobileAppPostsController.class);
	private static final String localhost = "http://125.209.199.221:8080";
	@Autowired
	private PostManager postManager;
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping("/")
	public void userBasePostsList(@RequestParam("id") int id,
								@RequestParam("start") int start,
								@RequestParam("sort") int sort,
								Model model) {
		try{
			Response response = new Response(ResultCode.SUCCESS);
			response.setTotal(postManager.getTotalRows());
			response.setData(postManager.getRecentlyLists(start, id));
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
	
	@RequestMapping(value="/new")
	public String newPost(@RequestParam("title") String title,
			@RequestParam("shopUrl") String shopUrl,
			@RequestParam("contents") String contents,
			@RequestParam("image") MultipartFile image,
			@RequestParam("price") int price,
			@RequestParam("id") int id,
			HttpSession session) {
		log.debug("포스트 업로드 요청");
		
		if (fileManager.isValidatedFile(image)) {
			String uploadPath = session.getServletContext().getRealPath("/WEB-INF/postsImages");
			String fileName = "";
			try {
				fileName = fileManager.saveFile(image, uploadPath);
			} catch (IllegalStateException | IOException e) {
				log.debug("파일 저장 오류 = {}",e.toString());
				return "redirect:/app/posts/error";
			}
			WritePost post = new WritePost(title, shopUrl, contents,localhost+"/postsImages/"+fileName, price, id);
			if(postManager.savePost(post)){
				return "redirect:/app/posts/success";
			}
		}
		
		return "redirect:/app/posts/error";
	}
	
	@RequestMapping(value="/newurl")
	public void newPostWithUrl(@RequestParam("title") String title,
			@RequestParam("shopUrl") String shopUrl,
			@RequestParam("contents") String contents,
			@RequestParam("image") String imageUrl,
			@RequestParam("price") int price,
			@RequestParam("id") int id,
			Model model) {
		log.debug("포스트 업로드 요청 with ImgURL");
		
		WritePost post = new WritePost(title, shopUrl, contents, imageUrl, price, id);
		if(postManager.savePost(post)){
			model.addAttribute("status", ResultCode.SUCCESS);
		}else{
			model.addAttribute("status", ResultCode.ERROR);
		}
	}
	
	@RequestMapping("/success")
	public void postSuccess(Model model) {
		 model.addAttribute("status", ResultCode.SUCCESS);
	}
	
	@RequestMapping("/error")
	public void postError(Model model) {
		 model.addAttribute("status", ResultCode.ERROR);
	}
	
	@RequestMapping("/edit/{pid}")
	public String editPost(@PathVariable("pid") long pid,
						@RequestParam("title") String title,
						@RequestParam("shopUrl") String shopUrl,
						@RequestParam("contents") String contents,
						@RequestParam("image") MultipartFile image,
						@RequestParam("price") int price,
						@RequestParam("id") int id,
						HttpSession session) {
		
		if (fileManager.isValidatedFile(image)) {
			String uploadPath = session.getServletContext().getRealPath("/WEB-INF/postsImages/");
			String fileName = "";
			try {
				String beforeFile = postManager.getPostImageFilePath(pid);
				fileName = fileManager.saveAndRemoveFile(image, uploadPath, beforeFile);
			} catch (IllegalStateException | IOException e) {
				log.debug("파일 저장 오류 = {}",e.toString());
				return "redirect:/app/posts/error";
			}
			WritePost post = new WritePost(title, shopUrl, contents, localhost+uploadPath+fileName, price, id);
			if(postManager.updateWithImage(post)){
				return "redirect:/app/posts/success";
			}
		}else {
			if(postManager.isExistentPost(pid)){
				WritePost post = new WritePost(title, shopUrl, contents, "", price, id);
				if(postManager.updateWithImage(post)){
					return "redirect:/app/posts/success";
				}
			}
		}
		
		return "redirect:/app/posts/error";
		
	}
	
	@RequestMapping(value="/delete/{pid}", method = {RequestMethod.DELETE,RequestMethod.GET})
	public void deletePost(@PathVariable("pid") long pid,
							@RequestParam("uid") int uid,
							Model model) {
		if(postManager.deletePost(pid, uid)){
			model.addAttribute("status", ResultCode.SUCCESS);
		}else{
			model.addAttribute("status", ResultCode.ERROR);
		}
	}
	
	@RequestMapping(value="/like", method = {RequestMethod.POST,RequestMethod.GET})
	public void likePost(@RequestParam("pid") long pid,
						@RequestParam("uid") int uid,
							Model model) {
		if(postManager.likePost(pid, uid)){
			model.addAttribute("status", ResultCode.SUCCESS);
		}else{
			model.addAttribute("status", ResultCode.ERROR);
		}
	}
	
	@RequestMapping(value="/like", method = {RequestMethod.DELETE,RequestMethod.GET})
	public void unlikePost(@RequestParam("pid") long pid,
							@RequestParam("uid") int uid,
							Model model) {
		if(postManager.unlikePost(pid, uid)){
			model.addAttribute("status", ResultCode.SUCCESS);
		}else{
			model.addAttribute("status", ResultCode.ERROR);
		}
	}
	
	@RequestMapping(value="/{pid}/read", method = {RequestMethod.POST,RequestMethod.GET})
	public void readPost(@PathVariable("pid") long pid,
							Model model) {
		if(postManager.readPost(pid)){
			model.addAttribute("status", ResultCode.SUCCESS);
		}else{
			model.addAttribute("status", ResultCode.ERROR);
		}
	}
	
	@ExceptionHandler(NestedServletException.class)
	public ModelAndView nullPoint(Exception e) 
	{
		log.error("NestedServletException : ", e.toString());
		return new ModelAndView("NestedServletException").addObject("status", ResultCode.ERROR);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView illegalArgument(Exception e) 
	{
		log.error("ArgumentException : ", e.toString());
		return new ModelAndView("IllegalArgumentException").addObject("result", ResultCode.ERROR);
	}
}
