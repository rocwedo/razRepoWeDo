package com.wedo.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.Element;

public class WeDoBlocSSMLFileFactory {

	private static WeDoBlocSSMLFileFactory wedoSSMLFileFactoryInit = null;
	private Document document;

	private Element speakNode;

	private ArrayList<WeDoBookInfo> wedoBookList = new ArrayList<WeDoBookInfo>();

	private WeDoBlocSSMLFileFactory() {

	}

	public static WeDoBlocSSMLFileFactory initWeDoBlocSSMLFileFactory() {
		if (null == wedoSSMLFileFactoryInit) {
			wedoSSMLFileFactoryInit = new WeDoBlocSSMLFileFactory();
		}
		return wedoSSMLFileFactoryInit;
	}

	public WeDoBlocSSMLFileFactory getWeDoBlocSSMLFileFactoryInit() {

		return initWeDoBlocSSMLFileFactory();
	}

	public void loadSSMLTempl(String pathFile) throws DocumentException {
		SAXReader reader = new SAXReader();
		document = reader.read(new File(pathFile));
		speakNode = document.getRootElement();
	}

	public void splitResFile(String fileName, String razLevel) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			WeDoBookInfo tempWeDoBookInfo = new WeDoBookInfo();
			while (line != null && !line.isEmpty()) {
				line.trim();
				String item[] = line.split("\t");

				if (item.length == 1) {
					tempWeDoBookInfo = new WeDoBookInfo(item[0].trim(), razLevel);
					wedoBookList.add(tempWeDoBookInfo);
				} else {
					if (0 == item.length) {
						line = reader.readLine();
						continue;
					}

					WeDoWordInfo tmpWeDoWordInfo = new WeDoWordInfo();
					tmpWeDoWordInfo.setEnWord(item[0].trim());
					tmpWeDoWordInfo.setPartOfSpeech(item[1].trim());
					// System.out.println(item[1]);
					tmpWeDoWordInfo.setCnWord(item[2].trim());
					tempWeDoBookInfo.addWoDoWords(tmpWeDoWordInfo);
				}
				line = reader.readLine();
			}
			reader.close();

		} catch (Exception e) {
			System.out.println(fileName);
			e.printStackTrace();

		}
	}

	public void toVoiceSSMLFile() {
		try {
			Iterator<WeDoBookInfo> it = wedoBookList.iterator();
			for (; it.hasNext();) {
				loadSSMLTempl("res\\templ.xml");
				WeDoBookInfo tmpWeDoBookInfo = it.next();
				tmpWeDoBookInfo.toVoiceSSMLFile();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void toVoiceSSMLFile(String outputFoldPath) {
		try {
			Iterator<WeDoBookInfo> it = wedoBookList.iterator();
			for (; it.hasNext();) {
				loadSSMLTempl("res\\templ.xml");
				WeDoBookInfo tmpWeDoBookInfo = it.next();
				tmpWeDoBookInfo.toVoiceSSMLFile(outputFoldPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearVoiceSSMLFileList() {
		wedoBookList.clear();
	}

}
