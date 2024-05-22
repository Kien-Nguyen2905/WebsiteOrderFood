package ecom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecom.DTO.AddCartItemRequestDTO;
import ecom.DTO.UpdateCartItemRequestDTO;
import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.CartItemModel;
import ecom.Models.CartModel;

import ecom.Models.UserModel;
import ecom.Services.Cart.CartService;
import ecom.Services.User.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/")
public class CartController {
@Autowired
private CartService cartService;

@Autowired
private UserService userService;

@PutMapping("/cart/add")
    public ResponseEntity<Object> createItemToCart(@RequestHeader("Authorization") String jwt, @RequestBody AddCartItemRequestDTO item ) throws Exception {

        UserModel findUser = userService.findByJwtToken(jwt);
        CartItemModel cartItemResponse = cartService.addItemToCart(item,findUser );

        return ResponseHandler.success("Add item to cart succes", HttpStatus.OK, cartItemResponse);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<Object> updateCartItemQuantity(@RequestHeader("Authorization") String jwt, @RequestBody UpdateCartItemRequestDTO item ) throws Exception {

        CartItemModel cartItemResponse = cartService.updateCartItemQuantity(item.getCartId(),item.getQuantity() );

        return ResponseHandler.success("Updated CartItem Quantity", HttpStatus.OK, cartItemResponse);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Object> deleteCartItem(@RequestHeader("Authorization") String jwt, @PathVariable int id ) throws Exception {

        UserModel findUser = userService.findByJwtToken(jwt);
        CartModel cartResponse = cartService.removeItemFromCart(id, findUser);

        return ResponseHandler.success("Removed cart-item", HttpStatus.OK, cartResponse);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Object> clearCart(@RequestHeader("Authorization") String jwt, @RequestBody UpdateCartItemRequestDTO item ) throws Exception {

        UserModel findUser = userService.findByJwtToken(jwt);

        CartModel cartResponse = cartService.clearCart(findUser.getId());

        return ResponseHandler.success("Updated CartItem Quantity", HttpStatus.OK, cartResponse);
    }

    @GetMapping("/cart")
    public ResponseEntity<Object> findUserCart(@RequestHeader("Authorization") String jwt, @RequestParam String param) throws Exception {
        UserModel findUser = userService.findByJwtToken(jwt);
        CartModel cartResponse = cartService.findCartByUserId(findUser.getId());
        return ResponseHandler.success("Updated CartItem Quantity", HttpStatus.OK, cartResponse);
    }
}
