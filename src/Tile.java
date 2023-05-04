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
                tile[i][j] = "E";
                //k++;
            }
        }
    }

    public void printTile() {
        /* System.out.print("\033[H\033[2J");  
        System.out.flush(); */ 
        //System.out.println();
        
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

    public String getTile(int X, int Y){
        return tile[Y-1][X-1];
    }

    public int getCountNonEmpty(){
        int count = 0;
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < length; j++) {
                if(tile[i][j] != "E"){
                    count++;
                }
            }
        }
        return count;
    }

}
