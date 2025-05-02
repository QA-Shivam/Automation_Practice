package TestCases;

import com.google.zxing.*;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import static org.testng.Assert.*;

public class QRCodeDecoderTest {

    // Icons for visual feedback
    private static final String CHECK = "✅";
    private static final String WARNING = "⚠️";
    private static final String ERROR = "❌";
    private static final String INFO = "ℹ️";
    private static final String SCAN = "🔍";
    private static final String LINK = "🔗";
    private static final String CODE = "💻";

    private static final String TEST_URL = "https://eliasnogueira.github.io/selenium-read-qrcode/";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        printBanner("Initializing Test Session");
        System.out.println(INFO + " Opening URL: " + TEST_URL);
        driver.get(TEST_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println(INFO + " Closing browser session");
            driver.quit();
        }
    }


    public String decodeQRCode(Object qrCodeSource) throws IOException, NotFoundException {
        System.out.println(SCAN + " Starting QR code decoding process...");

        BufferedImage bufferedImage;
        String sourceType = "";

        if (qrCodeSource instanceof String) {
            String source = (String) qrCodeSource;

            if (source.startsWith("http")) {
                sourceType = "URL";
                System.out.println(LINK + " Detected URL source");
                System.out.println(INFO + " Downloading QR image from: " + source);
                bufferedImage = ImageIO.read(new URL(source));
            }
            else if (source.startsWith("data:image")) {
                sourceType = "Base64";
                System.out.println(CODE + " Detected Base64 encoded image");
                String base64Data = source.split(",")[1];
                byte[] imageBytes = Base64.getDecoder().decode(base64Data);
                bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            }
            else {
                System.out.println(ERROR + " Unsupported source format");
                throw new IllegalArgumentException("Unsupported QR code source format");
            }
        }
        else {
            System.out.println(ERROR + " Invalid source type");
            throw new IllegalArgumentException("QR code source must be String");
        }

        if (bufferedImage == null) {
            System.out.println(ERROR + " Failed to process " + sourceType + " image");
            throw new IOException("Failed to read QR code image");
        }

        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

        System.out.println(CHECK + " Successfully decoded " + sourceType + " QR code");
        return new MultiFormatReader().decode(binaryBitmap).getText();
    }

    @Test
    public void testURLBasedQRCodeDecoding() {
        printTestHeader("URL-based QR Code Decoding Test");

        try {
            String qrCodeURL = driver.findElement(By.id("qr")).getAttribute("src");
            System.out.println(INFO + " Found QR code element: "+ qrCodeURL);

            String decodedText = decodeQRCode(qrCodeURL);
            System.out.println(CHECK + " Decoded content: " + decodedText);

            assertNotNull(decodedText, "Decoded text should not be null");
            System.out.println(CHECK + " Test passed: URL QR code decoded successfully");

        } catch (Exception e) {
            System.out.println(ERROR + " Test failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testBase64QRCodeDecoding() {
        printTestHeader("Base64 QR Code Decoding Test");

        try {
            String qrCodeBase64 = driver.findElement(By.id("qr-base64")).getAttribute("src");
            System.out.println(INFO + " Found Base64 QR code element: "+ qrCodeBase64);
            System.out.println(CODE + " Source data: " + qrCodeBase64.substring(0, 30) + "...");

            String decodedText = decodeQRCode(qrCodeBase64);
            System.out.println(CHECK + " Decoded content: " + decodedText);

            assertNotNull(decodedText, "Decoded text should not be null");
            System.out.println(CHECK + " Test passed: Base64 QR code decoded successfully!");

        } catch (Exception e) {
            System.out.println(ERROR + " Test failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void printBanner(String message) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  " + SCAN + " " + message);
        System.out.println("=".repeat(50));
    }

    private void printTestHeader(String testName) {
        System.out.println("\n" + "-".repeat(50));
        System.out.println(WARNING + " TEST: " + testName);
        System.out.println("-".repeat(50));
    }
}