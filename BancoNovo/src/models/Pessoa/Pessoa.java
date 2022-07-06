package models.Pessoa;

public abstract class Pessoa {
    private String nome;
    private Perfil perfil;
    protected Double taxaSaque = 0.0;
    protected Double adicionalInvestimento = 0.0;

    public abstract String getID();

    protected Pessoa(String nome){
        this.nome = nome;
        // this.perfil = perfil;
    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Perfil getPerfil() {
        return this.perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            "}";
    }

}
