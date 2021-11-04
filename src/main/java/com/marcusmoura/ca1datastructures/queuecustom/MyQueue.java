package com.marcusmoura.ca1datastructures.queuecustom;

import java.util.*;

public class MyQueue <T> {


    private List<T> myList = new ArrayList<>();
    private T front;
    private T rear;


    public MyQueue() {
    }

    private void setFront() {
        if (this.myList.isEmpty()) {
            this.front = null;
        } else {
            this.front = this.myList.get(0);
        }
    }

    private void setRear() {
        if (this.myList.isEmpty()) {
            this.rear = null;
        } else {
            this.rear = this.myList.get(this.myList.size());
        }
    }

    public T getFront() {
        if (this.myList.isEmpty()) {
            System.out.println("\nQueue is empty\n");
            return null;
        } else {
            return (this.myList.get(0));
        }
    }

    public T getRear() {
        return rear;
    }

    public Integer size() {
        Integer theSize = 0;
        for (T x : myList) {
            theSize++;
        }

        return theSize;
    }

    public void queueEnqueue(T item) {
        Boolean isThere = false;
        for (T t : this.myList) {
            if (t.equals(item)) {
                isThere = true;
            }
        }
        if (!isThere) {
            myList.add(item);
        } else {
            throw new IllegalArgumentException("Item already in the Queue");
        }
    }

    public void queueDequeue() {
        // is the queue empty?
        if (this.myList.isEmpty()) {
            System.out.println("\nQueue is empty\n");
            return;
        } else {
            System.out.println(this.myList.get(0));
            this.myList.remove(0);

        }
    }

    public void queueDisplay() {

        if (this.myList.isEmpty()) {
            System.out.println("\nQueue is empty\n");
            return;
        } else {
            System.out.println("Front");
            for (T t : this.myList) {
                System.out.println(t.toString());
            }
        }
    }

    public void queueFront() {


        if (this.myList.isEmpty()) {
            System.out.println("\nQueue is empty\n");
            return;
        } else {
            System.out.println("Front of Queue: ");
            System.out.println(this.myList.get(0).toString());
        }
    }

    public boolean isEmpty() {
        return this.myList.isEmpty();
    }


}
