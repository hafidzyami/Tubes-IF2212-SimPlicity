import java.util.ArrayList;
import java.util.Arrays;

public class TestMatrix {

    public static void main(String[] args){

        /* ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

        System.out.println(matrix.toString());//print empty matrix
        ArrayList<Integer> row1 = new ArrayList<Integer>(Arrays.asList(1,2,3));
        ArrayList<Integer> row2 = new ArrayList<Integer>(Arrays.asList(4,5,6));
        ArrayList<Integer> row3 = new ArrayList<Integer>(Arrays.asList(7,8,9));
        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);
        //System.out.println(matrix.toString()); */

        String[] arr = new String[10];
        Arrays.fill(arr, "c");
        ArrayList<String> row = new ArrayList<>(Arrays.asList(arr)) ;
        ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
        for (int i = 0;i < 5;i++) {
            matrix.add(row);
        }
        for (ArrayList<?> temp : matrix) {
            System.out.println(temp.toString().replaceAll("[\\[\\]\\,\\ ]", ""));
        }
        
    }

}