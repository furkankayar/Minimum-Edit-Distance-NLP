import java.io.Console;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Map.Entry;

public class UserInterface {
    
    public static UserInterface instance = new UserInterface();
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static String ANSI_BLACK = "\u001B[30m";
    public static String ANSI_YELLOW = "\u001B[33m";
    
    private static final FileManager fileManager = FileManager.getInstance();
    private static final MEDCalculator medCalculator = MEDCalculator.getInstance();

    private UserInterface(){
    }

    public void mainLoop(){

        List<String> words = fileManager.readAllLines("sozluk.txt");
        Console console = System.console();
        while(true){
            System.out.println("\nSelect operation: \n\n" +
                                "1- List 5 alternative correct words.\n" + 
                                "2- Find MED value and transformation steps of two words.\n" + 
                                "3- Exit\n"
            );
            System.out.print("> ");

            String choice = console.readLine().trim();
            if(choice.equals("1")){
                System.out.print("Enter a word> ");
                String word = console.readLine().trim();
                while(word.equals("")){
                    System.out.print("Enter a word> ");
                    word = console.readLine().trim();
                }
                
                long startTime = System.currentTimeMillis();
                TreeMap<String, Integer> wordDistances = medCalculator.calculateMEDForDictionary(word, words);
                int i = 0;
                System.out.println(String.format("\n %-12s%s\n-------------------", "Distance", "Word"));
                for(Entry<String, Integer> entry : wordDistances.entrySet()){
                    System.out.println(String.format(" %5s%3d%4s%s", " ", entry.getValue(), " ", entry.getKey()));
                    if(++i == 5)
                        break;
                }
                System.out.println("-------------------");
                System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) + " ms");
                System.out.println("-------------------");
            }   
            else if(choice.equals("2")){
                System.out.print("Enter first word> ");
                String word1 = console.readLine().trim();
                while(word1.equals("")){
                    System.out.print("Enter first word> ");
                    word1 = console.readLine().trim();
                }
                System.out.print("Enter second word> ");
                String word2 = console.readLine().trim();
                while(word2.equals("")){
                    System.out.print("Enter second word> ");
                    word2 = console.readLine().trim();
                }
                System.out.println("\nEdit Distance Matrix:\n");
                long startTime = System.currentTimeMillis();
                MEDModel medModel = medCalculator.calculateMED(word1, word2);
                printMEDModel(medModel);
                System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) + " ms");
                System.out.println("-------------------");                
            }
            else if(choice.equals("3")){
                break;
            }
            else{
                System.out.println("\nInvalid option!\n");
            }
        }
    }

    public void printMEDModel(MEDModel medModel){

        int[][] matrix = medModel.getDistanceMatrix();
        int maxX = matrix[0].length;
        int maxY = matrix.length;
        String str1 = "#" + medModel.getWord1();
        String str2 = "#" + medModel.getWord2();
        Stack<int[]> path = (Stack<int[]>)medModel.getPath().clone();

        System.out.println(ANSI_BLUE_BACKGROUND + "     " + str1.replace("", "    ").trim() + "  " + ANSI_RESET);
        for(int j = 0 ; j < maxY ; j++){
            System.out.print(ANSI_BLUE_BACKGROUND + " " + str2.charAt(j) + " " + ANSI_RESET);
            for(int i = 0 ; i < maxX ; i++){
                if(j == path.peek()[1] && i == path.peek()[0]){
                    path.pop();
                    if(path.empty()){
                        System.out.print(ANSI_RED_BACKGROUND);
                    }
                    else{
                        System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLACK);
                    }
                }
                else{
                    System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK);
                }
                System.out.print(String.format("  %-3d", matrix[j][i]) + ANSI_RESET);
            }
            System.out.println("");
        }

        Stack<String> operations = medModel.getOperations();
        System.out.println("\nMinimum Edit Distance is " + matrix[maxY-1][maxX-1] + ".\nOperations: \n");
        while(!operations.empty()){
            System.out.println(operations.pop());
        }
        
        if(ANSI_BLACK.equals("")){
            System.out.println("\nSteps: (Program could not colorize the matrix. You can trace the path by using coordinates below.)\n");
            path = medModel.getPath();
            System.out.println(" X Y\n-----");
            while(!path.isEmpty()){
                System.out.println(" " + path.peek()[0] + " " + path.pop()[1]);
            }
        }

        System.out.println("\n-------------------");
    }



    public static UserInterface getInstance(){
        return instance;
    }

}