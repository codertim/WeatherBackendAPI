/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.returnnil.weatherbackend;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author T
 */

@RestController
// @Controller, @ResponseBody
public class GreetingController {
    @Autowired
    private Environment env;

    //@Value("${api.key.weather}"
    private String apiKeyWeather;
 
    @RequestMapping("/test1")
    public String getGreeting() {
        return "Hello There";
    }
    
    @RequestMapping("/test2")
    public HashMap<String, String> testJson() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value 1");
        map.put("key2", "value 2");
        map.put("key3", "value 3");

        return map;
    }
    
    @RequestMapping("/test3")
    public String getTest3() {
        apiKeyWeather = env.getProperty("API_KEY_WEATHER");
        return "val: " + apiKeyWeather;
    }
}
