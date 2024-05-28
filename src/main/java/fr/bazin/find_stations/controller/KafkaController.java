package fr.bazin.find_stations.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.bazin.find_stations.business.Station;
import fr.bazin.find_stations.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KafkaController {

    @Autowired
    private StationService stationService;

    @GetMapping("/stations")
    public List<Station> getStations(@RequestParam String ville) throws JsonProcessingException {
        return stationService.getStations(ville);
    }




}
