package mate.academy.model.dto.response;

import java.time.LocalDateTime;

public class TicketResponseDto {
    private Long id;
    private Long movieSessionId;
    private String movieTitle;
    private LocalDateTime movieSessionShowTime;
    private String cinemaHallDescription;
}
