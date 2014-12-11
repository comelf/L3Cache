package org.l3cache.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.l3cache.dao.Response;
import org.l3cache.model.User;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import core.utils.ResultCode;

@Controller
@RequestMapping("/app/users")
public class MobileAppUsersController {
	private static final Logger log = LoggerFactory
			.getLogger(MobileAppUsersController.class);
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	private PostManager postManager;
	
	@RequestMapping(value="/new", method = {RequestMethod.POST, RequestMethod.GET})
	public void newUser(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, Model model) {
		
		if(sqlSession.selectOne("UserMapper.findByEmail", email) == null){
			User user = new User(email, password);
			int returnCode = sqlSession.insert("UserMapper.create", user);
			
			if(returnCode==1){
				model.addAttribute("status", ResultCode.SUCCESS);
			}else{
				model.addAttribute("status", ResultCode.ERROR);
				log.debug("유저 가입 오류 : email = {}, returnCode = {}",email, returnCode);
			}
		}else{
			model.addAttribute("status", ResultCode.EMAIL_DUPLICTION);
		}
		
	}
	
	@RequestMapping(value="/login", method = {RequestMethod.POST, RequestMethod.GET})
	public void userLogin(@RequestParam(value = "email") String email,
						@RequestParam(value = "password") String password, Model model) {
		User user = sqlSession.selectOne("UserMapper.findByEmail", email);
		if(user!=null){
			String matchPass = sqlSession.selectOne("UserMapper.findPassword", password);
			
			if(user.matchPassword(matchPass)){
				Map<String, String> map = new HashMap<String, String>();
				map.put("status", String.valueOf(ResultCode.SUCCESS));
				map.put("id", String.valueOf(user.getUserId()));
				model.addAllAttributes(map);
			}else{
				model.addAttribute("status", ResultCode.PASSWORD_ERROR);
			}
		}else{
			model.addAttribute("status", ResultCode.EMAIL_ERROR);
		}
		
	}
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public void deleteUser(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, Model model) {
		
		User user = sqlSession.selectOne("UserMapper.findByEmail", email);
		String matchPass = sqlSession.selectOne("UserMapper.findPassword", password);
		if(user == null){
			model.addAttribute("status", ResultCode.EMAIL_ERROR);
		}
		if(user.matchPassword(matchPass)){
			int returnCode = sqlSession.delete("UserMapper.deleteUserByEmail", email);
			if(returnCode==1){
				model.addAttribute("status", ResultCode.SUCCESS);
			}else{
				model.addAttribute("status", ResultCode.ERROR);
				log.debug("유저 탈퇴 오류 : email = {}, returnCode = {}",email, returnCode);
			}
		}else{
			model.addAttribute("status", ResultCode.PASSWORD_ERROR);
		}
	}
	
	@RequestMapping(value="/{uid}/likes")
	public void getMyLikes(@PathVariable("uid") int uid,@RequestParam(value = "start") int start, Model model) {
		if(uid>0 && start>=0){
			Response response = new Response(ResultCode.SUCCESS);
			response.setTotal(postManager.getUserLikesCount(uid));
			response.setData(postManager.getUserLikesList(uid, start));
			model.addAttribute(response);
		}else{
			model.addAttribute("status", ResultCode.ERROR);
		}
	}
	
	@RequestMapping(value="/{uid}/posts")
	public void getMySnap(@PathVariable("uid") int uid,@RequestParam(value = "start") int start, Model model) {
		if(uid>0 && start>=0){
			Response response = new Response(ResultCode.SUCCESS);
			response.setTotal(postManager.getUserPostsCount(uid));
			response.setData(postManager.getUserPostsList(uid, start));
			model.addAttribute(response);
		}else{
			model.addAttribute("status", ResultCode.ERROR);
		}
	}
	
	@RequestMapping(value="/{uid}")
	public void getMyInfo(@PathVariable("uid") int uid, Model model) {
		if(uid>0){
			User user = sqlSession.selectOne("UserMapper.findByUid", uid);
			if(user == null){
				model.addAttribute(new Response(ResultCode.EMAIL_ERROR));
			}else{
				Response response = new Response(ResultCode.SUCCESS);
				response.setData(user);
				model.addAttribute(response);
			}
		}else{
			model.addAttribute(new Response(ResultCode.ERROR));
		}
	}
	
	@RequestMapping(value="/edit/{uid}")
	public void editMyInfo(@PathVariable("uid") int uid, 
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam String newPassword, Model model) {
		if(uid>0){
			User user = sqlSession.selectOne("UserMapper.findByUidwithPassword", uid);
			String matchPass = sqlSession.selectOne("UserMapper.findPassword", password);
			if(user == null){
				model.addAttribute("status", ResultCode.EMAIL_ERROR);
			}else if(user.matchPassword(matchPass)){
				user.setEmail(email);
				user.setPassword(newPassword);
				sqlSession.update("UserMapper.updateUser", user);
				model.addAttribute("status", ResultCode.SUCCESS);
			}else{
				model.addAttribute("status", ResultCode.PASSWORD_ERROR);
			}
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
