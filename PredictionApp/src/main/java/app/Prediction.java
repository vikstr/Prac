package app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class Prediction {
    @Value("${app.address.dollars}")
    String dollars;
    @Value("${app.address.weather}")
    String weather;
    public Double predict(){
        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<String> rate = restTemplate.getForEntity(dollars, String.class);
        ResponseEntity<String> temperature = restTemplate.getForEntity(weather, String.class);

        Double y = Double.parseDouble(rate.getBody());
        Double x = Double.parseDouble(temperature.getBody());
        Double prediction = y*(x - x/y)/x;
        return prediction;
    }

}
