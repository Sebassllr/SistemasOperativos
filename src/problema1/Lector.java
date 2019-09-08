package problema1;

import static problema1.Principal.readCount;
import static problema1.Principal.readLock;
import static problema1.Principal.writeLock;

public class Lector implements Runnable {

	public void run() {
		try {
			readLock.acquire();
			readCount.getAndIncrement();
			if (readCount.equals(new Integer(1)))
				writeLock.acquire();

			readLock.release();

			System.out.println("Hilo " + Thread.currentThread().getName() + " leyendo ");
			Thread.sleep(1500);
			System.out.println("Hilo " + Thread.currentThread().getName() + " ha terminado de leer");

			readLock.acquire();
			readCount.getAndDecrement();
			if (readCount.equals(new Integer(0)))
				writeLock.release();

			readLock.release();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
