package com.mycompany.tp01metaheuristica.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/metaheuristicatp01";
    private static final String USUARIO = "root";
    private static final String SENHA = "leo170303";
    
    private static Connection conexao;
    
    public static Connection getConexao() throws SQLException{
        if(conexao == null){
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        return conexao;
    }
}
    
