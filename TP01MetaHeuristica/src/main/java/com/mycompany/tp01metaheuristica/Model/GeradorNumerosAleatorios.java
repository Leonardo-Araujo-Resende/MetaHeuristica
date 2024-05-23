package com.mycompany.tp01metaheuristica.Model;

import java.util.Random;

public class GeradorNumerosAleatorios {
    
    
    public static double geraDoubleEntre(double limiteInferior, double limiteSuperior){  
      
        Random random = new Random();
        double retorno = limiteInferior + random.nextDouble() * (limiteSuperior - limiteInferior);
        return retorno;
    }
    
}
