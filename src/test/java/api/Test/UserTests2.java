package api.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payloads.UserPOJO;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	UserPOJO userPayloads;
	
	public Logger logger;
	
	@BeforeTest
	public void setupData() {
		faker=new Faker();
		userPayloads=new UserPOJO();
		
		userPayloads.setId(faker.idNumber().hashCode());
		userPayloads.setUsername(faker.name().username());
		userPayloads.setFirstName(faker.name().firstName());
		userPayloads.setLastName(faker.name().lastName());
		userPayloads.setEmail(faker.internet().safeEmailAddress());
		userPayloads.setPassword(faker.internet().password(5, 10));
		userPayloads.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testPostUser(){
		
		logger.info("**********Creating User*************");
		Response response=UserEndPoints2.createUser(userPayloads);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("**********Reading User*************");

		
		Response response=UserEndPoints2.readUser(this.userPayloads.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		logger.info("**********Upadting User*************");

		userPayloads.setFirstName(faker.name().firstName());
		userPayloads.setLastName(faker.name().lastName());
		userPayloads.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUser(this.userPayloads.getUsername(),userPayloads);
		response.then().log().body();
		//checking data afetr update
		
		Response responseAfterUpdate=UserEndPoints2.readUser(this.userPayloads.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	}
	
	@Test(priority = 4)
	public void deleteUserByName() {
		logger.info("**********Deleting User*************");

		Response response=UserEndPoints2.deleteUser(this.userPayloads.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
}
