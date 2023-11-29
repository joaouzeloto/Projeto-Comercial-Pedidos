package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.dals.ClienteDAL;
import br.fipp.pedidosfx.db.entidades.Cliente;
import br.fipp.pedidosfx.util.MaskFieldUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

public class ClienteCadViewController implements Initializable {
    public TextField tfId;
    public TextField tfNome;
    public TextField tfDocumento;
    public TextField tfCep;
    public TextField tfCidade;
    public TextField tfEndereco;
    public TextField tfEmail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{tfNome.requestFocus();});
        MaskFieldUtil.cepField(tfCep);
       // MaskFieldUtil.cpfField(tfDocumento);
        if (ClienteViewController.cliente!=null)
        {
            tfId.setText(""+ClienteViewController.cliente.getId());
            tfNome.setText(ClienteViewController.cliente.getNome());
            tfDocumento.setText(Long.toString(ClienteViewController.cliente.getDocumento()));
            tfCep.setText(ClienteViewController.cliente.getCep());
            tfCidade.setText(ClienteViewController.cliente.getCidade());
            tfEndereco.setText(ClienteViewController.cliente.getEndereco());
            tfEmail.setText(ClienteViewController.cliente.getEmail());
        }
    }
    public void onConfirmar(ActionEvent actionEvent) {
        Cliente cliente = new Cliente(Long.parseLong(tfDocumento.getText()),tfNome.getText(),tfEndereco.getText(),
                                      "",tfCidade.getText(),tfCep.getText(),"",tfEmail.getText());
        if(ClienteViewController.cliente==null) {
            if (!new ClienteDAL().gravar(cliente)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(DBSingleton.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        else {
            cliente.setId(ClienteViewController.cliente.getId());
            if (!new ClienteDAL().alterar(cliente)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(DBSingleton.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        // salvar ou alterar o cliente
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void onCancelar(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }

    public String consultaCep(String cep, String formato)
    {
        StringBuffer dados = new StringBuffer();
        try {
            URL url = new URL("https://viacep.com.br/ws/"+ cep + "/"+formato+"/");
            URLConnection con = url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setAllowUserInteraction(false);
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = "";
            while (null != (s = br.readLine()))
                dados.append(s);
            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dados.toString();
    }


    public void onBuscarCep(KeyEvent keyEvent) {
        if(tfCep.getText().length()==9)
        {
            Task task = new Task<Void>() {
                @Override
                protected Void call() {
                    //coloque aqui o código para acessar a API e processar o JSON
                    String json=consultaCep(tfCep.getText(),"json");
                    JSONObject my_obj = new JSONObject(json);
                    tfEndereco.setText(my_obj.getString("logradouro"));
                    return null;
                }
            };
            new Thread(task).start();

        }
    }
}
