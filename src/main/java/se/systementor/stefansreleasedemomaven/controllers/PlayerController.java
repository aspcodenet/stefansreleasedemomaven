package se.systementor.stefansreleasedemomaven.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import se.systementor.stefansreleasedemomaven.DTO.PlayerDTO;
import se.systementor.stefansreleasedemomaven.models.FakePlayerRepository;
import se.systementor.stefansreleasedemomaven.services.JerseyNumberService;

import java.util.List;
import java.util.Random;

@RestController
public class PlayerController {
    final FakePlayerRepository playerRepository;
    private final JerseyNumberService jerseyNumberService;
    final Random rand = new Random();

    public PlayerController(FakePlayerRepository playerRepository, JerseyNumberService jerseyNumberService) {
        this.playerRepository = playerRepository;
        this.jerseyNumberService = jerseyNumberService;
    }


    @GetMapping("/api/jerseyfree/{number}")
    public Boolean isJerseyFree(@PathVariable Integer number) {
        return !jerseyNumberService.isJerseyTaken(number);
    }



    @GetMapping("/api/player")
    public List<PlayerDTO> players() {
        if(rand.nextInt(100) >= 95){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "fejkar fel"
            );
        }
        return playerRepository
                .findAll()
                .stream()
                .map(player ->
                        PlayerDTO.builder()
                                .id(String.valueOf(player.getId()))
                                .playerName(player.getPlayerName())
                                .bornYear(player.getBorn())
                                .jersey(player.getJerseyNumber())
                                .teamName(player.getTeamName()).build()).toList();
    }


}