package tests.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.CartPage;
import page.InventoryPage;
import page.LoginPage;
import tests.base.BaseTest;

import java.util.List;

public class InvetoryTest extends BaseTest {

    @Test
    @DisplayName("Deve adicionar um item ao carrinho")
    public void testAdicionarItemAoCarrinhoComSucesso() {

        // DADO que eu esteja logado
        // E esteja na página de produtos
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.acessPage();

        // QUANDO eu adicionar um item ao carrinho
        WebElement randomItemFromInventory = inventoryPage.getRandomItemFromInventory();
        inventoryPage.clickAddToCartButtonToItem(randomItemFromInventory);
        String itemName = randomItemFromInventory.findElement(By.cssSelector("div.inventory_item_name")).getText();

        // ENTÃO devo ver o ícone do carrinho com a quantidade de itens
        Assertions.assertEquals(1, inventoryPage.getQuantityItemsOnCartIcon());

        // E devo encontrar o item no carrinho
        CartPage cartPage = inventoryPage.navigateToCartPageByLink();
        List<WebElement> itemsOnCart = cartPage.getItemsOnCart();

        Assertions.assertEquals(itemName,
                itemsOnCart.get(0).findElement(By.cssSelector("div.inventory_item_name")).getText());
        Assertions.assertEquals(1, itemsOnCart.size());
    }

    @Test
    @DisplayName("Deve remover um item do carrinho")
    public void testRemoverItemDoCarrinhoComSucesso() {

        // DADO que eu esteja logado
        // E esteja na página de produtos
        // E esteja com um produto adicionado ao carrinho
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.acessPage();
        WebElement randomItemFromInventory = inventoryPage.getRandomItemFromInventory();
        inventoryPage.clickAddToCartButtonToItem(randomItemFromInventory);

        // QUANDO eu remover o item do carrinho
        inventoryPage.clickAddToCartButtonToItem(randomItemFromInventory);

        // ENTÃO devo ver o ícone do carrinho com a quantidade de itens
        Assertions.assertEquals(0, inventoryPage.getQuantityItemsOnCartIcon());

        // E não encontro o item no carrinho
        CartPage cartPage = inventoryPage.navigateToCartPageByLink();

        Assertions.assertEquals(0, cartPage.getItemsOnCart().size());
    }

    @Test
    @DisplayName("Deve deslogar com sucesso")
    public void testDeslogarComSucesso() {
        // DADO que eu esteja logado
        // E esteja na página de produtos
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.acessPage();

        // QUANDO eu clicar no botão de logout
        inventoryPage.logout();

        // ENTÃO devo ser redirecionado para a página de login
        Assertions.assertEquals(LoginPage.URL, getDriver().getCurrentUrl());
    }
}
