package com.springboot.wine.store.controllers;


import com.springboot.wine.store.common.Utils.Constants;
import com.springboot.wine.store.common.exceptions.BusinessCaseException;
import com.springboot.wine.store.dtos.WineDTO;
import com.springboot.wine.store.entities.Wine;
import com.springboot.wine.store.mappers.WineMapper;
import com.springboot.wine.store.services.WineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/wines")
public class WineController {

    private final WineService wineService;
    Logger logger = LoggerFactory.getLogger(WineController.class);

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }


    @PostMapping()
    public ResponseEntity<WineDTO> addWine(@RequestBody WineDTO wineDTO) {
        logger.info("Controller: WineController: addWine: Start");
        if (Objects.isNull(wineDTO.getName())) {
            throw new BusinessCaseException(Constants.WINE_DETAILS_NOT_COMPLETED, this.getClass().toString());
        } else {
            Wine wine = WineMapper.INSTANCE.dtoToWine(wineDTO);
            Wine newWine = wineService.addWine(wine);
            WineDTO wineToDto = WineMapper.INSTANCE.WineToDto(newWine);
            if (Objects.isNull(wineToDto)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                logger.info("Controller: WineController: addWine: End");
                return new ResponseEntity(wineToDto, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/{wine-id}")
    public ResponseEntity<WineDTO> getWineById(@PathVariable("wine-id") Long wineId) {
        logger.info("Controller: WineController: getWineById: Start");
        WineDTO wineDTO = WineMapper.INSTANCE.WineToDto(wineService.findWineById(wineId));
        if (Objects.isNull(wineDTO)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Controller: WineController: getWineById: end");
            return new ResponseEntity(wineDTO, HttpStatus.OK);
        }

    }

    @GetMapping("/findByYear/{year}")
    public ResponseEntity<List<WineDTO>> getWineByYear(@PathVariable("year") int year) {
        logger.info("Controller: WineController: getWineByYear: Start");
        List<WineDTO> wineDTOList = WineMapper.INSTANCE.convertListOfWineToWineDto(wineService.getWineFindByYear(year));

        if (wineDTOList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Controller: WineController: getWineByYear: End");
            return new ResponseEntity(wineDTOList, HttpStatus.OK);
        }
    }

    @GetMapping("/findByCountry/{country}")
    public ResponseEntity<List<WineDTO>> getWineByCountry(@PathVariable("country") String country) {
        logger.info("Controller: WineController: getWineByCountry:  Start");
        List<WineDTO> wineDTOList = WineMapper.INSTANCE.convertListOfWineToWineDto(wineService.getWineFindByCountry(country));
        if (wineDTOList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Controller: WineController: getWineByCountry:  End");
            return new ResponseEntity(wineDTOList, HttpStatus.OK);
        }
    }

    @GetMapping("/findByVarietal/{varietal}")
    public ResponseEntity<List<WineDTO>> getWineByVarietal(@PathVariable("varietal") String varietal) {
        logger.info("Controller: WineController: getWineByVarietal:  Start");
        List<WineDTO> wineDTOList = WineMapper.INSTANCE.convertListOfWineToWineDto(wineService.getWineFindByVarietal(varietal));
        if (wineDTOList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Controller: WineController: getWineByVarietal:  End");
            return new ResponseEntity(wineDTOList, HttpStatus.OK);
        }
    }
}
