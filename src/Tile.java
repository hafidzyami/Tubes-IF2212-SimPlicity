import java.util.Arrays;

public class Tile  {
    //private String[][] sketch;
    private int length;
    private int width;
    private String [][] tile;
    
    public Tile(int length,int width) {
        this.length = length;
        this.width = width;
        tile = new String[width][length];
        //int k = 0;
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < length; j++) {
                //String s =String.valueOf(k);
                // read information from somewhere
                tile[i][j] = "E";
                //k++;
            }
        }
    }

    public void printTile() {
        for(String[] temp1 : tile) {
            for(String temp2 : temp1) {
                System.out.print(temp2 +" ");
            }
            System.out.println();
        }
    }

    public void changeTile(String S,int X,int Y) {
        tile[Y-1][X-1] = S;
    } 

    public void fromRoomExpand() {
        
    }
}
