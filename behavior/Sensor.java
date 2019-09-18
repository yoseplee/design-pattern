package behavior;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import java.util.ArrayList;

public class Sensor implements Observable {
    private float temperature;
    private float humidity;
    private float pressure;
    private ArrayList<Display> listeners;
    private Display textDisplay;

    public Sensor() {
        listeners = new ArrayList<Display>();
        textDisplay = new Display();
    }

    public Sensor(Display mine) {
        this.textDisplay = mine;
    }

    public void setSensor(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        textDisplay.textDisplay(String.format("H: %f, P: %f, T:%f", humidity, pressure, temperature));
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeListener(InvalidationListener listener) {
        // TODO Auto-generated method stub

    }
}