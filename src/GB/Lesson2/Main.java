package GB.Lesson2;

public class Main {
    public static void main(String[] args) {

        System.out.println("Point 1");
        var arr = mirrorValue(new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0});
        for (var a : arr) System.out.print(a + " ");

        System.out.println("\n\nPoint 2");
        var arr2 = fillArrByThree(new int[8]);
        for (var a : arr2) System.out.print(a + " ");

        System.out.println("\n\nPoint 3");
        var arr3 = changeArr(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1});
        for (var a : arr3) System.out.print(a + " ");

        System.out.println("\n\nPoint 4");
        var arr4 = fillMatrixDiagonal(new int[5][5]);
        for (var row : arr4) {
            for (var a : row)
                System.out.print(a + " ");
            System.out.println();
        }

        System.out.println("\n\nPoint 5");
        var arr5 = mathMinMax(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1});
        var mth = new String[]{"Min = ", "Max = "};
        for (int i = 0; i < arr5.length; i++) {
            System.out.print(mth[i] + arr5[i] + " ");
        }


        System.out.println("\n\nPoint 6");
        var c = checkBalance(new int[]{2, 2, 2, 1, 2, 2, 10, 1});
        System.out.println(c);

        System.out.println("\n\nPoint 7");
        var arr7 = shiftArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 3);
        for (int i : arr7) {
            System.out.print(i + " ");
        }



    }

    // Point 1
    static int[] mirrorValue(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 1
                    ? 0
                    : 1;
        }
        return arr;
    }

    // Point 2
    static int[] fillArrByThree(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
        return arr;
    }

    // Point 3
    static int[] changeArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] < 6
                    ? arr[i] * 2
                    : arr[i];
        }
        return arr;
    }

    // Point 4
    static int[][] fillMatrixDiagonal(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = arr[i][arr.length - i - 1] = 1;
        }
        return arr;
    }

    // Point 5
    static int[] mathMinMax(int[] arr) {
        if (arr.length == 0 ) arr = new int[1];
        int min = arr[0];
        int max = min;

        for (int i : arr) {
            if (i < min) min = i;
            if (i > max) max = i;
        }
        return new int[]{min, max};
    }

    // Point 6
    static boolean checkBalance(int[] arr) {
        var left = 0;
        var sum = 0;
        for (var a : arr) sum += a;
        for (var a : arr) {
            left += a;
            if (sum - left == left) return true;
        }
        return false;

    }

    // Point 7
    static int[] shiftArray(int[] arr, int step) {
       int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            var pos = (arr.length + i - step) % arr.length;
            res[i] = arr[pos];
        }
        return  res;
    }


}
