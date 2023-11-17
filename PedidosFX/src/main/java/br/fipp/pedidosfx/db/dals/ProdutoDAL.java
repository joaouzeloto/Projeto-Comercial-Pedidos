package br.fipp.pedidosfx.db.dals;

import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.entidades.Produto;

import java.util.List;

public class ProdutoDAL implements IDAL<Produto>{

    @Override
    public boolean gravar(Produto entidade) {
        if (DBSingleton.conectar())
        {
            String sql = String.format("INSERT INTO produtos(pro_nome, pro_preco, pro_estoque, cat_id) VALUES ('%s', '%f', '%d', '%d')",
                    entidade.getNome(), entidade.getPreco(), entidade.getEstoque(), entidade.getCategoria().getId());
            boolean resultado = DBSingleton.getConexao().manipular(sql);
            String mensagemErro = DBSingleton.getConexao().getMensagemErro();

            if (resultado) {
                System.out.println("Operação realizada com sucesso");
                return resultado;
            } else {
                System.out.println("Erro na operação: " + mensagemErro);
                return false;
            }

        }
        else
        {
            System.out.println("Erro na conexão");
            return false;
        }
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
