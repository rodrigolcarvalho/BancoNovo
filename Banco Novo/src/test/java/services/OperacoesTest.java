package services;


import models.Contas.Conta;
import models.Contas.ContaCorrente;
import models.Contas.ContaInvestimento;
import models.Pessoa.PessoaFisica;
import models.Pessoa.PessoaJuridica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class OperacoesTest {

    static PessoaFisica pf1;
    static PessoaJuridica pj1;
    static Conta[] contas;

    @BeforeAll
    static void inicializar() {
        pf1 = new PessoaFisica("123", "TestePF");
        pj1 = new PessoaJuridica("456", "TestePJ");
        contas = new Conta[]{new ContaInvestimento(pf1), new ContaCorrente(pj1)};
    }

    @Test
    void saqueSucessoTest() {
        Conta c1 = BuscaContas.buscaContasPorPessoa(contas, pf1)[0];
        double saldoInicial = c1.getSaldo();

        double valor = 100.;
        c1.deposito(valor);
        boolean sucesso = c1.sacar(valor/2);
        double saldoFinal = c1.getSaldo();

        Assertions.assertTrue(sucesso);
        Assertions.assertEquals(saldoInicial + valor/2, saldoFinal);
    }

    @Test
    void depositoSucessoTest() {
        Conta c1 = BuscaContas.buscaContasPorPessoa(contas, pj1)[0];
        double saldoInicial = c1.getSaldo();

        double valor = 100.;
        c1.deposito(valor);
        double saldoFinal = c1.getSaldo();

        Assertions.assertEquals(saldoInicial + valor, saldoFinal);
    }
}