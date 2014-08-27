package go.euro.test;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * @author Antonio Nobre
 * 
 * This class have some methods that helpe's with the parsing of data that arrives from JSON format.
 *
 */
public class Jsonparser {
	
	/**
	 * This method parse the JSON Object and saves the data in StringBuffer.
	 * 
	 * @param jsonObject The object JSON.
	 * @param saveValuesCSV The StringBuffer that saves the data from JSON Object. 
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static void parseJson(JSONObject jsonObject,	StringBuffer saveValuesCSV) throws ParseException {

		Set<Object> set = jsonObject.keySet();
		
		Iterator<Object> iterator = set.iterator();
		
		while (iterator.hasNext()) {
			
			Object obj = iterator.next();
			
			if (jsonObject.get(obj) instanceof JSONArray) {
				
				System.out.println("JSON_ARRAY:" + obj.toString());
				
				getArray(jsonObject.get(obj), saveValuesCSV);
				
			} else {
				
				if (jsonObject.get(obj) instanceof JSONObject) {
					
					parseJson((JSONObject) jsonObject.get(obj), saveValuesCSV);
					
				} else {
					
					System.out.println(obj.toString() + "\t" + jsonObject.get(obj));
					
					HandleTextJSON.fillStringBuffer(obj, jsonObject.get(obj), saveValuesCSV);
					
				}
			}
		}
	}
	
	/**
	 * This method verify the length of JSON Object and proceed parsing if value is a JSONObject.
	 * 
	 * @param value Object with value of JSONArray
	 * @param saveValuesCSV StringBuffer that saves the values for insert into CSV file.
	 * @throws ParseException
	 */
	private static void getArray(Object value, StringBuffer saveValuesCSV) throws ParseException {

		JSONArray jsonArr = (JSONArray) value;

		for (int k = 0; k < jsonArr.size(); k++) {

			if (jsonArr.get(k) instanceof JSONObject) {
				
				parseJson((JSONObject) jsonArr.get(k), saveValuesCSV);
				
			} else {
				
				System.out.println(jsonArr.get(k));
				
			}

		}
	}

}
