package api.Test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.payloads.Booking;
import api.payloads.BookingDates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HerokuappUserTest {

			
		@Test (dataProvider="ExcelTestData")
		public void dataDrivenTesting(Map<String, String> testData) throws IOException {

			int totalPrice=Integer.parseInt(testData.get("TotalPrice"));
			
			BookingDates bookingDates=new BookingDates("2024-04-25","2024-04-30");
			
			Booking booking=new Booking(testData.get("Firstname"),testData.get("Lastname"), "breakfast",totalPrice,true,bookingDates);
			
			//Serilization
			ObjectMapper objectMapper=new ObjectMapper();
			String requestBody=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
			
			Response response=
					given()
					
						.contentType(ContentType.JSON)
						.body(requestBody)
						.baseUri("https://restful-booker.herokuapp.com/booking")
					
					.when()
						.post()
					.then()
					.assertThat()
					.statusCode(200)
					.log().all()
				.extract()
				.response();
						
					
			

		}
}
