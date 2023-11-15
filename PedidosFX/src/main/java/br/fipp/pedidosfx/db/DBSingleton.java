package br.fipp.pedidosfx.db;

public class DBSingleton {
    private static Conexao conexao;

    private DBSingleton() {
    }

    public static boolean conectar()
    {
        conexao=new Conexao();
        return conexao.conectar("jdbc:projeto-comercial-java.cjkoamzskotm.us-east-1.rds.amazonaws.com",
                "projeto-comercial-java",
                "admsqlfipp",
                "93yrRktC9e");
    }
    public static Conexao getConexao() {
        return conexao;
    }
}
