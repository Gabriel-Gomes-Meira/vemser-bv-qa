
export class HomePage {    

    static saldo = 'p#textBalance > span'

    static getSaldo() {
        return cy.get(this.saldo).invoke('text');
    }
}

