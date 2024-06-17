package yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс главной страницы
public class MainPage {
    private final WebDriver driver;

    // Локатор кнопки куки
    private By cookieButton = By.id("rcc-confirm-button");
    // Локатор списка
    public By listFAQ = By.className("accordion__button");
    // Кнопка "Заказать" вверху страницы
    private By orderButtonInUpSite = By.className("Button_Button__ra12g");
    // Кнопка "Заказать" в середине страницы, включение нужно, потому что в полноэкранном режиме меняется ее аргумент class *rage*
    private By orderButtonInMiddleSite = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButton(boolean useButtonInUpSite) {
        if (useButtonInUpSite) {
            // Используем кнопку "Заказать" вверху
            driver.findElement(orderButtonInUpSite).click();
        } else {
            // Используем кнопку "Заказать" в основном теле сайта
            driver.findElement(orderButtonInMiddleSite).click();
        }
    }

    // Метод для клика по кнопке принятия куки
    public void clickCookieButton() {
        try {
            driver.findElement(cookieButton).click();
        } catch (Exception e) {
            // Если элемент не найден, ничего не делаем
        }
    }
}
