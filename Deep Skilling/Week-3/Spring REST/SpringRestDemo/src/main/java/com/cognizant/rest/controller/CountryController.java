package com.cognizant.rest.controller;

import com.cognizant.rest.model.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @GetMapping
    public List<Country> getAllCountries() {
        return Arrays.asList(
            new Country("IN", "India"),
            new Country("US", "United States"),
            new Country("UK", "United Kingdom")
        );
    }
}
