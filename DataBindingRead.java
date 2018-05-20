package my.json;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import json.domain.SurreyStudent;

public class DataBindingRead {
	
	public static void main(String[] args) {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		/*String surreyStudentJSON = "{" + 
					"\"id\" : 1," +
					"\"name\" : \"Meher\"," +
					"\"lastname\" : \"Khan\"" +
		    "}";
		
		
		try {
			SurreyStudent surreyStudent = objectMapper.readValue(surreyStudentJSON, SurreyStudent.class);
			System.out.println("id:" + surreyStudent.getId());
			System.out.println("name:" + surreyStudent.getName());
			System.out.println("lastname:" + surreyStudent.getLastname());
	
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
	
		
		ClassLoader classLoader = DataBindingRead.class.getClassLoader();
		
		try {
			Map<String, Object> mapJSON = objectMapper.readValue(new File(classLoader.getResource("mapJSON.json").getFile()),
						new TypeReference<Map<String,Object>>() {
						}				
					);
			
			System.out.println(mapJSON);
			
			for (Map.Entry<String, Object> entry : mapJSON.entrySet()) {
				System.out.println("key:" + entry.getKey() + " value: " + entry.getValue());				
			} 
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
