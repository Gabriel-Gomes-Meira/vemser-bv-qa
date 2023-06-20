import { tr } from "@faker-js/faker";

export class RegisterPage {
    static acessarPagina() {
        cy.visit('https://bugbank.netlify.app/');
    }

    static registrarUsuarioComSaldo(email, name, password, passwordConfirm) {
        this.abrirFormularioDeRegistro();
        this.preencherCampoEmail(email);
        this.preencherCampoName(name);
        this.preencherCampoPassword(password);
        this.preencherCampoPasswordConfirm(passwordConfirm);
        this.clicarCheckContaComSaldo();
        this.clicarBotaoCadastrar();
    }

    static registrarUsuarioSemSaldo(email, name, password, passwordConfirm) {
        this.abrirFormularioDeRegistro();
        this.preencherCampoEmail(email);
        this.preencherCampoName(name);
        this.preencherCampoPassword(password);
        this.preencherCampoPasswordConfirm(passwordConfirm);
        this.clicarBotaoCadastrar();
    }

    static abrirFormularioDeRegistro() {
        cy.get("div.login__buttons > button[type=button]").click();
    }

    static preencherCampoEmail(email) {
        cy.get("div.card__register input[type='email']").type(email, {
            force: true,            
        });
    }

    static preencherCampoName(name) {
        cy.get("input[type='name']").type(name, {
            force: true,
        });
    }

    static preencherCampoPassword(password) {
        cy.get("div.card__register input[name='password']").type(password, {
            force: true,
        });
    }

    static preencherCampoPasswordConfirm(passwordConfirm) {
        cy.get("div.card__register input[name='passwordConfirmation']").type(passwordConfirm, {
            force: true,
        }).tab();
    }

    static clicarCheckContaComSaldo() {
        cy.get("#toggleAddBalance").click({ force: true });
    }

    static clicarBotaoCadastrar() {
        cy.get("div.card__register > form > button[type=submit]").click({ force: true });
    }

    static getTextoModal() {
        cy.get("#modalText").should('be.visible');
        return cy.get("#modalText").invoke('text');
    }

    static fecharModal() {
        cy.get("#btnCloseModal").click();
    }

    static getMensagemDeEmail() {
        return cy.get("div.card__register input[type='email'] + p").invoke('text');
    }

    static getMensagemDeName() {
        return cy.get("input[type='name'] + p").invoke('text');
    }

    static getMensagemDePassword() {
        return cy.get("div.card__register input[name='password'] + p").invoke('text');
    }

    static getMensagemDePasswordConfirm() {
        return cy.get("div.card__register input[name='passwordConfirmation'] + p").invoke('text');
    }
}


