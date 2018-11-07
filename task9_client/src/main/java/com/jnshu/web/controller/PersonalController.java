package com.jnshu.web.controller;

import com.jnshu.exception.MessageException;
import com.jnshu.pojo.Student;
import com.jnshu.service.RmiService;
import com.jnshu.util.CookieUtil;
import com.jnshu.util.TokenUtil;
import com.jnshu.util.alioos.AlioosUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/u")
public class PersonalController {
	@Autowired
	private AlioosUtil au;
	@Autowired
	RmiService rmiService;

	private Logger log = LoggerFactory.getLogger(PersonalController.class);

	@RequestMapping(value="/personalpage")
	public String personpage(Model model,HttpServletRequest request) throws Exception {
		long uid;
		try {
			uid = TokenUtil.getID(CookieUtil.getCookieValue(request, "token"));
		} catch (UnsupportedEncodingException e) {
			throw new MessageException("cookie工具类查找value异常");
		}
		Student stu=rmiService.getStudentInterface().selectOne(uid);
		model.addAttribute("stu", stu);		
		return "personalpage";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/imagehandle", method=RequestMethod.POST)
	public String uploadpicture(HttpServletRequest request,MultipartFile file) throws Exception{
		long uid;
		try {
			uid = TokenUtil.getID(CookieUtil.getCookieValue(request, "token"));
		} catch (UnsupportedEncodingException e) {
			throw new MessageException("cookie工具类查找value异常");
		}
		Student stu=rmiService.getStudentInterface().selectOne(uid);
		String url;
		try {
			if (file.getBytes().length<=(1024 * 1024 * 2)) {
				url = au.springuploadFile(stu.name, file);
				long time=System.currentTimeMillis();
				stu.setImage(url);
				stu.setUpdate_at(time);
				rmiService.getStudentInterface().update(stu);
				return "redirect:/homepage";

			} else {
				log.error("图片过大");
				throw new MessageException("阿里云对象存储图片上传报错");
			}
		} catch (Exception e) {
			throw new MessageException("阿里云对象存储图片上传报错");
		}

	}
	
	
	
	
}
