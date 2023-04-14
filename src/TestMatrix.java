import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;

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
        Arrays.fill(arr, "0");
        ArrayList<String> row0 = new ArrayList<>(Arrays.asList(arr)) ;
        Arrays.fill(arr, "1");
        ArrayList<String> row1 = new ArrayList<>(Arrays.asList(arr)) ;
        Arrays.fill(arr, "2");
        ArrayList<String> row2 = new ArrayList<>(Arrays.asList(arr)) ;
        Arrays.fill(arr, "3");
        ArrayList<String> row3 = new ArrayList<>(Arrays.asList(arr)) ;
        Arrays.fill(arr, "4");
        ArrayList<String> row4 = new ArrayList<>(Arrays.asList(arr)) ;
        ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();

        matrix.add(row0);
        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);
        matrix.add(row4);
        for (ArrayList<?> temp : matrix) {
            System.out.println(temp.toString().replaceAll("[\\[\\]\\,\\ ]", ""));
        }
        Point titik = new Point(4,3);
        System.out.println(matrix.get((int) titik.getX()-1 ));
        Object object = (matrix.get((int) titik.getX()-1 )).getClass();
        System.out.println(object);
        int indeksX =(int) titik.getX()-1;
        int indeksY =(int) titik.getY()-1 ;
        (matrix.get(indeksX )).set(indeksY, "X");
        for (ArrayList<?> temp : matrix) {
            System.out.println(temp.toString().replaceAll("[\\[\\]\\,\\ ]", ""));
        }

        System.out.println(matrix.get(1));
    }

}