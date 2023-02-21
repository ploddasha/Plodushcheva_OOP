package nsu.ru.plodushcheva;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.jupiter.api.Assertions;

/**
 * Creating a diagram of the amount of time
 * spent on calculations with the threads.
 */
public class Chart {

    /**
     * create the data from which the diagram will be drawn.
     *
     * @return dataset
     * @throws Exception if multiThread method got exception
     */
    private static CategoryDataset createDataset() throws Exception {

        final String succ = "Successively";
        final String thread = "Threads";
        final String ps = "ParallelsStream";

        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        double duration0 = func0();
        dataset.addValue(duration0, succ, "0");

        double duration1 = func1();
        dataset.addValue(duration1, thread, "1");
        double duration2 = func2();
        dataset.addValue(duration2, thread, "2");
        double duration3 = func3();
        dataset.addValue(duration3, thread, "3");
        double duration4 = func4();
        dataset.addValue(duration4, thread, "4");
        double duration5 = func5();
        dataset.addValue(duration5, thread, "5");
        double duration6 = func6();
        dataset.addValue(duration6, thread, "6");
        double duration7 = func7();
        dataset.addValue(duration7, thread, "7");
        double duration8 = func8();
        dataset.addValue(duration8, thread, "8");

        double duration9 = func9();
        dataset.addValue(duration9, ps, "9");

        return dataset;
    }

    /**
     * The main method for creating and displaying the diagram.
     *
     * @param args for main requirements
     * @throws Exception of multiThread
     */
    public static void main(String[] args) throws Exception {
        init();

        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Thread Time Chart",
                "Threads",
                "Thread Time (ms)",
                (CategoryDataset) dataset
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);

    }

    private static List<Integer> arr;

    /**
     * creating a large list of prime numbers.
     */
    public static void init() {
        arr = new ArrayList<>();
        for (long i = 0; i < 1000000; i++) {
            arr.add(6998053);
        }
    }

    private static double func0() {
        long startTime = System.nanoTime();
        Successively cl = new Successively();
        boolean ans = cl.func(arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double func1() throws Exception {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.func(1, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double func2() throws Exception {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.func(2, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double func3() throws Exception {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.func(3, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double func4() throws Exception {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.func(4, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double func5() throws Exception {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.func(5, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double func6() throws Exception {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.func(6, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double func7() throws Exception {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.func(7, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double func8() throws Exception {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.func(8, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double func9() {
        long startTime = System.nanoTime();
        ParallelsStream cl = new ParallelsStream();
        boolean ans = cl.func(arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }
}
