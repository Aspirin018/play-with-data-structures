package v1;

public class Main {

    public static void main(String[] args) {
//        int[] arr = new int[10];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = i;
//        }
//
//        int[] scores = new int[]{100, 98, 97};
//        for (int score : scores) {
//            System.out.println(score);
//        }
//
//        scores[0] = 99;
//        for (int score : scores) {
//            System.out.println(score);
//        }

        Array array = new Array(20);
        for (int i = 0; i < 5; i++) {
            array.addLast(i);
        }
        System.out.println(array.toString());
        array.add(2, 100);
        array.set(0, 99);
        System.out.println(array.toString());
    }
}
