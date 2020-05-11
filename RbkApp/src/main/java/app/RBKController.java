package app;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@RestController
public class RBKController {

    @Autowired
    private RBKService rbkService;

    @RequestMapping(value = "/dollarsMax",method = RequestMethod.GET)
    public Double index() {
        Double ans = rbkService.getMaxCurrency();
        return ans;
    }
    @RequestMapping(value = "/ping")
    public String ping(){
        return ("I'm working.");
    }
}
