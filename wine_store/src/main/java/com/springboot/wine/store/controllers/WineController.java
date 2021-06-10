package com.springboot.wine.store.controllers;


import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.mappers.WineMapper;
import com.springboot.wine.store.services.WineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/wines")
public class WineController {


    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @PostMapping()
    public ResponseEntity<WineDTO> addWine(@RequestBody WineDTO wineDTO) {

        if (Objects.isNull(wineDTO)) {
            throw new BusinessCaseException(Constants.WINE_DETAILS_NOT_COMPLETED, this.getClass().toString());
        } else {
            Wine wine = WineMapper.INSTANCE.dtoToWine(wineDTO);
            WineDTO wineToDto = WineMapper.INSTANCE.WineToDto(wineService.addWine(wine));
            if (Objects.isNull(wineDTO)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity(wineToDto, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/{wine-id}")
    public ResponseEntity<WineDTO> getWineById(@PathVariable("wine-id") Long wineId) {

        WineDTO wineDTO = WineMapper.INSTANCE.WineToDto(wineService.findWineById(wineId));
        if (Objects.isNull(wineDTO)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(wineDTO, HttpStatus.OK);
        }

    }

    @GetMapping("/findByYear/{year}")
    public ResponseEntity<List<WineDTO>> getWineByYear(@PathVariable("year") int year) {

        List<WineDTO> wineDTOList = WineMapper.INSTANCE.convertListOfWineToWineDto(wineService.getWineFindByYear(year));

        if (wineDTOList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(wineDTOList, HttpStatus.OK);
        }
    }

    @GetMapping("/findByCountry/{country}")
    public ResponseEntity<List<WineDTO>> getWineByCountry(@PathVariable("country") String country) {
        List<WineDTO> wineDTOList = WineMapper.INSTANCE.convertListOfWineToWineDto(wineService.getWineFindByCountry(country));
        if (wineDTOList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(wineDTOList, HttpStatus.OK);
        }
    }

    @GetMapping("/findByVarietal/{varietal}")
    public ResponseEntity<List<WineDTO>> getWineByVarietal(@PathVariable("varietal") String varietal) {
        List<WineDTO> wineDTOList = WineMapper.INSTANCE.convertListOfWineToWineDto(wineService.getWineFindByVarietal(varietal));
        if (wineDTOList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(wineDTOList, HttpStatus.OK);
        }
    }

//    private List<WineDTO> convertListOfWineToWineDto(List<Wine> wineList) {
//        List<WineDTO> wineDtoList = new ArrayList<>();
//        for (Wine wine : wineList) {
//            WineDTO wineDTO = WineMapper.INSTANCE.WineToDto(wine);
//            wineDtoList.add(wineDTO);
//        }
//        return wineDtoList;
//    }

}
