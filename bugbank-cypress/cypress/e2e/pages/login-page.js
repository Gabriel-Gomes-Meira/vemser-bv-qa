export class LoginPage {
    static logar(email, senha) {
        this.preencherCampoEmail(email);
        this.preencherCampoSenha(senha);
        this.clicarBotaoAcessar();
    }

    static acessarPagina() {
        cy.visit('https://bugbank.netlify.app');
    }

    static preencherCampoEmail(email) {
        cy.get("div.card__login input[type='email']").type(email);
    }

    static preencherCampoSenha(senha) {
        cy.get("div.card__login input[type='password']").type(senha).tab();
    }

    static clicarBotaoAcessar() {
        cy.get("div.login__buttons > button[type=submit]").click();
    }

    static clicarBotaoRegistrar() {
        cy.get("div.login__buttons > button[type=button]").click();
    }

    static getMensagemCampoEmail() {
        return cy.get("div.card__login > form > div.input__child > p").invoke('text');
    }

    static getMensagemCampoSenha() {
        return cy.get("div.card__login div.login__password [class*='input__child'] p").invoke('text');
    }

    static getTextoModal() {
        cy.get("#modalText").should('be.visible');
        return cy.get("#modalText").invoke('text');
    }
}