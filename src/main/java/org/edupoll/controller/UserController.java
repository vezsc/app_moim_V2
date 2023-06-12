package org.edupoll.controller;

import org.edupoll.model.entity.User;
import org.edupoll.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	@GetMapping("/user/join")
	public String showUserJoinForm() {
		return "user/join";
	}

	@PostMapping("/user/join")
	public String userJoinHandle(User user, Model model) {
		boolean rst = userService.createNewUser(user);
		logger.debug("userJoinHandle's result : {} ", rst);
		if (rst) {
			return "redirect:/user/login?loginId="+user.getId();
		} else {
			model.addAttribute("error", true);
			return "user/join";
		}
	}

	@GetMapping("/user/login")
	public String showUserLoginForm(Model model) {
		return "user/login";
	}
/*
	@PostMapping("/user/login")
	public String userLoginHandle(LoginRequestData data, HttpSession session, Model model) {
		boolean result = userService.isValidUser(data);
		logger.debug("userLoginHandle result : {} ", result);
		if (result) {
			session.setAttribute("logonId", data.getLoginId());
			return "redirect:/";
		}
		model.addAttribute("error", true);
		return "user/login";
	}

	@GetMapping("/user/logout")
	public String userLogoutHandle(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
*/
}
