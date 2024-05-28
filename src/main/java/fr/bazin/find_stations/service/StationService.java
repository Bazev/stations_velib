package fr.bazin.find_stations.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.bazin.find_stations.business.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StationService {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${app.security.token}")
    private String token;


    public List<Station> getStations(String ville) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?contract=%s&apiKey=%s", baseUrl, ville, token);
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Station> stations = mapper.readValue(response, new TypeReference<>() {
        });

        int availabilities = getAvailabilitiess(stations);
        kafkaProducerService.sendMessage(String.valueOf(availabilities));

        return stations;
    }

    private int getAvailabilitiess(List<Station> stations) {

        AtomicInteger availabilities = new AtomicInteger();
        stations.forEach(s-> {
            int avalaibalitie = s.getMainStands().getAvailabilities().getBikes();
            availabilities.addAndGet(avalaibalitie);

        });
        return availabilities.get();


    }

}
