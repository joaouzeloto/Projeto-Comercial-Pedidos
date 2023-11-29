package br.fipp.pedidosfx.db.dals;

import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.entidades.Item;
import br.fipp.pedidosfx.db.entidades.Pedido;
import br.fipp.pedidosfx.db.entidades.Produto;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PedidoDAL implements IDAL <Pedido>{
    @Override
    public boolean gravar(Pedido entidade) {
        String sql=String.format(Locale.US,"INSERT INTO pedidos(ped_data, ped_frete, ped_total, cli_id) VALUES ('%s', %.2f, %.2f, %d)",
                entidade.getData().toString(),entidade.getFrete(),entidade.getValor(),entidade.getCliente().getId());
        if(DBSingleton.getConexao().manipular(sql))
        {
            //gravando os itens
            // recupara o id do pedido recem criado
            int pedId=DBSingleton.getConexao().getMaxPK("pedidos","ped_id");
            String sqlItens;
            for (Item item : entidade.getItens())
            {
                sqlItens=String.format(Locale.US,"INSERT INTO itens_pedidos(itp_quant, itp_preco, pro_id, ped_id) VALUES ( %d, %.2f, %d, %d)",
                        item.getQuantidade(),item.getPreco(),item.getProduto().getId(),pedId);
                DBSingleton.getConexao().manipular(sqlItens);
            }

        }
        return true;
    }

    @Override
    public boolean alterar(Pedido entidade) {
        return false;
    }

    @Override
    public boolean apagar(Pedido entidade) {
        return false;
    }

    @Override
    public Pedido get(int id) {
        return null;
    }

    @Override
    public List<Pedido> get(String filtro) {
        String sql="select * from pedidos";
        if(!filtro.isEmpty())
        {
            sql+=" WHERE "+filtro;
        }
        List<Pedido> pedidos=new ArrayList();
        ResultSet rs= DBSingleton.getConexao().consultar(sql);
        try {
            while (rs.next()) {
                pedidos.add(new Pedido(rs.getInt("ped_id"), new ClienteDAL().get(rs.getInt("cli_id")),
                                       rs.getDate("ped_data").toLocalDate(),rs.getDouble("ped_frete")));
            }
        }catch(Exception e) { System.out.println(e); }
        return pedidos;
    }
}