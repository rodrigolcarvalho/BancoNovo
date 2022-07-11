package services;

import models.Pessoa.Pessoa;

public class GerenciadorPessoas {
    private static GerenciadorPessoas instancia;
    private Pessoa[] pessoas;

    private GerenciadorPessoas(){
        pessoas = new Pessoa[0];
    }

    public static synchronized GerenciadorPessoas getInstance() {
		if (instancia == null)
            instancia = new GerenciadorPessoas();

		return instancia;
	}

    public void adicionarPessoa(Pessoa pessoa){
        Pessoa[] novo = new Pessoa[pessoas.length+1];
        for (int i = 0; i < pessoas.length; i++) {
            novo[i] = pessoas[i];
        }
        novo[novo.length-1] = pessoa;

        pessoas = novo;
    }

    public boolean jaExiste(String cpfCnpj){
        return obterPessoa(cpfCnpj) != null;
    }

    public Pessoa obterPessoa(String cpfCnpj) {
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i].getID().equals(cpfCnpj))
                return pessoas[i];
        }

        return null;
    }

    public void listarPessoas(){
        System.out.println("Lista de pessoas: ");
        for (Pessoa pessoa : pessoas) {
            System.out.printf("- %s\n", pessoa.getNome());
        }
    }
}
