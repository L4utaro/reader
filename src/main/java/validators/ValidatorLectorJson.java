package validators;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ValidatorLectorJson {

	public boolean isValidRoute(String routeCodeJSON) {
		JSONParser parser = new JSONParser();
		@SuppressWarnings("unused")
		Object obj;
		try {
			obj = parser.parse(new FileReader(routeCodeJSON));
		} catch (IOException | ParseException e) {
			return false;
		}
		return true;
	}

	public boolean thereAreValidNamesOfFunctions(List<String> nameOfFunctions) {
		for (String nameOfFunction : nameOfFunctions) {
			int cont = 0;
			for (int i = 0; i < nameOfFunctions.size(); i++) {
				if (nameOfFunctions.get(i).equals(nameOfFunction)) {
					cont++;
				}
				if (cont >= 2) {
					return false;
				}
			}
		}
		return true;
	}
}
