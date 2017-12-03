package main;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 * Call the RESTful web service
 * @author kaitaipang
 *
 */
public class RESTMain {
	
	public static final String REST_URL = "http://localhost:8080/FoodRestServiceProject/rs/foods";
	public static final int OK_STATUS = Response.Status.OK.getStatusCode();
	
	public static final String USERNAME = "tadmin";
	public static final String USER_PASSWORD = "tadmin";

	public static void main(String[] args) {
		HttpAuthenticationFeature basicAuth = HttpAuthenticationFeature.basic(USERNAME, USER_PASSWORD);
		Client client = ClientBuilder.newBuilder().register(basicAuth).build();
		
		// call the service and get the response object
		Response response = client.target(REST_URL).request(MediaType.APPLICATION_JSON).get();
		
		// process the response object
		StatusType status = response.getStatusInfo();
		int statusCode = status.getStatusCode();
		if (statusCode == OK_STATUS) {
			System.out.println(response.readEntity(String.class));
		}
		else {
			System.out.printf("Service returned status: \"%d %s \"\n", 
					statusCode, status.getReasonPhrase());
		}
	}

}
