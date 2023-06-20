import {RegisterPage} from '../../pages/register-page'
import {LoginPage} from '../../pages/login-page'
import {HomePage} from '../../pages/home-page' 
import {
    getPessoaValida,
    getUsuarioComEmailInvalido,
    getUsuariosCamposVazios
} from '../../pages/datafactory';

describe('RegisterTest', () => {
  beforeEach(() => {
    RegisterPage.acessarPagina();
  });

  it('should register user with balance', () => {
    // Dado que eu esteja na página de registro    

    // E que eu informe uma pessoa válida
    const user = getPessoaValida();

    // Quando eu registrar o usuário informado com saldo
    RegisterPage.registrarUsuarioComSaldo(user.email, user.name, user.password, user.passwordConfirm);

    // Então deve ser exibida a mensagem de sucesso
    RegisterPage.getTextoModal().
    should('include','foi criada com sucesso');
    RegisterPage.fecharModal();

    // E devo conseguir fazer login com o usuário informado
    LoginPage.logar(user.email, user.password);
    
    // E devo ter 1000 de saldo    
    cy.url().should('eq', 'https://bugbank.netlify.app/home');
    HomePage.getSaldo().should('include', '1.000,00') 
  });

  it('should register user without balance', () => {
    // Dado que eu esteja na página de registro

    // E que eu informe uma pessoa válida
    const user = getPessoaValida();

    // Quando eu registrar o usuário informado sem saldo
    RegisterPage.registrarUsuarioSemSaldo(user.email, user.name, user.password, user.passwordConfirm);

    // Então deve ser exibida a mensagem de sucesso
    RegisterPage.getTextoModal()
    .should('include','foi criada com sucesso');
    RegisterPage.fecharModal();

    // E devo conseguir fazer login com o usuário informado
    LoginPage.logar(user.email, user.password);
    
    // E devo ter 0 de saldo
    cy.url().should('eq', 'https://bugbank.netlify.app/home');
    HomePage.getSaldo().should('include', '0,00') 
  });

  it('should fail to register user without filling required fields', () => {
    // Dado que eu esteja na página de registro

    // E informe um usuário com campos obrigatórios vazios
    getUsuariosCamposVazios().forEach((usuario) => {
      RegisterPage.acessarPagina();
      // Quando eu registrar o usuário informado
      RegisterPage.registrarUsuarioComSaldo(usuario.email, usuario.name, usuario.password, usuario.passwordConfirm);
      
      // Então deve ser exibida a mensagem de erro
      usuario.validate();
    });

  });

  it('should fail to register user with invalid email', () => {
    // Dado que eu esteja na página de registro

    // E informe um usuário com email inválido
    const user = getUsuarioComEmailInvalido();
    // Quando eu registrar o usuário informado
    RegisterPage.registrarUsuarioComSaldo(user.email, user.name, user.password, user.passwordConfirm);

    // Então deve ser exibida a mensagem de erro
    RegisterPage.getMensagemDeEmail().should('eq', 'Formato inválido');
  });

  it('should fail to register user with different passwords', () => {
    // Dado que eu esteja na página de registro

    // E informe um usuário com senhas diferentes
    const user = getPessoaValida();
    // Quando eu registrar o usuário informado
    RegisterPage.registrarUsuarioComSaldo(user.email, user.name, '123456', '1234567');

    // Então deve ser exibida a mensagem de erro
    RegisterPage.getTextoModal().should('eq', 'As senhas não são iguais.\n')
  });

  it('should fail to register user with already registered email', async () => {
    // Dado que eu esteja na página de registro

    // E informe um usuário com email já cadastrado
    const user = getPessoaValida();
    RegisterPage.registrarUsuarioComSaldo(user.email, user.name, user.password, user.passwordConfirm);
    RegisterPage.fecharModal();

    // Quando eu registrar o usuário informado
    RegisterPage.registrarUsuarioComSaldo(user.email, user.name, user.password, user.passwordConfirm);

    // Então deve ser exibida a mensagem de erro
    RegisterPage.getTextoModal().should('eq', 'O email já está sendo usado.');
  });
});
