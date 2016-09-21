package com.tzone.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tzone.spring.entity.Student;
import com.tzone.spring.exception.ApplicationException;
import com.tzone.spring.service.StudentService;
import com.tzone.spring.service.UserService;
import com.tzone.spring.service.impl.StudentServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("UserSpringService")
	private UserService userService;
	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping(value="/addUser.page")
	public String goToAddUser(HttpServletRequest request, HttpServletResponse response){
		
		return "addUser";
		
	}
	
	@RequestMapping(value="/addUser.do", method=RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Student student ) throws ApplicationException{             
		
		
		//userService.addUser(student);
		studentService.saveStudent(student);
		request.setAttribute("mainPageUrl", "/WEB-INF/views/addUser.jsp");
		
		findAllUser(request);
		return "mainUserPage";
		
	}
	
	@RequestMapping(value="/searchUser.page")
	public String goToSearchUser(HttpServletRequest request, HttpServletResponse response){
		findAllUser(request);
		
		return "searchUser";
		
	}
	
	@RequestMapping(value="/deleteUser.page", method=RequestMethod.GET)
	public String deleteUser(HttpServletRequest request, HttpServletResponse response ) {             
		
		
		//userService.addUser(student);
//		studentService.deleteStudent(0);
		int studentId = (Integer) request.getAttribute("studentId");
		studentService.deleteStudent(studentId);
		
		findAllUser(request);
		return "searchUser";
		
	}
	
	private void findAllUser (HttpServletRequest request){
		List <Student> studentList;
		
		try {
			studentList = userService.searchStudent();
			request.setAttribute("students", studentList);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
