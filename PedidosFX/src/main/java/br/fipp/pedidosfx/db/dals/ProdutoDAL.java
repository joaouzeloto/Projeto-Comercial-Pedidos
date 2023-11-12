package br.fipp.pedidosfx.db.dals;

import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.entidades.Produto;

import java.util.List;

public class ProdutoDAL implements IDAL<Produto>{

    @Override
    public boolean gravar(Produto entidade) {
        String sql=String.format("INSERT INTO produtos(pro_nome, pro_preco, pro_estoque, cat_id) VALUES (%d, '%s', %f, %d, %d)",
                entidade.getNome(),entidade.getPreco(),entidade.getEstoque(),entidade.getCategoria().getId());
        return DBSingleton.getConexao().manipular(sql);
    }

    @Override
    public boolean alterar(Produto entidade) {
        String sql=String.format("UPDATE produtos SET pro_nome='%s', pro_preco=%f, pro_estoque=%d, cat_id=%d WHERE pro_id=%d",
                entidade.getNome(),entidade.getPreco(),entidade.getEstoque(),entidade.getCategoria().getId(),entidade.getId());
        return DBSingleton.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(Produto entidade) {
        return DBSingleton.getConexao().manipular("DELETE FROM produtos WHERE cat_id="+entidade.getId());
    }

    @Override
    public Produto get(int id) {
        return null;
    }

    @Override
    public List<Produto> get(String filtro) {
        return null;
    }
}
