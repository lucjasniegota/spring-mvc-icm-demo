package com.github.kolorobot.icm.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class SigninController {

	@RequestMapping(value = "signin")
	public void signin() {}
}