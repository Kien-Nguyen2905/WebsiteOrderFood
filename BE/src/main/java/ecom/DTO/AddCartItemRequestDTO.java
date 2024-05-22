package ecom.DTO;

import java.util.List;

import lombok.Data;

@Data
public class AddCartItemRequestDTO {

    private int foodId;
    private int quantity;
    private List<String> ingredients;
}
