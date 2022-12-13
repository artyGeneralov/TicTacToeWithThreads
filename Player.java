import java.util.concurrent.Semaphore;

public abstract class Player extends Thread {
	protected PlayerType type;
	protected Semaphore semaphore;
	protected Game context;
	
	public Player(Game context, PlayerType type, Semaphore semaphore) {
		this.type = type;
		this.semaphore = semaphore;
		this.context = context;
	}

	public PlayerType getType() {
		return this.type;
	}
}
