package models.Pessoa;

public class PessoaFisica extends Pessoa{
    private final String cpf;

    public PessoaFisica(String cpf, String nome) {
        super(nome);
        this.cpf = cpf;
    }

    @Override
    public String getID() {
        return this.cpf;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "cpf='" + cpf + '\'' +
                '}';
    }
}
