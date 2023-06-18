package tests.products;

import jdk.jfr.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductsTest extends BaseTest {

    @Test(description = "Listar produtos")
    @Description("Validar listagem de produtos")
    public void testListProductsWithSucess() {

        HomePage.clickOnRandomCategory();

        List<WebElement> products = ProductsPage.getProducts();

        Assert.assertNotNull(products);
        Assert.assertEquals(ProductsPage.getProductsCount(), products.size());
    }

    @Test(description = "Ordernar listagem de produtos")
    @Description("Validar ordenação de produtos")
    public void testOrderProductsByPrice() {

        HomePage.clickOnRandomCategory();

        ArrayList<String> productsNames = new ArrayList<>(ProductsPage.getProductsNames());
        ArrayList<String> productsPrices = new ArrayList<>(ProductsPage.getProductsPrices());

        ProductsPage.orderNameDesc();
        Collections.sort(productsNames, Collections.reverseOrder());
        //productsNames.sort(Comparator.reverseOrder());

        List<String> namesPostOrder = ProductsPage.getProductsNames();
        for (int i = 0; i < productsNames.size(); i++) {
            Assert.assertEquals(productsNames.get(i), namesPostOrder.get(i));
        }

        ProductsPage.orderPriceAsc();
        productsPrices.sort(String::compareTo);

        List<String> pricesPostOrder = ProductsPage.getProductsPrices();
        for (int i = 0; i < productsPrices.size(); i++) {
            Assert.assertEquals(productsPrices.get(i), pricesPostOrder.get(i));
        }

    }
}
