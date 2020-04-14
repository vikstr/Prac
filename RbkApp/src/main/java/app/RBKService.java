package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;


import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class RBKService {

    @Bean
    public RestTemplate restBean() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restBean;

    @Autowired
    private DbWorker dbWorker;

    private Optional<Rate> rate;
    private static RBKServiceImpl service = new RBKServiceImpl();


    //@Transactional
    public void saveNewLine(Double dollar) {
        // here we save no time because we need only days (time will be 00:00:00).
        String currentDate = getDateNoTime();
        Rate rbk = new Rate(currentDate, dollar);
        dbWorker.save(rbk);
        //System.out.println(dbWorker);
    }

    public void saveMaxOfAllDollars(){;
        Double maxCurrency = service.getMaxFromArray(30);
        saveNewLine(maxCurrency);
    }

    //@Transactional
    public Double findTodayMax(String date) {
        Optional<Rate> dollar = dbWorker.findByDate(date);
        Optional<Double>  currency = dollar.map(Rate::getCurrency);
        if (currency.isPresent()){
            return currency.get();
        } else return Double.valueOf(-1);
    }

    @GetMapping("/getRBK")
    public Double getMaxCurrency() {
        Double maxCurrency;

        Double maxDollarDb = findTodayMax(getDateNoTime());
        if (maxDollarDb != -1) {
            maxCurrency = maxDollarDb;
        } else {
            //maxCurrency = myService.findMax(30);
            maxCurrency = getMaxFromServer();
            saveMaxOfAllDollars();
        }
        System.out.println("Our max currency " +  maxCurrency);
        return maxCurrency;
    }

    public Double getMaxFromServer(){
        return service.getMaxFromArray(30);
    }


    public static String getDateNoTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().toString();
    }

    public List<Double> getCurrencyForMonth(){
        return service.getRatesArray(30);
    }

}