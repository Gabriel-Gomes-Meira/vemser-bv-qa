package tests.myaccount;

import data.dto.AddressDTO;
import data.dto.UserDTO;
import data.factory.AddressDatafactory;
import data.factory.UserDatafactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddressPage;
import pages.MyAccountPage;
import tests.base.BaseTest;

public class AddressTest extends BaseTest {


    @Test(description = "Adicionar endereço com sucesso")
    @Description("Validar o cadastro de endereço com sucesso")
    public void testAddNewAddress() {

        UserDTO userDTO = UserDatafactory.getDefaultUser();
        MyAccountPage.fromLoginToMyAddresses(userDTO);

        AddressDTO address = AddressDatafactory.getFakerAddress();
        AddressPage.fillFormAddress(address);

        AddressPage.openFormUpdateAddress(address.getAddressAlias());
        Assert.assertEquals(AddressPage.getValueAddress1(), address.getAddress1());
        Assert.assertEquals(AddressPage.getValueCity(), address.getCity());
        Assert.assertEquals(AddressPage.getValueState(), address.getState());
        Assert.assertEquals(AddressPage.getValueZipCode(), address.getZip());
        Assert.assertEquals(AddressPage.getValueCountry(), address.getCountry());
        Assert.assertEquals(AddressPage.getValueHomePhone(), address.getHomePhone());
        Assert.assertEquals(AddressPage.getValueAddressAlias(), address.getAddressAlias());

        // Limpando teste
        AddressPage.fromAnyPageToMyAddresses();
        AddressPage.deleteAddress(address.getAddressAlias());
    }

    @Test(description = "Atualizar endereço com sucesso")
    @Description("Validar a atualização de endereço com sucesso")
    public void testUpdateAddress() {

            UserDTO userDTO = UserDatafactory.getDefaultUser();
            MyAccountPage.fromLoginToMyAddresses(userDTO);

            AddressDTO address = AddressDatafactory.getFakerAddress();
            AddressPage.fillFormAddress(address);

            AddressDTO newAddress = AddressDatafactory.getFakerAddress();
            newAddress.setAddressAlias(address.getAddressAlias());
            AddressPage.updateAddress(newAddress);

            AddressPage.openFormUpdateAddress(newAddress.getAddressAlias());
            Assert.assertEquals(AddressPage.getValueAddress1(), newAddress.getAddress1());
            Assert.assertEquals(AddressPage.getValueCity(), newAddress.getCity());
            Assert.assertEquals(AddressPage.getValueState(), newAddress.getState());
            Assert.assertEquals(AddressPage.getValueZipCode(), newAddress.getZip());
            Assert.assertEquals(AddressPage.getValueCountry(), newAddress.getCountry());
            Assert.assertEquals(AddressPage.getValueHomePhone(), newAddress.getHomePhone());
            Assert.assertEquals(AddressPage.getValueAddressAlias(), newAddress.getAddressAlias());

            // Limpando teste
            AddressPage.fromAnyPageToMyAddresses();
            AddressPage.deleteAddress(newAddress.getAddressAlias());
    }

    // remoção de endereço já está sendo coberta na limpeza dos testes acima

    @Test(description = "Cadastrar endereço sem preencher os campos obrigatórios")
    @Description("Validar o cadastro de endereço sem preencher os campos obrigatórios")
    public void testAddNewAddressWithoutRequiredFields() {

        UserDTO userDTO = UserDatafactory.getDefaultUser();
        MyAccountPage.fromLoginToMyAddresses(userDTO);

        AddressPage.clickAddAddressButton();
        AddressPage.clearFirstNameInput();
        AddressPage.clearLastNameInput();
        AddressPage.reFillAddressAlias("");
        AddressPage.clickSaveButton();

        Assert.assertTrue(AddressPage.getMeessagesAlertError()
        .contains("alias is required."));
        Assert.assertTrue(AddressPage.getMeessagesAlertError()
        .contains("lastname is required."));
        Assert.assertTrue(AddressPage.getMeessagesAlertError()
        .contains("firstname is required."));
        Assert.assertTrue(AddressPage.getMeessagesAlertError()
        .contains("address1 is required."));
        Assert.assertTrue(AddressPage.getMeessagesAlertError()
        .contains("city is required."));
        Assert.assertTrue(AddressPage.getMeessagesAlertError()
        .contains("You must register at least one phone number."));
        Assert.assertTrue(AddressPage.getMeessagesAlertError()
        .contains("This country requires you to chose a State."));
        Assert.assertTrue(AddressPage.getMeessagesAlertError()
        .contains("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));
    }

    @Test(description = "Cadastrar endereço com alias já existente")
    @Description("Validar o cadastro de endereço com alias já existente")
    public void testAddNewAddressWithExistingAlias() {

        UserDTO userDTO = UserDatafactory.getDefaultUser();
        MyAccountPage.fromLoginToMyAddresses(userDTO);

        AddressDTO address = AddressDatafactory.getFakerAddress();
        AddressPage.fillFormAddress(address);

        AddressPage.fillFormAddress(address);

        Assert.assertTrue(AddressPage.getMeessagesAlertError()
        .contains(
                String.format("The alias \"%s\" has already been used. Please select another one.",
                address.getAddressAlias())
        ));

        // Limpando teste
        AddressPage.fromAnyPageToMyAddresses();
        AddressPage.deleteAddress(address.getAddressAlias());
    }
}
