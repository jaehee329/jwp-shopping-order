package cart.ui;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cart.application.OrderService;
import cart.application.dto.GetDetailedOrderResponse;
import cart.application.dto.GetOrderResponse;
import cart.application.dto.GetOrdersRequest;
import cart.application.dto.PostOrderRequest;
import cart.domain.Member;

@RestController
public class OrdersApiController {

    private final OrderService orderService;

    public OrdersApiController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<GetOrderResponse>> getOrdersWithPagination(Member member,
        @ModelAttribute @Valid GetOrdersRequest request) {
        return ResponseEntity.ok(orderService.getOrdersWithPagination(member, request));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<GetDetailedOrderResponse> getDetailedOrder(Member member, @PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(member, orderId));
    }

    @PostMapping("/orders")
    public ResponseEntity<Void> addOrder(Member member, @RequestBody @Valid PostOrderRequest request) {
        Long orderId = orderService.addOrder(member, request);
        return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
    }
}