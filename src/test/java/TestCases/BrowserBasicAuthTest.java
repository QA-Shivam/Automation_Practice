package TestCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

//𝐓𝐞𝐬𝐭 𝐒𝐜𝐞𝐧𝐚𝐫𝐢𝐨: Develop an automation script that bypasses the Basic Browser Authentication Popup.
//testing Basic Authentication (a type of browser popup asking for username & password before loading the page).
//        Link: https://lnkd.in/dB3ZAGi7
//
//𝐔𝐬𝐞𝐫𝐧𝐚𝐦𝐞: admin
//𝐏𝐚𝐬𝐬𝐰𝐨𝐫𝐝: admin*/


public class BrowserBasicAuthTest {
    public  static WebDriver driver;
    WebDriverWait wait;
    @BeforeClass(alwaysRun = true)
    public void setDriver(){
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--start-maximized");
        driver= new ChromeDriver(options);
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @AfterClass
    public void tearDow(){
        driver.quit();
    }

    @Test
    public void BasicAuthTest(){
//      Selenium 4 interface for HTTP authentication handling.HasAuthentication, we can register username and password. When the browser requests authentication, Selenium provides credentials automatically,
//      and then we verify that login was successful.
        ((HasAuthentication) driver).register(() -> new UsernameAndPassword("admin", "admin"));
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        WebElement text = driver.findElement(By.xpath("//p"));
        Assert.assertEquals(text.getText(),"Congratulations! You must have the proper credentials.");
    }
}
