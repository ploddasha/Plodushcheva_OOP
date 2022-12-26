package ru.nsu.plodushcheva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    @Test
    public void plusTest() throws Exception {
        String str = "+ 1 2";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        Assertions.assertEquals(3.0, rez);
    }

    @Test
    public void plusMinusTest() throws Exception {
        String str = "+ - 1 2 3";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        Assertions.assertEquals(2.0, rez);
    }

    @Test
    public void multiplicationTest() throws Exception {
        String str = "* + - 1 2 3 100";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        Assertions.assertEquals(200.0, rez);
    }

    @Test
    public void divisionZeroTest() {
        String str = "/ 1 0";
        Calculator calc = new Calculator();

        Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> calc.calculate(str));

        Assertions.assertEquals("Division by 0", exception.getMessage());
    }

    @Test
    public void notEnoughArgumentTest() {
        String str = "+ 1";
        Calculator calc = new Calculator();

        Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> calc.calculate(str));

        Assertions.assertEquals("Wrong expression", exception.getMessage());
    }

    @Test
    public void logTest() throws Exception {
        String str = "log 2";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        Assertions.assertEquals(0.69, rez, 0.01);

        String str2 = "log 8";
        Calculator calc2 = new Calculator();

        Double rez2 = calc2.calculate(str2);
        Assertions.assertEquals(2.079, rez2, 0.001);

        String str3 = "log -5";
        Calculator calc3 = new Calculator();

        Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> calc3.calculate(str3));

        Assertions.assertEquals("Negative number for logarithm", exception.getMessage());
    }

    @Test
    public void powTest() throws Exception {
        String str = "pow 2 3";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        Assertions.assertEquals(8.0, rez);

        String str2 = "pow 2 -3";
        Calculator calc2 = new Calculator();

        Double rez2 = calc2.calculate(str2);
        Assertions.assertEquals(0.125, rez2);

    }

    @Test
    public void sqrtTest() throws Exception {
        String str = "sqrt 4";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        Assertions.assertEquals(2, rez);

        String str2 = "sqrt 8";
        Calculator calc2 = new Calculator();

        Double rez2 = calc2.calculate(str2);
        Assertions.assertEquals(2.83, rez2, 0.01);

        String str3 = "sqrt -5";
        Calculator calc3 = new Calculator();

        Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> calc3.calculate(str3));

        Assertions.assertEquals("Negative number for root", exception.getMessage());
    }

    @Test
    public void sinTest() throws Exception {
        String str = "sin 0";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        Assertions.assertEquals(0, rez);
    }

    @Test
    public void cosTest() throws Exception {
        String str = "cos 0";
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        Assertions.assertEquals(1, rez);
    }

    @Test
    public void expressionTest() throws Exception {
        String str = "+ - 64 * 15 4 / 20 4"; //64 – 15*4 + 20/4
        Calculator calc = new Calculator();

        Double rez = calc.calculate(str);
        Assertions.assertEquals(9.0, rez);
    }

}