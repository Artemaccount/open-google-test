package ru.open.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class OpenPage extends AbstractPage {

    @FindBy(xpath = "//tr[@class='main-page-exchange__table-header']//td")
    private List<WebElement> tableHeaders;

    @FindBy(xpath = "//tr[@class='main-page-exchange__row main-page-exchange__row--with-border']")
    private List<WebElement> linesList;

    @FindBy(xpath = "//div[@class='main-page-exchange main-page-info__card']")
    private WebElement valuesTable;

    private static final String OPEN_URL = "https://www.open.ru/";

    public OpenPage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().equals(OPEN_URL)) {
            driver.get(OPEN_URL);
        }
        driver.manage().timeouts().pageLoadTimeout(20000, TimeUnit.MILLISECONDS);
    }

    public void moveToTable() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", valuesTable);
    }


    public List<Map<String, String>> getCollectExchangeRates() {
        List<Map<String, String>> collectExchangeRates = new ArrayList<>();
        for (int i = 0; i < linesList.size(); ++i) {
            Map<String, String> collectRow = new HashMap<>();
            for (int j = 0; j < tableHeaders.size(); ++j) {
                collectRow.put(
                        tableHeaders.get(j).getText(),
                        linesList.get(i).findElement(By.xpath("./td[" + (j + 1) + "]")).getText()
                );
            }
            collectExchangeRates.add(collectRow);
        }
        return collectExchangeRates;
    }

    public double getValue(String currency, String operationType) {
        OpenPage openPage = new OpenPage(driver);
        return Double.parseDouble(openPage.getCollectExchangeRates().stream()
                .filter(s -> s.get("Валюта обмена").contains(currency))
                .findFirst()
                .get().get(operationType).replace(",", "."));
    }
}