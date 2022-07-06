import java.util.Arrays;
import java.util.Scanner;

import models.Pessoa.Pessoa;
import models.Pessoa.PessoaFisica;

public class Aplicacao {
    static Pessoa[] pessoas = new Pessoa[0];
    public static void main(String[] args) throws Exception {
		String[] opcoesLogado = {"SAIR", "ABRIR CONTA", "SACAR", "DEPOSITAR", "TRANSFERÊNCIA", "INVESTIR", "CONSULTAR SALDO"};
		String[] opcoesIniciais = {"SAIR", "CADASTRAR CLIENTE", "LOGIN"};
        
		Scanner sc = new Scanner(System.in);
		int opcao = 100;
        print(opcoesIniciais);
        while(opcao!= 0 ){
            opcao = sc.nextInt();
            System.out.printf("Você selecionou a opção: %s", opcoesIniciais[opcao]);
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
                    }
                    else if(tipo == 2){
                        System.out.println("Digite a razão social");
                        String nome = sc.nextLine();
                        System.out.println("Digite o CNPJ");
                        String cnpj = sc.nextLine();   
                    }
                    break;
                case 2:
                //TODO login
                    break;
                default:
                    break;
            }
        }
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
        System.out.println(Arrays.deepToString(pessoas));
    }
}