package com.mycompany.tp01metaheuristica.DAO;

import com.mycompany.tp01metaheuristica.Model.EstatisticaSA;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DaoEstatisticasSA {

    public static void CadastrasEstatisticas(EstatisticaSA estatistica) {
        String sqlComando = "INSERT INTO ESTATISTICAS_SA (QUESTAO,X,Y,RESULTADO,TEMPERATURA_INICIAL,PORCENTAGEM_DECREMENTO_TEMPERATURA,PERTUBACAO_VARIAVEIS) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = ConexaoBD.getConexao().prepareStatement(sqlComando);
            statement.setString(1, estatistica.questao);
            statement.setDouble(2, estatistica.x);
            statement.setDouble(3, estatistica.y);
            statement.setDouble(4, estatistica.resultado);
            statement.setDouble(5, estatistica.temperaturaInicial);
            statement.setDouble(6, estatistica.porcentagemDecrementoTemp);
            statement.setDouble(7, estatistica.pertubacaoVariaveis);

            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet PrintarEstatisticas(String questao) {
        try {

            String sqlComando = "SELECT * FROM metaheuristicatp01.estatisticas_sa where questao = \"" + questao + "\"";
            PreparedStatement query = ConexaoBD.getConexao().prepareStatement(sqlComando);
            ResultSet requisicao = query.executeQuery();
            
            requisicao.next(); 
            double minimo = requisicao.getDouble("resultado");
            double maximo = requisicao.getDouble("resultado");
            double melhorX = requisicao.getDouble("x");
            double melhorY = requisicao.getDouble("y");
            double somaResultados = requisicao.getDouble("resultado");
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
            System.out.println("--- Simulated Annealing " + questao + " ---");
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
    
    public static ResultSet PrintarEstatisticasTempInicial(String questao) {
        try {

            String sqlComando = "SELECT * FROM metaheuristicatp01.estatisticas_sa where questao = \"" + questao + "\"";
            PreparedStatement query = ConexaoBD.getConexao().prepareStatement(sqlComando);
            ResultSet requisicao = query.executeQuery();
            
            requisicao.next(); 
            double minimo = requisicao.getDouble("resultado");
            double maximo = requisicao.getDouble("resultado");
            double melhorX = requisicao.getDouble("x");
            double melhorY = requisicao.getDouble("y");
            double somaResultados = requisicao.getDouble("resultado");
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
            System.out.println("--- Simulated Annealing " + questao + " ---");
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
