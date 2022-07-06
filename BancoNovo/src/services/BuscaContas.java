package services;

import java.util.Arrays;

import models.Contas.Conta;
import models.Pessoa.Pessoa;

public class BuscaContas {

    public static Conta[] buscaContasPorPessoa (Conta[] contas, Pessoa pessoa){
        Conta[] contasEncontradas = new Conta[0];
        for (Conta conta : contas) {
            if(conta.getPessoa()== pessoa){
                contasEncontradas = newConta(contasEncontradas, conta);
            }
        }
        return contasEncontradas;
    }

    private static Conta[] newConta(Conta[] contas, Conta conta){
        Conta[] novo = new Conta[contas.length+1];
        for (int i = 0; i < contas.length; i++) {
            novo[i] = contas[i];
        }
        novo[novo.length-1] = conta;

        contas = novo;
        System.out.println(Arrays.deepToString(contas));
        return contas;
    }
}
