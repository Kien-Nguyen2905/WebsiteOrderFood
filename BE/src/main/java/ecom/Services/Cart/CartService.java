package ecom.Services.Cart;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ecom.DTO.AddCartItemRequestDTO;
import ecom.Helper.Handler.Exceptions.NotFoundException;
import ecom.Models.CartItemModel;
import ecom.Models.CartModel;
import ecom.Models.FoodModel;
import ecom.Models.UserModel;

import ecom.Repositories.Cart.CartItemRepository;
import ecom.Repositories.Cart.CartRepository;
import ecom.Services.Food.FoodService;

public class CartService implements CartServiceImp {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItemModel addItemToCart(AddCartItemRequestDTO newItem, UserModel user) throws Exception {

        FoodModel findFood = foodService.findById(newItem.getFoodId());

        CartModel findCart = cartRepository.findByCustomerId(user.getId());

        for (CartItemModel item : findCart.getItems()) {
            if (item.getFood().equals(findFood)) {
                int newQuantity = item.getQuantity() + newItem.getQuantity();
                return updateCartItemQuantity(item.getId(), newQuantity);
            }
        }

        CartItemModel newCardItem = new CartItemModel();
        newCardItem.setFood(findFood);
        newCardItem.setCart(findCart);
        newCardItem.setQuantity(newItem.getQuantity());
        newCardItem.setIngredients(newItem.getIngredients());

        // Convert int quantity to BigDecimal
        BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(newItem.getQuantity());

        // Get the price
        BigDecimal price = findFood.getPrice();

        // Multiply quantity and price
        BigDecimal total = quantityAsBigDecimal.multiply(price);

        newCardItem.setTotalPrice(total);

        CartItemModel savedCardItem = cartItemRepository.save(newCardItem);

        findCart.getItems().add(savedCardItem);

        return savedCardItem;

    }

    @Override
    public CartItemModel updateCartItemQuantity(int cartItemId, int quantity) throws Exception {
        Optional<CartItemModel> cartItemOpt = cartItemRepository.findById(cartItemId);

        if (cartItemOpt.isEmpty()) {
            throw new NotFoundException("Cart item not found");
        }

        CartItemModel item = cartItemOpt.get();
        item.setQuantity(quantity);

        // Convert int quantity to BigDecimal
        BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(item.getQuantity());

        // Get the price
        BigDecimal price = item.getFood().getPrice();

        // Multiply quantity and price
        BigDecimal total = quantityAsBigDecimal.multiply(price);

        item.setTotalPrice(total);
        return cartItemRepository.save(item);
    }

    @Override
    public CartModel removeItemFromCart(int cartItemId, UserModel user) throws Exception {

        CartModel findCart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItemModel> cartItemOpt = cartItemRepository.findById(cartItemId);

        if (cartItemOpt.isEmpty()) {
            throw new NotFoundException("Cart item not found");
        }

        CartItemModel item = cartItemOpt.get();

        findCart.getItems().remove(item);

        return cartRepository.save(findCart);

    }

    @Override
    public BigDecimal calculateCartTotal(CartModel cartModel) throws Exception {
    
        BigDecimal total = BigDecimal.ZERO;
        for (CartItemModel item : cartModel.getItems()) {
            BigDecimal price = item.getFood().getPrice();
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total;
    }

    @Override
    public CartModel findCartById(int cartId) throws Exception {
        Optional<CartModel> cartOpt = cartRepository.findById(cartId);

        if (cartOpt.isEmpty()) {
            throw new NotFoundException("Cart not found");
        }
        return cartOpt.get();

    }

    @Override
    public CartModel findCartByUserId(int userId) throws Exception {
        return cartRepository.findByCustomerId(userId);
    }

    @Override
    public CartModel clearCart(int userId) throws Exception {
        CartModel cart = findCartByUserId(userId);

        cart.getItems().clear();
        return cartRepository.save(cart);
    }

}
