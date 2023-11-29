package br.fipp.pedidosfx.db.dals;

import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.entidades.Cliente;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAL implements IDAL<Cliente>{
    @Override
    public boolean gravar(Cliente entidade) {
        String sql= "INSERT INTO clientes(cli_documento, cli_nome, cli_endereco, cli_cidade, cli_cep, cli_email) VALUES (#1, '#2', '#3', '#4', '#5', '#6')";
        sql=sql.replace("#1",""+entidade.getDocumento());
        sql=sql.replace("#2",entidade.getNome());
        sql=sql.replace("#3",entidade.getEndereco());
        sql=sql.replace("#4",entidade.getCidade());
        sql=sql.replace("#5",entidade.getCep());
        sql=sql.replace("#6",entidade.getEmail());
        return DBSingleton.getConexao().manipular(sql);
    }

    @Override
    public boolean alterar(Cliente entidade) {
        String sql= "UPDATE clientes SET cli_documento=#1, cli_nome='#2', cli_endereco='#3', cli_cidade='#4', cli_cep='#5', cli_email='#6' WHERE cli_id="+entidade.getId();
        sql=sql.replace("#1",""+entidade.getDocumento());
        sql=sql.replace("#2",entidade.getNome());
        sql=sql.replace("#3",entidade.getEndereco());
        sql=sql.replace("#4",entidade.getCidade());
        sql=sql.replace("#5",entidade.getCep());
        sql=sql.replace("#6",entidade.getEmail());
        return DBSingleton.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(Cliente entidade) {
        return DBSingleton.getConexao().manipular("DELETE FROM clientes where cli_id="+entidade.getId());
    }

    @Override
    public Cliente get(int id) {
        String sql="select * from clientes WHERE cli_id="+id;
        ResultSet rs= DBSingleton.getConexao().consultar(sql);
        Cliente cliente=null;
        try {
            if (rs.next()) {
                cliente= new Cliente(rs.getInt("cli_id"),
                        rs.getLong("cli_documento"), rs.getString("cli_nome"),
                        rs.getString("cli_endereco"), "",
                        rs.getString("cli_cidade"), rs.getString("cli_cep"),
                        "", rs.getString("cli_email"));
            }
        }catch(Exception e) { System.out.println(e); }
        return cliente;
    }
    @Override
    public List<Cliente> get (String filtro)
    {   String sql="select * from clientes";
        if(!filtro.isEmpty())
        {
            sql+=" WHERE "+filtro;
        }
        ResultSet rs= DBSingleton.getConexao().consultar(sql);
        List<Cliente> clientes=new ArrayList<>();
        try {
            while (rs.next()) {
                clientes.add(new Cliente(rs.getInt("cli_id"),
                        rs.getLong("cli_documento"), rs.getString("cli_nome"),
                        rs.getString("cli_endereco"), "",
                        rs.getString("cli_cidade"), rs.getString("cli_cep"),
                        "", rs.getString("cli_email")));
            }
        }catch(Exception e) { System.out.println(e); }
        return clientes;
    }
}
