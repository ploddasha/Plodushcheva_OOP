package org.example;

import org.junit.jupiter.api.Test;

class CalculatorTest {
    @Test
    public void plusTest() throws Exception {
        String str = "+ 1 2";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        System.out.println(rez);
    }
    @Test
    public void plusMinusTest() throws Exception {
        String str = "+ - 1 2 3";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        System.out.println(rez);
    }
    @Test
    public void divisionZeroTest() throws Exception {
        String str = "/ 1 0";
        Calculator calc = new Calculator();

        //Double rez = calc.calculate(str);
        //System.out.println(rez);
    }

    @Test
    public void notEnoughArgumentTest() throws Exception {
        String str = "+ 1";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        System.out.println(rez);
    }

}