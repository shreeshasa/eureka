package io.github.shreeshasa.selenium.driver;

import io.github.shreeshasa.config.BrowserProperties;
import org.openqa.selenium.WebDriver;

/**
 * Web drivers setup.
 *
 * @author shreeshasa
 */
public interface DriverSetup {

  WebDriver getWebDriver(BrowserProperties browserProperties);
}
