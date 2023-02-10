package be.bignited.todoapp.stepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class CommonStepDef extends BaseFunctions{

    @Given("the user navigate to home page")
    public void theUserNavigateToHomePage() {
        RestAssured.baseURI = getBaseEndpoint();
        request = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON);
    }
}
