package br.fipp.pedidosfx.db;

public class DBSingleton {
    private static Conexao conexao;

    private DBSingleton() {
    }

    public static boolean conectar()
    {
        conexao=new Conexao();
        return conexao.conectar("jdbc:postgresql://localhost/",
                "bancoInterfaces",
                "postgres",
                "262216698");
    }
    public static Conexao getConexao() {
        return conexao;
    }
}
