package com.jianglong.linearListAL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import static com.jianglong.linearListAL.linearDelDuplicatesAL.Linked;
import static com.jianglong.linearListAL.linearDelDuplicatesAL.Node;

/*单链表相关算法题*/
public class linkedListAL {
    /*
    * 题目1：输出单链表倒数第k个节点
    * 问题：输入一个单链表，输出此链表中的倒数第k个节点
    * */
    /*
    * 思路一：两次遍历法
    *         （1）遍历单链表，遍历同时得出链表长度N。
    *         （2）再次从头遍历，访问至第N-k个节点为所求节点
    * 时间复杂度：O(n*n)
    * */
    //计算链表长度
    public int listLength(Node<Integer> head){
        int count=0;
        Node<Integer> cur=head.getNext();
        if(cur==null) return 0;
        while (cur!=null){
            count++;
            cur=cur.getNext();
        }
        return count;
    }
    //查找第k个节点的值
    public Node<Integer> searchNodeK(Node<Integer> head,int k){
        int i=0;
        Node<Integer> cur=head;
        int len=listLength(head);
        if(k>len) return null;
        //循环len-k+1次
        for (i = 0; i < len-k+1; i++) {
            cur=cur.getNext();
        }
        return cur;//返回倒数第k个节点
    }

    /*
     * 思路二：双指针法
     *         （1）定义两个指针 p1 和 p2 分别指向链表头节点。
               （2）p1 前进 K 个节点，则 p1 与 p2 相距 K 个节点。
               （3）p1，p2 同时前进，每次前进 1 个节点。
               （4）当 p1 指向到达链表末尾，由于 p1 与 p2 相距 K 个节点，则 p2 指向目标节点。
     * 时间复杂度：O(n)
     * */
    public Node<Integer> searchNodeK2(Node head,int k){
        if(head==null || head.getNext()==null) return null;
        //n1,n2均指向头结点
        Node n1=head,n2=head;
        //n1先出发，先前进k个节点
        for (int i = 0; i < k; i++) {
            if(n1!=null) n1=n1.getNext();
            else return null;
        }
        while (n1!=null){
            n1=n1.getNext();
            n2=n2.getNext();
        }
        return n2;
    }

    /*
    * 题目2：判断链表是否有环
    * 问题：单链表中的环是指链表末尾的节点next指针不为NULL，而是指向了链表中的某个节点，导致链表中出现了环形结构。
    * 思路：
    *       （1）定义两个指针分别为 slow，fast，并且将指针均指向链表头节点。
    *       （2）规定，slow 指针每次前进 1 个节点，fast 指针每次前进两个节点。
    *       （3）当 slow 与 fast 相等，且二者均不为空，则链表存在环。
    *        若链表中存在环，则快慢指针必然能在环中相遇。这就好比在环形跑道中进行龟兔赛跑。由于兔子速度大于乌龟速度，则必然会出现兔子与乌龟再次相遇情况。
    *        因此，当出现快慢指针相等时，且二者不为NULL，则表明链表存在环。
    * */
    public boolean isExistLoop(Node head){
        Node fast=head,slow=head;//定义快慢指针，慢指针每次前进一个节点，快指针每次前进两个节点
        //当没有到达链表结尾时，继续前进
        while(slow!=null && fast.getNext()!=null){
            slow=slow.getNext();//慢指针前进一个节点
            fast=fast.getNext().getNext();//快指针前进两个节点
            if(slow==fast) return true;//若两个指针相遇，且均不存在NULL则存在环
        }
        return false;
    }

