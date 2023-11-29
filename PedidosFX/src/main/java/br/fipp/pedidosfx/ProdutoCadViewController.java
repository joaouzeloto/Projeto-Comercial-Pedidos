package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.dals.CategoriaDAL;
import br.fipp.pedidosfx.db.dals.ProdutoDAL;
import br.fipp.pedidosfx.db.entidades.Categoria;
import br.fipp.pedidosfx.db.entidades.Produto;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;

public class ProdutoCadViewController implements Initializable {

    @FXML
    private ComboBox<Categoria> cbCategoria;
    public TextField tfId;
    public TextField tfNome;
    public TextField tfPreco;
    public TextField tfEstoque;
    public TextField tfCategoria;

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


    public void onCancelar(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }


    @FXML
    void onConfirmar(ActionEvent actionEvent) {
        Categoria categoria = new Categoria();
        categoria.setId(11);
        Produto produto = new Produto(tfNome.getText(),categoria,Double.parseDouble(tfPreco.getText()),Integer.parseInt(tfEstoque.getText()));
        ProdutoDAL dal = new ProdutoDAL();
        if(ProdutoViewController.produto==null)
        {
            if(!dal.gravar(produto))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(DBSingleton.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        else
        {
            produto.setId(ProdutoViewController.produto.getId());
            if(!dal.alterar(produto))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(DBSingleton.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }
}
