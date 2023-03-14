package nsu.ru.plodushcheva;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");
        Pizzeria pizzeria = new Pizzeria( new JsonParser().getData());
        pizzeria.work();
    }
}