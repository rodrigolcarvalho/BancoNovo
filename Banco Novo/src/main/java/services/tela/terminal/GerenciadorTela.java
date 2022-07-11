package services.tela.terminal;

import java.util.Scanner;

import models.Contas.Conta;
import models.Pessoa.Pessoa;
import services.GerenciadorContas;

public class GerenciadorTela {
    private static GerenciadorTela instancia;

    private GerenciadorTela(){
    }

    public static synchronized GerenciadorTela getInstance() {
		if (instancia == null)
            instancia = new GerenciadorTela();

		return instancia;
	}

    private GerenciadorContas gerenteContas;
    private Scanner sc = new Scanner(System.in);
    private Terminal terminal;

    public void criarTela(){
        gerenteContas = GerenciadorContas.getInstance();
        
        terminal = new TerminalAcesso();

		int opcao = -1;
        do {
            try {
                terminal.print();
                opcao = sc.nextInt(); 
                terminal.selecionarOpcao(opcao);
            } catch (Exception e) {
                System.out.println("\nEntrada Invalida.\n");
                sc.nextLine();
            }
        } while( opcao != 0 );

        sc.close();
		System.out.println("\n-------------------- FIM ---------------------");
    }

    public void irParaTelaLogin(Pessoa pessoa) {
        System.out.println("Bem-vindo! " + pessoa.getNome());
        Conta[] contasDoCliente = gerenteContas.getContasPorPessoa(pessoa);
        terminal = new TerminalCliente(pessoa, contasDoCliente);
    }
}
