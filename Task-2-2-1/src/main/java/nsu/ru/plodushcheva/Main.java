package nsu.ru.plodushcheva;


import nsu.ru.plodushcheva.json.JsonParser;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Pizzeria pizzeria = new Pizzeria( new JsonParser().getData());
        pizzeria.work();
    }
}