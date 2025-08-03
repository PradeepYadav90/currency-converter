package app;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.net.*;
import java.util.*;

@RestController
public class CurrencyConverterController {

    @GetMapping("/convert")
    public Map<String, Object> convert(
        @RequestParam double amount,
        @RequestParam String from,
        @RequestParam String to
    ) throws IOException {

        double rate = fetchExchangeRate(from.toUpperCase(), to.toUpperCase());
        double converted = amount * rate;

        Map<String, Object> response = new HashMap<>();
        response.put("from", from.toUpperCase());
        response.put("to", to.toUpperCase());
        response.put("amount", amount);
        response.put("rate", rate);
        response.put("convertedAmount", converted);

        return response;
    }

    private double fetchExchangeRate(String from, String to) throws IOException {
        String urlStr = "https://api.exchangerate.host/latest?base=" + from + "&symbols=" + to;
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) content.append(line);
        in.close();

        String response = content.toString();
        int start = response.indexOf(to) + 5;
        int end = response.indexOf("}", start);
        return Double.parseDouble(response.substring(start, end));
    }
}
