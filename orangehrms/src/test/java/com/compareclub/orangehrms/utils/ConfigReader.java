package com.compareclub.orangehrms.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties properties;
	
	public static void loadTheProperties() throws IOException{
		properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
		properties.load(fileInputStream);
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
}
