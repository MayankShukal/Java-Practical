package practical6;

public class RemoteControl {

    interface Switchable {
        void on();
        void off();
        boolean isOn();

        default void toggle() {
            if (isOn()) {
                off();
            } else {
                on();
            }
        }
    }

    static class Fan implements Switchable {
        private boolean on = false;

        public void on() {
            on = true;
            System.out.println("Fan is ON");
        }

        public void off() {
            on = false;
            System.out.println("Fan is OFF");
        }

        public boolean isOn() {
            return on;
        }
    }

    static class Light implements Switchable {
        private boolean on = false;

        public void on() {
            on = true;
            System.out.println("Light is ON");
        }

        public void off() {
            on = false;
            System.out.println("Light is OFF");
        }

        public boolean isOn() {
            return on;
        }
    }

    @FunctionalInterface
    interface SwitchPolicy {
        boolean maySwitchOn(Switchable device, int hour);
    }

    static void checkAndSwitch(Switchable device, int hour,
                               SwitchPolicy policy) {
        if (policy.maySwitchOn(device, hour)) {
            device.on();
        } else {
            System.out.println(device.getClass().getSimpleName()
                    + " cannot be switched ON at " + hour + ":00");
        }
    }

    public static void main(String[] args) {

        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        System.out.println("Toggling devices:");

        for (Switchable device : devices) {
            device.toggle();
        }

        System.out.println("\nUsing anonymous class:");

        SwitchPolicy officeHours = new SwitchPolicy() {
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 18;
            }
        };

        checkAndSwitch(new Fan(), 10, officeHours);

        System.out.println("\nUsing lambda expression:");

        SwitchPolicy eveningPolicy =
                (device, hour) -> hour >= 7 && hour <= 22;

        checkAndSwitch(new Light(), 23, eveningPolicy);
    }
}
