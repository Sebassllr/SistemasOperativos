package problema5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tenedor {
	Lock up = new ReentrantLock();
	private final int id;

	public Tenedor(int id) {
		this.id = id;
	}

	public boolean recogerTenedor(Filosofo filo, String lado) throws InterruptedException {
		if (up.tryLock(10, TimeUnit.MILLISECONDS)) {
			System.out.println(filo + " recogió el " + this + " que se encuentra a su lado" + lado);
			return true;
		}
		return false;
	}

	public void dejarTenedor(Filosofo filo, String lado) {
		up.unlock();
		System.out.println(filo + " dejó el " + this + " " + lado);
	}

	@Override
	public String toString() {
		return "Tenedor-" + id;
	}

	public int getId() {
		return id;
	}
}
