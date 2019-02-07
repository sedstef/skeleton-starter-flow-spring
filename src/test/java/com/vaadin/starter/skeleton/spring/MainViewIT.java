package com.vaadin.starter.skeleton.spring;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainViewIT {

    private static int SERVERPORT = Integer.valueOf(System.getProperty("test.server.port"));

    private WebDriver webDriver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        // Create a new browser instance
        webDriver = new FirefoxDriver();
    }

    @After
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Ignore
    @Test
    public void testBowerComponentsAreLoadable() {
        // /frontend/bower_components/webcomponentsjs/webcomponents-loader.js must be loadable
        webDriver.get(String.format("http://localhost:%s/frontend/bower_components/webcomponentsjs/webcomponents-loader.js", SERVERPORT));
        System.out.println(webDriver.getPageSource());
    }

    @Test
    public void clickMeMustShowNotification() {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        webDriver.get(String.format("http://localhost:%s", SERVERPORT));

        WebElement clickmeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("vaadin-button")));
        clickmeButton.click();

        //<div part="content" class="style-scope vaadin-notification-card">Button was clicked at 18:30:47.795</div>
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("vaadin-notification-card")));
    }

}
