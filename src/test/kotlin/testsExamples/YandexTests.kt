package testsExamples

import dsl.*
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

val yandexSuite =

    suite("Yandex acceptance tests") {

        test("WebDriver") {
            step("Init") {

            }
        }

        test("Login with wrong credentials") {
            step("Open browser in incognito mode") {
                description = "use chrome browser"
                expectedResult = "Browsed opened"
            }
            step("Type 'url' and press enter") {
                data("url", "https://yandex.ru")
                expectedResult = "Yandex main page loaded"
            }
            step("Press login button") {
                expectedResult = "Login form opened"
            }
            step("Type 'login' and 'password'. Press 'enter'") {
                data("login", randomEmail)
                data("password", randomString)
                expectedResult = "login failed"
            }
        }


        test("Check calculator") {
            step("Check that you have 'chromdriver'") {
                description =
                    "download from https://chromedriver.storage.googleapis.com/index.html?path=2.45/ to working directory"
                expectedResult = "You have 'chromdriver'"
            }
            step("Open browser") {
                automated<YandexAutomatedSteps> { openPage() }
            }
            step("Search 'Calculator'") {
                automated<YandexAutomatedSteps> { search("Калькулятор") }
            }
            step("Check that 'Calculator appeared'") {
                expectedResult = "You can see calculator in 'Search result page'"
            }
            step("Calculate values") {
                description = "type 'A + B ='"
                data("A", randomNumber)
                data("B", randomNumber)
                expectedResult = "Result is correct"
            }
            step("Close browser") {
                automated<YandexAutomatedSteps> { closeBrowser() }
                expectedResult = "Browser closed"
            }
        }
    }



class WebDriverInit : Suite("WebDriver test") {

    class WebDriverCtx(testContext: TestContext) {
        var webDriver by Ctx<WebDriver>(testContext)
    }

    init {
        test("WebDriver") {
            step("Init") {
                automated<WebDriverCtx> {
                    webDriver = ChromeDriver()
                }
            }
            step("Open url") {
                automated<WebDriverCtx> {
                    webDriver.get("https://yandex.ru")
                }
            }
        }
    }
}



