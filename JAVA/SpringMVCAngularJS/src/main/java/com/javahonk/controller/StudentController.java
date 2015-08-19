package com.javahonk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javahonk.model.StudentTest;

@Controller
public class StudentController {

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView student() {
		return new ModelAndView("student", "command", new StudentTest());
	}

	
	
	/**
	 * @param student
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("SpringWeb") StudentTest student, ModelMap model) {
		model.addAttribute("lastName", student.getLastName());
		model.addAttribute("firstName", student.getFirstName());
		model.addAttribute("age", student.getAge());
		model.addAttribute("email", student.getEmail());

		return "result";
	}
	

}
