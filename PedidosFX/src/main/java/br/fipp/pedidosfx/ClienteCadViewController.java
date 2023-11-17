package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.CategoriaDAL;
import br.fipp.pedidosfx.db.dals.ClienteDAL;
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
        Cliente c1 = new Cliente(0,Long.parseLong(tfDocumento.getText()),tfNome.getText(),tfEndereco.getText(),tfBairro.getText(),tfCidade.getText(),tfCep.getText(),tfUf.getText(),tfEmail.getText());
        ClienteDAL c2 = new ClienteDAL();
        System.out.println(c2.gravar(c1));
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onCancelar(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }


}
