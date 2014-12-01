package org.l3cache.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.l3cache.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.utils.ResultCode;

@Controller
@RequestMapping("/app/users")
public class MobileAppUsersController {
	private static final Logger log = LoggerFactory
			.getLogger(MobileAppUsersController.class);
	
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping(value="/new", method = {RequestMethod.POST, RequestMethod.GET})
	public void newUser(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, Model model) {
		
		if(sqlSession.selectOne("UserMapper.findByEmail", email) != null){
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
		String matchPass = sqlSession.selectOne("UserMapper.findPassword", password);
		
		if(user.matchPassword(matchPass)){
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", String.valueOf(ResultCode.SUCCESS));
			map.put("id", String.valueOf(user.getUserId()));
			model.addAllAttributes(map);
		}else{
			model.addAttribute("status", ResultCode.PASSWORD_ERROR);
		}
	}
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public void deleteUser(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, Model model) {
		
		User user = sqlSession.selectOne("UserMapper.findByEmail", email);
		String matchPass = sqlSession.selectOne("UserMapper.findPassword", password);
		if(user == null)
			model.addAttribute("status", ResultCode.EMAIL_ERROR);
					
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
	
}
