import models.Contas.Conta;
import models.Contas.ContaCorrente;
import models.Contas.ContaInvestimento;
import models.Contas.ContaPoupanca;
import models.Pessoa.Pessoa;
import models.Pessoa.PessoaFisica;
import models.Pessoa.PessoaJuridica;
import services.GerenciadorContas;
import services.GerenciadorPessoas;
import services.tela.Operacoes;

import java.util.Scanner;

public class Aplicacao {
    static GerenciadorPessoas gerentePessoas;
    static GerenciadorContas gerenteContas;

    static Scanner sc = new Scanner(System.in);
    static String[] opcoesIniciais = {"SAIR", "CADASTRAR CLIENTE", "LOGIN"};

    public static void main(String[] args) throws Exception {
        gerentePessoas = GerenciadorPessoas.getInstance();
        gerenteContas = GerenciadorContas.getInstance();

		int opcao = -1;
        do {
            try {
                print(opcoesIniciais);
                opcao = sc.nextInt();
                if(opcao<opcoesIniciais.length){
                    System.out.printf("Você selecionou a opção: %s\n", opcoesIniciais[opcao]);
                }
                switch(opcao) {
                    case 1:
                        cadastrarCliente();
                        break;
                    case 2:
                        login();
                        break;
                    default:
                        gerentePessoas.listarPessoas();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Entrada Invalida.");
            }
        } while( opcao != 0 );

        sc.close();
		System.out.println("\n-------------------- FIM ---------------------");
    }

    
    public static void cadastrarCliente() {
        Pessoa pessoa = null;
        
        System.out.println("Digite 1 para pessoa física ou 2 para pessoa jurídica");
        int tipo = sc.nextInt();
        sc.nextLine();
        
        if(tipo == 1){
            System.out.println("Digite o nome");
            String nome = sc.nextLine();
            System.out.println("Digite o CPF");
            String cpf = sc.nextLine();

            pessoa = gerentePessoas.obterPessoa(cpf);

            if (pessoa != null) {
                System.out.println("Você já realizou o cadastro " + pessoa.getNome() + " e será redirecionado ao login");
                telaLogin(pessoa);
            } else {
                gerentePessoas.adicionarPessoa(new PessoaFisica(cpf, nome));
            }
        }
        else if(tipo == 2){
            System.out.println("Digite a razão social");
            String nome = sc.nextLine();
            System.out.println("Digite o CNPJ");
            String cnpj = sc.nextLine();

            pessoa = gerentePessoas.obterPessoa(cnpj);

            if (pessoa != null) {
                System.out.println("Você já realizou o cadastro " + pessoa.getNome() + " e será redirecionado ao login");
                telaLogin(pessoa);
            } 
            gerentePessoas.adicionarPessoa(new PessoaJuridica(cnpj, nome));
        }
    }


    public static void print(String[] funcionalidades) {
        System.out.println("-------------------- MENU --------------------");
        for (int i = 0; i < funcionalidades.length; i++) {
            System.out.printf("%02d - %s\n", i, funcionalidades[i]);
        }
        System.out.println("DIGITE A OPÇÃO: ");
    }

    // Login pelo CPF ou CNPJ
    public static void login() {
        System.out.println("Digite o CPF/CNPJ: ");
        sc.nextLine();
        String cpfCnpj = sc.nextLine();
        System.out.println(cpfCnpj);

        Pessoa pessoa = gerentePessoas.obterPessoa(cpfCnpj);

        if(pessoa == null) {
            System.out.println("Cliente não encontrado");
            return;
        }

        telaLogin(pessoa);
    }
    
    public static void telaLogin(Pessoa pessoa) {
        System.out.println("Bem-vindo! " + pessoa.getNome());
        String[] opcoesLogado = {"SAIR", "ABRIR CONTA", "SACAR", "DEPOSITAR", "TRANSFERÊNCIA", "INVESTIR", "CONSULTAR SALDO"};
        int opcao;
        do {
            print(opcoesLogado);
            opcao = sc.nextInt();
            sc.nextLine();
            Conta[] contasDoCliente = gerenteContas.getContasPorPessoa(pessoa);
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
        }  while(opcao != 0);
    }
}