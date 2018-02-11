package config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONLoader implements Config {

	private JSONObject json;

	private String filename;

	public JSONLoader(String filename) {
		this.filename = filename;
	}

	@Override
	public void load() {

		JSONParser parser = new JSONParser();
		json = null;
		try {
			json = (JSONObject) parser.parse(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getSuspects() {
		JSONArray array = (JSONArray) ((JSONObject) json.get("game")).get("suspects");
		return new LinkedList<String>(array);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRooms() {
		JSONArray array = (JSONArray) ((JSONObject) json.get("game")).get("rooms");
		return new LinkedList<String>(array);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getWeapons() {
		JSONArray array = (JSONArray) ((JSONObject) json.get("game")).get("weapons");
		return new LinkedList<String>(array);
	}

	public static void main(String[] args) {
		JSONLoader loader = new JSONLoader("config/config.json");
		loader.load();

		System.out.println(loader.getSuspects());
		System.out.println(loader.getRooms());
		System.out.println(loader.getWeapons());
	}

}
