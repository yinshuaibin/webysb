package com.ysb.test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yinshuaibin
 * @date 2021/6/8 9:54
 * @description 优先队列的使用
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
       Queue<User> queue = new PriorityQueue<>(new UserComparator());
       queue.offer(new User("xiaoming", 3));
       queue.offer(new User("xiaohong", 2));
       queue.offer(new User("xiaobai", 3));
        User poll = queue.poll();
        System.out.println(poll);
        User poll1 = queue.poll();
        System.out.println(poll1);
        User poll2 = queue.poll();
        System.out.println(poll2);
        // null 因为元素已经获取完了, 不要使用null添加队列, 因为无法判断是取出来的元素为null还是元素已经取完, 返回的null
        System.out.println(queue.poll());
    }
}

class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        int result = o1.getPriority() - o2.getPriority();
        if (result >= 0){
            return 1;
        }
        return 0;
    }
}

class User{
    private String username;
    private int priority;

    public User(String username, int priority){
        this.username = username;
        this.priority = priority;
    }

    public String getUsername(){
        return this.username;
    }

    public int getPriority(){
        return this.priority;
    }

    @Override
    public String toString(){
        return this.username + "_" + this.getPriority();
    }
}
