package com.jnshu.web.controller;

import com.jnshu.pojo.Student;
import com.jnshu.service.RmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StudentController {
	@Autowired
	RmiService rmiService;

	@RequestMapping(value="/homepage")
	public String student(Model model){
	//	int i=1/0;
		System.out.println("到达页面层");
		List<Student> list=rmiService.getStudentInterface().select();
		System.out.println("到达页面层，获得远程对象");
		model.addAttribute("list", list);
		int num=rmiService.getStudentInterface().count();
		model.addAttribute("num", num);
		System.out.println(num);
		return "homepage";
	}
	@RequestMapping(value="/recommend")
	public String recommend(Model model){
		return "recommend";
	}
	
	
	
	
	
	
}
