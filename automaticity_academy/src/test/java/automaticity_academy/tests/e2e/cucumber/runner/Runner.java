package automaticity_academy.tests.e2e.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"src/test/java/automaticity_academy/tests/e2e/cucumber/features/"},
    monochrome = true,
    plugin = {"html:target/cucumber.html"},
    glue = {"automaticity_academy.tests.e2e.cucumber.stepDefinitions", "automaticity_academy.tests.e2e.cucumber.hooks"}
)

public class Runner extends AbstractTestNGCucumberTests {

}
