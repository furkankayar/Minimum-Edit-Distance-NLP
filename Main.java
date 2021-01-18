import java.util.List;

public class Main {
    
    private static final FileManager fileManager = FileManager.getInstance();
    private static final MEDCalculator medCalculator = MEDCalculator.getInstance();

    public static void main(String[] args){

        //List<String> words = fileManager.readAllLines("sozluk.txt");
        System.out.println(medCalculator.calculateMED("tesana", "furkan"));
    }

}