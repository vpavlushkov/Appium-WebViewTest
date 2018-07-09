package com.kyyti.ride.test;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseAndroid {

    public String loginEmail = "test+citest@kyyti.com";

    public EnhancedAndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Before
    public void createAppiumDriverAndLogin() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Configs needed for local execution
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "foo");
        capabilities.setCapability(MobileCapabilityType.APP,
          "/path_to_this_repo/apps/app-kyytidevrelease.apk");

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 7913);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.SUPPORTS_LOCATION_CONTEXT, "true");
        capabilities.setCapability("autoGrantPermissions", "true");

        URL url = new URL("http://localhost:4723/wd/hub");

        driver = Factory.createAndroidDriver(url, capabilities);
        wait = new WebDriverWait(driver, 30, 500);
        driver.context("NATIVE_APP");

        // Wait until app loads, send email button visible
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("login.sendEmailButton")));

        driver.findElementByAccessibilityId("login.emailInputPlaceholder").setValue(loginEmail);
        driver.findElementByAccessibilityId("login.sendEmailButton").click();

        wait.until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("emailSent.problemsHelpPinCode")));
        driver.findElementByAccessibilityId("emailSent.problemsHelpPinCode").click();

        wait.until(ExpectedConditions
                .presenceOfElementLocated(MobileBy.AccessibilityId("pinCodeLogin.pinCodeInputPlaceholder")));
        driver.findElementByAccessibilityId("pinCodeLogin.pinCodeInputPlaceholder").setValue("123456");

        driver.findElementByAccessibilityId("pinCodeLogin.loginButton").click();

        // Wait main screen
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("main.hamburgerMenu")));
    }

    @After
    public void after() {
        driver.context("NATIVE_APP");
        if (driver != null) {
            driver.label("Stopping App");
            driver.quit();
        }
    }

}
