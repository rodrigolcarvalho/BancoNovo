package services.tela;

import models.Contas.Conta;
import models.Contas.ContaInvestimento;
import models.Contas.ContaMovimentacao;
import services.GerenciadorContas;

import java.util.Scanner;


public class Operacoes {

    private static GerenciadorContas gerenteContas;

    public static void operacaoSaque(Conta[] contasDoCliente){
        gerenteContas = GerenciadorContas.getInstance();

        if (contasDoCliente == null) {
            System.out.println("Não foi possível sacar, porque o cliente não tem conta");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("---------- SAQUE -----------");
        for (Conta conta : contasDoCliente) {
            conta.imprimirSaldo();
        }
        System.out.println("Digite o número da conta: ");
        int num = sc.nextInt();
       
        Conta contaSelecionada = gerenteContas.getContaPorNumero(contasDoCliente, num);
        if (contaSelecionada != null) {
            if (contaSelecionada.getSaldo() > 0) {
                System.out.println("Digite o valor desejado: ");
                Double valor = sc.nextDouble();
                boolean sucesso = contaSelecionada.sacar(valor);
                if (sucesso)
                    System.out.println("Seu novo saldo é de: " + contaSelecionada.getSaldo());
                else
                    System.out.println("Não foi possível sacar o dinheiro, pois seu saldo R$ "
                            + contaSelecionada.getSaldo() + " é inferior ao valor do saque");
            } else {
                System.out.println("Saldo insuficiente para realizar o saque com esta conta");
            }
        }
        else {
            System.out.println("Conta não encontrada");
        }
    }

    public static void operacaoDeposito(Conta[] contasDoCliente){
        gerenteContas = GerenciadorContas.getInstance();
        
        if (contasDoCliente == null) {
            System.out.println("Não foi possível depositar, porque o cliente não tem conta");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("---------- DEPÓSITO -----------");
        for (Conta conta : contasDoCliente) {
            conta.imprimirSaldo();
        }
        System.out.println("Digite o número da conta: ");
        int num = sc.nextInt();

        Conta contaSelecionada = gerenteContas.getContaPorNumero(contasDoCliente, num);
        if(contaSelecionada != null){
            System.out.println("Digite o valor desejado: ");
            Double valor = sc.nextDouble();
            contaSelecionada.deposito(valor);
            System.out.println("Seu novo saldo é de: "+ contaSelecionada.getSaldo());
        }
        else {
            System.out.println("Conta não encontrada");
        }
    }

    public static void operacaoTransferir(Conta[] contasDoCliente) {
        gerenteContas = GerenciadorContas.getInstance();
        
        Conta[] contasTransferenciaDoCliente = gerenteContas.getContasPorTipo(contasDoCliente, ContaMovimentacao.class);

        if (contasTransferenciaDoCliente == null) {
            System.out.println("Não foi possível transferir, porque o cliente não tem conta");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("---------- TRANSFERIR -----------");
        for (Conta conta : contasTransferenciaDoCliente) {
            conta.imprimirSaldo();
        }
        System.out.print("Digite o número da conta da qual deseja transferir: ");
        int numContaOrigem = sc.nextInt();
        Conta contaOrigem = gerenteContas.getContaPorNumero(contasTransferenciaDoCliente, numContaOrigem);
        if (contaOrigem != null) {
            System.out.print("Digite o número da conta para qual deseja transferir:");
            int numContaDestino = sc.nextInt();

            Conta contaDestino = gerenteContas.getContaPorNumero(numContaDestino);
            if(contaDestino != null){
                System.out.printf("Transferir para %s, conta %d \n", contaDestino.getPessoa().getNome(), contaDestino.getNumero());
                System.out.print("Digite o valor a transferir: ");
                Double valor = sc.nextDouble();
                if (valor > contaOrigem.getSaldo()) {
                    System.out.println("Saldo insuficiente.");
                    return;
                }
                contaDestino.deposito(valor);
                contaOrigem.sacar(valor);
                System.out.println("Seu novo saldo é de: "+ contaOrigem.getSaldo());
            }
            else {
                System.out.println("Conta não encontrada");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public static void operacaoInvestir(Conta[] contasDoCliente) {
        gerenteContas = GerenciadorContas.getInstance();
        
        // Conta[] contasInvestimentoDoCliente = gerenteContas.getContasPorTipo(contasDoCliente, ContaInvestimento.class);
        
        if (contasDoCliente == null) {
            System.out.println("Não é possível investir, porque o cliente não tem conta investimento");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("---------- INVESTIR -----------");
        for (Conta conta : contasDoCliente) {
            conta.imprimirSaldo();
        }
        System.out.print("Digite o número da conta que deseja investir: ");
        // int numContaOrigem = sc.nextInt();
        // Conta contaOrigem = gerenteContas.getContaPorNumero(contasInvestimentoDoCliente, numContaOrigem);
        // if (contaOrigem != null) {
        //     System.out.print("Digite o número da conta para qual deseja transferir:");
        //     int numContaDestino = sc.nextInt();

        //     Conta contaDestino = gerenteContas.getContaPorNumero(numContaDestino);
        //     if(contaDestino != null){
        //         System.out.printf("Transferir para %s, conta %d \n", contaDestino.getPessoa().getNome(), contaDestino.getNumero());
        //         System.out.print("Digite o valor a transferir: ");
        //         Double valor = sc.nextDouble();
        //         if (valor > contaOrigem.getSaldo()) {
        //             System.out.println("Saldo insuficiente.");
        //             return;
        //         }
        //         contaDestino.deposito(valor);
        //         contaOrigem.sacar(valor);
        //         System.out.println("Seu novo saldo é de: "+ contaOrigem.getSaldo());
        //     }
        //     else {
        //         System.out.println("Conta não encontrada");
        //     }
        // } else {
        //     System.out.println("Conta não encontrada.");
        // }
 
    }
}