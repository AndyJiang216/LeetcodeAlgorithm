package com.LeetCodeHighFrequency.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//贪心算法
public class greedy {

    //题目描述1：分配饼干。每个孩子都有一个满足度 grid，每个饼干都有一个大小 size，只有饼干的大小大于等于一个孩子的满足度，该孩子才会获得满足。求解最多可以获得满足的孩子数量。
    /*
     * 给一个孩子的饼干应当尽量小并且又能满足该孩子，这样大饼干才能拿来给满足度比较大的孩子。
       因为满足度最小的孩子最容易得到满足，所以先满足满足度最小的孩子。
     * */
    public int assignCookies(int[] grid,int[] size) {
        if(grid==null || size==null) return 0;
        //对满足度和饼干大小进行升序排序
        Arrays.sort(grid);
        Arrays.sort(size);
        int gi=0,si=0;
        while (gi<grid.length && si<size.length) {
            //从小到大遍历size，如果当前size能满足，则遍历到下一个孩子
            if(grid[gi] <= size[si]) {
                gi++;
            }
            si++;
        }
        return gi;
    }

    //题目描述2：不重叠区间个数。计算让一组区间不重叠所需要移除的区间个数。
    /*
     * 先计算最多能组成的不重叠区间个数，然后用区间总个数减去不重叠区间的个数。
       在每次选择中，区间的结尾最为重要，选择的区间结尾越小，留给后面的区间的空间越大，那么后面能够选择的区间个数也就越大。
       按区间的结尾进行排序，每次选择结尾最小，并且和前一个区间不重叠的区间。
     * */
    public int eraseOverlapIntervals(int[][] intervals){
        if(intervals.length==0) return 0;
        //按每个区间结尾的大小对二维数组进行排序
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] o1,int[] o2) {
                return (o1[1] < o2[1])?-1:((o1[1] == o2[1])?0:1);
            }
        });
        int count=1;
        //初始区间结尾为最左侧区间的结尾
        int end=intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            //如果当前区间左边界不小于end，则两个区间不重叠，count加一
            if(intervals[i][0]>=end){
                end=intervals[i][1];
                count++;
            }
        }
        return intervals.length-count;
    }

    //题目描述3：投飞镖刺破气球。气球在一个水平数轴上摆放，可以重叠，飞镖垂直投向坐标轴，使得路径上的气球都被刺破。求解最小的投飞镖次数使所有气球都被刺破。
    /*
     * 该题目思路与题目2相同，也是计算不重叠区间的个数，但区别在于对于该问题，[1,2]和[2,3]算是重叠区间。
     * */
    public int minDarts(int[][] balloons){
        if(balloons.length==0) return 0;
        Arrays.sort(balloons, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return (o1[1] < o2[1])?-1:((o1[1] == o2[1])?0:1);
            }
        });
        int count=1;
        //初始区间结尾为最左侧区间的结尾
        int end=balloons[0][1];
        for (int i = 1; i < balloons.length; i++) {
            //如果当前区间左边界不小于end，则两个区间不重叠，count加一
            if(balloons[i][0]>end){
                end=balloons[i][1];
                count++;
            }
        }
        return balloons.length-count;
    }

    //题目描述4：根据身高和序号重组队列。一个学生用两个分量 (h, k) 描述，h 表示身高，k 表示排在前面的有 k 个学生的身高比他高或者和他一样高。
    /*
    * Input:
        [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
      Output:
        [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
    * */
    /*
     * 为了使插入操作不影响后续的操作，身高较高的学生应该先做插入操作，否则身高较小的学生原先正确插入的第 k 个位置可能会变成第 k+1 个位置。
       身高 h 降序、个数 k 值升序，然后将某个学生插入队列的第 k 个位置中。
     * */
    public int[][] reconstructQueue(int[][] students){
        if(students==null || students.length==0 || students[0].length==0) return new int[0][0];
        Arrays.sort(students, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }else {
                    return o2[0]-o1[0];
                }
            }
        });
        List<int[]> queue=new ArrayList<int[]>();
        for(int[] s:students){
            queue.add(s[1],s);
        }
        return queue.toArray(new int[queue.size()][]);
    }

    //题目描述5：买卖股票的最大收益。可以进行多次交易，多次交易之间不能交叉进行，可以进行多次交易。
    /*
     * 对于 [a, b, c, d]，如果有 a <= b <= c <= d ，那么最大收益为 d - a。而 d - a = (d - c) + (c - b) + (b - a) ，
     * 因此当访问到一个 prices[i] 且 prices[i] - prices[i-1] > 0，那么就把 prices[i] - prices[i-1] 添加到收益中。
     * */
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length<=1) return 0;
        int profit=0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i]>prices[i-1]){
                profit+=prices[i]-prices[i-1];
            }
        }
        return profit;
    }

    //题目描述6：种植花朵。flowerbed 数组中 1 表示已经种下了花朵。花朵之间至少需要一个单位的间隔，求解是否能种下 n 朵花。
    /*
     * 数组从0开始进行判定，分别对每一个位置的数组元素的前置位和后置位元素进行判定，当两个位置均为0时，可以插入。
     * 注意首位的末尾的前置位与后置位的特殊情况即可。
     * */
    public boolean isAblePlaceFlowers(int[] flowerbed,int n) {
        int count=0;
        int len=flowerbed.length;
        for (int i = 0; i < len && count < n; i++) {
            if(flowerbed[i]==1) continue;
            //注意此处的代码简洁优雅写法：分别对i为数组首位与数组末尾的情况前置与后置位置进行定义
            int pre = i==0 ? flowerbed[len-1] : flowerbed[i-1];
            int next = i==len-1 ? flowerbed[0] : flowerbed[i+1];
            if(pre==0 && next==0) {
                count++;
                flowerbed[i]=1;
            }
        }
        return count>=n;
    }

    //题目描述7：判断是否为子序列。
    //s = "abc", t = "ahbgdc" Return true.
    /*
     * 注意只要是保持相对顺序地存在于字符串中即可认为是子序列
     * */
    public boolean isSubsequence(String s,String t) {
        if(t==null || t.length()==0) return false;
        int index=-1;
        for(char a : s.toCharArray()){
            //注意每次循环都是在上一个字符确定的位置之后开始遍历，故保持了字符间的相对顺序
            index=t.indexOf(a,index);
            if(index==-1) return false;
        }
        return true;
    }

    //题目描述8：修改一个数成为非递减数组。
    /*
     * 在出现 nums[i] < nums[i - 1] 时，需要考虑的是应该修改数组的哪个数，使得本次修改能使 i 之前的数组成为非递减数组，并且 不影响后续的操作。
     * 优先考虑令 nums[i - 1] = nums[i]，因为如果修改 nums[i] = nums[i - 1] 的话，那么 nums[i] 这个数会变大，就有可能比 nums[i + 1] 大，从而影响了后续操作。
     * 还有一个比较特别的情况就是 nums[i] < nums[i - 2]，修改 nums[i - 1] = nums[i] 不能使数组成为非递减数组，只能修改 nums[i] = nums[i - 1]。
     * */
    public boolean changeOneNum(int[] nums) {
        if(nums==null || nums.length<3) return true;
        int count=0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]>=nums[i-1]) continue;
            if(count>1) return false;
            count++;
            if(i-2>=0 && nums[i-2]>nums[i]){
                nums[i]=nums[i-1];
            }else {
                nums[i-1]=nums[i];
            }
        }
        return count<=1;
    }

    //题目描述9：子数组最大的和。
    //For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
    //the contiguous subarray [4,-1,2,1] has the largest sum = 6.
    /*
     * 此题我使用了动态规划算法，注意对于每个数组元素来说，以该元素为结尾的子数组的和有两种情况：
     * 1.i-1的最大值小于0，则只能以自己开头自己结尾
     * 2.i-1的最大值大于等于0，则加上i-1最大值求和
     * */
    public int maxSubArray(int[] array) {
        if(array==null || array.length<1) return 0;
        int[] dp=new int[array.length];
        dp[0]=array[0];
        int max=dp[0];
        for (int i = 1; i < array.length; i++) {
            if(dp[i-1]<0){
                dp[i]=array[i];
            }else {
                dp[i]=dp[i-1]+array[i];
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }



    public static void main(String[] args) {
        greedy greedy=new greedy();
        int[] array={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(greedy.maxSubArray(array));
    }

}
