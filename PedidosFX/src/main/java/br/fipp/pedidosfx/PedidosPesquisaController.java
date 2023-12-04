package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.CategoriaDAL;
import br.fipp.pedidosfx.db.dals.PedidoDAL;
import br.fipp.pedidosfx.db.entidades.Categoria;
import br.fipp.pedidosfx.db.entidades.Cliente;
import br.fipp.pedidosfx.db.entidades.Pedido;
import br.fipp.pedidosfx.db.entidades.PedidoAux;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PedidosPesquisaController implements Initializable {

    public TableView<PedidoAux> tableView;
    public TableColumn <PedidoAux,Integer> colIdPedido;
    public TableColumn <PedidoAux, String> colNome;
    public TableColumn <PedidoAux, Double> colPreco;
    public TableColumn <PedidoAux, LocalDate> colData;
    public TextField tfPesquisa;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colIdPedido.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        preencherTabela("");
    }

    public void onPesquisarN(KeyEvent keyEvent) {
        String filtro=tfPesquisa.getText().toUpperCase();
        preencherTabela("upper(cli_id) like '%"+filtro+"%'");}

    public void onVerMais(ActionEvent actionEvent) {
    }

    public void onFechar(ActionEvent actionEvent) {((Button)actionEvent.getSource()).getScene().getWindow().hide();}

    private void preencherTabela(String filtro)
    {
        List<Pedido> pedidos = new PedidoDAL().get(filtro);
        List<PedidoAux> pedidosAux = new ArrayList<PedidoAux>();
        for(int i=0;i<pedidos.size();i++)
        {
            pedidosAux.add(new PedidoAux(pedidos.get(i).getId(),pedidos.get(i).getCliente().getNome(),
                    pedidos.get(i).getPedidoTotal(),pedidos.get(i).getData()));
        }
        tableView.setItems(FXCollections.observableArrayList(pedidosAux));
    }
}
