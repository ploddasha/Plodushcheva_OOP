package ru.nsu.plodushcheva;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class SubString {

    public static ArrayList<Integer> subStringFinder (String sub,File fileName) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            ArrayList<Integer> result = new ArrayList<>();
            kmp(reader,sub, result);
            return result;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    static void kmp (BufferedReader text, String sub, ArrayList<Integer> arr) throws IOException {

        int M = sub.length();
        int lps[] = new int[M];
        computeLPSArray(sub, lps);

        int i = 0;
        int j = 0;
        int a = text.read();
        while (a != -1) {
            if (sub.charAt(j) == (char) a) {
                a = text.read();
                j++;
                i++;
            }
            if (j == M) {
                arr.add(i-j);
                j = lps[j - 1];
            }
            else if ( a!=-1 && sub.charAt(j) != (char) a) {
                if (j != 0) {
                    j = lps[j - 1];
                }else {
                    a = text.read();
                    i = i + 1;
                }
            }
        }
    }

    static void computeLPSArray(String sub, int lps[])
    {
        int M = sub.length();
        lps[0] = 0;

        for (int i=1; i<M; ++i) {
            int j = lps[i-1];
            while (j > 0 && sub.charAt(i) != sub.charAt(j))
                j = lps[j-1];
            if (sub.charAt(i) == sub.charAt(j)){
                ++j;
            }
            lps[i] = j;
        }
    }

}
