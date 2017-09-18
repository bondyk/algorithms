package com.bondyk.ctci;

/**
 * Given a sorted array of strings that is interspersed with empty strings, write a
 method to find the location of a given string.
 EXAMPLE
 Input: ball, {"at", "" , "" , "" ,  "ball", "" , "" , "car",  "" , "" , "dad", "" , ""}
 Output: 4
 */
public class SparseSearch {

    public static void main(String[] args) {
        String[] array = {"at", "" , "" , "" ,  "ball", "", "" , "car",  "" , "" , "dad", "" , ""};
        System.out.println(search(array, "ball", 0, array.length));
    }

    private static int search(String[] array, String s, int from, int to) {
        if (from <= to) {
            int middle = (from + to) / 2;
            int l = middle;
            int r = middle;
            while ("".equals(array[middle]) && (l >= 0 || r < array.length)) {
                if (l >= 0 && !"".equals(array[l])) {
                    middle = l;
                } else if (r < array.length) {
                    middle = r;
                }
                l--;
                r++;
            }

            if (!"".equals(array[middle])) {

                int cmp = array[middle].compareTo(s);
                if (cmp < 0) {
                    return search(array, s, middle + 1, to);
                } else if (cmp > 0) {
                    return search(array, s, from, middle - 1);
                } else {
                    return middle;
                }
            }
        }

        return -1;
    }
}
