package services;

import models.Contas.Conta;
import models.Contas.ContaCorrente;
import models.Contas.ContaInvestimento;
import models.Contas.ContaPoupanca;
import models.Pessoa.PessoaFisica;
import models.Pessoa.PessoaJuridica;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuscaContasTest {

    static PessoaFisica pf1, pf2;
    static PessoaJuridica pj1;
    static Conta conta1, conta2;
    static Conta[] contas;

    @BeforeAll
    static void inicializar() {
        pf1 = new PessoaFisica("123", "TestePF");
        pf2 = new PessoaFisica("789", "TestePF2");
        pj1 = new PessoaJuridica("456", "TestePJ");
        conta1 = new ContaInvestimento(pf1);
        conta2 = new ContaPoupanca(pf1);
        contas = new Conta[]
                {
                        conta1,
                        new ContaCorrente(pj1),
                        conta2,
                        new ContaPoupanca(pj1),
                        new ContaPoupanca(pf2)
                };
    }

    @Test
    void sucessoEncontraContaPorPessoaTest() {
        Conta[] encontradas = BuscaContas.buscaContasPorPessoa(contas, pf1);
        Conta[] resposta = new Conta[]{conta2, conta1};

        boolean existe = false;
        for (Conta conta : encontradas) existe = existe | existeNoArray(resposta, conta);
        assertTrue(encontradas.length == resposta.length && existe);
    }

    @Test
    void semContaNoEncontraContaPorPessoaTest() {
        Conta[] encontradas = BuscaContas.buscaContasPorPessoa(contas, new PessoaFisica("1", "LALA"));
        assertNull(encontradas);
    }

    boolean existeNoArray(Object[] lista, Object item) {
        boolean existe = false;
        for (Object buscando : lista) existe = existe | (buscando == item);
        return existe;
    }

    @Test
    void sucessoEncontraContaPorNumeroTest() {
        Conta conta = BuscaContas.buscaContaPorNumero(contas, contas[1].getNumero());
        assertEquals(conta, contas[1]);
    }

    @Test
    void semContaNoEncontraContaPorNumeroTest() {
        Conta encontrada = BuscaContas.buscaContaPorNumero(contas, -1);
        assertNull(encontrada);
    }
}