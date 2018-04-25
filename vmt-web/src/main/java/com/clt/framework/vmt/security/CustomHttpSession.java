package com.clt.framework.vmt.security;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CustomHttpSession implements HttpSessionListener {
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		 HttpSession session = se.getSession();
		 ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		 if(ctx!=null){
			 com.clt.framework.vmt.security.AuthUserDetailsService authenUser=(com.clt.framework.vmt.security.AuthUserDetailsService ) ctx.getBean("authUserDetailsService");
			 if(authenUser!=null){
				 authenUser.LogoutUserBySessionID(session.getId());
				 session.invalidate();
				 authenUser.removeSessionRegistry(session.getId());
			 }
		 }
	}
}
