/*
 * A tic-tac-toe board is represented by a two dimensional vector. X is 
 * represented by :x, O is represented by :o, and empty is represented by 
 * :e. A player wins by placing three Xs or three Os in a horizontal, 
 * vertical, or diagonal row. Write a function which analyzes a 
 * tic-tac-toe board and returns :x if X has won, :o if O has won, and 
 * nil if neither player has won.
 * 
 * Example input:
 * [[:x :e :o]
 * [:x :e :e]
 * [:x :e :o]]
 * Result:
 * :x
 * */

public class TicTacToe {

  public static final char N = 'N';
	public static final char E = 'E';
	public static final char X = 'X';
	public static final char O = 'O';
	
	public static final char[] VALID_VECTOR_VALUE = new char[] {E,X,O};
	public static final int WIN_LINKS = 3;
	
	public class Vector {
		char value = E;
		
		// variables keep counting max linked not E vectors so far
		int links_leftup = 0;  // leftup -> rightdown diagonal direction
		int links_up = 0;      // up -> down vertical direction
		int links_rightup = 0; // rightup -> leftdown diagonal direction
		int links_left = 0;    // left -> right horizontal direction
		
		public Vector(char v) {
			value = v;
		}
	}
	
	public static char analyze(Vector[][] board) {
		for (int row=0; row<board.length; row++) {
			for (int col=0; col<board[row].length; col++) {
				if (board[row][col].value == E) {
					// empty vector, reset counters
					board[row][col].links_leftup = 0;
					board[row][col].links_up = 0;
					board[row][col].links_rightup = 0;
					board[row][col].links_left = 0;
					continue;
				}
				// current vector has value either X or O
				// check left up vector
				if (row > 0 && col > 0) {
					// exists
					if (board[row][col].value == board[row-1][col-1].value) {
						// same vector values, increase count by 1
						board[row][col].links_leftup = 
							          board[row-1][col-1].links_leftup + 1;
						if (board[row][col].links_leftup == WIN_LINKS)
							// links count matches winning requirement
							return board[row][col].value;
					} else {
						// different vector values, reset counter
						board[row][col].links_leftup = 1;
					}
				} else {
					// no exists, start here
					board[row][col].links_leftup = 1;
				}
				// check up vector
				if (row > 0) {
					// exists
					if (board[row][col].value == board[row-1][col].value) {
						// same vector values, increase count by 1
						board[row][col].links_up = 
						          board[row-1][col].links_up + 1;
						if (board[row][col].links_up == WIN_LINKS)
							// links count matches winning requirement
							return board[row][col].value;
					} else {
						// different vector values, reset counter
						board[row][col].links_up = 1;
					}
				} else {
					// no exists, start here
					board[row][col].links_up = 1;
				}
				// check right up vector
				if (row > 0 && col < board[row].length-1) {
					// exists
					if (board[row][col].value == board[row-1][col+1].value) {
						// same vector values, increase count by 1
						board[row][col].links_rightup = 
						          board[row-1][col+1].links_rightup + 1;
						if (board[row][col].links_rightup == WIN_LINKS)
							// links count matches winning requirement
							return board[row][col].value;
					} else {
						// different vector values, reset counter
						board[row][col].links_rightup = 1;
					}
				} else {
					// no exists, start here
					board[row][col].links_rightup = 1;
				}
				// check left vector
				if (col > 0) {
					// exists
					if (board[row][col].value == board[row][col-1].value) {
						// same vector values, increase count by 1
						board[row][col].links_left = 
						          board[row][col-1].links_left + 1;
						if (board[row][col].links_left == WIN_LINKS)
							// links count matches winning requirement
							return board[row][col].value;
					} else {
						// different vector values, reset counter
						board[row][col].links_left = 1;
					}
				} else {
					// no exists, start here
					board[row][col].links_left = 1;
				}
			}
		}
		return N;
	}
	
	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
		Vector[][] board = new Vector[][]
				{{t.new Vector(X), t.new Vector(E), t.new Vector(O)},
			     {t.new Vector(X), t.new Vector(E), t.new Vector(E)},
			     {t.new Vector(X), t.new Vector(E), t.new Vector(O)}};
		char result = analyze(board);
		System.out.println(result);
	}
}
