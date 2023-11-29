package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.dals.CategoriaDAL;
import br.fipp.pedidosfx.db.entidades.Categoria;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriaCadViewController implements Initializable {

    public TextField tfId;
    public TextField tfCategoria;
    public TextField tfDescricao;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Platform.runLater(()->{tfCategoria.requestFocus();});
        if (CategoriaViewController.categoria!=null)
        {
            tfId.setText(""+CategoriaViewController.categoria.getId());
            tfCategoria.setText(CategoriaViewController.categoria.getNome());
            tfDescricao.setText(CategoriaViewController.categoria.getDescricao());
        }
    }

    public void onConfirmar(ActionEvent actionEvent) {
        Categoria cat1 = new Categoria(tfCategoria.getText(),tfDescricao.getText());
        CategoriaDAL cat2 = new CategoriaDAL();
        if(CategoriaViewController.categoria==null)
        {
            if(!cat2.gravar(cat1))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(DBSingleton.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        else
        {
            cat1.setId(CategoriaViewController.categoria.getId());
            if(!cat2.alterar(cat1))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(DBSingleton.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onCancelar(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }
}
