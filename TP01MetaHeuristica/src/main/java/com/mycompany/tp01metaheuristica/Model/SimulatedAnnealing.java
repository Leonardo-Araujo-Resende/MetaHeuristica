package com.mycompany.tp01metaheuristica.Model;

import com.mycompany.tp01metaheuristica.DAO.DaoEstatisticasSA;


public class SimulatedAnnealing {
    
    public static final double PI = 3.14;
    
    private double temperaturaAtual;
    private double temperaturaInicial;
    
    private final double LIMITESUPERIOR;
    private final double LIMITEINFERIOR;
    
    private double melhorValorX;
    private double melhorValorY;
    
    private String questao;
    

    public SimulatedAnnealing(double limiteInferior, double limiteSuperior, String questao) {
        this.LIMITEINFERIOR = limiteInferior;
        this.LIMITESUPERIOR = limiteSuperior;
        this.questao = questao;
    }
    
    private  double  funcaoMinimizar(double x, double y){
        double resultado;
        if(questao.charAt(0) == '1')resultado = -0.0001 * Math.pow(Math.abs(Math.sin(x) * Math.sin(y) * Math.abs(Math.exp(100 - Math.sqrt(x*x + y*y)/PI))) + 1,0.1);
        else resultado = x * Math.sin(4*x) + 1.1 * y * Math.sin(2*y);
        return resultado;
    }
    
    private boolean  criterioAceitacao(double melhorSolucao, double solucaoNova, double temperaturaAtual){
        if(solucaoNova < melhorSolucao){
            return true;
        }
        
        //Criterio de metropolis
        double aux = Math.exp((-solucaoNova + melhorSolucao)/(temperaturaAtual/temperaturaInicial));
        if(aux > GeradorNumerosAleatorios.geraDoubleEntre(0, 1)){
            return true;
        }   
        return false;
    }
    
    public void executarAlgoritmo(double temperaturaInicial, double diminuicaoTemperaturaPorcentagem, int iteracoesPorTemperatura, double pertubacoesVariaveisDecisao){
        this.temperaturaAtual = temperaturaInicial;
        this.temperaturaInicial = temperaturaInicial;
        
        melhorValorX = GeradorNumerosAleatorios.geraDoubleEntre(LIMITEINFERIOR, LIMITESUPERIOR);
        melhorValorY = GeradorNumerosAleatorios.geraDoubleEntre(LIMITEINFERIOR, LIMITESUPERIOR);
        double melhorResultado = funcaoMinimizar(melhorValorX, melhorValorY);
        double atualValorX;
        double atualValorY;
        double atualResultado;
        
        while(temperaturaAtual > 0.0001){
            for (int i = 0; i < iteracoesPorTemperatura; i++){
                 atualValorX = limitesVariaveisDecisao(melhorValorX, GeradorNumerosAleatorios.geraDoubleEntre(-pertubacoesVariaveisDecisao, pertubacoesVariaveisDecisao));
                 atualValorY = limitesVariaveisDecisao(melhorValorY, GeradorNumerosAleatorios.geraDoubleEntre(-pertubacoesVariaveisDecisao, pertubacoesVariaveisDecisao));
                
                atualResultado = funcaoMinimizar(atualValorX, atualValorY);
                if(criterioAceitacao(melhorResultado, atualResultado, temperaturaAtual)){
                    melhorResultado = atualResultado;
                    melhorValorX = atualValorX;
                    melhorValorY = atualValorY;
                }
            temperaturaAtual = temperaturaAtual * (1-diminuicaoTemperaturaPorcentagem/100);
            }
        }       
        salvarEstatisticasBanco(questao, melhorValorX, melhorValorY, melhorResultado, temperaturaInicial, diminuicaoTemperaturaPorcentagem, pertubacoesVariaveisDecisao);
    }
    
    private double limitesVariaveisDecisao(double variavel, double somar){
        if(variavel + somar > LIMITESUPERIOR) return LIMITEINFERIOR;
        if(variavel + somar < LIMITEINFERIOR) return LIMITESUPERIOR;
        return variavel + somar;
    }
    
    
    private void salvarEstatisticasBanco(String questao, double x, double y, double resultado, double temperaturaInicial, double porcentagemDecrementoTemp, double pertubacaoVariaveis){ 
        EstatisticaSA estatistica = new EstatisticaSA(questao,x,y,resultado,temperaturaInicial, porcentagemDecrementoTemp, pertubacaoVariaveis);
        DaoEstatisticasSA.CadastrasEstatisticas(estatistica);
    }
    

}
