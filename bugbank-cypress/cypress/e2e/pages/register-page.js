 

export class RegisterPage {

    static url = 'https://bugbank.netlify.app/';
    static registerButton = "div.login__buttons > button[type=button]";
    static emailInput = "div.card__register input[type='email']";
    static nameInput = "input[type='name']";
    static passwordInput = "div.card__register input[name='password']";
    static passwordConfirmInput = "div.card__register input[name='passwordConfirmation']";
    static checkContaComSaldo = "#toggleAddBalance";
    static cadastroButton = "div.card__register > form > button[type=submit]";
    static modalText = "#modalText";
    static btnCloseModal = "#btnCloseModal";
    static mensagemDeEmail = "div.card__register input[type='email'] + p";
    static mensagemDeName = "input[type='name'] + p";
    static mensagemDePassword = "div.card__register input[name='password'] + p";
    static mensagemDePasswordConfirm = "div.card__register input[name='passwordConfirmation'] + p";

    

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

    static acessarPagina() {
        cy.visit(this.url);
    }

    static abrirFormularioDeRegistro() {
        cy.get(this.registerButton).click();
    }

    static preencherCampoEmail(email) {
        cy.get(this.emailInput).type(email, {
            force: true,            
        });
    }

    static preencherCampoName(name) {
        cy.get(this.nameInput).type(name, {
            force: true,
        });
    }

    static preencherCampoPassword(password) {
        cy.get(this.passwordInput).type(password, {
            force: true,
        });
    }

    static preencherCampoPasswordConfirm(passwordConfirm) {
        cy.get(this.passwordConfirmInput).type(passwordConfirm, {
            force: true,
        }).tab();
    }

    static clicarCheckContaComSaldo() {
        cy.get(this.checkContaComSaldo).click({ force: true });
    }

    static clicarBotaoCadastrar() {
        cy.get(this.cadastroButton).click({ force: true });
    }

    static getTextoModal() {
        cy.get(this.modalText).should('be.visible');
        return cy.get(this.modalText).invoke('text');
    }

    static fecharModal() {
        cy.get(this.btnCloseModal).click();
    }

    static getMensagemDeEmail = () => {
        return cy.get(this.mensagemDeEmail).invoke('text');
    }

    static getMensagemDeName = () => {
        return cy.get(this.mensagemDeName).invoke('text');
    }

    static getMensagemDePassword = () => {
        return cy.get(this.mensagemDePassword).invoke('text');
    }

    static getMensagemDePasswordConfirm = () => {
        return cy.get(this.mensagemDePasswordConfirm).invoke('text');
    }
}


