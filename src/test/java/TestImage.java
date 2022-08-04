import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class TestImage {

    WebDriver driver;

    @BeforeClass
    public static void setUp () {
        chromedriver().setup();
    }

    @Before
    public void beforeTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.google.com/");
    }

    @Test
    public void checkImages() {
        WebElement searchBar = driver.findElement(By.xpath("//input[contains (@name, 'q')]"));
        WebElement searchButton = driver.findElement(By.xpath("//div[@class='CqAVzb lJ9FBc']//input[contains (@class, 'gNO89b')]"));

        searchBar.sendKeys("cats");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        WebElement picturesTab = driver.findElement(By.xpath("//a[@data-hveid='CAIQAw']"));

        picturesTab.click();

        List<WebElement> pictures = driver.findElements(By.xpath("//div[@class='bRMDJf islir']/img"));

        for (WebElement el: pictures
        ) {
            System.out.println(el.getTagName());
            Assert.assertEquals(el.getTagName(), "img");
        }
    }


}
