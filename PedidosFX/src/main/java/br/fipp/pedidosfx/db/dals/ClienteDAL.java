package br.fipp.pedidosfx.db.dals;

import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.entidades.Cliente;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAL {

    public boolean gravar(Cliente entidade)
    {
        if (DBSingleton.conectar()) {
            String sql = String.format("INSERT INTO clientes(cli_documento, cli_nome, cli_endereco,cli_bairro, cli_cidade, cli_cep, cli_uf, cli_email) VALUES ('%d','%s', '%s','%s', '%s','%s', '%s','%s')",
                    entidade.getDocumento(),entidade.getNome(), entidade.getEndereco(),entidade.getBairro(),entidade.getCidade(),entidade.getCep(),entidade.getUf(),entidade.getEmail());

            boolean resultado = DBSingleton.getConexao().manipular(sql);
            String mensagemErro = DBSingleton.getConexao().getMensagemErro();

            if (resultado) {
                System.out.println("Operação realizada com sucesso");
                return resultado;
            } else {
                System.out.println("Erro na operação: " + mensagemErro);
                return false;
            }
        } else {
            System.out.println("Erro na conexão");
            return false;
        }

    }


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
                        rs.getString("cli_endereco"), rs.getString("cli_bairro"),
                        rs.getString("cli_cidade"), rs.getString("cli_cep"),
                        rs.getString("cli_uf"), rs.getString("cli_email")));
            }
        }catch(Exception e) { System.out.println(e); }
        return clientes;
    }
}
