package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.CategoriaDAL;
import br.fipp.pedidosfx.db.entidades.Categoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProdutoCadViewController implements Initializable {

    @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    private TextField tfEstoque;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPreco;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarCategorias();
    }

    private void carregarCategorias() {
        List<Categoria> categorias=new CategoriaDAL().get("");
//        for (Categoria categoria : categorias) {
            cbCategoria.getItems().addAll(categorias);
  //      }
    }


    @FXML
    void onCancelar(ActionEvent event) {

    }

    @FXML
    void onConfirmar(ActionEvent event) {

    }


}
