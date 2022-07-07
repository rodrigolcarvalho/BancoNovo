package models.Contas;

import models.Pessoa.Pessoa;

/**
 * Conta
 */
public abstract class Conta {
    static int numero_contas = 0;

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

    public String getTipo(){
        if(this instanceof ContaCorrente){
            return "Conta Corrente";
        }
        else if(this instanceof ContaPoupanca){
            return "Conta Poupança";
        }
        else if(this instanceof ContaInvestimento){
            return "Conta Investimento";
        }
        else{
            return "Tipo indefinido";
        }
    }
}