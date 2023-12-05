package br.fipp.pedidosfx.db.dals;

import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.entidades.ItensPedido;
import br.fipp.pedidosfx.db.entidades.Pedido;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItensPedidosDAL
{
    public List<ItensPedido> get(String filtro) {
        String sql="select * from itens_pedidos";
        if(!filtro.isEmpty())
        {
            sql+=" WHERE "+filtro;
        }
        List<ItensPedido> listItens=new ArrayList();
        ResultSet rs= DBSingleton.getConexao().consultar(sql);
        try {
            while (rs.next()) {
                listItens.add(new ItensPedido(rs.getInt("itp_id"), rs.getInt("itp_quant"),
                        rs.getDouble("itp_preco"),rs.getInt("pro_id"),rs.getInt("ped_id")));
            }
        }catch(Exception e) { System.out.println(e); }
        return listItens;
    }
}
