package ru.nsu.plodushcheva;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubStringTest {
    @Test
    void initialTest() {
        try (InputStream stream =
                     getClass().getClassLoader().getResourceAsStream("input.txt")) {

            String sub = "one";
            List<Integer> actual;

            actual = SubString.subStringFinder(sub, stream);

            List<Integer> expected = new ArrayList<>();
            expected.add(0);
            expected.add(12);
            expected.add(16);

            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void emptyTest() {

        try (InputStream stream =
                    getClass().getClassLoader().getResourceAsStream("input.txt")) {

            String sub = "miracle and mir";

            List<Integer> actual;

            actual = SubString.subStringFinder(sub, stream);

            List<Integer> expected = new ArrayList<>();

            Assertions.assertEquals(expected, actual);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * the end of one substring is the beginning if another substring
     * text is "AABAACAADAABAABA"
     * subString is "AABA"
     * .AABAACAAD.AAB.AABA
     */
    @Test
    void subSubStringTest() {
        try (InputStream stream =
                    getClass().getClassLoader().getResourceAsStream("input.txt")) {

            String sub = "AABA";

            List<Integer> actual;
            actual = SubString.subStringFinder(sub, stream);

            List<Integer> expected = new ArrayList<>();
            expected.add(61);
            expected.add(70);
            expected.add(73);

            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void twoKbTest() throws IOException {

        try (RandomAccessFile file =
                     new RandomAccessFile("./src/test/resources/input1.txt", "rw")) {
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
            file.seek(0);

            String sub = "meow";
            List<Integer> actual;

            InputStream stream = Channels.newInputStream(file.getChannel());
            actual = SubString.subStringFinder(sub, stream);

            List<Integer> expected = new ArrayList<>();
            expected.add(100);
            expected.add(300);
            expected.add(1000);
            expected.add(1048576);

            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    void oneGbTest() {
        try (RandomAccessFile file =
                     new RandomAccessFile("./src/test/resources/input1.txt", "rw")) {

            file.setLength(1024 * 1024 * 1024);

            file.seek(100);
            file.writeBytes("word");
            file.seek(300);
            file.writeBytes("word");
            file.seek(1000);
            file.writeBytes("word");
            file.seek(1048576);
            file.writeBytes("word");
            file.seek(1073000000);
            file.writeBytes("word");
            file.seek(0);

            String sub = "word";
            List<Integer> actual;

            InputStream stream = Channels.newInputStream(file.getChannel());

            actual = SubString.subStringFinder(sub, stream);

            List<Integer> expected = new ArrayList<>();
            expected.add(100);
            expected.add(300);
            expected.add(1000);
            expected.add(1048576);
            expected.add(1073000000);

            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void tenGbTest() throws IOException {
        try (RandomAccessFile file =
                     new RandomAccessFile("./src/test/resources/input1.txt", "rw")) {
            file.setLength(10737418240L);

            file.seek(100);
            file.writeBytes("winterwinter");
            file.seek(16589);
            file.writeBytes("winter");
            file.seek(1048576);
            file.writeBytes("winter");
            file.seek(1073000000);
            file.writeBytes("winter");
            file.seek(0);

            String sub = "winter";
            List<Integer> actual;

            InputStream stream = Channels.newInputStream(file.getChannel());

            actual = SubString.subStringFinder(sub, stream);

            List<Integer> expected = new ArrayList<>();
            expected.add(100);
            expected.add(106);
            expected.add(16589);
            expected.add(1048576);
            expected.add(1073000000);

            Assertions.assertEquals(expected, actual);
        }
    }

}