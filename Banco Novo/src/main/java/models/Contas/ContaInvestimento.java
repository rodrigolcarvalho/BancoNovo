package models.Contas;

import interfaces.Rentavel;
import models.Pessoa.Pessoa;
import models.Pessoa.PessoaJuridica;

public class ContaInvestimento extends Conta implements Rentavel {

    public ContaInvestimento(Pessoa pessoa) {
        super(pessoa);
    }

    @Override
    public void render() {
        Double rendimento = 1.01;
        if (super.getPessoa() instanceof PessoaJuridica) {
            rendimento += 0.02;
        }
        super.setSaldo(super.getSaldo()*rendimento);            
    }

    @Override
    public void investir(Double valor) {
        super.deposito(valor);
    }
    
}
