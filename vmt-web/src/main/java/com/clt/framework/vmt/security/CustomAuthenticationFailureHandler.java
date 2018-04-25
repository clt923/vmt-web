package com.clt.framework.vmt.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.clt.framework.vmt.constants.CommonConstants;
import com.clt.framework.vmt.dto.MessageResponseDto;
import com.clt.framework.vmt.utilities.CommonUtil;

public class CustomAuthenticationFailureHandler implements
		AuthenticationFailureHandler {

	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		if(request.getSession().getAttribute(CommonConstants.ERROR_LOGIN_MESSAGE)!=null){
			MessageResponseDto messcodeError=(MessageResponseDto) request.getSession().getAttribute(CommonConstants.ERROR_LOGIN_MESSAGE);
			if(!CommonUtil.isNull(messcodeError)&&messcodeError.getErrorMessage().equals("SP001")){
				response.sendRedirect("login");
				return;
			}
		}
		String lang=(String)request.getSession().getAttribute("lang");
		response.sendRedirect("login?error&lang="+lang);
	}

}
