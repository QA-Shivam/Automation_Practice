package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

/*𝐔𝐬𝐢𝐧𝐠 𝐬𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 - 𝐂𝐥𝐢𝐜𝐤 𝐨𝐧 𝐭𝐡𝐞 𝐠𝐫𝐨𝐰𝐢𝐧𝐠 𝐛𝐮𝐭𝐭𝐨𝐧 𝐚𝐧𝐝 𝐨𝐧𝐜𝐞 𝐜𝐥𝐢𝐜𝐤𝐞𝐝 𝐲𝐨𝐮 𝐬𝐡𝐨𝐮𝐥𝐝 𝐬𝐞𝐞 "𝐄𝐯𝐞𝐧𝐭 𝐓𝐫𝐢𝐠𝐠𝐞𝐫𝐞𝐝" 𝐦𝐞𝐬𝐬𝐚𝐠𝐞.
𝐕𝐞𝐫𝐢𝐟𝐲 𝐭𝐡𝐚𝐭 "𝐄𝐯𝐞𝐧𝐭 𝐓𝐫𝐢𝐠𝐠𝐞𝐫𝐞𝐝".
https://lnkd.in/d9HmwQu7  */

public class GrowingButtonTest {
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
    public void AnimationButtonClickTest(){
    driver.get("https://testpages.eviltester.com/styled/challenges/growing-clickable.html");
    WebElement button = driver.findElement(By.id("growbutton"));
    wait.until(ExpectedConditions.attributeContains(button, "class", "grown"));
    button.click();
    WebElement eventTrigger= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("growbuttonstatus")));
    System.out.println("Event Trigger Status: "+ eventTrigger.getText());
}
}
