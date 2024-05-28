package fr.bazin.find_stations.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KakkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KakkaConsumerService.class);

    @KafkaListener(topics = "station", groupId = "my-group")
    public void listen(String message) {
        logger.info("Received message {}", message);
    }
}
