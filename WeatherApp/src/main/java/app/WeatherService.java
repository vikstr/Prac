package app;

import app.JsonParsing.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

@Service
public class WeatherService {

    final String TOKEN = "ac1830efeff59c748d212052f27d49aa";


    public String getResponse(String time) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.darksky.net/forecast/" + TOKEN + "/55.3601,37.5589" + time + "?exclude=currently&units=auto";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new IllegalStateException();
        }
        return response.getBody();
    }

    public Double parseResponse(String responseString) throws IOException {
        String info = getResponse(responseString);
        JsonParser jsonGetter = new JsonParser(info);
        Double curTemp = jsonGetter.getTemperature();
        return curTemp;
    }

    public ArrayList<Double> getTemperatureForLastMonth() throws IOException {
        ArrayList<Double> temps = new ArrayList<>();

        long currentDayInSec = Calendar.getInstance().getTimeInMillis() / 1000;
        long oneDayInSec = 24 * 60 * 60L;

        for (int i = 0; i < 30; i++) {
            long curDateSec = currentDayInSec - i * oneDayInSec;
            Double curTemp = parseResponse("" + curDateSec);
            temps.add(curTemp);
        }

        return temps;
    }

    public ArrayList<Double> getTemperatureForLastDays(int days) throws IOException {
        ArrayList<Double> temps = new ArrayList<>();

        long currentDayInSec = Calendar.getInstance().getTimeInMillis() / 1000;
        long oneDayInSec = 24 * 60 * 60L;

        for (int i = 0; i < days; i++) {
            long curDateSec = currentDayInSec - i * oneDayInSec;
            Double curTemp = parseResponse("" + curDateSec);
            temps.add(curTemp);
        }

        return temps;
    }
}
