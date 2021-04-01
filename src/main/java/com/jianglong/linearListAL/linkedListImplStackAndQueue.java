package com.jianglong.linearListAL;

//使用链表实现栈与队列
public class linkedListImplStackAndQueue {
    //栈的基本方法
    public interface Stack<E> {
        void push(E e);
        E pop();
        E peek();
        int getSize();
        boolean isEmpty();
    }

    //链表的基础类定义及方法实现
    public static class LinkedList<E> {
        //链表节点类实现
        private class Node{
            public E e;
            public Node next;
            public Node(E e,Node next){
                this.e=e;
                this.next=next;
            }
            public Node(E e){
                this.e=e;
                this.next=null;
            }
            public Node(){
                this(null,null);
            }
        }

        private Node dummyHead;//链表头结点
        private int size;//链表长度

        public LinkedList(){
            dummyHead=null;
            this.size=0;
        }

        public int getSize(){
            return size;
        }

        public boolean isEmpty(){
            return size==0;
        }

        //添加一个节点
        public void add(int index,E e) throws IllegalArgumentException{
            if(index<0||index>size) throw new IllegalArgumentException("Add failed,Illegal index.");
            Node prev=dummyHead;
            if(index==0) addFirst(e);
            for (int i = 0; i < index; i++) {
                prev=prev.next;
            }
            prev.next=new Node(e,prev.next);//使用这种方式就避免了双指针来分别记录上一个节点与下一个节点
            size++;
        }

        //在链表头部添加数据
        public void addFirst(E e){
            this.dummyHead=new Node(e,this.dummyHead);
            this.size++;
        }
        //在链表尾部添加元素
        public void addLast(E e){
            add(size,e);
        }

        //获取索引对应的值
        public E get(int index) throws IllegalArgumentException{
            if(index<0||index>size) throw new IllegalArgumentException("Add failed,Illegal index.");
            Node cur=dummyHead;
            for (int i = 0; i < index; i++) {
                cur=cur.next;
            }
            return cur.e;
        }
        //获取第一个元素
        public E getFirst(){
            return get(0);
        }
        //获取最后一个元素
        public E getLast(){
            return get(size-1);
        }

        //修改第index位置的元素
        public void set(int index,E e) throws IllegalArgumentException{
            if(index<0||index>size) throw new IllegalArgumentException("Add failed,Illegal index.");
            Node cur=dummyHead;
            for (int i = 0; i < index; i++) {
                cur=cur.next;
            }
            cur.e=e;
        }

        //遍历链表中是否存在元素
        public boolean contains(E e){
            Node cur=dummyHead;
            while (cur!=null){
                if(cur.e.equals(e)) return true;
                cur=cur.next;
            }
            return false;
        }

        //删除指定位置的元素
        public E remove(int index) throws IllegalArgumentException{
            if(index<0||index>size||size==0) throw new IllegalArgumentException("Add failed,Illegal index.");
            Node prev=dummyHead;
            if(index==0){
                dummyHead=prev.next;
                prev.next=null;
                return prev.e;
            }
            for (int i = 0; i < index; i++) {
                prev=prev.next;
            }
            Node retNode=prev.next;
            prev.next=retNode.next;
            retNode.next=null;
            size--;
            return retNode.e;
        }
        //删除第一个元素
        public E removeFirst(){
            return remove(0);
        }
        //删除最后一个元素
        public E removeLast(){
            return remove(size-1);
        }

        public String toString(){
            StringBuilder res=new StringBuilder();
            Node cur=dummyHead;
            while (cur!=null){
                res.append(cur.e+"-->");
                cur=cur.next;
            }
            res.append("NULL");
            return res.toString();
        }
    }

    //借助链表实现栈的相关操作
    public static class LinkedListStack<E> implements Stack<E>{
        private LinkedList<E> list;
        public LinkedListStack(){
            list=new LinkedList<E>();
        }

        public void push(E e) {
            list.addFirst(e);
        }

        public E pop() {
            return list.removeFirst();
        }

        public E peek() {
            return list.getFirst();
        }

        public int getSize() {
            return list.getSize();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("Stack top ");
            res.append(list);
            return res.toString();
        }
    }

    //队列的接口定义
    public interface Queue<E>{
        int getSize();
        boolean isEmpty();
        void enqueue(E e);
        E dqueue();
        E getFront();
    }
    //队列的基本操作
    public static class LinkedListQueue<E> implements Queue<E>{

        // 定义的节点
        private class Node {
            public E e;
            public Node next;

            public Node(E e, Node next) {
                this.e = e;
                this.next = next;
            }

            public Node(E e) {
                this(e, null);
            }

            public Node() {
                this(null, null);
            }

            @Override
            public String toString() {
                return e.toString();
            }
        }

        //定义一个头指针和尾指针
        private Node head,tail;
        private int size;
        public LinkedListQueue(){
            this.head=null;
            this.tail=null;
            this.size=0;
        }

        //获取队列元素的数量
        public int getSize() {
            return size;
        }
        //判断队列是否为空
        public boolean isEmpty() {
            return size==0;
        }

        //入队操作
        public void enqueue(E e) {
            if(tail==null){
                tail=new Node(e);
                head=tail;
            }else {
                tail.next=new Node(e);
                tail=tail.next;
            }
            size++;
        }
        //出队操作
        public E dqueue() throws IllegalArgumentException{
            if(isEmpty()) throw new IllegalArgumentException("Queue is empty");
            Node retNode=head;//取队首元素并拿掉
            head=head.next;
            retNode.next=null;
            if(head==null) tail=null;
            size--;
            return retNode.e;
        }

        //获取队首
        public E getFront() throws IllegalArgumentException{
            if(isEmpty()) throw new IllegalArgumentException("Queue is empty");
            return head.e;
        }

        public String toString(){
            StringBuilder res=new StringBuilder();
            res.append("Queue front ");
            Node cur=head;
            while(cur!=null){
                res.append(cur+"-->");
                cur=cur.next;
            }
            res.append("NULL tail");
            return res.toString();
        }

    }
    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
        for(int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
        for(int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }

}
