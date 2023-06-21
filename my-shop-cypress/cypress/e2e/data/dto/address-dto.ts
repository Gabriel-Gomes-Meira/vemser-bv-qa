export default class AddressDTO {

    firstName: string;
    lastName: string;
    company: string;
    address1: string;
    address2: string;
    city: string;
    state: string;
    zip: string;
    country: string;
    homePhone: string;
    mobilePhone: string;
    additionalInfo: string;
    addressAlias: string;

    constructor(firstName: string, lastName: string, address1: string, city: string, state: string, zip: string, country: string, homePhone: string, addressAlias: string) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.homePhone = homePhone;
        this.addressAlias = addressAlias;
    }
}