package com.wedo.init;

public class WeDoWordInfo {
	
	private String enWord;
	private String cnWord;
	private String desc="";
	private String partOfSpeech="";
	
	public WeDoWordInfo(String enWord,String cnWord) {
		this.enWord=enWord;
		this.cnWord=cnWord;
	}
	public WeDoWordInfo() {
		this.enWord="";
		this.cnWord="";
	}

	public String getEnWord() {
		return enWord;
	}

	public void setEnWord(String enWord) {
		this.enWord = enWord;
	}

	public String getCnWord() {
		return cnWord;
	}

	public void setCnWord(String cnWord) {
		this.cnWord = cnWord;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPartOfSpeech() {
		return partOfSpeech;
	}

	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	
	

}
