package models.Contas;

import models.Pessoa.Pessoa;

/**
 * Conta
 */
public abstract class Conta {
    // TODO definir classe Pessoa

    private Pessoa pessoa;
    private Double saldo;

    public Conta(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.saldo = 0.0;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Double getSaldo() {
        return this.saldo;
    }

    public Boolean deposito(Double valor) {
        this.saldo += valor;
        return true;
    }

    public Boolean sacar(Double valor) {
        if (this.getSaldo() < valor) return false;

        this.saldo += valor;
        return true;
    }

}