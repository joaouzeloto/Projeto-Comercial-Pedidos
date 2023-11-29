package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.dals.ClienteDAL;
import br.fipp.pedidosfx.db.dals.PedidoDAL;
import br.fipp.pedidosfx.db.dals.ProdutoDAL;
import br.fipp.pedidosfx.db.entidades.Cliente;
import br.fipp.pedidosfx.db.entidades.Item;
import br.fipp.pedidosfx.db.entidades.Pedido;
import br.fipp.pedidosfx.db.entidades.Produto;
import br.fipp.pedidosfx.util.MaskFieldUtil;
import br.fipp.pedidosfx.util.ModalTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class NovoPedidoViewController implements Initializable {
    private Cliente cliente=null;
    private Produto produto=null;

    @FXML
    private TableColumn<Item, Produto> colProduto;

    @FXML
    private TableColumn<Item, Integer> colQuant;

    @FXML
    private TableColumn<Item, Double> colValorTotal;

    @FXML
    private Label lbTotalPedido;

    @FXML
    private Spinner<Integer> spQuantidade;

    @FXML
    private TableView<Item> tableview;

    @FXML
    private TextField tfCliente;

    @FXML
    private DatePicker tfData;

    @FXML
    private TextField tfFrete;

    @FXML
    private TextField tfProduto;

    @FXML
    private TextField tfValorProduto;

    @FXML
    private TextField tfTotalItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfData.setValue(LocalDate.now());
        MaskFieldUtil.monetaryField(tfFrete);
        MaskFieldUtil.monetaryField(tfTotalItem);
        MaskFieldUtil.monetaryField(tfValorProduto);
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colQuant.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colValorTotal.setCellValueFactory(new PropertyValueFactory<>("preco"));

        spQuantidade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000,1));
        tfFrete.setText("0,00");
    }

    @FXML
    void onBuscarCliente(MouseEvent event) {
        List<Cliente> clientes = new ClienteDAL().get("");
        ModalTable mt=new ModalTable(clientes,new String[]{"documento","nome","endereco"},"nome");
        Stage stage=new Stage();
        stage.setScene(new Scene(mt));
        stage.setWidth(600); stage.setHeight(480); //stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        cliente = (Cliente)mt.getSelecionado();
        if (cliente!=null)
            tfCliente.setText(cliente.getNome());

    }

    @FXML
    void onBuscarProduto(MouseEvent event) {
        List<Produto> produtos = new ProdutoDAL().get("");
        ModalTable mt=new ModalTable(produtos,new String[]{"id","nome","categoria","preco"},"nome");
        Stage stage=new Stage();
        stage.setScene(new Scene(mt));
        stage.setWidth(600); stage.setHeight(480); //stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        produto = (Produto)mt.getSelecionado();
        if (produto!=null) {
            tfProduto.setText(produto.getNome());
            tfValorProduto.setText(String.format(Locale.US,"%.2f",produto.getPreco()));
            double valor=Double.parseDouble(tfValorProduto.getText().replace(",","."));
            tfTotalItem.setText(String.format("%.2f",(valor*spQuantidade.getValue().intValue())));
        }
    }

    @FXML
    void onCancelar(ActionEvent event) {
        tfFrete.getScene().getWindow().hide();
    }

    @FXML
    void onConfirmar(ActionEvent event) {
        Pedido pedido = new Pedido();
        pedido.setData(tfData.getValue());
        pedido.setCliente(cliente);
        pedido.setFrete(Double.parseDouble(tfFrete.getText().replace(",",".")));
        for(Item item : tableview.getItems())
        {
            pedido.addItem(item);
        }
        if(!new PedidoDAL().gravar(pedido))
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao gravar o pedido");
            alert.showAndWait();
        }
        tfFrete.getScene().getWindow().hide();

    }

    @FXML
    void onInserirProduto(ActionEvent event) {
        Item item = new Item(produto,Double.parseDouble(tfTotalItem.getText().replace(",",".")),spQuantidade.getValue().intValue());
        tableview.getItems().add(item);
        // somar no valor total do pedido
    }

    @FXML
    void onRemoverProduto(ActionEvent event) {

    }


}
