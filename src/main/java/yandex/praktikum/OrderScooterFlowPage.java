package yandex.praktikum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Класс страницы оформления заказа
public class OrderScooterFlowPage {
    private final WebDriver driver;

    // Кнопка "Далее"
    private By buttonNext = By.xpath("//button[text()='Далее']");
    // Кнопка "Заказать"
    private By buttonOrder = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    // Кнопка "Да" на экране оформления заказа
    private By buttonYes = By.xpath("//button[contains(text(), 'Да')]");
    // Локаторы полей анкеты оформления
    private By firstNameField = By.xpath("//*[@placeholder='* Имя']");
    private By secondNameField = By.xpath("//*[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//*[@placeholder='* Адрес: куда привезти заказ']");
    private By stationMetro = By.xpath("//*[@placeholder='* Станция метро']");
    private By clientNumberField = By.xpath("//*[@placeholder='* Телефон: на него позвонит курьер']");
    private By dateDeliveryField = By.xpath("//*[@placeholder='* Когда привезти самокат']");
    private By commentForCourierField = By.xpath("//*[@placeholder='Комментарий для курьера']");
    private By successOrderText = By.className("Order_ModalHeader__3FDaJ");

    public OrderScooterFlowPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstName(String firstName) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void fillSecondName(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }

    public void fillAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void selectStationMetro(String station) {
        driver.findElement(stationMetro).sendKeys(station + Keys.ARROW_DOWN + Keys.ENTER);
    }

    public void fillClientNumber(String clientNumber) {
        driver.findElement(clientNumberField).sendKeys(clientNumber);
    }

    public void clickNextButton() {
        driver.findElement(buttonNext).click();
    }

    public void fillDateDelivery(String date) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(dateDeliveryField));
        driver.findElement(dateDeliveryField).sendKeys(date + Keys.ENTER);
    }

    public void selectDayDelivery(String dayPeriod) {
        driver.findElement(By.className("Dropdown-placeholder")).click();
        driver.findElement(By.xpath("//div[@class='Dropdown-option' and text()='" + dayPeriod + "']")).click();
    }

    public void selectColorScooter(String color) {
        driver.findElement(By.xpath("//label[@for='black' and contains(text(),'" + color + "')]")).click();
    }

    public void fillCommentForCourier(String comment) {
        driver.findElement(commentForCourierField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(buttonOrder).click();
    }

    public void clickYesButton() {
        driver.findElement(buttonYes).click();
    }

    public void verifySuccessOrderWindow() {
        Assert.assertEquals("Окно с данными о заказе не найдено", "Заказ оформлен", driver.findElement(successOrderText).getText());
    }
}
