package com.udemy.springboot.thymeleaftdemo.controller;

import com.udemy.springboot.thymeleaftdemo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student student) {
        String fullName = student.getFirstName() + " " + student.getLastName();
        System.out.println(fullName);
        return "student-confirmation";
    }
}
