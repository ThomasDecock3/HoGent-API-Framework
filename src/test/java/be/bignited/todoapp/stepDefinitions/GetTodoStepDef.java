package be.bignited.todoapp.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetTodoStepDef extends BaseFunctions {

    @Given("todo number {int} exist")
    public void todoNumberExist(int todoId) {
        this.todoId = todoId;
    }


    @Then("the user get the correct todo")
    public void theUserGetTheCorrectTodo() {
        response.then().statusCode(equalTo(200));
        response.then().body("todoId", equalTo(todoId));
    }

    @When("the user gets todo number {int}")
    public void theUserGetsTodoNumber(int todoId) {
        response = request.when().get("{todoId}", todoId);
    }

    @Then("the user get an error message telling that todo number {int} not found")
    public void theUserGetAnErrorMessageTellingThatTodoNumberNotFound(int todoId) {
        response.then().statusCode(equalTo(404));
        response.then().body("msg", containsString("not found"));
    }

    @Given("todo number {int} doesn't exist")
    public void todoNumberDoesnTExist(int todoId) {
        this.todoId = todoId;
    }
}
