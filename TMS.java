import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.*;


public class TMS {

    private static int row = 10, col = 10;
    private static String[][] board = new String[row][col];
    private static String[][] board_copy = new String[row][col];


    public static void main(String[] args) {
        for(String[] array:board) {
            Arrays.fill(array, "-");
        }
        create_mines();
        Scanner input = new Scanner(System.in);
        int inpx, inpy;

        while(true){
            show_board();
              System.out.print("\nEnter the row and column indexes: ");
            inpx = input.nextInt();
            inpy = input.nextInt();
            if(board_copy[inpx][inpy].equals("*")){
                System.out.println("YOU LOOSE");
                for(int r = 0; r < row; r++){
                    for(int c = 0; c < col; c++){
                        if (board_copy[r][c].equals("*"))
                            board[r][c] = "*";
                    }
                }
                show_board();
                break;
            }
            check_move(inpx, inpy);
            if (!check_win()){
                System.out.println("YOU WIN!");
                for(int r = 0; r < row; r++){
                    for(int c = 0; c < col; c++){
                        if (board_copy[r][c].equals("*"))
                            board[r][c] = "*";
                    }
                }
                show_board();
                break;
            }
        }
    }


    private static void show_board() {
        for(String[] row: board){
            for(String element: row){
                System.out.print(element+"\t");
            }System.out.println();
        }
    }


    private static void create_mines() {
        int n = 10;
        for(String[] array:board_copy) {
            Arrays.fill(array, "-");
        }
        int[][] bc2 = new int[row][col];
        ArrayList<Integer> bc3 = new ArrayList<>();
        Random rand = new Random();
        int k = 1;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                bc2[i][j] = k;
                bc3.add(k);
                k++;
            }
        }for(int i = 0; i < n; i++) {
            k = bc3.get(rand.nextInt(bc3.size()));
            bc3.remove(bc3.indexOf(k));
            for(int r = 0; r < row; r++){
                for(int c = 0; c < col; c++){
                    if (bc2[r][c] == k)
                        board_copy[r][c] = "*";
                }
            }
        }
    }

    private static void check_move(int posx, int posy){
        int mines = 0;
        if(posx-1 >= 0 && posy-1 >= 0)
            mines = (board_copy[posx-1][posy-1].equals("*"))? (mines+1):mines;
        if(posx >= 0 && posy-1 >= 0)
            mines = (board_copy[posx][posy-1].equals("*"))? (mines+1):mines;
        if(posx+1 < row && posy+1 < col)
            mines = (board_copy[posx+1][posy+1].equals("*"))? (mines+1):mines;
        if(posx-1 >= 0 && posy >= 0)
            mines = (board_copy[posx-1][posy].equals("*"))? (mines+1):mines;
        if(posx+1 < row && posy >= 0)
            mines = (board_copy[posx+1][posy].equals("*"))? (mines+1):mines;
        if(posx >= 0 && posy+1 < col)
            mines = (board_copy[posx][posy+1].equals("*"))? (mines+1):mines;
        if(posx-1 >= 0 && posy+1 < col)
            mines = (board_copy[posx-1][posy+1].equals("*"))? (mines+1):mines;
        if(posx+1 < row && posy-1 >= 0)
            mines = (board_copy[posx+1][posy-1].equals("*"))? (mines+1):mines;
        board[posx][posy] = Integer.toString(mines);

        if(mines == 0) {
            if((posx-1) >= 0 && (posy-1) >= 0 && !(Character.isDigit(board[posx-1][posy-1].charAt(0)))) {
                check_move((posx - 1), (posy - 1));
            }
            if(posx >= 0 && (posy-1) >= 0 && !(Character.isDigit(board[posx][posy-1].charAt(0)))) {
                check_move(posx, (posy - 1));
            }
            if((posx+1) < row && (posy+1) < col && !(Character.isDigit(board[posx+1][posy+1].charAt(0)))) {
                check_move((posx + 1), (posy + 1));
            }
            if((posx-1) >= 0 && posy >= 0 && !(Character.isDigit(board[posx-1][posy].charAt(0)))) {
                check_move((posx - 1), posy);
            }
            if((posx+1) < row && posy >= 0 && !(Character.isDigit(board[posx+1][posy].charAt(0)))) {
                check_move((posx + 1), posy);
            }
            if((posx >= 0 && (posy+1) < col) && !(Character.isDigit(board[posx][posy+1].charAt(0)))) {
                check_move(posx, (posy + 1));
            }
            if((posx-1) >= 0 && (posy+1) < col && !(Character.isDigit(board[posx-1][posy+1].charAt(0)))) {
                check_move((posx - 1), (posy + 1));
            }
            if((posx+1) < row && (posy-1) >= 0 && !(Character.isDigit(board[posx+1][posy-1].charAt(0)))) {
                check_move((posx + 1), (posy - 1));
            }
        }
    }


    private static boolean check_win(){
        boolean flag = false;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if (board_copy[i][j].equals("*"))
                    continue;
                flag = (!(Character.isDigit(board[i][j].charAt(0))));
                if (flag)
                    break;
                }if (flag)
                    break;
        }return flag;
    }
}