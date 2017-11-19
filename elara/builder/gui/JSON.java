/**
 * Author: Brandon
 * Date Created: 12 Oct. 2017
 * File : ConfigReader.java
 */
package elara.builder.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * ConfigReader
 *
 * Description: Provides methods for reading and writing JSON 
 * configuration files.
 */
public class JSON
{
	public static JSONObject read(File file) throws ParseException, FileNotFoundException, IOException 
	{
		if (file.exists()) {
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse(new FileReader(file));
		}
		
		return null;
	}
	
	public static JSONObject read(String file) throws ParseException, FileNotFoundException, IOException
	{
		if (new File(file).exists()) {
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse(new FileReader(file));
		}
		
		return null;
	}

	/**
	 * Write out the json, prettified.
	 * @param string
	 * @throws IOException 
	 */
	public static void write(JSONObject object, String path) throws IOException
	{
		Files.write(new File(path).toPath(), makePretty(object.toJSONString()).getBytes());
	}
	
	/**
	 * Take in some json and make it pretty.
	 * @param json
	 * @return
	 */
	public static String makePretty(String json)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		return gson.toJson(element);
	}
}
