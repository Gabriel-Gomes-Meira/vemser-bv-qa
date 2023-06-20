import {LoginPage} from '../../pages/login-page';

describe('LoginTest', () => {

    beforeEach(() => {
        LoginPage.acessarPagina();
    });

    it('Validar falha de autenticação sem credenciais', () => {        

        // Quando eu tentar fazer login sem informar credenciais
        LoginPage.logar('{del}', '{del}');

        // Então deve ser exibida a mensagem de erro
        LoginPage.getMensagemCampoEmail().should('eq', 'É campo obrigatório');
        LoginPage.getMensagemCampoSenha().should('eq', 'É campo obrigatório');

        // Dado que eu esteja na página de login
        LoginPage.acessarPagina();

        // Quando eu tentar fazer login sem informar a senha
        LoginPage.logar('anyone@email.com', '{del}');

        // Então deve ser exibida a mensagem de erro
        LoginPage.getMensagemCampoSenha().should('eq', 'É campo obrigatório');

        // Dado que eu esteja na página de login
        LoginPage.acessarPagina();

        // Quando eu tentar fazer login sem informar o email
        LoginPage.logar('{del}', '12345678');

        // Então deve ser exibida a mensagem de erro
        LoginPage.getMensagemCampoEmail().should('eq', 'É campo obrigatório');

    });

    it('Validar falha de autenticacao com credenciais invalidas', () => {

        // Quando eu tentar fazer login com credenciais inválidas
        const user = {
            email: 'invalidemail@example.com',
            password: 'invalidpassword'
        };
        LoginPage.logar(user.email, user.password);

        // Então deve ser exibida a mensagem de erro
        LoginPage.getTextoModal().should('eq', 'Usuário ou senha inválido.\nTente novamente ou verifique suas informações!');

    });

});
