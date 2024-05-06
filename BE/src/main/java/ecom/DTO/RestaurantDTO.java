package ecom.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.util.*;

@Data
@Embeddable
public class RestaurantDTO {

    private int id;
    private String title;
    private String description;
    
    @Column(length = 1000)
    private List<String> images;
  

}
