package automaticity_academy.tests.e2e.features;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions(
    features = {"src/test/java/automaticity_academy/tests/e2e/features/cucumber.feature"},
    dryRun = false,
    snippets = SnippetType.CAMELCASE,
    monochrome = true,
    plugin = {"html:target/cucumber.html"},
    glue = {"e2e.steps"}
)

public class Runner extends AbstractTestNGCucumberTests {

}
