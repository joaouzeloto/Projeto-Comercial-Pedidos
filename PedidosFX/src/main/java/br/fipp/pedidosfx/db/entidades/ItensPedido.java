package br.fipp.pedidosfx.db.entidades;

public class ItensPedido
{
    private int itp_id;
    private int itp_quant;
    private double itp_preco;
    private int pro_id;
    private int ped_id;

    public ItensPedido(){}

    public ItensPedido(int itp_id, int itp_quant, double itp_preco, int pro_id, int ped_id) {
        this.itp_id = itp_id;
        this.itp_quant = itp_quant;
        this.itp_preco = itp_preco;
        this.pro_id = pro_id;
        this.ped_id = ped_id;
    }

    public int getItp_id() {
        return itp_id;
    }

    public void setItp_id(int itp_id) {
        this.itp_id = itp_id;
    }

    public int getItp_quant() {
        return itp_quant;
    }

    public void setItp_quant(int itp_quant) {
        this.itp_quant = itp_quant;
    }

    public double getItp_preco() {
        return itp_preco;
    }

    public void setItp_preco(double itp_preco) {
        this.itp_preco = itp_preco;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getPed_id() {
        return ped_id;
    }

    public void setPed_id(int ped_id) {
        this.ped_id = ped_id;
    }
}
