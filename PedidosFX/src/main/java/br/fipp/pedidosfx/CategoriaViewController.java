package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.CategoriaDAL;
import br.fipp.pedidosfx.db.entidades.Categoria;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoriaViewController extends Application implements Initializable {

    public TableView<Categoria> tableView;
    public TableColumn <Categoria,Integer> colID;
    public TableColumn <Categoria, String> colNome;
    public TableColumn <Categoria, String> colDescricao;




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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        preencherTabela("");
    }
    private void preencherTabela(String filtro)
    {
        List<Categoria> categorias = new CategoriaDAL().get(filtro);
        tableView.setItems(FXCollections.observableArrayList(categorias));
    }
}
