import { faker } from '@faker-js/faker';
import { RegisterPage } from './register-page';

function getPessoaValida() {
    const senha = faker.internet.password();
    return {
        email: faker.internet.email(),
        name: faker.person.firstName(),
        password: senha,
        passwordConfirm: senha
    };
}

function getUsuarioComEmailInvalido() {

    const senha = faker.internet.password();
    return {
        email: faker.string.uuid(),
        name: faker.person.firstName(),
        password: senha,
        passwordConfirm: senha
    };
}

function getUsuariosCamposVazios() {
    const mensagemDeEmail = RegisterPage.getMensagemDeEmail;
    const mensagemDeName = RegisterPage.getMensagemDeName;
    const mensagemDePassword = RegisterPage.getMensagemDePassword;
    const mensagemDePasswordConfirm = RegisterPage.getMensagemDePasswordConfirm;
    
    return [
        {
            email:'{del}',
            name:'{del}',
            password:'{del}', 
            passwordConfirm:'{del}',  
            validate: () => {            
                mensagemDeEmail().should('eq', 'É campo obrigatório');
                // mensagemDeName().should('eq', 'É campo obrigatório')
                mensagemDePassword().should('eq', 'É campo obrigatório');
                mensagemDePasswordConfirm().should('eq', 'É campo obrigatório');
                
            }
        },        
        {
            email: '{del}', 
            name: 'Random Person', 
            password: '12345678', 
            passwordConfirm: '12345678', 
            validate: () => {
                mensagemDeEmail().should('eq', 'É campo obrigatório')
            }
        },
        // {
        //     email: faker.internet.email(), 
        //     name: '{del}', 
        //     password: '12345678', 
        //     passwordConfirm: '12345678', 
        //     validate: () => {
        //         mensagemDeName().should('eq', 'É campo obrigatório')
        //     }
        // },      
        {
            email: faker.internet.email(),
            name: 'Random Person',
            password: '{del}', 
            passwordConfirm: '12345678', 
            validate: () => {
                mensagemDePassword().should('eq', 'É campo obrigatório')
            }
        },
        {
            email: faker.internet.email(),
            name: 'Random Person', 
            password: '12345678', 
            passwordConfirm: '{del}', 
            validate: () => {
                mensagemDePasswordConfirm().should('eq', 'É campo obrigatório')
            }
        }
    ];
}

// function validarMensagensDeErroEmCamposVazios(index) {
//     const mensagemDeEmail = RegisterPage.getMensagemDeEmail();
//     const mensagemDeName = RegisterPage.getMensagemDeName();
//     const mensagemDePassword = RegisterPage.getMensagemDePassword();
//     const mensagemDePasswordConfirm = RegisterPage.getMensagemDePasswordConfirm();

//     switch (index) {
//         case 0:
//             return (
//                 mensagemDeEmail === 'É campo obrigatório' &&
//                 mensagemDeName === 'É campo obrigatório' &&
//                 mensagemDePassword === 'É campo obrigatório' &&
//                 mensagemDePasswordConfirm === 'É campo obrigatório'
//             );
//         case 1:
//             return mensagemDeEmail === 'É campo obrigatório';
//         case 2:
//             return mensagemDeName === 'É campo obrigatório';
//         case 3:
//             return mensagemDePassword === 'É campo obrigatório';
//         case 4:
//             return mensagemDePasswordConfirm === 'É campo obrigatório';
//         default:
//             return false;
//     }
// }

module.exports = {
    getPessoaValida,
    getUsuarioComEmailInvalido,
    getUsuariosCamposVazios,
};