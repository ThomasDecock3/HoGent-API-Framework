package be.bignited.todoapp.stepDefinitions;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.equalTo;

public class EditTodoStepDef extends BaseFunctions {
    @When("the user wants to edit todo")
    public void theUserWantsToEditTodo() {
        request.basePath("todo");
    }

    @Given("the user change todo to {string}")
    public void theUserChangeTodoTo(String newDescription) {
        todo = new JsonObject();
        todo.add("description", newDescription);
        request.body(todo.toString());
    }

    @When("the user edit todo number {string}")
    public void theUserEditTodo(String todoId) {
        response = request.put(todoId);
    }

    @Then("the todo will be changed")
    public void theTodoWillBeChanged() {
        response.body().prettyPrint();
        response.then().statusCode(equalTo(200));
    }

    @And("the description will be {string}")
    public void theDescriptionWillBe(String responseDescription) {
        response.then().body("description", equalTo(responseDescription));
    }

    @Then("the user get an error message telling that todo nr {string} not found")
    public void theUserGetAnErrorMessageTellingThatTodoNrNotFound(String todoId) {
        response.then().statusCode(equalTo(404));
        response.then().body("msg", equalTo("Todo with id " + todoId + " not found"));
    }

    @Then("the user get a bedRequest response")
    public void theUserGetABedRequestResponse() {
        response.then().statusCode(equalTo(400));
    }
}
