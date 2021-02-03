import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static FileManager instance = new FileManager();

    private FileManager() {
    }

    public List<String> readAllLines(String fileName) {

        List<String> words = new ArrayList<String>();
        System.out.println("Reading " + fileName);
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ISO-8859-9"));
            String line = "";
            while((line = br.readLine()) != null){
                words.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.print("Press enter to continue . . .");
            new Scanner(System.in).nextLine();
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.print("Press enter to continue . . .");
            new Scanner(System.in).nextLine();
            System.exit(0);
        }
        
        return words;
    }

    public static FileManager getInstance(){
        return instance;
    }
}