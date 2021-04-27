package io.github.soufianeodf.jsonbpostgresql;

import io.github.soufianeodf.jsonbpostgresql.model.Country;
import io.github.soufianeodf.jsonbpostgresql.repositories.CountryRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class JsonbPostgresqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonbPostgresqlApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CountryRepository countryRepository) {
        return args -> {
            // persistJson(countryRepository);
            // fetchJson(countryRepository);
        };
    }

    private void fetchJson(CountryRepository countryRepository) {
        // get the last element
        Optional<Country> country = countryRepository.findById(1L);
        country.ifPresent(c -> {
            if (!isJSONValid(c.getCities())) {
                System.exit(0);
            }
            JSONArray oldCities;
            String lastCity = null;
            try {
                if (c.getCities() != null) {
                    oldCities = new JSONArray(c.getCities());
                    if(oldCities.length() != 0) {
                        // get the last object
                        lastCity = oldCities.getString(oldCities.length()-1);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // get String from last object
            if (lastCity != null) {
                System.out.println(lastCity);
            }
        });
    }

    private void persistJson(CountryRepository countryRepository) {
        Optional<Country> country = countryRepository.findById(1L);
        country.ifPresent(c -> {
            System.out.println(c);
            String newCity = "new york";
            if (isJSONValid(c.getCities()) && (c.getCities() == null || !c.getCities().contains(newCity))) {
                try {
                    String newCities;
                    if(c.getCities() == null) {
                        newCities = new JSONArray('[' + newCity + ']').toString();
                    } else {
                        newCities = new JSONArray(c.getCities()).put(newCity).toString();
                    }
                    countryRepository.save(c.setCities(newCities));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            countryRepository.findById(1L).ifPresent(System.out::println);
        });
    }

    /* This function is only for validating JSON for confirmation and operation_id,
    * should not be used for general cases.
    */
    private boolean isJSONValid(String value) {
        if (value != null) {
            try {
                new JSONObject(value);
            } catch (JSONException ex) {
                try {
                    new JSONArray(value);
                } catch (JSONException ex1) {
                    System.err.println("column containing invalid json value");
                    return false;
                }
            }
        }
        return true;
    }

}
