package com.compareclub.orangehrms.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;

public class JsonDataReader {

public static List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read json to string

		String  jsonContent=  FileUtils.readFileToString(new File(filePath), "UTF-8");
		
		//string to hashmap jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		return data;
		}
        }

