package be.bignited.todoapp.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/be/bignited/todoapp/features",
        glue = {"be.bignited.todoapp.stepDefinitions"},
        monochrome = true,
        tags = "not @ignore"
)
public class TestRunner {
}
