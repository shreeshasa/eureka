package io.github.shreeshasa.selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.shreeshasa.config.BrowserProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

/**
 * Supported web drivers.
 *
 * @author shreeshasa
 */
public enum WebDrivers implements DriverSetup {

  CHROME {
    @Override
    public WebDriver getWebDriver(BrowserProperties browserProperties) {
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.setHeadless(browserProperties.isHeadless());
      options.merge(new DesiredCapabilities(browserProperties.getCapabilities()));
      System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
      return new ChromeDriver(options);
    }
  },
  FIREFOX {
    @Override
    public WebDriver getWebDriver(BrowserProperties browserProperties) {
      WebDriverManager.firefoxdriver().setup();
      FirefoxOptions options = new FirefoxOptions();
      options.setHeadless(browserProperties.isHeadless());
      options.merge(new DesiredCapabilities(browserProperties.getCapabilities()));
      return new FirefoxDriver(options);
    }
  },
  IE {
    @Override
    public WebDriver getWebDriver(BrowserProperties browserProperties) {
      WebDriverManager.iedriver().setup();
      InternetExplorerOptions options = new InternetExplorerOptions();
      options.merge(new DesiredCapabilities(browserProperties.getCapabilities()));
      return new InternetExplorerDriver(options);
    }
  },
  EDGE {
    @Override
    public WebDriver getWebDriver(BrowserProperties browserProperties) {
      WebDriverManager.edgedriver().setup();
      EdgeOptions options = new EdgeOptions();
      options.merge(new DesiredCapabilities(browserProperties.getCapabilities()));
      return new EdgeDriver(options);
    }
  },
  SAFARI {
    @Override
    public WebDriver getWebDriver(BrowserProperties browserProperties) {
      WebDriverManager.edgedriver().setup();
      SafariOptions options = new SafariOptions();
      options.merge(new DesiredCapabilities(browserProperties.getCapabilities()));
      return new SafariDriver(options);
    }
  },
  OPERA {
    @Override
    public WebDriver getWebDriver(BrowserProperties browserProperties) {
      WebDriverManager.edgedriver().setup();
      OperaOptions options = new OperaOptions();
      options.merge(new DesiredCapabilities(browserProperties.getCapabilities()));
      return new OperaDriver(options);
    }
  }
}
