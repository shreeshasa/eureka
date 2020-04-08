package io.github.shreeshasa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * A configuration bean for browser related properties.
 *
 * @author shreeshasa
 */
@Component
@ConfigurationProperties (prefix = "browser")
@Data
public class BrowserProperties {

  private String type;

  private boolean headless;

  private Map<String, String> capabilities;

  @PostConstruct
  public void init() {
    if (type == null) {
      type = "chrome";
    }
  }
}
