package TestCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

public class CodeVerificationTest {

    /* 𝐔𝐬𝐢𝐧𝐠 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐞𝐧𝐭𝐞𝐫 𝐭𝐡𝐞 𝐯𝐚𝐥𝐢𝐝 𝐜𝐨𝐝𝐞 𝐛𝐲 𝐤𝐞𝐲𝐛𝐨𝐚𝐫𝐝 𝐤𝐞𝐲𝐬 𝐛𝐲 𝐩𝐫𝐞𝐬𝐬𝐢𝐧𝐠 𝐭𝐡𝐞 𝐨𝐧𝐥𝐲 𝐤𝐞𝐲 𝐛𝐮𝐭𝐭𝐨𝐧 𝐚𝐧𝐝 𝐚𝐬𝐬𝐞𝐫𝐭𝐢𝐧𝐠 "𝐬𝐮𝐜𝐜𝐞𝐬𝐬" 𝐦𝐞𝐬𝐬𝐚𝐠𝐞.
        The confirmation code is - "999999".
        You cannot use sendkeys("9") directly.
        https://lnkd.in/ddfR-Gpa
        𝐇𝐢𝐧𝐭: Use keyboard events Keys concept
         */

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
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void VerifyCode(){
        driver.get("https://qaplayground.dev/apps/verify-account/");
        String code= driver.findElement(By.cssSelector(".info")).getText().split("is")[1].replaceAll("-","").trim();
        System.out.println("The confirmation code is: "+ code);
        List<WebElement> inputs=driver.findElements(By.cssSelector(".code-container input"));
        Actions actions= new Actions(driver);
        IntStream.range(0, inputs.size()).forEach(i -> {
            char digit = code.charAt(i); // get each digit
            String keyName = "NUMPAD" + digit; // e.g., NUMPAD9
            Keys key = Keys.valueOf(keyName);
            actions.moveToElement(inputs.get(i)).click().keyDown(key).keyUp(key).perform();
        });
        Assert.assertTrue(driver.findElement(By.cssSelector(".success")).getText().equalsIgnoreCase("Success"));
    }

}
