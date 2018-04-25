package com.clt.framework.vmt.service;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TTNoStoreService {
	private String fileTTNo = "TTNoIPAdress.xml";
	private File fXmlFile = null;
	private DocumentBuilderFactory dbFactory = null;
	private DocumentBuilder dBuilder = null;
	private Document doc = null;

	public TTNoStoreService() {
		createFolder();
		fXmlFile = new File(System.getProperty("catalina.base")+"/Files/"+fileTTNo);
		dbFactory = DocumentBuilderFactory.newInstance();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void createFolder(){
		String pathTomcatRoot=System.getProperty("catalina.base")+"/Files/";
		System.out.println(pathTomcatRoot);
	    File dir = new File(pathTomcatRoot);
	    boolean exists = dir.exists();
	    if (!exists) {
	    	dir.mkdir();
		}
	    pathTomcatRoot=System.getProperty("catalina.base")+File.separator+"Files"+File.separator+fileTTNo;
    	dir=new File(pathTomcatRoot);
    	if(!dir.exists()&&!dir.isDirectory()){
    		//System.out.println(pathTomcatRoot);
    		try {
    			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("IpTTNoMap");
				doc.appendChild(rootElement);
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(dir);
				
				transformer.transform(source, result);
				//System.out.println("File saved!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
	}
	
	/*
	public void setIpAddressForTTNo(String ipadress,String TTno) {
		Element  IpMap = doc.getDocumentElement();
		NodeList listIp = doc.getElementsByTagName("Ip");
		boolean addNew=true;
		for (int i = 0; i < listIp.getLength(); i++) {
			Node ip = listIp.item(i);
			if (ip.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) ip;
				if(ipadress.trim().equals(eElement.getAttribute("id"))){
					eElement.setTextContent(TTno.trim());
					addNew=false;
				}
			}
		}
		if(addNew){
			Element ip=doc.createElement("Ip");
			ip.setAttribute("id", ipadress.trim());
			ip.setTextContent(TTno.trim());
			IpMap.appendChild(ip);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(fXmlFile);
			transformer.transform(source, result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	*/
	
	 public String getTTnoByipAddress(String ipaddress) {
		 NodeList listIp = doc.getElementsByTagName("Ip");
		 for (int i = 0; i < listIp.getLength(); i++) {
				Node ip = listIp.item(i);
				if (ip.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) ip;
					if(ipaddress.trim().equals(eElement.getAttribute("id"))){
						return eElement.getTextContent();
					}
				}
			}
		 return "";
	 }
	 

}
