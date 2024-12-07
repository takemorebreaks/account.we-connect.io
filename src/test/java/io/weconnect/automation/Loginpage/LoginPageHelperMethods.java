package io.weconnect.automation.Loginpage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageHelperMethods {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPageHelperMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofMillis(5000));
    }

    // Method to enter the username
    public void enterUsername(String username) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageElementLocators.userName)));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    // Method to enter the password
    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageElementLocators.passsword)));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // Method to click the login button
    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LoginPageElementLocators.loginButton)));
       // loginButton.click();
    }

    // Method to verify the 'I'm not a robot' checkbox
    public boolean isNotRobotCheckboxVisible() {
        try {
            WebElement notRobotCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageElementLocators.checkboxNotRobot)));
           if(notRobotCheckbox.isDisplayed()) {
        	   notRobotCheckbox.click();
        	   return true;
           }
        } catch (Exception e) {
            return false;
        }
		return false;
    }

}
