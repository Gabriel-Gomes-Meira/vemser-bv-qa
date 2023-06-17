package data.factory;

import data.dto.UserDTO;
import net.datafaker.Faker;
import util.Enviroment;

public class UserDatafactory {

    private static final Faker faker = new Faker();

    public static UserDTO getFakerUser() {
        return new UserDTO(
            faker.internet().emailAddress(),
            faker.internet().password(),
            faker.name().firstName(),
            faker.name().lastName(),
            String.valueOf(faker.number().numberBetween(1, 28)),
            String.valueOf(faker.number().numberBetween(1, 12)),
            String.valueOf(faker.number().numberBetween(1900, 2021)),
            faker.bool().bool()
        );
    }

    public static UserDTO getDefaultUser() {
        return new UserDTO(
            Enviroment.getMyshopDefaultEmail(),
            Enviroment.getMyshopDefaultPassword(),
            "Fulano",
            "de Tal",
            "15",
            "06",
            "1990",
            true
        );
    }

    public static UserDTO getDefaultUserToUpdatePersonalInformation() {
        UserDTO myUser = getDefaultUser();
        myUser.setFistName("Goularte");
        myUser.setLastName("Goncalves");
        myUser.setDayOfBirth("16");
        myUser.setMonthOfBirth("07");
        myUser.setYearOfBirth("1991");
        myUser.setMen(false);
        return myUser;
    }

    public static UserDTO getDefaultUserToUpdateEmail() {
        UserDTO myUser = getDefaultUser();
        myUser.setEmail("second.emal@email.com");
        return myUser;
    }

    public static UserDTO getDefaultUserToUpdatePassword() {
        UserDTO myUser = getDefaultUser();
        myUser.setPassword("newpassword");
        return myUser;
    }

}
