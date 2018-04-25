package com.clt.framework.vmt.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.clt.framework.vmt.constants.CommonConstants;
import com.clt.framework.vmt.dto.MessageResponseDto;
import com.clt.framework.vmt.dto.UserInforDto;
import com.clt.framework.vmt.service.HessianUserService;
import com.clt.framework.vmt.utilities.CommonUtil;

public class ExUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private HessianUserService userService;
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;

	public void setUserService(HessianUserService userService) {
		this.userService = userService;
	}

	@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String ttNo = request.getParameter("ttNo");
        request.getSession().setAttribute("ttNo", ttNo);
        
        String team = request.getParameter("team");
        request.getSession().setAttribute("team", team);
        
        String password = request.getParameter("password");
        request.getSession().setAttribute("password", password);
        
        String username = request.getParameter("username");
        request.getSession().setAttribute("username", username.toUpperCase());
        String trailerNo = request.getParameter("trailerNo");
        request.getSession().setAttribute("trailerNo", trailerNo);
        String lang=request.getParameter("lang");
        if(CommonUtil.isNull(lang)){
        	lang="en";
        }
        request.getSession().setAttribute("lang", lang);
        String ipAddress= request.getRemoteAddr();
        request.getSession().setAttribute("ipAddress", ipAddress);

        String actionName=request.getParameter("actionLogin");
        
        if(actionName.equals("2")){
        	if(sessionRegistry.getAllPrincipals().size()>0){
				List<Object> principals = sessionRegistry.getAllPrincipals();
				boolean isExistUser=false;
				//List<String> lstRemoveSessionInfor=new ArrayList<String>();
				for (Object object : principals) {
					if(object instanceof UserInforDto){
						UserInforDto userInf=(UserInforDto)object;
						if((ttNo.equals(userInf.getUser().getTtNo())||username.equals(userInf.getUser().getUserId()))){
							 for (SessionInformation information : sessionRegistry.getAllSessions(object, true)) {
			                        information.expireNow();
			                 }
							
							 isExistUser=true;
						}
					}
				}
				/*if(isExistUser){
					 
				}*/
				
        	}	
        	userService.setLogout4Machine(username, password, ttNo);
        	//userService.setMachineStatus(ttNo, false);
			MessageResponseDto messageError=new MessageResponseDto(true,"SP001",false);
			RequestContextHolder.getRequestAttributes().setAttribute(CommonConstants.ERROR_LOGIN_MESSAGE,messageError , RequestAttributes.SCOPE_SESSION);
			throw new AuthenticationException("Force Logout"){};
        }
        
        return super.attemptAuthentication(request, response); 
    } 
}
