package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SocialLinksTest {
    /*  𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐩𝐞𝐫𝐟𝐨𝐫𝐦𝐬 𝐭𝐡𝐞 "𝐑𝐢𝐠𝐡𝐭 𝐂𝐥𝐢𝐜𝐤" 𝐨𝐟 𝐦𝐨𝐮𝐬𝐞 𝐚𝐧𝐝
        𝐲𝐨𝐮 𝐰𝐢𝐥𝐥 𝐬𝐞𝐞 𝐭𝐡𝐞 𝐦𝐞𝐧𝐮 𝐭𝐡𝐞𝐧 𝐧𝐚𝐯𝐢𝐠𝐚𝐭𝐞 𝐭𝐨 "𝐒𝐡𝐚𝐫𝐞 𝐦𝐞𝐧𝐮" 𝐨𝐩𝐭𝐢𝐨𝐧 𝐚𝐧𝐝 𝐜𝐥𝐢𝐜𝐤 𝐨𝐧 𝐚𝐥𝐥 "𝐬𝐨𝐜𝐢𝐚𝐥 𝐦𝐞𝐝𝐢𝐚 𝐥𝐢𝐧𝐤𝐬" 𝐢𝐧 𝐬𝐮𝐛-𝐦𝐞𝐧𝐮.
        𝐚𝐧𝐝 𝐚𝐬𝐬𝐞𝐫𝐭𝐬 𝐭𝐡𝐞 𝐯𝐞𝐫𝐢𝐟𝐢𝐜𝐚𝐭𝐢𝐨𝐧 𝐦𝐞𝐬𝐬𝐚𝐠𝐞 𝐟𝐨𝐫 𝐚𝐥𝐥 𝐬𝐨𝐜𝐢𝐚𝐥 𝐥𝐢𝐧𝐤𝐬.
        e.g: "Menu item Twitter clicked"
        https://lnkd.in/dFjNKBKE */

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void setDriver() {
        ChromeOptions op= new ChromeOptions();
        op.addArguments("--start-maximized");
        driver = new ChromeDriver(op);
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void PerformRightClickOnSocialLinks(){
        driver.get("https://qaplayground.dev/apps/context-menu/");
        Actions actions= new Actions(driver);
        WebElement body = driver.findElement(By.tagName("body"));
        WebElement shareIcon = driver.findElement(By.cssSelector(".uil-share"));
        List<WebElement> socialLinks = driver.findElements(By.cssSelector("ul.share-menu li"));
        By socialmenumessage= By.cssSelector("p#msg");

        for (WebElement link : socialLinks)
        {
            actions.contextClick(body).moveToElement(shareIcon).perform();
            String menuText = link.getText();
            actions.moveToElement(link).click().perform();
            System.out.println("Clicked on: " + menuText);
            wait.until(ExpectedConditions.visibilityOfElementLocated(socialmenumessage));
            
            if (menuText.equalsIgnoreCase("Twitter")) {
                Assert.assertTrue(driver.findElement(socialmenumessage).getText().equalsIgnoreCase("Menu item Twitter clicked"));
                System.out.println("Twitter link - verified ");
            }
            else if (menuText.equalsIgnoreCase("Instagram")) {
                Assert.assertTrue(driver.findElement(socialmenumessage).getText().equalsIgnoreCase("Menu item Instagram clicked"));
                System.out.println("Instagram link -verified ");
            }
            else if (menuText.equalsIgnoreCase("Dribble")) {
                Assert.assertTrue(driver.findElement(socialmenumessage).getText().equalsIgnoreCase("Menu item Dribble clicked"));
                System.out.println("Dribble link -verified ");
            }
            else if (menuText.equalsIgnoreCase("Telegram")) {
                Assert.assertTrue(driver.findElement(socialmenumessage).getText().equalsIgnoreCase("Menu item Telegram clicked"));
                System.out.println("Telegram link -verified ");
            }
        }
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}

