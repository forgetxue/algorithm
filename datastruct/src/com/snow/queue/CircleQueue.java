package com.snow.queue;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-03-10
 **/
public class CircleQueue {
    private int front;
    private int rear;
    private int maxSize;
    private int[] arr;

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    /*
     * 判断是否为空 空返回true
     */
    public boolean isEmpty() {
        return rear == front;
    }
    /*
     * 判断队列是否已满 满返回true
     */

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
        /*
         * rear指向队尾元素的下一个位置，为了区分队列空时的的条件，留出一个位置，当这个空出来的位置即rear与出现在front后面
         * 或者front在0，rear在maxSize-1时队列满，前者情况rear下标比front小于1，结合第二种情况
         * 满队的条件为(rear + 1) % maxSize
         */
    }
    //得到队列中的元素个数
    public int length() {
        return (rear - front + maxSize) % maxSize;
        /*
         * 1.当rear大于front时，lenght = rear - front
         * 2.当rear小于front时，length分为两部分，
         * 		①：maxSize - front
         * 		②： rear -  0
         * 	此时length = rear - front + maxSize
         *
         * 结合两种情况：(rear -front + maxSize) % maxSize
         *
         */
    }
    //入队
    public void enQueue(int e) {
        if(isFull()) {
            throw new RuntimeException("队列满~~~");
        }
        arr[rear] = e;
        //实现循环
        rear = (rear + 1) % maxSize;
        System.out.println("入队成功！");
    }

    //出队
    public int deQueue() {
        if(isEmpty()) {
            throw new RuntimeException("队列已空~~~");
        }
        int re = arr[front];
        front = (front + 1) % maxSize;
        return re;
    }
    //遍历队列

    public void travel() {
        if(isEmpty()) {
            throw new RuntimeException("队列空~~");
        }
        for(int i = front; i < length();i++) {
            System.out.println(arr[i % maxSize]);
        }
    }

}
