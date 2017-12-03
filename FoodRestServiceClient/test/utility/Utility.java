package utility;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

public class Utility {
	public static final int OK_STATUS = Response.Status.OK.getStatusCode();
	public static final int NOT_FOUND_STATUS = Response.Status.NOT_FOUND.getStatusCode();
	public static final int CREATED_STATUS = Response.Status.CREATED.getStatusCode();
	public static final int NOT_MODIFIED_STATUS = Response.Status.NOT_MODIFIED.getStatusCode();
	public static final int NO_CONTENT_STATUS = Response.Status.NO_CONTENT.getStatusCode();
	
	public static int getStatusCode(Response response) {
		StatusType status = response.getStatusInfo();
		return status.getStatusCode();
	}
	
	public static void printResponse(Response response) {
		System.out.println("------------ Status of Output ---------------");
		
		StatusType status = response.getStatusInfo();
		int statusCode = status.getStatusCode();
		
		System.out.printf("Service return status: \"%d %s\"\n", 
				statusCode, status.getReasonPhrase());
		
		if (statusCode == OK_STATUS) {
			System.out.println(response.readEntity(String.class));
		}
		else {
			System.out.println("[No Output]");
		}
		System.out.println("---------------- End of Output ----------------");
		System.out.println();
	}
}
