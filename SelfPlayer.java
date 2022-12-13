import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

// Computer Player
public class SelfPlayer extends Player {
	private final int SLEEP_TIME = 500;

	public SelfPlayer(Game context, PlayerType type, Semaphore semaphore) {
		super(context, type, semaphore);
	}

	@Override
	public void run() {
		context.printBoard();
		while (!Thread.interrupted()) {
			try {
				if (semaphore.availablePermits() == 0) {
					Thread.sleep(SLEEP_TIME);
					ArrayList<Cell> empty_cells = context.getAllEmptyCells();
					String s = "";

					System.out.println(s);
					Random rnd = new Random();
					int next = rnd.nextInt(empty_cells.size());
					context.makeMove(type, empty_cells.get(next).getCoord());
					System.out.println(type.value + " has made a move\n");
					context.printBoard();
					context.advanceTurn();
					semaphore.release();
				}
			} catch (InterruptedException e) {
			} catch (IllegalArgumentException e) {}
		}
	}
}
