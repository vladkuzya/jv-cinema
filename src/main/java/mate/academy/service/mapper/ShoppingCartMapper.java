package mate.academy.service.mapper;

import java.util.stream.Collectors;
import mate.academy.model.ShoppingCart;
import mate.academy.model.Ticket;
import mate.academy.model.dto.response.ShoppingCartResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setTicketIds(shoppingCart.getTickets().stream()
                            .map(Ticket::getId)
                            .collect(Collectors.toList()));
        return dto;
    }
}
