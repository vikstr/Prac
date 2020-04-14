package app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictController {
    private Prediction prediction = new Prediction();

    @RequestMapping(value = "/prediction", method = RequestMethod.GET)
    @ResponseBody
    public Double indexprediction() {
        Double ans = prediction.predict();
        return ans;
    }
}
