package io.github.shreeshasa;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * An abstraction class for a TestNg runner that kicks off cucumber.
 * <br><br>
 * Extend this in your test suite and configure your glue package like:
 *
 * <pre>
 *   <code>
 *     &#64;CucumberOptions(glue = {"${project.groupId}.steps"})
 *     public class AutomationTests extends AbstractAutomationTests {
 *     }
 *   </code>
 * </pre>
 *
 * @author shreeshasa
 */
@CucumberOptions (features = "classpath:features",
  strict = true,
  monochrome = true,
  tags = {"not @ignore and not @skip and not @wip"},
  plugin = {
    "pretty",
    "io.github.shreeshasa.cucumber.plugin.TestReportListener",
    "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm",
  },
  glue = {"io.github.shreeshasa.cucumber.glue"})
public abstract class AbstractAutomationTests extends AbstractTestNGCucumberTests {

  @Override
  @DataProvider (parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
