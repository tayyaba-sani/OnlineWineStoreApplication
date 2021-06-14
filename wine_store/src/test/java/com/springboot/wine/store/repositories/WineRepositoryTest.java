package com.springboot.wine.store.repositories;

import com.springboot.wine.store.entities.Wine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WineRepositoryTest {

    @Autowired
    WineRepository wineRepository;

    @Test
    void testHappyPathForFindByYear() {

        Wine wine = new Wine();
        wine.setCountry("Germany");
        wine.setYear(2021);
        wine.setName("White Wine");

        wineRepository.save(wine);
        int year = 2021;
        List<Wine> expectedList  = wineRepository.findByYear(year);

        assertThat(expectedList).isNotNull();

    }
    @Test
    void testFailurePathForFindByYear() {
        int year = 2021;
        List<Wine> expectedList  = wineRepository.findByYear(year);

        assertThat(expectedList.size()).isEqualTo(0);

    }

    @Test
    void testHappyPathForFindByCountry() {
        Wine wine = new Wine();
        wine.setCountry("Germany");
        wine.setYear(2021);
        wine.setName("White Wine");

        wineRepository.save(wine);
        String countryName = "Germany";
        List<Wine> expectedList  = wineRepository.findByCountry(countryName);

        assertThat(expectedList).isNotNull();
    }
    @Test
    void testFailurePathForFindByCountry() {
        String countryName = "Germany";
        List<Wine> expectedList  = wineRepository.findByCountry(countryName);

        assertThat(expectedList.size()).isEqualTo(0);
    }

    @Test
    void testHappyPathForFindByVarietal() {
        Wine wine = new Wine();
        wine.setCountry("Germany");
        wine.setYear(2021);
        wine.setName("White Wine");
        wine.setVarietal("abc");

        wineRepository.save(wine);
        String varietal = "abc";
        List<Wine> expectedList  = wineRepository.findByVarietal(varietal);

        assertThat(expectedList).isNotNull();
    }
    @Test
    void testFailurePathForFindByVarietal() {
        String varietal = "abc";
        List<Wine> expectedList  = wineRepository.findByVarietal(varietal);

        assertThat(expectedList.size()).isEqualTo(0);
    }
}