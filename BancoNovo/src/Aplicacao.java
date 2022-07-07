import java.util.Scanner;

import models.Contas.Conta;
import models.Contas.ContaCorrente;
import models.Contas.ContaInvestimento;
import models.Contas.ContaPoupanca;
import models.Pessoa.Pessoa;
import models.Pessoa.PessoaFisica;
import models.Pessoa.PessoaJuridica;
import services.BuscaContas;
import services.Operacoes;

public class Aplicacao {
    static Pessoa[] pessoas = new Pessoa[0];
    static Conta[] contas = new Conta[0];
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
		String[] opcoesIniciais = {"SAIR", "CADASTRAR CLIENTE", "LOGIN"};
        //teste:
        newPessoa(new PessoaFisica("017", "Rodrigo"));
        
		int opcao;
        do{
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
                        newPessoa(new PessoaFisica(cpf, nome));
                        System.out.println("Cadastro realizado com sucesso\n\n");
                    }
                    else if(tipo == 2){
                        System.out.println("Digite a razão social");
                        String nome = sc.nextLine();
                        System.out.println("Digite o CNPJ");
                        String cnpj = sc.nextLine();   
                        newPessoa(new PessoaJuridica(cnpj, nome));
                        System.out.println("Cadastro realizado com sucesso\n\n");
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
        // System.out.println(Arrays.deepToString(pessoas));
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
    
    public static void login() {
        System.out.println("Digite o CPF/CNPJ: ");
        sc.nextLine();
        String cpfCnpj = sc.nextLine();
        System.out.println(cpfCnpj);
        boolean achou = false;
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getID().equals(cpfCnpj)) {
                telaLogin(pessoa);
                achou = true;
            }
        }
        if(!achou) {
            System.out.println("Cliente não encontrado");
        }
    }
    
    public static void telaLogin(Pessoa pessoa) {
        String[] opcoesLogado = {"SAIR", "ABRIR CONTA", "SACAR", "DEPOSITAR", "TRANSFERÊNCIA", "INVESTIR", "CONSULTAR SALDO"};
        int opcao; 
        int num;
        do{
            System.out.println("Bem-vindo! " + pessoa.getNome());
            print(opcoesLogado);
            opcao = sc.nextInt();
            sc.nextLine();
            Conta[] contasDoCliente = BuscaContas.buscaContasPorPessoa(contas, pessoa);
            switch(opcao) {
                case 0:
                    break;
                case 1:
                    System.out.println("Digite: \n 1 para nova conta corrente");
                    if(pessoa instanceof PessoaFisica){
                        System.out.println(" 2 para nova conta poupança");
                    }
                    System.out.println(" 3 para conta investimento");
                    int tipo = sc.nextInt();
                    sc.nextLine();
                    if(tipo == 1){
                        newConta(new ContaCorrente(pessoa));
                    }
                    else if (tipo == 2){
                        newConta(new ContaPoupanca(pessoa));
                    }
                    else if (tipo == 3){
                        newConta(new ContaInvestimento(pessoa));
                    }
                    contasDoCliente = BuscaContas.buscaContasPorPessoa(contas, pessoa);
                    break;
                case 2: //SACAR
                    Operacoes.OperacaoSaque(contas, pessoa);

                    break;
                case 3: // DEPOSITAR
                    Operacoes.OperacaoDeposito(contas, pessoa);
                    break;
                case 6:
                    System.out.println("---------- SALDOS -----------");
                    System.out.println("Saldos de suas contas: ");
                    contasDoCliente = BuscaContas.buscaContasPorPessoa(contas, pessoa);
                    if(contasDoCliente.length >=1){
                        for (Conta conta : contasDoCliente) {
                            System.out.print(conta.getTipo() + " nº " +conta.getNumero() + ": ");
                            System.out.println(conta.getSaldo());
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