package ecom.Controller.Order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.OrderModel;
import ecom.Services.Order.OrderService;

@RestController
@RequestMapping("api/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<Object> getOrderHistory( @PathVariable int id, @RequestParam(required = false) String order_status  ) throws Exception {


      List<OrderModel> ordersResponse = orderService.getRestaurantOrder(id,order_status);

        return ResponseHandler.success("Order created", HttpStatus.CREATED, ordersResponse);
    }

    @PutMapping("/order/{orderid}/{orderStatus}")
    public ResponseEntity<Object> updateOrderStatus( @PathVariable int orderid, @PathVariable String orderStatus  ) throws Exception {


     OrderModel orderResponse = orderService.updatOrder(orderid, orderStatus);

        return ResponseHandler.success("Order created", HttpStatus.CREATED, orderResponse);
    }

}

