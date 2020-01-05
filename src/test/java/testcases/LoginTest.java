package testcases;

import base.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void setUp() throws IOException {
        init();
        logger = Logger.getLogger(LoginTest.class);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(dataProvider = "invalidLoginDataProvider")
    public void invalidLoginTest(String username, String password, String confirmUsername, String expectedResult) {
        logger.info("invalid login test started ....... ");

        driver.findElement(By.xpath(application.getProperty("username"))).sendKeys(username);
        logger.info("username entered");

        driver.findElement(By.xpath(application.getProperty("password"))).sendKeys(password);
        logger.info("password entered");

        driver.findElement(By.xpath(application.getProperty("confirmusername"))).sendKeys(confirmUsername);
        logger.info("confirmed username");

        driver.findElement(By.xpath(application.getProperty("loginbutton"))).click();
        logger.info("login button clicked");

        String actualResult = driver.findElement(By.xpath(application.getProperty("error.message"))).getText();

        Assert.assertEquals(actualResult.trim(),expectedResult.trim());
        logger.info("login successful");
    }

    @Test(dataProvider = "validLoginDataProvider")
    public void validLoginTest(String username, String password, String confirmUsername, String expectedResult) {
        logger.info("valid login test started ....... ");

        driver.findElement(By.xpath(application.getProperty("username"))).sendKeys(username.trim());
        logger.info("username entered");

        driver.findElement(By.xpath(application.getProperty("password"))).sendKeys(password.trim());
        logger.info("password entered");

        driver.findElement(By.xpath(application.getProperty("confirmusername"))).sendKeys(confirmUsername.trim());
        logger.info("confirmed username");

        driver.findElement(By.xpath(application.getProperty("loginbutton"))).click();
        logger.info("login button clicked");

        String actualResult = driver.findElement(By.xpath(application.getProperty("success.message"))).getText();

        Assert.assertEquals(actualResult.trim(),expectedResult.trim());
        logger.info("login successful");
    }


    @DataProvider(name = "invalidLoginDataProvider")
    public Iterator<Object[]> invalidLoginDataProviderMethod() throws IOException {
       return csvUtils.readFile("./src/test/resources/csv/invalidLoginData.csv");
    }

    @DataProvider(name = "validLoginDataProvider")
    public Iterator<Object[]> validLoginDataProviderMethod() throws IOException {
        return csvUtils.readFile("./src/test/resources/csv/validLoginData.csv");
    }
}