package org.l3cache.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.l3cache.dao.PostsResult;
import org.l3cache.dao.Response;
import org.l3cache.model.Post;
import org.l3cache.model.PostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/app/posts/")
public class MobileAppPostsController {
	private static final Logger log = LoggerFactory.getLogger(MobileAppPostsController.class);
	
	
	@RequestMapping("/")
	public void requestTest(Model model) {
		log.debug("/app/posts/");
		Response response = new Response(10);
		response.setData(makeTestData());
		
		model.addAttribute(response);
	}
	
	
	@RequestMapping("/{pid}")
	public void requestTest(@PathVariable("pid") long pid, Model model) {
		log.debug("/app/posts/[pid] = {}",pid);
		
		Response response = new Response(10);		
		response.setData(makePost(1));
		
		model.addAttribute(response);
	}
	
	@RequestMapping("/new")
	public void newPosts(PostDto postDto, Model model, HttpSession session) {
		
		MultipartFile uploadfile = postDto.getImage();
        if (uploadfile != null) {
            String fileName = uploadfile.getOriginalFilename();
            try {
            	String uploadPath = session.getServletContext().getRealPath("/WEB-INF/postsImages/");
                File file = new File(uploadPath + fileName);
                uploadfile.transferTo(file);
                
                model.addAttribute("status", "10");
            } catch (IOException e) {
            	model.addAttribute("status", "20");
                e.printStackTrace();
            } 
        } 
	}
	
	private PostsResult makeTestData() {
		int maxPosts = 10;
		List<Post> list = new ArrayList<Post>();
		
		for(int i=0; i< maxPosts; i++){
			list.add(makePost(i));
		}
		
		PostsResult postResult = new PostsResult(1, maxPosts, list);
		
		return postResult;
	}

	private Post makePost(int i) {
		Post post = new Post();
		post.setContents("test post "+i);
		post.setImgUrl("http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg");
		post.setLike(false);
		post.setNumLike(10);
		post.setPid(i);
		post.setPrice("9900");
		post.setShopUrl("http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA");
		post.setTitle("게스 스터드 장식 모어 스키니진");
		return post;
	}
}
