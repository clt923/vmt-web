package com.clt.framework.vmt.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.framework.vmt.constants.CommonConstants;
import com.clt.framework.vmt.dto.MessageResponseDto;
import com.clt.framework.vmt.dto.UserInforDto;
import com.clt.framework.vmt.dto.UserLogin;
import com.clt.framework.vmt.service.HessianUserService;
import com.clt.framework.vmt.service.TTNoStoreService;
import com.clt.framework.vmt.utilities.CommonUtil;

@Controller
public class LoginController extends BaseController{
	private String versionNumber;

	@Resource(name="sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	@Autowired
    private MessageSource messageSource;
	
	private HessianUserService userService;
	private TTNoStoreService ttnoMappingService;
	
	public TTNoStoreService getTtnoMappingService() {
		return ttnoMappingService;
	}

	public void setTtnoMappingService(TTNoStoreService ttnoMappingService) {
		this.ttnoMappingService = ttnoMappingService;
	}

	public void setUserService(HessianUserService userService) {
		this.userService = userService;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(defaultValue="",value="url",required=false) String url,
			@CookieValue(value = CommonConstants.COOKIE_TTNO, defaultValue = "") String ttnoCookie,
			@RequestParam(value = "error", required = false) String error,
			HttpServletRequest request,HttpServletResponse response,
			ModelMap model) {
		//System.out.println(VmtWorkOrder.class.getDeclaredFields().length);
		Locale local= LocaleContextHolder.getLocale();
		request.getSession().setAttribute(CommonConstants.VERSION_NUMBER, versionNumber);
		if(error!=null){
			//String messageError=request.getSession().getAttribute(CommonConstants.ERROR_LOGIN_MESSAGE)+"";
			String strError="";
			//String strErrorCode="";
			if(request.getSession().getAttribute(CommonConstants.ERROR_LOGIN_MESSAGE)!=null){
				MessageResponseDto messcodeError=(MessageResponseDto) request.getSession().getAttribute(CommonConstants.ERROR_LOGIN_MESSAGE);
				if(messcodeError.isServeError()){
					if("F1".equals(messcodeError.getErrorMessage())){
						strError=messageSource.getMessage("LoginError.F1", null, local);
					}else if("F2".equals(messcodeError.getErrorMessage())){
						strError=messageSource.getMessage("LoginError.F2", null, local);
					}else if("F3".equals(messcodeError.getErrorMessage())){
						strError=messageSource.getMessage("LoginError.F3", null, local);
					}else if("F4".equals(messcodeError.getErrorMessage())){
						strError=messageSource.getMessage("LoginError.F4", null, local);
					}else if("F5".equals(messcodeError.getErrorMessage())){
						strError=messageSource.getMessage("LoginError.F5", null, local);
					}else if("F6".equals(messcodeError.getErrorMessage())){
						strError=messageSource.getMessage("LoginError.F6", null, local);
					}else if("F7".equals(messcodeError.getErrorMessage())){
						strError=messageSource.getMessage("LoginError.F7", null, local);
					}else if("F8".equals(messcodeError.getErrorMessage())){
						strError=messageSource.getMessage("LoginError.F8", null, local);
					}else{
						strError=messageSource.getMessage("LoginError.U1", null, local);
					}
				}else{
					strError=messageSource.getMessage(messcodeError.getErrorMessage(), null, local);
				}
				model.addAttribute("errorCode",messcodeError.getErrorMessage());
			}
			model.addAttribute("messageError", strError);
			
		}
		UserInforDto userInfor=getUserInfo();
		if(!CommonUtil.isNull(userInfor)){
			return "redirect:/tt-201";
		}
		String username=(String) request.getSession().getAttribute("username");
		String password=(String) request.getSession().getAttribute("password");
		UserLogin user=new UserLogin();
		user.setCurrentUrl(url);
		if(!CommonUtil.isNull(ttnoCookie)){
			user.setTtNo(ttnoCookie);
		}else{
		String ttno=ttnoMappingService.getTTnoByipAddress(request.getRemoteAddr());
			user.setTtNo(ttno);
		}
		if(CommonUtil.isNull(user.getTtNo())){
			String ttNo=(String) request.getSession().getAttribute("ttNo");
			user.setTtNo(ttNo);
		}
		user.setUserId(username);
		user.setPassword(password);
		model.addAttribute("userForm", user);
		model.addAttribute("lang",local.getLanguage());
		
		return "login";
	}
	
	
	@RequestMapping(value = "/getUserGroup", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getUserGroup(@RequestBody UserLogin user) {
		List<String> lstUserGroup=null;
		HashMap<String, Object> map=new HashMap<String, Object>();
		MessageResponseDto result=null;
		Locale local= LocaleContextHolder.getLocale();
		if(!CommonUtil.isNull(user)&&!CommonUtil.isNull(user.getUserId().toUpperCase())){
			try {
				lstUserGroup=userService.getUserAccessRole(user.getUserId().toUpperCase());
				if(!CommonUtil.isNull(lstUserGroup)&&lstUserGroup.size()>0){
					result=new MessageResponseDto(true,"");
					map.put("error", result);
					map.put("data", lstUserGroup);
				}else{
					String error=messageSource.getMessage("MS0001", null, local);
					result=new MessageResponseDto(false,error);
					map.put("error", result);
					
				}
			} catch (Exception e) {
				String error=messageSource.getMessage("MS0002", null, local);
				result=new MessageResponseDto(false,error);
				result.setServeError(true);
				map.put("error", result);
			}
		}
		return map;
	}
	
	@RequestMapping(value="/logutuserDuplicator", method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> logutuserDuplicator(HttpServletRequest request){
		HashMap<String, Object> map=new HashMap<String, Object>();
		String userName=request.getParameter("username");
		List<Object> principals = sessionRegistry.getAllPrincipals();
		for (Object object : principals) {
			if(object instanceof UserInforDto){
				UserInforDto userInf=(UserInforDto)object;
				if(userName.equals(userInf.getUser().getUserId())){
					for (SessionInformation session : sessionRegistry.getAllSessions(object, false)) {
		                session.expireNow();
		            }
					break;
				}
			}
		}
		
		return map;
	}
	
}	
