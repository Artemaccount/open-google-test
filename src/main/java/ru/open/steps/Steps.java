package ru.open.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.open.pages.GooglePage;
import ru.open.pages.OpenPage;

public class Steps {
    private static WebDriver driver;
    private static GooglePage googlePage;

    public Steps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Шаг 1. Произвести поиск в Google по запросу {request}")
    public void findInGoogle(String request) {
        googlePage = new GooglePage(driver);
        googlePage.find(request);
    }

    @Step("Шаг 2. Проверить, что в результатах поиска есть ссылка на {url}")
    public void checkUrlInResults(String url) {
        googlePage.screenshot();
        Assert.assertTrue(googlePage.getSearchResult().stream()
                .anyMatch(s -> s.findElement(By.tagName("a"))
                        .getAttribute("href")
                        .equals(url)));
    }

    @Step("Шаг 3. Перейти на страницу {url} из поиска Google")
    public void goPage(String url) {
        googlePage.openFromResults(url);
    }

    @Step("Шаг 1. Проверить, что курс продажи {currency} больше курса покупки")
    public void checkCurrency(String currency) {
        OpenPage openPage = new OpenPage(driver);
        openPage.moveToTable();
        openPage.screenshot();
        double buyCurrency = openPage.getValue(currency, "Банк покупает");
        double sellCurrency = openPage.getValue(currency, "Банк продает");
        System.out.println("Покупает: " + buyCurrency + "/ Продает: " + sellCurrency);
        Assert.assertTrue(buyCurrency < sellCurrency);
    }
}
