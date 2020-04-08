package io.github.shreeshasa;

import io.github.shreeshasa.config.BrowserProperties;
import io.github.shreeshasa.cucumber.plugin.TestReportListener;
import io.github.shreeshasa.selenium.driver.WebDrivers;
import io.qameta.allure.util.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Properties;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

/**
 * The Spring Boot application configuration that starts wiring up the application to be executed.
 *
 * @author shreeshasa
 */
@Slf4j
@SpringBootApplication
public class AutomationApplication {

  private static ThreadLocal<WebDriver> WEB_DRIVER = new ThreadLocal<>();

  private final ApplicationContext ctx;
  private final BrowserProperties browserProperties;

  public AutomationApplication(ApplicationContext ctx, BrowserProperties browserProperties) {
    this.ctx = ctx;
    this.browserProperties = browserProperties;
  }

  public static WebDriver getWebDriver() {
    return WEB_DRIVER.get();
  }

  @Bean (destroyMethod = "quit")
  @Scope (SCOPE_CUCUMBER_GLUE)
  public WebDriver webDriver() {
    final WebDriver webDriver = createWebDriver();
    WEB_DRIVER.set(webDriver);
    return webDriver;
  }

  private WebDriver createWebDriver() {
    if (MapUtils.isNotEmpty(browserProperties.getCapabilities())) {
      browserProperties.getCapabilities().put("name", TestReportListener.getTestName());
    }
    switch (browserProperties.getType().toLowerCase()) {
      case "chrome":
        return WebDrivers.CHROME.getWebDriver(browserProperties);
      case "firefox":
        return WebDrivers.FIREFOX.getWebDriver(browserProperties);
      case "ie":
        return WebDrivers.IE.getWebDriver(browserProperties);
      case "edge":
        return WebDrivers.EDGE.getWebDriver(browserProperties);
      case "safari":
        return WebDrivers.SAFARI.getWebDriver(browserProperties);
      case "opera":
        return WebDrivers.OPERA.getWebDriver(browserProperties);
      default:
        throw new IllegalArgumentException(String.format("Browser %s not supported.", browserProperties.getType()));
    }
  }

  @PostConstruct
  public void writeAllureEnvironment() {
    final String path = getAllureResultsPath();
    new File(path).mkdirs();
    final File environmentFile = new File(path, "environment.properties");
    try (OutputStream outputStream = new FileOutputStream(environmentFile)) {
      final Properties properties = new Properties();
      properties.put("spring.profiles.active", StringUtils.join(ctx.getEnvironment().getActiveProfiles(), ","));
      System.getProperties().entrySet().parallelStream()
        .filter(property -> Arrays.asList("threadCount", "cucumber.tags").contains(property.getKey()))
        .forEach(property -> properties.put(property.getKey(), property.getValue()));
      properties.store(outputStream, null);
    } catch (final IOException e) {
      log.warn("error saving environment.properties", e);
    }
  }

  private String getAllureResultsPath() {
    final Properties properties = PropertiesUtils.loadAllureProperties();
    return properties.getProperty("allure.results.directory", "target/allure-results");
  }

  public static void main(String[] args) {
    SpringApplication.run(AutomationApplication.class, args);
  }
}
