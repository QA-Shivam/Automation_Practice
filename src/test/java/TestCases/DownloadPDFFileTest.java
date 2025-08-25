package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;

public class DownloadPDFFileTest {
 /* 𝐂𝐫𝐞𝐚𝐭𝐞 𝐚𝐧 𝐚𝐮𝐭𝐨𝐦𝐚𝐭𝐢𝐨𝐧 𝐒𝐞𝐥𝐞𝐧𝐢𝐮𝐦 𝐭𝐞𝐬𝐭 𝐬𝐜𝐫𝐢𝐩𝐭 𝐭𝐡𝐚𝐭 𝐝𝐨𝐰𝐧𝐥𝐨𝐚𝐝𝐬 𝐭𝐡𝐞 𝐩𝐝𝐟 𝐟𝐢𝐥𝐞 𝐚𝐧𝐝 𝐬𝐭𝐨𝐫𝐞𝐬 𝐢𝐧 𝐲𝐨𝐮𝐫 𝐥𝐨𝐜𝐚𝐥 𝐬𝐲𝐬𝐭𝐞𝐦
        𝐒𝐭𝐞𝐩𝐬 :
          1) 𝐍𝐚𝐯𝐢𝐠𝐚𝐭𝐞 𝐭𝐨 𝐰𝐞𝐛𝐬𝐢𝐭𝐞 𝐨𝐧𝐞 𝐛𝐲 𝐨𝐧𝐞 𝐰𝐡𝐢𝐜𝐡 𝐢𝐬 𝐦𝐞𝐧𝐭𝐢𝐨𝐧𝐞𝐝 𝐛𝐞𝐥𝐨𝐰 : https://lnkd.in/d6dUJb7t 𝐘𝐨𝐮 𝐧𝐞𝐞𝐝 𝐭𝐨 𝐬𝐭𝐨𝐫𝐞 𝐔𝐑𝐋 𝐢𝐧 𝐜𝐨𝐧𝐟𝐢𝐠 𝐟𝐢𝐥𝐞.
          2) 𝐎𝐩𝐞𝐧 𝐭𝐡𝐞 𝐩𝐚𝐠𝐞 𝐚𝐧𝐝 𝐜𝐥𝐢𝐜𝐤 𝐨𝐧 𝐭𝐡𝐞 𝐛𝐞𝐥𝐨𝐰 𝐥𝐢𝐧𝐤 𝐞𝐥𝐞𝐦𝐞𝐧𝐭 𝐨𝐧 𝐭𝐡𝐞 𝐖𝐞𝐛𝐩𝐚𝐠𝐞 𝐛𝐲 𝐥𝐨𝐜𝐚𝐭𝐨𝐫 𝐬𝐭𝐫𝐚𝐭𝐞𝐠𝐲 𝐠𝐢𝐯𝐞𝐧 𝐛𝐞𝐥𝐨𝐰 :
          WebElement pdfLink = driver.findElement(By.linkText("Download a Printable PDF of this Cheat Sheet"));
          3) 𝐀𝐟𝐭𝐞𝐫 𝐜𝐥𝐢𝐜𝐤𝐢𝐧𝐠 𝐨𝐧 𝐭𝐡𝐞 𝐥𝐢𝐧𝐤, 𝐚 𝐧𝐞𝐰 𝐏𝐃𝐅 𝐟𝐢𝐥𝐞 𝐢𝐬 𝐨𝐩𝐞𝐧𝐞𝐝 𝐢𝐧 𝐚 𝐧𝐞𝐰 𝐭𝐚𝐛 𝐢𝐧 𝐏𝐃𝐅 𝐯𝐢𝐞𝐰𝐞𝐫 - 𝐲𝐨𝐮 𝐧𝐞𝐞𝐝 𝐭𝐨 𝐝𝐨𝐰𝐧𝐥𝐨𝐚𝐝 𝐭𝐡𝐚𝐭 𝐏𝐃𝐅 𝐟𝐢𝐥𝐞 𝐨𝐧 𝐲𝐨𝐮𝐫 𝐥𝐨𝐜𝐚𝐥 𝐝𝐞𝐯𝐢𝐜𝐞.
          4) 𝐀𝐥𝐬𝐨 𝐯𝐞𝐫𝐢𝐟𝐲 𝐢𝐟 𝐭𝐡𝐞 𝐩𝐚𝐭𝐡 𝐲𝐨𝐮 𝐩𝐫𝐨𝐯𝐢𝐝𝐞𝐝, 𝐡𝐚𝐬 𝐝𝐨𝐰𝐧𝐥𝐨𝐚𝐝𝐞𝐝 𝐭𝐡𝐞 𝐩𝐝𝐟 𝐟𝐢𝐥𝐞 𝐨𝐫 𝐧𝐨𝐭.
          𝐂𝐡𝐚𝐥𝐥𝐞𝐧𝐠𝐞: You cannot use 𝐂𝐡𝐫𝐨𝐦𝐞𝐎𝐩𝐭𝐢𝐨𝐧𝐬,𝐅𝐢𝐫𝐞𝐟𝐨𝐱𝐎𝐩𝐭𝐢𝐨𝐧𝐬 and 𝐊𝐞𝐲𝐛𝐨𝐚𝐫𝐝 𝐬𝐡𝐨𝐫𝐭𝐜𝐮𝐭𝐬 - 𝐊𝐞𝐲𝐄𝐯𝐞𝐧𝐭𝐬 for it.*/

    public static WebDriver driver;
    WebDriverWait wait;
    public static String outputFilePath = "C://Users//satya//Downloads";

    @BeforeClass(alwaysRun = true)
    public void setDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void DownloadAndSavePDFFile() throws IOException {
        driver.get("https://intellipaat.com/blog/tutorial/selenium-tutorial/selenium-cheat-sheet/");
        String parenthandle=driver.getWindowHandle();
        WebElement pdfLink = driver.findElement(By.linkText("Download a Printable PDF of this Cheat Sheet"));
        pdfLink.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        driver.switchTo().window(driver.getWindowHandles().stream()
                .filter(handel->!handel.equals(parenthandle))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("No Child Window Found")));
        String pdfurl=driver.getCurrentUrl();
        System.out.println(pdfurl);
        Assert.assertFalse(pdfurl.isEmpty(),"PDF URL is empty");

        //Creates a network connection to the given PDF URL.
        URLConnection connection = new URL(pdfurl).openConnection();
        //Reads raw binary data from the connection (like downloading in small chunks).
        InputStream inputStream=connection.getInputStream();
        //Prepares to write data into a new file named pdfTest.pdf in the output folder.
        FileOutputStream fileOutputStream= new FileOutputStream(outputFilePath+File.separator+"pdfTest.pdf");
        //Creates a 1KB array to store chunks of data temporarily while downloading
        byte[] buffer =new byte[1024];
        int bytesread;

        //inputStream.read(buffer) reads bytes into the buffer.
        //Returns how many bytes were actually read.
        //-1 means end of file (download completed).
        while((bytesread=inputStream.read(buffer))!=-1){
            //fileOutputStream.write(...) writes those bytes to the file.
            fileOutputStream.write(buffer,0,bytesread);
        }
        System.out.println("PDF File downloaded successfully to: "+ outputFilePath);
        inputStream.close();
        fileOutputStream.close();
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}