    /*
     * 题目3：使用链表实现大数加法
     * 问题： 两个用链表代表的整数，其中每个节点包含一个数字。数字存储按照在原来整数中相反的顺序，使得第一个数字位于链表的开头。
     *       写出一个函数将两个整数相加，用链表形式返回和。
     * 思路：
     *       （1）定义两个指针分别为 slow，fast，并且将指针均指向链表头节点。
     *       （2）规定，slow 指针每次前进 1 个节点，fast 指针每次前进两个节点。
     *       （3）当 slow 与 fast 相等，且二者均不为空，则链表存在环。
     *        若链表中存在环，则快慢指针必然能在环中相遇。这就好比在环形跑道中进行龟兔赛跑。由于兔子速度大于乌龟速度，则必然会出现兔子与乌龟再次相遇情况。
     *        因此，当出现快慢指针相等时，且二者不为NULL，则表明链表存在环。
     * */
    public Node<Integer> numberAddAsList(Node<Integer> n1,Node<Integer> n2){
        Node<Integer> ret=n1,pre=n1;//ret记录初始化头结点，pre记录当前节点移动到的位置
        int up=0;
        while(n1!=null && n2!=null){
            //当前位置数值相加
            n1.setValue(n1.getValue()+n2.getValue()+up);
            //计算是否有进位
            up=n1.getValue()/10;
            //保留计算结果的个位
            n1.setValue(n1.getValue()%10);
            //记录当前节点位置
            pre=n1;
            //同时节点向后移位
            n1=n1.getNext();
            n2=n2.getNext();
        }
        //若n1到达末尾，说明n1长度小于n2
        if(n1==null) pre.setNext(n2);
        n1=pre.getNext();
        //继续计算剩余节点
        while(n1!=null){
            n1.setValue(n1.getValue()+up);
            up=n1.getValue()/10;
            n1.setValue(n1.getValue()%10);
            pre=n1;
            n1=n1.getNext();
        }
        if(up!=0){
            Node<Integer> tmp=new Node<Integer>(up);
            pre.setNext(tmp);
        }
        return ret;
    }

    /*
     * 题目4：有序链表合并
     * 问题： 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 思路1：
     *       （1）对空链表存在的情况进行处理，假如 pHead1 为空则返回 pHead2 ，pHead2 为空则返回 pHead1。（两个都为空此情况在pHead1为空已经被拦截）
     *       （2）在两个链表无空链表的情况下确定第一个结点，比较链表1和链表2的第一个结点的值，将值小的结点保存下来为合并后的第一个结点。并且把第一个结点为最小的链表向后移动一个元素。
     *       （3）继续在剩下的元素中选择小的值，连接到第一个结点后面，并不断next将值小的结点连接到第一个结点后面，直到某一个链表为空。
     *       （4）当两个链表长度不一致时，也就是比较完成后其中一个链表为空，此时需要把另外一个链表剩下的元素都连接到第一个结点的后面。
     * */
    public Node<Integer> mergeTwoOrderedLists(Node<Integer> head1,Node<Integer> head2){
        Node<Integer> tail=null;//指向新链表的最后一个节点
        Node<Integer> newHead=null;//指向合并后链表第一个节点
        if(head1==null){
            return head2;
        }else if(head2==null){
            return head1;
        }else {
            //确定头指针
            if(head1.getValue()<head2.getValue()){
                newHead=head1;
                head1=head1.getNext();//指向链表的第二个节点
            }else {
                newHead=head2;
                head2=head2.getNext();
            }
            tail=newHead;//指向第一个节点
            while (head1!=null && head2!=null){
                if(head1.getValue()<=head2.getValue()){
                    tail.setNext(head1);
                    head1=head1.getNext();
                }else {
                    tail.setNext(head2);
                    head2=head2.getNext();
                }
                tail=tail.getNext();
            }
            if(head1==null){
                tail.setNext(head2);
            }else if(head2==null){
                tail.setNext(head1);
            }
            return newHead;
        }
    }
    /*
    * 思路2：递归方案
    *   （1）对空链表存在的情况进行处理，假如 pHead1 为空则返回 pHead2 ，pHead2 为空则返回 pHead1。
    *   （2）比较两个链表第一个结点的大小，确定头结点的位置
    *   （3）头结点确定后，继续在剩下的结点中选出下一个结点去链接到第二步选出的结点后面，然后在继续重复（2 ）（3） 步，直到有链表为空。
    * */
    public Node<Integer> mergeTwoOrderedLists2(Node<Integer> head1,Node<Integer> head2){
        Node<Integer> newHead=null;
        if(head1==null){
            return head2;
        }else if(head2==null){
            return head1;
        }else{
            if(head1.getValue()<head2.getValue()){
                newHead=head1;
                newHead.setNext(mergeTwoOrderedLists2(head1.getNext(),head2));
            }else {
                newHead=head2;
                newHead.setNext(mergeTwoOrderedLists2(head1,head2.getNext()));
            }
            return newHead;
        }
    }

