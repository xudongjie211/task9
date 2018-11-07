package com.jnshu.web.interceptor;

import com.jnshu.pojo.User;
import com.jnshu.service.RmiService;
import com.jnshu.util.CookieUtil;
import com.jnshu.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	RmiService rmiService;

	Logger log= LoggerFactory.getLogger(LoginInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
	String tk= CookieUtil.getCookieValue(request, "token");
	if(tk!=null){
	 	long id =TokenUtil.getID(tk);
	 	long time=TokenUtil.getLogntime(tk);
	 	User u=new User();
	 	u.setUid(id);
		 User user= rmiService.getUserInterface().findUser(u);
		if(user.longtime==time){
			log.info("拦截器通过了========================================");
			return true;
			
			
		}else{
			log.error("拦截器出错了-------------------------------------");
			
			return false;
		}
	}else{
		String path=request.getContextPath();
		request.getRequestDispatcher("/error.jsp").forward(request, response);
		//response.sendRedirect("../index.jsp");
		log.error("拦截器没有cookie值了-------------------------------------");
		return false;
	}

		
	}
	
	
	
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String path=request.getContextPath();
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
