package com.shanhai.dataStructe._17sort;

import com.shanhai.dataStructe._17sort.count.CountingSort01;
import com.shanhai.dataStructe._17sort.count.CountingSort03;
import com.shanhai.dataStructe._17sort.insertion.ShellSort01;
import com.shanhai.dataStructe._17sort.insertion.ShellSort02;
import com.shanhai.dataStructe._17sort.merge.MergeInsertionSort;
import com.shanhai.dataStructe._17sort.merge.MergeSort01;
import com.shanhai.dataStructe._17sort.merge.MergeSort02;
import com.shanhai.dataStructe._17sort.quick.QuickSortHandleDuplicate;
import com.shanhai.dataStructe._17sort.quick.QuickSortHoare;
import com.shanhai.dataStructe._17sort.quick.QuickSortLomuto;
import com.shanhai.dataStructe._17sort.radix.RadixSort01;
import com.shanhai.dataStructe._17sort.radix.RadixSort02;
import com.shanhai.dataStructe._17sort.selection.HeapSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SortTest {
    public static void main(String[] args) throws InterruptedException {
        Integer[] array = getIntegerNums();
        BaseSort<Integer> baseSort = null;
        List<SortedResult> resultList = new ArrayList<>();

        /*==============================================*/

       /* baseSort = new BubbleSort01<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new BubbleSort02<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new SelectionSort<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new InsertionSort01<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new InsertionSort02<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new InsertionSort03<>(getCopy(array));
        resultList.add(baseSort.executeSort());*/

        /*==============================================*/
        baseSort = new JavaSort<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new ShellSort01<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new ShellSort02<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new MergeSort01<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new MergeSort02<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new MergeInsertionSort<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new HeapSort<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new QuickSortLomuto<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new QuickSortHoare<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new QuickSortHandleDuplicate<>(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new CountingSort01(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new CountingSort03(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new RadixSort01(getCopy(array));
        resultList.add(baseSort.executeSort());

        baseSort = new RadixSort02(getCopy(array));
        resultList.add(baseSort.executeSort());

        printResult(resultList);
    }

    private static Integer[] getCopy(Integer[] array) {
        return Arrays.copyOf(array, array.length);
    }

    public static Integer[] getIntegerNums() {
        Random random = new Random();
        int length = 50_0000;
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(0, length * 5);
        }
        return array;
    }

    private static void printResult(List<SortedResult> resultList) {
        resultList = resultList.stream().sorted().toList();
        String format = String.format("%-24s | \t%10s | \t\t%10s | \t %-2s", "排序名称", "比较次数", "交换次数", "执行时间(ms)");
        System.out.println(format);
        for (SortedResult sortedResult : resultList) {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println(sortedResult);
        }
    }
}
