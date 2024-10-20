package extra_exercises;

public class lotteryticket {

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

        // for (int i = 0; i < 3; i++) {
        // System.out.println(lotteryticket[i][i]); // printing out (0,0) , (1,1) ,
        // (2,2) [diagonals]
        // }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(lotteryticket[i][j] + " "); // printing out all three rows
            }
            System.out.println();
        }
        System.out.println();

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                System.out.print(lotteryticket2[x][y] + " "); // printing out all three rows
            }
            System.out.println();
        }
    }
}
