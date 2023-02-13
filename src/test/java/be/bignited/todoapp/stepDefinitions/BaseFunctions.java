package be.bignited.todoapp.stepDefinitions;

import dto.TodoDto;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class BaseFunctions {
    private final Logger logger = Logger.getLogger(BaseFunctions.class.getName());
    protected static RequestSpecification request;
    protected static Response response;
    protected static TodoDto[] result;

    protected static JsonObject todo;

    public String getBaseEndpoint() {
        Properties prop = new Properties();
        try (InputStream input = BaseFunctions.class.getResourceAsStream("/config.properties")) {
            prop.load(input);
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            logger.severe("Property can not be found: " + e.getMessage());
        }
        return prop.getProperty("BASE_URL");
    }
}
