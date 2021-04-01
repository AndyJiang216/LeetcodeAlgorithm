package com.jianglong.divideAL;

/*分治算法*/
public class divideAndConquerAL {
    /*二分查找*/
    /*算法流程：
      （1）选择一个标志 i 将集合分为二个子集合。
      （2）判断标志 L(i) 是否能与要查找的值 des 相等，相等则直接返回。
      （3）否则判断 L(i) 与 des 的大小。
      （4）基于判断的结果决定下步是向左查找还是向右查找。
      （5）递归继续上面的步骤。*/
    /*通过二分查找的流程可以看出，二分查找是将原有序数列划分为左右两个子序列，然后在对两个子序列中的其中一个在进行划分，直至查找成功。*/
    public int binarySearch(int[] a,int x,int start,int end){//a表示需要二分的有序数组（升序），x表示需要查找的数字，start end表示要查找的区间阈值
        if(start>end) return -1;
        int mid=(end+start)/2;
        if(x==a[mid]) return mid;//找到x
        else if(x>a[mid]) return binarySearch(a,x,mid+1,end);//x在后半部分，则在后半部分继续进行二分查找
        else return binarySearch(a,x,start,mid-1);//x在前半部分，则在前半部分继续进行二分查找
    }

    /*汉诺塔*/
    /*
    汉诺塔问题：古代有一个梵塔，塔内有三个座A、B、C，A座上有64个盘子，盘子大小不等，大的在下，小的在上。
               有一个和尚想把这个盘子从A座移到B座，但每次只能允许移动一个盘子，并且在移动过程中，3个座上的盘子始终保持大盘在下，小盘在上。
    * */
    public void hanoi(int n,String sourceTower,String tempTower,String targetTower){
        if(n==1) move(n,sourceTower,targetTower);//如果只有一个盘子，那么直接从sourceTower移动到targetTower
        else {
            //将（第1-第n-1个盘子）从sourceTower经过targetTower移动到tempTower
            hanoi(n-1,sourceTower,targetTower,tempTower);
            //将第n个盘子从sourceTower移动到targetTower
            move(n,sourceTower,targetTower);
            //将之前移动到tempTower的第1-第n-1个盘子，从tempTower经过sourceTower移动到targetTower
            hanoi(n-1,tempTower,sourceTower,targetTower);
        }
    }
    public void move(int n,String sourceTower,String targetTower){
        System.out.println("第"+n+"号盘子从"+sourceTower+"--->"+targetTower);
    }

    public static void main(String[] args) {
        int[] a={1,2,3,4,5,6};
        divideAndConquerAL da=new divideAndConquerAL();
        //System.out.println(da.binarySearch(a,6,0,5));

        da.hanoi(3,"A","B","C");
    }
}
