package behavior;

public class Display {
    private Sensor sensor;

    public Display() {
        this.setSensor(new Sensor());
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public void textDisplay(String message) {
        System.out.println(message);
    }

    public String toString() {

        return new String();
    }

    public static void main(String[] args) {
        Display myDisplay = new Display();
        Sensor mySensor = new Sensor(myDisplay);
        mySensor.setSensor(19.5f, 55.2f, 10.5f);
    }
}