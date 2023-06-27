package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before

    public void setUp() {
        openBrowser(baseUrl); // setting up the browser
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        // finding and clicking the login button
        driver.findElement(By.linkText("Log in")).click();

        //verifying "Welcome, Please Sign In!" is displayed or not
        String expectedMessage = "Welcome, Please Sign In!";
        WebElement actualMessage = driver.findElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        String actualMessageText = actualMessage.getText();

        Assert.assertEquals("User is not redirected to login page", expectedMessage, actualMessageText);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        // finding and clicking log in button
        driver.findElement(By.linkText("Log in")).click();

        // finding and entering email and password
        driver.findElement(By.id("Email")).sendKeys("iamshaikh@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("123456");

        // clicking the login button
        driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();

        //verifying user is logged in successful or not by verifying the text displayed on the screen
        String expectedMessage = "Log out";
        WebElement actualMessage = driver.findElement(By.linkText("Log out"));
        String actualMessageText = actualMessage.getText();

        Assert.assertEquals("User is not logged in successfully", expectedMessage, actualMessageText);

    }

    @Test
    public void verifyTheErrorMessage() {
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.id("Email")).sendKeys("prime1234@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("Prime1234");
        driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
        // verifying the error message displayed or not
        String expectedMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found";
        WebElement actualMessage = driver.findElement(By.xpath("//div[contains(@class,'message-error ')]"));
        String actualMessageText = actualMessage.getText();
        Assert.assertEquals("Login is not successful, Invalid Credential", expectedMessage, actualMessageText);

    }

    @After

    public void tearDown() {
        closeBrowser(); // closing the browser
    }


}