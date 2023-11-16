package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.entidades.Categoria;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import br.fipp.pedidosfx.db.entidades.Cliente;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaViewController extends Application {


    @FXML
    private TableView<Categoria> tableView;
    @FXML
    private TableColumn<Categoria, Integer> colID;
    @FXML
    private TableColumn<Categoria, String> colNome;
    @FXML
    private TableColumn<Categoria, String> colDescricao;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categoria-view.fxml"));
            Object root = fxmlLoader.load();
            CategoriaViewController controller = fxmlLoader.getController();

            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            primaryStage.setTitle("Sua Aplicação");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Carregar dados do banco de dados
            //controller.carregarDadosDoBanco();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
