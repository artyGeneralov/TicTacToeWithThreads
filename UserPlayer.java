import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class UserPlayer extends Player {
	private final int SLEEP_TIME = 500;

	public UserPlayer(Game context, PlayerType type, Semaphore semaphore) {
		super(context, type, semaphore);
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			Scanner input = new Scanner(System.in);

			if (semaphore.availablePermits() == 0) {
				ArrayList<Cell> empty_cells = context.getAllEmptyCells();

				boolean picked = false;
				int answer;
				do {
					System.out.println("Player " + this.type + " Please type a number for your move.");
					answer = input.nextInt();
					for (Cell c : empty_cells)
						if (c.getCoord() == answer)
							picked = true;
					if (picked == false)
						System.out.println("Wrong number.");
				} while (picked == false);

				context.makeMove(type, answer);
				context.printBoard();
				context.advanceTurn();
				semaphore.release();
			}

		}
	}

}
