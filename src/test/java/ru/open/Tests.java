package ru.open;

import org.testng.annotations.Test;
import ru.open.data.DProvider;

public class Tests extends BaseTest {

    @Test(description = "Тест 1. В результатах поиска есть https://www.open.ru/",
            dataProvider = "urlData", dataProviderClass = DProvider.class,
            priority = 1)
    public void openInResultsTest(String url) {
        steps.findInGoogle("Открытие");
        steps.checkUrlInResults(url);
        steps.goPage(url);
    }

    @Test(description = "Тест 2. Курс продажи больше курса покупки",
            dataProvider = "currencyData", dataProviderClass = DProvider.class,
            priority = 2)
    public void buyLessThenSellTest(String currency) {
        steps.checkCurrency(currency);
    }
}
