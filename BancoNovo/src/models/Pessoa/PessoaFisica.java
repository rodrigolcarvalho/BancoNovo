package models.Pessoa;

public class PessoaFisica extends Pessoa{
    private String cpf;

    public PessoaFisica(String cpf, String nome) {
        super(nome);
        this.cpf = cpf;
    }

    @Override
    public String getID() {
        return this.cpf;
    }

}
