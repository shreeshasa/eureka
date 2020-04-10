package io.github.shreeshasa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * An abstraction class for a page object the can be executed for browsers.
 * <br><br>
 * Extend this in your page like:
 * <pre>
 *   <code>
 *     &#64;Page
 *     public class MyPage extends AbstractPage {
 *     }
 *   </code>
 * </pre>
 *
 * @author shreeshasa
 */
public abstract class AbstractPage {

  @Autowired
  protected WebDriver driver;

  @PostConstruct
  public void init() {
    PageFactory.initElements(driver, this);
  }

  /**
   * Waits until Clickable for given <code>webElement</code> upto <code>timeOutInSeconds</code> seconds
   *
   * @param timeOutInSeconds
   */
  private Wait<WebDriver> getWait(final long timeOutInSeconds) {
    return new FluentWait<>(driver)
      .withTimeout(Duration.ofSeconds(timeOutInSeconds))
      .pollingEvery(Duration.ofSeconds(1))
      .ignoring(Exception.class);
  }

  /**
   * Waits until Clickable for given <code>webElement</code> upto 10 seconds
   *
   * @param webElement
   */
  protected WebElement waitUntilClickable(final WebElement webElement) {
    return waitUntilClickable(webElement, 10);
  }

  /**
   * Waits until Clickable for given <code>webElement</code> upto <code>timeOutInSeconds</code> seconds
   *
   * @param webElement
   * @param timeOutInSeconds
   */
  protected WebElement waitUntilClickable(final WebElement webElement, final long timeOutInSeconds) {
    return getWait(timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(webElement));
  }

  /**
   * Waits until Clickable for given <code>locator</code> upto 5 seconds
   *
   * @param locator
   */
  protected WebElement waitUntilClickable(final By locator) {
    return waitUntilClickable(locator, 10);
  }

  /**
   * Waits until Clickable for given <code>locator</code> upto <code>timeOutInSeconds</code> seconds
   *
   * @param locator
   * @param timeOutInSeconds
   */
  protected WebElement waitUntilClickable(final By locator, final long timeOutInSeconds) {
    return getWait(timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
  }

  /**
   * Waits until Visibility for given <code>webElement</code> upto 10 seconds
   *
   * @param webElement
   */
  protected WebElement waitUntilVisibility(final WebElement webElement) {
    return waitUntilVisibility(webElement, 10);
  }

  /**
   * Waits until Visibility for given <code>webElement</code> upto <code>timeOutInSeconds</code> seconds
   *
   * @param webElement
   * @param timeOutInSeconds
   */
  protected WebElement waitUntilVisibility(final WebElement webElement, final long timeOutInSeconds) {
    return getWait(timeOutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
  }

  /**
   * Waits until Visibility for given <code>locator</code> upto 10 seconds
   *
   * @param locator
   */
  protected WebElement waitUntilVisibility(final By locator) {
    return waitUntilVisibility(locator, 10);
  }

  /**
   * Waits until Visibility for given <code>locator</code> upto <code>timeOutInSeconds</code> seconds
   *
   * @param locator
   * @param timeOutInSeconds
   */
  protected WebElement waitUntilVisibility(final By locator, final long timeOutInSeconds) {
    return getWait(timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  /**
   * Waits until Visibility for given <code>webElement</code> upto <code>timeOutInSeconds</code> seconds
   *
   * @param webElement
   * @param timeOutInSeconds
   */
  protected boolean waitUntilInvisibility(final WebElement webElement, final long timeOutInSeconds) {
    return getWait(timeOutInSeconds).until(ExpectedConditions.invisibilityOf(webElement));
  }
}
