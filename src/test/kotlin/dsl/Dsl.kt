package dsl

fun suite(title: String, suiteBody: Suite.() -> Unit): Suite = Suite(title).apply(suiteBody)

class Suite(val title: String) {
    val _tests = mutableListOf<Test>()

    fun test(title: String, testBody: Test.() -> Unit = {}) = _tests.add(Test(title).apply(testBody))
    fun test(test: Test) = _tests.add(test)
}

fun test(title: String, testBody: Test.() -> Unit = {}): Test = Test(title).apply(testBody)

class Test(val title: String) {
    val _steps = mutableListOf<Step>()

    fun step(step: Step) = _steps.add(step)
    fun step(title: String, stepBody: Step.() -> Unit = {}) = _steps.add(Step(title).apply(stepBody))
}

fun step(title: String, stepBody: Step.() -> Unit = {}): Step = Step(title).apply(stepBody)

class Step(val title: String) {
    val _testData = mutableMapOf<String, TestDataProvider>()

    var _automated = false
    var _automationFunc: (testContext: TestContext) -> Unit = {}

    fun data(key: String, valueProvider: TestDataProvider) = _testData.put(key, valueProvider)
    fun data(key: String, value: String) = _testData.put(key, TextDataProvider(value))

    var description: String = ""
    var expectedResult: String = ""

    fun automated(code: (testContext: TestContext) -> Unit) {
        _automated = true
        _automationFunc = code
    }
}

