package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloClass 
{
	@GetMapping("/hello")
	public String sayHello(Model model)
	{
		String s = "Hello all";
		model.addAttribute("hello", s);
		return "welcome";
	}
}
