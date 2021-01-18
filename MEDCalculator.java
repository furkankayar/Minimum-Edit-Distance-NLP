import java.util.ArrayList;
import java.util.List;

public class MEDCalculator {
    private static MEDCalculator instance = new MEDCalculator();
    
    private MEDCalculator(){}

    public List<String> get5NearestWords(String str, List<String> dictionary){
        List<String> fiveNearestWords = new ArrayList<String>();



        return fiveNearestWords;
    }

    public int calculateMED(String str1, String str2){
        
        str1 = "#" + str1;
        str2 = "#" + str2;
        int maxY = str1.length();
        int maxX = str2.length();
        int distanceMatrix[][] = initDistanceMatrix(maxY, maxX);

        for(int j = 1 ; j < maxY ; j++){
            for(int i = 1 ; i < maxX ; i++){
                if(str2.charAt(i) == str1.charAt(j)){
                    distanceMatrix[j][i] = distanceMatrix[j-1][i-1];
                }
                else{
                    distanceMatrix[j][i] = min(distanceMatrix[j-1][i], distanceMatrix[j][i-1], distanceMatrix[j-1][i-1]) + 1;
                }
            }
        }
    
        analyzeDistanceMatrix(distanceMatrix, str1, str2);
        return distanceMatrix[maxY-1][maxX-1];
    }

    //https://stackoverflow.com/a/5861206
    private void analyzeDistanceMatrix(int matrix[][], String str1, String str2){
        
        int x = matrix[0].length - 1;
        int y = matrix.length - 1;

        while(x != 0 || y != 0){
            System.out.println(x + " " + y);
        if(x > 0 && y > 0 && min(matrix[y-1][x-1], matrix[y-1][x], matrix[y][x-1]) == matrix[y-1][x-1] && (matrix[y-1][x-1] == matrix[y][x] || matrix[y-1][x-1] == matrix[y][x] - 1)){
            
            if(matrix[y-1][x-1] == matrix[y][x] - 1){
                System.out.println("substitute " + str1.charAt(y) + " to " +  str2.charAt(x));
            }
            x = x-1;
            y = y-1;
        }
        else if(y == 0 || (x > 0 && (y > 0 && matrix[y][x-1] <= matrix[y-1][x]) && (matrix[y][x-1] == matrix[y][x] || matrix[y][x-1] == matrix[y][x] - 1))){    
            System.out.println("insert " + str2.charAt(x));
            x = x-1;
        }
        else{
            System.out.println("deletion " + str1.charAt(y));
            y = y-1;
        }
        }
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

    public static MEDCalculator getInstance(){
        return instance;
    }
}