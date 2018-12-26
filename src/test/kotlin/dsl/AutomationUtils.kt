package dsl

import java.lang.IllegalStateException
import kotlin.reflect.KProperty

class TestContext {
    val objects = mutableMapOf<String, Any>()
}

@Suppress("UNCHECKED_CAST")
class  Ctx<T : Any>(private val ctx: TestContext) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return ctx.objects[property.name] as? T ?: throw IllegalStateException("${property.name} is not initialized")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        ctx.objects[property.name] = value
    }
}