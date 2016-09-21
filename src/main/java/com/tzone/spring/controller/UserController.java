package com.tzone.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tzone.spring.entity.Student;
import com.tzone.spring.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("UserSpringService")
	private UserService userService;
	
	
	@RequestMapping(value="/addUser.page")
	public String goToAddUser(HttpServletRequest request, HttpServletResponse response){
		return "addUser";
		
	}
	
	@RequestMapping(value="/addUser.do", method=RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Student student ){             
		
		request.setAttribute("mainPageUrl", "/WEB-INF/views/addUser.jsp");
		
		return "mainUserPage";
		
	}
	
	@RequestMapping(value="/searchUser.page")
	public String goToSearchUser(HttpServletRequest request, HttpServletResponse response){
		List <Student> studentList;
		
		
		try {
			studentList = userService.searchStudent();
			request.setAttribute("students", studentList);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "searchUser";
		
	}
	
}
