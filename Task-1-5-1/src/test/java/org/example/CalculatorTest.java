package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    public void plusTest(){
        String str = "+ 1 2";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        System.out.println(rez);
    }
    @Test
    public void plusMinusTest(){
        String str = "+ - 1 2 3";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        System.out.println(rez);
    }

}