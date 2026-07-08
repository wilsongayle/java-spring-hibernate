package com.udemy.springboot.thymeleaftdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    @RequestMapping("/processForm2")
    public String shoutServletRequest(HttpServletRequest request, Model model) {

        // read request param from html form
        String name = request.getParameter("studentName");

        // set to upper case
        name = name.toUpperCase();

        // create message
        String result = "HEY " + name + "!";

        // add message to model
        model.addAttribute("message", result);
        return "helloworld";
    }

    @GetMapping("/processForm3")
    public String shoutRequestParam(@RequestParam("studentName") String name, Model model) {

        // set to upper case
        name = name.toUpperCase();

        // create message
        String result = "HEY THERE " + name + "!";

        // add message to model
        model.addAttribute("message", result);
        return "helloworld";
    }

    @PostMapping("/processForm4")
    public String postShout(@RequestParam("studentName") String name, Model model) {

        // set to upper case
        name = name.toUpperCase();

        // create message
        String result = "HEY THERE " + name + "!";

        // add message to model
        model.addAttribute("message", result);
        return "helloworld";
    }
}
