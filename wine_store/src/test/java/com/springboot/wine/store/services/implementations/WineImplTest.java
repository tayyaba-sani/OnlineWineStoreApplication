package com.springboot.wine.store.services.implementations;

import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.repositories.WineRepository;
import com.springboot.wine.store.services.WineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WineImplTest {

    @Mock
    private WineRepository wineRepository;

    private WineService wineService;

    @BeforeEach
    void setUp() {
        wineService = new WineImpl(wineRepository);
    }

    @Test
    void testHappyPathForAddWine() {

        Wine wine = new Wine();
        wine.setName("Hello");
        when(wineRepository.save(any(Wine.class))).thenReturn(wine);
        Wine newWine = wineService.addWine(wine);
        assertThat(newWine).isNotNull();
        verify(wineRepository).save(wine);
    }

    @Test
    void testHappyPathForWineFindAll() {

        wineService.getWineFindAll();
        verify(wineRepository).findAll();
    }

    @Test
    void testHappyPathForWineFindByYear() {
        int year = 2021;
        wineService.getWineFindByYear(year);

        verify(wineRepository).findByYear(year);
    }

    @Test
    void testHappyPathForWineFindByCountry() {
        String country = "germany";
        wineService.getWineFindByCountry(country);

        verify(wineRepository).findByCountry(country);
    }

    @Test
    void getWineFindByVarietal() {

        String varietal = "abc";
        wineService.getWineFindByVarietal(varietal);

        verify(wineRepository).findByVarietal(varietal);
    }

    @Test
    void findWineById() {
        long id  = 2;
        wineService.findWineById(id);

        verify(wineRepository).findById(id);
    }
}