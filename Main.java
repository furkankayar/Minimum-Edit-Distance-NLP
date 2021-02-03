import java.io.IOException;


public class Main {
    
    private static final UserInterface userInterface = UserInterface.getInstance();

    public static void main(String[] args) {

        try {
            new ProcessBuilder("cmd", "/c", "color").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            System.out.println("Color command failed please use cmd.exe or powershell.exe to see colorized matrix.");
            UserInterface.ANSI_BLACK = "";
            UserInterface.ANSI_BLUE_BACKGROUND = "";
            UserInterface.ANSI_RESET = "";
            UserInterface.ANSI_YELLOW = "";
            UserInterface.ANSI_YELLOW_BACKGROUND = "";
            UserInterface.ANSI_GREEN_BACKGROUND = "";
            UserInterface.ANSI_RED_BACKGROUND = "";

        }

        
       

        userInterface.mainLoop();
        //List<String> words = fileManager.readAllLines("sozluk.txt");
        //userInterface.printMEDModel(medCalculator.calculateMED("test test", "at kestanesi"));
    }

}