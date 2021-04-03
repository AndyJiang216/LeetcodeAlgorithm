package com.jianglong.dynamicAndGreddyAL;

/*KMP字符串匹配算法*/
public class KMPMatchAL {
    /*
    * KMP匹配算法实际上是借助动态规划思想。简单的暴力匹配算法时间复杂度为O(n*n)，且对于重复字符字符串而言，遍历指针会不断回溯，
    * 即不断从已经遍历过的索引处重新开始遍历匹配。而KMP算法将字符串状态通过给定的待匹配字符串计算出dp数组，对于不同的文本，
    * 利用这个dp数组都能够在O(n)时间完成字符串匹配，避免了遍历指针的回退。
    * 由于dp数组只与待匹配模板字符串pat相关，KMP算法可以这样设计：
    * public class KMP{
        private int[][] dp;
        private String pat;

        public KMP(String pat){
            this.pat=pat;
            //通过pat构建dp数组
            //需要O(M)时间
        }

        public int search(String txt){
            //借助dp数组去匹配txt
            //需要O(N)时间
        }
    }
    * 这样，我们需要用同一pat去匹配不同的txt时，就不需要浪费时间构造dp数组了
    * 这种情况下，二维dp数组的定义及含义如下：
    * dp[j][c]=next
    * 0<= j <M，代表当前的状态,M为模板字符串的长度,j即代表当前已经匹配了j个字符
    * 0<= c <256，代表遇到的字符（ASCII码）
    * 0<= next <=M，代表下一个状态
    *
    * dp[4]['A']=3表示：
    *       当前是状态4，如果遇到字符A，pat应该转移到状态3
    * dp[1]['B']=2表示：
    *       当前是状态1，如果遇到字符B，pat应该转移到状态2
    * 根据dp数组定义和刚才状态转移的过程，我们可以先写出KMP算法的search函数代码
    * public int search(String txt){
        int M=pat.length();
        int N=txt.length();
        //pat的初始状态为0
        int j=0;
        for (int i = 0; i < N; i++) {
            //当前是状态i，遇到字符txt[i]
            //pat应该转移到哪个状态?
            j=dp[j][txt.charAt(i)];
            //如果到达终止状态，则返回匹配开头的索引值
            if(j==M) return i-M+1;
        }
        //如果没有到达终止状态，则匹配失败
        return -1;
    }
    * 状态转移可以通过状态推进和影子状态（和当前状态具有同样前缀的状态）回退进行操作
    * */
    public class KMP{
        private int[][] dp;
        private String pat;

        public KMP(String pat){
            this.pat=pat;
            int M=pat.length();
            //dp[状态][字符]=下个状态
            dp=new int[M][256];
            //base case
            dp[0][pat.charAt(0)]=1;
            //影子状态X的初始值为0
            int X=0;
            //当前状态j从1开始
            for(int j=1;j<M;j++){
                for(int c=0;c<256;c++){
                    if(pat.charAt(j)==c)
                        dp[j][c]=j+1;
                    else
                        dp[j][c]=dp[X][c];
                }
                //更新影子状态
                X=dp[X][pat.charAt(j)];
            }
        }

        public int search(String txt){
            int M=pat.length();
            int N=txt.length();
            //pat的初始状态为0
            int j=0;
            for (int i = 0; i < N; i++) {
                //当前是状态i，遇到字符txt[i]
                //pat应该转移到哪个状态?
                j=dp[j][txt.charAt(i)];
                //如果到达终止状态，则返回匹配开头的索引值
                if(j==M) return i-M+1;
            }
            //如果没有到达终止状态，则匹配失败
            return -1;
        }
    }

    /*BF算法*/
    /*
    * BF算法是指使用暴力匹配方法求解字符串主串中是否包含子串序列。算法的核心原理是，将子串与主串从首位开始进行匹配，
    * 如果对应位置字符匹配成功，则主串与子串索引位置均进一位，否则子串索引回到首位，主串索引位置回到上一次匹配起始位置的下一位。
    * */
    public int BFMatch(String txt,String pat,int pos){
        char[] arr1=txt.toCharArray();
        char[] arr2=pat.toCharArray();
        int i=pos;
        int j=0;
        while(i<arr1.length&&j<arr2.length){
            if(arr1[i]==arr2[j]){
                i++;
                j++;
            }else {
                i=i-j+1;
                j=0;
            }
        }
        if(j==arr2.length) return i-j;
        else return -1;
    }

    public static void main(String[] args) {
        String a = "aaabbaaaccssdd";
        String b = "acc";
        KMPMatchAL ka=new KMPMatchAL();
        System.out.println(ka.BFMatch(a, b, 3));
    }
}

