package services;

import java.util.Scanner;

import models.Contas.Conta;
import models.Pessoa.Pessoa;

public class Operacoes {
    public static void OperacaoSaque(Conta[] contas, Pessoa pessoa){
        Scanner sc = new Scanner(System.in);
        Conta[] contasDoCliente = BuscaContas.buscaContasPorPessoa(contas, pessoa);
        System.out.println("---------- SAQUE -----------");
        System.out.println("Escolha a conta: ");
        contasDoCliente = BuscaContas.buscaContasPorPessoa(contas, pessoa);
        if(contasDoCliente.length >=1){
            for (Conta conta : contasDoCliente) {
                System.out.print(conta.getTipo() + " nº " +conta.getNumero() + ": ");
                System.out.println(conta.getSaldo());
            }
        }
        else {
            System.out.println("Contas não encontradas para este cliente");
            sc.close();
            return;
        }
        int num = sc.nextInt();
        sc.nextLine();
        if(BuscaContas.buscaContaPorNumero(contasDoCliente, num) != null){
            System.out.println("Digite o valor desejado: ");
            Double valor = sc.nextDouble();
            sc.nextLine();
            BuscaContas.buscaContaPorNumero(contasDoCliente, num).sacar(valor);
            System.out.println("Seu novo saldo é de: "+ BuscaContas.buscaContaPorNumero(contasDoCliente, num).getSaldo());
        }
        else {
            System.out.println("Conta não encontrada");
        }
        
    }
    public static void OperacaoDeposito (Conta[] contas, Pessoa pessoa){
        Scanner sc = new Scanner(System.in);
        Conta[] contasDoCliente = BuscaContas.buscaContasPorPessoa(contas, pessoa);
        System.out.println("---------- DEPÓSITO -----------");
        System.out.println("Escolha a conta: ");
        contasDoCliente = BuscaContas.buscaContasPorPessoa(contas, pessoa);
        if(contasDoCliente.length >=1){
            for (Conta conta : contasDoCliente) {
                System.out.print(conta.getTipo() + " nº " +conta.getNumero() + ": ");
                System.out.println(conta.getSaldo());
            }
        }
        else {
            System.out.println("Contas não encontradas para este cliente");
            return;
        }
        int num = sc.nextInt();
        sc.nextLine();
        if(BuscaContas.buscaContaPorNumero(contasDoCliente, num) != null){
            System.out.println("Digite o valor desejado: ");
            Double valor = sc.nextDouble();
            sc.nextLine();
            BuscaContas.buscaContaPorNumero(contasDoCliente, num).deposito(valor);
            System.out.println("Seu novo saldo é de: "+ BuscaContas.buscaContaPorNumero(contasDoCliente, num).getSaldo());
        }
        else {
            System.out.println("Conta não encontrada");
        }

    }
}
