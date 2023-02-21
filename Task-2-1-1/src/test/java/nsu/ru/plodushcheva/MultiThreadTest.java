package nsu.ru.plodushcheva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MultiThreadTest {

    @Test
    void exampleOneTest() throws Exception {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4);
        MultiThread test = new MultiThread();
        boolean act = test.func(1, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void exampleTwoTest() throws Exception {
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
        boolean act = test.func(1, arr);
        Assertions.assertFalse(act);
    }

    @Test
    void exceptionTest() {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4);
        MultiThread test = new MultiThread();
        Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> test.func(0, arr));

        Assertions.assertEquals("Wrong count of Threads", exception.getMessage());
    }

    @Test
    void twoThreadsTest() throws Exception {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4);
        MultiThread test = new MultiThread();
        boolean act = test.func(2, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void threeThreadsTest() throws Exception {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4);
        MultiThread test = new MultiThread();
        boolean act = test.func(3, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void fourThreadsTest() throws Exception {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4);
        MultiThread test = new MultiThread();
        boolean act = test.func(4, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void fiveThreadsTest() throws Exception {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4);
        MultiThread test = new MultiThread();
        boolean act = test.func(5, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void sixThreadsTest() throws Exception {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4, 7890, 244);
        MultiThread test = new MultiThread();
        boolean act = test.func(6, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void sevenThreadsTest() throws Exception {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4, 678, 334, 1, 0);
        MultiThread test = new MultiThread();
        boolean act = test.func(7, arr);
        Assertions.assertTrue(act);
    }

    @Test
    void eightThreadsTest() throws Exception {
        List<Integer> arr = Arrays.asList(645, 86, 77, 7, 13, 9, 4, 77778, 5);
        MultiThread test = new MultiThread();
        boolean act = test.func(8, arr);
        Assertions.assertTrue(act);
    }

}