package Data;

/**
 * Created by Дмитрий on 27.04.2016.
 */
public class Data {
    public String city = "";
    public String temp = "";
    public String pressure = "";
    public String weather = "";
    public String weatherDescription = "";
    public String windSpeed = "";
    public String date = "";

    @Override
    public String toString() {
        String out = "city " + city + "\n";
        out += "weather " + weather + "\n";
        out += "description " + weatherDescription + "\n";
        out += "temp " + temp + "\n";
        out += "pressure " + pressure + "\n";
        out += "wind.speed " + windSpeed + "\n";
        out += "time " + date + "\n\n";
        return out;
    }
}
