package com.wedo.init;

import java.io.File;
import java.util.ArrayList;

public class WeDoBlocVoiceServiceApp {
	private static WeDoBlocSSMLFileFactory wedotest = WeDoBlocSSMLFileFactory.initWeDoBlocSSMLFileFactory();
	private static WeDoBlocVoiceServiceApp app = new WeDoBlocVoiceServiceApp();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start...");
		
		app.dealwithRes("res\\raz02\\raz m.txt","Raz level M");
//		app.dealwithResFold("res\\raz02");
		System.out.println("End");
	}
	
	public void dealwithRes(String fileName,String razLevel) {
		wedotest.splitResFile(fileName,razLevel);
		wedotest.toVoiceSSMLFile();
		wedotest.clearVoiceSSMLFileList();
	}
	
	public void dealwithRes(String fileName,String razLevel,String outputPath) {
		wedotest.splitResFile(fileName,razLevel);
		wedotest.toVoiceSSMLFile(outputPath);
		wedotest.clearVoiceSSMLFileList();
		
	}
	
	public void dealwithResFold(String foldname) {
		File foldFile = new File(foldname);
		File[] tmpFiles=foldFile.listFiles();
		for(int i=0;i<tmpFiles.length;i++) {
			if(tmpFiles[i].isFile()) {
			String tmpString = tmpFiles[i].getName();
			String[] tmp = tmpString.split(" ");
				if(tmp.length>1) {
					//生成对应Raz级别
					StringBuffer buffRazLevelString= new StringBuffer("raz level ").append(tmp[1].trim().toUpperCase());
					//生成对应目标文件夹
					StringBuffer destOutputPath =new StringBuffer("raz level ").append(tmp[1].trim().toUpperCase());
					if(tmp.length>2) {
						if(tmp[2].endsWith(".txt")) {
							tmp[2] = tmp[2].substring(0, tmp[2].indexOf("."));
						}
						destOutputPath = destOutputPath.append(" term ").append(tmp[2]);
					}else {
						
					}
					app.dealwithRes(tmpFiles[i].getAbsolutePath(),buffRazLevelString.toString(),destOutputPath.toString());
				}else {
					continue;
				}
			}else {
				continue;
			}
		}
	}

}
