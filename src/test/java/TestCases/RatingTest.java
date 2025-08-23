package TestCases;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class RatingTest {
     /* 𝐔𝐬𝐢𝐧𝐠 𝐬𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 - 𝐫𝐞𝐚𝐝 𝐭𝐡𝐞 * 𝐫𝐚𝐭𝐢𝐧𝐠 𝐨𝐟 𝐭𝐡𝐞 𝐛𝐨𝐨𝐤,
        𝐞𝐧𝐭𝐞𝐫 𝐢𝐭 𝐢𝐧 𝐭𝐡𝐞 𝐭𝐞𝐱𝐭 𝐛𝐨𝐱 𝐚𝐧𝐝 𝐜𝐥𝐢𝐜𝐤 "𝐜𝐡𝐞𝐜𝐤 𝐫𝐚𝐭𝐢𝐧𝐠" 𝐛𝐮𝐭𝐭𝐨𝐧. 𝐲𝐨𝐮 𝐬𝐡𝐨𝐮𝐥𝐝 𝐬𝐞𝐞 "𝐰𝐞𝐥𝐥 𝐝𝐨𝐧𝐞!" 𝐦𝐞𝐬𝐬𝐚𝐠𝐞.
         𝐋𝐢𝐧𝐤: https://lnkd.in/dr5adTZK
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

    @Test
    public void RatingValidationTest(){

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
