package ru.nsu.plodushcheva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



/**
 * SubString finds the specified substring in the text.
 * The text is read in a stream, using the Knuth-Morris-Pratt algorithm.
 */
public class SubString {

    /*private final InputStream stream ;

    public SubString(InputStream stream) {
        this.stream = stream;
    } */

    /**
     * file stream is passed to the InputStreamReader object,
     * which converts the byte stream to character stream.
     *
     * @param sub is the string whose occurrences we are looking for
     * @return array with indices of substring occurrences
     * @throws IOException while reading file
     */
    public static List<Integer> subStringFinder(String sub, InputStream stream)
            throws IOException {
        //param stream the stream of file from resources
        InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);

        try (Reader reader = new BufferedReader(streamReader)) {
            List<Integer> result;
            result = kmp(reader, sub);
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
     *
     * @throws IOException wile working with text
     */
    static List<Integer> kmp(Reader text, String sub) throws IOException {
        List<Integer> arr = new ArrayList<Integer>();
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
                    i++;
                }
            }
        }
        return arr;
    }

    /**
     * prefix function,
     * an array of numbers is defined as follows:
     * element is the longest length of the largest self suffix of a substring
     * that matches its prefix (self suffix means not matching the whole string).
     *
     * @param sub is the string whose occurrences we are looking for
     * @param lps a prefix array
     */
    static void computePrefixArray(String sub, int[] lps) {
        int len = sub.length();

        for (int i = 1; i < len; i++) {
            int j = 0;
            while (i + j < len && sub.charAt(i + j) == sub.charAt(j)) {
                lps[i + j] = Math.max(lps[i + j], j + 1);
                j++;
            }
        }
    }

}
