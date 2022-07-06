package models.Pessoa;

public abstract class Pessoa {
    private String nome;
    private Perfil perfil;

    public abstract String getID ();

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

}
