package ecom.Services.Order;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;

import ecom.DTO.OrderDTO;
import ecom.Helper.Handler.Exceptions.NotFoundException;
import ecom.Models.AddressModel;
import ecom.Models.CartItemModel;
import ecom.Models.CartModel;
import ecom.Models.OrderItemModel;
import ecom.Models.OrderModel;
import ecom.Models.RestaurantModel;
import ecom.Models.UserModel;
import ecom.Repositories.AddressRepository;
import ecom.Repositories.UserRepository;
import ecom.Repositories.Order.OrderItemRepository;
import ecom.Repositories.Order.OrderRepository;
import ecom.Services.Cart.CartService;
import ecom.Services.Restaurant.RestaurantService;

public class OrderService implements OrderServiceImp {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public OrderModel createOrder(OrderDTO newOrder, UserModel customer) throws Exception {
        AddressModel Address = newOrder.getDeliveryAddress();
        AddressModel savedAddress = addressRepository.save(Address);

        if (!customer.getAddresses().contains(savedAddress)) {
            customer.getAddresses().add(savedAddress);
            userRepository.save(customer);
        }

        RestaurantModel findRestaurant = restaurantService.findById(newOrder.getRestaurantId());

        OrderModel createOrder = new OrderModel();
        createOrder.setCustomer(customer);
        createOrder.setCreatedAt(new Date());
        createOrder.setOrderStatus("PENDING");
        createOrder.setDeliveryAddress(Address);
        createOrder.setRestaurant(findRestaurant);

        CartModel cart = cartService.findCartByUserId(customer.getId());

        List<OrderItemModel> orderItems = new ArrayList<>();
        for (CartItemModel item : cart.getItems()) {
            OrderItemModel orderItem = new OrderItemModel();
            orderItem.setFood(item.getFood());
            orderItem.setIngredients(item.getIngredients());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(item.getTotalPrice());

            OrderItemModel savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        BigDecimal totalPrice = cartService.calculateCartTotal(cart);

        createOrder.setItems(orderItems);
        createOrder.setTotalPrice(totalPrice);

        OrderModel savedOrder = orderRepository.save(createOrder);
        findRestaurant.getOrders().add(savedOrder);


        return createOrder;

    }

    @Override
    public OrderModel updatOrder(int orderId, String orderStatus) throws Exception {
        OrderModel order = findById(orderId);
        if (orderStatus.equals("OUT_FOR_DELIVERY") ||
                orderStatus.equals("DELIVERED") ||
                orderStatus.equals("COMPLETED") ||
                orderStatus.equals("PENDING")) {

                    order.setOrderStatus(orderStatus);
                    return orderRepository.save(order);
        }

        throw new BadRequestException("Please select a valid order status");

    }

    @Override
    public void calceOrder(int orderId) throws Exception {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<OrderModel> getUsersOrder(int customerId) throws Exception {
       return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public List<OrderModel> getRestaurantOrder(int restaurantId, String orderStatus) throws Exception {
        List<OrderModel> orders = orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus != null){
            orders =   orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
            return orders;
    }

    @Override
    public OrderModel findById(int orderId) {
         Optional<OrderModel> opt = orderRepository.findById(orderId);
         if(opt.isEmpty()){
            throw new NotFoundException("Order not found");
         }
       return opt.get();
    }

}
