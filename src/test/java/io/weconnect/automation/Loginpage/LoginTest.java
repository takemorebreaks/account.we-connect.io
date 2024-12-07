package io.weconnect.automation.Loginpage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.weconnect.automation.webdriverFactory.DriverFactory;

public class LoginTest {
    WebDriver driver;
    LoginPageHelperMethods loginPage;

    @BeforeClass
    public void setupClass() {
        // Initialize WebDriver using DriverFactory before any tests are run
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.GetWebDriver("chrome");
        // Initialize LoginPageHelperMethods
        loginPage = new LoginPageHelperMethods(driver);
    }

    @BeforeMethod
    public void configTest() {
        // Navigate to the login page before each test
        driver.get("https://account.we-connect.io/login");
    }

    @Test(description = "Verify valid login functionality",dataProvider = "loginData",dataProviderClass = LoginTestData.class)
    public void testValidLogin(String userName,String password,String Result) {
        // Input valid username and password
        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);

        // Click the login button
        loginPage.clickLoginButton();
    }

    @Test(description = "Verify invalid login functionality")
    public void testInvalidLogin() {
        // Input invalid username and password
        loginPage.enterUsername("invalidUsername");
        loginPage.enterPassword("invalidPassword");

        // Click the login button
        loginPage.clickLoginButton();
    }

    @Test(description = "Verify 'I'm not a robot' checkbox visibility")
    public void testNotRobotCheckbox() {
        // Verify if the 'I'm not a robot' checkbox is visible
        boolean isCheckboxVisible = loginPage.isNotRobotCheckboxVisible();
    }
}
