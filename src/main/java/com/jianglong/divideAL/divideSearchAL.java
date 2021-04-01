package com.jianglong.divideAL;

/*二分搜索算法*/
/*后面给出了二分查找模板*/
public class divideSearchAL {

    /*搜索一个数，如果存在，即返回索引，否则返回-1*/
    public int binarySearch(int[] nums,int target){
        if(nums==null||nums.length==0) return -1;
        int left=0,right=nums.length-1;

        //注意此处为什么是<=而不是<，因为<=不存在为空的搜索区间，而<会存在如[2,2]的非空搜索区间
        while(left<=right){
            int mid=(left+right)/2;
            if(nums[mid]==target) return mid;
            else if(nums[mid]<target) left=mid+1;
            else if(nums[mid]>target) right=mid-1;
        }
        return -1;
    }

    /*寻找左侧边界的二分搜索*/
    /*寻找左侧边界的实质在于搜索数组中小于target值的元素个数*/
    public int binarySearch_leftBound(int[] nums,int target){
        if(nums==null||nums.length==0) return -1;
        int left=0,right=nums.length;

        while(left<right){
            int mid=(left+right)/2;
            if(nums[mid]==target) right=mid;
            else if(nums[mid]>target) right=mid;
            else if(nums[mid]<target) left=mid+1;
        }
        return left;
    }

    /*寻找右侧边界的二分搜索*/
    /*寻找右侧边界的实质在于搜索数组中大于target值的元素个数*/
    public int binarySearch_rightBound(int[] nums,int target){
        if(nums==null||nums.length==0) return -1;
        int left=0,right=nums.length;

        while(left<right){
            int mid=(left+right)/2;
            if(nums[mid]==target) left=mid+1;
            else if(nums[mid]>target) right=mid;
            else if(nums[mid]<target) left=mid+1;
        }
        if(left==0) return -1;
        return nums[left-1]==target?(left-1):-1;
    }

    /*
    * 二分查找模板：
    *   int start=0,end=nums.length-1;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(...) start=mid;
            else end=mid;
        }
        if(nums[start]==target) result=start;
        else if(nums[end]==target) result=end;
    * */
    /*
    * 问题：在排序数组中查找元素的第一个和最后一个位置。
    * 思路：给定一个元素，要找其最先出现的 index，还要找其最后出现的 index。我们只需要用两次二分查找，一次找前，一次找后。
    * */
    public int[] searchRange(int[] nums,int target){
        int[] result=new int[]{-1,-1};
        if(nums==null||nums.length==0) return result;

        //查找元素的第一个位置
        int start=0,end=nums.length-1;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(nums[mid]>=target) end=mid;
            else start=mid;
        }
        if(nums[start]==target) result[0]=start;
        else if(nums[end]==target) result[0]=end;

        //查找元素的最后一个位置
        start=0;end=nums.length-1;
        while (start+1<end){
            int mid=start+(end-start)/2;
            if(nums[mid]<=target) start=mid;
            else end=mid;
        }
        if(nums[end]==target) result[1]=end;
        else if(nums[start]==target) result[1]=start;

        return result;
    }

    public static void main(String[] args) {
        int[] array={1,4,4,8,9};
        divideSearchAL da=new divideSearchAL();
        System.out.println(da.binarySearch_rightBound(array,4));
    }
}
