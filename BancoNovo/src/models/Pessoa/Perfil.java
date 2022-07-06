package models.Pessoa;

public class Perfil {
    private Double taxaSaque;
    private Double taxaDeposito;
    private Double adicionalInvestimento;

    public Double getTaxaSaque() {
        return this.taxaSaque;
    }

    public void setTaxaSaque(Double taxaSaque) {
        this.taxaSaque = taxaSaque;
    }

    public Double getTaxaDeposito() {
        return this.taxaDeposito;
    }

    public void setTaxaDeposito(Double taxaDeposito) {
        this.taxaDeposito = taxaDeposito;
    }

    public Double getAdicionalInvestimento() {
        return this.adicionalInvestimento;
    }

    public void setAdicionalInvestimento(Double adicionalInvestimento) {
        this.adicionalInvestimento = adicionalInvestimento;
    }

}
