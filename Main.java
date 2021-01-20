import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final FileManager fileManager = FileManager.getInstance();
    private static final MEDCalculator medCalculator = MEDCalculator.getInstance();
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