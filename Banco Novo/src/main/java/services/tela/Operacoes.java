package services.tela;

import models.Contas.Conta;
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
        System.out.println("Digite o número da conta: ");
        for (Conta conta : contasDoCliente) {
            conta.imprimirSaldo();
        }
        int num = sc.nextInt();
        sc.nextLine();
        Conta contaSelecionada = gerenteContas.getContaPorNumero(contasDoCliente, num);
        if (contaSelecionada != null) {
            if (contaSelecionada.getSaldo() > 0) {
                System.out.println("Digite o valor desejado: ");
                Double valor = sc.nextDouble();
                sc.nextLine();
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
        if (contasDoCliente == null) {
            System.out.println("Não foi possível depositar, porque o cliente não tem conta");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("---------- DEPÓSITO -----------");
        System.out.println("Digite o número da conta: ");
        for (Conta conta : contasDoCliente) {
            conta.imprimirSaldo();
        }
        int num = sc.nextInt();
        sc.nextLine(); 
        Conta contaSelecionada = gerenteContas.getContaPorNumero(contasDoCliente, num);
        if(contaSelecionada != null){
            System.out.println("Digite o valor desejado: ");
            Double valor = sc.nextDouble();
            sc.nextLine();
            contaSelecionada.deposito(valor);
            System.out.println("Seu novo saldo é de: "+ contaSelecionada.getSaldo());
        }
        else {
            System.out.println("Conta não encontrada");
        }
    }

    public static void operacaoTransferir(Conta[] contasDoCliente) {
        gerenteContas = GerenciadorContas.getInstance();
        
        if (contasDoCliente == null) {
            System.out.println("Não foi possível transferir, porque o cliente não tem conta");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("---------- TRANSFREIR -----------");
        System.out.print("Digite o número da conta da qual deseja transferir: ");
        for (Conta conta : contasDoCliente) {
            conta.imprimirSaldo();
        }
        int numContaOrigem = sc.nextInt();
        sc.nextLine();
        Conta contaOrigem = gerenteContas.getContaPorNumero(contasDoCliente, numContaOrigem);
        if (contaOrigem != null) {
            System.out.print("Digite o número da conta para qual deseja transferir: \n");
            int numContaDestino = sc.nextInt();
            sc.nextLine();
            Conta contaDestino = gerenteContas.getContaPorNumero(numContaDestino);
            if(contaDestino != null){
                System.out.printf("Transferir para %s, conta %d \n", contaDestino.getPessoa().getNome(), contaDestino.getNumero());
                System.out.println("Digite o valor a transferir: ");
                Double valor = sc.nextDouble();
                if (valor > contaOrigem.getSaldo()) {
                    System.out.println("Saldo insuficiente.");
                    return;
                }
                sc.nextLine();
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

    
}
