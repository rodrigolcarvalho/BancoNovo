package services.tela.terminal;

import java.util.Scanner;

import models.Contas.Conta;
import models.Contas.ContaCorrente;
import models.Contas.ContaInvestimento;
import models.Contas.ContaPoupanca;
import models.Pessoa.Pessoa;
import models.Pessoa.PessoaFisica;
import services.GerenciadorContas;
import services.tela.Operacoes;

public class TerminalCliente extends Terminal{

    private Conta[] contasDoCliente;
    private GerenciadorContas gerenteContas;
    private Scanner sc = new Scanner(System.in);
    private Pessoa pessoa;

    public TerminalCliente(Pessoa pessoa, Conta[] contasDoCliente) {
        super(new String[]{"SAIR", "ABRIR CONTA", "SACAR", "DEPOSITAR", "TRANSFERÊNCIA", "INVESTIR", "CONSULTAR SALDO"});

        this.contasDoCliente = contasDoCliente;
        this.pessoa = pessoa;
        gerenteContas = GerenciadorContas.getInstance();
    }

    @Override
    public void selecionarOpcao(int opcao) {
        switch(opcao) {
            case 0:
                break;
            case 1:
                System.out.println("Digite: \n 1- para nova conta corrente");
                System.out.println(" 2- para conta investimento");
                if(pessoa instanceof PessoaFisica) {
                    System.out.println(" 3- para nova conta poupança");
                }
                int tipo = sc.nextInt();
                sc.nextLine();
                if(tipo == 1){
                    gerenteContas.adicionarConta(new ContaCorrente(pessoa));
                    System.out.println("Conta Corrente criada com sucesso!");
                }
                else if (tipo == 2){
                    gerenteContas.adicionarConta(new ContaInvestimento(pessoa));
                    System.out.println("Conta Investimento criada com sucesso!");
                }
                else if (tipo == 3 && pessoa instanceof PessoaFisica){
                    gerenteContas.adicionarConta(new ContaPoupanca(pessoa));
                    System.out.println("Conta Poupança criada com sucesso!");
                }

                contasDoCliente = gerenteContas.getContasPorPessoa(pessoa);
                break;
            case 2: //SACAR
                Operacoes.operacaoSaque(contasDoCliente);

                break;
            case 3: // DEPOSITAR
                Operacoes.operacaoDeposito(contasDoCliente);
                break;
            case 6:
                System.out.println("---------- SALDOS -----------");
                System.out.println("Saldos de suas contas: ");
                contasDoCliente = gerenteContas.getContasPorPessoa(pessoa);
                if(contasDoCliente != null){
                    for (Conta conta : contasDoCliente) {
                        conta.imprimirSaldo();
                    }
                }
                else {
                    System.out.println("Contas não encontradas para este cliente");
                }
                break;
            default:
                break;
        }
    }
}
