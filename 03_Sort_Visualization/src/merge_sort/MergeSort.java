package merge_sort;

import java.util.Arrays;

public class MergeSort {

    // 不产生实例
    private MergeSort(){}

    // 将 arr[l...mid]和arr[mid+1...r]两部分进行归并
    // O(n)
    private static void merge(Comparable[] arr, int l, int mid, int r) {
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);

        // i=0
        // j=mid+1
        // k=current
        // r=right
        // l=left
        // 初始化，i指向左半部分的起始索引位置l; j指向右半部分起始索引位置 mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i>mid) { // 如果左半部分元素已经全部处理完毕
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) { // 如果右半部分元素已经全部处理完毕
                arr[k]=aux[i-l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) { // 左半部分所指元素 < 右半部分所指元素
                arr[k] = aux[i - l];
                i++;
            } else { // 左半部分所指元素 >= 右半部分所指元素
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    // 递归使用归并排序, 对 arr[mid+1...r] 的范围进行排序
    private static void sort(Comparable[] arr, int l, int r, int depth) {
        System.out.print(repeatChars('-',depth*2));
        System.out.println("Deal with [ " + l + " , " + r + " ] ");

        if (l>=r) {
            return;
        }
        int mid = (l + r) / 2;
        sort(arr, l, mid, depth + 1);
        sort(arr, mid + 1, r, depth + 1);
        merge(arr, l, mid, r);
    }

    private static String repeatChars(char character, int length) {
        StringBuilder s = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            s.append(character);
        }
        return s.toString();
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1, 0);
    }

    // 测试 MergeSort
    public static void main(String[] args) {

        Integer[] arr = new Integer[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = new Integer(8 - i);
        }
        MergeSort.sort(arr);
        return;
    }
}
