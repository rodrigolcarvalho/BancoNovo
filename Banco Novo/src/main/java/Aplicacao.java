import services.tela.terminal.GerenciadorTela;

public class Aplicacao {

    public static void main(String[] args) throws Exception {
        GerenciadorTela gerenciadorTela = GerenciadorTela.getInstance();
        gerenciadorTela.criarTela();
    }
}