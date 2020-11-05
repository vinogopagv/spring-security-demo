package com.example.securitydemo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.securitydemo.model.Student;

@RestController
@RequestMapping("management/controller/v1/students")
public class StudentManagementController {

	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(1, "Vignesh"),
			new Student(2,"Vinoth"),
			new Student(3, "Vishwa"));

	@GetMapping	
	public List<Student> getAllStudents() {
		System.out.println("getAllStudents");
		return STUDENTS;
	}

	@DeleteMapping(path = {"studentId"})
	public void deleteStudents(@PathVariable Integer studentId) {
		System.out.println("delete");
	}

	@PutMapping(path= {"studentId"})
	public void updateStudents(@PathVariable Integer studentId,@RequestBody Student student) {
		System.out.println("update");
	}

	@PostMapping()
	public void registerStudents(@RequestBody Student student) {
		System.out.println("register");
	}

}
