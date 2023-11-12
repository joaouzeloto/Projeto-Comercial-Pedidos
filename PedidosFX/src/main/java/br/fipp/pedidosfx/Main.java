package br.fipp.pedidosfx;

import br.fipp.pedidosfx.db.Conexao;
import br.fipp.pedidosfx.db.DBSingleton;
import br.fipp.pedidosfx.db.dals.ClienteDAL;
import br.fipp.pedidosfx.db.entidades.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DBSingleton.conectar();
        List<Cliente> clientes = new ClienteDAL().get("");
        for(Cliente c : clientes)
            System.out.println(c.getNome());
    }
}
