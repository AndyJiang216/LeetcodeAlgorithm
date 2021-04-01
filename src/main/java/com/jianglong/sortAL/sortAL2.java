package com.jianglong.sortAL;

import java.util.*;

/*排序算法2*/
public class sortAL2 {

    /*归并排序*/
    /*
    * 归并排序，采用分治思想，先把待排序序列拆分成一个个子序列，直到子序列只有一个元素，停止拆分，然后对每个子序列进行边排序边合并。
    * 空间复杂度：需要用到一个数组保存排序结果，也就是合并的时候，需要开辟空间来存储排序结果，故为 O ( n );
    * 时间复杂度：最好最坏都为 O(nlogn);
    * */
    /*归并排序的核心思想在于将数组切分为单个元素后，采用递归的思想，把局部少量的元素排列成有序数组，然后一步步合并为整体的有序数组*/
    public int[] sortArrayMerge(int[] a,int low,int high){
        //对数组进行分割，直到切分为单个元素，然后进行递归合并
        int mid=(low+high)/2;
        if(low<high){//当数组范围切分至单个元素时，停止切分
            sortArrayMerge(a,low,mid);
            sortArrayMerge(a,mid+1,high);
            //左右归并
            merge(a,low,mid,high);
        }
        return a;
    }

    private void merge(int[] a,int low,int mid,int high){
        int[] temp=new int[high-low+1];
        int i=low,j=mid+1;
        int k=0;
        //把较小的数先移到新数组中
        while(i<=mid&&j<=high){
            if(a[i]<a[j]){
                temp[k++]=a[i++];
            }else {
                temp[k++]=a[j++];
            }
        }
        //把左边剩余的数移入数组
        while(i<=mid){
            temp[k++]=a[i++];
        }
        //把右边剩余的数移入数组
        while(j<=high){
            temp[k++]=a[j++];
        }
        //把新数组中的数覆盖nums数组
        for(int x=0;x<temp.length;x++){
            a[x+low]=temp[x];
        }
    }

    /*计数排序*/
    /*
    * 前述6种排序都是基于比较的思想，即比较两个元素的大小，然后交换位置。
    * 而计数排序的核心思想是把一个无序序列A转换为另一个有序序列B，从B中逐个取出所有元素，取出的元素即为有序序列，适用于待排序序列中的元素取值范围比较小。
    * 即找到数组最大值最小值，根据差值确定计数数组的长度，然后根据数组值计算元素在计数数组中的下标。遍历计数数组，最终转换为排序好的数组。
    * 空间复杂度：需要额外申请空间，复杂度为“桶”的个数，故为 O ( k )， k 为“桶”的个数，也就是 countArr 的长度;
    * 时间复杂度：最好最坏都为 O(n+k)， k 为“桶”的个数，也就是 countArr 的长度;
    * */
    public int[] sortArrayCount(int[] unsortArray){
        int min=unsortArray[0],max=unsortArray[0];
        //遍历找出待排序数组的最大值与最小值
        for (int i = 0; i < unsortArray.length; i++) {
            if(min>unsortArray[i]) min=unsortArray[i];
            if(max<unsortArray[i]) max=unsortArray[i];
        }
        //确定计数数组的数组长度
        int[] countArr=new int[max-min+1];
        //初始化计数数组，将数组的每个位置都填充为0
        for (int i = 0; i < countArr.length; i++) {
            countArr[i]=0;
        }
        //根据待排序数组每个元素的值与最小值的差，确定该元素在计数数组中的索引值，并将计数数组对应位置元素值+1
        for (int i = 0; i < unsortArray.length; i++) {
            int value=unsortArray[i];
            countArr[value-min]=countArr[value-min]+1;
        }
        //因为计数数组索引值是有序的，因此直接提出索引值对应的计数数量即可填充为排序好的数组
        for (int i = 0,k=0; i < countArr.length; i++) {
            if(countArr[i]>0){
                unsortArray[k++]=i+min;
                countArr[i]--;
            }
        }
        return unsortArray;
    }

