package domain;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import utility.Utility;

public class RESTClientTest {

	@Test
	public void getAllTest() {
		RESTClient client = new RESTClient();
		Response response = client.getAll(MediaType.APPLICATION_XML);
		Utility.printResponse(response);
		
		// test the response
		assertTrue(Utility.getStatusCode(response) == Utility.OK_STATUS);
	}
	
	public static final String FOOD_NAME = "soda";
	
	@Test
	public void getTest() {
		RESTClient client = new RESTClient();
		Response response = client.get(FOOD_NAME, MediaType.APPLICATION_JSON);
		Utility.printResponse(response);
		
		int responseCode = Utility.getStatusCode(response);
		boolean notFound = (responseCode == Utility.NOT_FOUND_STATUS);
		boolean ok = (responseCode == Utility.OK_STATUS);
		assertTrue(ok || notFound);
	}
	
	@Test
	public void postTest() {
		RESTClient client = new RESTClient();
		Response response = client.post("turnip", 30, "3 slices");
		
		// test the response
		int responseCode = Utility.getStatusCode(response);
		boolean notModified = (responseCode == Utility.NOT_MODIFIED_STATUS);
		boolean created = (responseCode == Utility.CREATED_STATUS);
		assertTrue(notModified || created);
	}
	
	@Test
	public void putTest() {
		RESTClient client = new RESTClient();
		Response response = client.put("turnip", 80, "4 slices");
		assertTrue(Utility.getStatusCode(response) == Utility.OK_STATUS);
	}
}
