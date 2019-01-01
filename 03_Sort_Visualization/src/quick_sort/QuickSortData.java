package quick_sort;

public class QuickSortData {

    public int[] numbers;
    public int l, r; // 左右边界
    public boolean[] fixedPivots;
    public int curPivot;
    public int curElement;

    public QuickSortData(int N, int randomBound) {
        numbers = new int[N];
        fixedPivots = new boolean[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
            fixedPivots[i] = false;
        }

    }

    public int N() {
        return numbers.length;
    }

    public int get(int index) {
        return numbers[index];
    }

    public void swap(int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
