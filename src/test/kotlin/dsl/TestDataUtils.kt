package dsl

import kotlin.random.Random

interface TestDataProvider : () -> String

class TextDataProvider(private val text: String) : TestDataProvider {
    override fun invoke(): String {
        return text
    }
}


// standard test data generators

val randomEmail = object : TestDataProvider {
    override fun invoke(): String {
        return "myLogin_${System.currentTimeMillis()}@yandex.ru"
    }
}

val randomString = object : TestDataProvider {
    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    override fun invoke(): String {
        return (1..9)
            .map { i -> Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("");
    }
}

val randomNumber = object : TestDataProvider {
    override fun invoke(): String {
        return Random.nextInt(0, 10).toString()
    }
}