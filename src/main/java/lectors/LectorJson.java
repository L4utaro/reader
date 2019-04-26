package lectors;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lectors.interfaces.ILector;
import validators.ValidatorLectorJson;

public class LectorJson implements ILector{
	private String routeCodeJSON;
	private ValidatorLectorJson validatorLectorJson;

	public LectorJson(final String routeCodeJSON) {
		this.routeCodeJSON = routeCodeJSON;
		this.validatorLectorJson = new ValidatorLectorJson();
		isAValidRoute();
	}

	public void isAValidRoute() {
		if (!this.validatorLectorJson.isValidRoute(this.routeCodeJSON)) {
			throw new IllegalArgumentException("This is not a valid route of actions.json");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> getListOfJson(String keyValue) {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(routeCodeJSON));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray leng = (JSONArray) jsonObject.get(keyValue);
			return leng;
		} catch (IOException | ParseException e) {
			throw new IllegalArgumentException("The actions doesn't exits");
		}
	}
	
	public JSONObject getObjectJson(String keyValue) {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(routeCodeJSON));
			JSONObject jsonObject = (JSONObject) obj;
			return (JSONObject) jsonObject.get(keyValue);
		} catch (IOException | ParseException e) {
			throw new IllegalArgumentException("The actions doesn't exits");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getNamesOfArrays() {
		List<String> nameOfFunctions = new ArrayList<>();
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(this.routeCodeJSON));
			JSONObject jsonObject = (JSONObject) obj;
			nameOfFunctions.addAll(jsonObject.keySet());
			if(!this.validatorLectorJson.thereAreValidNamesOfFunctions(nameOfFunctions)) {
				throw new IllegalArgumentException("There are name of functions repited");
			}
			return nameOfFunctions;
		} catch (IOException | ParseException e) {
			throw new IllegalArgumentException("The actions doens't exits");
		}
	}

	public Object getValueFromJSON(final JSONObject objectJSON, final String clave){
		return (objectJSON.get(clave));
	}
}
