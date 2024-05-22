package ecom.DTO;

import ecom.Models.AddressModel;
import lombok.Data;

@Data
public class OrderDTO {
    private int restaurantId;
    private AddressModel deliveryAddress;
}
