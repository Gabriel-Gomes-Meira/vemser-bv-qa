package data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {


    private String name;
    private String color;
    private String size;
    private String quantity;
}
