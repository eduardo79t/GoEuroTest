package go.euro.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Antonio Nobre
 *
 *This class is the main for running parsing of JSON Objects and improve the JSON Objects for of good handling process.
 *
 */
public class GoEuroTest {

	private static final String filePath = "C:\\Users\\user\\Downloads\\STRING.json";
	private static final String filePathCSV = "STRING.csv";
	
	public static void main(String[] args) {

		JSONObject jsonObject = null;
		BufferedReader in = null;

		try {

			in = new BufferedReader(new FileReader(args[0]));

			StringBuffer stringReader = new StringBuffer();

			int i = 0;
			while (in.ready()) {
				String line = in.readLine();

				if (i == 0 && line.trim().endsWith("[") || i == 0 && line.trim().endsWith("]")) {
					stringReader.append("{" + "\"" + "STRING" + "\"" + ": ");
					stringReader.append("\n");
				}

				if (line.contains(":")) {
					stringReader.append(HandleTextJSON.addAspas(line));
					stringReader.append("\n");
				} else {

					stringReader.append(line);
					stringReader.append("\n");
				}
				++i;
			}
			stringReader.append("}");

			FileWriter fw = new FileWriter(args[0]);
			fw.write(stringReader.toString());
			fw.close();

			JSONParser jsonParser = new JSONParser();

			File file = new File(filePath);

			Object object = jsonParser.parse(new FileReader(file));

			jsonObject = (JSONObject) object;

			StringBuffer saveValuesCSV = new StringBuffer();
			saveValuesCSV.append("_ID");
			saveValuesCSV.append(";");
			saveValuesCSV.append("NAME");
			saveValuesCSV.append(";");
			saveValuesCSV.append("TYPE");
			saveValuesCSV.append(";");
			saveValuesCSV.append("LATITUDE");
			saveValuesCSV.append(";");
			saveValuesCSV.append("LONGITUDE");
			saveValuesCSV.append("\n");

			Jsonparser.parseJson(jsonObject, saveValuesCSV);

			FileWriter fwCSV = new FileWriter(filePathCSV);
			fwCSV.write(saveValuesCSV.toString());
			fwCSV.close();

		} catch (IOException e1) {
			System.out.println("Error during reading/writing");

		} catch (ParseException e1) {
			System.out.println("Error during parsing the JSON Object");

		}finally {

			try {
				in.close();
			} catch (IOException e) {
				System.out.println("Error during reading/writing");
			}
		}

	}

}
