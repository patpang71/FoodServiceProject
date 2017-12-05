package main;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class RESTMain {

	// REST Service
	public static final String REST_URL = "https://localhost:8443/FoodRestServiceApacheSSL/rest/foods";
	public static final int OK_STATUS = Response.Status.OK.getStatusCode();
	
	public static final String USERNAME = "tuser";
	public static final String USER_PASSWORD = "tuser";
	
	static {
		// for localhost testing only
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				return hostname.equals("localhost");
			}
		});
	}
	public static void main(String[] args) {
		// authentication setup
		HttpAuthenticationFeature basicAuth = HttpAuthenticationFeature.basic(USERNAME, USER_PASSWORD);
		Response response;
		
		// create a client
		//Client client = ClientBuilder.newBuilder().build();
		Client client = ClientBuilder.newBuilder().register(basicAuth).build();
		
		// call the service and get all foods
		response = client.target(REST_URL).request(MediaType.APPLICATION_JSON).get();
		printResponse(response);
		System.out.println();
		
		// call the delete 
		response = client.target(REST_URL).path("onion").request().delete();
		printResponse(response);
		System.out.println();
	}
	
	public static void printResponse(Response response) {
		StatusType status = response.getStatusInfo();
		int statusCode = status.getStatusCode();
		if (statusCode == OK_STATUS) {
			System.out.println(response.readEntity(String.class));
		}
		else {
			System.out.printf("Service return status: \"%d %s\"\n", 
					statusCode, status.getReasonPhrase());
		}
	}

}
