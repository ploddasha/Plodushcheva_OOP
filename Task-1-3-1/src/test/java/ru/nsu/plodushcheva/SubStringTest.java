package ru.nsu.plodushcheva;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


class SubStringTest {
    @Test
    void initialTest() throws IOException{
        File file = new File("input.txt");

        String sub = "one";

        ArrayList<Integer> actual;
        try {
            actual = SubString.subStringFinder(sub,file);
            System.out.println(actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(12);
        expected.add(16);

        Assertions.assertEquals(actual,expected);
    }
    @Test
    void twoKBTest() throws IOException {
        RandomAccessFile file = new RandomAccessFile("random.txt", "rw");
        //file.setLength(1024 * 1024 * 1024 );
        file.setLength(1024 * 1024 * 2 );
        file.seek(100);
        file.writeBytes("meow");
        file.seek(300);
        file.writeBytes("meow");
        file.seek(500);
        file.writeBytes("woof");
        file.seek(1000);
        file.writeBytes("meow");
        file.seek(1048576);
        file.writeBytes("meow");


        File file1 = new File("random.txt");

        String sub = "meow";
        ArrayList<Integer> actual;
        try {
            actual = SubString.subStringFinder(sub,file1);
            System.out.println(actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(100);
        expected.add(300);
        expected.add(1000);
        expected.add(1048576);

        Assertions.assertEquals(actual,expected);
        file.close();
    }
    @Test
    void oneGBTest() throws IOException {
        RandomAccessFile file = new RandomAccessFile("random.txt", "rw");
        file.setLength(1024 * 1024 * 1024 );

        Random r = new Random();
        for(int i = 0; i<(1024*1024*1024); i++){
            file.seek(i);
            char code = (char) (r.nextInt(94) + 33);
            file.writeBytes(String.valueOf(code));
        }
        file.seek(100);
        file.writeBytes("meow");
        file.seek(300);
        file.writeBytes("meow");
        file.seek(1000);
        file.writeBytes("meow");
        file.seek(1048576);
        file.writeBytes("meow");
        file.seek(1073000000);
        file.writeBytes("meow");


        File file1 = new File("C:/Users/plodd/IdeaProjects/Plodushcheva_OOP/Task-1-3-1/random.txt");

        String sub = "meow";
        ArrayList<Integer> actual;
        try {
            actual = SubString.subStringFinder(sub,file1);
            System.out.println(actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(100);
        expected.add(300);
        expected.add(1000);
        expected.add(1048576);
        expected.add(1073000000);


        Assertions.assertEquals(actual,expected);
        file.close();
    }


}