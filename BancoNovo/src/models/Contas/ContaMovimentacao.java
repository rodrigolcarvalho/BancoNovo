package models.Contas;

import models.Pessoa.Pessoa;

/**
 * ContaMovimentacao
 */
public abstract class ContaMovimentacao extends Conta {

    public ContaMovimentacao(Pessoa pessoa) {
        super(pessoa);
    }

    public Boolean transferir(Double valor, Conta contaDestino) {

        if (this.getSaldo() < valor) return false;

        this.sacar(valor);
        contaDestino.deposito(valor);
        return true;
        
    }
}