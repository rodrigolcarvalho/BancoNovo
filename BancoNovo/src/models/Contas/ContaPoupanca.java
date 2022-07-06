package models.Contas;

import models.Pessoa.Pessoa;

public abstract class ContaPoupanca extends ContaMovimentacao {

    public ContaPoupanca(Pessoa pessoa) {
        super(pessoa);
    }
    
}
