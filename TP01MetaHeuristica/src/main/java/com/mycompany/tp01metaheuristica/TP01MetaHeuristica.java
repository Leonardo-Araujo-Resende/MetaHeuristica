package com.mycompany.tp01metaheuristica;


import com.mycompany.tp01metaheuristica.DAO.DaoEstatisticasHC;
import com.mycompany.tp01metaheuristica.DAO.DaoEstatisticasSA;
import com.mycompany.tp01metaheuristica.Model.HillClimbing;
import com.mycompany.tp01metaheuristica.Model.SimulatedAnnealing;
import java.sql.SQLException;

/**
 *
 * @author Léo
 */
public class TP01MetaHeuristica {

    public static void main(String[] args) throws SQLException {
        
        // temperaturaInicial, diminuicaoTemperaturaPorcentagem, iteracoesPorTemperatura, pertubacoesVariaveisDecisao
        SimulatedAnnealing a = new SimulatedAnnealing(-10, 10, "12");;
        double tempI = 100;
        double diminuicaoTemp = 1;
        int iteracoes = 1;
        double pertubacao = 0;
        
        
        
        for (int i = 0; i < 30; i++) {
            a.executarAlgoritmo(tempI, diminuicaoTemp, iteracoes, 0.01);  
        }
        for (int i = 0; i < 30; i++) {
            a.executarAlgoritmo(tempI, diminuicaoTemp, iteracoes, 0.1);  
        }
        for (int i = 0; i < 30; i++) {
            a.executarAlgoritmo(tempI, diminuicaoTemp, iteracoes, 0.5);  
        }
        for (int i = 0; i < 30; i++) {
            a.executarAlgoritmo(tempI, diminuicaoTemp, iteracoes, 1);  
        }
        for (int i = 0; i < 30; i++) {
            a.executarAlgoritmo(tempI, diminuicaoTemp, iteracoes, 2);  
        }
        for (int i = 0; i < 30; i++) {
            a.executarAlgoritmo(tempI, diminuicaoTemp, iteracoes, 3);  
        }
        for (int i = 0; i < 30; i++) {
            a.executarAlgoritmo(tempI, diminuicaoTemp, iteracoes, 4);  
        }
        for (int i = 0; i < 30; i++) {
            a.executarAlgoritmo(tempI, diminuicaoTemp, iteracoes, 5);  
        }
        for (int i = 0; i < 30; i++) {
            a.executarAlgoritmo(tempI, diminuicaoTemp, iteracoes, 6);  
        }
        for (int i = 0; i < 30; i++) {
            a.executarAlgoritmo(tempI, diminuicaoTemp, iteracoes, 7);  
        }
        
        
        
        
       
        
        

    }
}
