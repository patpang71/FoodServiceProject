package main;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.json.Json;
import javax.json.stream.JsonParser;

public class JSONMain {
	
	public static final String JSON_FILE_NAME = "rate-exchange.json";
	
	/**
	 * Parse and display the JSON file
	 * @param args
	 */
	public static void main (String[] args) throws FileNotFoundException {
		JsonParser parser = Json.createParser(new FileReader(JSON_FILE_NAME));
		
		// parse the file
		while (parser.hasNext()) {
			JsonParser.Event event = parser.next();
			if (event.equals(JsonParser.Event.KEY_NAME)) {
				String key = parser.getString();
				parser.next();
				String value = parser.getString();
				System.out.printf(">> %s: %s\n", key, value);
			}
		}
	}
}
