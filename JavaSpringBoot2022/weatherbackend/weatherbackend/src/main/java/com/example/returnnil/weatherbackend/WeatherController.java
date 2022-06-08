/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.returnnil.weatherbackend;

///import java.net.http.HttpHeaders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author T
 */
@RestController
public class WeatherController {
    @Autowired
    private Environment env;
    
    String apiKeyWeather = "";
    
    @RequestMapping("/city")
    ///public HashMap<String, String> getWeatherByCity(String city) {
    public HashMap<String, String> getWeatherByCity(String city) {

        ///city = "Seattle";   // TODO: remove this
        
        
        
        apiKeyWeather = env.getProperty("API_KEY_WEATHER");
        
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKeyWeather;
        RestTemplate restTemplate = new RestTemplate();
        ///HttpHeaders headers;
        ///headers = new HttpHeaders();
        ///headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ///headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        ///HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ///ResponseEntity<String> response =
        ///        restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();
        
        
        ///return responseBody;
        
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeRoot = null;
        try {
            jsonNodeRoot = objectMapper.readTree(responseBody);
        }
        catch (JsonProcessingException e)
        {
            //return "Error: " + e.getMessage();
        }
        
        HashMap<String, String> jsonMap = new HashMap<>();

        String description = jsonNodeRoot.get("weather").get(0).get("description").asText();
        String cityName = jsonNodeRoot.get("name").asText();
        String windSpeed = jsonNodeRoot.get("wind").get("speed").asText();
        String temp = jsonNodeRoot.get("main").get("temp").asText();
        String tempMin = jsonNodeRoot.get("main").get("temp_min").asText();
        String tempMax = jsonNodeRoot.get("main").get("temp_max").asText();
        jsonMap.put("description", description);
        jsonMap.put("cityName", cityName);
        jsonMap.put("windSpeed", windSpeed);
        jsonMap.put("temp", temp);
        jsonMap.put("tempMin", tempMin);
        jsonMap.put("tempMax", tempMax);

        //Assertions.assertNotNull(name.asText());
        ///request = requests.get(url)
        ///json = request.json()
        ///description = json.get('weather')[0].get('description')

        ///return "Description: " + descriptionNode.asText();
        return jsonMap;
    }
}
