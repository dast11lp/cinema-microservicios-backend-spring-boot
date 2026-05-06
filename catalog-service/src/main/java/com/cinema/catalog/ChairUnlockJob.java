package com.cinema.catalog;

import com.cinema.catalog.entities.FunctionChair;
import com.cinema.catalog.repositories.FunctionChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ChairUnlockJob {

    @Autowired
    private FunctionChairRepository chairRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void releaseExpiredChairs() {
        List<FunctionChair> expired = chairRepository.findExpiredBlockedChairs(LocalDateTime.now());
        expired.forEach(chair -> {
            chair.setStatus("AVAILABLE");
            chair.setBlockedUntil(null);
        });
        chairRepository.saveAll(expired);
    }
}
