package com.tzone.spring.dao;

import java.util.List;

import com.tzone.spring.entity.Student;

public interface StudentDao {
	
	public List<Student> findAll();
	public void saveStudent(Student student);
	

}
