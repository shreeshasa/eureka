package io.github.shreeshasa.selenium;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

/**
 * A marker interface for PageObjects. Annotate any PageObject classes with this.
 *
 * @author shreeshasa
 */
@Target (ElementType.TYPE)
@Retention (RetentionPolicy.RUNTIME)
@Component
@Scope (SCOPE_CUCUMBER_GLUE)
public @interface Page {

}
