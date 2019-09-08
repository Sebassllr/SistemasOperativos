package problema3;

public class Semaphore {
	
	private int counter;
	
	Semaphore(int counter) {
		this.counter = counter;
	}
	
	int getCounter() {
		return this.counter;
	}
	
	void _wait() {
		this.counter--;
	}
	
	void signal() {
		this.counter++;
	}

}
