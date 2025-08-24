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

public class SortListTest {
    /* 𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐩𝐞𝐫𝐟𝐨𝐫𝐦𝐬 𝐭𝐡𝐞 𝐃𝐫𝐚𝐠 𝐚𝐧𝐝 𝐝𝐫𝐨𝐩 𝐭𝐡𝐞 𝐢𝐭𝐞𝐦𝐬 𝐢𝐧𝐭𝐨 𝐭𝐡𝐞𝐢𝐫 𝐜𝐨𝐫𝐫𝐞𝐬𝐩𝐨𝐧𝐝𝐢𝐧𝐠 𝐬𝐩𝐨𝐭𝐬 .
    𝐇𝐞𝐫𝐞 𝐢𝐬 𝐚 𝐥𝐢𝐬𝐭 𝐨𝐟 𝐭𝐡𝐞 10 𝐑𝐢𝐜𝐡𝐞𝐬𝐭 𝐏𝐞𝐨𝐩𝐥𝐞 - 𝐲𝐨𝐮 𝐧𝐞𝐞𝐝 𝐭𝐨 𝐚𝐫𝐫𝐚𝐧𝐠𝐞 𝐭𝐡𝐞𝐧 𝐢𝐧 𝐭𝐡𝐞 𝐜𝐨𝐫𝐫𝐞𝐜𝐭 𝐨𝐫𝐝𝐞𝐫 𝐚𝐬 𝐠𝐢𝐯𝐞𝐧 𝐛𝐞𝐥𝐨𝐰 :

    // 𝐄𝐱𝐩𝐞𝐜𝐭𝐞𝐝 𝐨𝐫𝐝𝐞𝐫 𝐨𝐟 𝐧𝐚𝐦𝐞𝐬 𝐢𝐧 𝐋𝐢𝐬𝐭 𝐨𝐫𝐝𝐞𝐫:
        position: 1, name: "Jeff Bezos"
        position: 2, name: "Bill Gates"
        position: 3, name: "Warren Buffett"
        position: 4, name: "Bernard Arnault"
        position: 5, name: "Carlos Slim Helu"
        position: 6, name: "Amancio Ortega"
        position: 7, name: "Larry Ellison"
        position: 8, name: "Mark Zuckerberg"
        position: 9, name: "Michael Bloomberg"

        https://lnkd.in/dvEj3Jha
     */

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void setDriver() {
        ChromeOptions op = new ChromeOptions();
        op.addArguments("--start-maximized");
        driver = new ChromeDriver(op);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void SortingRichestPeople() {
        driver.get("https://qaplayground.dev/apps/sortable-list/");

        String[] expectedList = {
                "Jeff Bezos",
                "Bill Gates",
                "Warren Buffett",
                "Bernard Arnault",
                "Carlos Slim Helu",
                "Amancio Ortega",
                "Larry Ellison",
                "Mark Zuckerberg",
                "Michael Bloomberg"
        };
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        int counter = 1;
        for (String name : expectedList) {
            WebElement draggableItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), '" + name + "')]")));
            WebElement targetPosition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), '" + counter + "')]")));
            actions.dragAndDrop(draggableItem, targetPosition).build().perform();
            counter++;
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check")));
        driver.findElement(By.id("check")).click();
        for (String name : expectedList) {
            WebElement listItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), '" + name + "')]")));
            String color = listItem.getCssValue("color");
            Assert.assertEquals(color, "rgba(58, 227, 116, 1)", name + " is not placed correctly. Found color:" + color);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
