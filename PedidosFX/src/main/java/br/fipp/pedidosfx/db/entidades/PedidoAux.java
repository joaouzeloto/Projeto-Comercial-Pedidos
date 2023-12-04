package br.fipp.pedidosfx.db.entidades;

import java.time.LocalDate;

public class PedidoAux {
    private int id;
    private String nome;
    private double preco;
    private LocalDate data;

    public PedidoAux(){}

    public PedidoAux(int id, String nome, double valor, LocalDate data) {
        this.id = id;
        this.nome = nome;
        this.preco = valor;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
