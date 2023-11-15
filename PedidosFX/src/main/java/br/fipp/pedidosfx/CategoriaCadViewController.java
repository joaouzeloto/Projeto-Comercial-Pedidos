package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.CategoriaDAL;
import br.fipp.pedidosfx.db.entidades.Categoria;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CategoriaCadViewController extends Application {

    public TextField tfIdC;
    public TextField tfCategoria;
    public TextField tfDescricao;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    public void onConfirmar(ActionEvent actionEvent) {
        Categoria cat1 = new Categoria(tfCategoria.getText(),tfDescricao.getText());
        System.out.println(cat1.getNome() + " " +cat1.getDescricao());
        CategoriaDAL cat2 = new CategoriaDAL();
        cat2.gravar(cat1);
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onCancelar(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }
}
