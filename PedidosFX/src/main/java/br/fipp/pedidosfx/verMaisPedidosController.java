package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.ClienteDAL;
import br.fipp.pedidosfx.db.dals.ItensPedidosDAL;
import br.fipp.pedidosfx.db.dals.ProdutoDAL;
import br.fipp.pedidosfx.db.entidades.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class verMaisPedidosController implements Initializable {

    public Label labelName;
    public TableView<itensAux> tableView;
    public TableColumn<itensAux,Integer> colId;
    public TableColumn <itensAux, String> colProdutos;
    public TableColumn <itensAux, Double> colPreco;
    public TableColumn <itensAux, LocalDate> colQtde;

    public void onFechar(ActionEvent actionEvent) {((Button)actionEvent.getSource()).getScene().getWindow().hide();}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        colId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colProdutos.setCellValueFactory(new PropertyValueFactory<>("produtos"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colQtde.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        if (PedidosPesquisaController.pedidos!=null)
        {
            PedidoAux aux = new PedidoAux(PedidosPesquisaController.pedidos.getId(),PedidosPesquisaController.pedidos.getNome(),
                    PedidosPesquisaController.pedidos.getPreco(),PedidosPesquisaController.pedidos.getData());
            labelName.setText("Cliente:  "+aux.getNome()+"  ID da Compra:  "+aux.getId());
            preencherTabela("ped_id="+aux.getId());
        }
    }

    private void preencherTabela(String filtro)
    {
        List<ItensPedido> itens = new ItensPedidosDAL().get(filtro);
        List<itensAux> list = new ArrayList<itensAux>();
        Produto a = new Produto();
        ProdutoDAL b = new ProdutoDAL();
        for(int i=0;i<itens.size();i++)
        {
            a = b.get(itens.get(i).getPro_id());
            list.add(new itensAux(itens.get(i).getItp_id(),
                    itens.get(i).getItp_quant(),
                    itens.get(i).getItp_preco(),
                    a.getNome()));
        }
        tableView.setItems(FXCollections.observableArrayList(list));
    }
}
