package com.example.securitydemo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.securitydemo.model.Student;

@RequestMapping("controller/v1/students")
@RestController
public class StudentSecurityController {
	
	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(001,"Vinoth"),
			new Student(002, "Vishwa"),
			new Student(003, "Vignesh"));
	
	@GetMapping(path= "{studentId}")
	public Student getStudents(@PathVariable("studentId") Integer studentId) {
		return STUDENTS.stream()
				.filter(student -> studentId.equals(student.getStudentId()))
				.findFirst()
				.orElseThrow (()-> new IllegalStateException("Student" +studentId +"deos not exist"));

	}
	
}
