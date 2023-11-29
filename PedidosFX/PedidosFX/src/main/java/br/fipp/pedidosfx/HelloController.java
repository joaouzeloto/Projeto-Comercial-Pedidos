package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.ClienteDAL;
import br.fipp.pedidosfx.db.dals.PedidoDAL;
import br.fipp.pedidosfx.db.entidades.Cliente;
import br.fipp.pedidosfx.db.entidades.Pedido;
import br.fipp.pedidosfx.util.ModalTable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onProdutos(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("produto-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("Produtos");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onClientes(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cliente-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("Clientes");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onCategorias(ActionEvent actionEvent) {


    }

    public void onSair(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja sair?");
        if(alert.showAndWait().get()== ButtonType.OK)
            Platform.exit();
    }

    public void onNovoPedido(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NovoPedidoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("Novo Pedido");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onAbrirPedido(ActionEvent actionEvent) {
        List<Pedido> pedidos = new PedidoDAL().get("");
        ModalTable mt=new ModalTable(pedidos,new String[]{"id","cliente","data"},"data");
        Stage stage=new Stage();
        stage.setScene(new Scene(mt));
        stage.setWidth(600); stage.setHeight(480); //stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        Pedido pedido = (Pedido)mt.getSelecionado();
        if (pedido!=null)
            System.out.println(pedido.getCliente().getNome());
    }

    public void onRelClientes(ActionEvent actionEvent) {
    }

    public void onRelProdutos(ActionEvent actionEvent) {
    }

    public void onRelPedidos(ActionEvent actionEvent) {
    }

    public void onSobre(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("PedidosFX\nversão 0.1\nDesenvolvido por:\nSilvio Carro");
        alert.showAndWait();
    }


}