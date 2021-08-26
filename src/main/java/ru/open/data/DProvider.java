package ru.open.data;

import org.testng.annotations.DataProvider;

public class DProvider {
    @DataProvider(name = "urlData")
    public Object[][] urlData() {
        return new Object[][]{
                {"https://www.open.ru/"}
        };
    }

    @DataProvider(name = "currencyData")
    public Object[][] currencyData() {
        return new Object[][]{
                {"USD"},
                {"EUR"}
        };
    }
}