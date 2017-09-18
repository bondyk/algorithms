package com.bondyk.ctci;


import java.util.PriorityQueue;

/**
 * Numbers are randomly generated and passed to a method. Write a program
 to find and maintain the median value as new values are generated.
 */
public class ContinuousMedian {

    //a max heap for the values below the median
    private final PriorityQueue<Integer> maxHeap;
    //a min heap for the values above the median
    private final PriorityQueue<Integer> minHeap;

    public ContinuousMedian() {
        this.minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
        this.maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
    }

    private void add(int element) {
        if (!minHeap.isEmpty() && element > minHeap.peek()) {
            minHeap.add(element);
        } else {
            maxHeap.add(element);
        }

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        } else if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        }

        System.out.println("add " + element + ", median " + getMedian());
    }

    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }

        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        ContinuousMedian cm = new ContinuousMedian();

        cm.add(3);//3
        cm.add(5);//4
        cm.add(7);//5
        cm.add(1);//4
        cm.add(2);//3
        cm.add(0);//2.5
        cm.add(10);//3
        cm.add(15);//4
        cm.add(13);//5
        cm.add(17);//6
        cm.add(4);//5
    }
}
