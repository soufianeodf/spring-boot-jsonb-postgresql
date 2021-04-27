package io.github.soufianeodf.jsonbpostgresql.repositories;

import io.github.soufianeodf.jsonbpostgresql.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
