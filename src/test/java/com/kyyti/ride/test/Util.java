package com.kyyti.ride.test;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {
    public static MobileElement findByByOrName(EnhancedAndroidDriver<MobileElement> driver, By by, String name) {
        try {
            return driver.findElement(by);

        } catch (org.openqa.selenium.NoSuchElementException e) {
            return findByName(driver, name);
        }
    }

    public static MobileElement findByName(EnhancedAndroidDriver<MobileElement> driver, String name) {
        return driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
                        + name + "\").instance(0))");
    }

    /**
     * Auxiliary method to list elements on a page with given accessibilityId that
     * can run retries to address the stability issue caused by the React dynamic
     * page rendering.
     * 
     * @param driver   Android driver
     * @param wait     WebDriver wait
     * @param accessId accessibilityId to match the elements to
     * @param retries  number of retries before giving up
     * @return list of elements that have specified accessibilityId
     */
    public static List<MobileElement> findElementByAccessibilityIdWithRetry(EnhancedAndroidDriver<MobileElement> driver,
            WebDriverWait wait, String accessId, int retries) {
        List<MobileElement> suggestionsList = new ArrayList<MobileElement>();
        for (int i = 0; i <= retries; i++) {
            wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(accessId)));
            suggestionsList = driver.findElementsByAccessibilityId(accessId);
            if (suggestionsList.size() > 0) {
                return suggestionsList;
            }
        }
        return suggestionsList;
    }

    /**
     * This is an interface to the identically named method above when number of
     * retries is set to default 1.
     * 
     * @param driver   Android driver
     * @param wait     WebDriver wait
     * @param accessId accessibilityId to match elements to
     * @return list of elements that have specified accessibilityId
     */
    public static List<MobileElement> findElementByAccessibilityIdWithRetry(EnhancedAndroidDriver<MobileElement> driver,
            WebDriverWait wait, String accessId) {
        return findElementByAccessibilityIdWithRetry(driver, wait, accessId, 1);
    }

    /**
     *
     * @param length Length of the string
     * @return String of given length consisting of alphanumeric characters only
     */
    public static String randomString(int length) {
        char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'z', 'u', 'i',
                'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'y', 'x', 'c', 'v', 'b', 'n', 'm', 'Q', 'W', 'E',
                'R', 'T', 'Z', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Y', 'X', 'C', 'V', 'B',
                'N', 'M' };

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(chars[new Random().nextInt(chars.length)]);
        }
        return stringBuilder.toString();
    }
}
