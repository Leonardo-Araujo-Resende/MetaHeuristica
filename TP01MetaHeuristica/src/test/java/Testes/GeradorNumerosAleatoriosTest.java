package Testes;

import com.mycompany.tp01metaheuristica.Model.GeradorNumerosAleatorios;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GeradorNumerosAleatoriosTest {
    
    
    @Test
    public void testNumerosExcedemLimite(){
        double numGerado;
        for (int i = 0; i < 10000; i++) {
            numGerado = GeradorNumerosAleatorios.geraDoubleEntre(-100, 100);
            if(numGerado > 100 || numGerado < -100) Assert.fail();
        }
    }
    
    @Test
    public void testNumerosMedia(){
        double soma = 0;
        for (int i = 0; i < 10000; i++) {
            soma = GeradorNumerosAleatorios.geraDoubleEntre(-100, 100);
        }
        if(soma/10000 > 10 || soma/10000 < -10){
            System.out.println("Soma = " + soma);
            Assert.fail();
        }
    }
    
    
}
