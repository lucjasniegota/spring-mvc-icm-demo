package bdd;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:target/cucumber-report.json"}, features={"src/test/resources/bdd/createincident.feature"})
public class RunCukesTest {
}