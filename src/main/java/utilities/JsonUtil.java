package utilities;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	
	private static ObjectMapper mapper;   //org.codehaus.jackson import ediyoruz
	
	static {      //Static blocklar variablelere deger atamak icin kullanilir.Herseyden önce calisir
		mapper= new ObjectMapper();
	}
	
	//Java objectini Json formatina ceviren method==>Serialization
	//convert=>cevirme
	public static String convertJavaToJson(Object object) {
		String jsonResult="";
		
		try {
			jsonResult=mapper.writeValueAsString(object);//Surround with try/catch ile olustur
		} catch (JsonGenerationException e) {
			System.out.println("Java Objesini Json'a cevirirken exception olustu");
		} catch (JsonMappingException e) {
			System.out.println("Java Objesini Json'a cevirirken exception olustu");
		} catch (IOException e) {
			System.out.println("Java Objesini Json'a cevirirken exception olustu");
		}   
		
		return jsonResult;
	}
	
	// Json formatindaki datayi Java objectine ceviren method==>De-Serialization
	
	public static <T> T convertJsonToJava(String json,Class<T> cls) { 
		//generic method return typeni kullanirken belirliyecegiz onun icin T yazdik.Map,List,Pojo vs
	
		T javaResult=null;
		
	try {
		javaResult=	mapper.readValue(json, cls);
	} catch (JsonParseException e) {
	System.out.println("JsonI Java'ya cevirirken Exception olustu"+e.getMessage());
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return javaResult;
	}
	
	
	
	
	
	
	
	
	
	

}
