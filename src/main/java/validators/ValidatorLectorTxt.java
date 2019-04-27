package validators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ValidatorLectorTxt {

	@SuppressWarnings({ "unused", "resource" })
	public boolean isValidRoute(String routeTxt) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(routeTxt));
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}
