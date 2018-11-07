package com.jnshu.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnshu.pojo.Student;
import com.jnshu.service.RmiService;
import com.jnshu.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="")
public class ListController {
	@Autowired
	RmiService rmiService;
	
	@RequestMapping(value="/u/list")
	public String list(Model model,Page page){
	//	int i=1/0;
//		if(true){
//			throw new MessageException("到达list页面的错误异常抛出");			
//		}
		
		PageHelper.offsetPage(page.getStart(),3);
		List<Student> cs=rmiService.getStudentInterface().select();
		int total = (int) new PageInfo<>(cs).getTotal();
		page.calculateLast(total);
		model.addAttribute("cs", cs);
		return "list";
	}
	
	
	@RequestMapping(value="/edit/{id}" , method=RequestMethod.GET)
	public ModelAndView edit(Model model,@PathVariable("id") long id){
		ModelAndView mac=new ModelAndView("edit");
		Student stu=rmiService.getStudentInterface().selectOne(id);
		mac.addObject("stu", stu);
		System.out.println("到达controller层了");
		return mac;
	}
	
	@RequestMapping(value="/list/{id}" , method=RequestMethod.DELETE)
	public String delete(Model model,@PathVariable("id") long id){
		rmiService.getStudentInterface().delete(id);
		return "redirect:/homepage";
	}
	
	
	@RequestMapping(value="/list/{id}" , method=RequestMethod.PUT,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String update(Model model,@PathVariable("id") long id,Student stu){
		Student s=rmiService.getStudentInterface().selectOne(stu.id);
		s.setQq(stu.qq);
		s.setName(stu.name);
		s.setJob(stu.job);
		rmiService.getStudentInterface().update(s);
		
		return "修改成功";
	}
//	@RequestMapping(value="/list/{id}" , method=RequestMethod.POST)
//	public String update(Model model,@PathVariable("id") long id,Student stu){
//		stu.setId(id);
//		si.update(stu);
//		
//		return "redirect:/homepage";
//	}
}
