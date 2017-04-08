package lab.heisenbug.sandbox.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by parker on 08/04/2017.
 */
public class StepDefinitions {

    @Given("^logged in$")
    public void logged_in() throws Throwable {

    }

    @Given("^logged out$")
    public void logged_out() throws Throwable {
        throw new Exception();
    }

    @When("^an employee is created$")
    public void an_employee_is_created() throws Throwable {

    }

    @Then("^the employee should be found in database$")
    public void the_employee_should_be_found_in_database() throws Throwable {

    }

}
