package assistEmulator

import dsl.Suite
import dsl.TestContext
import testsExamples.yandexSuite

class AssistEmulator {
    fun emulateWork(suite: Suite) {
        println("Started session '${suite.title}'")

        for (test in suite._tests) {
            println("Test: " + test.title)
            val context = TestContext()
            for (step in test._steps) {
                println(" Step: ${step.title}")
                println("   Description: ${step.description}")
                step._testData.forEach { key, valueFun ->
                    println("       $key: ${valueFun()}")
                }
                println("   Expected: ${step.expectedResult}")
                println("      ......processing")
                if (step._automated) {
                    println("      ......automatic")
                    step._automationFunc(context)
                } else {
                    println("      ......manually")
                    Thread.sleep(1000)
                }
                println("      ......passed!")
            }
        }
    }
}

fun main(args: Array<String>) {
    AssistEmulator().emulateWork(yandexSuite)
}