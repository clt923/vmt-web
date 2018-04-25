package com.clt.framework.vmt.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {
	public static boolean isNull(String obj) {
		if (obj == null || obj.equals("") || obj.equals("null")
				|| obj.equals("undefined")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNull(Object obj) {
		if (obj == null || obj.equals("") || obj.equals("null")
				|| obj.equals("undefined")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String objectToJson(Object obj) throws JsonProcessingException {
		  ObjectMapper mapper = new ObjectMapper();
		  // Object to JSON in String
		  String sValue = mapper.writeValueAsString(obj);
		  return sValue;
	}

	public static String CallAPIRestFull(String messagePath,String objectJson){
		String jsondata="";
		
		
		try {
			URL url = new URL(messagePath);
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(objectJson.getBytes());
			os.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			jsondata = br.readLine();
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return jsondata;
	}
}
