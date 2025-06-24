import java.util.ArrayList;
import java.util.List;

public class Board {
    Box[][] board = new Box[8][8];
    List<Pice> pices = new ArrayList<Pice>();

    public void initPices(){
        pices.add()
    }

    public void createBoard(){
        for(Box[] row : board ){}
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                // j = x, i = y
                if(i+j%2 == 0){
                    board[j][i].color = 0;
                    System.out.print(" x ");
                }else {
                    board[j][i].color = 1;
                    System.out.print(" o ");
                }
            }
            System.out.println();
        }
    }
}
