package behavior;

import java.util.Observable;

public class Sensor extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public void setSensor(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        
        this.notifyObservers((Object)String.format("H: %f, P: %f, T:%f", getHumidity(), getPressure(), getTemperature()));
        // this.notifyObservers();
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
}