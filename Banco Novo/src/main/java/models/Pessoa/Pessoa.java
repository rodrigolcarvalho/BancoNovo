package models.Pessoa;

public abstract class Pessoa {
    private String nome;
    protected Double taxaSaque = 0.0;
    protected Double adicionalInvestimento = 0.0;

    public abstract String getID();

    protected Pessoa(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
