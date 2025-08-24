package TestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

public class ShadowDomTest {


        /* 𝐒𝐡𝐚𝐝𝐨𝐰 𝐃𝐎𝐌 page link: https://lnkd.in/d24JP747
        This is a page with a Shadow DOM component guid-generator. Using it one can generate a guid and copy it to the clipboard.

        - 𝐂𝐫𝐞𝐚𝐭𝐞 𝐚 𝐭𝐞𝐬𝐭 𝐭𝐡𝐚𝐭 𝐜𝐥𝐢𝐜𝐤𝐬 𝐨𝐧 ⚙𝐚𝐧𝐝 𝐭𝐡𝐞𝐧 𝐨𝐧 ⏹ 𝐜𝐨𝐩𝐲 𝐜𝐥𝐢𝐩𝐛𝐨𝐚𝐫𝐝 𝐛𝐮𝐭𝐭𝐨𝐧𝐬. 𝐓𝐡𝐢𝐬 𝐬𝐞𝐪𝐮𝐞𝐧𝐜𝐞 𝐨𝐟 𝐬𝐭𝐞𝐩𝐬 𝐠𝐞𝐧𝐞𝐫𝐚𝐭𝐞𝐬 𝐧𝐞𝐰 𝐠𝐮𝐢𝐝 𝐚𝐧𝐝 𝐜𝐨𝐩𝐢𝐞𝐬 𝐢𝐭 𝐭𝐨 𝐭𝐡𝐞 𝐜𝐥𝐢𝐩𝐛𝐨𝐚𝐫𝐝.
        - 𝐀𝐝𝐝 𝐚𝐧 𝐚𝐬𝐬𝐞𝐫𝐭𝐢𝐨𝐧 𝐬𝐭𝐞𝐩 𝐭𝐨 𝐲𝐨𝐮𝐫 𝐭𝐞𝐬𝐭 𝐭𝐨 𝐜𝐨𝐦𝐩𝐚𝐫𝐞 𝐭𝐡𝐞 𝐯𝐚𝐥𝐮𝐞 𝐟𝐫𝐨𝐦 𝐭𝐡𝐞 𝐜𝐥𝐢𝐩𝐛𝐨𝐚𝐫𝐝 𝐰𝐢𝐭𝐡 𝐭𝐡𝐞 𝐯𝐚𝐥𝐮𝐞 𝐨𝐟 𝐭𝐡𝐞 𝐢𝐧𝐩𝐮𝐭 𝐟𝐢𝐞𝐥𝐝.
        - 𝐓𝐡𝐞𝐧 𝐞𝐱𝐞𝐜𝐮𝐭𝐞 𝐭𝐡𝐞 𝐭𝐞𝐬𝐭 𝐭𝐨 𝐦𝐚𝐤𝐞 𝐬𝐮𝐫𝐞 𝐭𝐡𝐚𝐭 𝐭𝐡𝐞 𝐚𝐬𝐬𝐞𝐫𝐭𝐢𝐨𝐧 𝐬𝐭𝐞𝐩 𝐢𝐬 𝐧𝐨𝐭 𝐟𝐚𝐢𝐥𝐢𝐧𝐠.
        */
public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void setDriver() {
        ChromeOptions op = new ChromeOptions();
        op.addArguments("--start-maximized");
        op.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(op);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void DomTest() throws IOException, UnsupportedFlavorException {
        driver.get("https://uitestingplayground.com/shadowdom");
        SearchContext DomContext = driver.findElement(By.tagName("guid-generator")).getShadowRoot();
        DomContext.findElement(By.cssSelector("#buttonGenerate")).click();
        String guid = DomContext.findElement(By.cssSelector("#editField")).getAttribute("value");
        System.out.println(guid);
        DomContext.findElement(By.cssSelector("#buttonCopy")).click();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String clipData = (String) clipboard.getData(DataFlavor.stringFlavor);
        Assert.assertEquals(clipData, guid, "Clipboard GUID does not match generated GUID!");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
