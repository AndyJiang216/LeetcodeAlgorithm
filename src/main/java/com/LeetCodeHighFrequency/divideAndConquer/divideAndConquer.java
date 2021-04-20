package com.LeetCodeHighFrequency.divideAndConquer;

import java.util.ArrayList;
import java.util.List;

//分治算法
public class divideAndConquer {

    //题目1：给表达式加括号
    //Input: "2-1-1".
    //((2-1)-1) = 0
    //(2-(1-1)) = 2
    //Output : [0, 2]
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ways=new ArrayList<Integer>();
        for (int i = 0; i < input.length(); i++) {
            char c=input.charAt(i);
            if(c=='+' || c=='-' || c=='*') {
                List<Integer> left=diffWaysToCompute(input.substring(0,i));
                List<Integer> right=diffWaysToCompute(input.substring(i+1));
                for(int l:left){
                    for(int r:right){
                        switch (c) {
                            case '+':
                                ways.add(l+r);
                                break;
                            case '-':
                                ways.add(l-r);
                                break;
                            case '*':
                                ways.add(l*r);
                                break;
                        }
                    }
                }
            }
        }
        if(ways.size()==0){
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }

}
