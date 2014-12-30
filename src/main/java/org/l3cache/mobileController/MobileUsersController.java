package org.l3cache.mobileController;

import org.apache.ibatis.session.SqlSession;
import org.l3cache.dto.Response;
import org.l3cache.dto.Status;
import org.l3cache.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import core.utils.ResultCode;

@RestController
@RequestMapping("/app/users")
public class MobileUsersController {
	private static final Logger LOG = LoggerFactory
			.getLogger(MobileUsersController.class);

	private static final int STARTING_COUNT = 1;
	
	@Autowired
	SqlSession sqlSession;

	@RequestMapping(value="/new", method = RequestMethod.POST)
	public Status newUser(@RequestParam(value = "email") String email,
						  @RequestParam(value = "password") String password) {
		
		if(sqlSession.selectOne("UserMapper.findByEmail", email) != null){
			return Status.emailDuplication();
		}
		
		User user = new User(email, password);
		sqlSession.insert("UserMapper.create", user);
		return Status.success();
	}
	
	@RequestMapping(value="/login", method = {RequestMethod.POST, RequestMethod.GET})
	public Status userLogin(@RequestParam(value = "email") String email,
						  @RequestParam(value = "password") String password) {
		
		User user = sqlSession.selectOne("UserMapper.findByEmail", email);
		if(user==null){
			return Status.emailError();
		}
		
		String matchPass = sqlSession.selectOne("UserMapper.findPassword", password);
		if(!user.matchPassword(matchPass)){
			return Status.passwordError();
		}
		
		return Status.successAndLogin(user.getUserId());
	}
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public Status deleteUser(@RequestParam(value = "email") String email,
						   @RequestParam(value = "password") String password) {
		
		User user = sqlSession.selectOne("UserMapper.findByEmail", email);
		if(user==null){
			return Status.emailError();
		}
		
		String matchPass = sqlSession.selectOne("UserMapper.findPassword", password);
		if(!user.matchPassword(matchPass)){
			return Status.passwordError();
		}
		
		sqlSession.delete("UserMapper.deleteUserByEmail", email);
		return Status.success();
	}
	
	@RequestMapping(value="/{uid}")
	public Response getMyInfo(@PathVariable("uid") int uid) {
		if(uid < STARTING_COUNT){
			return Response.error();
		}
		
		User user = sqlSession.selectOne("UserMapper.findByUid", uid);
		if(user == null){
			return Response.emailError();
		}
		
		Response response = Response.success();
		response.setData(user);
		return response;
	}
	
	@RequestMapping(value="/edit/{uid}")
	public Response editMyInfo(@PathVariable("uid") int uid, 
						   @RequestParam String email,
						   @RequestParam String password,
						   @RequestParam String newPassword) {
		if(uid < STARTING_COUNT){
			return Response.error();
		}
		
		User user = sqlSession.selectOne("UserMapper.findByUidwithPassword", uid);
		if(user == null){
			return Response.emailError();
		}
		
		String matchPass = sqlSession.selectOne("UserMapper.findPassword", password);
		if(user.matchPassword(matchPass)){
			return Response.passwordError();
		}
		
		user.setEmail(email);
		user.setPassword(newPassword);
		sqlSession.update("UserMapper.updateUser", user);
		return Response.success();
	}
	
	@RequestMapping(value="/tpLike/{uid}", method=RequestMethod.GET)
	public Response sumPriceWhenLike(@PathVariable("uid") int uid) {
		if(uid < STARTING_COUNT){
			return Response.error();
		}
		
		long total;
		try {
			total = sqlSession.selectOne("UserMapper.totalPriceOfUserLike", uid);
		}catch(Exception e){
			LOG.debug("total price of Like : {}", e.getMessage());
			total = 0;
		}
		
		Response response = Response.success();
		response.setTotal(total);;
		return response;
	}

	
	@RequestMapping(value="/tpWrite/{uid}", method=RequestMethod.GET)
	public Response sumPriceWhenWrite(@PathVariable("uid") int uid) {
		if(uid < STARTING_COUNT){
			return Response.error();
		}
		
		long total;
		try {
			total = sqlSession.selectOne("UserMapper.totalPriceOfUserWrite", uid);
		}catch(Exception e){
			LOG.debug("total price of Writer : {}", e.getMessage());
			total = 0;
		}
		LOG.debug("[return]total price of Writer = {}", total );
		Response response = Response.success();
		response.setTotal(total);;
		return response;
	}
	
	
	@ExceptionHandler(value={ NestedServletException.class, IllegalArgumentException.class})
	public ModelAndView exceptionHandler(Exception e) {
		LOG.error("NestedServletException : ", e.toString());
		return new ModelAndView("NestedServletException").addObject("status",ResultCode.ERROR);
	}
}
