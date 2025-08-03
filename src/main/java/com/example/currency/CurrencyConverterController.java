package com.example.currency;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurrencyConverterController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam double amount, Model model) {
        double converted = amount * 0.012; // Example rate
        model.addAttribute("converted", converted);
        return "index";
    }
}
