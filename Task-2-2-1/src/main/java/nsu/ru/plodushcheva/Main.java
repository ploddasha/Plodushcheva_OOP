package nsu.ru.plodushcheva;

import nsu.ru.plodushcheva.json.JsonParser;
import nsu.ru.plodushcheva.pizzeria.Pizzeria;

public class Main {

    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria( new JsonParser().getData());
        pizzeria.work(15000);

    }
}