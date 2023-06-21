import { faker } from '@faker-js/faker';
import AddressDTO from '../dto/address-dto';
import UserDatafactory from './user-factory';

export default class AddressDatafactory {

    static getFakerAddress() {

        return new AddressDTO(
            faker.person.firstName(),
            faker.person.lastName(),
            faker.location.streetAddress(),
            faker.location.city(),
            String(faker.number.int({min: 1, max: 53})),
            String(faker.number.int({min: 10000, max: 99999})),
            "21",
            String(faker.number.bigInt({min: 100000000, max: 999999999})),
            faker.lorem.word()
        );
    }

    static getDefaultAddress() {
        const fakerAddress = AddressDatafactory.getFakerAddress();
        const defaultUser = UserDatafactory.getDefaultUser();

        fakerAddress.firstName = defaultUser.firstName;
        fakerAddress.lastName = defaultUser.lastName;
        fakerAddress.addressAlias = 'My address';

        return fakerAddress;
    }
}
