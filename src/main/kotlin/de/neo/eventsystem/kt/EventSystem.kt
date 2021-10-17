package de.neo.eventsystem.kt

import java.lang.reflect.Method

class EventSystem {

    private val registeredListener = HashMap<Class<Event>, HashMap<EventPriority, HashMap<Any, Method>>>()

    fun registerListener(listenerClass: Any) {
        for(method in listenerClass.javaClass.methods) {
            if(method.annotations.isNotEmpty()) {
                val listener = method.getAnnotation(Listener::class.java)
                if(method.parameterCount == 1) {
                    val eventClass = method.parameterTypes[0] as Class<Event>
                    val list = registeredListener[eventClass] ?: HashMap()
                    val set = list[listener.priority] ?: HashMap()
                    set[listenerClass] = method
                    list[listener.priority] = set
                    registeredListener[eventClass] = list
                }
            }
        }
    }

    fun executeEvent(event: Event) {
        val list = registeredListener[event.javaClass] ?: HashMap()
        list[EventPriority.LOWEST]?.forEach { it.value.invoke(it.key, event) }
        list[EventPriority.LOW]?.forEach { it.value.invoke(it.key, event) }
        list[EventPriority.NORMAL]?.forEach { it.value.invoke(it.key, event) }
        list[EventPriority.HIGH]?.forEach { it.value.invoke(it.key, event) }
        list[EventPriority.HIGHEST]?.forEach { it.value.invoke(it.key, event) }
    }
}