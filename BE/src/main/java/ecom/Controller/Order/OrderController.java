package ecom.Controller.Order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ecom.DTO.OrderDTO;
import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.OrderModel;
import ecom.Models.UserModel;
import ecom.Services.Order.OrderService;
import ecom.Services.User.UserService;

@RestController
@RequestMapping("api/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Object> createOrder(@RequestHeader("Authorization") String jwt, @RequestBody OrderDTO order ) throws Exception {
        UserModel findUser = userService.findByJwtToken(jwt);

      OrderModel orderResponse = orderService.createOrder(order, findUser);

        return ResponseHandler.success("Order created", HttpStatus.CREATED, orderResponse);
    }

    @GetMapping("/order/user")
    public ResponseEntity<Object> getOrderHistory(@RequestHeader("Authorization") String jwt ) throws Exception {
        UserModel findUser = userService.findByJwtToken(jwt);

      List<OrderModel> ordersResponse = orderService.getUsersOrder(findUser.getId());

        return ResponseHandler.success("Order created", HttpStatus.CREATED, ordersResponse);
    }

}

