package io.github.soufianeodf.jsonbpostgresql.repositories;

import io.github.soufianeodf.jsonbpostgresql.model.Country;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Before
    public void before() {
        countryRepository.deleteAll();
    }

    @Test
    public void test() {
        Country country = new Country();
        country.setCities("[\"tanger\", \"tetouan\"]");

        Country savedObject = countryRepository.save(country);

        Optional<Country> obj = countryRepository.findById(savedObject.getId());

        Assertions.assertThat(obj.get()).isEqualTo(country);

    }
}