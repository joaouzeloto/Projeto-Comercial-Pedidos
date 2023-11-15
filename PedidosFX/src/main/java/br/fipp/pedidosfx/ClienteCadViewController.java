package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.entidades.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ClienteCadViewController implements Initializable {
    public TextField tfId;
    public TextField tfDocumento;
    public TextField tfNome;
    public TextField tfEndereco;
    public TextField tfBairro;
    public TextField tfCidade;
    public TextField tfCep;
    public TextField tfUf;
    public TextField tfEmail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onConfirmar(ActionEvent actionEvent) {
        // salvar ou alterar o cliente

        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onCancelar(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }


}
