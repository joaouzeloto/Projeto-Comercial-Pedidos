package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.CategoriaDAL;
import br.fipp.pedidosfx.db.dals.ProdutoDAL;
import br.fipp.pedidosfx.db.entidades.Categoria;
import br.fipp.pedidosfx.db.entidades.Produto;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;




public class ProdutoCadViewController extends Application  {
    public TextField tfNome;
    public TextField tfPreco;
    public TextField tfEstoque;
    public TextField tfIdCategoria;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    public void onConfirmar(ActionEvent actionEvent) {
        Categoria aux = new Categoria();
        CategoriaDAL busca = new CategoriaDAL();
        aux = busca.get(Integer.parseInt(tfIdCategoria.getText()));
        Produto p1 = new Produto(tfNome.getText(),aux,Double.parseDouble(tfPreco.getText()),Integer.parseInt(tfEstoque.getText()));
        ProdutoDAL p2 = new ProdutoDAL();
        p2.gravar(p1);
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onCancelar(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }

}
