package com.jnshu.web.controller;

import com.jnshu.pojo.Course;
import com.jnshu.service.RmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
//@RequestMapping(value="/u")
public class CourseController {
		@Autowired
	     RmiService rmiService;

		
		@RequestMapping(value="/coursepage")
		public String course(Model model){
			List<Course> course=rmiService.getCourseInterface().select();
			model.addAttribute("courses", course);
			//System.out.println("cousrsssssss");
			return "coursepage";
		}
		
		


	
	
}
