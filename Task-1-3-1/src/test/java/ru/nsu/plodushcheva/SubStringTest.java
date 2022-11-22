package ru.nsu.plodushcheva;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubStringTest {
    @Test
    void initialTest() {
        File file = new File("input.txt");

        String sub = "one";

        ArrayList<Integer> actual;
        try {
            actual = SubString.subStringFinder(sub, file);
            System.out.println(actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(12);
        expected.add(16);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void emptyTest() {
        File file = new File("input.txt");

        String sub = "miracle and mir";

        ArrayList<Integer> actual;
        try {
            actual = SubString.subStringFinder(sub, file);
            System.out.println(actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> expected = new ArrayList<>();

        Assertions.assertEquals(expected, actual);
    }

    /**
     * the end of one substring is the beginning if another substring
     * text is "AABAACAADAABAABA"
     * subString is "AABA"
     * .AABAACAAD.AAB.AABA
     */
    @Test
    void subSubStringTest() {
        File file = new File("input.txt");

        String sub = "AABA";

        ArrayList<Integer> actual;
        try {
            actual = SubString.subStringFinder(sub, file);
            System.out.println(actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(61);
        expected.add(70);
        expected.add(73);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void twoKbTest() throws IOException {
        RandomAccessFile file = new RandomAccessFile("random1.txt", "rw");
        file.setLength(1024 * 1024 * 2);

        Random r = new Random();

        for (int i = 0; i < 1024 * 1024 * 2; i++) {
            file.seek(i);
            char code = (char) (r.nextInt(94) + 33);
            file.writeBytes(String.valueOf(code));
        }

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

        File file1 = new File("random1.txt");

        String sub = "meow";
        ArrayList<Integer> actual;
        try {
            actual = SubString.subStringFinder(sub, file1);
            System.out.println(actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(100);
        expected.add(300);
        expected.add(1000);
        expected.add(1048576);

        Assertions.assertEquals(expected, actual);
        file.close();
    }

    @Test
    void oneGbTest() throws IOException {
        RandomAccessFile file = new RandomAccessFile("random2.txt", "rw");
        file.setLength(1024 * 1024 * 1024);

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

        File file1 = new File("random2.txt");

        String sub = "meow";
        ArrayList<Integer> actual;
        try {
            actual = SubString.subStringFinder(sub, file1);
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

        Assertions.assertEquals(expected, actual);
        file.close();
    }

    @Test
    void tenGbTest() throws IOException {
        RandomAccessFile file = new RandomAccessFile("random3.txt", "rw");
        file.setLength(10737418240L);

        file.seek(100);
        file.writeBytes("meowmeow");
        file.seek(16589);
        file.writeBytes("meow");
        file.seek(1048576);
        file.writeBytes("meow");
        file.seek(1073000000);
        file.writeBytes("meow");

        File file1 = new File("random3.txt");

        String sub = "meow";
        ArrayList<Integer> actual;
        try {
            actual = SubString.subStringFinder(sub, file1);
            System.out.println(actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(100);
        expected.add(104);
        expected.add(16589);
        expected.add(1048576);
        expected.add(1073000000);

        Assertions.assertEquals(expected, actual);
        file.close();
    }

}