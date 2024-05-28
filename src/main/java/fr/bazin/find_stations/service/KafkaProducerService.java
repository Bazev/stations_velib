package fr.bazin.find_stations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String availabilities) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm");
        String dateFormatted = LocalDateTime.now().format(formatter);

        String data = dateFormatted
                .concat(" - Nombres de places disponibles : ")
                .concat(availabilities);

        kafkaTemplate.send("station",data);
    }

}
