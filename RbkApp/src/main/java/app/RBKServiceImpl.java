package app;

import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RBKServiceImpl {

    public RBKServiceImpl() {};

    public String getResponse(int days) {
        RestTemplate restTemplate = new RestTemplate();
        String info;
        String Url = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers=USD000000TOD&separator=TAB&data_format=BROWSER&lastdays=" + days;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(Url + "/1", String.class);
            System.out.println(response.getStatusCode());
            info = response.getBody();
        } catch (Exception e) {
            info = "80.8";
        }

        return info;
    }

    public List<Double> getRatesArray(int days) {
        List<Double> ans = new ArrayList<>();

        String body = getResponse(days);

        String[] lines = body.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            String[] splits = lines[i].split("\\s+");
            String curValue = splits[splits.length - 1];
            ans.add(Double.parseDouble(curValue));
        }

        return ans;
    }

    public double getMaxFromArray(int days) {
        List<Double> ans = getRatesArray(days);

        double max = Double.MIN_VALUE;
        for (Double d : ans) {
            max = Math.max(max,d);
        }
        return max;
    }

    public double getMaxThroughDates(List<Double> dates) {
        double max = Double.MIN_VALUE;
        for (Double d : dates) {
            max = Math.max(max,d);
        }
        return max;
    }
}
