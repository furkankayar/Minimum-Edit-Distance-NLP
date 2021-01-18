import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileManager {
    public static FileManager instance = new FileManager();

    private FileManager() {
    }

    public List<String> readAllLines(String fileName) {

        List<String> words = new ArrayList<String>();
        System.out.println("Reading " + fileName);
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String line = "";
            while((line = br.readLine()) != null){
                words.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return words;
    }

    public void writeString(String str, String fileName){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(str);
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }


    public static FileManager getInstance(){
        return instance;
    }
}