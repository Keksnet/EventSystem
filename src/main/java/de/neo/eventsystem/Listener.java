package de.neo.eventsystem;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Listener {

    EventPriority priority() default EventPriority.NORMAL;

}
