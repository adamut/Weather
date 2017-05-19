package com.weather.weather.model;


public class WeatherJSON {

    private String name;
    private String speed;
    private String pressure;
    private String humidity;
    private String mainWeather;
    private String description;
    private String id;
    private String day;
    private String min;
    private String max;
    private int idIcon;

    public WeatherJSON(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public int getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(int idIcon) {
        this.idIcon = idIcon;
    }

    public WeatherJSON(String name, String speed, String pressure, String humidity, String mainWeather, String description, String id, String day, String min, String max, int idIcon) {

        this.name = name;
        this.speed = speed;
        this.pressure = pressure;
        this.humidity = humidity;
        this.mainWeather = mainWeather;
        this.description = description;
        this.id = id;
        this.day = day;
        this.min = min;
        this.max = max;
        this.idIcon = idIcon;
    }
}
