package qa.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddItemToCard {

    private WebDriver driver;
    private WebDriver driver1;

    @BeforeTest
    public void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        driver1 = new FirefoxDriver();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();driver1.quit();
    }

    @DataProvider(name = "CorrectloginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "CorrectloginData")
    public void loginSuccess(String username, String password) {
        driver.get("https://www.saucedemo.com/");

        WebElement user_name = driver.findElement(By.id("user-name"));
        user_name.click();
        user_name.sendKeys(username);

        WebElement password_input = driver.findElement(By.id("password"));
        password_input.click();
        password_input.sendKeys(password);

        WebElement login_button = driver.findElement(By.className("btn_action"));
        login_button.click();

        WebElement add_item_button = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        add_item_button.click();

        WebElement add_second_Item = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        add_second_Item.click();



        WebElement showBUtton = driver.findElement(By.className("shopping_cart_badge"));

        Assert.assertTrue(showBUtton.isDisplayed());
    }
}
