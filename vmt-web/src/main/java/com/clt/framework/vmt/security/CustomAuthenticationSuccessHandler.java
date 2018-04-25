package com.clt.framework.vmt.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.clt.framework.vmt.constants.CommonConstants;
import com.clt.framework.vmt.dto.UserInforDto;
import com.clt.framework.vmt.service.TTNoStoreService;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {
	private String redirectPageDamge="";
	private TTNoStoreService ttnoMappingService;
	
	public TTNoStoreService getTtnoMappingService() {
		return ttnoMappingService;
	}
	
	public void setTtnoMappingService(TTNoStoreService ttnoMappingService) {
		this.ttnoMappingService = ttnoMappingService;
	}

	public String getRedirectPageDamge() {
		return redirectPageDamge;
	}

	public void setRedirectPageDamge(String redirectPageDamge) {
		this.redirectPageDamge = redirectPageDamge;
	}

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		UserInforDto userinfor= (UserInforDto) authentication.getPrincipal();
		userinfor.setSessionId(request.getSession().getId());
		/*JobListDto dto=new JobListDto();
		dto.setUserid(userinfor.getUser().getUserId());
		dto.setTtNo(userinfor.getUser().getTtNo());
		dto.setMachineType(userinfor.getUser().getMachineType());
		dto.setMachineOn(true);
		dto.setTrailerNo(userinfor.getUser().getTrailerNo());
		autoloadService.LoadJobListManual(dto);*/
		
		Cookie cookieTtNo = new Cookie(CommonConstants.COOKIE_TTNO, userinfor.getUser().getTtNo());
		cookieTtNo.setMaxAge(172800);//2 days expire
		response.addCookie(cookieTtNo);
		response.setStatus(HttpServletResponse.SC_OK);
		if("N".equals(redirectPageDamge)){
			response.sendRedirect("tt-201");
		}else{
			response.sendRedirect("tt-401");
		}
	}
}
