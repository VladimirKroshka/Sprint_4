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

    // Массив ожидаемых текстов в выпадающем описании
    public String[] expectedTexts = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области.",
    };

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
