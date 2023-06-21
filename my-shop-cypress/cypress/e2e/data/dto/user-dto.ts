export default class UserDTO {
    email: string;
    password: string;
    fistName: string;
    lastName: string;
    dayOfBirth: string;
    monthOfBirth: string;
    yearOfBirth: string;
    isMen: boolean;

    constructor(email: string, password: string, fistName: string, lastName: string, dayOfBirth: string, monthOfBirth: string, yearOfBirth: string, isMen: boolean) {
        this.email = email;
        this.password = password;
        this.fistName = fistName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.isMen = isMen;
    }
}