package org.example.nminexcel.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.xml.sax.SAXException;

public final class Demo {
    private static final int size = 20;
    private static final int n = 3;

    public static void testNMinStorage() {
        NMinStorage nMinStorage = new NMinStorage(n);

        int[] arr = new Random().ints(0, 20)
            .limit(size)
            .toArray();

        for (int i = 0; i < arr.length; i++) {
            nMinStorage.push(arr[i]);
        }

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Heap: " + nMinStorage);
        System.out.println(n+"-th min element: " + nMinStorage.getNthMin());
    }

    public static void parse(int n, String filename)
        throws OpenXML4JException, IOException, SAXException {
        System.out.println(Parser.parse(n, filename));
    }
}
