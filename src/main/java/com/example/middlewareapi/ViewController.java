package com.example.middlewareapi;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ViewController {

    @GetMapping("/view")
    public String serveHtmlPage() {
        return "index";
    }

    @PostMapping("/forward")
    public ResponseEntity<String> forwardData(@RequestBody String data) {
        RestTemplate restTemplate = new RestTemplate();
        String externalApiUrl = "https://external.api.endpoint/";

        ResponseEntity<String> response = restTemplate.postForEntity(externalApiUrl, data, String.class);

        // Parse the response body using org.json
        JSONObject jsonResponse = new JSONObject(response.getBody());

        // Extract the desired fields
        String status = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("status");
        double amount = jsonResponse.getJSONObject("data").getJSONObject("attributes").getDouble("amount");
        Object customerData = jsonResponse.getJSONObject("data").getJSONObject("relationships").getJSONObject("customer").get("data");

        // Print out the extracted fields
        System.out.println("Status: " + status);
        System.out.println("Amount: " + amount);
        System.out.println("Customer Data: " + customerData);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

    }
}
