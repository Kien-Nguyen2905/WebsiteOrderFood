package ecom.Services.Cart;

import java.math.BigDecimal;

import ecom.DTO.AddCartItemRequestDTO;
import ecom.Models.CartItemModel;
import ecom.Models.CartModel;
import ecom.Models.UserModel;

public interface CartServiceImp {
    public CartItemModel addItemToCart(AddCartItemRequestDTO newItem, UserModel user) throws Exception;

    public CartItemModel updateCartItemQuantity(int cartItemId , int quantity)throws Exception;

    public CartModel removeItemFromCart(int cartItemId , UserModel user)throws Exception;

    public BigDecimal calculateCartTotal(CartModel cartModel)throws Exception;

    public CartModel findCartById(int cartId) throws Exception;

    public CartModel findCartByUserId(int userId) throws Exception;

    public CartModel clearCart(int userId) throws Exception;
}
