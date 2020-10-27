package mate.academy.controllers;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.model.dto.request.MovieRequestDto;
import mate.academy.model.dto.response.MovieResponseDto;
import mate.academy.service.MovieService;
import mate.academy.service.mapper.MovieMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movieMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto dto) {
        movieService.add(movieMapper.mapToEntity(dto));
    }
}
