package extra_exercises;

public class TwoDimensionArray {

    public static void main(String[] args) {

        int[][] lotteryticket = { { 2, 7, 99 }, // method 1 to create 2D array
                { 11, 25, 44 },
                { 29, 47, 10 }
        };

        int[][] lotteryticket2 = new int[3][3]; // method 2 to create 2D array

        lotteryticket2[0][0] = 2;
        lotteryticket2[0][1] = 7;
        lotteryticket2[0][2] = 99;
        lotteryticket2[1][0] = 11;
        lotteryticket2[1][1] = 25;
        lotteryticket2[1][2] = 44;
        lotteryticket2[2][0] = 29;
        lotteryticket2[2][1] = 47;
        lotteryticket2[2][2] = 10;

        int[][] lotteryticket3 = new int[3][3]; // 2D array resulting from adding up the first two 2D arrays

        // for (int i = 0; i < 3; i++) {
        // System.out.println(lotteryticket[i][i]); // printing out (0,0) , (1,1) ,
        // (2,2) [diagonals]
        // }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                lotteryticket3[i][j] = lotteryticket[i][j] + lotteryticket2[i][j];
                System.out.print(lotteryticket3[i][j] + " "); // printing out all three rows
            }
            System.out.println();
        }
        System.out.println();
        

        // for (int x = 0; x < 3; x++) {
        // for (int y = 0; y < 3; y++) {
        // System.out.print(lotteryticket2[x][y] + " "); // printing out all three rows
        // }
        // System.out.println();
        // }

        // char[][] dashbox = new char[10][10];

        // for (int i = 0; i < 10; i++) {
        // for (int j = 0; j < 10; j++) {
        // dashbox[i][j] = '-';
        // System.out.print(dashbox[i][j] + " ");
        // }
        // System.out.println();
        // }

        // int[][] integers = {
        // { 1, 10, 3, 8 },
        // { 9, 12, 6, 7 },
        // { 5, 2, 11, 4 }
        // };

        // for (int i = 0; i < 3; i++) {
        // int max = integers[i][0];
        // for (int j = 1; j < 4; j++) {
        // max = (integers[i][j] > max) ? integers[i][j] : max;
        // }
        // System.out.println("Max of Row " + (i + 1) + ": " + max);
        // }

    }
}
