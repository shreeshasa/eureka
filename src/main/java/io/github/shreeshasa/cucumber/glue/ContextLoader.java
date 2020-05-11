package io.github.shreeshasa.cucumber.glue;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.shreeshasa.AutomationApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This class loads the spring context for cucumber execution.
 * This package is included as glue in cucumber options.
 *
 * @author shreeshasa
 */
@CucumberContextConfiguration
@RunWith (SpringRunner.class)
@SpringBootTest (classes = AutomationApplication.class)
public class ContextLoader {

  @Before
  public void setup() {
  }
}
