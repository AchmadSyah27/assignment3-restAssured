package restassured.assignment.utils;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class TestUtils {

	public static void hardWait(int timeInSeconds) {

		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static LinkedTreeMap<String, Object> convertJsonToMap(String jsonString) {

		Gson gson = new Gson();
		LinkedTreeMap<String, Object> jsonMap = new LinkedTreeMap<String, Object>();

		jsonMap = gson.fromJson(jsonString, jsonMap.getClass());

		return jsonMap;
	}
}
