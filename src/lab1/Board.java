package lab1;

import java.util.Arrays;

public class Board {

	private String[][] board;
	private int size;

	Board(int size) {
		this.size = size;
		board = createBoard(size);
	}

	private String[][] createBoard(int size) {
		String[][] board = new String[size][size];
		int current = 1;

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = String.valueOf(current++);
			}
		}

		return board;
	}

	private String formatCell(String cell) {
		if (cell.length() == 1)
			return String.format(" %s ", cell);

		else if (cell.length() == 2)
			return String.format(" %s", cell);

		else
			return cell;
	}

	private int getColByPosition(int position) {
		return (position - 1) % size;
	}

	private int getRowByPosition(int position) {
		return (position - 1) / size;
	}

	private String getRowSeparator() {
		String[] dashes = new String[size];
		Arrays.fill(dashes, "---");
		return String.join("|", dashes);
	}
	
	private boolean hasWinningCol(String sign, int col) {
		boolean hasWinningCol = true;
		
		for (int row = 0; row < size; row++) {
			if(!board[row][col].equals(sign)) {
				hasWinningCol = false;
			}
		}
		
		return hasWinningCol;
	}
	
	private boolean hasWinningColPotential(String sign) {		
		for (int col = 0; col < size; col++) {
			boolean hasWinningColPotential = true;
			for (int row = 0; row < size; row++) {
				if(!isNumeric(board[row][col]) && !board[row][col].equals(sign)) {
					hasWinningColPotential = false;
				}
			}
			if (hasWinningColPotential) return true;
			
		}
		
		return false;
	}
	
	private boolean hasWinningDiagonal(String sign) {
		boolean hasWinningMainDiagonal = true;
		
		for (int row = 0; row < size; row++) {
			if (!board[row][row].equals(sign)) {
				hasWinningMainDiagonal = false;
			}
		}
		
		boolean hasWinningOppositeDiagonal = true;
		
		for (int row = 0; row < size; row++) {
			if (!board[row][size - row - 1].equals(sign)) {
				hasWinningOppositeDiagonal = false;
			}
		}
		
		return hasWinningMainDiagonal || hasWinningOppositeDiagonal;
	}
	
	private boolean hasWinningDiagonalPotential(String sign) {
		boolean hasWinningMainDiagonalPotential = true;
		
		for (int row = 0; row < size; row++) {
			if (!board[row][row].equals(sign) && !isNumeric(board[row][row])) {
				hasWinningMainDiagonalPotential = false;
			}
		}
		
		boolean hasWinningOppositeDiagonalPotential = true;
		
		for (int row = 0; row < size; row++) {
			if (!board[row][size - row - 1].equals(sign) &&
					!isNumeric(board[row][size - row - 1])) {
				hasWinningOppositeDiagonalPotential = false;
			}
		}
		
		return hasWinningMainDiagonalPotential || hasWinningOppositeDiagonalPotential;
	}
	
	private boolean hasWinningRow(String sign, int row) {
		boolean hasWinningRow = true;
		
		for (int col = 0; col < size; col++) {
			if(!board[row][col].equals(sign)) {
				hasWinningRow = false;
			}
		}
		
		return hasWinningRow;
	}
	
	private boolean hasWinningRowPotential(String sign) {		
		for (int row = 0; row < size; row++) {
			boolean hasWinningRowPotential = true;
			for (int col = 0; col < size; col++) {
				if(!isNumeric(board[row][col]) && !board[row][col].equals(sign)) {
					hasWinningRowPotential = false;
				}
			}
			if (hasWinningRowPotential) return true;
			
		}
		
		return false;
	}
	
	public boolean isDraw(String sign, int position) {
		
		return !hasWinningRowPotential(sign) && 
				!hasWinningColPotential(sign) && !hasWinningDiagonalPotential(sign);
	}

	private boolean isNumeric(String arg) {
		return arg.matches("\\d+");
	}
	
	private boolean isOccupied(String arg) {
		return isNumeric(arg);
	}
	
	public boolean isWinner(String sign, int position) {
		int row = getRowByPosition(position);
		int col = getColByPosition(position);
		
		return hasWinningRow(sign, row) || 
				hasWinningCol(sign, col) || hasWinningDiagonal(sign);
	}	

	public boolean put(String sign, int position) {
		int row = getRowByPosition(position);
		int col = getColByPosition(position);

		if (position > 0 && position <= size * size && isOccupied(board[row][col])) {
			board[row][col] = sign;
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				toString.append(formatCell(board[row][col]));

				if (col != size - 1)
					toString.append("|");
			}
			toString.append(System.lineSeparator());

			if (row != size - 1)
				toString.append(getRowSeparator());

			toString.append(System.lineSeparator());
		}

		return toString.toString();
	}

}
