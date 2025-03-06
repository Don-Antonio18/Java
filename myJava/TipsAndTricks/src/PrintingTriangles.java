package TipsAndTricks.src;

public class PrintingTriangles {
    public static void main(String[] args) {
        int num = 5;

        // // Square Pattern
        // for (int rows = 1; rows <= num; ++rows){
        //     for (int columns = 1; columns <= num; ++columns) {
        //         System.out.print("* ");
        //     }

        // System.out.println();
        // } 
    
        // // Increasing Triangle Pattern
        // for (int rows = 1; rows <= num; ++rows){
        //     for (int columns = 1; columns <= rows; ++columns){
        //         System.out.print("* ");
        //     }
        // System.out.println();
        // }

        // // Decreasing Triangle program
        // for (int rows = 1; rows <= num; rows++){
        //     for (int columns = rows; columns <= num; columns++){
        //         System.out.print("* ");
        //     }
        // System.out.println();
        // }

        // Right side increasing triangle
        for (int rows = 1; rows <= num; rows++ ){
            // decreasing blank spaces
            for (int columns = rows; columns <= num; columns++){
                System.out.print("  "); //! note the double space
            }
            // increasing dots
            for (int columns = 1; columns <= rows; columns ++){
                System.out.print("* ");
            }

            System.out.println();
        }

        // pyramid triangle
        for (int rows = 1; rows <= num; rows++ ){
            // decreasing blank spaces
            for (int columns = rows; columns <= num; columns++){
                System.out.print(" ");  //! note the single space
            }
            // increasing dots
            for (int columns = 1; columns <= rows; columns ++){
                System.out.print("* ");
            }

            System.out.println();
        }
    }   
}