    /*
     * 题目5：删除链表中的节点，要求时间复杂度为O(1)
     * 问题： 给定一个单链表中的表头和一个等待被删除的节点。请在 O(1) 时间复杂度删除该链表节点。并在删除该节点后，返回表头。
     * 思路：
     *       在之前介绍的单链表删除节点中，最普通的方法就是遍历链表，复杂度为O(n)。
     *       如果我们把删除节点的下一个结点的值赋值给要删除的结点，然后删除这个结点，这相当于删除了需要删除的那个结点。
     *       因为我们很容易获取到删除节点的下一个节点，所以复杂度只需要O(1)。
     * */
    public Node<Integer> deleteNode(Node<Integer> head,Node<Integer> delNode){
        if(delNode==null) return null;
        if(delNode.getNext()!=null){
            Node<Integer> next=delNode.getNext();
            //下一个节点的值赋值给待删除元素
            delNode.setValue(next.getValue());
            delNode.setNext(next.getNext());
            //删除待删除节点的下一个节点
            next=null;
        }else if(head==delNode){//删除的是头结点
            head=head.getNext();
            delNode=null;
        }else {//删除的是尾结点
            Node<Integer> node=head;
            while (node.getNext()!=delNode){
                node=node.getNext();
            }
            node.setNext(null);
            delNode=null;
        }
        return head;
    }

    /*
     * 题目6：从尾到头打印链表
     * 问题： 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     * */
    public List<Integer> printListFromTailToHead(Node<Integer> head){
        ArrayList<Integer> value=new ArrayList<Integer>();
        Node<Integer> p=head;
        Stack<Integer> stack=new Stack<Integer>();
        while (head!=null){
            stack.push(head.getValue());
            head=head.getNext();
        }
        while(!stack.isEmpty()){
            value.add(stack.peek());
            stack.pop();
        }
        return value;
    }

    /*
     * 题目7：反转链表
     * 问题： 给定一个单链表的表头，将该链表节点反转输出。
     * 思路：
     *       设置三个节点pre、cur、next
     *      （1）每次查看cur节点是否为NULL，如果是，则结束循环，获得结果
     *      （2）如果cur节点不是为NULL，则先设置临时变量next为cur的下一个节点
     *      （3）让cur的下一个节点变成指向pre，而后pre移动cur，cur移动到next
     *      （4）重复（1）（2）（3）
     * */
    public Node<Integer> reverseLinkedList(Node<Integer> head) {
        Node<Integer> pre=null;
        Node<Integer> cur=head;
        while (cur!=null){
            Node<Integer> next=cur.getNext();
            cur.setNext(pre);
            cur=next;
        }
        return pre;
    }

    public static void main(String[] args) {
        linkedListAL la=new linkedListAL();
        int[] array={1,2,7};
        int[] array2={4,5,6};
        Linked<Integer> list=new Linked<Integer>();
        Linked<Integer> list2=new Linked<Integer>();
        for(int a:array){
            list.addLast(a);
        }
        for(int a:array2){
            list2.addLast(a);
        }
        List<Integer> result=la.printListFromTailToHead(list.getHead());
        Iterator i=result.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
        /*Node<Integer> node=la.numberAddAsList(list.getHead(),list2.getHead());
        StringBuilder str = new StringBuilder();
        while (node!=null){
            str.append(node.getValue());
            str.append("-->");
            node=node.getNext();
        }
        str.append("NULL");
        System.out.println(str);*/
        //System.out.println(la.searchNodeK2(list.getHead(),2).getValue());
    }

}
