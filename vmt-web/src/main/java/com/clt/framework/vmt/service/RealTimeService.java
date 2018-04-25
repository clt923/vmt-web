package com.clt.framework.vmt.service;

import com.clt.framework.vmt.dto.RealTimeRequestDto;

public class RealTimeService {
	private static final String USER_AGENT = "Mozilla/5.0";
	private String host;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void RealTimeService(){
		
	}
	
	public void postRealTime(RealTimeRequestDto dto ) throws Exception {

		/*String POST_URL = host + "/" + dto.getApi();
		URL obj = new URL(POST_URL);
		
		try{
			
		    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		    con.setDoOutput(true);
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			
			OutputStream os = con.getOutputStream();
			os.write(dto.getJsonData().getBytes("UTF-8"));
			os.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(con.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			con.disconnect();

			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}
}