    /*桶排序*/
    /*
    * 桶排序的核心思想是确定桶的个数，根据待排序数组的取值范围确定每个桶的取值范围。遍历待排序数组，将数组元素插入符合范围的桶中，
    * 插入桶的时候使用插入排序对桶内元素进行排序，从而保证每个桶内均为有序数组，最终合并所有桶，即为有序数组。
    * 空间复杂度：桶的个数加元素的个数，为 O ( n + k );
    * 时间复杂度：最好为 O( n + k )，最坏为 O（n * n）;
    * */
    public int[] sortArrayBucket(int[] unsortArray){
        //确定数组中的最大值与最小值
        int min=unsortArray[0],max=unsortArray[0];
        for (int i = 0; i < unsortArray.length; i++) {
            if(min>unsortArray[i]) min=unsortArray[i];
            if(max<unsortArray[i]) max=unsortArray[i];
        }
        //创建桶，桶的个数为数组长度
        int bucketNum=unsortArray.length;
        List<LinkedList<Integer>> bucketList=new ArrayList<LinkedList<Integer>>(bucketNum);
        //初始化桶
        for (int i = 0; i <= bucketNum; i++) {
            bucketList.add(new LinkedList<Integer>());
        }
        //遍历数组元素，将所有元素都放入到对应的桶当中，并进行排序
        for (int i = 0; i < unsortArray.length; i++) {
            //计算当前元素应该放在哪个桶里面
            int num=(int)((unsortArray[i]-min)/((max-min)/(bucketNum-1)));
            insertSort(bucketList.get(num),unsortArray[i]);
        }
        //输出全部元素
        int k=0;
        for(LinkedList<Integer> bucket:bucketList){
            for(Integer value:bucket){
                unsortArray[k]=value;
                k++;
            }
        }
        return unsortArray;
    }
    public void insertSort(List<Integer> bucket,int data){
        ListIterator<Integer> it=bucket.listIterator();
        boolean insertFlag=true;
        while(it.hasNext()){
            if(data<=it.next()){
                it.previous();//把迭代器的位置偏移回到上一个位置
                it.add(data);//把数据插入到迭代器的当前位置
                insertFlag=false;
                break;
            }
        }
        if(insertFlag){
            bucket.add(data);//否则把数据插入到链表的末尾
        }
    }

    /*基数排序*/
    /*
    * 基数排序是从待排序序列中找出可以作为排序的[关键字]，按照关键字进行多次排序，最终得到有序序列。比如对 100 以内的序列 arr =  [ 3, 9, 489, 1, 5, 10, 2, 7, 6, 204 ]进行排序，
    * 排序关键字为「个位数」、「十位数」和「百位数」这 3 个关键字，分别对这 3 个关键字进行排序，最终得到一个有序序列。
    * 以 arr =  [ 3,  9,  489,  1,  5, 10, 2, 7, 6, 204 ] 为例，最大为 3 位数，分别对个、十、百位进行排序，
    * 最终得到的序列就是有序序列。可以把 arr 看成 [ 003,  009,  489,  001,  005, 010, 002, 007, 006, 204 ]，这样理解起来比较简单。
    * */
    public void sortArrayRadix(int[] unsortArray){
        int digit=getMaxDigit(unsortArray);//获取最大的数是多少位
        for (int i = 0; i < digit; i++) {//这里从高位向低位排序与从低位向高位排序均可
            redixSort(unsortArray,i);//针对第i位对数组进行基数排序
        }
    }
    //获取最大的数是多少位
    private int getMaxDigit(int[] arr){
        int digit=1;//默认只有一位
        int base=10;//十进制每多一位，代表其值大了十倍
        for(int i:arr){
            while(i>base){
                digit++;
                base=base*10;
            }
        }
        return digit;
    }
    //对数组进行基数排序
    private void redixSort(int[] arr,int digit){
        int base=(int)Math.pow(10,digit);
        //初始化桶
        ArrayList<ArrayList<Integer>> buckets=new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {//每一位上只可能有0~9这十个数，所以准备十个桶
            buckets.add(new ArrayList<Integer>());
        }
        //对当前位上的数值进行排序
        for(int i:arr){
            int index=(i/base)%10;
            buckets.get(index).add(i);
        }
        //将临时数组的元素输出替换到原始数组中
        int index=0;
        for(ArrayList<Integer> bucket:buckets){
            for(int i:bucket){
                arr[index++]=i;
            }
        }
    }

    public static void main(String[] args) {
        try{
            int[] array={2,3,1,7,9,6,4,8};
            sortAL2 sortAL2=new sortAL2();
            sortAL2.sortArrayRadix(array);
            for(int a:array){
                System.out.print(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
