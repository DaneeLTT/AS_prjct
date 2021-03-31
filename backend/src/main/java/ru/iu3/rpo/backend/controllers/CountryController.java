package ru.iu3.rpo.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.iu3.rpo.backend.models.Country;
import ru.iu3.rpo.backend.repositories.CountryRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CountryController {
    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // curl -d '{"name":"Russia"}' -H "Content-Type: application/json" -X POST http://localhost:8081/api/v1/countries/1
    @PostMapping("/countries")
    public ResponseEntity<Object> createCountry(@Valid @RequestBody Country country) {
        try {
            Country nc = countryRepository.save(country);
            return new ResponseEntity<>(nc, HttpStatus.OK);
        }
        catch (Exception ex) {
            String error;
            if(ex.getMessage().contains("countries.name_UNIQUE")) {
                error = "Country already exist!";
            }
            else {
                error = "Unknown error";
            }
            Map<String, String> map = new HashMap<>();
            map.put("error", error);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
    // curl -d '{"name":"Russia"}' -H "Content-Type: application/json" -X PUT http://localhost:8081/api/v1/countries/1
    @PutMapping("/countries/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") Long idcountry,
                                                 @Valid @RequestBody Country countryDetails) {
        Country country;
        Optional<Country> cc = countryRepository.findById(idcountry);
        if (cc.isPresent()) {
            country = cc.get();
            country.name = countryDetails.name;
            countryRepository.save(country);
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Country not found!"
            );
        }
        return ResponseEntity.ok(country);
    }


}
