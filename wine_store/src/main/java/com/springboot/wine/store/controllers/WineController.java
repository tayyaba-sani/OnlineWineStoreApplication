package com.springboot.wine.store.controllers;


import com.springboot.wine.store.dtos.ResponseDTO;
import com.springboot.wine.store.dtos.ResponseHandler;
import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.mappers.WineMapper;
import com.springboot.wine.store.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wines")
public class WineController {


    @Autowired
    private WineService wineService;

    @PostMapping("/addWine")
    public ResponseHandler<WineDTO> addWine(@RequestBody WineDTO wineDTO) {
        Wine wine = WineMapper.INSTANCE.dtoToWine(wineDTO);
        return ResponseDTO.responseSuccessful(wineService.addWine(wine));
    }

    @GetMapping("/findById/{id}")
    public ResponseHandler<WineDTO> getWineById(@PathVariable("id") Long wineId) {
        return ResponseDTO.responseSuccessful(wineService.findWineById(wineId));

    }

    @GetMapping("/findByYear/{year}")
    public ResponseHandler<List<WineDTO>> getWineByYear(@PathVariable("year") int year) {
        return ResponseDTO.responseSuccessful(wineService.getWineFindByYear(year));
    }

    @GetMapping("/findByCountry/{country}")
    public ResponseHandler<List<Wine>> getWineByCountry(@PathVariable("country") String country) {
        return ResponseDTO.responseSuccessful(wineService.getWineFindByCountry(country));
    }

    @GetMapping("/findByVarietal/{varietal}")
    public ResponseHandler<List<Wine>> getWineByVarietal(@PathVariable("varietal") String varietal) {
        return ResponseDTO.responseSuccessful(wineService.getWineFindByVarietal(varietal));
    }

}