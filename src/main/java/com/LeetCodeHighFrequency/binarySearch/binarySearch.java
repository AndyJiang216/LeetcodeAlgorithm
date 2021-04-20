package com.LeetCodeHighFrequency.binarySearch;

import java.util.Arrays;

//二分查找算法问题
//对于二分法查找算法而言，重要的是边界判断及循环条件退出设计
public class binarySearch {

    //题目1：求开方
    /*
    * 一个数 x 的开方 sqrt 一定在 0 ~ x 之间，并且满足 sqrt == x / sqrt。可以利用二分查找在 0 ~ x 之间查找 sqrt。
    * 对于 x = 8，它的开方是 2.82842...，最后应该返回 2 而不是 3。在循环条件为 l <= h 并且循环退出时，h 总是比 l 小 1，也就是说 h = 2，l = 3，因此最后的返回值应该为 h 而不是 l。
    * */
    public int findSqrt(int x) {
        if(x<0) {
            throw new IllegalArgumentException("x不可以为负数");
        }
        if(x<=1) return x;
        int l=0,h=x;
        while (l<=h){//因为数组中不一定存在sqrt的值，且需要向下取整
            int mid=l+(h-l)/2;//这种写法数组不会越界
            if(mid*mid==x) return mid;
            else if(mid*mid<x) l=mid+1;
            else h=mid-1;
        }
        return h;
    }

    //题目2：求大于给定元素的最小元素。给定一个有序的字符数组 letters 和一个字符 target，要求找出 letters 中大于 target 的最小字符，如果找不到就返回第 1 个字符。
    public char findNextGreatestLetter(char[] letters, char target) {
        int l=0,h=letters.length;//因为要判断最终搜索到的数组位置是否越界，因此需要初始化为arr.length
        while (l<h) {
            int mid=l+(h-l)/2;
            if(letters[mid]-target<=0) l=mid+1;
            else h=mid;
        }
        return l==letters.length?letters[0]:letters[l];
    }

    //题目3：有序数组的 Single Element。一个有序数组只有一个数不出现两次，找出这个数。
    /*
    * 令 index 为 Single Element 在数组中的位置。在 index 之后，数组中原来存在的成对状态被改变。如果 m 为偶数，并且 m + 1 < index，那么 nums[m] == nums[m + 1]；m + 1 >= index，那么 nums[m] != nums[m + 1]。
    * 从上面的规律可以知道，如果 nums[m] == nums[m + 1]，那么 index 所在的数组位置为 [m + 2, h]，此时令 l = m + 2；如果 nums[m] != nums[m + 1]，那么 index 所在的数组位置为 [l, m]，此时令 h = m。
    * 因为 h 的赋值表达式为 h = m，那么循环条件也就只能使用 l < h 这种形式。
    * */
    public int singleNoDuplicate(int[] nums) {
        int l=0,h=nums.length-1;
        while (l<h) {
            int mid=l+(h-l)/2;
            if(mid%2==1) mid--;//保证l/mid/h都在偶数位，使得查找区间大小一直都是奇数
            if(nums[mid]==nums[mid+1]){
                l=mid+2;
            }else {
                h=mid;
            }
        }
        return nums[l];
    }

    //题目4：找出旋转数组的最小数字。
    //Input: [3,4,5,1,2],Output: 1
    public int findMin(int[] nums) {
        int l=0,h=nums.length-1;
        while (l<h){
            int mid=l+(h-l)/2;
            if(nums[mid]<=nums[h]){
                h=mid;//此处不是mid-1是因为不确定mid的值是不是子数组的边界
            }else {
                l=mid+1;
            }
        }
        return nums[l];
    }

    //题目5：查找有序数组中目标元素出现的第一个和最后一个位置
    //Input: nums = [5,7,7,8,8,10], target = 8,Output: [3,4]
    //Input: nums = [5,7,7,8,8,10], target = 6,Output: [-1,-1]
    /*
    * 可以用二分查找找出第一个位置和最后一个位置，但是寻找的方法有所不同，需要实现两个二分查找。
    * 我们将寻找 target 最后一个位置，转换成寻找 target+1 第一个位置，再往前移动一个位置。这样我们只需要实现一个二分查找代码即可。
    * */
    public int[] searchRange(int[] nums,int target){
        int first=findFirst(nums,target);
        int last=findFirst(nums,target+1)-1;
        if(first==nums.length || nums[first]!=target){
            return new int[]{-1,-1};
        }else {
            return new int[]{first,last};
        }
    }
    private int findFirst(int[] nums,int target) {
        if(nums.length<1) return -1;
        int l=0,h=nums.length;
        while (l<h){
            int mid=l+(h-l)/2;
            if(nums[mid]>=target) h=mid;
            else l=mid+1;
        }
        return l;
    }

    public static void main(String[] args) {
        binarySearch bs=new binarySearch();
        int[] arr={5,7,7,8,8,10};
        System.out.println(Arrays.toString(bs.searchRange(arr,6)));
    }

}
