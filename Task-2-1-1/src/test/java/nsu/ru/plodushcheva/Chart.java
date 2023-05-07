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
     */
    private static CategoryDataset createDataset()  {

        final String succ = "Successively";
        final String thread = "Threads";
        final String ps = "ParallelsStream";

        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        double duration0 = nonPrimeFinder0();
        dataset.addValue(duration0, succ, "1S");

        double duration1 = nonPrimeFinder1();
        dataset.addValue(duration1, thread, "1");
        double duration2 = nonPrimeFinder2();
        dataset.addValue(duration2, thread, "2");
        double duration3 = nonPrimeFinder3();
        dataset.addValue(duration3, thread, "3");
        double duration4 = nonPrimeFinder4();
        dataset.addValue(duration4, thread, "4");
        double duration5 = nonPrimeFinder5();
        dataset.addValue(duration5, thread, "5");
        double duration6 = nonPrimeFinder6();
        dataset.addValue(duration6, thread, "6");
        double duration7 = nonPrimeFinder7();
        dataset.addValue(duration7, thread, "7");
        double duration8 = nonPrimeFinder8();
        dataset.addValue(duration8, thread, "8");
        double duration30 = nonPrimeFinder30();
        dataset.addValue(duration30, thread, "30");
        double duration150 = nonPrimeFinder150();
        dataset.addValue(duration150, thread, "150");

        double durationPs = nonPrimeFinderPs();
        dataset.addValue(durationPs, ps, "PS");

        return dataset;
    }

    /**
     * The main method for creating and displaying the diagram.
     *
     * @param args for main requirements
     */
    public static void main(String[] args)  {
        init();

        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Thread Time Chart",
                "Threads",
                "Thread Time (ms)",
                dataset
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

    private static double nonPrimeFinder0() {
        long startTime = System.nanoTime();
        Successively cl = new Successively();
        boolean ans = cl.nonPrimeFinder(arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinder1()  {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.nonPrimeFinder(1, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinder2()  {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.nonPrimeFinder(2, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinder3()  {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.nonPrimeFinder(3, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinder4()  {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.nonPrimeFinder(4, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinder5()  {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.nonPrimeFinder(5, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinder6()  {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.nonPrimeFinder(6, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinder7()  {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.nonPrimeFinder(7, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinder8()  {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.nonPrimeFinder(8, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinder30()  {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.nonPrimeFinder(30, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinder150()  {
        long startTime = System.nanoTime();
        MultiThread cla = new MultiThread();
        boolean ans = cla.nonPrimeFinder(150, arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }

    private static double nonPrimeFinderPs() {
        long startTime = System.nanoTime();
        ParallelsStream cl = new ParallelsStream();
        boolean ans = cl.nonPrimeFinder(arr);
        Assertions.assertFalse(ans);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6;
    }
}
