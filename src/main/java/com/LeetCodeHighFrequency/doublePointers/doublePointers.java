package com.LeetCodeHighFrequency.doublePointers;
import static com.jianglong.linearListAL.linearDelDuplicatesAL.Node;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//双指针类问题
public class doublePointers {

    //题目描述1：在有序数组中找出两个数，使它们的和为 target。
    /*
    * 使用双指针，一个指针指向值较小的元素，一个指针指向值较大的元素。指向较小元素的指针从头向尾遍历，指向较大元素的指针从尾向头遍历。
        如果两个指针指向元素的和 sum == target，那么得到要求的结果；
        如果 sum > target，移动较大的元素，使 sum 变小一些；
        如果 sum < target，移动较小的元素，使 sum 变大一些。
        数组中的元素最多遍历一次，时间复杂度为 O(N)。只使用了两个额外变量，空间复杂度为 O(1)。
    * */
    public int[] twoSum(int[] arr,int target){
        if(arr==null||arr.length<2) return null;
        int[] result=new int[2];
        int low=0,high=arr.length-1;
        while(low<high){
            if(arr[low]+arr[high]==target){
                result[0]=arr[low];
                result[1]=arr[high];
                return result;
            }else if(arr[low]+arr[high]<target){
                low++;
            }else {
                high--;
            }
        }
        return null;
    }

    //题目描述2：判断一个非负整数是否为两个整数的平方和。
    /*
    * 可以看成是在元素为 0~target 的有序数组中查找两个数，使得这两个数的平方和为 target，如果能找到，则返回 true，表示 target 是两个整数的平方和。
    * 设右指针为 x，左指针固定为 0，为了使 02 + x2 的值尽可能接近 target，我们可以将 x 取为 sqrt(target)。
    * */
    public boolean isSquareSum(int target){
        if(target<0) return false;
        int low=0,high=(int)Math.sqrt(target);
        while (low<=high){//注意此处允许两个整数是相等的
            int sum=low*low+high*high;
            if(sum==target){
                return true;
            }else if(sum>target){
                high--;
            }else {
                low++;
            }
        }
        return false;
    }

    //题目描述3：反转字符串中的元音字符。
    /*
    * 使用双指针，一个指针从头向尾遍历，一个指针从尾到头遍历，当两个指针都遍历到元音字符时，交换这两个元音字符。
      为了快速判断一个字符是不是元音字符，我们将全部元音字符添加到集合 HashSet 中，从而以 O(1) 的时间复杂度进行该操作。
        时间复杂度为 O(N)：只需要遍历所有元素一次
        空间复杂度 O(1)：只需要使用两个额外变量
    * */
    public String reverseVowels(String s){
        if(s==null) return null;
        final HashSet<Character> vowels=new HashSet<Character>(
                Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        );
        int low=0,high=s.length()-1;
        char[] result=new char[s.length()];
        while (low<=high){
            if(!vowels.contains(s.charAt(low))){
                result[low]=s.charAt(low);
                low++;
            }else if(!vowels.contains(s.charAt(high))){
                result[high]=s.charAt(high);
                high--;
            }else {
                result[low]=s.charAt(high);
                result[high]=s.charAt(low);
                low++;
                high--;
            }
        }
        return new String(result);
    }

    //题目描述4：可以删除一个字符，判断是否能构成回文字符串。
    /*
    * 本题的关键是处理删除一个字符。在使用双指针遍历字符串时，如果出现两个指针指向的字符不相等的情况，我们就试着删除一个字符，再判断删除完之后的字符串是否是回文字符串。
        在判断是否为回文字符串时，我们不需要判断整个字符串，因为左指针左边和右指针右边的字符之前已经判断过具有对称性质，所以只需要判断中间的子字符串即可。
        在试着删除字符时，我们既可以删除左指针指向的字符，也可以删除右指针指向的字符。
    * */
    public boolean validPalindrome(String s){
        if(s==null||s.length()<1) return false;
        for (int i = 0,j=s.length()-1; i < j; i++,j--) {
            if(s.charAt(i)!=s.charAt(j)){
                return isPalindrome(s,i,j+1)||isPalindrome(s,i+1,j);
            }
        }
        return true;
    }
    private boolean isPalindrome(String s,int i,int j){
        while (i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //题目描述5：归并两个有序数组，把归并结果存到第一个数组上。
    /*
    * Input:
        nums1 = [1,2,3,0,0,0], m = 3
        nums2 = [2,5,6],       n = 3
      Output: [1,2,2,3,5,6]
    * */
    /*
    * 本题的关键是处理删除一个字符。在使用双指针遍历字符串时，如果出现两个指针指向的字符不相等的情况，我们就试着删除一个字符，再判断删除完之后的字符串是否是回文字符串。
        在判断是否为回文字符串时，我们不需要判断整个字符串，因为左指针左边和右指针右边的字符之前已经判断过具有对称性质，所以只需要判断中间的子字符串即可。
        在试着删除字符时，我们既可以删除左指针指向的字符，也可以删除右指针指向的字符。
    * */
    public int[] mergeSortedArray(int[] arr1,int m,int[] arr2,int n){
        if(arr2.length==0) return arr1;
        int i = m-1,j = n-1;
        int indexMerge = m+n-1;
        while (i>=0 || j>=0){
            if(i<0){
                arr1[indexMerge]=arr2[j];
                indexMerge--;
                j--;
            }else if(j<0){
                arr1[indexMerge]=arr1[i];
                indexMerge--;
                i--;
            }else if(arr1[i]>arr2[j]){
                arr1[indexMerge]=arr1[i];
                indexMerge--;
                i--;
            }else {
                arr1[indexMerge]=arr2[j];
                indexMerge--;
                j--;
            }
        }
        return arr1;
    }

    //题目描述6：判断链表是否存在环。
    /*
    * 使用双指针，一个指针每次移动一个节点，一个指针每次移动两个节点，如果存在环，那么这两个指针一定会相遇。
    * */
    public boolean hasCircle(Node head){
        if(head==null || head.getNext()==null) return false;
        Node l1=head,l2=head.getNext();
        while (l1!=null && l2!=null && l2.getNext()!=null){
            if(l1==l2) return true;
            l1=l1.getNext();
            l2=l2.getNext().getNext();
        }
        return false;
    }

    public static void main(String[] args) {
        doublePointers dp=new doublePointers();
        int[] nums1={1,2,3,0,0,0},nums2={2,5,6};
        int m=3,n=3;
        System.out.println(Arrays.toString(dp.mergeSortedArray(nums1,m,nums2,n)));
    }

}
