package com.clt.framework.vmt.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.clt.framework.vmt.constants.CommonConstants;
import com.clt.framework.vmt.dto.UserLogin;
import com.clt.framework.vmt.utilities.CommonUtil;

public class AuthenticationInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory
			.getLogger(AuthenticationInterceptor.class);

		
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("After-completion");
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		log.debug("Post-handle");
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		
		String url=request.getRequestURI();
		String urlServerlet=request.getServletPath();
		List<String> lstNotAuthencation =new ArrayList<String>();
		lstNotAuthencation.add("/vmt-web/login");
		lstNotAuthencation.add("/vmt-web/userLogin");
		lstNotAuthencation.add("/vmt-web/getUserGroup");
		// Avoid a redirect loop for some urls
		if (!lstNotAuthencation.contains(url)){
			UserLogin userlogin = (UserLogin) request.getSession().getAttribute(
					CommonConstants.LOGIN_USER);
			if (CommonUtil.isNull(userlogin)) {
				response.sendRedirect("/vmt-web/login?url= "+urlServerlet);
				return false;
			}
		}
		return true;
	}

	
}
