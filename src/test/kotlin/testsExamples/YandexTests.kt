package testsExamples

import dsl.randomEmail
import dsl.randomNumber
import dsl.randomString
import dsl.suite

val yandexSuite =

    suite("Yandex acceptance tests") {


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
                automated { it.yandexSteps().openPage() }
            }
            step("Search 'Calculator'") {
                automated { it.yandexSteps().search("Калькулятор") }
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
                automated { it.yandexSteps().closeBrowser() }
                expectedResult = "Browser closed"
            }
        }
    }