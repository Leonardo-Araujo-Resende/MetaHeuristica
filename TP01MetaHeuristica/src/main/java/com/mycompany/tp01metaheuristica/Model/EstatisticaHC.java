
package com.mycompany.tp01metaheuristica.Model;

public class EstatisticaHC {
    public String questao;
    public double x;
    public double y;
    public double resultado;
    public double pertubacaoVariaveis;

    public EstatisticaHC(String questao, double x, double y, double resultado, double pertubacaoVariaveis) {
        this.questao = questao;
        this.x = x;
        this.y = y;
        this.resultado = resultado;
        this.pertubacaoVariaveis = pertubacaoVariaveis;
    }

 
}
