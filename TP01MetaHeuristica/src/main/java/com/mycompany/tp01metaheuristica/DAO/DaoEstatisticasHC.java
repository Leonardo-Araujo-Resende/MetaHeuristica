package com.mycompany.tp01metaheuristica.DAO;

import com.mycompany.tp01metaheuristica.Model.EstatisticaHC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DaoEstatisticasHC {
    
    
    
    public static void CadastrasEstatisticas(EstatisticaHC estatistica) {
        String sqlComando = "INSERT INTO ESTATISTICAS_HC (QUESTAO,X,Y,RESULTADO,PERTUBACAO) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement statement = ConexaoBD.getConexao().prepareStatement(sqlComando);
            statement.setString(1, estatistica.questao);
            statement.setDouble(2, estatistica.x);
            statement.setDouble(3, estatistica.y);
            statement.setDouble(4, estatistica.resultado);
            statement.setDouble(5, estatistica.pertubacaoVariaveis);


            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static ResultSet PrintarEstatisticas(String questao) {
        try {

            String sqlComando = "SELECT * FROM estatisticas_hc where questao = \"" + questao + "\"";
            PreparedStatement query = ConexaoBD.getConexao().prepareStatement(sqlComando);
            ResultSet requisicao = query.executeQuery();
            
            requisicao.next(); 
            double minimo = requisicao.getDouble("resultado");
            double maximo = requisicao.getDouble("resultado");
            double somaResultados = requisicao.getDouble("resultado");
            double melhorX = requisicao.getDouble("x");
            double melhorY = requisicao.getDouble("y");
            int qntResultados = 1;
            
            
            double aux;
            while (requisicao.next()) {
                aux = requisicao.getDouble("resultado");
                if(aux < minimo){
                    minimo = aux;
                    melhorX = requisicao.getDouble("x");
                    melhorY = requisicao.getDouble("y");
                }
                if(aux > maximo) maximo = aux;
                somaResultados += aux;
                qntResultados += 1;
            }
            
            requisicao = query.executeQuery();
            
            double media = somaResultados/qntResultados;
            double somaDiferencaMedia = 0;
            while (requisicao.next()){
                somaDiferencaMedia = Math.pow(requisicao.getDouble("resultado") - media, 2);
                
            }
            
            System.out.println("");
            System.out.println("--- Hill Climbing " + questao + " ---");
            System.out.println("Valor minimo " + String.format("%.5f",minimo));
            System.out.println("Melhor x " + String.format("%.5f",melhorX));
            System.out.println("Melhor y " + String.format("%.5f",melhorY));
            System.out.println("Valor maximo " + String.format("%.5f",maximo));
            System.out.println("Media " + String.format("%.5f",media));
            System.out.println("Desvio padrao " + String.format("%.5f",Math.sqrt(somaDiferencaMedia/qntResultados)));
            System.out.println("");
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "DAOEstatisticas " + erro);
        }
        return null;
    }
    
    
}
