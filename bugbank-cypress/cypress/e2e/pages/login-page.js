export class LoginPage {

    static url = 'https://bugbank.netlify.app'
    static emailInput = "div.card__login input[type='email']"
    static senhaInput = "div.card__login input[type='password']"
    static botaoAcessar = "div.card__login > form > div.login__buttons > button[type=submit]"
    static botaoRegistrar = "div.card__login > form > div.login__buttons > button[type=button]"
    static mensagemCampoEmail = "div.card__login > form > div.input__child > p"
    static mensagemCampoSenha = "div.card__login div.login__password [class*='input__child'] p"
    static textoModal = "#modalText"
    
    static logar(email, senha) {
        this.preencherCampoEmail(email);
        this.preencherCampoSenha(senha);
        this.clicarBotaoAcessar();
    }

    static acessarPagina() {
        cy.visit(this.url);
    }

    static preencherCampoEmail(email) {
        cy.get(this.emailInput).type(email);
    }

    static preencherCampoSenha(senha) {
        cy.get(this.senhaInput).type(senha).tab();
    }

    static clicarBotaoAcessar() {
        cy.get(this.botaoAcessar).click();
    }

    static clicarBotaoRegistrar() {
        cy.get(this.botaoRegistrar).click();
    }

    static getMensagemCampoEmail() {
        return cy.get(this.mensagemCampoEmail).invoke('text');
    }

    static getMensagemCampoSenha() {
        return cy.get(this.mensagemCampoSenha).invoke('text');
    }

    static getTextoModal() {
        cy.get(this.textoModal).should('be.visible');
        return cy.get(this.textoModal).invoke('text');
    }
}