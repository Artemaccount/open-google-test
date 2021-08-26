package ru.open.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class GooglePage extends AbstractPage {

    @FindBy(xpath = "//input[@title='Поиск']")
    private WebElement googleSearchForm;

    @FindBy(xpath = "//input[@value='Поиск в Google']")
    private WebElement googleSearchButton;

    @FindBy(xpath = "//*[@class='g']")
    private List<WebElement> searchResult;


    public GooglePage(WebDriver driver) {
        super(driver);
        driver.get("https://www.google.com/");
        driver.manage().timeouts().pageLoadTimeout(20000, TimeUnit.MILLISECONDS);
    }


    public void find(String input) {
        googleSearchForm.sendKeys(input);
        googleSearchButton.click();
    }

    public void openFromResults(String url) {
        for (WebElement item : searchResult) {
            if (item.findElement(By.tagName("a"))
                    .getAttribute("href").equals(url)) {
                item.findElement(By.tagName("a")).click();
                break;
            } else {
                fail("Ссылка не найдена");
            }

        }
    }

    public List<WebElement> getSearchResult() {
        return searchResult;
    }
}