package services.tela.terminal;

public abstract class Terminal {
    protected String[] opcoes;

    public Terminal(String[] opcoes) {
        this.opcoes = opcoes;
    }

    public abstract void selecionarOpcao(int opcao);

    public void print() {
        System.out.println("-------------------- MENU --------------------");
        for (int i = 0; i < opcoes.length; i++) {
            System.out.printf("%02d - %s\n", i, opcoes[i]);
        }
        System.out.println("DIGITE A OPÇÃO: ");
    }
}
