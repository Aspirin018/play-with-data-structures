package recursion;

/**
 * 用递归的方式实现数组求和
 */
public class RecursionSum {
    public int sum(int[] arr){
        return sumRecur(arr, 0);
    }

    /**
     * 计算[start, n)的和
     * @param arr
     * @param start
     * @return
     */
    private int sumRecur(int[] arr, int start) {
        //这一步就是最基本的问题
        if(start == arr.length) {
            return 0;
        }
        //这一步是将原问题转换为更小的同一问题
        return arr[start] + sumRecur(arr, start+1);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        RecursionSum recursionSum = new RecursionSum();
        int sum = recursionSum.sum(arr);
        System.out.println(sum);
    }
}
