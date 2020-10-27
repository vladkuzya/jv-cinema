package mate.academy.service.mapper;

import mate.academy.model.CinemaHall;
import mate.academy.model.dto.request.CinemaHallRequestDto;
import mate.academy.model.dto.response.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {
    public CinemaHallResponseDto mapToDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();
        dto.setId(cinemaHall.getId());
        dto.setCapacity(cinemaHall.getCapacity());
        dto.setDescription(cinemaHall.getDescription());
        return dto;
    }

    public CinemaHall mapToEntity(CinemaHallRequestDto dto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(dto.getCapacity());
        cinemaHall.setDescription(dto.getDescription());
        return cinemaHall;
    }
}
