package com.jianglong.linearListAL;
import static com.jianglong.linearListAL.linearDelDuplicatesAL.Linked;
import static com.jianglong.linearListAL.linearDelDuplicatesAL.Node;
public class kLinkedListMerge {

    /*
    * 题目：合并k个排序链表，返回合并后的排序链表
    * 思路：类似归并排序原理，采用递归、分治的思想
    *       1、先一分为二，分别“递归地”解决了与原问题同结构，但规模更小的两个子问题；
            2、再考虑如何合并，这个合并的过程也是一个递归方法。
    * 时间复杂度：O(Nlogk)，这里 N 是这 k 个链表的结点总数，k 个链表二分是对数级别的。
    * 空间复杂度：O(1)，合并两个排序链表需要“穿针引线”的指针数是常数个的。
    * */
    public Node<Integer> mergeKLists(Node<Integer>[] linkedLists){
        int len=linkedLists.length;
        if(len==0) return null;
        return mergeKLists(linkedLists,0,len-1);
    }
    public Node<Integer> mergeKLists(Node<Integer>[] linkedLists,int l,int r){
        if(l==r) return linkedLists[l];
        int mid=(r-l)/2+l;
        Node l1=mergeKLists(linkedLists,l,mid);
        Node l2=mergeKLists(linkedLists,mid+1,r);
        return mergeTwoSortedListNode(l1,l2);
    }
    private Node<Integer> mergeTwoSortedListNode(Node<Integer> n1,Node<Integer> n2){
        if(n1==null) return n2;
        if(n2==null) return n1;
        if(n1.getValue()<n2.getValue()){
            n1.setNext(mergeTwoSortedListNode(n1.getNext(),n2));
            return n1;
        }else {
            n2.setNext(mergeTwoSortedListNode(n1,n2.getNext()));
            return n2;
        }
    }
}
