import MyAccountPage from './my-account-page';
import AddressDTO from '../../data/dto/address-dto';

export default class AddressPage extends MyAccountPage {

    static addAddressButton = "a[title='Add an address']";
    static address1 = "#address1";
    static cityInput = "#city";
    static stateInput = "#id_state";
    static zipCodeInput = "#postcode";
    static countryInput = "#id_country";
    static homePhoneInput = "#phone";
    static mobilePhone = "#phone_mobile";
    static addressAliasInput = "#alias";
    static saveButton = "#submitAddress";
    static successAlert = "p.alert.alert-success";
    static rowAddress = "div.bloc_adresses.row";

    static clickAddAddressButton() {
        cy.get(this.addAddressButton).click();
    }

    static fillAddress1(address) {
        cy.get(this.address1).clear().type(address);
    }

    static reFillAddress1(address) {
        cy.get(this.address1).clear().clear().type(address);
    }

    static getValueAddress1() {
        return cy.get(this.address1).invoke('attr', 'value');
    }

    static fillCity(city) {
        cy.get(this.cityInput).clear().type(city);
    }

    static reFillCity(city) {
        cy.get(this.cityInput).clear().clear().type(city);
    }

    static getValueCity() {
        return cy.get(this.cityInput).invoke('attr', 'value');
    }

    static selectState(state) {
        cy.get(this.stateInput).select(state);
    }

    static getValueState() {
        return cy.get(this.stateInput).invoke('attr', 'value');
    }

    static fillZipCode(zipCode) {
        cy.get(this.zipCodeInput).clear().clear().type(zipCode);
    }


    static getValueZipCode() {
        return cy.get(this.zipCodeInput).invoke('attr', 'value');
    }

    static selectCountry(country: string) {
        cy.get(this.countryInput).select(country);
    }

    static getValueCountry() {
        return cy.get(this.countryInput).invoke('attr', 'value');
    }

    static fillHomePhone(phone) {
        cy.get(this.homePhoneInput).clear().type(phone);
    }

    static getValueHomePhone() {
        return cy.get(this.homePhoneInput).invoke('attr', 'value');
    }

    static fillAddressAlias(alias: string) {
        cy.get(this.addressAliasInput).clear().type(alias);
    }

    static getValueAddressAlias() {
        return cy.get(this.addressAliasInput).invoke('attr', 'value');
    }

    static clickSaveButton() {
        cy.get(this.saveButton).click();
    }

    static getSuccessAlert() {
        return cy.get(this.successAlert).invoke('text');
    }    

    static findCrudButtonToAddress(alias: string, selector: string) {
        const h3_neigh = cy.get(this.rowAddress).find('h3.page-subheading').contains(alias);
        return h3_neigh.parent().parent().find(selector);
    }


    static openFormUpdateAddress(alias: string) {
        this.findCrudButtonToAddress(alias, "a[title='Update']").click();
    }

    static fillFormAddress(address: AddressDTO) {
        this.clickAddAddressButton();
        this.fillAddress1(address.address1);
        this.fillCity(address.city);
        this.selectState(address.state);
        this.fillZipCode(address.zip);
        this.selectCountry(address.country);
        this.fillHomePhone(address.homePhone);
        this.fillAddressAlias(address.addressAlias);
        this.clickSaveButton();
    }

    static updateAddress(address: AddressDTO) {
        this.openFormUpdateAddress(address.addressAlias);
        this.reFillAddress1(address.address1);
        this.reFillCity(address.city);
        this.selectState(address.state);
        this.fillZipCode(address.zip);
        this.selectCountry(address.country);
        this.fillHomePhone(address.homePhone);
        this.fillAddressAlias(address.addressAlias);
        this.clickSaveButton();
    }

    static deleteAddress(alias: string) {
        this.findCrudButtonToAddress(alias, "a[title='Delete']").click()
    }
}
