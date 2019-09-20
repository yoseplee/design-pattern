package behavior;

import java.util.Observable;
import java.util.Observer;

public class Display implements Observer {

    public Display() {
    }

    public String toString() {

        return new String();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println((String)arg);
    }

    public static void main(String[] args) {
        
        Display myDisplay = new Display();
        Sensor mySensor = new Sensor();
        mySensor.addObserver(myDisplay);

        mySensor.setSensor(19.5f, 55.2f, 10.5f);
    }
}