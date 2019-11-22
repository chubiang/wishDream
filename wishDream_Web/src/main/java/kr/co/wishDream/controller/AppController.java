package kr.co.wishDream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/")
	public String index(final Model model) {

		return "index";
	}
	
	@RequestMapping("/login")
	public String login(final Model model) {
		return "login";
	}
}
