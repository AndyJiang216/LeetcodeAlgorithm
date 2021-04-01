package com.jianglong.hashTableAL;

import java.util.*;

/*哈希表相关算法问题*/
public class hashTableAL {

    /*
    * 题目1：两数之和
    * 问题：给定一个整数数组nums和一个目标值target，请你在数组中找出和为目标值的那两个整数，并返回他们的数组下标。
    *       你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
    * 示例：给定 nums = [2, 7, 11, 15], target = 9
    *       因为 nums[0] + nums[1] = 2 + 7 = 9
    *       所以返回 [0, 1]
    * 思路：首先设置一个 map 容器 record 用来记录元素的值与索引，然后遍历数组 nums 。
            每次遍历时使用临时变量 complement 用来保存目标值与当前值的差值
            在此次遍历中查找 record ，查看是否有与 complement 一致的值，如果查找成功则返回查找值的索引值与当前变量的值i
            如果未找到，则在 record 保存该元素与索引值 i
    * */
    public int[] twoSum(int[] nums,int target){
        Hashtable<Integer,Integer> record=new Hashtable<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement=target-nums[i];
            if(record.containsKey(complement)){
                int res[]={record.get(complement),i};
                return res;
            }
            record.put(nums[i],i);
        }
        int[] res={-1,-1};
        return res;
    }

    /*
    * 题目2：无重复字符的最长子串
    * 问题：给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
    * 思路：建立一个 HashMap ，建立每个字符和其最后出现位置之间的映射，然后再定义两个变量 res 和 left ，其中 res 用来记录最长无重复子串的长度，
    *       left 指向该无重复子串左边的起始位置的前一个，一开始由于是前一个，所以在初始化时就是 -1。接下来遍历整个字符串，对于每一个遍历到的字符，
    *       如果该字符已经在 HashMap 中存在了，并且如果其映射值大于 left 的话，那么更新 left 为当前映射值，然后映射值更新为当前坐标 i，
    *       这样保证了 left 始终为当前边界的前一个位置，然后计算窗口长度的时候，直接用 i-left 即可，用来更新结果 res 。
    * */
    public int lengthOfLongestSubstring(String str){
        int res=0,left=-1;
        //创建一个hashtable，key:字符，value:字符下标
        Hashtable<Character,Integer> map=new Hashtable<Character, Integer>();
        //遍历字符串，并将字符串存入hashtable中
        for(int start=0,end=0;end<str.length();end++){
            char ch=str.charAt(end);
            if(map.containsKey(ch)){
                start=Math.max(map.get(ch),start);
            }
            //取较大值
            res=Math.max(res,end-start+1);
            //更新ch对应位置
            map.put(ch,end+1);
        }
        return res;
    }
    /*
    * 思路2：滑动窗口
    * 建立一个 256 位大小的整型数组 freg ，用来建立字符和其出现位置之间的映射。
      维护一个滑动窗口，窗口内的都是没有重复的字符，去尽可能的扩大窗口的大小，窗口不停的向右滑动。
        （1）如果当前遍历到的字符从未出现过，那么直接扩大右边界；
        （2）如果当前遍历到的字符出现过，则缩小窗口（左边索引向右移动），然后继续观察当前遍历到的字符；
        （3）重复（1）（2），直到左边索引无法再移动；
        （4）维护一个结果 res，每次用出现过的窗口大小来更新结果 res ，最后返回 res 获取结果。
    * */
    public int lengthOfLongestSubstring2(String str){
        int[] freq=new int[256];
        int left=0,right=-1;//滑动窗口为str[left...right]
        int res=0;
        //整个循环从left=0,right=-1这个空窗口开始，到left=str.length(),right=str.length()-1这个空窗口截止
        //在每次循环里改变窗口，维护freq，并记录当前窗口中是否找到了一个新的最优值
        while(left<str.length()){
            if(right+1<str.length() && freq[str.charAt(right+1)]==0){
                right++;
                freq[str.charAt(right)]++;
            }else {//right已经到头或者
                freq[str.charAt(left)]--;
                left++;
            }
            res=Math.max(res,right-left+1);
        }
        return res;
    }

    /*
     * 题目3：两个数组的交集
     * 问题：给定两个数组，编写一个函数来计算他们的交集。
     * 思路：容器类 set 的使用。
            遍历 num1，通过 set 容器 record 存储 num1 的元素
            遍历 num2，在 record 中查找是否有相同的元素，如果有，用 set 容器 resultSet 进行存储
            将 resultSet 转换为 vector 类型
     * */
    public int[] intersection(int[] nums1,int[] nums2){
        if(nums1.length==0) return nums1;
        if(nums2.length==0) return nums2;
        //利用Set不可以有重复的元素特性
        Set<Integer> set=new HashSet<Integer>();
        Set<Integer> list=new HashSet<Integer>();
        //先将其中一个数组添加至集合中
        for(int i:nums1){
            set.add(i);
        }
        //遍历第二个数组
        for(int i:nums2){
            if(set.contains(i)){
                list.add(i);
            }
        }
        int[] temp=new int[list.size()];
        int i=0;
        for(int a:list){
            temp[i++]=a;
        }
        return temp;
    }
    /*
     * 题目4：两个数组的交集
     * 问题：给定两个数组，编写一个函数来计算他们的交集。
     * 示例：输入: nums1 = [1,2,2,1], nums2 = [2,2]
             输出: [2,2]
     * 思路：与上题 两个数组的交集 类似。只不过这里使用的是 map 。
             遍历 num1，通过 map 容器 record 存储 num1 的元素与频率；
             遍历 num2 ，在 record 中查找是否有相同的元素（该元素的存储频率大于 0 ），如果有，用 map 容器resultVector 进行存储，同时该元素的频率减一。
     * */
    public int[] intersection2(int[] nums1,int[] nums2){
        if(nums1.length==0) return nums1;
        if(nums2.length==0) return nums2;
        List<Integer> list=new ArrayList<Integer>();
        Map<Integer,Integer> record=new HashMap<Integer, Integer>();
        for(int num:nums1){
            if(record.containsKey(num)) record.put(num,record.get(num)+1);
            else record.put(num,1);
        }
        for(int num:nums2){
            if(record.containsKey(num)&&record.get(num)>0){
                list.add(num);
                record.put(num,record.get(num)-1);
            }
        }
        int[] res=new int[nums2.length];
        Iterator<Integer> iterator=list.iterator();
        int i=0;
        while (iterator.hasNext()){
            res[i]=iterator.next();
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        hashTableAL ha=new hashTableAL();
        int[] nums={2,7,11,15};
        System.out.println(Arrays.toString(ha.twoSum(nums,9)));
        int[] nums1={1,2,2,1};
        int[] nums2={2,2};
        System.out.println(Arrays.toString(ha.intersection2(nums1,nums2)));
    }

}
