package se.systementor.stefansreleasedemomaven.services;

import org.springframework.stereotype.Service;
import se.systementor.stefansreleasedemomaven.models.FakePlayerRepository;

@Service
public class JerseyNumberService {
    private final FakePlayerRepository fakePlayerRepository;

    public JerseyNumberService(FakePlayerRepository fakePlayerRepository) {
        this.fakePlayerRepository = fakePlayerRepository;
    }

    public boolean isJerseyTaken(int jerseyNumber){
        return fakePlayerRepository.findAll().stream().anyMatch(player -> player.getJerseyNumber() == jerseyNumber);
    }

}