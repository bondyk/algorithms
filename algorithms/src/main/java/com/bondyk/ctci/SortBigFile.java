package com.bondyk.ctci;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

/**
 * Imagine you have a 20 GB file with one string per line. Explain how you would sort the file.
 *
 * This algorithm is known as external sort
 */
public class SortBigFile {

    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static final int BUFFER_SIZE = 1000000;
    private static final File RESULT_FOLDER = new File("algorithms/bin/ctci/sort-big-file/result");

    public static void main(String[] args) {
        try {
            deleteFolder(RESULT_FOLDER.toPath());
            if (!RESULT_FOLDER.mkdirs()) {
                System.err.println("Folder not created: " + RESULT_FOLDER.getAbsolutePath());
            }
            File f = sortFile("/ctci/sort-big-file/test1.txt");
            System.out.println(f.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File sortFile(String fileName) throws IOException {


        InputStream input = SortBigFile.class.getResourceAsStream(fileName);
        int size = input.available();
        File b1 = new File(RESULT_FOLDER,"sort-bucket-0.txt");
        File b2 = new File(RESULT_FOLDER,"sort-bucket-1.txt");
        File[] bb = {b1, b2};
        int k = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, CHARSET))) {
            char[] chars = new char[BUFFER_SIZE];
            int offset = 0;
            int readAmount;
            while ((readAmount = reader.read(chars, 0, BUFFER_SIZE)) > 0) {
                Arrays.sort(chars, 0, readAmount);
                append(bb[k % bb.length], chars, readAmount);
                offset += readAmount;
                k++;
            }
        }

        return size > BUFFER_SIZE ? mergeBuckets(b1, b2, BUFFER_SIZE, size) : b1;
    }

    private static File mergeBuckets(File f1, File f2, int bucketSize, long fileSize) throws IOException {

        File b1 = new File(RESULT_FOLDER,"merge-bucket-" + bucketSize + "-0.txt");
        File b2 = new File(RESULT_FOLDER,"merge-bucket-" + bucketSize + "-1.txt");
        File[] bb = {b1, b2};
        int fi = 0;
        try (BufferedReader r1 = Files.newBufferedReader(f1.toPath(), CHARSET);
             BufferedReader r2 = Files.newBufferedReader(f2.toPath(), CHARSET)) {
            //TODO: read on demand during merge
            int offset = 0;
            while (offset < Math.max(f1.length(), f2.length())) {
                merge(bb[fi % bb.length], r1, r2, offset, bucketSize);
                offset += bucketSize;
                fi++;
            }
        }

        if (bucketSize < fileSize && fi > 1) {
            return mergeBuckets(b1, b2, bucketSize * 2, fileSize);
        }
        return b1;
    }

    private static long merge(File mergeResultFile, BufferedReader r1, BufferedReader r2, int offset, int bucketSize) throws IOException {

        char[] b1chars = new char[BUFFER_SIZE];
        char[] b2chars = new char[BUFFER_SIZE];
        char[] merged = new char[BUFFER_SIZE];

        int endpoint = offset + bucketSize;
        int i1 = 0;
        int i2 = 0;
        int b1read = 0;
        int b2read = 0;
        int offset1 = offset;
        int offset2 = offset;
        int i = 0;
        while (i < bucketSize * 2) {
            if (b1read != -1 && i1 >= b1read && offset1 < endpoint) {
                b1read = r1.read(b1chars, 0, Math.min(BUFFER_SIZE, endpoint - offset1));
                if (b1read > 0) {
                    offset1 += b1read;
                }
                i1 = 0;
            }
            if (b2read != -1 && i2 >= b2read && offset2 < endpoint) {
                b2read = r2.read(b2chars, 0, Math.min(BUFFER_SIZE, endpoint - offset2));
                if (b2read > 0) {
                    offset2 += b2read;
                }
                i2 = 0;
            }

            if (i1 < b1read && (i2 >= b2read || b1chars[i1] <= b2chars[i2])) {
                merged[i++] = b1chars[i1++];
            } else if (i2 < b2read && (i1 >= b1read || b1chars[i1] > b2chars[i2])) {
                merged[i++] = b2chars[i2++];
            } else {
                append(mergeResultFile, merged, i);
                break;
            }

            if (i >= merged.length) {
                append(mergeResultFile, merged, i);
                merged = new char[BUFFER_SIZE];
                i = 0;
            }
        }

        return i;
    }

    private static void append(File f, char[] chars, int length) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(f.toPath(), CHARSET, StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            writer.append(new String(chars, 0, length));
        }
    }

    private static void deleteFolder(Path folderPath) throws IOException {
        if (Files.exists(folderPath)) {
            Files.walkFileTree(folderPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }
}
