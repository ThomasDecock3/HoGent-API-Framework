package be.bignited.todoapp.stepDefinitions;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.equalTo;

public class AddTodoStepDef extends BaseFunctions {

    @When("the user wants to add todo")
    public void theUserWantsToAddTodo() {
        request.basePath("todo");
    }

    @Given("the user gives no description")
    public void theUserGivesNoDescription() {
        todo = new JsonObject();
        request.body(todo);
    }

    @When("the user add todo")
    public void theUserAddTodo() {
        response = request.when().post();
    }

    @Then("todo will not be added")
    public void todoWillNotBeAdded() {
        response.then().statusCode(equalTo(400));
    }

    @And("the user get the error message {string}")
    public void theUserGetTheErrorMessage(String message) {
        response.then().body("msg", equalTo(message));
    }

    @Given("the user gives no todo")
    public void theUserGivesNoTodo() {
        todo = null;
    }

    @Given("the user gives {string} as description")
    public void theUserGivesDescriptionAsDescription(String description) {
        todo = new JsonObject();
        todo.add("description", description);
        request.body(todo.toString());
    }

    @Then("todo will be added")
    public void todoWillBeAdded() {
        response.body().prettyPrint();
        response.then().statusCode(equalTo(200));
        response.then().body("description", equalTo(todo.getString("description", "")));
    }
}
