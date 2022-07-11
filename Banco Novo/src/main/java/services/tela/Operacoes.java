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
}
