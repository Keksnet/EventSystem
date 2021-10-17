package de.neo.eventsystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class EventSystem {

    private HashMap<Class<Event>, HashMap<EventPriority, HashMap<Object, Method>>> registeredListener;

    public EventSystem() {
        registeredListener = new HashMap<>();
    }

    public void registerListener(Object listenerClass) {
        for(Method method : listenerClass.getClass().getDeclaredMethods()) {
            Listener listener = method.getAnnotation(Listener.class);
            if(listener == null) {
                System.out.println(method.getName() + ": NULL");
                continue;
            }
            if(method.getParameterCount() == 1) {
                Class<Event> eventClass = (Class<Event>) method.getGenericParameterTypes()[0];
                HashMap<EventPriority, HashMap<Object, Method>> list = registeredListener.getOrDefault(eventClass,
                        new HashMap<>());
                HashMap<Object, Method> set = list.getOrDefault(listener.priority(), new HashMap<>());
                set.put(listenerClass, method);
                list.put(listener.priority(), set);
                registeredListener.put(eventClass, list);
            }
        }
    }

    public void executeEvent(Event event) {
        HashMap<EventPriority, HashMap<Object, Method>> list = registeredListener.getOrDefault(event.getClass(), new HashMap<>());
        list.getOrDefault(EventPriority.LOWEST, new HashMap<>()).forEach((k, v) -> {
            try {
                v.invoke(k, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        list.getOrDefault(EventPriority.LOW, new HashMap<>()).forEach((k, v) -> {
            try {
                v.invoke(k, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        list.getOrDefault(EventPriority.NORMAL, new HashMap<>()).forEach((k, v) -> {
            try {
                v.invoke(k, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        list.getOrDefault(EventPriority.HIGH, new HashMap<>()).forEach((k, v) -> {
            try {
                v.invoke(k, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        list.getOrDefault(EventPriority.HIGHEST, new HashMap<>()).forEach((k, v) -> {
            try {
                v.invoke(k, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}
