package testsExamples

import dsl.Ctx
import dsl.TestContext
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

fun TestContext.yandexSteps() = YandexAutomatedSteps(this)

class YandexAutomatedSteps(testContext: TestContext) {

    private var webDriver: WebDriver by Ctx(testContext)

    fun openPage() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe")
        webDriver = ChromeDriver()
        webDriver.get("https://yandex.ru")
    }

    fun search(text: String) = with(webDriver) {
        findElement(By.id("text")).sendKeys(text)
        findElement(By.className("search2__button")).click()
    }

    fun closeBrowser() {
        webDriver.quit()
    }
}