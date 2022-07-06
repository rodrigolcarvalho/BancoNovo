package models.Contas;

import models.Pessoa.Pessoa;

/**
 * Conta
 */
public abstract class Conta {
    // TODO colocar contador de contas criadas para dar o numero delas
    int numero_contas = 0;

    private Pessoa pessoa;
    private Double saldo;
    private int numero;

    public Conta(Pessoa pessoa) {
        numero_contas++;
        this.pessoa = pessoa;
        this.saldo = 0.0;
        this.numero = numero_contas;
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

        this.saldo -= valor;
        return true;
    }


    public int getNumero_contas() {
        return this.numero_contas;
    }

    public void setNumero_contas(int numero_contas) {
        this.numero_contas = numero_contas;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}