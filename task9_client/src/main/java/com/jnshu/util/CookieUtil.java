package com.jnshu.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CookieUtil {

	public static void addCookie(HttpServletResponse response, String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie);
	}

	/**
	 * 删除cookie
	 * 
	 * @param response
	 * @param request
	 * @param key
	 */
	public static void deleteCookie(HttpServletResponse response, HttpServletRequest request, String key) {

		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(key)) {
					Cookie cookie = new Cookie(key, null);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}

	public static void setCookieTime(HttpServletResponse response, HttpServletRequest request, String key,int second) {

		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(key)) {
					cookies[i].setMaxAge(second);
					response.addCookie(cookies[i]);
				}
			}
		}
	}


	public static String getCookieValue(HttpServletRequest request, String key) throws UnsupportedEncodingException {
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals(key)) {
				return URLDecoder.decode(cookie.getValue(), "UTF-8");
			}
		}
		return null;
	}

}