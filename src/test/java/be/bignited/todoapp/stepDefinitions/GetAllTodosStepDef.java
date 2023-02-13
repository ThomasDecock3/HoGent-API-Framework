package be.bignited.todoapp.stepDefinitions;

import dto.TodoDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

public class GetAllTodosStepDef extends BaseFunctions {
    @When("the user gets todo's")
    public void theUserGetsTodoS() {
        response = request.when().get();
    }

    @Then("the user sees all todo's")
    public void theUserSeesAllTodoS() {
        response.then().body(not(empty()));
    }

    @When("the user gets incomplete todo's")
    public void theUserGetsIncompleteTodoS() {
        response = request.when().get("incomplete");
    }

    @Then("the user sees a list of todo's")
    public void theUserSeesAListOfTodoS() {
        result = response.body().as(TodoDto[].class);
    }

    @And("all todo's are incomplete")
    public void allTodoSAreIncomplete() {
        for (TodoDto todoDto : result) {
            assertThat(todoDto.complete(), is(false));
        }
    }
}
