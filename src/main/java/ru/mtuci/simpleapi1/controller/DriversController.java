package ru.mtuci.simpleapi1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.simpleapi1.model.Drivers;
import ru.mtuci.simpleapi1.servise.DriversService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = DriversController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DriversController {
    public static final String REST_URL ="/api/v1/drivers";
    private final DriversService driversService;
    @Autowired
    public DriversController(DriversService driversService) {
        this.driversService = driversService;
    }
    @GetMapping(value = "/{id}")
    public Drivers get(@PathVariable("id")Long id){
        log.info("get"+id);
        return driversService.get(id);
    }
    @GetMapping
    public List<Drivers> getALL(){
        log.info("getALL");
        return driversService.getAll();
    }
    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public Drivers save(@RequestBody Drivers drivers){
        log.info("save"+drivers);
        return driversService.save(drivers);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id")Long id){
        log.info("delete"+id);
        driversService.delete(id);
    }
}
