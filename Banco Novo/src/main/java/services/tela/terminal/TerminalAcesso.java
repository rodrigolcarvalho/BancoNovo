package services.tela.terminal;

import java.util.Scanner;

import models.Pessoa.Pessoa;
import models.Pessoa.PessoaFisica;
import models.Pessoa.PessoaJuridica;
import services.GerenciadorPessoas;

public class TerminalAcesso extends Terminal {

    private GerenciadorTela gerenteTela;
    private GerenciadorPessoas gerentePessoas;
    private Scanner sc = new Scanner(System.in);

    public TerminalAcesso() {
        super(new String[]{ "SAIR", "CADASTRAR CLIENTE", "LOGIN" });
        gerentePessoas = GerenciadorPessoas.getInstance();
        gerenteTela = GerenciadorTela.getInstance();
    }

    @Override
    public void selecionarOpcao(int opcao) {
        if(opcao < opcoes.length)
            System.out.printf("Você selecionou a opção: %s\n", opcoes[opcao]);

        switch(opcao) {
            case 1:
                System.out.println("Digite 1 para pessoa física ou 2 para pessoa jurídica");
                int tipo = sc.nextInt();
                sc.nextLine();
            
                cadastrarCliente(tipo);
                break;
            case 2:
                login();
                break;
            default:
                gerentePessoas.listarPessoas();
                break;
        }
        
    }

    private void cadastrarCliente(int tipo) {
        if(tipo != 1 && tipo != 2) {
            System.out.println("\nEntrada Invalida\n");
            return;
        }

        Pessoa pessoa = null;
        if(tipo == 1){
            System.out.println("Digite o nome");
            String nome = sc.nextLine();
            System.out.println("Digite o CPF");
            String cpf = sc.nextLine();

            pessoa = gerentePessoas.obterPessoa(cpf);

            if (pessoa != null) {
                System.out.println("Você já realizou o cadastro " + pessoa.getNome() + " e será redirecionado ao login");
                
                gerenteTela.irParaTelaLogin(pessoa);
                return;
            } 

            gerentePessoas.adicionarPessoa(new PessoaFisica(cpf, nome));

        }else if(tipo == 2){
            System.out.println("Digite a razão social");
            String nome = sc.nextLine();
            System.out.println("Digite o CNPJ");
            String cnpj = sc.nextLine();

            pessoa = gerentePessoas.obterPessoa(cnpj);

            if (pessoa != null) {
                System.out.println("Você já realizou o cadastro " + pessoa.getNome() + " e será redirecionado ao login");
                gerenteTela.irParaTelaLogin(pessoa);
                return;
            } 

            gerentePessoas.adicionarPessoa(new PessoaJuridica(cnpj, nome));
        }

        System.out.println("Cliente cadastrado com sucesso!");
    }

    // Login pelo CPF ou CNPJ
    private void login() {
        System.out.println("Digite o CPF/CNPJ: ");
        String cpfCnpj = sc.nextLine();
        // System.out.println(cpfCnpj);

        Pessoa pessoa = gerentePessoas.obterPessoa(cpfCnpj);

        if(pessoa == null) {
            System.out.println("Cliente não encontrado");
            return;
        }

        gerenteTela.irParaTelaLogin(pessoa);
    }
}
