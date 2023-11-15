package br.fipp.pedidosfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import br.fipp.pedidosfx.db.entidades.Cliente;

import java.io.IOException;

public class CategoriaViewController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    public void onPesquisarC(KeyEvent keyEvent) {
    }

    public void onNovaCategoria(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categoria-cad-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage=new Stage();
        stage.setTitle("Categoria-Cad");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onFechar(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onAlterar(ActionEvent actionEvent) {
    }

    public void onApagar(ActionEvent actionEvent) {
    }
}
