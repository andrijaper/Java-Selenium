package automaticity_academy.tests.e2e.features;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/automaticity_academy/tests/e2e/features/"},
        monochrome = true,
        plugin = {"html:target/cucumber.html", "pretty"},
        glue = "automaticity_academy.tests.e2e.stepDefinitions"
)

public class Runner extends AbstractTestNGCucumberTests {

}
