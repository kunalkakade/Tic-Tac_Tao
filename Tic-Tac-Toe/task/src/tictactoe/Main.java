package tictactoe;

import java.util.Scanner;

public class Main {

	public final static char EMPTY_CELL = ' ';

	private void printTT(String input) {
		int inputIndex = 0;
		System.out.println("---------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(input.charAt(inputIndex++) + " ");
			}
			System.out.println("|");
		}
		System.out.println("---------");
	}

	private void printMatrix(char[][] input) {
		System.out.println("---------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(input[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("---------");
	}

	private boolean checkColumn(char[][] matrix, int column, char value) {
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][column] != value)
				return false;
		}
		return true;
	}

	private boolean checkRow(char[][] matrix, int row, char value) {
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[row][i] != value)
				return false;
		}
		return true;
	}

	private boolean checkDiagonal(char[][] matrix, int side, char value) {
		if (side == 0) {
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[i][i] != value)
					return false;
			}
		}
		for (int i = 2; i >= 0; i--) {
			if (matrix[2 - i][i] != value)
				return false;
		}
		return true;
	}

	public boolean checkWin(char player, char[][] matrix) {

		if (checkRow(matrix, 0, player) || checkRow(matrix, 1, player) || checkRow(matrix, 2, player)) {
			return true;
		} else if (checkColumn(matrix, 0, player) || checkColumn(matrix, 1, player) || checkColumn(matrix, 2, player)) {
			return true;
		} else if (checkDiagonal(matrix, 0, player) || checkDiagonal(matrix, 1, player)) {
			return true;
		}
		return false;
	}

	public static boolean checkEmptyCell(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == EMPTY_CELL)
					return true;
			}
		}
		return false;
	}

	public static int getCount(char[][] matrix, char player) {
		int cnt = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == player)
					cnt++;
			}
		}
		return cnt;
	}


	public static char[][] getMatrixFromInput(String input) {
		char[][] matrix = new char[3][3];
		int inputIndex = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrix[i][j] = input.charAt(inputIndex++);
			}
		}
		return matrix;
	}

	private int getCorrectIndex(int x) {
		switch (x) {
			case 1:
				return 2;
			case 2:
				return 1;
			case 3:
				return 0;
		}
		return 0;
	}

	public char getNextPlayer(char currentPlayer) {
		return currentPlayer == 'X' ? 'O' : 'X';
	}

	public int getCoordinate(String ss, int index) throws Exception {

		try {
			Integer x =  Integer.parseInt(ss.split(" ")[index]);
			if (x < 1 || x > 3 ){
				System.out.println("Coordinates should be from 1 to 3!");
				throw new Exception("Exception message");
			}
			return x;
		}catch (Exception e){
			throw e;
		}
	}

	public void getStatus(String input) {

		char[][] matrix = getMatrixFromInput(input);
		printTT(input);
		char nextPlayer = 'X';
		Scanner sc = new Scanner(System.in);
		boolean draw = false;
		while (!draw) {
			System.out.print("Enter the coordinates: ");
			try {
				String ss = sc.nextLine();
				int x = getCoordinate(ss, 0);
				int y = getCoordinate(ss, 1);

				if (matrix[getCorrectIndex(y)][x - 1] != EMPTY_CELL) {
					System.out.println("This cell is occupied! Choose another one!");
					continue;
				}
				matrix[getCorrectIndex(y)][x - 1] = nextPlayer;
				printMatrix(matrix);

				if (checkWin('X', matrix)) {
					System.out.println("X wins");
					return;
				} else if (checkWin('O', matrix)) {
					System.out.println("O wins");
					return;
				} else if (!checkEmptyCell(matrix)) {
					System.out.println("Draw");
					return;
				}
				nextPlayer = getNextPlayer(nextPlayer);
			}catch (NumberFormatException e){
				System.out.println("You should enter numbers!");
				continue;
			}
			catch (Exception e) {
				continue;
			}
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.getStatus("         ");
	}
}
