export default class UserDTO {
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    dayOfBirth: string;
    monthOfBirth: string;
    yearOfBirth: string;
    isMen: boolean;

    constructor(email?: string, password?: string, firstName?: string, lastName?: string, dayOfBirth?: string, monthOfBirth?: string, yearOfBirth?: string, isMen?: boolean) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.isMen = isMen;
    }
}