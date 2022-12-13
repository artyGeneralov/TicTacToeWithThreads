import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public abstract class Game {

	protected static ArrayList<Cell> gameBoard = new ArrayList<Cell>();
	protected static PlayerType nextTurn = PlayerType.CROSS;

	protected String theWinner = "";

	public abstract void playGame();

	protected static void fillEmpty() {
		for (int i = 1; i <= 9; i++)
			gameBoard.add(new Cell(i));
	}

	public void printBoard() {
		if (gameBoard.isEmpty())
			fillEmpty();
		String s = "";
		for (Cell c : gameBoard) {
			if (c.isEmpty())
				s += c.getCoord();
			else
				s += c.getValue().value;
			s += " | ";
			if (c.getCoord() % 3 == 0)
				s += "\n____________\n";
		}
		System.out.println(s);
	}

	protected PlayerType getTurn() {
		return nextTurn;
	}

	public synchronized static ArrayList<Cell> getAllEmptyCells() {
		if (gameBoard.isEmpty())
			fillEmpty();
		ArrayList<Cell> empty_cells = new ArrayList<Cell>();
		for (Cell e : gameBoard)
			if (e.isEmpty()) 
				empty_cells.add(e);
		return empty_cells;
	}

	public void makeMove(PlayerType player, int coordinate) {
		gameBoard.get(coordinate - 1).setValue(player);
	}

	protected boolean isGameEnded() {
			// rows:
			for (int i = 0; i < 9; i += 3)
				if (!gameBoard.get(i).isEmpty() && !gameBoard.get(i + 1).isEmpty() && !gameBoard.get(i + 2).isEmpty()
						&& gameBoard.get(i).getValue() == gameBoard.get(i + 1).getValue()
						&& gameBoard.get(i).getValue() == gameBoard.get(i + 2).getValue()) {
					theWinner = gameBoard.get(i).getValue().value;
					return true;
				}

			// columns:
			for (int i = 0; i < 3; i++)
				if (!gameBoard.get(i).isEmpty() && !gameBoard.get(i + 3).isEmpty() && !gameBoard.get(i + 6).isEmpty()
						&& gameBoard.get(i).getValue() == gameBoard.get(i + 3).getValue()
						&& gameBoard.get(i).getValue() == gameBoard.get(i + 6).getValue()) {
					theWinner = gameBoard.get(i).getValue().value;
					return true;
				}

			// diagonals:
			if (!gameBoard.get(0).isEmpty() && !gameBoard.get(4).isEmpty() && !gameBoard.get(8).isEmpty()
					&& gameBoard.get(0).getValue() == gameBoard.get(4).getValue()
					&& gameBoard.get(0).getValue() == gameBoard.get(8).getValue()) {
				theWinner = gameBoard.get(0).getValue().value;
				return true;
			}
			if (!gameBoard.get(2).isEmpty() && !gameBoard.get(4).isEmpty() && !gameBoard.get(6).isEmpty()
					&& gameBoard.get(2).getValue() == gameBoard.get(4).getValue()
					&& gameBoard.get(2).getValue() == gameBoard.get(6).getValue()) {
				theWinner = gameBoard.get(2).getValue().value;
				return true;
			}


		if (getAllEmptyCells().isEmpty() == true) {
			theWinner = "It's a tie!";
			return true;
		}

		return false;
	}

	public void advanceTurn() {
		nextTurn = this.nextTurn == PlayerType.CROSS ? PlayerType.CIRCLE : PlayerType.CROSS;
	}

}
