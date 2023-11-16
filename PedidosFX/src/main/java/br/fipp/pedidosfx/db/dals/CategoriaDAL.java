package br.fipp.pedidosfx.db.dals;

import br.fipp.pedidosfx.db.Conexao;
import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.entidades.Categoria;
import br.fipp.pedidosfx.db.entidades.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAL implements IDAL<Categoria> {


    @Override
    public boolean gravar(Categoria entidade) {
        if (DBSingleton.conectar()) {
            String sql = String.format("INSERT INTO categorias(cat_nome, cat_desc) VALUES ('%s', '%s')",
                    entidade.getNome(), entidade.getDescricao());

            boolean resultado = DBSingleton.getConexao().manipular(sql);

            return resultado;
        } else {
            System.out.println("Erro na conexão: ");
            return false;
        }
    }

    @Override
    public boolean alterar(Categoria entidade) {
        String sql=String.format("UPDATE categorias SET cat_nome='%s', cat_desc='%s' WHERE cat_id=%d)," +
                        entidade.getId(),entidade.getNome(),entidade.getDescricao());
        return DBSingleton.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(Categoria entidade) {
        return DBSingleton.getConexao().manipular("DELETE FROM categorias WHERE cat_id="+entidade.getId());
    }

    @Override
    public Categoria get(int id) {
        String sql="select * from categorias WHERE cat_id="+id;
        Categoria categoria=null;
        ResultSet rs= DBSingleton.getConexao().consultar(sql);
        List<Categoria> categorias=new ArrayList<>();
        try {
            if (rs.next()) {
                categoria = new Categoria(rs.getInt("cat_id"), rs.getString("cat_nome"), rs.getString("cat_desc"));
            }
        }catch(Exception e) { System.out.println(e); }
        return categoria;
    }

    @Override
    public List<Categoria> get(String filtro) {
        String sql="select * from categorias";
        if(!filtro.isEmpty())
        {
            sql+=" WHERE "+filtro;
        }
        ResultSet rs= DBSingleton.getConexao().consultar(sql);
        List<Categoria> categorias=new ArrayList<>();
        try {
            while (rs.next()) {
                categorias.add(new Categoria(rs.getInt("cat_id"), rs.getString("cat_nome"), rs.getString("cat_desc")));
            }
        }catch(Exception e) { System.out.println(e); }
        return categorias;
    }

    public void gravar(String nome, String descricao) {
    }
}
