package services;

import java.util.Arrays;

import models.Contas.Conta;
import models.Pessoa.Pessoa;

public class GerenciadorContas {
    private Conta[] contas;
    private static GerenciadorContas instancia;

    private GerenciadorContas() {
        contas = new Conta[0];
    }

    public static synchronized GerenciadorContas getInstance(){
        if(instancia == null) instancia = new GerenciadorContas();

        return instancia;
    }

    public void adicionarConta(Conta conta){
        Conta[] novo = new Conta[contas.length+1];
        for (int i = 0; i < contas.length; i++) {
            novo[i] = contas[i];
        }
        novo[novo.length-1] = conta;
        
        contas = novo;
    }

    public Conta getContaPorNumero (int numero){
        return getContaPorNumero(this.contas, numero);
    }

    public Conta getContaPorNumero (Conta[] contas, int numero){
        for (Conta conta : contas) 
            if(conta.getNumero() == numero)
                return conta;
                
        return null;
    }

    public Conta[] getContasPorPessoa(Pessoa pessoa){
        Conta[] contasEncontradas = new Conta[0];
        for (Conta conta : contas) {
            if(conta.getPessoa() == pessoa){
                contasEncontradas = appendConta(contasEncontradas, conta);
            }
        }
        return contasEncontradas.length > 0 ? contasEncontradas : null;
    }

    public Conta[] getContasPorTipo(Conta[] contas, Class classeConta) {
        Conta[] contasEncontradas = new Conta[0];
        for (Conta conta : contas) {
            if(classeConta.isInstance(conta)){
                contasEncontradas = appendConta(contasEncontradas, conta);
            }
        }
        return contasEncontradas.length > 0 ? contasEncontradas : null;
    }

    private Conta[] appendConta(Conta[] contas, Conta novaConta){
        int length = contas.length;
        Conta[] novo = Arrays.copyOf(contas, length + 1);
        novo[length] = novaConta;
        return novo;
    }


}
