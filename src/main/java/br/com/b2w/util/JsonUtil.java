package br.com.b2w.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.b2w.model.PlanetsJsonToObject;

public class JsonUtil {
	
	public static PlanetsJsonToObject JsonToObjectPlanets(String planeta) throws IOException {

		HttpGet httpGet = new HttpGet(URL.JsonPlanetas+planeta);

		// --- texto json --- //
		String json = getRequest(httpGet).toString();

		// --- texto json --- //
		json = getRequest(httpGet).toString();

		int begin = json.indexOf("[");
		int end = json.lastIndexOf("]");

		String filtrada = json.substring(begin + 1, end);

		// --- transformando em Objeto Java --- //
		Gson gson = new Gson(); // conversor
		PlanetsJsonToObject objPlanets = gson.fromJson(filtrada, PlanetsJsonToObject.class);


		return objPlanets;

	}

	
	public static JsonObject getRequest(HttpGet getRequest) throws IOException {

		HttpClient httpClient = HttpClientBuilder.create().build();
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}

		JsonObject jsonObject = deserialize(stringBuilder.toString());
		bufferedReader.close();

		return jsonObject;
	}

	public static JsonObject deserialize(String json) {
		Gson gson = new Gson();
		JsonObject jsonClass = gson.fromJson(json, JsonObject.class);
		return jsonClass;
	}
	

}
