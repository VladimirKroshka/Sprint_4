import org.junit.After;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import yandex.praktikum.MainPage;
import yandex.praktikum.OrderScooterFlowPage;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class TestCase {

    // Этот класс будет использоваться для запуска тестов на проверку вопросов FAQ
    @RunWith(Parameterized.class)
    public static class QuestionsAboutGeneralTest {
        private String browser;
        private String expectedText;
        private int index;
        private WebDriver driver;

        public QuestionsAboutGeneralTest(String browser, String expectedText, int index) {
            this.browser = browser;
            this.expectedText = expectedText;
            this.index = index;
        }

        @Parameterized.Parameters
        public static Iterable<Object[]> getQuestionsAboutGeneral() {
            return Arrays.asList(new Object[][]{
                    {"chrome", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                    {"chrome", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                    {"chrome", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                    {"chrome", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                    {"chrome", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                    {"chrome", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                    {"chrome", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                    {"chrome", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
            });
        }

        @Test
        public void testQuestionsAboutGeneral() {
            setUpBrowser(browser);
            MainPage objMainPage = new MainPage(driver);
            // Закрываем кнопку с куками, потому что они перекрывают остальные элементы
            objMainPage.clickCookieButton();
            // Найти все элементы списка
            List<WebElement> items = driver.findElements(objMainPage.listFAQ);
            // Проверить количество элементов и ожидаемых текстов
            assertEquals("Количество элементов и ожидаемых текстов не совпадает", items.size(), 8);
            // Проверить конкретный элемент списка
            WebElement item = items.get(index);
            // Кликнуть на элемент, чтобы раскрыть его
            item.click();
            // Ждем, когда прогрузится элемент
            WebElement description = new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='accordion__panel-" + index + "']")));
            // Сравнить текст в элементе с ожидаемым текстом
            assertEquals("Текст в выпадающем описании не совпадает", expectedText, description.getText());
        }

        //После прогона теста закрываем браузер
        @After
        public void teardown() {
            if (driver != null) {
                driver.quit();
            }
        }

        //метод инициализирует браузер, в котором будем запускать тесты
        public void setUpBrowser(String browser) {
            if (browser.equals("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new ChromeDriver(options);
                driver.get("https://qa-scooter.praktikum-services.ru");
            } else if (browser.equals("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new FirefoxDriver(options);
            }
        }
    }

    // Этот класс будет использоваться для запуска тестов на оформление заказа самоката
    @RunWith(Parameterized.class)
    public static class OrderScooterTest {
        private String browser;
        private boolean useButtonInUpSite;
        private String firstName;
        private String secondName;
        private String address;
        private String stationMetro;
        private String clientPhoneNumber;
        private String dayDelivery;
        private String colorScooter;
        private String commentForCourier;
        private WebDriver driver;

        public OrderScooterTest(String browser, boolean useButtonInUpSite, String firstName, String secondName, String address,
                                String stationMetro, String clientPhoneNumber, String dayDelivery, String colorScooter, String commentForCourier) {
            this.browser = browser;
            this.useButtonInUpSite = useButtonInUpSite;
            this.firstName = firstName;
            this.secondName = secondName;
            this.address = address;
            this.stationMetro = stationMetro;
            this.clientPhoneNumber = clientPhoneNumber;
            this.dayDelivery = dayDelivery;
            this.colorScooter = colorScooter;
            this.commentForCourier = commentForCourier;
        }

        @Parameterized.Parameters
        public static Iterable<Object[]> getDataForOrder() {
            return Arrays.asList(new Object[][]{
                    {"chrome", true, "Владимир", "Крошка", "В тьму таракань", "Бульвар Рокоссовского", "80000000000",
                            "четверо суток", "чёрный жемчуг", "Хочу кататься"},
                    {"firefox", false, "Петя", "Петушков", "В 146 школу", "Серпуховская", "80000000001",
                            "семеро суток", "серая безысходность", "Двоих выдержит?"}
            });
        }

        @Test
        public void orderScooter() {
            setUpBrowser(browser);
            MainPage objMainPage = new MainPage(driver);
            OrderScooterFlowPage objFlowPage = new OrderScooterFlowPage(driver);
            // Закрываем кнопку с куками, потому что они перекрывают остальные элементы
            objMainPage.clickCookieButton();
            // Первое окно для ввода данных
            objMainPage.clickOrderButton(useButtonInUpSite);
            objFlowPage.fillFirstName(firstName);
            objFlowPage.fillSecondName(secondName);
            objFlowPage.fillAddress(address);
            objFlowPage.selectStationMetro(stationMetro);
            objFlowPage.fillClientNumber(clientPhoneNumber);
            objFlowPage.clickNextButton();
            // Второе окно для ввода данных
            // Берем сегодняшний день + 1
            java.time.LocalDate deliveryDate = java.time.LocalDate.now().plusDays(1);
            objFlowPage.fillDateDelivery(deliveryDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            objFlowPage.selectDayDelivery(dayDelivery);
            objFlowPage.selectColorScooter(colorScooter);
            objFlowPage.fillCommentForCourier(commentForCourier);
            // Подтверждаем оформление заказа
            objFlowPage.clickOrderButton();
            objFlowPage.clickYesButton();
            // Проверяем, что получили окно с успехом
            objFlowPage.verifySuccessOrderWindow();
        }

        //После прогона теста закрываем браузер
        @After
        public void teardown() {
            if (driver != null) {
                driver.quit();
            }
        }

        //метод инициализирует браузер, в котором будем запускать тесты
        public void setUpBrowser(String browser) {
            if (browser.equals("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new ChromeDriver(options);
                driver.get("https://qa-scooter.praktikum-services.ru");
            } else if (browser.equals("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new FirefoxDriver(options);
            }
        }
    }
}
