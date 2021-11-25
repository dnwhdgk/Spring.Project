package com.bbs.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbs.service.UsersService;

@Controller
public class Maincontroller {
	

	private static final Logger logger = LoggerFactory.getLogger(Maincontroller.class);
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
	
		
		return "main/main";
	}
	
}
