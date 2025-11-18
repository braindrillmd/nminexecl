package org.example.nminexcel.util;

import java.util.Collections;
import java.util.Optional;
import java.util.PriorityQueue;

public class NMinStorage {
    protected int maxSize;
    protected  PriorityQueue<Integer> maxNHeap;

    public NMinStorage(int maxSize) {
        this.maxSize = maxSize;

        maxNHeap = new PriorityQueue<>(maxSize, Collections.reverseOrder());
    }

    public void push(int value) {
        if(!maxNHeap.contains(value)) {
            if (maxNHeap.size() < maxSize) {
                maxNHeap.add(value);
            } else if (maxNHeap.peek() != null && value < maxNHeap.peek()) {
                maxNHeap.poll();
                maxNHeap.add(value);
            }
        }
    }

    public Optional<Integer> getNthMin() {
        if(maxNHeap.size() < maxSize || maxNHeap.peek() == null) {
            return Optional.empty();
        }
        else {
            return Optional.of(maxNHeap.peek());
        }
    }

    public String toString(){
        return maxNHeap.toString();
    }
}