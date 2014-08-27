package go.euro.test;


/**
 * @author Antonio Nobre
 * 
 * This class is have method that can helping with manipulation of data that arrives in JSON Object.
 *
 */
public class HandleTextJSON {
	
	private static String id;
	private static String name;
	private static String type;
	private static String latitude;
	private static String longitude;
	private static boolean idBoolean;
	private static boolean nameBoolean;
	private static boolean typeBoolean;
	private static boolean latitudeBoolean;
	private static boolean longitudeBoolean;
	
	/**
	 * This method pretends transform the data that comes from object in JSON Object.
	 * 
	 * @param line Line of JSON Object
	 * @return
	 */
	public static String addAspas(String line) {
		
		int twoPoints = line.indexOf(":");
		String getkey = line.substring(0,twoPoints);
		String getValue = line.substring(twoPoints+1, line.length()-1);
		
		if (line.substring(twoPoints+1).contains("\"") || line.substring(twoPoints+1).contains("{"))
			return "\"" + getkey + "\"" + ": " + line.substring(twoPoints+1);
		else if (line.substring(twoPoints+1).contains(","))
			return "\"" + getkey + "\"" + ": " + "\"" + getValue + "\"" + ",";
		else 
			return "\"" + getkey + "\"" + ": " + "\"" + line.substring(twoPoints+1) + "\"";
	}
	
	
	/**
	 * This method creates the data for insert in CSV file in right order.
	 * 
	 * @param obj Type of object
	 * @param jsonArray Value of object
	 * @param saveValuesCSV The string buffer that saves the values for insert into file CSV.
	 */
	public static void fillStringBuffer (Object obj, Object jsonArray, StringBuffer saveValuesCSV) {
		
		if (obj.toString().equalsIgnoreCase("_id")) {
			id = (String) jsonArray;
			idBoolean = true;
        }
        if (obj.toString().equalsIgnoreCase("name")) {
        	name = (String) jsonArray;
        	nameBoolean = true;
        }
        if (obj.toString().equalsIgnoreCase("type")) {
        	type = (String) jsonArray;
        	typeBoolean = true;
        }
        if (obj.toString().equalsIgnoreCase("latitude")) {
        	latitude = (String) jsonArray;
        	latitudeBoolean = true;
        }
        if (obj.toString().equalsIgnoreCase("longitude")) {
        	longitude = (String) jsonArray;
        	longitudeBoolean = true;
        }
        
        if (idBoolean && nameBoolean && typeBoolean && latitudeBoolean && longitudeBoolean) {
			saveValuesCSV.append(id);
        	saveValuesCSV.append(";");
        	saveValuesCSV.append(name);
        	saveValuesCSV.append(";");
        	saveValuesCSV.append(type);
        	saveValuesCSV.append(";");
        	saveValuesCSV.append(latitude);
        	saveValuesCSV.append(";");
        	saveValuesCSV.append(longitude);
        	saveValuesCSV.append("\n");
        	
        	idBoolean = false;
        	nameBoolean = false;
        	typeBoolean = false;
        	latitudeBoolean = false;
        	longitudeBoolean = false;
        }
		
	}
	

}
