package mate.academy.service.mapper;

import java.util.stream.Collectors;
import mate.academy.model.Order;
import mate.academy.model.Ticket;
import mate.academy.model.dto.response.OrderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderDateTime(order.getOrderData().toString());
        dto.setTicketIds(order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        dto.setUserId(order.getUser().getId());
        return dto;
    }
}
