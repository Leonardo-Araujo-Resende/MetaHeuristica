
package com.mycompany.tp01metaheuristica.Model;

public class EstatisticaSA{
    public String questao;
    public double x;
    public double y;
    public double resultado;
    public double temperaturaInicial;
    public double porcentagemDecrementoTemp;
    public double pertubacaoVariaveis;

    public EstatisticaSA(String questao, double x, double y, double resultado, double temperaturaInicial, double porcentagemDecrementoTemp, double pertubacaoVariaveis) {
        this.questao = questao;
        this.x = x;
        this.y = y;
        this.resultado = resultado;
        this.temperaturaInicial = temperaturaInicial;
        this.porcentagemDecrementoTemp = porcentagemDecrementoTemp;
        this.pertubacaoVariaveis = pertubacaoVariaveis;
    }

    
    
    
    
    
}
