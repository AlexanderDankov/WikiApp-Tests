package com.simbirsoft.tests;

import com.codeborne.selenide.Configuration;
import com.simbirsoft.drivers.BrowserStackMobileDriver;
import com.simbirsoft.drivers.EmulatorMobileDriver;
import com.simbirsoft.drivers.LocalMobileDriver;
import com.simbirsoft.drivers.SelenoidMobileDriver;
import com.simbirsoft.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.simbirsoft.helpers.Attach.getSessionId;

public class TestBase {
    public static String deviceHost = System.getProperty("deviceHost");

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        switch (deviceHost) {
            case "real":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            case "emulator":
                Configuration.browser = EmulatorMobileDriver.class.getName();
                break;
            case "selenoid":
                Configuration.browser = SelenoidMobileDriver.class.getName();
                break;
            case "browserstack":
                Configuration.browser = BrowserStackMobileDriver.class.getName();
                break;
        }

        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if(deviceHost.equals("browserstack") || deviceHost.equals("selenoid")) {
            String sessionId = getSessionId();
            Attach.attachVideo(sessionId);
        }

        closeWebDriver();
    }
}
