package de.neo.eventsystem.kt

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Listener(val priority: EventPriority = EventPriority.NORMAL)
