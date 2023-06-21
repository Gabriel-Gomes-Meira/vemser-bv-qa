import { faker } from '@faker-js/faker';
import { RegisterPage } from '../pages/register-page'

class User {
    email;
    name;
    password;
    passwordConfirm;
    saldo;
    validate;


    constructor(email, name, password, passwordConfirm, validate = null) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.validate = validate;
    }

    toString() {
        return `${this.email} - ${this.name} - ${this.password} - ${this.passwordConfirm}`;
    }
}

function getPessoaValida() {
    const senha = faker.internet.password();
    return new User(
        faker.internet.email(),
        faker.person.firstName(),
        senha,
        senha
    );
}

function getUsuarioComEmailInvalido() {

    const senha = faker.internet.password();
    return new User(
        faker.string.uuid(),
        faker.person.firstName(),
        senha,
        senha
    );
}

function getUsuariosCamposVazios() {
    const mensagemDeEmail = RegisterPage.getMensagemDeEmail;
    const mensagemDeName = RegisterPage.getMensagemDeName;
    const mensagemDePassword = RegisterPage.getMensagemDePassword;
    const mensagemDePasswordConfirm = RegisterPage.getMensagemDePasswordConfirm;

    return [
        new User('{del}', '{del}', '{del}', '{del}', 
        () => {
            mensagemDeEmail().should('eq', 'É campo obrigatório');
            mensagemDeName().should('eq', 'É campo obrigatório')
            mensagemDePassword().should('eq', 'É campo obrigatório');
            mensagemDePasswordConfirm().should('eq', 'É campo obrigatório');

        }),
        new User('{del}', 'Random Person', '12345678', '12345678', 
        () => {
            mensagemDeEmail().should('eq', 'É campo obrigatório')
        }),
        new User (faker.internet.email(), '{del}', '12345678', '12345678', 
        () => { 
            mensagemDeName().should('eq', 'É campo obrigatório') 
        }),      
        new User(faker.internet.email(), 'Random Person', '{del}', '12345678', () => { 
            mensagemDePassword().should('eq', 'É campo obrigatório') 
        }),
        new User(faker.internet.email(), 'Random Person', '12345678', '{del}', () => { 
            mensagemDePasswordConfirm().should('eq', 'É campo obrigatório') 
        })
    ];
}

module.exports = {
    getPessoaValida,
    getUsuarioComEmailInvalido,
    getUsuariosCamposVazios,
};