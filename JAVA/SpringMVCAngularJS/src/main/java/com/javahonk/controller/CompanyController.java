package com.javahonk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javahonk.model.StudentTest;

@Controller
public class CompanyController {

	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public String goCompanyPage(ModelMap model) {
		return "company";
	}

	/**
	 * Get list of Student from Database
	 * 
	 * @return
	 */
	@RequestMapping(value = "/studentsForCompanyURL", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	public @ResponseBody List<StudentTest> getStudentsForCompany() {
		System.out.println("CompanyController.getStudentsFromServer() .........");
		StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
		List<StudentTest> students = studentServiceImpl.getAllStudents();
		System.out.println("CompanyController.getStudentsFromServer(): students.size(): " + students.size() + "  .........");
		return students;
	}

}
