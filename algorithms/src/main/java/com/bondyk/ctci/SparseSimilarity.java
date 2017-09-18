package com.bondyk.ctci;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Sparse Similarity: The similarity of two documents (each with distinct words) is defined to be the
 size of the intersection divided by the size of the union. For example, if the documents consist of
 integers, the similarity of {1, 5, 3} and { 1, 7, 2, 3} is 0. 4, because the intersection has size
 2 and the union has size 5.
 We have a long list of documents (with distinct values and each with an associated ID) where the
 similarity is believed to be "sparse:'That is, any two arbitrarily selected documents are very likely to
 have similarity O. Design an algorithm that returns a list of pairs of document IDs and the associated
 similarity.
 Print only the pairs with similarity greater than 0. Empty documents should not be printed at all. For
 simplicity, you may assume each document is represented as an array of distinct integers.
 EXAMPLE
 Input:
 13: {14, 15, 100, 9, 3}
 16: {32, 1, 9, 3, 5}
 19: {15, 29, 2, 6, 8, 7}
 24: {7, 10}
 Output:
 ID1, ID2, SIMILARITY
 13, 19       0.1
 13, 16       0.25
 19, 24       0.14285714285714285
  */
public class SparseSimilarity {

    public static void main(String[] args) {

        printSimilarity(new Document[] {
                new Document(13, new int[] { 14, 15, 100, 9, 3 }),
                new Document(16, new int[] { 32, 1, 9, 3, 5 }),
                new Document(19, new int[] { 15, 29, 2, 6, 8, 7 }),
                new Document(24, new int[] { 7, 10 })
        });

    }

    private static void printSimilarity(Document[] docs) {
        System.out.println("-------------------------------------------------------");
        printSimilarity0(docs);
        System.out.println("-------------------------------------------------------");
        printSimilarity1(docs);
    }

    private static void printSimilarity0(Document[] docs) {

        for (int i = 0; i < docs.length; i++) {
            for (int j = i + 1; j < docs.length; j++) {
                docs[i].printSimilarity(docs[j]);
            }
        }

    }

    private static void printSimilarity1(Document[] docs) {
        // Build mapping word -> docs and then for each pair of docs calculate number of same elements (intersections)
        //After that we can calculate sparse similarity: similarity = intersection - (doc1.size + doc2.size - intersection)
        Map<Pair, Integer> intersectionMap = new HashMap<>();
        Map<Integer, Set<Document>> wordInDocsMap = new HashMap<>();
        for (Document doc : docs) {
            for (Integer word : doc.elements) {
                if (!wordInDocsMap.containsKey(word)) {
                    wordInDocsMap.put(word, new HashSet<>());
                }
                Set<Document> existingDocs = wordInDocsMap.get(word);
                for (Document existingDoc : existingDocs) {
                    Pair pair = new Pair(existingDoc, doc);
                    if (!intersectionMap.containsKey(pair)) {
                        intersectionMap.put(pair, 0);
                    }
                    int intersectionsCount = intersectionMap.get(pair);
                    intersectionMap.put(pair, intersectionsCount + 1);
                }

                wordInDocsMap.get(word).add(doc);
            }
        }

        for (Map.Entry<Pair, Integer> entry : intersectionMap.entrySet()) {
            int intersection = entry.getValue();
            if (intersection != 0) {
                Pair pair = entry.getKey();
                int union = pair.totalSize - intersection;
                double similarity = (double)intersection / union;
                System.out.println(pair.doc1 + ", " + pair.doc2 + "   " + similarity);
            }

        }
    }

    private static class Document {
        private final int id;
        private final Set<Integer> elements;

        Document(int id, int[] elems) {
            this.id = id;
            this.elements = new HashSet<>();
            for (int elem : elems) {
                this.elements.add(elem);
            }
        }

        private void printSimilarity(Document d) {
            int intersection = 0;
            for (Integer element : elements) {
                if (d.elements.contains(element)) {
                    intersection++;
                }
            }

            if (intersection != 0) {
                int union = elements.size() + d.elements.size() - intersection;
                System.out.println(id + ", " + d.id + "   " + ((double)intersection / union));
            }

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Document document = (Document) o;

            return id == document.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    private static class Pair {
        private final int doc1;
        private final int doc2;
        private int totalSize = 0;

        Pair(Document doc1, Document doc2) {
            this.doc1 = doc1.id;
            this.doc2 = doc2.id;
            this.totalSize = doc1.elements.size() + doc2.elements.size();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            return (doc1 == pair.doc1 && doc2 == pair.doc2)
                    || doc1 == pair.doc2 && doc2 == pair.doc1;
        }

        @Override
        public int hashCode() {
            return 101 * doc1 + 101 * doc2;
        }
    }
}
