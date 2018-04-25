package com.clt.framework.vmt.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.clt.framework.vmt.dto.UserInforDto;


public class BaseController {
	private final Logger logger = Logger.getLogger(BaseController.class);
	private String isEnableSuspend;
	private String isEnableUpdateDamage;
	private String isEnableRefresh;
	private String versionNumber;
	
	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	
	public String getIsEnableSuspend() {
		return isEnableSuspend;
	}

	public void setIsEnableSuspend(String isEnableSuspend) {
		this.isEnableSuspend = isEnableSuspend;
	}

	public String getIsEnableUpdateDamage() {
		return isEnableUpdateDamage;
	}

	public void setIsEnableUpdateDamage(String isEnableUpdateDamage) {
		this.isEnableUpdateDamage = isEnableUpdateDamage;
	}

	public String getIsEnableRefresh() {
		return isEnableRefresh;
	}

	public void setIsEnableRefresh(String isEnableRefresh) {
		this.isEnableRefresh = isEnableRefresh;
	}

	public UserInforDto getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       try {
    	   return (UserInforDto) authentication.getPrincipal();
		} catch (Exception e) {
			return null;
		}
       
    }
	
	public Authentication getAuthencation(){
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  return authentication;
	}
	
	public void setUnAuthencation(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 authentication.setAuthenticated(false);
	}

	public String GetDateTimeNow(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	//Return minutes
	public long TotaltimeBetweenDate(String startDateString){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date startDate;
		try {
			startDate = dateFormat.parse(startDateString);
			
			long duration  = new Date().getTime() - startDate.getTime();
			long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
			
			return diffInMinutes;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
