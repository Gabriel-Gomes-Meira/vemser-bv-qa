package data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String homePhone;
    private String mobilePhone;
    private String additionalInfo;
    private String addressAlias;

    public AddressDTO(String firstName, String lastName, String address1, String city, String state, String zip, String country, String homePhone, String addressAlias) {
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
