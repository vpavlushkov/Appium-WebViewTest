package com.kyyti.ride.test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

public class AccountTest extends BaseAndroid {

        @Test
        public void updateVAGroupInfoTest() {
                driver.label("App stated & main screen opened");

                Random r = new Random();
                int employeeNumber = r.nextInt();
                String employeeNumberString = Integer.toString(employeeNumber);

                // opening the drawer menu
                driver.findElementByAccessibilityId("main.hamburgerMenu").click();
                wait.until(ExpectedConditions
                                .presenceOfElementLocated(MobileBy.AccessibilityId("drawer.accountItemLabelLink")));
                driver.label("Drawer menu opened");

                // opening account menu item
                driver.findElementByAccessibilityId("drawer.accountItemLabelLink").click();
                wait.until(ExpectedConditions.presenceOfElementLocated(
                                MobileBy.xpath("//android.widget.TextView[@text=\"ACCOUNT\"]")));
                driver.label("User account view opened");

                // opening groups view
                driver.findElementByXPath("//android.widget.TextView[@text=\"Groups\"]").click();
                wait.until(ExpectedConditions.presenceOfElementLocated(
                                MobileBy.xpath("//android.widget.TextView[@text=\"GROUPS\"]")));
                driver.label("Groups view opened");

                // opening VA group for editing
                driver.findElementByXPath("//android.widget.TextView[@text=\"Valmet Automotive\"]").click();
                // wait.until(ExpectedConditions.presenceOfElementLocated(
                // MobileBy.xpath("//android.widget.Button[@text=\"Save\"]")));
                wait.until(ExpectedConditions.presenceOfElementLocated(
                                MobileBy.xpath("//android.webkit.WebView/android.webkit.WebView/android.view.View")));
                driver.label("Update group info view opened");

                // Switching to WebView context
                Set<String> contextNames = driver.getContextHandles();
                System.out.println("context name: " + contextNames);
                String webViewContextNameRaw = contextNames.toArray()[1].toString();
                String[] webViewContextNameArray = webViewContextNameRaw.split(":");
                String webViewContextName = webViewContextNameArray[0];
                System.out.println("WebView context name: " + webViewContextName);
                driver.context(webViewContextName);

                // trying to provide empty employer number and checking popup warning
                driver.findElement(By.xpath("//input[@class=\"input__field\"]")).setValue("12345");
                //driver.findElementByXPath("//*[@text=\"Employee number\"]");
                 // /following-sibling::android.widget.EditText").setValue("");
                driver.context("NATIVE_APP");
                driver.label("Found employee number text");

/*
                driver.findElementByXPath("//android.widget.Button[@text=\"Save\"]").click();
                wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("android:id/alertTitle")));
                wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath(
                                "//android.widget.TextView[@text=\"Please fill all fields in order to proceed\"]")));
                driver.label("Warning popup is shown");

                // getting back by pressing "OK" and entering a valid number
                driver.findElementById("android:id/button1").click();
                driver.findElementByXPath(
                                "//android.view.View[@text=\"Employee number\"]/following-sibling::android.widget.EditText")
                                .setValue(employeeNumberString);
                driver.findElementByXPath("//android.widget.Button[@text=\"Save\"]").click();
                wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("android:id/alertTitle")));
                wait.until(ExpectedConditions.presenceOfElementLocated(
                                MobileBy.xpath("//android.widget.TextView[@text=\"Group info updated\"]")));
                driver.label("Update confirmation popup is shown");

                // getting to Account view by pressing "OK" and checking updated group info
                driver.findElementById("android:id/button1").click();
                driver.findElementByXPath("//android.widget.TextView[@text=\"Groups\"]").click();
                driver.findElementByXPath("//android.widget.TextView[@text=\"Valmet Automotive\"]").click();
                wait.until(ExpectedConditions.presenceOfElementLocated(
                                MobileBy.xpath("//android.widget.TextView[@text=\"UPDATE GROUP INFO\"]")));
                wait.until(ExpectedConditions.presenceOfElementLocated(
                                MobileBy.xpath("//android.widget.EditText[@text=\"" + employeeNumberString + "\"]")));
                driver.label("Group info updated correctly");

                // randomly deciding whether returning to groups screen via application or
                // platform back button
                if (r.nextFloat() > 0.5) {
                        // app back
                        driver.findElementByAccessibilityId("whiteNavBar.backArrow").click();
                        wait.until(ExpectedConditions.presenceOfElementLocated(
                                        MobileBy.xpath("//android.widget.TextView[@text=\"GROUPS\"]")));
                        driver.label("Returned to groups screen via application back");
                } else {
                        // platform back
                        driver.navigate().back();
                        wait.until(ExpectedConditions.presenceOfElementLocated(
                                        MobileBy.xpath("//android.widget.TextView[@text=\"GROUPS\"]")));
                        driver.label("Returned to groups screen via platform back");
                }

                // randomly deciding whether returning to account screen via application or
                // platform back button
                if (r.nextFloat() > 0.5) {
                        // app back
                        driver.findElementByAccessibilityId("whiteNavBar.backArrow").click();
                        wait.until(ExpectedConditions.presenceOfElementLocated(
                                        MobileBy.xpath("//android.widget.TextView[@text=\"ACCOUNT\"]")));
                        driver.label("Returned to account screen via application back");
                } else {
                        // platform back
                        driver.navigate().back();
                        wait.until(ExpectedConditions.presenceOfElementLocated(
                                        MobileBy.xpath("//android.widget.TextView[@text=\"ACCOUNT\"]")));
                        driver.label("Returned to account screen via platform back");
                }

                // randomly deciding whether returning back to main screen via application or
                // platform back button
                if (r.nextFloat() > 0.5) {
                        // app back
                        driver.findElementByAccessibilityId("staticNavBar.backButton").click();
                        wait.until(ExpectedConditions.presenceOfElementLocated(
                                        MobileBy.AccessibilityId("main.textInputPlaceholder")));
                        driver.label("Returned to main screen via application back");
                } else {
                        // platform back
                        driver.navigate().back();
                        wait.until(ExpectedConditions.presenceOfElementLocated(
                                        MobileBy.AccessibilityId("main.textInputPlaceholder")));
                        driver.label("Returned to main screen via platform back");
                }
*/
        }
}
