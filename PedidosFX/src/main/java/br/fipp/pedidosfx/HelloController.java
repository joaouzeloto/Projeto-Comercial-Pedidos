package br.fipp.pedidosfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onProdutos(ActionEvent actionEvent) {
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
    }

    public void onSair(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja sair?");
        if(alert.showAndWait().get()== ButtonType.OK)
            Platform.exit();
    }

    public void onNovoPedido(ActionEvent actionEvent) {
    }

    public void onAbrirPedido(ActionEvent actionEvent) {
    }

    public void onRelClientes(ActionEvent actionEvent) {
    }

    public void onRelProdutos(ActionEvent actionEvent) {
    }

    public void onRelPedidos(ActionEvent actionEvent) {
    }

    public void onSobre(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("PedidosFX\nversão 0.1\nDesenvolvido por:\nSilvio Carro");
        alert.showAndWait();
    }


}