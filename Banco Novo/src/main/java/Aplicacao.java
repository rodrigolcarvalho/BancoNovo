import models.Contas.Conta;
import models.Contas.ContaCorrente;
import models.Contas.ContaInvestimento;
import models.Contas.ContaPoupanca;
import models.Pessoa.Pessoa;
import models.Pessoa.PessoaFisica;
import models.Pessoa.PessoaJuridica;
import services.BuscaContas;
import services.tela.Operacoes;

import java.util.Scanner;

public class Aplicacao {
    static Pessoa[] pessoas = new Pessoa[0];
    static Conta[] contas = new Conta[0];
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
		String[] opcoesIniciais = {"SAIR", "CADASTRAR CLIENTE", "LOGIN"};
        //teste:
        newPessoa(new PessoaFisica("017", "Rodrigo"));
        
		int opcao;
        do {
            print(opcoesIniciais);
            opcao = sc.nextInt();
            if(opcao<opcoesIniciais.length){
                System.out.printf("Você selecionou a opção: %s\n", opcoesIniciais[opcao]);
            }
            switch(opcao) {
                case 1:
                //TODO cadastrar cliente
                    System.out.println("Digite 1 para pessoa física ou 2 para pessoa jurídica");
                    int tipo = sc.nextInt();
                    sc.nextLine();
                    if(tipo == 1){
                        System.out.println("Digite o nome");
                        String nome = sc.nextLine();
                        System.out.println("Digite o CPF");
                        String cpf = sc.nextLine();
                        Pessoa pessoa = pessoaJaExiste(pessoas, cpf);
                        if (pessoa != null) {
                            System.out.println("Você já realizou o cadastro " + pessoa.getNome() + " e será redirecionado ao login");
                            telaLogin(pessoa);
                        } else {
                            newPessoa(new PessoaFisica(cpf, nome));
                        }
                    }
                    else if(tipo == 2){
                        System.out.println("Digite a razão social");
                        String nome = sc.nextLine();
                        System.out.println("Digite o CNPJ");
                        String cnpj = sc.nextLine();
                        Pessoa pessoa = pessoaJaExiste(pessoas, cnpj);
                        if (pessoa != null) {
                            System.out.println("Você já realizou o cadastro " + pessoa.getNome() + " e será redirecionado ao login");
                        } else {
                            newPessoa(new PessoaJuridica(cnpj, nome));
                        }
                    }
                    break;
                case 2:
                    login();
                    break;
                    
                default:
                    printPessoas();
                    break;
            }
        } while( opcao != 0);
        sc. close();
		System.out.println("\n-------------------- FIM ---------------------");
    }

    
    public static void print(String[] funcionalidades) {
        System.out.println("-------------------- MENU --------------------");
        for (int i = 0; i < funcionalidades.length; i++) {
            System.out.printf("%02d - %s\n", i, funcionalidades[i]);
        }
        System.out.println("DIGITE A OPÇÃO: ");
    }

    public static void newPessoa(Pessoa pessoa){
        Pessoa[] novo = new Pessoa[pessoas.length+1];
        for (int i = 0; i < pessoas.length; i++) {
            novo[i] = pessoas[i];
        }
        novo[novo.length-1] = pessoa;

        pessoas = novo;
        System.out.println("Cadastro realizado com sucesso\n\n");
        // System.out.println(Arrays.deepToString(pessoas));
    }

    public static Pessoa pessoaJaExiste(Pessoa[] pessoas, String cpfCnpj) {
        Pessoa pessoa = null;
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i].getID().equals(cpfCnpj))
                pessoa = pessoas[i];
        }
        return pessoa;
    }

    public static void newConta(Conta conta){
        Conta[] novo = new Conta[contas.length+1];
        for (int i = 0; i < contas.length; i++) {
            novo[i] = contas[i];
        }
        novo[novo.length-1] = conta;
        
        contas = novo;
        // System.out.println(Arrays.deepToString(contas));
    }

    public static void printPessoas(){
        System.out.println("Lista de pessoas: ");
        for (Pessoa pessoa : pessoas) {
            System.out.printf("- %s\n", pessoa.getNome());
        }
    }

    // Login pelo CPF ou CNPJ
    public static void login() {
        System.out.println("Digite o CPF/CNPJ: ");
        sc.nextLine();
        String cpfCnpj = sc.nextLine();
        System.out.println(cpfCnpj);
        boolean achou = false;
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getID().equals(cpfCnpj)) {
                achou = true;
                telaLogin(pessoa);
            }
        }
        if(!achou) {
            System.out.println("Cliente não encontrado");
        }
    }
    
    public static void telaLogin(Pessoa pessoa) {
        System.out.println("Bem-vindo! " + pessoa.getNome());
        String[] opcoesLogado = {"SAIR", "ABRIR CONTA", "SACAR", "DEPOSITAR", "TRANSFERÊNCIA", "INVESTIR", "CONSULTAR SALDO"};
        int opcao;
        do {
            print(opcoesLogado);
            opcao = sc.nextInt();
            sc.nextLine();
            Conta[] contasDoCliente = BuscaContas.buscaContasPorPessoa(contas, pessoa);
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
                        newConta(new ContaCorrente(pessoa));
                        System.out.println("Conta Corrente criada com sucesso!");
                    }
                    else if (tipo == 2){
                        newConta(new ContaInvestimento(pessoa));
                        System.out.println("Conta Investimento criada com sucesso!");
                    }
                    else if (tipo == 3 && pessoa instanceof PessoaFisica){
                        newConta(new ContaPoupanca(pessoa));
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
                    contasDoCliente = BuscaContas.buscaContasPorPessoa(contas, pessoa);
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