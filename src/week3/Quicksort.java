package week3;

import edu.princeton.cs.introcs.StdRandom;

// Recursive quicksort implementation
public class Quicksort {

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;
        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) break;
            }
            while (less(a[lo], a[--j])) {
                if (j == lo) break;
            }

            if (i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    private static void threeWaySort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi, i = lo;
        Comparable v = a[i];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)
                exch(a, lt++, i++);
            else if (cmp > 0)
                exch(a, i, gt--);
            else
                i++;
        }

        threeWaySort(a, lo, lt-1);
        threeWaySort(a, gt+1, hi);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 1, a.length-1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] test = { 2, 5, -10, 15, 20, 7, 9 };
        sort(test);
        for (int i : test)
            System.out.println(i);
    }
}
