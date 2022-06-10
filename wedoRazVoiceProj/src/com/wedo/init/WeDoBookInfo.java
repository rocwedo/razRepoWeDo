package com.wedo.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class WeDoBookInfo {

	private ArrayList<WeDoWordInfo> wedoWordsList = new ArrayList<WeDoWordInfo>();
	private String razBookName;
	private String razLevel;
	private Document document;
	private Element speakNode;
	public WeDoBookInfo() {
	}
	public WeDoBookInfo(String razBookName,String razLevel) {
		this.razBookName = razBookName;
		this.razLevel = razLevel;
	}
	public String getRazBookName() {
		return razBookName;
	}

	public void setRazBookName(String razBookName) {
		this.razBookName = razBookName;
	}
	
	public String getRazLevel() {
		return razLevel;
	}
	public void setRazLevel(String razLevel) {
		this.razLevel = razLevel;
	}
	
	public void addWoDoWords(WeDoWordInfo wedoWordInfo) {
		wedoWordsList.add(wedoWordInfo);
	}
	
	public ArrayList<WeDoWordInfo> getWoDoWordsList(){
		return wedoWordsList;
	}
	
	private void loadSSMLTempl(String pathFile) throws DocumentException {
		SAXReader reader = new SAXReader();
		document = reader.read(new File(pathFile));
		speakNode = document.getRootElement();
	}
	
	public void toVoiceSSMLFile() {
		try {
			loadSSMLTempl("res\\templ.xml");
			String destFileName = new StringBuffer("output\\").append(razBookName).append(".xml").toString();

			/**
			 * <voice name="en-US-JennyNeural"> 
			 * 	<mstts:express-as style="friendly"> 
			 * 		Raz  level B 
			 * 		<break time="1000ms" /> 
			 * 		A Cold Day 
			 * 		<break time="2000ms" />
			 * 	</mstts:express-as> 
			 * </voice>
			 **/
			Element fileElement = speakNode.addElement("voice").addAttribute("name", "en-US-JennyNeural");
			Element msttsElement = fileElement.addElement("mstts:express-as").addAttribute("style", "friendly");
			msttsElement.addText(razLevel);
			msttsElement.addElement("break").addAttribute("time", "1000ms");
			msttsElement.addText(this.razBookName);
			msttsElement.addElement("break").addAttribute("time", "2000ms");
			
			Iterator<WeDoWordInfo> it = this.wedoWordsList.iterator();
			
			while (it.hasNext()) {
				WeDoWordInfo tmpWeDoWordInfo = it.next();
				WeDoWordVoiceInfo tmpWeDoWordVoiceInfo = new WeDoWordVoiceInfo(tmpWeDoWordInfo);
				tmpWeDoWordVoiceInfo.addVoiceNodeParent(speakNode);
			}
			
//			System.out.println(document.asXML());
			FileWriter fileWriter = new FileWriter(destFileName);
			XMLWriter writer = new XMLWriter(fileWriter);
            writer.write(document);
            writer.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void toVoiceSSMLFile(String outputFoldPath) {
		try {
			loadSSMLTempl("res\\templ.xml");
			String destPath = new StringBuffer("output\\").append(outputFoldPath).toString();
			File dir = new File(destPath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			String destFileName = new StringBuffer(destPath).append("\\").append(razBookName).append(".xml").toString();

			/**
			 * <voice name="en-US-JennyNeural"> 
			 * 	<mstts:express-as style="friendly"> 
			 * 		Raz  level B 
			 * 		<break time="1000ms" /> 
			 * 		A Cold Day 
			 * 		<break time="2000ms" />
			 * 	</mstts:express-as> 
			 * </voice>
			 **/
			Element fileElement = speakNode.addElement("voice").addAttribute("name", "en-US-JennyNeural");
			Element msttsElement = fileElement.addElement("mstts:express-as").addAttribute("style", "friendly");
			msttsElement.addText(razLevel);
			msttsElement.addText(": ");
			msttsElement.addElement("break").addAttribute("time", "1000ms");
			msttsElement.addText(this.razBookName);
			msttsElement.addElement("break").addAttribute("time", "2000ms");
			
			Iterator<WeDoWordInfo> it = this.wedoWordsList.iterator();
			
			while (it.hasNext()) {
				WeDoWordInfo tmpWeDoWordInfo = it.next();
				WeDoWordVoiceInfo tmpWeDoWordVoiceInfo = new WeDoWordVoiceInfo(tmpWeDoWordInfo);
				tmpWeDoWordVoiceInfo.addVoiceNodeParent(speakNode);
			}
			
//			System.out.println(document.asXML());
			FileWriter fileWriter = new FileWriter(destFileName);
			XMLWriter writer = new XMLWriter(fileWriter);
            writer.write(document);
            writer.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
