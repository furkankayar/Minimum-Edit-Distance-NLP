import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

public class MEDCalculator {
    private static MEDCalculator instance = new MEDCalculator();
    
    private MEDCalculator(){}

    public List<String> get5NearestWords(String str, List<String> dictionary){
        List<String> fiveNearestWords = new ArrayList<String>();



        return fiveNearestWords;
    }

    public TreeMap<String, Integer> calculateMEDForDictionary(String str1, List<String> words){

        TreeMap<String, Integer> wordDistances = new TreeMap<String, Integer>();

        for(String word : words){
            wordDistances.put(word, calculateMEDValue(str1, word));
        }

        return sortByValues(wordDistances);
    }

    private int calculateMEDValue(String str1, String str2){

        str1 = "#" + str1;
        str2 = "#" + str2;
        int maxY = str2.length();
        int maxX = str1.length();
        int distanceMatrix[][] = initDistanceMatrix(maxY, maxX);

        for(int j = 1 ; j < maxY ; j++){
            for(int i = 1 ; i < maxX ; i++){
                if(str2.charAt(j) == str1.charAt(i)){
                    distanceMatrix[j][i] = distanceMatrix[j-1][i-1];
                }
                else{
                    distanceMatrix[j][i] = min(distanceMatrix[j-1][i], distanceMatrix[j][i-1], distanceMatrix[j-1][i-1]) + 1;
                }
            }
        }

        return distanceMatrix[maxY-1][maxX-1];
    }

    public MEDModel calculateMED(String str1, String str2){
        
        MEDModel medModel = new MEDModel();
        medModel.setWord1(str1);
        medModel.setWord2(str2);
        str1 = "#" + str1.toLowerCase();
        str2 = "#" + str2.toLowerCase();
        int maxY = str2.length();
        int maxX = str1.length();
        int distanceMatrix[][] = initDistanceMatrix(maxY, maxX);

        for(int j = 1 ; j < maxY ; j++){
            for(int i = 1 ; i < maxX ; i++){
                if(str2.charAt(j) == str1.charAt(i)){
                    distanceMatrix[j][i] = distanceMatrix[j-1][i-1];
                }
                else{
                    distanceMatrix[j][i] = min(distanceMatrix[j-1][i], distanceMatrix[j][i-1], distanceMatrix[j-1][i-1]) + 1;
                }
            }
        }
        
        
        List<Stack> analysis = analyzeDistanceMatrix(distanceMatrix, str1, str2);
        
        medModel.setDistanceMatrix(distanceMatrix);
        medModel.setOperations((Stack<String>)analysis.get(0));
        medModel.setPath((Stack<int[]>)analysis.get(1));

        return medModel;
    }

    //https://stackoverflow.com/a/5861206
    public List<Stack> analyzeDistanceMatrix(int matrix[][], String str1, String str2){
        
        Stack<String> operations = new Stack<String>();
        Stack<int[]> steps = new Stack<int[]>();
        int x = matrix[0].length - 1;
        int y = matrix.length - 1;
        steps.push(new int[]{x, y});

        while(x != 0 || y != 0){
            if(x > 0 && y > 0 && min(matrix[y-1][x-1], matrix[y-1][x], matrix[y][x-1]) == matrix[y-1][x-1] && (matrix[y-1][x-1] == matrix[y][x] || matrix[y-1][x-1] == matrix[y][x] - 1)){
            
                if(matrix[y-1][x-1] == matrix[y][x] - 1){
                    //System.out.println("substitute " + str1.charAt(x) + " with " +  str2.charAt(y));
                    operations.push(String.format("%3s ==> %19s ==> %-3s" , str1.charAt(x), ("Substitute " + str1.charAt(x) + " with " +  str2.charAt(y)), str2.charAt(y)));
                }
                else{
                    //System.out.println("no operation");
                    operations.push(String.format("%3s ==>    %12s     ==> %-3s" , str1.charAt(x), "No Operation", str2.charAt(y)));
                }
                x = x-1;
                y = y-1;
            }
            else if(y == 0 || (x > 0 && (y > 0 && matrix[y][x-1] <= matrix[y-1][x]) && (matrix[y][x-1] == matrix[y][x] || matrix[y][x-1] == matrix[y][x] - 1))){    
                //System.out.println("delete " + str1.charAt(x));
                operations.push(String.format("%3s ==>      %8s       ==> %-3s", str1.charAt(x),("Delete " + str1.charAt(x)), "-"));
                x = x-1;
            }
            else{
                //System.out.println("insert " + str2.charAt(y));
                operations.push(String.format("%3s ==>      %8s       ==> %-3s", "-", ("Insert " + str2.charAt(y)), str2.charAt(y)));
                y = y-1;
            }

            steps.push(new int[]{x, y});
        }

        return Arrays.asList(new Stack[]{operations, steps});
    }

    private int min(int x1, int x2, int x3){
        return Math.min(Math.min(x1, x2), x3);
    }

    private int[][] initDistanceMatrix(int size1, int size2){

        int matrix[][] = new int[size1][size2];

        for(int i = 0 ; i < size1; i++){
            matrix[i][0] = i;
        }
        for(int j  = 0; j < size2; j++){
            matrix[0][j] = j; 
        }

        return matrix;
    }


    public TreeMap<String, Integer> sortByValues(TreeMap<String, Integer> treeMap) {
        Comparator<String> valueComparator = new Comparator<String>() {
            public int compare(String k1, String k2) {
            int compare = treeMap.get(k1).compareTo(treeMap.get(k2));
            if (compare == 0) 
                return 1;
            else 
                return compare;
            }
        };
 
        TreeMap<String, Integer> sortedByValues = new TreeMap<String, Integer>(valueComparator);
        sortedByValues.putAll(treeMap);
        return sortedByValues;
    }

    public static MEDCalculator getInstance(){
        return instance;
    }
}