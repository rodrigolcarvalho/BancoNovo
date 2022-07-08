package services;

import models.Contas.Conta;
import models.Pessoa.Pessoa;

public class BuscaContas {

    public static Conta[] buscaContasPorPessoa (Conta[] contas, Pessoa pessoa){
        Conta[] contasEncontradas = new Conta[0];
        for (Conta conta : contas) {
            if(conta.getPessoa() == pessoa){
                contasEncontradas = newConta(contasEncontradas, conta);
            }
        }
        return contasEncontradas.length > 0 ? contasEncontradas : null;
    }
    public static Conta buscaContaPorNumero (Conta[] contas, int numero){
        
        for (Conta conta : contas) {
            if(conta.getNumero()== numero){
                return conta;
            }
        }
        return null;
    }

    private static Conta[] newConta(Conta[] contas, Conta adicionarConta){
        Conta[] novo = new Conta[contas.length+1];
        System.arraycopy(contas, 0, novo, 0, contas.length);
        novo[contas.length] = adicionarConta;

        contas = novo;
        return contas;
    }
}
