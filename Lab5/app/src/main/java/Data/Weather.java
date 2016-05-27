package Data;

/**
 * Created by Дмитрий on 27.04.2016.
 */
public class Weather {
    private City city = new City();

    private String temperature;
    private String temperatureMax;
    private String temperatureMin;

    private String humidity;
    private String humidityUnit;

    private String pressure;
    private String pressureUnit;

    private Wind wind = new Wind();

    private String cloudsValue;
    private String cloudsName;

    private String visibilityValue;

    private String precipitationValue;

    private String weatherNumber;
    private String weatherValue;
    private String weatherIcon;

    private String lastUpdate;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getHumidityUnit() {
        return humidityUnit;
    }

    public void setHumidityUnit(String humidityUnit) {
        this.humidityUnit = humidityUnit;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getPressureUnit() {
        return pressureUnit;
    }

    public void setPressureUnit(String pressureUnit) {
        this.pressureUnit = pressureUnit;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getCloudsValue() {
        return cloudsValue;
    }

    public void setCloudsValue(String cloudsValue) {
        this.cloudsValue = cloudsValue;
    }

    public String getCloudsName() {
        return cloudsName;
    }

    public void setCloudsName(String cloudsName) {
        this.cloudsName = cloudsName;
    }

    public String getVisibilityValue() {
        return visibilityValue;
    }

    public void setVisibilityValue(String visibilityValue) {
        this.visibilityValue = visibilityValue;
    }

    public String getPrecipitationValue() {
        return precipitationValue;
    }

    public void setPrecipitationValue(String precipitationValue) {
        this.precipitationValue = precipitationValue;
    }

    public String getWeatherNumber() {
        return weatherNumber;
    }

    public void setWeatherNumber(String weatherNumber) {
        this.weatherNumber = weatherNumber;
    }

    public String getWeatherValue() {
        return weatherValue;
    }

    public void setWeatherValue(String weatherValue) {
        this.weatherValue = weatherValue;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        String out = "Weather: \n";
        out += "City: " + city.getName() + "\n";
        out += "Country: " + city.getCountry() + "\n";
        out += "Temperature: " + temperature + "\n";
        out += "Humidity: " + humidity + humidityUnit + "\n";
        out += "Pressure: " + pressure + pressureUnit + "\n";
        out += "Wind: " + wind.getName() + " Direction: " + wind.getDirectionName() + "\n";
        out += "Clouds: " + cloudsName;
        return out;
    }
}
