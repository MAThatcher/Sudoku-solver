import java.util.*;
class Sudoku{
  
  Integer board[][] = { 
      {9,0,3,2,0,0,7,0,6},
      {0,0,0,0,0,0,0,0,0},
      {2,4,0,8,0,0,0,0,0},
      {0,0,0,0,5,0,8,0,0},
      {0,2,6,0,0,0,4,3,0},
      {0,0,1,0,8,0,0,0,0},
      {0,0,0,0,0,5,0,6,7},
      {0,0,0,0,0,0,0,0,0},
      {1,0,4,0,0,6,9,0,8} 
    };
  
  
  // Fuction to display contents of board
  public void displayBoard(){
    for (int i = 0 ; i < 9 ; i++){
      for (int j = 0; j < 9 ; j++){
        System.out.print(String.valueOf(board[i][j]) + " ");
        if (j == 2 || j==5){
          System.out.print("| ");
        }
      }
      System.out.println();
      if (i ==2 || i == 5){
        System.out.println("------+------+------");
      }
    }
    System.out.println();
  }
  
  // Function do find if a givin number can be placed in a particular box
  // Takes coordinates of box as x and y. Number to be tested is num
  // If num cannot fit into box then return false
  // If it is a legal placement, return true
  private Boolean possible(int x, int y, int num){
    
    //check virtical line for num
    for (int i = 0 ; i < 9 ; i++){
      if (board[y][i] == num){
        return false;
      }
    }
    
    //check horizontal line for num
    for (int i = 0 ; i < 9 ; i++){
      if (board[i][x] == num){
        return false;
      }
    }
    
    int x_0 = (int)Math.floor((x/3)*3);
    int y_0 = (int)Math.floor((y/3)*3);
    
    // check local box for number
    for (int i = 0 ; i < 3 ; i++){
      for (int j = 0 ; j < 3 ; j++){
        if (board[y_0+i][x_0+j] == num){
          return false;
        }
      }
    }
    return true;
  }
  
  public Boolean solve(){
    //iterate through the board left to right
    for (int y = 0 ; y < 9 ; y++){
      for (int x = 0 ; x <9 ; x++){
        // if we come across an empty space
        if (board[y][x] == 0){
          //look through numbers 1-9 and check if it is legal to place
          for (int i = 1; i < 10; i++){
            if (possible(x,y,i)){
              // if number is legal then fill box with that number
              board[y][x] = i;
              //recursivly call solve()
              if(solve()){
                return true;
              }
              //If the board is not solved set this location back to 0
              //wrong number was tried
              else {
                board[y][x] = 0;
              }
            }
          }
        return false;
        }
      }
    }
    return true;
  }
  
  public static void main(String[] args){
    Sudoku sudoku = new Sudoku();
    sudoku.solve();
    sudoku.displayBoard();
  }
    
}
  