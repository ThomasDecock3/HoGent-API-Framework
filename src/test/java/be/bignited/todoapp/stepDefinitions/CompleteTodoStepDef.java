package be.bignited.todoapp.stepDefinitions;

import dto.TodoDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class CompleteTodoStepDef extends BaseFunctions {

    private int todoId;
    private final Logger logger = Logger.getLogger(CompleteTodoStepDef.class.getName());
    @When("the user wants to set todo as complete")
    public void theUserWantsToSetTodoAsComplete() {
        request.basePath("todo");
    }

    @Given("todo is not complete")
    public void todoIsNotComplete() {
        response = request.when().get("incomplete");
        result = response.body().as(TodoDto[].class);
        if (result.length > 0)
            todoId = result[0].todoId();
    }

    @When("the user makes todo complete")
    public void theUserMakesTodoComplete() {
        if (todoId != 0)
            response = request.when().put("/complete/{todoId}", todoId);
        else
            logger.warning("All todo's are complete");
    }

    @Then("todo will set as complete")
    public void todoWillSetAsComplete() {
        if (result.length > 0) {
            response.then().statusCode(equalTo(200));
            response.then().body("complete", Matchers.is(true));
        }
        else {
            logger.warning("All todo's are complete");
        }
    }

    @Given("todo is complete")
    public void todoIsComplete() {
        response = request.when().get();
        result = response.body().as(TodoDto[].class);
        Arrays.stream(result)
                .filter(TodoDto::complete)
                .findFirst()
                .ifPresent(todoDto -> todoId = todoDto.todoId());
    }

    @Then("the user get an error message telling that todo is complete")
    public void theUserGetAnErrorMessageTellingThatTodoIsComplete() {
        if (result.length > 0) {
            response.then().statusCode(equalTo(409));
            response.then().body("msg", containsString("already completed"));
        }
        else
            logger.warning("All todo's are incomplete");
    }

    @Then("the user get an error message telling that todo not found")
    public void theUserGetAnErrorMessageTellingThatTodoNotFound() {
        if (result.length > 0) {
            response.then().statusCode(equalTo(404));
            response.then().body("msg", containsString("not found"));
        }
        else
            logger.warning("All todo's are incomplete");
    }

    @Given("todo not exist")
    public void todoNotExist() {
        response = request.when().get();
        result = response.body().as(TodoDto[].class);
        Arrays.stream(result)
                .max(Comparator.comparing(TodoDto::todoId))
                .ifPresent(todoDto -> todoId = todoDto.todoId() + 1);
    }
}
