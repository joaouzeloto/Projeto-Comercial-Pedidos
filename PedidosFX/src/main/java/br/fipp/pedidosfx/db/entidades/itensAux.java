package br.fipp.pedidosfx.db.entidades;

public class itensAux {
    private int ID;
    private int quantidade;
    private double preco;
    private String produtos;

    public itensAux(){}

    public itensAux(int ID, int quantidade, double preco, String produtos) {
        this.ID = ID;
        this.quantidade = quantidade;
        this.preco = preco;
        this.produtos = produtos;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }
}
