package br.fipp.pedidosfx.db.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private LocalDate data;
    private double frete;
    private double pedidoTotal;
    private List<Item> itens;

    public Pedido() {
        this(0,new Cliente(),LocalDate.now(),0);
    }

    public Pedido(int id, Cliente cliente, LocalDate data, double frete) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.frete = frete;
        itens=new ArrayList<>();
    }

    public double getPedidoTotal() {
        return pedidoTotal;
    }

    public void setPedidoTotal(double pedidoTotal) {
        this.pedidoTotal = pedidoTotal;
    }

    public Pedido(int id, Cliente cliente, LocalDate data, double frete, double total) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.frete = frete;
        itens=new ArrayList<>();
        this.pedidoTotal = total;
    }

    public Pedido(Cliente cliente, LocalDate data, double frete) {
        this(0,cliente,data,frete);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public List<Item> getItens() {
        return itens;
    }
    public void addItem(Produto produto, double preco, int quantidade)
    {
        itens.add(new Item(produto,preco,quantidade));
    }
    public void addItem(Item item)
    {
        itens.add(item);
    }
    public double getValor()
    {
        double valor=0;
        for(Item item : itens)
            valor+=item.getPreco();
        return valor;
    }
    @Override
    public String toString() {
        return cliente.getNome();
    }
}
