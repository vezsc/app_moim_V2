package org.edupoll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

	@GetMapping("/")
	public String indexHandle(HttpSession session) {
		System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));
		// return "index";
		return "search/form";
	}
	
	@GetMapping("/status")
	@ResponseBody
	public Object statusHandle(HttpSession session) {
		// System.out.println();
		// return "index";
		return session.getAttribute("SPRING_SECURITY_CONTEXT");
	}
}
