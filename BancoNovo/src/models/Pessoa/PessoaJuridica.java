package models.Pessoa;

public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica(String cnpj, String nome) {
        super(nome);
        this.cnpj = cnpj;
        super.taxaSaque = 0.005;
        super.adicionalInvestimento = 0.02;
    }


    public String getCnpj() {
        return this.cnpj;
    }

    @Override
    public String getID() {
        return this.getCnpj();
    }
}
