package ru.nsu.plodushcheva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


/**
 * SubString finds the specified substring in the text.
 * The text is read in a stream, using the Knuth-Morris-Pratt algorithm.
 */
public class SubString {

    /**
     * file stream is passed to the InputStreamReader object,
     * which converts the byte stream to character stream.
     *
     * @param sub is the string whose occurrences we are looking for
     * @param fileName the name of the file with text
     * @return array with indices of substring occurrences
     * @throws IOException while reading file
     */
    public static ArrayList<Integer> subStringFinder(String sub, File fileName)
            throws IOException {

        FileInputStream file = new FileInputStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(file, StandardCharsets.UTF_8);
        try (BufferedReader reader = new BufferedReader(streamReader)) {
            ArrayList<Integer> result = new ArrayList<>();
            kmp(reader, sub, result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Knuth–Morris–Pratt string-searching algorithm (or KMP algorithm)
     * searches for occurrences of a word within a main text string by employing the observation
     * that when a mismatch occurs, the word itself embodies sufficient information to determine
     * where the next match could begin,
     * thus bypassing re-examination of previously matched characters.
     *
     * @param text is source text
     * @param sub is the string whose occurrences we are looking for
     * @param arr is array with indices of substring occurrences
     * @throws IOException wile working with text
     */
    static void kmp(BufferedReader text, String sub, ArrayList<Integer> arr) throws IOException {

        int len = sub.length();
        int[] lps = new int[len];
        computePrefixArray(sub, lps);

        int i = 0;
        int j = 0;
        int a = text.read();
        while (a != -1) {
            if (sub.charAt(j) == (char) a) {
                a = text.read();
                j++;
                i++;
            }
            if (j == len) {
                arr.add(i - j);
                j = lps[j - 1];
            } else if (a != -1 && sub.charAt(j) != (char) a) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    a = text.read();
                    i = i + 1;
                }
            }
        }
    }

    /**
     * prefix function,
     * i.e. an array of numbers is defined as follows:
     * element is the longest length of the largest self suffix of a substring
     * that matches its prefix (self suffix means not matching the whole string).
     *
     * @param sub is the string whose occurrences we are looking for
     * @param lps a prefix array
     */
    static void computePrefixArray(String sub, int[] lps) {
        int len = sub.length();
        lps[0] = 0;

        for (int i = 1; i < len; ++i) {
            int j = lps[i - 1];
            while (j > 0 && sub.charAt(i) != sub.charAt(j)) {
                j = lps[j - 1];
                if (sub.charAt(i) == sub.charAt(j)) {
                    ++j;
                }
                lps[i] = j;
            }
        }
    }

}
