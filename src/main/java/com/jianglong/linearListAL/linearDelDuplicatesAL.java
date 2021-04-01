package com.jianglong.linearListAL;

/*数组及链表去重*/
public class linearDelDuplicatesAL {

    /*
    * 如何给有序数组去重？通用技巧是尽量避免在中间删除元素，想办法将重复元素放置于数组尾部，使用pop方法删除即可。
    * 这样不需要额外空间，时间复杂度也降低到O(1)。如双指针方法（快慢指针方法）：
    * 我们将慢指针slow走在后面，快指针fast走在前面探路，找到一个不重复的元素就告诉slow并让slow前进一步。
    * 这样当fast指针遍历完整个数组nums后，nums[0...slow]就是不重复元素，之后的元素都是重复元素。
    * */
    public int removeArrayDuplicates(int[] nums){
        int len=nums.length;
        if(len==0) return 0;
        int slow=0,fast=1;
        while(fast<len){
            if(nums[slow]!=nums[fast]){
                slow++;
                nums[slow]=nums[fast];
            }
            fast++;
        }
        return slow+1;
    }

    /*
    * 对于有序链表而言，去重算法原理其实是和数组是一模一样的，唯一的区别是把数组的复制操作变成操作指针而已。
    * */
    //定义链表类
    public static class Node <T>{
        private T value;
        private Node next;
        public Node(T value,Node next){
            this.value=value;
            this.next=next;
        }
        public Node(T value){
            this(value,null);
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    public static class Linked <T>{
        //定义链表节点类

        private Node head;//头结点
        private int size;//链表元素个数
        //构造函数
        public Linked(){
            this.head=null;
            this.size=0;
        }

        public Node getHead() {
            return head;
        }

        public int getSize() {
            return size;
        }

        //链表头部添加元素
        public void addFirst(T value){
            Node node=new Node(value,this.head);
            this.head=node;
            this.size++;
        }
        //链表尾部添加元素
        public void addLast(T value){
            this.add(value,this.size);
        }
        //链表中间添加元素
        public void add(T value,int index) throws IllegalArgumentException{
            if(index<0||index>size) throw new IllegalArgumentException("index is not in right range");
            if(index==0){
                this.addFirst(value);
                return;
            }
            Node preNode=this.head;
            //找到要插入节点的前一个节点
            for (int i = 0; i < index-1; i++) {
                preNode=preNode.next;
            }
            //要插入节点的下一个节点指向preNode节点的下一个节点
            Node node=new Node(value,preNode.next);
            //preNode的下一个节点指向要插入的节点node
            preNode.next=node;
            this.size++;
        }
        //删除链表元素
        public void remove(T value){
            if(this.head==null){
                System.out.println("无元素可以删除");
                return;
            }
            //要删除的元素与头结点元素相同
            while(this.head.value.equals(value)){
                this.head=this.head.next;
                this.size--;
            }
            /*上面已经对头结点判别是否要进行删除，所以要对头结点的下一个节点进行判别*/
            Node cur=this.head;
            while(cur!=null&&cur.next!=null){
                if(cur.next.value.equals(value)){
                    this.size--;
                    cur.next=cur.next.next;
                }
                else cur=cur.next;
            }
        }
        //删除链表第一个元素
        public void removeFirst(){
            if(this.head==null){
                System.out.println("无元素可删除");
                return;
            }
            this.head=this.head.next;
            this.size--;
        }
        //删除链表的最后一个元素
        public void removeLast(){
            if(this.head==null){
                System.out.println("无元素可删除");
                return;
            }
            //只有一个元素
            if(this.size==1) this.removeFirst();
            Node cur=this.head;//记录当前节点
            Node pre=this.head;//记录要删除节点的前一个节点
            while(cur.next!=null){
                pre=cur;
                cur=cur.next;
            }
            pre.next=cur.next;
            this.size--;
        }
        //链表中是否包含某个元素
        public boolean isContains(T value){
            Node cur=this.head;
            while(cur!=null){
                if(cur.value.equals(value)) return true;
                else cur=cur.next;
            }
            return false;
        }
        public String toString(){
            StringBuffer sb=new StringBuffer();
            Node cur=this.head;
            while(cur!=null){
                sb.append(cur.value+"->");
                cur=cur.next;
            }
            sb.append("NULL");
            return sb.toString();
        }
    }

    //删除单向链表重复元素
    public void deleteLinkedListDuplicates(Linked<Integer> linkedList){
        Node head=linkedList.head;
        if(head==null) return;
        Node slow=head,fast=head.next;
        while (fast!=null){
            if(fast.value!=slow.value){
                slow.next=fast;
                slow=slow.next;
            }
            fast=fast.next;
        }
        slow.next=null;
        //return linkedList;
    }

    public static void main(String[] args) {
        int[] array={0,1,1,1,2,2,4};
        linearDelDuplicatesAL la=new linearDelDuplicatesAL();
        Linked<Integer> linked=new Linked<Integer>();
        for (int i = 0; i < array.length; i++) {
            linked.addLast(array[i]);
        }
        System.out.println(linked.toString());
        la.deleteLinkedListDuplicates(linked);
        System.out.println(linked.toString());
    }
}
