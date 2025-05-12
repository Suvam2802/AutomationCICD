package rahulshettyacademy.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

public class SubmitOrderStandAlone {

    public static void main(String[] args) {
        String productName = "ZARA COAT 3";
        String ConfirmationMessage = "THANKYOU FOR THE ORDER.";

        // WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Timeout - Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Login
        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().window().maximize();

        // Provide UserName and password
        driver.findElement(By.id("userEmail")).sendKeys("leyvingsuvam7488@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Testing@2802");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream()
            .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
            .findFirst()
            .orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

        // Validate product added to the cart
        List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartproducts.stream()
            .anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));

        Assert.assertTrue(match);

        // Click on Checkout
        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions act = new Actions(driver);
        act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit ")).click();

        String confirmationTxt = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertTrue(confirmationTxt.equalsIgnoreCase(ConfirmationMessage));

        // Close driver
        driver.quit();
    }
}
