package com.jianglong.dynamicAndGreddyAL;

/*贪心算法*/
public class greedyAL {
    /*小明手中有 1，5，10，50，100 五种面额的纸币，每种纸币对应张数分别为 5，2，2，3，5 张。若小明需要支付 456 元，则需要多少张纸币？*/
    public static void main(String[] args) {
        final int N=5;
        int[] Count={5,2,2,3,5};//每一张纸币的数量
        int[] Value={1,5,10,50,100};
        greedyAL gr=new greedyAL();
        System.out.println(gr.solve(N,Count,Value,456));
    }
    public int solve(int N,int[] Count,int[] Value,int money){
        int num=0;
        for (int i = N-1; i >= 0; i--) {
            int c=Math.min(money/Value[i],Count[i]);//每一种面额的钱所需要的张数
            money=money-c*Value[i];
            num+=c;
        }
        if(money>0) num=-1;
        return num;
    }
}
