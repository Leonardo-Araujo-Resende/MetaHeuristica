
package com.mycompany.tp01metaheuristica.Model;

import static com.mycompany.tp01metaheuristica.DAO.DaoEstatisticasHC.CadastrasEstatisticas;
import static com.mycompany.tp01metaheuristica.Model.SimulatedAnnealing.PI;
import com.sun.jdi.request.InvalidRequestStateException;

public class HillClimbing {
    public static final double PI = 3.14;
    
    private final double LIMITESUPERIOR;
    private final double LIMITEINFERIOR;
    
    private double melhorValorX;
    private double melhorValorY;
    
    private String questao;

    public HillClimbing(double LIMITEINFERIOR, double LIMITESUPERIOR, String questao) {
        this.LIMITESUPERIOR = LIMITESUPERIOR;
        this.LIMITEINFERIOR = LIMITEINFERIOR;
        this.questao = questao;
    }
    
    public  double  funcaoMinimizar(double x, double y){
        double resultado;
        if(questao.charAt(0) == '1')resultado = -0.0001 * Math.pow(Math.abs(Math.sin(x) * Math.sin(y) * Math.abs(Math.exp(100 - Math.sqrt(x*x + y*y)/PI))) + 1,0.1);
        else resultado = x * Math.sin(4*x) + 1.1 * y * Math.sin(2*y);
        return resultado;
    }
    
    public double limitesVariaveisDecisao(double variavel, double somar){
        if(variavel + somar > LIMITESUPERIOR) return LIMITEINFERIOR;
        if(variavel + somar < LIMITEINFERIOR) return LIMITESUPERIOR;
        return variavel + somar;
    }

    
    public void executarAlgoritmo(double porcentagemPertubacao){
        melhorValorX = GeradorNumerosAleatorios.geraDoubleEntre(LIMITEINFERIOR, LIMITESUPERIOR);
        melhorValorY = GeradorNumerosAleatorios.geraDoubleEntre(LIMITEINFERIOR, LIMITESUPERIOR);
        double melhorResultado = funcaoMinimizar(melhorValorX, melhorValorY);
        
        double pertubacao = porcentagemPertubacao/100;
        double atualValorX = melhorValorX;
        double atualValorY = melhorValorY;
        double atualResultado;
        int contIteracoesSemMelhora = 0;
        
        while(contIteracoesSemMelhora < 100000){
            
            atualValorX = limitesVariaveisDecisao(melhorValorX, GeradorNumerosAleatorios.geraDoubleEntre(- Math.abs(atualValorX) * pertubacao, Math.abs(atualValorX) * pertubacao));
            atualResultado = funcaoMinimizar(atualValorX, atualValorY);
  
            if(atualResultado < melhorResultado){
                melhorResultado = atualResultado;
                melhorValorX = atualValorX;
                contIteracoesSemMelhora = 0;
            } 
            
            atualValorY = limitesVariaveisDecisao(melhorValorY, GeradorNumerosAleatorios.geraDoubleEntre(- Math.abs(atualValorY) * pertubacao, Math.abs(atualValorY) * pertubacao));
            atualResultado = funcaoMinimizar(atualValorX, atualValorY);
  
            if(atualResultado < melhorResultado){
                melhorResultado = atualResultado;
                melhorValorY = atualValorY;
                contIteracoesSemMelhora = 0;
            } 
            
            contIteracoesSemMelhora ++;
        }
        SalvarEstatisticasBanco(questao, melhorValorX, melhorValorY, melhorResultado, pertubacao);
    }
    
    private void SalvarEstatisticasBanco(String questao, double x, double y, double resultado, double pertubacao){
            EstatisticaHC estatisticas = new EstatisticaHC(questao, x,y,resultado,pertubacao);
            CadastrasEstatisticas(estatisticas);
    }
    
      
    
    
}
    
    
    
  
