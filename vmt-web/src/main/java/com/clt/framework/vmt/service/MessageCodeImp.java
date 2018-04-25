package com.clt.framework.vmt.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.clt.framework.vmt.dto.MessageResponseDto;
import com.clt.framework.vmt.utilities.CommonUtil;

public class MessageCodeImp implements MessageCodeInterface{

	private Map<String, String> mapMessage = new HashMap<String, String>();
	String propFileName = "messages_en.properties";
	
	public MessageCodeImp() throws IOException{
		Properties prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		if (inputStream != null) {
			prop.load(inputStream);
			for (String key : prop.stringPropertyNames()) {
				String value = prop.getProperty(key);
				mapMessage.put(key, value);
			}
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
	}
	
	public MessageResponseDto GetValueByKey(String key) {
		String value = mapMessage.get(key);
		MessageResponseDto result = null;
		if (!CommonUtil.isNull(value)) {
			result = new MessageResponseDto();
			String[] arrayCode = value.split("\\|");
			int messagecode = 0;
			if (arrayCode.length > 0) {
				messagecode = Integer.parseInt(arrayCode[0]);
				if(messagecode!=0){
					result.setStatus(false);
				}
				else{
					result.setStatus(true);
				}
				
				if (arrayCode.length > 1) {
					result.setErrorMessage(arrayCode[1]);
				} else {
					// result.setResponseMsg(StringUtils.EMPTY);
				}
			}
		}
		return result;
	}

	public MessageResponseDto GetValueByKeyAndFormat(String key, Object... args) {
		String value = mapMessage.get(key);
		MessageResponseDto result = null;
		if (!CommonUtil.isNull(value)) {
			result = new MessageResponseDto();
			String[] arrayCode = value.split("\\|");
			int messagecode = 0;
			if (arrayCode.length > 0) {
				messagecode = Integer.parseInt(arrayCode[0]);
				if(messagecode!=0){
					result.setStatus(false);
				}
				else{
					result.setStatus(true);
				}
				if (arrayCode.length > 1) {
					String strFormat = arrayCode[1];
					result.setErrorMessage(String.format(strFormat, args));
				}
			}
		}
		return result;
	}

}
