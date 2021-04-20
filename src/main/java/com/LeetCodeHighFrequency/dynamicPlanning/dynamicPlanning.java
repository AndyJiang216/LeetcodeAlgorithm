package com.LeetCodeHighFrequency.dynamicPlanning;

import java.util.Arrays;

//动态规划算法
public class dynamicPlanning {

    //题目1：数组中等差递增数列的个数
    /*
    * A = [0, 1, 2, 3, 4]
      return: 6, for 3 arithmetic slices in A:
      [0, 1, 2],[1, 2, 3],[0, 1, 2, 3],[0, 1, 2, 3, 4],[ 1, 2, 3, 4],[2, 3, 4]
    * */
    /*
    * dp[i]表示以A[i]为结尾的等差数列递增子区间的个数。
    * 当 A[i] - A[i-1] == A[i-1] - A[i-2]，那么 [A[i-2], A[i-1], A[i]] 构成一个等差递增子区间。而且在以 A[i-1] 为结尾的递增子区间的后面再加上一个 A[i]，一样可以构成新的递增子区间。
    * 综上，在 A[i] - A[i-1] == A[i-1] - A[i-2] 时，dp[i] = dp[i-1] + 1
    * */
    public int numberOfArithmeticsSlices(int[] A) {
        if (A == null || A.length <= 2) return 0;
        int n = A.length;
        int[] dp = new int[n];
        for (int i = 2; i < n; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        int count = 0;
        for (int a : dp) {
            count += a;
        }
        return count;
    }

    //题目2：整数拆分。给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
    /*
    * given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
    * */
    /*
     * dp[i]表示将正整数i拆分成至少两个正整数的和之后，这些正整数的最大乘积。由于0和1都不能拆分，因此dp[0]=dp[1]=0。
     * 当i>=2时，假设对正整数i拆分出的第一个正整数是j（1<=j<i），则有以下两种方案：
        将i拆分成i和 i-j的和，且i-j不再拆分成多个正整数，此时的乘积是j×(i−j)；
        将i拆分成j和 i-j的和，且 i-j继续拆分成多个正整数，此时的乘积是j×dp[i−j]。
        因此，当j固定时，有dp[i]=max(j×(i−j),j×dp[i−j])。
     * */
    public int integerSlice(int n){
        if(n<=1) return 0;
        int[] dp=new int[n];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i-1; j++) {
                dp[i]=Math.max(dp[i],Math.max(j*(i-j),j*dp[i-j]));
            }
        }
        return dp[n];
    }

    //题目3：最长数对链。给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
    /*
     * given [[1,2], [2,3], [3,4]], return 2，最长的数对链是 [1,2] -> [3,4]
     * */
    /*
     * dp[i]表示以第i个数对为结尾的数对链的长度，对于j(0<=j<=i)，如果pairs[j]<pairs[i]，则dp[i]=max(dp[i],dp[j]+1)
     * */
    public int findLongestChain(int[][] pairs){
        if(pairs==null||pairs.length<1) return 0;
        int n=pairs.length;
        int[] dp=new int[n];
        Arrays.fill(dp,1);
        int maxLength=1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(pairs[j][1]<pairs[i][0]) dp[i]=Math.max(dp[i],dp[j]+1);
            }
            maxLength=Math.max(maxLength,dp[i]);
        }
        return maxLength;
    }

    //题目4：摆动序列。如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
    /*
     * given [1,7,4,9,2,5], return 6，整个序列均为摆动序列。
     * given [1,17,5,10,13,15,10,5,16,8], return 7，这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
     * */
    /*
     * dp[i]表示以第i个数对为结尾的最长摆动序列的长度。定义一个 sub 数组，用来记录 nums 数组中每两个相邻元素的差值。
     * 若 sub[i] < 0 && sub[j] > 0 或 sub[i] > 0 && sub[j] < 0 有一种情况成立，则此时 nums[i] 和 nums[i - 1]放到 nums[j] 和 nums[j - 1] 的后面可以形成摆动序列再加上前 j 个元素的摆动序列的长度，此时状态转移方程：dp[i] = dp[j] + 1
     * 若该条件不成立，则此时 nums[i] 和 nums[i - 1]放到 nums[j] 和 nums[j - 1] 的后面无法形成摆动序列，直接跳过即可，此时状态转移方程：dp[i] = dp[i]。
     * 两种情况，最终取长度更长的结果。
     * */
    public int wiggleMaxLength(int[] nums) {
        if(nums==null||nums.length<1) return 0;
        int n=nums.length;
        int[] sub=new int[n];
        for (int i = 1; i < n; i++) {
            sub[i]=nums[i]-nums[i-1];
        }

        int maxLength=0;

        int[] dp=new int[n];
        dp[0]=1;
        for (int i = 1; i < n; i++) {
            if(sub[i]==0) dp[i]=1;
            else dp[i]=2;
            for (int j = 0; j < i; j++) {
                if((sub[i]<0&&sub[j]>0)||(sub[i]>0&&sub[j]<0)){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            maxLength=Math.max(maxLength,dp[i]);
        }
        return maxLength;
    }

    //题目5：最长公共子序列。给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
    /*
     * given text1 = "abcde", text2 = "ace" , return 3，最长公共子序列是 "ace" ，它的长度为 3 。
     * given text1 = "abc", text2 = "def", return 0，两个字符串没有公共子序列，返回 0 。
     * */
    /*
     * 首先，区分两个概念：子序列可以是不连续的；子数组（子字符串）需要是连续的；
     * 另外，动态规划也是有套路的：单个数组或者字符串要用动态规划时，可以把动态规划 dp[i] 定义为 nums[0:i] 中想要求的结果；
     * 当两个数组或者字符串要用动态规划时，可以把动态规划定义成两维的 dp[i][j] ，其含义是在 A[0:i] 与 B[0:j] 之间匹配得到的想要的结果。
     * 1.状态定义：比如对于本题而言，可以定义 dp[i][j] 表示 text1[0:i-1] 和 text2[0:j-1] 的最长公共子序列。 （注：text1[0:i-1] 表示的是 text1 的 第 0 个元素到第 i - 1 个元素，两端都包含）
     *      之所以 dp[i][j] 的定义不是 text1[0:i] 和 text2[0:j] ，是为了方便当 i = 0 或者 j = 0 的时候，dp[i][j]表示的为空字符串和另外一个字符串的匹配，这样 dp[i][j] 可以初始化为 0.
     * 2.状态转移方程：当 text1[i - 1] == text2[j - 1] 时，说明两个子字符串的最后一位相等，所以最长公共子序列又增加了 1，所以 dp[i][j] = dp[i - 1][j - 1] + 1；举个例子，比如对于 ac 和 bc 而言，他们的最长公共子序列的长度等于 a 和 b 的最长公共子序列长度 0 + 1 = 1。
            当 text1[i - 1] != text2[j - 1] 时，说明两个子字符串的最后一位不相等，那么此时的状态 dp[i][j] 应该是 dp[i - 1][j] 和 dp[i][j - 1] 的最大值。举个例子，比如对于 ace 和 bc 而言，他们的最长公共子序列的长度等于 ① ace 和 b 的最长公共子序列长度0 与 ② ac 和 bc 的最长公共子序列长度1 的最大值，即 1。
         综上状态转移方程为：
            dp[i][j]=dp[i-1][j-1]+1，当text1[i-1]==text2[j-1];
            dp[i][j]=max(dp[i-1][j],dp[i][j-1])，当text1[i-1]!=text2[j-1]。
       3.状态的初始化：初始化就是要看i=0与j=0时，dp[i][j]应该取值为多少。
            当i=0时，dp[0][j]表示的是text1中取空字符串跟text2的最长公共子序列，结果肯定为0.
            当j=0时，dp[i][0]表示的是text2中取空字符串跟text1的最长公共子序列，结果肯定为0.
       综上，当i=0或者j=0时，dp[i][j]初始化为0.
     * */
    public int longestCommonSubsequence(String text1,String text2){
        int M=text1.length();
        int N=text2.length();
        int[][] dp=new int[M+1][N+1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[M][N];
    }

    //题目6：0-1背包问题。有一个容量为N的背包，要用这个背包装下最大价值的物品，这些物品有两个属性：体积w和价值v。
    /*
    * 1.状态定义：定义一个二维数组dp存储最大价值，其中dp[i][j]表示前i件物品体积不超过j的情况下能达到的最大价值。
    * 2.状态转移：设第i件物品的体积为w，价值为v，根据第i件物品是否添加到背包中，可以分两种情况讨论：
    *       a.第i件物品没有添加到背包，总体积不超过j的前i件物品的最大价值就是总体积不超过j的前i-1件物品的最大价值，dp[i][j]=dp[i-1][j]。
    *       b.第i件物品添加到背包中，则前i-1件物品在背包中占用的空间变为j-w，则有dp[i][j]=dp[i][j-w]+v。
    *   第i件物品可以添加也可以不添加，这取决于哪种情况下最大价值更大。因此，0-1背包的状态转移方程为：
    *   dp[i][j]=max(dp[i-1][j],dp[i][j-w]+v)
    * */
    //W:背包总体积，N:物品数量
    public int maxValueBackpack(int W,int N,int[] weights,int[] values){
        int[][] dp=new int[N+1][W+1];
        for (int i = 1; i <= N; i++) {
            int w=weights[i-1],v=values[i-1];
            for (int j = 1; j <= W; j++) {
                if(j>=w){
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-w]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[N][W];
    }
    //题目7：划分数组为和相等的两部分。
    /*
    * Input: [1, 5, 11, 5]，Output: true.Explanation: The array can be partitioned as [1, 5, 5] and [11].
    * */
    /*
    * 本题可以看成是背包问题的一个变种，即背包大小为sum/2的0-1背包问题。
    * dp[i][j]为第i个元素
    * */
    public boolean canPartition(int[] nums){
        int sum=0;
        for(int a:nums){
            sum+=a;
        }
        if(sum%2!=0) return false;
        int target=sum/2;
        int len=nums.length;
        boolean[][] dp=new boolean[len][target+1];
        //先填表格第0行，第一个数只能让容积恰好为自己的背包装满
        if(nums[0]<=target) dp[0][nums[0]]=true;
        //再填表格后面几行
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <=target ; j++) {
                dp[i][j]=dp[i-1][j];
                if(nums[i]==j){
                    dp[i][j]=true;
                    continue;
                }
                if(nums[i]<j){
                    dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[len-1][target];
    }


    public static void main(String[] args) {
        dynamicPlanning dp=new dynamicPlanning();
        int[][] arr=new int[3][2];
        arr[0]=new int[]{1,2};
        arr[1]=new int[]{2,3};
        arr[2]=new int[]{3,4};
        System.out.println(dp.findLongestChain(arr));
    }

}
