package com.jianglong.linearListAL;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/*
* 单调栈是指在栈的后进先出的基础之上额外再添加一个特性：从栈顶到栈底的元素是严格递增（or递减），以下以单调递增栈为例
* 具体进栈过程如下：对于单调递增栈，若当前进栈元素为e，从栈顶开始遍历元素，把小于e或者等于e的元素弹出栈，直到遇到一个大于e的元素
* 或者栈为空为止，然后再把e压入栈中。
* */
public class stackIncreasingly {

    /*
     * 题目：下一个更大的元素
     * 问题：给定两个没有重复元素的数组nums1和nums2，其中nums1是nums2的子集。找到nums1中每个元素在nums2中的下一个比其更大的值。
     *       nums1中数字X的下一个更大元素是指X在nums2中对应位置的右边第一个比X大的元素。如果不存在，对应位置输出-1。
     * 思路：我们可以忽略数组nums1，先对nums2中的每一个元素，求出其下一个更大的元素。随后将这些答案放入哈希映射中，再遍历数组nums1，
     *       并直接找出答案。对于nums2，我们可以使用单调栈来解决这个问题。
     * */
    public int[] nextGreaterElement(int[] nums1,int[] nums2){
        HashMap<Integer,Integer> map=new HashMap<Integer, Integer>();
        Stack<Integer> stack=new Stack<Integer>();
        int[] result=new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            //当栈中元素不为空且当前数组元素大于栈顶元素，则元素出栈并将出栈元素存入哈希表
            while (!stack.isEmpty()&&stack.peek()<nums2[i]){
                map.put(stack.pop(),nums2[i]);
            }
            //满足单调递增栈的元素压入栈
            stack.push(nums2[i]);
        }
        while(!stack.isEmpty()){
            map.put(stack.pop(),-1);//单调递增栈内的元素后面均没有更大的元素
        }
        for (int i = 0; i < nums1.length; i++) {
            result[i]=map.get(nums1[i]);
        }
        return result;
    }

    /*
    * 题目：每日温度
    * 问题：请根据每日气温列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
    *       如果气温在这之后都不会升高，请在该位置用0来代替。
    * 例如：给定一个列表temperatures=[73,74,75,71,69,72,76,73]，你的输出应该是[1,1,4,2,1,1,0,0]
    * 思路：借助单调栈的特性，将数组元素进行循环判断
    * */
    //单调栈，栈空或不满足预期条件（即出现）
    public int[] dailyTemperatures(int[] temperatures){
        int[] result=new int[temperatures.length];
        Stack<Integer> stack=new Stack<Integer>();
        for (int i = 0; i < temperatures.length; i++) {
            //因为题目求解的是相差天数，所以栈内保存的是索引值而不是数组元素值
            while(!stack.empty()&&temperatures[stack.peek()]<temperatures[i]){
                result[stack.peek()]=i-stack.pop();
            }
            stack.push(i);
        }
        //为什么不需要将未赋值数组位置填充为0呢？因为java的数组默认初始化会填充固定值，如整型填充为0，浮点型填充为0.0，char型填充为空格，string填充为null，布尔型填充为false
        return result;
    }

    public static void main(String[] args) {
        /*int[] nums1={4,1,2},nums2={1,3,4,2};
        stackIncreasingly stack=new stackIncreasingly();
        int[] result=stack.nextGreaterElement(nums1,nums2);
        System.out.println(Arrays.toString(result));*/
        int[] temperatures={73,74,75,71,69,72,76,73};
        stackIncreasingly stack=new stackIncreasingly();
        System.out.println(Arrays.toString(stack.dailyTemperatures(temperatures)));
    }

}
