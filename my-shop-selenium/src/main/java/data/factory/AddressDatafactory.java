package data.factory;

import data.dto.AddressDTO;
import data.dto.UserDTO;
import net.datafaker.Faker;

public class AddressDatafactory {

    private static final Faker faker = new Faker();

    public static AddressDTO getFakerAddress() {
        return new AddressDTO(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().fullAddress(),
                faker.address().city(),
                String.valueOf(faker.number().numberBetween(1, 53)),
                faker.address().zipCode(),
                "21",
                faker.number().digits(11),
                faker.lorem().word()
        );
    }

    public static AddressDTO getDefaultAddress() {
        AddressDTO fakerAddress = getFakerAddress();
        UserDTO defaultUser = UserDatafactory.getDefaultUser();

        fakerAddress.setFirstName(defaultUser.getFistName());
        fakerAddress.setLastName(defaultUser.getLastName());
        fakerAddress.setAddressAlias("My address");

        return fakerAddress;
    }
}
