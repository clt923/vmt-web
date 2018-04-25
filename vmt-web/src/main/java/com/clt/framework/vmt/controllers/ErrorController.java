package com.clt.framework.vmt.controllers;

import javax.servlet.http.HttpServletRequest;




import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.clt.framework.vmt.dto.UserInforDto;

@Controller
public class ErrorController extends BaseController {
	private final Logger logger = Logger.getLogger(ErrorController.class);
    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
        ModelAndView errorPage = new ModelAndView("errorPage");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
        Throwable throwable = (Throwable) httpRequest.getAttribute("javax.servlet.error.exception");
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }
        logger.error("URL: "+httpRequest.getRequestURL());
        logger.error("MesageError: "+ throwable);
        UserInforDto userInfor=getUserInfo();
        if(userInfor!=null){
        	logger.error("TTNO: "+userInfor.getUser().getTtNo().toUpperCase());
            logger.error("UserId: "+userInfor.getUser().getUserId().toUpperCase());
        }
        //errorMsg=throwable.getMessage();
        errorPage.addObject("errorMsg", errorMsg);
        errorPage.addObject("errorCode", httpErrorCode);
        return errorPage;
    }
     
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
    
    @ExceptionHandler(value = Exception.class)
    public void handleAllException(Exception e) {
    	if(e.getMessage()!=null){
    		logger.error("MesageError: "+ e.getMessage());
    	}
    	 
    }
}
