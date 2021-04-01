package com.jianglong.sortAL;

/*排序算法*/
public class sortAL {

    /*
    * 注明：排序算法关于两个数组元素相等的情况判断原则：如果元素相等，则不需要更改原本的元素顺序，即不需要交换元素，也不需要在<或>加上=的条件
    * */
    /*冒泡排序*/
    /*
    冒泡排序是通过比较两个相邻元素的大小实现排序，如果前一个元素大于后一个元素，就交换这两个元素。这样就会让每一趟冒泡都能找到最大一个元素并放到最后。
    * 空间复杂度：整个排序过程是在原数据上进行操作，故为O（1）
    * 时间复杂度：由于嵌套了2层循环，故为O（n*n）
    * */
    public int[] sortArrayBubble(int[] unsortArray){
        for (int i = 0; i < unsortArray.length-1; i++) {
            for (int j = 0; j < unsortArray.length-1-i; j++) {
                if(unsortArray[j]>unsortArray[j+1]){
                    int temp=unsortArray[j];
                    unsortArray[j]=unsortArray[j+1];
                    unsortArray[j+1]=temp;
                }
            }
        }
        return unsortArray;
    }

    /*选择排序*/
    /*
    * 选择排序的思想是，依次从【无序列表】中找到一个最小的元素放到【有序列表】的最后面。以arr={8,1,4,6,2,3,5,4}为例，排序开始时把arr分为有序列表A=[]，
    * 无序列表B=[8,1,4,6,2,3,5,4]，依次从B中找出最小的元素放到A的最后面。这种排序也是逻辑上的分组，实际上不回创建A和B，只是用下标来标记A和B。
    * 以 arr = [ 8, 1, 4, 6, 2, 3, 5, 4 ] 为例，第一次找到最小元素 1 与 8 进行交换，这时有列表 A = [1], 无序列表 B = [8, 4, 6, 2, 3, 5, 4]；
    * 第二次从 B 中找到最小元素 2，与 B 中的第一个元素进行交换，交换后 A = [1，2]，B = [4, 6, 8, 3, 5, 4]；就这样不断缩短 B，扩大 A，最终达到有序。
    * 空间复杂度：整个排序过程是在原数据上进行操作，故为O（1）
    * 时间复杂度：由于嵌套了2层循环，故为O（n*n）
    * */
    public int[] sortArrayChoose(int[] unsortArray){
        for (int i = 0; i < unsortArray.length; i++) {
            int miniIndex=i;
            for (int j = i; j < unsortArray.length; j++) {
                //找到最小元素对应的下标
                if(unsortArray[miniIndex]>unsortArray[j]) miniIndex=j;
            }
            //交换i与最小元素下标的位置
            int temp=unsortArray[i];
            unsortArray[i]=unsortArray[miniIndex];
            unsortArray[miniIndex]=temp;
        }
        return unsortArray;
    }

    /*插入排序*/
    /*
    * 以 arr = [ 8, 1, 4, 6, 2, 3, 5, 7] 为例，它会把 arr 分成两组 A = [ 8 ] 和 B = [ 1, 4, 6, 2, 3, 5, 7] ，逐步遍历 B 中元素插入到 A 中，最终构成一个有序序列。
    *空间复杂度：整个排序过程是在原数据上进行操作，故为O（1）
    * 时间复杂度：由于嵌套了2层循环，故为O（n*n）
    * */
    public int[] sortArrayInsert(int[] unsortArray){
        //记录当前已排序数组的最大下标
        int preIndex=0;
        //记录当前待排序元素的值
        int current=0;
        for (int i = 1; i < unsortArray.length; i++) {
            preIndex=i-1;
            current=unsortArray[i];
            for (int j = preIndex; j >=0 ; j--) {
                if(unsortArray[j]>current) {
                    unsortArray[j+1]=unsortArray[j];//如果当前待排序数组元素的值小于第j个元素的值，则第j个元素向右移动一位
                    preIndex--;//更新当前待排序数组元素插入位置下标，注意这里不能等于j，因为在此之后j--
                }
            }
            unsortArray[preIndex+1]=current;
        }
        return unsortArray;
    }

    /*希尔排序*/
    /*
    * 它的核心思想是把一个序列分组，对分组后的内容进行插入排序，这里的分组只是逻辑上的分组，不会重新开辟存储空间。
    * 它其实是插入排序的优化版，插入排序对基本有序的序列性能好，希尔排序利用这一特性把原序列分组，对每个分组进行排序，逐步完成排序。
    * 空间复杂度：由于整个排序过程是在原数据上进行操作，故为 O(1);
      时间复杂度：希尔排序的时间复杂度与增量序列的选取有关，例如希尔增量时间复杂度为O(n²)，而Hibbard增量的希尔排序的时间复杂度为O(log n的3/2)，希尔排序时间复杂度的下界是n*log2n
    * */
    public int[] sortArrayShell(int[] unsortArray){
        //len=9
        int len=unsortArray.length;
        //floor向下取整，所以gap的值为:4,2,1
        for(int gap = (int) Math.floor(len/2); gap>0; gap= (int) Math.floor(gap/2)){
            // i=4;i<9;i++ (4,5,6,7,8)
            for (int i = gap; i < len; i++) {
                // j=0,1,2,3,4
                // [0]-[4] [1]-[5] [2]-[6] [3]-[7] [4]-[8]
                for (int j = i-gap; j >= 0&&unsortArray[j]>unsortArray[j+gap]; j=j-gap) {
                    //交换位置
                    int temp=unsortArray[j];
                    unsortArray[j]=unsortArray[j+gap];
                    unsortArray[j+gap]=temp;
                }
            }
        }
        return unsortArray;
    }

