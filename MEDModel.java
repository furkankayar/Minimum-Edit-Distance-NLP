import java.util.Stack;

public class MEDModel {
    
    private String word1;
    private String word2;
    private int[][] distanceMatrix;
    private Stack<String> operations;
    private Stack<int[]> path; 

    public MEDModel() {
    }

    public MEDModel(String word1, String word2, int[][] distanceMatrix, Stack<String> operations, Stack<int[]> path) {
        this.word1 = word1;
        this.word2 = word2;
        this.distanceMatrix = distanceMatrix;
        this.operations = operations;
        this.path = path;
    }

    public String getWord1() {
        return this.word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return this.word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public int[][] getDistanceMatrix() {
        return this.distanceMatrix;
    }

    public void setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public Stack<String> getOperations() {
        return this.operations;
    }

    public void setOperations(Stack<String> operations) {
        this.operations = operations;
    }

    public Stack<int[]> getPath() {
        return this.path;
    }

    public void setPath(Stack<int[]> path) {
        this.path = path;
    }
   
}