package mate.academy.controllers;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.model.ShoppingCart;
import mate.academy.model.dto.response.OrderResponseDto;
import mate.academy.service.OrderService;
import mate.academy.service.ShoppingCartService;
import mate.academy.service.UserService;
import mate.academy.service.mapper.OrderMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           ShoppingCartService shoppingCartService,
                           UserService userService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam String email) {
        ShoppingCart shoppingCart =
                shoppingCartService.getByUser(userService.findByEmail(email).get());
        orderService.completeOrder(shoppingCart.getTickets(), shoppingCart.getUser());
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistoryByUser(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.getById(userId)).stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
