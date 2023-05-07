package nsu.ru.plodushcheva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MultiThreadTest {

    @Test
    void exampleOneTest() {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(1, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void exampleTwoTest() {
        List<Integer> arr = new ArrayList<>();
        arr.add(6997901);
        arr.add(6997927);
        arr.add(6997937);
        arr.add(6997967);
        arr.add(6998009);
        arr.add(6998029);
        arr.add(6998039);
        arr.add(6998051);
        arr.add(6998053);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(1, arr);
        Assertions.assertFalse(act);
    }

    @Test
    void exceptionTest() {
        List<Integer> arr = Arrays.asList(60, 80, 7, 13, 90, 40);
        MultiThread test = new MultiThread();
        Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> test.nonPrimeFinder(0, arr));

        Assertions.assertEquals("Wrong count of Threads", exception.getMessage());
    }

    @Test
    void twoThreadsTest() {
        List<Integer> arr = Arrays.asList(600, 800, 17, 13, 900, 400);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(2, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void threeThreadsTest() {
        List<Integer> arr = Arrays.asList(66, 88, 7, 13, 9, 44);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(3, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void fourThreadsTest()  {
        List<Integer> arr = Arrays.asList(6, 8, 777, 13, 9, 4);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(4, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void fiveThreadsTest()  {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 99, 4);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(5, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void threadsLessThanSizeTest()  {
        List<Integer> arr = Arrays.asList(500, 8, 7, 13);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(5, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void sixThreadsTest()  {
        List<Integer> arr = Arrays.asList(800, 8, 7, 13, 9, 4, 7890, 244);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(6, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void sevenThreadsTest()  {
        List<Integer> arr = Arrays.asList(400, 8, 7, 13, 9, 4, 678, 334, 1, 90);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(7, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void eightThreadsTest()  {
        List<Integer> arr = Arrays.asList(645, 86, 77, 7, 13, 9, 4, 77778, 5);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(8, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void nineThreadsTest()  {
        List<Integer> arr = Arrays.asList(645, 86, 77, 7, 13, 9, 4, 77778, 5,
                78, 98, 68, 988, 44, 55, 6);
        MultiThread test = new MultiThread();
        boolean act = test.nonPrimeFinder(9, arr);
        Assertions.assertTrue(act);
    }

}