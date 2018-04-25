package com.clt.framework.vmt.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.clt.framework.vmt.dto.UserInforDto;
import com.clt.framework.vmt.dto.UserLogin;
import com.clt.framework.vmt.service.HessianUserService;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Resource(name="sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	private HessianUserService userService;

	public void setUserService(HessianUserService userService) {
		this.userService = userService;
	}

	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication != null && authentication.getDetails() != null) {
			
			try {
				String forcelogout=request.getParameter("forceLogout");
				//System.out.println(forcelogout);
				UserInforDto userInfor= (UserInforDto) authentication.getPrincipal();
				if(userInfor!=null){
					new SecurityContextLogoutHandler().logout(request, response, authentication);
					UserLogin userlogin=userInfor.getUser();
					if("CLT".equals(userlogin.getUserId().toUpperCase())==false){
						for (SessionInformation session : sessionRegistry.getAllSessions(userInfor, false)) {
			                session.expireNow();
			            }
					}else{
						for (SessionInformation session : sessionRegistry.getAllSessions(userInfor, false)) {
			                session.expireNow();
			            }
					}
				}
				
				request.getSession().invalidate();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		response.setStatus(HttpServletResponse.SC_OK);
        //redirect to login
		response.sendRedirect("login");
	}

}
