package lab1;

import java.util.Scanner;

public class TicTacToe {
	private static final int BOARD_SIZE = 3;
	private static final String[] PLAYERS = { "X", "O" };
	private int playerIndex;
	private Board board;

	public TicTacToe() {
		board = new Board(BOARD_SIZE);
	}

	public void start() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				String player = getPlayer();
				int position = readPosition(scanner, player);
				while (!board.put(player, position)) {
					System.out.println("Въвели сте неправилна" + " позиция или вече е играно на тази позиция");
					position = readPosition(scanner, player);
				}
				
				if(board.isWinner(player, position)) {
					System.out.println(board);
					System.out.printf("Играч %s спечели играта", player);
					break;
				} else if(board.isDraw(player, position)) {
					System.out.println(board);
					System.out.println("Играта свършва наравно");
					break;
				}
			}
		}
	}

	private String getPlayer() {
		if (playerIndex >= PLAYERS.length) {
			playerIndex = 0;
		}

		return PLAYERS[playerIndex++];
	}

	private int readPosition(Scanner scanner, String player) {
		promtInput(player);
		String line = scanner.nextLine();
		while (!line.matches("\\d+")) {
			System.out.println("Въведения вход е невалиден моля въведете отново");
			promtInput(player);
			line = scanner.nextLine();
		}

		return Integer.parseInt(line);
	}

	private void promtInput(String player) {
		System.out.println(board);
		System.out.printf("Играч %s, моля въведете своя ход (1-%d)>", player, BOARD_SIZE * BOARD_SIZE);

	}

}
