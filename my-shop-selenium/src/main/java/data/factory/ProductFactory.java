package data.factory;

import data.dto.ProductDTO;

public class ProductFactory {

    public static ProductDTO getRandomProductToSelect() {
        ProductDTO product = new ProductDTO();

        String[] sizes = {"1", "2", "3"};
        product.setSize(sizes[new java.util.Random().nextInt(sizes.length)]);

        product.setQuantity(String.valueOf(new java.util.Random().nextInt(10)));
        return product;
    }
}
