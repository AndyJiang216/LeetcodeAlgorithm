package com.jianglong.linearListAL;

import java.util.Stack;

/*栈和队列部分算法题*/
public class stackAndQueueAL {

    /*
    * 题目1：有效的括号
    * 问题：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    * 思路：本题使用栈进行解决
    *           遍历输入字符串
                如果当前字符为左半边括号时，则将其压入栈中
                如果遇到右半边括号时，分类讨论：
                1）如栈不为空且为对应的左半边括号，则取出栈顶元素，继续循环
                2）若此时栈为空，则直接返回 false
                3）若不为对应的左半边括号，反之返回 false
    * */
    public boolean isValidBrackets(String str){
        char[] strArray=str.toCharArray();
        Stack<Character> stack=new Stack<Character>();
        for (int i = 0; i < strArray.length; i++) {
            if(strArray[i]=='('||strArray[i]=='['||strArray[i]=='{') stack.push(strArray[i]);
            else if(stack.isEmpty()) return false;
            else if((strArray[i]==')'&&stack.peek()=='(') || (strArray[i]==']'&&stack.peek()=='[') || (strArray[i]=='}'&&stack.peek()=='{')) stack.pop();
            else stack.push(strArray[i]);
        }
        return stack.size()==0;
    }

    /*
    * 题目2：用两个栈实现队列
    * 问题：用两个栈实现队列，完成队列的Push和Pop操作
    * 思路：in 栈用来处理入栈（push）操作，out 栈用来处理出栈（pop）操作。一个元素进入 in 栈之后，出栈的顺序被反转。当元素要出栈时，需要先进入 out 栈，
    *       此时元素出栈顺序再一次被反转，因此出栈顺序就和最开始入栈顺序是相同的，先进入的元素先退出，这就是队列的顺序。
                push元素时，始终是进入栈，pop和peek元素时始终是走出栈。
                pop和peek操作，如果出栈为空，则需要从入栈将所有元素移到出栈，也就是调换顺序，比如开始push的顺序是 3-2-1，1 是最先进入的元素，则到出栈的顺序是 1-2-3，那 pop 操作拿到的就是 1，满足了先进先出的特点。
                pop和peek操作，如果出栈不为空，则不需要从入栈中移到数据到出栈。
    * */
    public class Queue<E> {
        Stack<E> in=new Stack<E>();
        Stack<E> out=new Stack<E>();
        public void push(E node){
            in.push(node);
        }
        public E pop() throws Exception{
            if(out.isEmpty()){
                while (!in.isEmpty()){
                    out.push(in.pop());
                }
            }
            if(out.isEmpty()) throw new Exception("Queue is empty");
            return out.pop();
        }
    }

    /*
    * 题目3：栈的压入、弹出序列
    * 问题：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
    *       例如序列 1，2，3，4，5 是某栈的压入顺序，序列 4，5，3，2，1是该压栈序列对应的一个弹出序列，但4，3，5，1，2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
    * 思路：借用一个辅助的栈，遍历压栈顺序，先讲第一个放入栈中，这里是 1，然后判断栈顶元素是不是出栈顺序的第一个元素，这里是 4，很显然 1≠4 ，所以需要继续压栈，直到相等以后开始出栈。
            出栈一个元素，则将出栈顺序向后移动一位，直到不相等，这样循环等压栈顺序遍历完成，如果辅助栈还不为空，说明弹出序列不是该栈的弹出顺序。
    * */
    public boolean isPopOrder(int[] pushSequence,int[] popSequence){
        int n=pushSequence.length;
        Stack<Integer> stack=new Stack<Integer>();
        for(int pushIndex=0,popIndex=0;pushIndex<n;pushIndex++){
            stack.push(pushSequence[pushIndex]);
            //如果当前栈顶元素等于弹出序列对应索引位置的值，则弹出序列后移且栈弹出元素
            while (popIndex<n&&!stack.isEmpty()&&stack.peek()==popSequence[popIndex]){
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    /*
    * 题目4：包含min函数的栈
    * 问题：定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数
    * 思路：使用两个 stack，一个作为数据栈，另一个作为辅助栈。其中 数据栈 用于存储所有数据，而 辅助栈 用于存储最小值。
          举个例子：
            入栈的时候：首先往空的数据栈里压入数字 3 ，此时 3 是最小值，所以把最小值压入辅助栈。接下来往数据栈里压入数字 4 。由于 4 大于之前的最小值，因此只要入数据栈，不需要压入辅助栈。
            出栈的时候：当数据栈和辅助栈的栈顶元素相同的时候，辅助栈的栈顶元素出栈。否则，数据栈的栈顶元素出栈。
            获得栈顶元素的时候：直接返回数据栈的栈顶元素。
            栈最小元素：直接返回辅助栈的栈顶元素。
    * */
    public class miniStack {
        private Stack<Integer> dataStack=new Stack<Integer>();
        private Stack<Integer> minStack=new Stack<Integer>();
        public void push(Integer node){
            dataStack.push(node);
            minStack.push(minStack.isEmpty()?node:Math.min(minStack.peek(),node));
        }
        public void pop(){
            if(dataStack.peek()==minStack.peek()){
                dataStack.pop();
                minStack.pop();
            }else {
                dataStack.pop();
            }
        }
        public int peek(){
            return dataStack.peek();
        }
        public int min(){
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        String str="{]";
        stackAndQueueAL stackAndQueueAL=new stackAndQueueAL();
        System.out.println(stackAndQueueAL.isValidBrackets(str));
    }
}
