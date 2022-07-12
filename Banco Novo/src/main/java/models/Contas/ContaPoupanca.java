package models.Contas;

import interfaces.Rentavel;
import models.Pessoa.Pessoa;

public class ContaPoupanca extends ContaMovimentacao implements Rentavel {

    public ContaPoupanca(Pessoa pessoa) {
        super(pessoa);
    }

    @Override
    public void render() {
        super.setSaldo(super.getSaldo()*(1.005)); 
    }

    @Override
    public void investir(Double valor) {
        super.deposito(valor);        
    }
    
}
