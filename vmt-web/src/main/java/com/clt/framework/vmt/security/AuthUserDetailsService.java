package com.clt.framework.vmt.security;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.clt.framework.vmt.constants.CommonConstants;
import com.clt.framework.vmt.dto.MessageResponseDto;
import com.clt.framework.vmt.dto.UserInforDto;
import com.clt.framework.vmt.dto.UserLogin;
import com.clt.framework.vmt.service.HessianUserService;
import com.clt.framework.vmt.utilities.CommonUtil;
import com.clt.tos.external.vmt.wp.model.VmtLogin;

public class AuthUserDetailsService implements UserDetailsService{

	@Resource(name="sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	private MessageSource messgeSource;
	
	private HessianUserService userService;
	private String passwordMonitoring;
	
	public void setUserService(HessianUserService userService) {
		this.userService = userService;
	}

	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}

	public void removeSessionRegistry(String sesisonId){
		sessionRegistry.removeSessionInformation(sesisonId);
	}
	
	public MessageSource getMessgeSource() {
		return messgeSource;
	}

	public void setMessgeSource(MessageSource messgeSource) {
		this.messgeSource = messgeSource;
	}
	
	public void setPasswordMonitoring(String passwordMonitoring) {
		this.passwordMonitoring = passwordMonitoring;
	}

	public void LogoutUserBySessionID(String sessionId){
		if(sessionRegistry.getAllPrincipals().size()>0){
			List<Object> principals = sessionRegistry.getAllPrincipals();
			for (Object object : principals) {
				if(object instanceof UserInforDto){
					UserInforDto userInf=(UserInforDto)object;
					if((sessionId.equals(userInf.getSessionId()))){
						UserLogin userlogin=userInf.getUser();
						if(userlogin!=null){
							//userService.setLogout4Machine(userlogin.getUserId().toUpperCase(),userlogin.getPassword(),userlogin.getTtNo().toUpperCase());
							//userService.setMachineStatus(userlogin.getTtNo().toUpperCase(), false);
						}
						break;
					}
				}
			}
		}
	}
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		String ttNo=RequestContextHolder.getRequestAttributes().getAttribute("ttNo", RequestAttributes.SCOPE_SESSION)+"";
		String ipAdress=RequestContextHolder.getRequestAttributes().getAttribute("ipAddress", RequestAttributes.SCOPE_SESSION)+"";
		String lang=RequestContextHolder.getRequestAttributes().getAttribute("lang", RequestAttributes.SCOPE_SESSION)+"";
		String action=RequestContextHolder.getRequestAttributes().getAttribute("action", RequestAttributes.SCOPE_SESSION)+"";
		String team=RequestContextHolder.getRequestAttributes().getAttribute("team", RequestAttributes.SCOPE_SESSION)+"";
		String password=RequestContextHolder.getRequestAttributes().getAttribute("password", RequestAttributes.SCOPE_SESSION)+"";
		String trailerNo=RequestContextHolder.getRequestAttributes().getAttribute("trailerNo", RequestAttributes.SCOPE_SESSION)+"";
		username=username.toUpperCase();
		if("CLT".equals(username.toUpperCase()) && passwordMonitoring.equals(password.toUpperCase())){
			UserLogin userlogin=new UserLogin();
			userlogin.setUserId(username);
			userlogin.setTtNo(ttNo);
			userlogin.setPassword(password);
			userlogin.setMachineType("YT");
			userlogin.setUserName(username);
			userlogin.setIpAddress(ipAdress);
			userlogin.setTrailerNo(trailerNo);
			UserInforDto userInforDto=new UserInforDto(userlogin);
			return userInforDto;
		}
		
		
		if(CommonUtil.isNull(username)||(CommonUtil.isNull(password))){
			MessageResponseDto messageError=new MessageResponseDto(true,"MS0005",false);
			RequestContextHolder.getRequestAttributes().setAttribute(CommonConstants.ERROR_LOGIN_MESSAGE,messageError , RequestAttributes.SCOPE_SESSION);
			throw new UsernameNotFoundException("error");
		}
		
		if(CommonUtil.isNull(trailerNo)){
			MessageResponseDto messageError=new MessageResponseDto(true,"MS0010",false);
			RequestContextHolder.getRequestAttributes().setAttribute(CommonConstants.ERROR_LOGIN_MESSAGE,messageError , RequestAttributes.SCOPE_SESSION);
			throw new UsernameNotFoundException("error");
		}else{
			String regexTrailerNo="\\d{3}";
			boolean isCorrectformat=trailerNo.matches(regexTrailerNo);
			if(!isCorrectformat){
				MessageResponseDto messageError=new MessageResponseDto(true,"MS0011",false);
				RequestContextHolder.getRequestAttributes().setAttribute(CommonConstants.ERROR_LOGIN_MESSAGE,messageError , RequestAttributes.SCOPE_SESSION);
				throw new UsernameNotFoundException("error");
			}
		}
		
		VmtLogin checkLogin=userService.setLogin4Machine(username.toUpperCase(), password.toUpperCase(), ttNo.toUpperCase(), team.toUpperCase(),trailerNo.toUpperCase());
		if(!CommonUtil.isNull(checkLogin)){
			if(!"S".equals(checkLogin.getRsnCd())){
				MessageResponseDto messageError=new MessageResponseDto(true,checkLogin.getRsnCd(),true);
				RequestContextHolder.getRequestAttributes().setAttribute(CommonConstants.ERROR_LOGIN_MESSAGE,messageError , RequestAttributes.SCOPE_SESSION);
				throw new UsernameNotFoundException("error");
			}else{
				if(sessionRegistry.getAllPrincipals().size()>0){
					List<Object> principals = sessionRegistry.getAllPrincipals();
					/*for (Object object : principals) {
						if(object instanceof UserInforDto){
							UserInforDto userInf=(UserInforDto)object;
							if(username.equals(userInf.getUser().getUserId())){
								if((ttNo.equals(userInf.getUser().getTtNo()))){
									SessionInformation sessionInformation=sessionRegistry.getSessionInformation(userInf.getSessionId());
									if(sessionInformation.isExpired()){
										continue;
									}
									MessageResponseDto messageError=new MessageResponseDto(true,"MS0004",false);
									RequestContextHolder.getRequestAttributes().setAttribute(CommonConstants.ERROR_LOGIN_MESSAGE,messageError , RequestAttributes.SCOPE_SESSION);
									throw new UsernameNotFoundException("error");
								}
							}
						}
					}*/
				}
				//userService.setMachineStatus(trailerNo, true);
				UserLogin userlogin=new UserLogin();
				userlogin.setUserId(username);
				userlogin.setTtNo(ttNo);
				userlogin.setPassword(password);
				userlogin.setMachineType("YT");
				userlogin.setUserName(checkLogin.getUsrNm());
				userlogin.setIpAddress(ipAdress);
				userlogin.setTrailerNo(trailerNo);
				return new UserInforDto(userlogin);
			}
		}
		return null;
	}

}