    /*快速排序*/
    /*
    * 快速排序的核心思想是对待排序序列通过一个「支点」（支点就是序列中的一个元素，别把它想的太高大上）进行拆分，使得左边的数据小于支点，右边的数据大于支点。
    * 然后把左边和右边再做一次递归，直到递归结束。我们以 （左边index + 右边index）/ 2 来选择支点。
    * 快速排序使用一个高效的方法做数据拆分。
    * 用一个指向左边的游标 i，和指向右边的游标 j，逐渐移动这两个游标，直到找到 arr[i] > 6 和 arr[j] < 6, 停止移动游标，交换 arr[i] 和 arr[j]，交换完后 i++，j--（对下一个元素进行比较），直到 i>=j，停止移动。
    * 空间复杂度：O(1)
    * 时间复杂度：O(nlogn)
    * */
    public int[] sortArrayQuick(int[] unsortArray,int leftIndex,int rightIndex) throws ArrayIndexOutOfBoundsException{
        int i=leftIndex,j=rightIndex;
        //取中间值作为一个支点
        int flagValue=unsortArray[(leftIndex+rightIndex)/2];
        while(i<=j){
            //左侧游标向右移动，直到找到大于支点的元素
            while(unsortArray[i]<flagValue){
                i++;
            }
            //右侧游标向左移动，直到找到小于支点的元素
            while(unsortArray[j]>flagValue){
                j--;
            }
            //交换两个元素，让左边的大于支点，右边的小于支点
            if(i<=j){
                if(i!=j){
                    int temp=unsortArray[i];
                    unsortArray[i]=unsortArray[j];
                    unsortArray[j]=temp;
                }
                i++;
                j--;
            }
        }
        //递归左边，进行快速排序
        if(leftIndex<j){
            sortArrayQuick(unsortArray,leftIndex,j);
        }
        //递归右边，进行快速排序
        if(i<rightIndex){
            sortArrayQuick(unsortArray,i,rightIndex);
        }
        return unsortArray;
    }

    /*
    * 快速排序2（易懂版）
    * 快速排序说白了就是给基准数据找到其正确的索引位置的过程。假设最开始的基准数据是数组的第一个元素，则首先用一个临时变量去存储基准数据，
    * 然后分别从数组的两端扫描数组，设置两个指示标志：low指向起始位置，high指向末位位置。
    * （1）首先从后半部分开始，如果扫描到的值大于基准数据就让high减1，如果发现有元素比该基准数据的值小，就将high位置的值赋给low位置。
    * （2）然后开始从前往后扫描，如果扫描到的值小于基准数据就让low加1，如果发现有元素大于基准数据的值，就再将low位置的值赋给high位置的值。
    * （3）继续前两步的遍历，直到low=high结束循环，此时low或high的下标就是基准数据23在该数组中的正确索引位置。
    * 通过以上步骤可以看出，快速排序的本质就是把基准数大的都放在基准数的右边，把比基准数小的放在基准数的左边，这样就找到了该基准数在数组中的正确位置。
    * 以后采用递归的方式分别对前半部分和后半部分进行排序，当前半部分和后半部分均有序时该数组就自然有序了
    * */
    public void sortArrayFast(int[] array,int low,int high) {
        if(low<high){
            //找到基准数据对应的正确索引位置
            int index=getIndex(array,low,high);
            // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
            sortArrayFast(array,low,index-1);
            sortArrayFast(array,low+1,high);
        }
    }
    int getIndex(int[] array,int low,int high) {
        int temp=array[low];
        while (low<high){
            // 当队尾的元素大于等于基准数据时,向前挪动high指针
            while (low<high&&array[high]>=temp){
                high--;
            }
            // 如果队尾元素小于tmp了,需要将其赋值给low
            array[low]=array[high];
            // 当队首元素小于等于tmp时,向前挪动low指针
            while (low<high&&array[low]<=temp){
                low++;
            }
            //当队首元素大于tmp时,需要将其赋值给high
            array[high]=array[low];
        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        array[low]=temp;
        return low;
    }

    public static void main(String[] args) {
        try{
            int[] array={2,3,1,7,9,5,6,4,8};
            sortAL sortAL=new sortAL();
            array=sortAL.sortArrayQuick(array,0,array.length-1);
            for(int a:array){
                System.out.print(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
