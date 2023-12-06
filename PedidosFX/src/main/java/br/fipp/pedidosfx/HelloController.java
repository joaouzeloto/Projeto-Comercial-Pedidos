package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.ClienteDAL;
import br.fipp.pedidosfx.db.dals.PedidoDAL;
import br.fipp.pedidosfx.db.entidades.Cliente;
import br.fipp.pedidosfx.db.entidades.Pedido;
import br.fipp.pedidosfx.util.ModalTable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onProdutos(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("produto-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("Produtos");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onClientes(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("Clientes");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onCategorias(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categoria-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage=new Stage();
        stage.setTitle("Categoria");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onSair(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja sair?");
        if(alert.showAndWait().get()== ButtonType.OK)
            Platform.exit();
    }

    public void onNovoPedido(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NovoPedidoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("Novo Pedido");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onAbrirPedido(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pedido-pesquisa-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("Pedidos");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onRelClientes(ActionEvent actionEvent) {
    }

    public void onRelProdutos(ActionEvent actionEvent) {
    }

    public void onRelPedidos(ActionEvent actionEvent) {
    }

    public void onSobre(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("PedidosFX\nversão 1.0\nDesenvolvido por:\nJoão Uzeloto\nHumberto Stuani\nDiego Godoy");
        alert.showAndWait();
    }


}