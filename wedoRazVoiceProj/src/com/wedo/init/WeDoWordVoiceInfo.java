package com.wedo.init;

import org.dom4j.Element;

public class WeDoWordVoiceInfo {
	private WeDoWordInfo wordInfo;
	public WeDoWordInfo getWordInfo() {
		return wordInfo;
	}

	public void setWordInfo(WeDoWordInfo wordInfo) {
		this.wordInfo = wordInfo;
	}

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public double getCnSpeakSpeed() {
		return cnSpeakSpeed;
	}

	public void setCnSpeakSpeed(double cnSpeakSpeed) {
		this.cnSpeakSpeed = cnSpeakSpeed;
	}

	public double getEnSpeakSpeed() {
		return enSpeakSpeed;
	}

	public void setEnSpeakSpeed(double enSpeakSpeed) {
		this.enSpeakSpeed = enSpeakSpeed;
	}

	//结尾间隔时间，单位毫秒 默认 3000ms;
	private int delayTime=3000;
	
	//中文发音的相对速度
	private double cnSpeakSpeed = -0.1;
	//英文文发音的相对速度
	private double enSpeakSpeed = 0;
	
	public WeDoWordVoiceInfo(WeDoWordInfo wordInfo) {
		this.wordInfo = wordInfo;
	}
	
	public void addVoiceNodeParent(Element parentNode) {
		
		/**
		 * <voice name="en-US-JennyNeural">
		 * <mstts:express-as style="friendly">cold</mstts:express-as> </voice>
		 * 
		 **/
		Element enVoiceNode = parentNode.addElement("voice").addAttribute("name", "en-US-JennyNeural");
		Element tmpEnWordNode = enVoiceNode.addElement("mstts:express-as").addAttribute("style","friendly");
		tmpEnWordNode.setText(wordInfo.getEnWord());
		/**
		 * 
		 * <voice name="zh-CN-YunyangNeural"> 
		 * 	<prosody rate="-30.00%">寒冷的</prosody>
		 * 	<break time="3000ms" /> 
		 * </voice>
		 **/
		Element cnVoiceNode = parentNode.addElement("voice").addAttribute("name", "zh-CN-YunyangNeural");
		cnVoiceNode.addElement("prosody").addAttribute("rate", "-30.00%").setText(wordInfo.getCnWord());
		cnVoiceNode.addElement("break").addAttribute("time", "3000ms");
	}
}
