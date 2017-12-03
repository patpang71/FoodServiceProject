package domain;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class RESTClient {
	
	// REST Service Constants
	private static final String REST_URL = "http://localhost/FoodRestService/rs";
	private static final int SERVER_PORT = 8080;
	private static final String PATH = "foods";
	
	private WebTarget target;
	
	public RESTClient() {
		URI uri;
		Client client;
		
		// create the URL
		uri = UriBuilder.fromUri(REST_URL).port(SERVER_PORT).build();
		
		// create a client
		
		client = ClientBuilder.newClient();
		
		// create a target
		target = client.target(uri).path(PATH);
	}
	
	public Response getAll(String mediaType) {
		return target.request(mediaType).get();
	}
	
	public Response get(String food, String mediaType) {
		return target.path(food).request(mediaType).get();
	}
	
	public Response post(String foodName, int calories, String servingSize) {
		Food food = new Food(foodName, calories, servingSize);
		Entity<Food> entity = Entity.entity(food, MediaType.APPLICATION_XML);
		return target.request().post(entity);
	}
	
	public Response put(String foodName, int calories, String servingSize) {
		Food food = new Food(foodName, calories, servingSize);
		Entity<Food> entity = Entity.entity(food,  MediaType.APPLICATION_XML);
		return target.request().put(entity);
	}
	
	public Response delete(String foodName) {
		return target.path(foodName).request().delete();
	}
}
