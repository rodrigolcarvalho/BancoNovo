import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) throws Exception {
		String[] opcoesLogado = {"SAIR", "ABRIR CONTA", "SACAR", "DEPOSITAR", "TRANSFERÊNCIA", "INVESTIR", "CONSULTAR SALDO"};
		String[] opcoesIniciais = {"SAIR", "CADASTRAR CLIENTE", "LOGIN"};
        
		Scanner sc = new Scanner(System.in);
		int opcao = 100;
        Aplicacao.print(opcoesIniciais);
        while(opcao!= 0 ){
            opcao = sc.nextInt();
            System.out.printf("Você selecionou a opção: %s", opcoesIniciais[opcao]);
            switch(opcao) {
                case 1:
                //TODO cadastrar cliente
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
}
