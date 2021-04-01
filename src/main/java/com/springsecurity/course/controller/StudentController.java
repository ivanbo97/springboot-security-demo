package com.springsecurity.course.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.course.entity.Student;
import com.springsecurity.course.exception.StudentNotFoundException;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

	private static final List<Student> students = Arrays.asList(
			new Student (1, "James Bond"),
			new Student (2, "Maria Jones"),
			new Student (3, "Anna Smith"));	
	
	
	@GetMapping(path = "{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		
		return students.stream()
				.filter(student -> studentId.equals(student.getStudentId()))
				.findFirst()
				.orElseThrow(() -> new StudentNotFoundException("Student with id - " + studentId + " does not exist. "));
	}
}
