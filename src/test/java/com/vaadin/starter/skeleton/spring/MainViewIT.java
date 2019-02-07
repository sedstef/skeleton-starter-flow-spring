package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.testbench.TestBenchTestCase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainViewIT extends TestBenchTestCase {

    private static int SERVERPORT = Integer.valueOf(System.getProperty("test.server.port"));

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        setDriver(new FirefoxDriver());
    }

    @After
    public void teardown() {
        getDriver().quit();
    }

    @Ignore
    @Test
    public void testBowerComponentsAreLoadable() {
        // /frontend/bower_components/webcomponentsjs/webcomponents-loader.js must be loadable
        getDriver().get(String.format("http://localhost:%s/frontend/bower_components/webcomponentsjs/webcomponents-loader.js", SERVERPORT));
        System.out.println(getDriver().getPageSource());
    }

    @Test
    public void clickMeMustShowNotification() {
        getDriver().get(String.format("http://localhost:%s", SERVERPORT));

        ButtonElement clickmeButton = $(ButtonElement.class).first();
        clickmeButton.click();

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        //<div part="content" class="style-scope vaadin-notification-card">Button was clicked at 18:30:47.795</div>
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("vaadin-notification-card")));
    }

}
