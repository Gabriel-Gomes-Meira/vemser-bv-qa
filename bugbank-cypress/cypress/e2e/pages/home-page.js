
export class HomePage {    

    static saldo = 'p#textBalance > span'
    static saudacao = '#textName'
    static accountNumber = '#textAccountNumber > span'
    static btnLogout = '#btnExit'

    static getSaldo = () => {
        return cy.get(this.saldo).invoke('text');
    }

    static getSaudacao = () => {
        return cy.get(this.saudacao).invoke('text');
    }

    static getAccountNumber = () => {
        return cy.get(this.accountNumber).invoke('text');
    }

    static logout = () => {
        cy.get(this.btnLogout).click();
    }

}

