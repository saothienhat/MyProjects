package com.javahonk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javahonk.model.StudentTest;

@Controller
public class SpringMVCController {
	
	@RequestMapping(value="/angularJS",method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "angularJS";
	}
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String returnHomePage(ModelMap model) {
		return "home";
	}
	
	
	/**
	 * Get Student from Database
	 * @return
	 */
	@RequestMapping(value="/springAngularJS", 
			method=RequestMethod.GET,
			produces={"application/xml", "application/json"})
    public @ResponseBody StudentTest  getStudentFromServer() {	
		StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
		StudentTest student = studentServiceImpl.getStudent(5);
		return student;
	}
	
	
}