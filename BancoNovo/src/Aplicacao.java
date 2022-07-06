import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) throws Exception {
		String[] opcoes1 = {"SAIR", "ABRIR CONTA", "SACAR", "DEPOSITAR", "TRANSFERÊNCIA", "INVESTIR", "CONSULTAR SALDO"};
        
		Scanner sc = new Scanner(System.in);
		int opcao = 100;
        Aplicacao.print(opcoes1);
        while(opcao!= 0 ){
            opcao = sc.nextInt();
            System.out.printf("Você selecionou a opção: %s", opcoes1[opcao]);
            switch(opcao) {
                case 1:
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
