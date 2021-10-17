package de.neo.eventsystem;

public class EventSystemTest {

    public static void main(String[] args) throws InterruptedException {
        EventSystem eSys = new EventSystem();
        eSys.registerListener(new TestListener());
        eSys.registerListener(new LowTestListener());
        eSys.executeEvent(new TestEvent("Test 1"));
        Thread.sleep(5000);
        eSys.executeEvent(new TestEvent("Test 2"));
    }
}

class TestEvent extends Event {

    private String msg;

    public TestEvent(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return this.msg;
    }
}

class TestListener {

    @Listener
    public void onTest(TestEvent event) {
        System.out.println("Neue Nachricht: " + event.getMessage());
    }

}

class LowTestListener {

    @Listener
    public void onTest(TestEvent event) {
        System.out.println("Neue Nachricht (low): " + event.getMessage());
    }

}