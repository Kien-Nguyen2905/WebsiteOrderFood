package ecom.DTO;

import lombok.Data;

@Data
public class UpdateCartItemRequestDTO {
    private int cartId;
    private int quantity;
}
