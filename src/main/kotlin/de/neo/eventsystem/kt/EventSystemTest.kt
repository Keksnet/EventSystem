package de.neo.eventsystem.kt

fun main() {
    val eSys = EventSystem()
    eSys.registerListener(TestListener())
    eSys.registerListener(LowTestListener())
    eSys.executeEvent(TestEvent("Test 1"))
    Thread.sleep(5000)
    eSys.executeEvent(TestEvent("Test 2"))
}

class TestEvent(val msg: String) : Event() {
}

class TestListener {

    @Listener
    fun onTest(event: TestEvent) {
        println("Neue Nachricht: ${event.msg}")
    }

}

class LowTestListener {

    @Listener
    fun onTest(event: TestEvent) {
        println("Neue Nachricht (low): ${event.msg}")
    }

}