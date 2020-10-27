package mate.academy.model.dto.request;

import java.util.List;
import mate.academy.model.dto.response.TicketResponseDto;

public class OrderRequestDto {
    private List<TicketResponseDto> tickets;
    private String orderDateTime;
    private String userEmail;

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
