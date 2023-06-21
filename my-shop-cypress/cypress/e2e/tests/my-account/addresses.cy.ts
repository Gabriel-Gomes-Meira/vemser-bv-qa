import UserDatafactory from '../../data/factory/user-factory';
import AddressDatafactory from '../../data/factory/adress-factory';
import MyAccountPage from '../../pages/my-account/my-account-page';
import AddressPage from '../../pages/my-account/addresses-page';

describe('Address Test', () => {

    

    it('Adicionar endereço com sucesso', () => {
        const userDTO = UserDatafactory.getDefaultUser();
        MyAccountPage.fromLoginToMyAddresses(userDTO);

        const address = AddressDatafactory.getFakerAddress();
        AddressPage.fillFormAddress(address);

        AddressPage.openFormUpdateAddress(address.addressAlias);
        AddressPage.getValueAddress1().should('eq', address.address1);
        AddressPage.getValueCity().should('eq', address.city);
        AddressPage.getValueState().should('eq', address.state);
        AddressPage.getValueZipCode().should('eq', address.zip);
        AddressPage.getValueCountry().should('eq', address.country);
        AddressPage.getValueHomePhone().should('eq', address.homePhone);
        AddressPage.getValueAddressAlias().should('eq', address.addressAlias);

        // Limpando teste
        AddressPage.fromAnyPageToMyAddresses();
        AddressPage.deleteAddress(address.addressAlias);
    });

    it('Atualizar endereço com sucesso', () => {
        const userDTO = UserDatafactory.getDefaultUser();
        MyAccountPage.fromLoginToMyAddresses(userDTO);

        const address = AddressDatafactory.getFakerAddress();
        AddressPage.fillFormAddress(address);

        const newAddress = AddressDatafactory.getFakerAddress();
        newAddress.addressAlias = address.addressAlias;
        AddressPage.updateAddress(newAddress);

        AddressPage.openFormUpdateAddress(newAddress.addressAlias);
        AddressPage.getValueAddress1().should('eq', newAddress.address1);
        AddressPage.getValueCity().should('eq', newAddress.city);
        AddressPage.getValueState().should('eq', newAddress.state);
        AddressPage.getValueZipCode().should('eq', newAddress.zip);
        AddressPage.getValueCountry().should('eq', newAddress.country);
        AddressPage.getValueHomePhone().should('eq', newAddress.homePhone);
        AddressPage.getValueAddressAlias().should('eq', newAddress.addressAlias);

        // Limpando teste
        AddressPage.fromAnyPageToMyAddresses();
        AddressPage.deleteAddress(newAddress.addressAlias);
    });

    // remoção de endereço já está sendo coberta na limpeza dos testes acima

    it('Cadastrar endereço sem preencher os campos obrigatórios', () => {
        const userDTO = UserDatafactory.getDefaultUser();
        MyAccountPage.fromLoginToMyAddresses(userDTO);

        AddressPage.clickAddAddressButton();
        AddressPage.clearFirstNameInput();
        AddressPage.clearLastNameInput();
        AddressPage.fillAddressAlias('{del}');
        AddressPage.clickSaveButton();

        AddressPage.getMeessagesAlertError().should('contain', 'alias is required.');
        AddressPage.getMeessagesAlertError().should('contain', 'lastname is required.');
        AddressPage.getMeessagesAlertError().should('contain', 'firstname is required.');
        AddressPage.getMeessagesAlertError().should('contain', 'address1 is required.');
        AddressPage.getMeessagesAlertError().should('contain', 'city is required.');
        AddressPage.getMeessagesAlertError().should('contain', 'You must register at least one phone number.');
        AddressPage.getMeessagesAlertError().should('contain', 'This country requires you to chose a State.');
    });

    it('Cadastrar endereço com alias já existente', () => {
        const userDTO = UserDatafactory.getDefaultUser();
        MyAccountPage.fromLoginToMyAddresses(userDTO);

        const address = AddressDatafactory.getFakerAddress();
        AddressPage.fillFormAddress(address);

        AddressPage.fillFormAddress(address);

        AddressPage.getMeessagesAlertError().should('contain',
            `The alias "${address.addressAlias}" has already been used. Please select another one.`
        );

        // Limpando teste
        AddressPage.fromAnyPageToMyAddresses();
        AddressPage.deleteAddress(address.addressAlias);
    });

});
