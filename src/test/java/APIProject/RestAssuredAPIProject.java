package APIProject;
import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import org.json.simple.JSONObject;
	import org.testng.Assert;
	import org.testng.annotations.Test;
	import io.restassured.RestAssured;
	import io.restassured.response.Response;
	import static io.restassured.RestAssured.*;
	import org.apache.commons.io.IOUtils;
	public class RestAssuredAPIProject 
	{
	public class day1 {
	@Test
	public void SwggerMethod(){
		System.out.println("swagger project");
	}
	@Test(dependsOnMethods="postmethodwithescapechar")
	public void getMethod() {
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given()
		        .get("/user/uname").
		then()
		         .statusCode(200).log().all();
					
	}
	@Test(priority=1)
	public void postmethodwithescapechar(){
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		String body="{\"username\":\"uname\",\"firstName\":\"kiran\",\"lastName\":\"Bhor\",\"email\":\"abc@gmail.com\",\"password\":\"skkb\",\"phone\":\"6576576565\",\"userStatus\":0}";
		Response resp=given()
				.header("content-type","application/json").body(body).
		   when()
				.post("/user");
		System.out.println(resp.asString());
		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertEquals( resp.jsonPath().getInt("code"),200);
		
	}
	@Test
	public void postmethodwithJsontype() throws IOException {
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+"\\Data\\post.json"));
		Response resp=given()
				.body(IOUtils.toString(file)).header("content-type","application/json").
		when()
		        .post("/user");
		Assert.assertEquals( resp.getStatusCode(),200);
		
	}
	@Test(dependsOnMethods="getMethod")
	public void putMethodWithescaperchar() {
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		String body="{\"username\":\"uname78\",\"firstName\":\"kiran\",\"lastName\":\"Bhor\",\"email\":\"abc@gmail.com\",\"password\":\"skkb\",\"phone\":\"6576576565\",\"userStatus\":0}";
		given()
				.header("content-type","application/json").body(body).
		when()
		        .put("/user/kiran").
		        then()
		        .statusCode(200).log().all();
			
		
	}@Test
	public void putmethodWithJsontype() throws IOException {
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+"\\Data\\put.json"));
		Response resp=given()
				.body(IOUtils.toString(file)).header("content-type","application/json").
		when()
		        .put("/user/KK");
		Assert.assertEquals( resp.getStatusCode(),200);


	}
	@Test()
	public void deleteMethod200() {
		RestAssured.baseURI="https://petstore.swagger.io/v2";
	    given()
	           .delete("/user/uname78").
	    then()
	          .statusCode(200).log().all().extract().response();
	}
	@Test
	public void getMethod404() {
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given()
		      .get("/user/SEen").
		then()
		      .statusCode(404).log().all().extract().response();
	}
	@Test
	public void deleteMethod404() {
		RestAssured.baseURI="https://petstore.swagger.io/v2";
	  given()
	         .delete("/user/seeea").
	  then()
	         .statusCode(404).log().all().extract().response();
	}
	}
	}
