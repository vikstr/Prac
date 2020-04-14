package app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class Prediction {
    public Double predict(){
        RestTemplate restTemplate = new RestTemplate();

        String dollars = "http://rbc:8060/rbk";
        String weather = "http://weather:8070/weather";

        ResponseEntity<String> rate = restTemplate.getForEntity(dollars, String.class);
        ResponseEntity<String> temperature = restTemplate.getForEntity(weather, String.class);

        Double y = Double.parseDouble(rate.getBody());
        Double x = Double.parseDouble(temperature.getBody());
        Double prediction = y*(x - x/y)/x;
        return prediction;
    }

}
