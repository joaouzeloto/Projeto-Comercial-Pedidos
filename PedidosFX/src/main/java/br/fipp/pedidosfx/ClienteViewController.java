package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.ClienteDAL;
import br.fipp.pedidosfx.db.entidades.Cliente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClienteViewController implements Initializable {
    public TextField tfPesquisa;
    public TableView <Cliente>tableView;
    public TableColumn <Cliente,Integer> colID;
    public TableColumn <Cliente, String> colNome;
    public TableColumn <Cliente, String> colCidade;
    public TableColumn <Cliente, String> colTelefone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        preencherTabela("");
    }
    private void preencherTabela(String filtro)
    {
        List<Cliente> clientes = new ClienteDAL().get(filtro);
        tableView.setItems(FXCollections.observableArrayList(clientes));
    }

    public void onNovoCliente(ActionEvent actionEvent) throws IOException {
        abrirCadCliente();

    }

    public void onPesquisar(KeyEvent keyEvent) {
    }

    public void onAlterar(ActionEvent actionEvent) throws IOException {
        abrirCadCliente();
    }

    public void onApagar(ActionEvent actionEvent) {
    }

    public void onFechar(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }
    private void abrirCadCliente() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente-cad-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("Clientes");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
