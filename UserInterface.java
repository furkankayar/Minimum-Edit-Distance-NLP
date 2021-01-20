import java.util.Stack;

public class UserInterface {
    
    public static UserInterface instance = new UserInterface();
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static String ANSI_BLACK = "\u001B[30m";
    public static String ANSI_YELLOW = "\u001B[33m";


    private UserInterface(){
    }


    public void printMEDModel(MEDModel medModel){

        int[][] matrix = medModel.getDistanceMatrix();
        int maxX = matrix[0].length;
        int maxY = matrix.length;
        String str1 = "#" + medModel.getWord1();
        String str2 = "#" + medModel.getWord2();
        Stack<int[]> path = medModel.getPath();

        System.out.println(ANSI_BLUE_BACKGROUND + "     " + str1.replace("", "    ").trim() + "  " + ANSI_RESET);
        for(int j = 0 ; j < maxY ; j++){
            System.out.print(ANSI_BLUE_BACKGROUND + " " + str2.charAt(j) + " " + ANSI_RESET);
            for(int i = 0 ; i < maxX ; i++){
                if(j == path.peek()[1] && i == path.peek()[0]){
                    System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLACK);
                    path.pop();
                }
                else{
                    System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK);
                }
                System.out.print(String.format("  %-3d", matrix[j][i]) + ANSI_RESET);
            }
            System.out.println("");
        }
    }



    public static UserInterface getInstance(){
        return instance;
    }

}