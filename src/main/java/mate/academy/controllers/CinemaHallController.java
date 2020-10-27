package mate.academy.controllers;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.model.dto.request.CinemaHallRequestDto;
import mate.academy.model.dto.response.CinemaHallResponseDto;
import mate.academy.service.CinemaHallService;
import mate.academy.service.mapper.CinemaHallMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addCinemaHall(@RequestBody CinemaHallRequestDto dto) {
        cinemaHallService.add(cinemaHallMapper.mapToEntity(dto));
    }
}