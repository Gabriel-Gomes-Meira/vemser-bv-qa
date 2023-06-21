import UserDTO from '../dto/user-dto';
import { faker } from '@faker-js/faker';


export default class UserDatafactory {
    static getFakerUser(): UserDTO {
        return new UserDTO(
            faker.internet.email(),
            faker.internet.password(),
            faker.person.firstName(),
            faker.person.lastName(),
            String(faker.number.int({ min: 1, max: 28 })),
            String(faker.number.int({ min: 1, max: 12 })),
            String(faker.number.int({ min: 1900, max: 2021 })),
            faker.datatype.boolean()
        );
    }

    static getDefaultUser(): UserDTO {
        return new UserDTO(
            Cypress.env("MYSHOP_DEFAUL_EMAIL"),
            Cypress.env("MYSHOP_DEFAUL_PASSWORD"),
            'Fulano',
            'de Tal',
            '15',
            '6',
            '1990',
            true
        );
    }

    static getDefaultUserToUpdatePersonalInformation(): UserDTO {
        const myUser = UserDatafactory.getDefaultUser();
        myUser.fistName = 'Goularte';
        myUser.lastName = 'Goncalves';
        myUser.dayOfBirth = '16';
        myUser.monthOfBirth = '7';
        myUser.yearOfBirth = '1991';
        myUser.isMen = false;
        return myUser;
    }

    static getDefaultUserToUpdateEmail(): UserDTO {
        const myUser = UserDatafactory.getDefaultUser();
        myUser.email = 'second.email@email.com';
        return myUser;
    }

    static getDefaultUserToUpdatePassword(): UserDTO {
        const myUser = UserDatafactory.getDefaultUser();
        myUser.password = 'newpassword';
        return myUser;
    }
}
