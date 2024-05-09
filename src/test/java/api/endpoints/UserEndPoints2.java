package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints2 {

	static ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //Load properties file
		return routes;
	}
	
	public static Response createUser(UserPOJO Payload){
		
		String post_url=getURL().getString("post_url");
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(Payload)
		
		.when()
			.post(Routes.post_url);
	return response;		
	}
	
	public static Response readUser(String username){
		
		String get_url=getURL().getString("get_url");

		Response response=given()
			.pathParam("username", username)
			
			.when()
				.get(get_url);
		return response;		
		}
	
public static Response updateUser(String username,UserPOJO Payload){
		
	String update_url=getURL().getString("update_url");

		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.pathParam("username", username)
			
			.when()
				.put(update_url);
		return response;		
		}

public static Response deleteUser(String username){
	
	String delete_url=getURL().getString("delete_url");

	Response response=given()
		.pathParam("username", username)
		
		.when()
			.delete(delete_url);
	return response;		
	}

}
