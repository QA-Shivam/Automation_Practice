package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class InputValueInDisabledFieldTest {

    /*-𝐂𝐨𝐝𝐢𝐧𝐠 𝐂𝐡𝐚𝐥𝐥𝐞𝐧𝐠𝐞:
         𝐓𝐞𝐬𝐭 𝐒𝐜𝐞𝐧𝐚𝐫𝐢𝐨: Develop an automation script that will input a value in a disabled field
         (You cannot able to input value in field with sendkeys() directly if field is disabled)
         𝐋𝐢𝐧𝐤: https://lnkd.in/deJDkARE*/

    public static WebDriver driver;
    public static void main(String[] args) {
        driver= new ChromeDriver();
        driver.navigate().to("https://seleniumpractise.blogspot.com/2016/09/how-to-work-with-disable-textbox-or.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('pass').removeAttribute('disabled')");
        driver.findElement(By.id("pass")).sendKeys("Hello...");
        String value= driver.findElement(By.id("pass")).getAttribute("value");
        System.out.println(value);
        Assert.assertEquals(value, "Hello...");
        driver.quit();
    }
}
