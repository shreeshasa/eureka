package io.github.shreeshasa.selenium;

import lombok.Getter;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

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

  @Getter
  @Autowired
  private WebDriver webDriver;

  @PostConstruct
  public void init() {
    PageFactory.initElements(getWebDriver(), this);
  }

  /**
   * Check whether element is present
   *
   * @param webElement
   */
  public boolean isElementPresent(final WebElement webElement) {
    if (webElement.isDisplayed() && webElement.isEnabled()) {
      final Point point = webElement.getLocation();
      return point.x > 0 && point.y > 0;
    }
    return false;
  }
}
