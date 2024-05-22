package ecom.Services.Order;

import ecom.DTO.OrderDTO;
import ecom.Models.OrderModel;
import ecom.Models.UserModel;
import java.util.*;
public interface OrderServiceImp {
    public OrderModel createOrder(OrderDTO newOrder, UserModel customer) throws Exception;

    public OrderModel updatOrder(int orderId, String orderStatus) throws Exception;

    public void calceOrder(int orderId) throws Exception;

    public List<OrderModel> getUsersOrder(int customerId) throws Exception;

    public List<OrderModel> getRestaurantOrder(int restaurantId , String orderStatus) throws Exception;

    public OrderModel findById(int orderId) throws Exception;
}
