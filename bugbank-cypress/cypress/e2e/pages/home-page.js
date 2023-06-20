
export class HomePage {    

    static getSaldo() {
        return cy.get('p#textBalance > span').invoke('text');
    }
}

