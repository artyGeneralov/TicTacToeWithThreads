import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SelfGame extends Game {

	private Semaphore player_1_permit = new Semaphore(1, true);
	private Semaphore player_2_permit = new Semaphore(1, true);

	public SelfGame() {
		fillEmpty();
	}

	public void playGame() {
		SelfPlayer p1 = new SelfPlayer(this, PlayerType.CROSS, player_1_permit);
		SelfPlayer p2 = new SelfPlayer(this, PlayerType.CIRCLE, player_2_permit);

		p1.start();
		p2.start();
		while (!isGameEnded()) {
			try {
				if (!isGameEnded() && nextTurn == PlayerType.CROSS && player_1_permit.availablePermits() == 1)
					player_1_permit.acquire();
				else if(!isGameEnded() && nextTurn == PlayerType.CIRCLE && player_2_permit.availablePermits() == 1)
					player_2_permit.acquire();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("ended, the winner is : " + theWinner);
		p1.interrupt();
		p2.interrupt();

	}

}
