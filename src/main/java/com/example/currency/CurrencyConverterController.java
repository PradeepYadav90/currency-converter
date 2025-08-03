import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurrencyConverterController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/convert")
    public String convert(@RequestParam double amount,
                          @RequestParam String from,
                          @RequestParam String to) {
        double rate = 80.0; // Dummy conversion rate
        double result = amount * rate;
        return amount + " " + from + " = " + result + " " + to;
    }
}
