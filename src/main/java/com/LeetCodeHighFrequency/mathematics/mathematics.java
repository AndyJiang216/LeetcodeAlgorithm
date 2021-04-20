package com.LeetCodeHighFrequency.mathematics;

import java.util.Arrays;

//数学类相关问题计算算法
public class mathematics {

    /*
    * 知识1：素数分解
    * 每一个数都可以分解成素数的乘积，例如 84 = 22 * 31 * 50 * 71 * 110 * 130 * 170 * …
    * */

   /*
   * 知识2：整除
   * 令 x = 2m0 * 3m1 * 5m2 * 7m3 * 11m4 * …
     令 y = 2n0 * 3n1 * 5n2 * 7n3 * 11n4 * …
     如果 x 整除 y（y mod x == 0），则对于所有 i，mi <= ni。
   * */

   /*
   * 知识3：最大公约数和最小公倍数
   * x 和 y 的最大公约数为：gcd(x,y) = 2min(m0,n0) * 3min(m1,n1) * 5min(m2,n2) * ...
   * x 和 y 的最小公倍数为：lcm(x,y) = 2max(m0,n0) * 3max(m1,n1) * 5max(m2,n2) * ...
   * */

   /*
   题目1：生成素数序列。统计所有小于非负整数 n 的质数的数量。
          输入：n = 10
          输出：4
          解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
   */
   public int countPrimes(int n) {
       boolean[] isPrime=new boolean[n+1];
       Arrays.fill(isPrime,true);
       int count=0;
       for (int i = 2; i < n; i++) {
           if(isPrime[i]){
               count++;
               for (int j = i*2; j < n; j+=i) {
                   isPrime[j]=false;
               }
           }
       }
       return count;
   }

   //题目2：求两个整数的最大公约数与最小公倍数
    int gcd(int a,int b) {
       return b==0 ? a : gcd(b,a%b);
    }
    int lcm(int a,int b) {
       //最小公倍数为两数的乘积除以最大公约数。
        return a*b/gcd(a,b);
    }

    //题目3：使用位操作和减法求解最大公约数
    //对于 a 和 b 的最大公约数 f(a, b)，有：
    //如果 a 和 b 均为偶数，f(a, b) = 2*f(a/2, b/2);
    //如果 a 是偶数 b 是奇数，f(a, b) = f(a/2, b);
    //如果 b 是偶数 a 是奇数，f(a, b) = f(a, b/2);
    //如果 a 和 b 均为奇数，f(a, b) = f(b, a-b);
    //其中，乘2和除2可以转换为移位操作
    public int gcdByShift(int a,int b) {
       if(a<b) return gcdByShift(b,a);
       if(b==0) return a;
       boolean isAEven=isEven(a),isBEven=isEven(b);
       if(isAEven && isBEven) {
           return 2*gcdByShift(a>>1,b>>1);
       }else if(isAEven && !isBEven) {
           return gcdByShift(a>>1,b);
       }else if(!isAEven && isBEven) {
           return gcdByShift(a,b>>1);
       }else {
           return gcdByShift(b,a-b);
       }
    }
    private boolean isEven(int a){
       return a%2==1;
    }

    public static void main(String[] args) {
        mathematics m=new mathematics();
        System.out.println(m.gcd(5,10));
    }

}
