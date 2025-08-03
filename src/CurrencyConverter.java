import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        System.out.print("From currency (e.g., USD): ");
        String from = scanner.next().toUpperCase();

        System.out.print("To currency (e.g., EUR): ");
        String to = scanner.next().toUpperCase();

        double rate = fetchExchangeRate(from, to);
        double converted = amount * rate;

        System.out.printf("%.2f %s = %.2f %s\n", amount, from, converted, to);
    }

    private static double fetchExchangeRate(String from, String to) throws IOException {
        String apiKey = "https://api.exchangerate.host/latest?base=" + from + "&symbols=" + to;
        URL url = new URL(apiKey);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream())
        );

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        String response = content.toString();
        int start = response.indexOf(to) + 5;
        int end = response.indexOf("}", start);
        String rateStr = response.substring(start, end);
        return Double.parseDouble(rateStr);
    }
}
