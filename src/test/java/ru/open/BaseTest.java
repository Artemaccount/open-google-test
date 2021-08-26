package ru.open;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.open.steps.Steps;

import java.util.List;

public class BaseTest {
    protected Steps steps;
    protected WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(List.of("start-maximized", "disable-infobars", "--no-sandbox"));
        try {
            driver = new ChromeDriver(options);
            steps = new Steps(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }
}
