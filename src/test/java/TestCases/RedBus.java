package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class RedBus {
    public  static WebDriver driver;
    public static  String base_url="https://www.redbus.in/";
    WebDriverWait wait;
    @BeforeMethod
    public void setup(){
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--start-maximized");
        driver= new ChromeDriver(options);
        driver.get(base_url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @AfterMethod
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }
    @Test
    public void Test_Scenario_1() {
        // Red Bus Search Functionality
        By FromDropDownLocator=By.xpath("//div[.='From']/ancestor::div[contains(@class,'placeHolder')]");
        WebElement FromDropDown= wait.until(ExpectedConditions.visibilityOfElementLocated(FromDropDownLocator));
        FromDropDown.click();
        By searchSuggestionsLocator=By.cssSelector("[aria-label='Search suggestions']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionsLocator));
        WebElement searchFromSource= driver.switchTo().activeElement();
        searchFromSource.sendKeys("Mumbai");
        List<WebElement> locations = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class,'searchCategory')]"), 2)).get(0).findElements(By.xpath(".//div[contains(@class,'listHeader')]"));
        // Log all locations
        locations.forEach(loc -> System.out.println("Source Location: " + loc.getText()));
        // Then filter & click "Mumbai"
        locations.stream()
                .filter(loc -> loc.getText().equalsIgnoreCase("Mumbai"))
                .findFirst()
                .ifPresent(loc -> {
                    String locaton=loc.getText();
                    loc.click();
                    System.out.println("Clicked on location: "+locaton);
                });

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionsLocator));
        WebElement searchFromDes= driver.switchTo().activeElement();
        searchFromDes.sendKeys("Pune");
        locations = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class,'searchCategory')]"), 2)).get(0).findElements(By.xpath(".//div[contains(@class,'listHeader')]"));
        // Log all locations
        locations.forEach(loc -> System.out.println("Destination Location: " + loc.getText()));
        // Then filter & click "Pune"
        locations.stream()
                .filter(loc -> loc.getText().equalsIgnoreCase("Pune"))
                .findFirst()
                .ifPresent(loc -> {
                    String locaton=loc.getText();
                    loc.click();
                    System.out.println("Clicked on location: "+locaton);
                });
        By searchBusesBtn= By.xpath("//button[.='Search buses']");
        WebElement searchBuses= wait.until(ExpectedConditions.elementToBeClickable(searchBusesBtn));
        searchBuses.click();
        By primoFilterLocator= By.cssSelector("[aria-label*='Primo Bus']");
        WebElement primoFilter= wait.until(ExpectedConditions.visibilityOfElementLocated(primoFilterLocator));
        primoFilter.click();
        WebElement eveningFilter=driver.findElement(By.cssSelector("[aria-label*='18:00']"));
        eveningFilter.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        System.out.println();
        By busTitlesLocator = By.xpath("//div[contains(@class,'busTitleWrapper')]");
        By endOfListLocator = By.xpath("//div[contains(@class,'endOfResult')]");
        while (true) {
            // Get visible rows
            List<WebElement> rowList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(busTitlesLocator));
            // Check if "End of list" is visible
            List<WebElement> endElements = driver.findElements(endOfListLocator);
            if (!endElements.isEmpty() && endElements.get(0).isDisplayed()) {
                String text = endElements.get(0).getText();
                if (text != null && text.contains("End of list")) {
                    System.out.println("Reached end of list: " + text);
                    break;
                }
            }
            // Scroll to near the bottom (last - 3 element for lazy loading trigger)
            WebElement lastVisible = rowList.get(rowList.size() - 3);
            js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'end'})", lastVisible);
            System.out.println("Scrolled to row: " + lastVisible.getText());
        }
        List<WebElement> busTitles= wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(busTitlesLocator,0));
        System.out.println("*==========Buses Titles=========*");
        busTitles.stream().map(WebElement::getText).filter(title->!title.isBlank()).forEach(System.out::println);
        System.out.println("Total Buses: "+ busTitles.size());
    }
}
